package controllers;

import model.cyclists.Cyclist;
import model.points.CyclistRace;
import model.races.Race;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import utils.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Jon on 31/01/2015.
 */
public class Cyclists extends Controller {

    public final static String KEY_FIRST_NAME = "firstName";
    public final static String KEY_LAST_NAME ="lastName";
    public final static String KEY_COUNTRY = "country";
    public final static String KEY_BIRTHDAY = "birthday";
    public final static String KEY_TEAM_ID = "teamId";

    public final static String KEY_PRICE = "price";
    public final static String KEY_POINTS = "points";
    public final static String KEY_CYCLIST_ID = "cyclistId";
    public final static String KEY_RACE_ID = "raceId";

    @Transactional
    public static Result list() {
        EntityManager em = JPA.em();
        List<Cyclist> cyclists = em.createQuery("select c from cyclists c", Cyclist.class).getResultList();
        return ok(Json.toJson(cyclists));
    }

    @Transactional
    public static Result get(long id) {
        Cyclist cyclist = JPA.em().find(Cyclist.class, id);
        if (cyclist != null) {
            return ok(Json.toJson(cyclist));
        } else {
            return ok(Json.newObject());
        }
    }

    @Transactional
    public static Result getByRace(long id) {
        EntityManager em = JPA.em();
        Race race = JPA.em().find(Race.class,id);
        List<CyclistRace> cyclists = em.createQuery("select c from cyclistraces c where c.race = :race", CyclistRace.class).setParameter("race", race).getResultList();
        return ok(Json.toJson(cyclists));
    }

    @Transactional
    public static Result update(long id) {
        Http.RequestBody body = request().body();
        final Map<String, String[]> values = body.asFormUrlEncoded();

        EntityManager em = JPA.em();
        Cyclist s = em.find(Cyclist.class, id);
        if (s == null) {
            return notFound("Id not found");
        }
        if (values.containsKey(KEY_FIRST_NAME)) {
            s.setFirstName(values.get(KEY_FIRST_NAME)[0]);
        }
        if (values.containsKey(KEY_LAST_NAME)) {
            s.setLastName(values.get(KEY_LAST_NAME)[0]);
        }
        if (values.containsKey(KEY_COUNTRY)) {
            s.setCountry(values.get(KEY_COUNTRY)[0]);
        }
        if (values.containsKey(KEY_BIRTHDAY)) {
            s.setBirthday(new Date(Long.parseLong(values.get(KEY_BIRTHDAY)[0])));
        }
        if (values.containsKey(KEY_TEAM_ID)) {
            s.setTeamId(Long.parseLong(values.get(KEY_TEAM_ID)[0]));
        }
        em.merge(s);
        return ok(Json.toJson(s));
    }

    @Transactional
    public static Result delete(long id) {
        EntityManager em = JPA.em();
        Cyclist s = em.find(Cyclist.class, id);
        if (s != null) {
            em.remove(s);
            em.flush();
            return ok("Object deleted");
        } else {
            return notFound("Id not found");
        }
    }

    @Transactional
    public static Result create() {
        Http.RequestBody body = request().body();
        final Map<String, String[]> values = body.asFormUrlEncoded();
        if (values == null) {
            return status(422,"missing data teamId, birthday, country, firstName and lastName");
        }
        if (values.containsKey(KEY_TEAM_ID) && values.containsKey(KEY_BIRTHDAY) && values.containsKey(KEY_COUNTRY) && values.containsKey(KEY_FIRST_NAME) && values.containsKey(KEY_LAST_NAME)) {
            EntityManager em = JPA.em();

            Cyclist cyclist = new Cyclist();
            cyclist.setBirthday(new Date(Long.parseLong(values.get(KEY_BIRTHDAY)[0])));
            cyclist.setFirstName(values.get(KEY_FIRST_NAME)[0]);
            cyclist.setLastName(values.get(KEY_LAST_NAME)[0]);
            cyclist.setCountry(values.get(KEY_COUNTRY)[0]);
            cyclist.setTeamId(Long.parseLong(values.get(KEY_TEAM_ID)[0]));
            try {
                em.persist(cyclist);
            } catch (PersistenceException e) {
                return status(400,"Cyclist with that name and birthday exists");
            }
            em.flush();
            return ok(Json.toJson(cyclist));
        } else {
            return status(422,"Parametros incorrectos: Son necesarios firstName, lastName, birthday, country y teamId");
        }
    }

    @Transactional
    @Security.Authenticated(AdminSecured.class)
    public static Result createCyclistRace(long raceId, long cyclistId) {
        EntityManager em = JPA.em();
        Cyclist cyclist = em.find(Cyclist.class, cyclistId);
        Race race = em.find(Race.class,raceId);

        Http.RequestBody body = request().body();
        final Map<String, String[]> values = body.asFormUrlEncoded();

        if (values == null || !values.containsKey(KEY_PRICE)) {
            return status(400,"Missing price parameter");
        }
        if (cyclist != null && race != null) {
            CyclistRace cr = new CyclistRace();
            cr.setCyclist(cyclist);
            cr.setPoints(0);
            cr.setRace(race);
            cr.setPrice(Integer.parseInt(values.get(KEY_PRICE)[0]));
            try {
                em.persist(cr);
            } catch (PersistenceException e) {
                return status(400,"cyclist exists for this race");
            }
            em.flush();
            return ok(Json.toJson(cr));
        } else {
            return status(400,"cyclist or race or both doesnt exist");
        }
    }

    @Transactional
    @Security.Authenticated(AdminSecured.class)
    public static Result updateCyclistRace(long id) {
        EntityManager em = JPA.em();
        CyclistRace cr = em.find(CyclistRace.class, id);

        Http.RequestBody body = request().body();
        final Map<String, String[]> values = body.asFormUrlEncoded();

        if (values.containsKey(KEY_PRICE)) {
            cr.setPrice(StringUtils.getInt(values,KEY_PRICE));
        }
        if (values.containsKey(KEY_POINTS)) {
            cr.setPoints(StringUtils.getInt(values,KEY_POINTS));
        }
        if (values.containsKey(KEY_CYCLIST_ID)) {
            Cyclist cyclist = em.find(Cyclist.class,StringUtils.getLong(values,KEY_CYCLIST_ID));
            if (cyclist != null) {
                cr.setCyclist(cyclist);
            }
        }
        if (values.containsKey(KEY_RACE_ID)) {
            Race race = em.find(Race.class,StringUtils.getLong(values,KEY_RACE_ID));
            if (race != null) {
                cr.setRace(race);
            }
        }
        em.merge(cr);
        return ok(Json.toJson(cr));
    }

    @Transactional
    public static Result getNonAdded(long id) {
        EntityManager em = JPA.em();
        Race race = JPA.em().find(Race.class,id);
        List<Cyclist> racingCyclists = em.createQuery("select c.cyclist from cyclistraces c where c.race = :race", Cyclist.class).setParameter("race", race).getResultList();
        List<Cyclist> cyclists = em.createQuery("select c from cyclists c where c not in (:selected)", Cyclist.class).setParameter("selected",racingCyclists ).getResultList();
        return ok(Json.toJson(cyclists));
    }

    @Transactional
    @Security.Authenticated(AdminSecured.class)
    public static Result deleteCyclistRace(long id) {
        EntityManager em = JPA.em();
        CyclistRace s = em.find(CyclistRace.class, id);
        if (s != null) {
            em.remove(s);
            em.flush();
            return ok("Object deleted");
        } else {
            return notFound("Id not found");
        }
    }
}
