package controllers;

import model.races.Race;
import model.races.Stage;
import org.hibernate.exception.ConstraintViolationException;
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
 * Created by lenovo on 03/02/2015.
 */
public class Stages extends Controller {

    public final static String KEY_RACE_ID = "raceId";
    public final static String KEY_DESCRIPTION = "description";
    public final static String KEY_MHC = "mHC";
    public final static String KEY_M1 = "m1";
    public final static String KEY_M2 = "m2";
    public final static String KEY_M3 = "m3";
    public final static String KEY_M4 = "m4";
    public final static String KEY_S_INT = "sInt";
    public final static String KEY_STAGE_NUMBER = "stageNumber";
    public final static String KEY_IS_TTT = "isTTT";
    public final static String KEY_HAS_MC = "hasMC";
    public final static String KEY_HAS_RC = "hasRC";


    @Transactional
    public static Result list() {
        EntityManager em = JPA.em();
        List<Stage> races = em.createQuery("select s from stages s", Stage.class).getResultList();
        return ok(Json.toJson(races));
    }

    @Transactional
    public static Result get(long id) {
        Stage stage = JPA.em().find(Stage.class, id);
        if (stage != null) {
            return ok(Json.toJson(stage));
        } else {
            return ok(Json.newObject());
        }
    }

    @Transactional
    public static Result update(long id) {
        Http.RequestBody body = request().body();
        final Map<String, String[]> values = body.asFormUrlEncoded();

        EntityManager em = JPA.em();
        Stage stage = em.find(Stage.class, id);
        if (values.containsKey(KEY_IS_TTT)) {
            int isTTT = Integer.parseInt(values.get(KEY_IS_TTT)[0]);
            if (isTTT == 0) {
                stage.setTTT(false);
            } else {
                stage.setTTT(true);
            }
        }
        if (values.containsKey(KEY_HAS_MC)) {
            int hasMC = Integer.parseInt(values.get(KEY_HAS_MC)[0]);
            if (hasMC == 0) {
                stage.setMC(false);
            } else {
                stage.setMC(true);
            }
        }
        if (values.containsKey(KEY_HAS_RC)) {
            int hasRC = Integer.parseInt(values.get(KEY_HAS_RC)[0]);
            if (hasRC == 0) {
                stage.setRC(false);
            } else {
                stage.setRC(true);
            }
        }
        if (values.containsKey(KEY_MHC)) {
            stage.setmHC(Integer.valueOf(values.get(KEY_MHC)[0]));
        }
        if (values.containsKey(KEY_M1)) {
            stage.setmHC(Integer.valueOf(values.get(KEY_M1)[0]));
        }
        if (values.containsKey(KEY_M2)) {
            stage.setmHC(Integer.valueOf(values.get(KEY_M2)[0]));
        }
        if (values.containsKey(KEY_M3)) {
            stage.setmHC(Integer.valueOf(values.get(KEY_M3)[0]));
        }
        if (values.containsKey(KEY_M4)) {
            stage.setmHC(Integer.valueOf(values.get(KEY_M4)[0]));
        }
        if (values.containsKey(KEY_S_INT)) {
            stage.setmHC(Integer.valueOf(values.get(KEY_S_INT)[0]));
        }
        if (values.containsKey(KEY_DESCRIPTION)) {
            stage.setDescription(values.get(KEY_DESCRIPTION)[0]);
        }
        if (values.containsKey(KEY_STAGE_NUMBER)) {
            stage.setStageNumber(Integer.valueOf(values.get(KEY_STAGE_NUMBER)[0]));
        }
        em.merge(stage);
        return ok(Json.toJson(stage));
    }

    @Transactional
    public static Result delete(long id) {
        EntityManager em = JPA.em();
        Stage stage = em.find(Stage.class, id);
        if (stage != null) {
            em.remove(stage);
            em.flush();
            return ok("Object deleted");
        } else {
            return notFound("Id not found");
        }
    }

    @Transactional
    public static Result getByRace(long raceId) {
        Race race;
        try {
            race = getRace(raceId);
            return ok(Json.toJson(race.getStages()));
        } catch (Exception e) {
            return status(422,e.getMessage());
        }
    }

    @Transactional
    public static Result create() {
        Http.RequestBody body = request().body();
        final Map<String, String[]> values = body.asFormUrlEncoded();
        if (values.containsKey(KEY_DESCRIPTION) && values.containsKey(KEY_RACE_ID) && values.containsKey(KEY_STAGE_NUMBER)
                && values.containsKey(KEY_HAS_MC) && values.containsKey(KEY_HAS_RC)) {
            Race race = null;
            try {
                race = getRace(Long.parseLong(values.get(KEY_RACE_ID)[0]));
            } catch (Exception e) {
                return status(500,e.getMessage());
            }

            Stage stage = new Stage();
            stage.setDescription(values.get(KEY_DESCRIPTION)[0]);
            stage.setRace(race);
            stage.setStageNumber(Integer.parseInt(values.get(KEY_STAGE_NUMBER)[0]));
            if (values.containsKey(KEY_IS_TTT)) {
                stage.setTTT(Boolean.parseBoolean(values.get(KEY_IS_TTT)[0]));
            }
            if (values.containsKey(KEY_HAS_MC)) {
                stage.setMC(Boolean.parseBoolean(values.get(KEY_HAS_MC)[0]));
            }
            if (values.containsKey(KEY_HAS_RC)) {
                stage.setRC(Boolean.parseBoolean(values.get(KEY_HAS_RC)[0]));
            }
            if (values.containsKey(KEY_MHC)) {
                stage.setmHC(Integer.valueOf(values.get(KEY_MHC)[0]));
            }
            if (values.containsKey(KEY_M1)) {
                stage.setM1(Integer.valueOf(values.get(KEY_M1)[0]));
            }
            if (values.containsKey(KEY_M2)) {
                stage.setM2(Integer.valueOf(values.get(KEY_M2)[0]));
            }
            if (values.containsKey(KEY_M3)) {
                stage.setM3(Integer.valueOf(values.get(KEY_M3)[0]));
            }
            if (values.containsKey(KEY_M4)) {
                stage.setM4(Integer.valueOf(values.get(KEY_M4)[0]));
            }
            if (values.containsKey(KEY_S_INT)) {
                stage.setsInt(Integer.valueOf(values.get(KEY_S_INT)[0]));
            }
            EntityManager em = JPA.em();
            try {
                em.persist(stage);
            } catch (PersistenceException e) {
                return status(400,"stage already exists with that race and stage number");
            }
            em.flush();
            return ok(Json.toJson(stage));
        } else {
            return status(422,"Parametros incorrectos: Son necesarios " + KEY_DESCRIPTION + "," + KEY_RACE_ID + ","
                    + KEY_STAGE_NUMBER + "," + KEY_HAS_MC + "," + KEY_HAS_RC);
        }
    }

    private static Race getRace(long id) throws Exception{
        Race race = JPA.em().find(Race.class, id);
        if (race != null) {
            return race;
        } else {
            throw new Exception("race not found for id:" + id);
        }
    }
}
