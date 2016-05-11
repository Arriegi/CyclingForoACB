package controllers;

import model.cyclists.Team;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 02/02/2015.
 */
public class Teams extends Controller{

    public final static String KEY_NAME = "name";
    public static final String KEY_UCI_CODE = "uciCode";

    @Transactional
    public static Result list() {
        EntityManager em = JPA.em();
        List<Team> teams = em.createQuery("select t from teams t", Team.class).getResultList();
        return ok(Json.toJson(teams));
    }

    @Transactional
    public static Result get(long id) {
        Team team = JPA.em().find(Team.class, id);
        if (team != null) {
            return ok(Json.toJson(team));
        } else {
            return ok(Json.newObject());
        }
    }

    @Transactional
    public static Result update(long id) {
        Http.RequestBody body = request().body();
        final Map<String, String[]> values = body.asFormUrlEncoded();

        EntityManager em = JPA.em();
        Team s = em.find(Team.class, id);
        if (s == null) {
            return notFound("Id not found");
        }
        if (values.containsKey(KEY_NAME)) {
            s.setName(values.get(KEY_NAME)[0]);
        }
        if (values.containsKey(KEY_UCI_CODE)) {
            if (values.get(KEY_UCI_CODE)[0].length() == 3) {
                s.setUciCode(values.get(KEY_UCI_CODE)[0]);
            } else {
                return status(422, "UCI Code is 3 character length");
            }
        }
        em.merge(s);
        return ok(Json.toJson(s));
    }

    @Transactional
    public static Result delete(long id) {
        EntityManager em = JPA.em();
        Team s = em.find(Team.class, id);
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
        if (values.containsKey(KEY_UCI_CODE) && values.containsKey(KEY_NAME)) {
            if (values.get(KEY_UCI_CODE)[0].length() != 3) {
                return status(422,"UCI Code is 3 characters length");
            }
            Team team = new Team();
            team.setName(values.get(KEY_NAME)[0]);
            team.setUciCode(values.get(KEY_UCI_CODE)[0]);
            EntityManager em = JPA.em();
            try {
                em.persist(team);
            } catch(PersistenceException e) {
                return status(400,"Team exists");
            }
            em.flush();
            return ok(Json.toJson(team));
        } else {
            return status(422,"Parametros incorrectos: Son necesarios firstName, lastName, birthday, country y teamId");
        }
    }

}
