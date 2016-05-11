package controllers;

import model.cyclists.Cyclist;
import model.points.CyclistRace;
import model.points.PlayerTeam;
import model.races.Race;
import model.races.RaceName;
import model.races.Stage;
import model.users.Player;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 04/02/2015.
 */
public class PlayerTeams extends Controller{

    public static final String KEY_PLAYER_ID = "playerId";
    public static final String KEY_RACE_ID = "raceId";
    public static final String KEY_CYCLIST1 = "cyclist1";
    public static final String KEY_CYCLIST2 = "cyclist2";
    public static final String KEY_CYCLIST3 = "cyclist3";
    public static final String KEY_CYCLIST4 = "cyclist4";
    public static final String KEY_CYCLIST5 = "cyclist5";
    public static final String KEY_CYCLIST6 = "cyclist6";
    public static final String KEY_CYCLIST7 = "cyclist7";
    public static final String KEY_CYCLIST8 = "cyclist8";
    public static final String KEY_CYCLIST9 = "cyclist9";


    @Transactional
    public static Result list() {
        EntityManager em = JPA.em();
        List<PlayerTeam> teams = em.createQuery("select t from playerteams t", PlayerTeam.class).getResultList();
        return ok(Json.toJson(teams));
    }

    @Transactional
    public static Result get(long id) {
        PlayerTeam team = JPA.em().find(PlayerTeam.class, id);
        if (team != null) {
            return ok(Json.toJson(team));
        } else {
            return ok(Json.newObject());
        }
    }

    @Transactional
    public static Result create() {
        Http.RequestBody body = request().body();
        final Map<String, String[]> values = body.asFormUrlEncoded();
        if (values.containsKey(KEY_PLAYER_ID) && values.containsKey(KEY_RACE_ID) && values.containsKey(KEY_CYCLIST1) && values.containsKey(KEY_CYCLIST2) && values.containsKey(KEY_CYCLIST3) &&
                values.containsKey(KEY_CYCLIST4) && values.containsKey(KEY_CYCLIST5) && values.containsKey(KEY_CYCLIST6) && values.containsKey(KEY_CYCLIST7) && values.containsKey(KEY_CYCLIST8) &&
                values.containsKey(KEY_CYCLIST9)) {
            EntityManager em = JPA.em();
            Player player = em.find(Player.class, StringUtils.getLong(values,KEY_PLAYER_ID));
            Race race = em.find(Race.class,StringUtils.getLong(values,KEY_RACE_ID));
            if (player == null || race == null) {
                return status(400, "race or player ids doesn't exist");
            }
            CyclistRace cyclist1 = em.find(CyclistRace.class,StringUtils.getLong(values,KEY_CYCLIST1));
            CyclistRace cyclist2 = em.find(CyclistRace.class,StringUtils.getLong(values,KEY_CYCLIST2));
            CyclistRace cyclist3 = em.find(CyclistRace.class,StringUtils.getLong(values,KEY_CYCLIST3));
            CyclistRace cyclist4 = em.find(CyclistRace.class,StringUtils.getLong(values,KEY_CYCLIST4));
            CyclistRace cyclist5 = em.find(CyclistRace.class,StringUtils.getLong(values,KEY_CYCLIST5));
            CyclistRace cyclist6 = em.find(CyclistRace.class,StringUtils.getLong(values,KEY_CYCLIST6));
            CyclistRace cyclist7 = em.find(CyclistRace.class,StringUtils.getLong(values,KEY_CYCLIST7));
            CyclistRace cyclist8 = em.find(CyclistRace.class,StringUtils.getLong(values,KEY_CYCLIST8));
            CyclistRace cyclist9 = em.find(CyclistRace.class,StringUtils.getLong(values,KEY_CYCLIST9));
            if (cyclist1 == null || cyclist2 == null || cyclist3 == null || cyclist4 == null || cyclist5 == null ||
                    cyclist6 == null || cyclist7 == null || cyclist8 == null || cyclist9 == null) {
                return status(400,"at least 1 cyclist doesn't exist");
            }
            PlayerTeam team = new PlayerTeam();
            team.setRace(race);
            team.setPlayer(player);
            team.setCyclist1(cyclist1);
            team.setCyclist2(cyclist2);
            team.setCyclist3(cyclist3);
            team.setCyclist4(cyclist4);
            team.setCyclist5(cyclist5);
            team.setCyclist6(cyclist6);
            team.setCyclist7(cyclist7);
            team.setCyclist8(cyclist8);
            team.setCyclist9(cyclist9);
            if (team.getCost() > PlayerTeam.SECOND_DIVISION_COST) {
                return badRequest("Too expensive team");
            }
            try {
                //Delete possible user teams for this race
                em.createNativeQuery("DELETE FROM playerteams WHERE playerId = " + player.getId() + " AND raceId = " + race.getId()).executeUpdate();
                em.persist(team);
                em.flush();
            } catch(PersistenceException e) {
                return status(400,"team for that player and race already exists");
            }
            return ok(Json.toJson(team));
        } else {
            return status(422,"Missing one or more parameters: playerId, raceId, cyclist1, cyclist2... cyclist9");
        }
    }

