package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import model.races.Stage;
import model.races.classification.TeamTimeTrialClassification;
import model.races.classification.general.General;
import model.races.classification.general.Mountain;
import model.races.classification.general.Points;
import model.races.classification.mountain.*;
import model.races.classification.sprint.IntermediateSprint;
import model.races.classification.sprint.StageSprint;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.JsonUtils;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by lenovo on 02/02/2015.
 */


public class Classifications extends Controller {

    @Transactional
    public static Result list() {
        EntityManager em = JPA.em();
        JsonNode json;
        List<TeamTimeTrialClassification> tttClass = em.createQuery("select s from stagettt s", TeamTimeTrialClassification.class).getResultList();
        json = Json.toJson(tttClass);
        List<StageSprint> stageClass = em.createQuery("select s from stagesprint s", StageSprint.class).getResultList();
        json = JsonUtils.merge(json, Json.toJson(stageClass));
        List<MountainHC> mhc = em.createQuery("select m from mhc m", MountainHC.class).getResultList();
        json = JsonUtils.merge(json, Json.toJson(mhc));
        List<MountainFirst> m1 = em.createQuery("select m from m1 m", MountainFirst.class).getResultList();
        json = JsonUtils.merge(json, Json.toJson(m1));
        List<MountainSecond> m2 = em.createQuery("select m from m2 m", MountainSecond.class).getResultList();
        json = JsonUtils.merge(json, Json.toJson(m2));
        List<MountainThird> m3 = em.createQuery("select m from m3 m", MountainThird.class).getResultList();
        json = JsonUtils.merge(json, Json.toJson(m3));
        List<MountainFourth> m4 = em.createQuery("select m from m4 m", MountainFourth.class).getResultList();
        json = JsonUtils.merge(json, Json.toJson(m4));
        List<IntermediateSprint> intSprint = em.createQuery("select s from intermediatesprint s", IntermediateSprint.class).getResultList();
        json = JsonUtils.merge(json, Json.toJson(intSprint));
        List<General> general = em.createQuery("select s from generals s", General.class).getResultList();
        json = JsonUtils.merge(json, Json.toJson(general));
        List<Points> points = em.createQuery("select s from points s", Points.class).getResultList();
        json = JsonUtils.merge(json, Json.toJson(points));
        List<Mountain> mountain = em.createQuery("select s from mountain s", Mountain.class).getResultList();
        json = JsonUtils.merge(json, Json.toJson(mountain));
        return ok(json);
    }

    @Transactional
    public static Result getByStage(long id) {
        EntityManager em = JPA.em();
        Stage stage = JPA.em().find(Stage.class, id);
        if (stage == null) {
            return status(422,"stage with id:" + id + " not found");
        }
        JsonNode json;
        List<TeamTimeTrialClassification> tttClass = em.createQuery("select s from stagettt s WHERE s.stage = :stage", TeamTimeTrialClassification.class).setParameter("stage", stage).getResultList();
        json = Json.toJson(tttClass);
        List<StageSprint> stageClass = em.createQuery("select s from stagesprint s WHERE s.stage = :stage", StageSprint.class).setParameter("stage", stage).getResultList();
        json = JsonUtils.merge(json, Json.toJson(stageClass));
        List<MountainHC> mhc = em.createQuery("select m from mhc m WHERE m.stage = :stage", MountainHC.class).setParameter("stage", stage).getResultList();
        json = JsonUtils.merge(json, Json.toJson(mhc));
        List<MountainFirst> m1 = em.createQuery("select m from m1 m WHERE m.stage = :stage", MountainFirst.class).setParameter("stage", stage).getResultList();
        json = JsonUtils.merge(json, Json.toJson(m1));
        List<MountainSecond> m2 = em.createQuery("select m from m2 m WHERE m.stage = :stage", MountainSecond.class).setParameter("stage", stage).getResultList();
        json = JsonUtils.merge(json, Json.toJson(m2));
        List<MountainThird> m3 = em.createQuery("select m from m3 m WHERE m.stage = :stage", MountainThird.class).setParameter("stage",stage).getResultList();
        json = JsonUtils.merge(json, Json.toJson(m3));
        List<MountainFourth> m4 = em.createQuery("select m from m4 m WHERE m.stage = :stage", MountainFourth.class).setParameter("stage", stage).getResultList();
        json = JsonUtils.merge(json, Json.toJson(m4));
        List<IntermediateSprint> intSprint = em.createQuery("select s from intermediatesprint s WHERE s.stage = :stage", IntermediateSprint.class).setParameter("stage",stage).getResultList();
        json = JsonUtils.merge(json, Json.toJson(intSprint));
        List<General> general = em.createQuery("select s from generals s WHERE s.stage = :stage", General.class).setParameter("stage",stage).getResultList();
        json = JsonUtils.merge(json, Json.toJson(general));
        List<Points> points = em.createQuery("select s from points s WHERE s.stage = :stage", Points.class).setParameter("stage",stage).getResultList();
        json = JsonUtils.merge(json, Json.toJson(points));
        List<Mountain> mountain = em.createQuery("select s from mountain s WHERE s.stage = :stage", Mountain.class).setParameter("stage",stage).getResultList();
        json = JsonUtils.merge(json, Json.toJson(mountain));
        return ok(json);
    }

}
