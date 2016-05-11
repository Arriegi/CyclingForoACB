package controllers;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import controllers.forms.NewUser;
import controllers.forms.User;
import model.cyclists.Team;
import model.points.CyclistRace;
import model.points.PlayerTeam;
import model.races.Race;
import model.races.RaceName;
import model.users.Player;
import play.Logger;
import play.data.Form;
import play.data.validation.ValidationError;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import utils.DBUtils;
import views.html.index;
import views.html.input;
import views.html.login;
import views.html.signin;
import views.html.prices;
import views.html.playerteam;
import views.html.classifications;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.*;

public class Application extends Controller {

    public static Result index() {
        if (session("email")!=null) {
            return ok(index.render("Hello " + session("email")));
        } else {
            return login();
        }
    }

    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect("/login");
    }

    @Transactional
    @Security.Authenticated(AdminSecured.class)
    public static Result adminRace(String raceName, int year) {
        RaceName name = RaceName.valueOf(raceName.toUpperCase());
        EntityManager em = JPA.em();
        List<Race> races = em.createQuery("select r from races r where name = :name and year = :year", Race.class).setParameter("name", name).setParameter("year",year).getResultList();
        if (races == null || races.size() == 0) {
            return status(400,raceName + " " + year + " not found");
        }
        Race race = races.get(0);
        return ok(prices.render(race));
    }

    @Transactional
    @Security.Authenticated(AdminSecured.class)
    public static Result setClassifications(String raceName, int year) {
        RaceName name = RaceName.valueOf(raceName.toUpperCase());
        EntityManager em = JPA.em();
        List<Race> races = em.createQuery("select r from races r where name = :name and year = :year", Race.class).setParameter("name", name).setParameter("year",year).getResultList();
        if (races == null || races.size() == 0) {
            return status(400,raceName + " " + year + " not found");
        }
        Race race = races.get(0);
        List<CyclistRace> cyclists = JPA.em().createQuery("select c from cyclistraces c where c.race = :race", CyclistRace.class)
                .setParameter("race", race).getResultList();
        List<Team> teams = JPA.em().createNativeQuery("SELECT * from teams where id in ( select distinct c.teamId from cyclistraces" +
                " as cr inner join cyclists as c on cr.cyclistId = c.id where cr.raceId = " + race.getId() + ")",Team.class).getResultList();
        return ok(classifications.render(race,cyclists,teams));
    }

    public static Result signIn() {
        return ok(signin.render(Form.form(NewUser.class)));
    }

    @Transactional
    public static Result createAccount() {
        Form<NewUser> formData = Form.form(NewUser.class).bindFromRequest();
        if (formData.hasErrors()) {
            //Show all errors in form
            String stringErrors = "";
            List<ValidationError> emailErrors = formData.errors().get("email");
            List<ValidationError> pwdErrors = formData.errors().get("password");
            if (emailErrors != null && emailErrors.size()>0) {
                for (ValidationError error : emailErrors) {
                    stringErrors = stringErrors + error.message() + ",";
                }
            }
            if (pwdErrors != null && pwdErrors.size()>0) {
                for (ValidationError error : pwdErrors) {
                    stringErrors = stringErrors + error.message() + ",";
                }
            }
            stringErrors = stringErrors.substring(0,stringErrors.lastIndexOf(","));
            flash("error", stringErrors);
            return badRequest(signin.render(formData));
        } else {
            NewUser user = formData.get();
            Player player = new Player();
            player.setEmail(user.getEmail());
            player.setAlreadyMd5Password(user.getPassword());
            player.setSignInDate(new Date());
            EntityManager em = JPA.em();
            try {
                em.persist(player);
            } catch (PersistenceException e) {
                return status(400,"user with that email exists already");
            }
            em.flush();
            session("email",user.getEmail());
            return redirect("/");
        }
    }

    @Transactional
    public static Result login() {
        if (session().get("email") != null) {
            return redirect("/");
        } else {
            return ok(login.render(Form.form(User.class)));
        }
    }

    @Security.Authenticated(AdminSecured.class)
    public static Result input() {
        return ok(input.render());
    }

    @Transactional
    public static Result authenticate() {
        Form<User> formData = Form.form(User.class).bindFromRequest();
        if (formData.hasErrors()) {
            flash("error", "Please correct errors above.");
            return badRequest(login.render(formData));
        } else {
            User user = formData.get();
            session().clear();
            session("email",user.getEmail());
            return ok(index.render("Hello " + user.getEmail()));
        }
    }

    @Security.Authenticated(LoggedSecured.class)
    @Transactional
    public static Result getTeamForRace(long id) {
        Race race = JPA.em().find(Race.class,id);
        List<CyclistRace> cyclists = JPA.em().createQuery("select c from cyclistraces c where c.race = :race", CyclistRace.class).setParameter("race", race).getResultList();
        Player player = DBUtils.getPlayerByEmail(session().get("email"));
        if (player == null || race == null) {
            return badRequest("Race or player doesn't exist");
        }
        PlayerTeam team = DBUtils.getPlayerTeam(race,player);
        return ok(playerteam.render(team, cyclists,race, player));
    }

    @Security.Authenticated(LoggedSecured.class)
    @Transactional
    public static Result getTeamForLastRace() {
        //Get next available race
        Config conf = ConfigFactory.load();
        List<String> availables = conf.getStringList("races.availables");
        List<Race> races = new ArrayList<Race>();
        EntityManager em = JPA.em();
        Race race;
        RaceName name = RaceName.valueOf(availables.get(0).split("-")[0]);
        int year = Integer.parseInt(availables.get(0).split("-")[1]);
        try {
            race = em.createQuery("select r from races r where r.name = :name and r.year = :year", Race.class)
                    .setParameter("name",name).setParameter("year",year).getSingleResult();
        } catch (Exception e) {
            Logger.warn(e.getMessage());
            return badRequest("No available races right now");
        }
        List<CyclistRace> cyclists = JPA.em().createQuery("select c from cyclistraces c where c.race = :race order by c.price desc", CyclistRace.class).setParameter("race", race).getResultList();
        Player player = DBUtils.getPlayerByEmail(session().get("email"));
        if (player == null || race == null) {
            return badRequest("Race or player doesn't exist");
        }
        PlayerTeam team = DBUtils.getPlayerTeam(race,player);
        return ok(playerteam.render(team, cyclists, race, player));
    }

}