    @Transactional
    public static Result update(long id) {
        Http.RequestBody body = request().body();
        final Map<String, String[]> values = body.asFormUrlEncoded();

        EntityManager em = JPA.em();

        PlayerTeam team = em.find(PlayerTeam.class,id);
        if (team == null) {
            return badRequest("Team not found for id " + id);
        }

        if (values.containsKey(KEY_CYCLIST1)) {
            CyclistRace cyclist1 = em.find(CyclistRace.class,StringUtils.getLong(values,KEY_CYCLIST1));
            if (cyclist1 == null) {
                badRequest("Cyclist1 doesnt exist for id "+StringUtils.getLong(values,KEY_CYCLIST1));
            } else {
                team.setCyclist1(cyclist1);
            }
        }
        if (values.containsKey(KEY_CYCLIST2)) {
            CyclistRace cyclist2 = em.find(CyclistRace.class,StringUtils.getLong(values,KEY_CYCLIST2));
            if (cyclist2 == null) {
                badRequest("Cyclist1 doesnt exist for id "+StringUtils.getLong(values,KEY_CYCLIST2));
            } else {
                team.setCyclist2(cyclist2);
            }
        }
        if (values.containsKey(KEY_CYCLIST3)) {
            CyclistRace cyclist3 = em.find(CyclistRace.class,StringUtils.getLong(values,KEY_CYCLIST3));
            if (cyclist3 == null) {
                badRequest("Cyclist1 doesnt exist for id "+StringUtils.getLong(values,KEY_CYCLIST3));
            } else {
                team.setCyclist3(cyclist3);
            }
        }
        if (values.containsKey(KEY_CYCLIST4)) {
            CyclistRace cyclist4 = em.find(CyclistRace.class,StringUtils.getLong(values,KEY_CYCLIST4));
            if (cyclist4 == null) {
                badRequest("Cyclist1 doesnt exist for id "+StringUtils.getLong(values,KEY_CYCLIST4));
            } else {
                team.setCyclist4(cyclist4);
            }
        }
        if (values.containsKey(KEY_CYCLIST5)) {
            CyclistRace cyclist5 = em.find(CyclistRace.class,StringUtils.getLong(values,KEY_CYCLIST5));
            if (cyclist5 == null) {
                badRequest("Cyclist1 doesnt exist for id "+StringUtils.getLong(values,KEY_CYCLIST5));
            } else {
                team.setCyclist5(cyclist5);
            }
        }
        if (values.containsKey(KEY_CYCLIST6)) {
            CyclistRace cyclist6 = em.find(CyclistRace.class,StringUtils.getLong(values,KEY_CYCLIST6));
            if (cyclist6 == null) {
                badRequest("Cyclist1 doesnt exist for id "+StringUtils.getLong(values,KEY_CYCLIST6));
            } else {
                team.setCyclist6(cyclist6);
            }
        }
        if (values.containsKey(KEY_CYCLIST7)) {
            CyclistRace cyclist7 = em.find(CyclistRace.class,StringUtils.getLong(values,KEY_CYCLIST7));
            if (cyclist7 == null) {
                badRequest("Cyclist1 doesnt exist for id "+StringUtils.getLong(values,KEY_CYCLIST7));
            } else {
                team.setCyclist7(cyclist7);
            }
        }
        if (values.containsKey(KEY_CYCLIST8)) {
            CyclistRace cyclist8 = em.find(CyclistRace.class,StringUtils.getLong(values,KEY_CYCLIST8));
            if (cyclist8 == null) {
                badRequest("Cyclist1 doesnt exist for id "+StringUtils.getLong(values,KEY_CYCLIST8));
            } else {
                team.setCyclist8(cyclist8);
            }
        }
        if (values.containsKey(KEY_CYCLIST9)) {
            CyclistRace cyclist9 = em.find(CyclistRace.class,StringUtils.getLong(values,KEY_CYCLIST9));
            if (cyclist9 == null) {
                badRequest("Cyclist1 doesnt exist for id "+StringUtils.getLong(values,KEY_CYCLIST9));
            } else {
                team.setCyclist9(cyclist9);
            }
        }
        em.merge(team);
        return ok(Json.toJson(team));
    }

