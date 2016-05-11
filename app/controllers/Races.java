package controllers;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import model.races.Race;
import model.races.RaceName;
import play.Logger;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 03/02/2015.
 */
public class Races extends Controller {

    public final static String KEY_NAME = "name";
    public static final String KEY_YEAR = "year";

    @Transactional
    public static Result list() {
        EntityManager em = JPA.em();
        List<Race> races = em.createQuery("select r from races r", Race.class).getResultList();
        return ok(Json.toJson(races));
    }

    @Transactional
    public static Result get(long id) {
        Race race = JPA.em().find(Race.class, id);
        if (race != null) {
            return ok(Json.toJson(race));
        } else {
            return ok(Json.newObject());
        }
    }

    @Transactional
    public static Result update(long id) {
        Http.RequestBody body = request().body();
        final Map<String, String[]> values = body.asFormUrlEncoded();

        EntityManager em = JPA.em();
        Race r = em.find(Race.class, id);
        if (r == null) {
            return notFound("Id not found");
        }
        if (values.containsKey(KEY_NAME)) {
            r.setName(RaceName.valueOf(values.get(KEY_NAME)[0]));
        }
        if (values.containsKey(KEY_YEAR)) {
            r.setYear(Integer.parseInt(values.get(KEY_YEAR)[0]));
        }
        em.merge(r);
        return ok(Json.toJson(r));
    }

    @Transactional
    public static Result delete(long id) {
        EntityManager em = JPA.em();
        Race r = em.find(Race.class, id);
        if (r != null) {
            em.remove(r);
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
        if (values.containsKey(KEY_NAME) && values.containsKey(KEY_YEAR)) {
            Race race = new Race();
            race.setName(RaceName.valueOf(values.get(KEY_NAME)[0]));
            race.setYear(Integer.parseInt(values.get(KEY_YEAR)[0]));
            EntityManager em = JPA.em();
            try {
                em.persist(race);
            } catch (PersistenceException e) {
                return status(400,"race exists");
            }
            em.flush();
            return ok(Json.toJson(race));
        } else {
            return status(422,"Parametros incorrectos: Son necesarios name, year");
        }
    }

    @Transactional
    public static Result availables() {
        Config conf = ConfigFactory.load();
        List<String> availables = conf.getStringList("races.availables");
        List<Race> races = new ArrayList<Race>();
        EntityManager em = JPA.em();
        for (String string : availables) {
            RaceName name = RaceName.valueOf(string.split("-")[0]);
            int year = Integer.parseInt(string.split("-")[1]);
            Race race;
            try {
                race = em.createQuery("select r from races r where r.name = :name and r.year = :year", Race.class)
                        .setParameter("name",name).setParameter("year",year).getSingleResult();
            } catch (Exception e) {
                Logger.warn(e.getMessage());
                continue;
            }
            races.add(race);
        }
        return ok(Json.toJson(races));
    }
}
