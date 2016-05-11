package controllers;

import model.users.Player;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 03/02/2015.
 */
public class Players extends Controller {

    public final static String KEY_EMAIL = "email";
    public final static String KEY_PASSWORD = "password";
    public final static String KEY_SIGN_IN_DATE = "signInDate";

    @Transactional
    public static Result list() {
        EntityManager em = JPA.em();
        List<Player> players = em.createQuery("select p from players p", Player.class).getResultList();
        return ok(Json.toJson(players));
    }

    @Transactional
    public static Result get(long id) {
        Player player = JPA.em().find(Player.class, id);
        if (player != null) {
            return ok(Json.toJson(player));
        } else {
            return ok(Json.newObject());
        }
    }

    @Transactional
    public static Result update(long id) {
        Http.RequestBody body = request().body();
        final Map<String, String[]> values = body.asFormUrlEncoded();

        EntityManager em = JPA.em();
        Player p = em.find(Player.class, id);
        if (p == null) {
            return notFound("Id not found");
        }
        if (values.containsKey(KEY_EMAIL)) {
            p.setEmail(values.get(KEY_EMAIL)[0]);
        }
        if (values.containsKey(KEY_PASSWORD)) {
            p.setPassword(values.get(KEY_PASSWORD)[0]);
        }
        if (values.containsKey(KEY_SIGN_IN_DATE)) {
            p.setSignInDate(new Date(Long.parseLong(values.get(KEY_SIGN_IN_DATE)[0])));
        }
        em.merge(p);
        return ok(Json.toJson(p));
    }

    @Transactional
    public static Result delete(long id) {
        EntityManager em = JPA.em();
        Player p = em.find(Player.class, id);
        if (p != null) {
            em.remove(p);
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
        if (values.containsKey(KEY_EMAIL) && values.containsKey(KEY_PASSWORD) && values.containsKey(KEY_SIGN_IN_DATE)) {
            Player player = new Player();
            player.setSignInDate(new Date(Long.parseLong(values.get(KEY_SIGN_IN_DATE)[0])));
            player.setPassword(values.get(KEY_PASSWORD)[0]);
            player.setEmail(values.get(KEY_EMAIL)[0]);
            EntityManager em = JPA.em();
            try {
                em.persist(player);
            } catch (PersistenceException e) {
                return status(400,"player with that email already exists");
            }
            em.flush();
            return ok(Json.toJson(player));
        } else {
            return status(422,"Parametros incorrectos: Son necesarios firstName, lastName, birthday, country y teamId");
        }
    }
}