    @Transactional
    public static Result delete(long id) {
        EntityManager em = JPA.em();
        PlayerTeam team = em.find(PlayerTeam.class, id);
        if (team != null) {
            em.remove(team);
            em.flush();
            return ok("Object deleted");
        } else {
            return notFound("Id not found");
        }
    }

    @Transactional
    public static Result getByPlayer(long id) {
        EntityManager em = JPA.em();
        Player player = em.find(Player.class,id);
        if (player == null) {
            return status(400,"player doesnt exist");
        }
        List<PlayerTeam> teams = em.createQuery("select t from playerteams t where t.player = :player", PlayerTeam.class)
                .setParameter("player",player).getResultList();
        return ok(Json.toJson(teams));
    }

    @Transactional
    public static Result getByRace(String raceName, int year) {
        EntityManager em = JPA.em();
        RaceName raceEnum = RaceName.valueOf(raceName.toUpperCase());
        //Get race by name and year
        Race race = null;
        List<Race> races = em.createQuery("select r from races r where r.name = :name and r.year = :year", Race.class)
                .setParameter("name", raceEnum).setParameter("year", year).getResultList();
        if (races != null && races.size()>0) {
            race = races.get(0);
        } else {
            return status(400,"Race doesn't exist");
        }
        //Get player teams by race
        List<PlayerTeam> teams = em.createQuery("select t from playerteams t where t.race = :race", PlayerTeam.class)
                .setParameter("race",race).getResultList();
        return ok(Json.toJson(teams));
    }

    @Transactional
    public static Result getByPlayerAndRace(long id, String raceName, int year) {
        EntityManager em = JPA.em();
        RaceName raceEnum = RaceName.valueOf(raceName.toUpperCase());
        //Get player
        Player player = em.find(Player.class,id);
        //Get race by name and year
        Race race = null;
        List<Race> races = em.createQuery("select r from races r where r.name = :name and r.year = :year", Race.class)
                .setParameter("name", raceEnum).setParameter("year", year).getResultList();
        if (races != null && races.size()>0) {
            race = races.get(0);
        }else {
            return status(400,"Race doesn't exist");
        }
        //Get player teams by race and player
        List<PlayerTeam> teams = em.createQuery("select t from playerteams t where t.race = :race and t.player = :player", PlayerTeam.class)
                .setParameter("race", race).setParameter("player", player).getResultList();
        return ok(Json.toJson(teams));
    }

}
