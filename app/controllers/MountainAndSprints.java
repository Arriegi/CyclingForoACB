package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import model.cyclists.Cyclist;
import model.cyclists.Team;
import model.points.CyclistRace;
import model.races.Stage;
import model.races.classification.TeamTimeTrialClassification;
import model.races.classification.general.General;
import model.races.classification.general.Mountain;
import model.races.classification.general.Points;
import model.races.classification.mountain.*;
import model.races.classification.sprint.IntermediateSprint;
import model.races.classification.sprint.StageSprint;
import play.Logger;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.Crud;
import utils.DBUtils;
import utils.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.*;

/**
 * Created by lenovo on 09/02/2015.
 */
public class MountainAndSprints extends Controller{

    public final static String CYCLIST = "cyclist";
    public final static String GCYCLIST = "gcyclist";
    public final static String RCYCLIST = "rcyclist";
    public final static String MCYCLIST = "mcyclist";
    public final static String STAGE = "stage";
    public final static String NAME = "name";
    public final static String TEAM = "team";


    //=========================  GET BY ID =====================================//

    @Transactional
    public static Result getM1(long id) {
        Crud<MountainFirst> crud = new Crud<MountainFirst>("m1",MountainFirst.class);
        return crud.get(id);
    }

    @Transactional
    public static Result getM2(long id) {
        Crud<MountainSecond> crud = new Crud<MountainSecond>("m2",MountainSecond.class);
        return crud.get(id);
    }

    @Transactional
    public static Result getM3(long id) {
        Crud<MountainThird> crud = new Crud<MountainThird>("m3",MountainThird.class);
        return crud.get(id);
    }

    @Transactional
    public static Result getM4(long id) {
        Crud<MountainFourth> crud = new Crud<MountainFourth>("m4",MountainFourth.class);
        return crud.get(id);
    }

    @Transactional
    public static Result getMHC(long id) {
        Crud<MountainHC> crud = new Crud<MountainHC>("mhc",MountainHC.class);
        return crud.get(id);
    }

    @Transactional
    public static Result getSInt(long id) {
        Crud<IntermediateSprint> crud = new Crud<IntermediateSprint>("intermediatesprint",IntermediateSprint.class);
        return crud.get(id);
    }

    @Transactional
    public static Result getTTT(long id) {
        Crud<TeamTimeTrialClassification> crud = new Crud<TeamTimeTrialClassification>("stagettt",TeamTimeTrialClassification.class);
        return crud.get(id);
    }

    @Transactional
    public static Result getStageClassification(long id) {
        Crud<StageSprint> crud = new Crud<StageSprint>("stagesprint",StageSprint.class);
        return crud.get(id);
    }

    //=========================  DELETE =====================================//

    @Transactional
    public static Result deleteM1(long id) {
        Crud<MountainFirst> crud = new Crud<MountainFirst>("m1",MountainFirst.class);
        return crud.delete(id);
    }

    @Transactional
    public static Result deleteM2(long id) {
        Crud<MountainSecond> crud = new Crud<MountainSecond>("m2",MountainSecond.class);
        return crud.delete(id);
    }

    @Transactional
    public static Result deleteM3(long id) {
        Crud<MountainThird> crud = new Crud<MountainThird>("m3",MountainThird.class);
        return crud.delete(id);
    }

    @Transactional
    public static Result deleteM4(long id) {
        Crud<MountainFourth> crud = new Crud<MountainFourth>("m4",MountainFourth.class);
        return crud.delete(id);
    }

    @Transactional
    public static Result deleteMHC(long id) {
        Crud<MountainHC> crud = new Crud<MountainHC>("mhc",MountainHC.class);
        return crud.delete(id);
    }

    @Transactional
    public static Result deleteSInt(long id) {
        Crud<IntermediateSprint> crud = new Crud<IntermediateSprint>("intermediatesprint",IntermediateSprint.class);
        return crud.delete(id);
    }

    @Transactional
    public static Result deleteTTT(long id) {
        Crud<TeamTimeTrialClassification> crud = new Crud<TeamTimeTrialClassification>("stagettt",TeamTimeTrialClassification.class);
        return crud.delete(id);
    }

    @Transactional
    public static Result deleteStageClassification(long id) {
        Crud<StageSprint> crud = new Crud<StageSprint>("stagesprint",StageSprint.class);
        return crud.delete(id);
    }

    //=========================  CREATE =====================================//

    @Transactional
    public static Result createM1() {
        MountainFirst m1 = new MountainFirst();
        Http.RequestBody body = request().body();
        Map<String,String[]> values =  body.asFormUrlEncoded();
        //Check all keys exists
        if (!StringUtils.allCyclistsExists(values,5) || !values.containsKey(STAGE)) {
            return badRequest("Missing parameters cyclist1..cyclist5 or stage");
        }
        Crud<CyclistRace> crudCyclist = new Crud<CyclistRace>("cyclists",CyclistRace.class);
        Crud<Stage> crudStage = new Crud<Stage>("stages",Stage.class);
        CyclistRace c1,c2,c3,c4,c5;
        Stage stage= crudStage.getModel(StringUtils.getLong(values, STAGE));
        c1 = crudCyclist.getModel(StringUtils.getLong(values, CYCLIST + 1));
        c2 = crudCyclist.getModel(StringUtils.getLong(values,CYCLIST+2));
        c3 = crudCyclist.getModel(StringUtils.getLong(values,CYCLIST+3));
        c4 = crudCyclist.getModel(StringUtils.getLong(values,CYCLIST+4));
        c5 = crudCyclist.getModel(StringUtils.getLong(values,CYCLIST+5));
        //Check ids exists
        if (c1==null || c2 == null || c3 == null || c4 == null || c5 == null || stage == null) {
            return badRequest("At least one cyclist or stage not existing in DB");
        }
        //Check ids exist and are not repated
        if (!uniqueElements(c1.getId(),c2.getId(),c3.getId(),c4.getId(),c5.getId())) {
            return badRequest("All cyclists must be different");
        }
        //Check that stage hasn't reached number of m1s specified
        int n = DBUtils.getNumberOfSprints("m1",stage);
        if (n >= stage.getM1()) {
            return badRequest("This stage has already stored " + n + " m1s");
        }
        m1.setCyclist1(c1);
        m1.setCyclist2(c2);
        m1.setCyclist3(c3);
        m1.setCyclist4(c4);
        m1.setCyclist5(c5);
        m1.setStage(stage);

        EntityManager em = JPA.em();
        if (values.containsKey(NAME)) {
            m1.setName(values.get(NAME)[0]);
        }
        try {
            em.persist(m1);
        } catch (PersistenceException e) {
            return status(400,"Persisting error");
        }
        em.flush();
        return ok(Json.toJson(m1));
    }

    @Transactional
    public static Result createM2() {
        MountainSecond m2 = new MountainSecond();
        Http.RequestBody body = request().body();
        Map<String,String[]> values =  body.asFormUrlEncoded();
        //Check all keys exists
        if (!StringUtils.allCyclistsExists(values,3) || !values.containsKey(STAGE)) {
            return badRequest("Missing parameters cyclist1..cyclist3 or stage");
        }
        Crud<CyclistRace> crudCyclist = new Crud<CyclistRace>("cyclists",CyclistRace.class);
        Crud<Stage> crudStage = new Crud<Stage>("stages",Stage.class);
        CyclistRace c1,c2,c3;
        Stage stage= crudStage.getModel(StringUtils.getLong(values, STAGE));
        c1 = crudCyclist.getModel(StringUtils.getLong(values, CYCLIST + 1));
        c2 = crudCyclist.getModel(StringUtils.getLong(values,CYCLIST+2));
        c3 = crudCyclist.getModel(StringUtils.getLong(values,CYCLIST+3));
        //Check ids exists
        if (c1==null || c2 == null || c3 == null || stage == null) {
            return badRequest("At least one cyclist or stage not existing in DB");
        }
        //Check ids exist and are not repated
        if (!uniqueElements(c1.getId(),c2.getId(),c3.getId())) {
            return badRequest("All cyclists must be different");
        }
        //Check that stage hasn't reached number of m1s specified
        int n = DBUtils.getNumberOfSprints("m2",stage);
        if (n >= stage.getM1()) {
            return badRequest("This stage has already stored " + n + " m2s");
        }
        m2.setCyclist1(c1);
        m2.setCyclist2(c2);
        m2.setCyclist3(c3);
        m2.setStage(stage);

        EntityManager em = JPA.em();
        if (values.containsKey(NAME)) {
            m2.setName(values.get(NAME)[0]);
        }
        try {
            em.persist(m2);
        } catch (PersistenceException e) {
            return status(400,"Persisting error");
        }
        em.flush();
        return ok(Json.toJson(m2));
    }

    @Transactional
    public static Result createM3() {
        MountainThird m3 = new MountainThird();
        Http.RequestBody body = request().body();
        Map<String,String[]> values =  body.asFormUrlEncoded();
        //Check all keys exists
        if (!StringUtils.allCyclistsExists(values,2) || !values.containsKey(STAGE)) {
            return badRequest("Missing parameters cyclist1..cyclist2 or stage");
        }
        Crud<CyclistRace> crudCyclist = new Crud<CyclistRace>("cyclists",CyclistRace.class);
        Crud<Stage> crudStage = new Crud<Stage>("stages",Stage.class);
        CyclistRace c1,c2;
        Stage stage= crudStage.getModel(StringUtils.getLong(values, STAGE));
        c1 = crudCyclist.getModel(StringUtils.getLong(values, CYCLIST + 1));
        c2 = crudCyclist.getModel(StringUtils.getLong(values,CYCLIST+2));
        //Check ids exists
        if (c1==null || c2 == null || stage == null) {
            return badRequest("At least one cyclist or stage not existing in DB");
        }
        //Check ids exist and are not repated
        if (c1.getId() == c2.getId()) {
            return badRequest("All cyclists must be different");
        }
        //Check that stage hasn't reached number of m1s specified
        int n = DBUtils.getNumberOfSprints("m3",stage);
        if (n >= stage.getM1()) {
            return badRequest("This stage has already stored " + n + " m3s");
        }
        m3.setCyclist1(c1);
        m3.setCyclist2(c2);
        m3.setStage(stage);

        EntityManager em = JPA.em();
        if (values.containsKey(NAME)) {
            m3.setName(values.get(NAME)[0]);
        }
        try {
            em.persist(m3);
        } catch (PersistenceException e) {
            return status(400,"Persisting error");
        }
        em.flush();
        return ok(Json.toJson(m3));
    }

    @Transactional
    public static Result createM4() {
        MountainFourth m3 = new MountainFourth();
        Http.RequestBody body = request().body();
        Map<String,String[]> values =  body.asFormUrlEncoded();
        //Check all keys exists
        if (!StringUtils.allCyclistsExists(values,1) || !values.containsKey(STAGE)) {
            return badRequest("Missing parameters cyclist1 or stage");
        }
        Crud<CyclistRace> crudCyclist = new Crud<CyclistRace>("cyclists",CyclistRace.class);
        Crud<Stage> crudStage = new Crud<Stage>("stages",Stage.class);
        CyclistRace c1;
        Stage stage= crudStage.getModel(StringUtils.getLong(values, STAGE));
        c1 = crudCyclist.getModel(StringUtils.getLong(values, CYCLIST + 1));
        //Check ids exists
        if (c1==null || stage == null) {
            return badRequest("At least one cyclist or stage not existing in DB");
        }
        //Check that stage hasn't reached number of m1s specified
        int n = DBUtils.getNumberOfSprints("m4",stage);
        if (n >= stage.getM1()) {
            return badRequest("This stage has already stored " + n + " m4s");
        }
        m3.setCyclist1(c1);
        m3.setStage(stage);

        EntityManager em = JPA.em();
        if (values.containsKey(NAME)) {
            m3.setName(values.get(NAME)[0]);
        }
        try {
            em.persist(m3);
        } catch (PersistenceException e) {
            return status(400,"Persisting error");
        }
        em.flush();
        return ok(Json.toJson(m3));
    }

    @Transactional
    public static Result createMHC() {
        MountainHC mhc = new MountainHC();
        Http.RequestBody body = request().body();
        Map<String,String[]> values =  body.asFormUrlEncoded();
        //Check all keys exists
        if (!StringUtils.allCyclistsExists(values,8)|| !values.containsKey(STAGE)) {
            return badRequest("Missing parameters cyclist1..cyclist8 or stage");
        }
        Crud<CyclistRace> crudCyclist = new Crud<CyclistRace>("cyclists",CyclistRace.class);
        Crud<Stage> crudStage = new Crud<Stage>("stages",Stage.class);
        CyclistRace c1,c2,c3,c4,c5,c6,c7,c8;
        Stage stage= crudStage.getModel(StringUtils.getLong(values, STAGE));
        c1 = crudCyclist.getModel(StringUtils.getLong(values, CYCLIST + 1));
        c2 = crudCyclist.getModel(StringUtils.getLong(values,CYCLIST+2));
        c3 = crudCyclist.getModel(StringUtils.getLong(values,CYCLIST+3));
        c4 = crudCyclist.getModel(StringUtils.getLong(values,CYCLIST+4));
        c5 = crudCyclist.getModel(StringUtils.getLong(values,CYCLIST+5));
        c6 = crudCyclist.getModel(StringUtils.getLong(values,CYCLIST+6));
        c7 = crudCyclist.getModel(StringUtils.getLong(values,CYCLIST+7));
        c8 = crudCyclist.getModel(StringUtils.getLong(values,CYCLIST+8));
        //Check ids exists
        if (c1==null || c2 == null || c3 == null || c4 == null || c5 == null || c6 == null ||
                c7 == null || c8 == null || stage == null) {
            return badRequest("At least one cyclist or stage not existing in DB");
        }
        //Check ids exist and are not repated
        if (!uniqueElements(c1.getId(),c2.getId(),c3.getId(),c4.getId(),c5.getId(),c6.getId(),c7.getId(),c8.getId())) {
            return badRequest("All cyclists must be different");
        }
        //Check that stage hasn't reached number of m1s specified
        int n = DBUtils.getNumberOfSprints("mhc",stage);
        if (n >= stage.getM1()) {
            return badRequest("This stage has already stored " + n + " mhcs");
        }
        mhc.setCyclist1(c1);
        mhc.setCyclist2(c2);
        mhc.setCyclist3(c3);
        mhc.setCyclist4(c4);
        mhc.setCyclist5(c5);
        mhc.setCyclist6(c6);
        mhc.setCyclist7(c7);
        mhc.setCyclist8(c8);
        mhc.setStage(stage);

        EntityManager em = JPA.em();
        if (values.containsKey(NAME)) {
            mhc.setName(values.get(NAME)[0]);
        }
        try {
            em.persist(mhc);
        } catch (PersistenceException e) {
            return status(400,"Persisting error");
        }
        em.flush();
        return ok(Json.toJson(mhc));
    }

    @Transactional
    public static Result createSInt() {
        IntermediateSprint sInt = new IntermediateSprint();
        Http.RequestBody body = request().body();
        Map<String,String[]> values =  body.asFormUrlEncoded();
        //Check all keys exists
        if (!StringUtils.allCyclistsExists(values,3)|| !values.containsKey(STAGE)) {
            return badRequest("Missing parameters cyclist1..cyclist3 or stage");
        }
        Crud<CyclistRace> crudCyclist = new Crud<CyclistRace>("cyclists",CyclistRace.class);
        Crud<Stage> crudStage = new Crud<Stage>("stages",Stage.class);
        CyclistRace c1,c2,c3;
        Stage stage= crudStage.getModel(StringUtils.getLong(values, STAGE));
        c1 = crudCyclist.getModel(StringUtils.getLong(values, CYCLIST + 1));
        c2 = crudCyclist.getModel(StringUtils.getLong(values,CYCLIST+2));
        c3 = crudCyclist.getModel(StringUtils.getLong(values,CYCLIST+3));
        //Check ids exists
        if (c1==null || c2 == null || c3 == null || stage == null) {
            return badRequest("At least one cyclist or stage not existing in DB");
        }
        //Check ids exist and are not repated
        if (!uniqueElements(c1.getId(), c2.getId(), c3.getId())) {
            return badRequest("All cyclists must be different");
        }
        //Check that stage hasn't reached number of m1s specified
        int n = DBUtils.getNumberOfSprints("intermediatesprint",stage);
        if (n >= stage.getM1()) {
            return badRequest("This stage has already stored " + n + " intermediate sprints");
        }
        sInt.setCyclist1(c1);
        sInt.setCyclist2(c2);
        sInt.setCyclist3(c3);
        sInt.setStage(stage);

        EntityManager em = JPA.em();
        if (values.containsKey(NAME)) {
            sInt.setName(values.get(NAME)[0]);
        }
        try {
            em.persist(sInt);
        } catch (PersistenceException e) {
            return status(400,"Persisting error");
        }
        em.flush();
        return ok(Json.toJson(sInt));
    }

    @Transactional
    public static Result createTTT() {
        TeamTimeTrialClassification ttt = new TeamTimeTrialClassification();

        Http.RequestBody body = request().body();
        Map<String,String[]> values =  body.asFormUrlEncoded();

        //Check if missing parameters
        if (!StringUtils.allTeamsExists(values) || !values.containsKey(STAGE)) {
            return badRequest("Missing parameters teams, gcyclists, mcyclists or rcyclists");
        }
        Team team1,team2,team3,team4,team5,team6,team7,team8,team9,team10;
        Stage stage;

        //All teams, gcyclists,mcyclists and rcyclists must be different
        if (!uniqueElements(StringUtils.getLong(values,TEAM+1),StringUtils.getLong(values,TEAM+2),StringUtils.getLong(values,TEAM+3),
                StringUtils.getLong(values,TEAM+4),StringUtils.getLong(values,TEAM+5),StringUtils.getLong(values,TEAM+6),
                StringUtils.getLong(values,TEAM+7),StringUtils.getLong(values,TEAM+8),StringUtils.getLong(values,TEAM+9),
                StringUtils.getLong(values,TEAM+10))) {
                return badRequest("Teams, gcyclists, rcyclists and mcyclists must be different");
        }
        EntityManager em = JPA.em();
        team1 = em.find(Team.class,StringUtils.getLong(values,TEAM+1));
        team2 = em.find(Team.class,StringUtils.getLong(values,TEAM+2));
        team3 = em.find(Team.class,StringUtils.getLong(values,TEAM+3));
        team4 = em.find(Team.class,StringUtils.getLong(values,TEAM+4));
        team5 = em.find(Team.class,StringUtils.getLong(values,TEAM+5));
        team6 = em.find(Team.class,StringUtils.getLong(values,TEAM+6));
        team7 = em.find(Team.class,StringUtils.getLong(values,TEAM+7));
        team8 = em.find(Team.class,StringUtils.getLong(values,TEAM+8));
        team9 = em.find(Team.class,StringUtils.getLong(values,TEAM+9));
        team10 = em.find(Team.class,StringUtils.getLong(values,TEAM+10));
        stage = em.find(Stage.class,StringUtils.getLong(values,STAGE));

        //Check all ids exist
        if (team1 == null || team2 == null || team3 == null || team4==null || team5 == null || team6 == null || team7 == null
                || team8 == null || team9 == null || team10 == null || stage == null) {
            return badRequest("Some id not exists in DB");
        }
        ttt.setTeam1(team1);
        ttt.setTeam2(team2);
        ttt.setTeam3(team3);
        ttt.setTeam4(team4);
        ttt.setTeam5(team5);
        ttt.setTeam6(team6);
        ttt.setTeam7(team7);
        ttt.setTeam8(team8);
        ttt.setTeam9(team9);
        ttt.setTeam10(team10);
        ttt.setStage(stage);

        try {
            em.persist(ttt);
        } catch (PersistenceException e) {
            return status(400,"Persisting error");
        }
        em.flush();
        return ok(Json.toJson(ttt));
    }

    @Transactional
    public static Result createStageClassification() {
        StageSprint ss = new StageSprint();

        Http.RequestBody body = request().body();
        Map<String,String[]> values =  body.asFormUrlEncoded();

        //Check if missing parameters
        if (!StringUtils.allCyclistsExists(values, 20)  || !values.containsKey(STAGE)) {
            return badRequest("Missing parameters teams, gcyclists, mcyclists or rcyclists");
        }
        CyclistRace c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18,c19,c20;
        Stage stage;

        //All teams, cyclists must be different
        if (!uniqueElements(StringUtils.getLong(values,CYCLIST+1),StringUtils.getLong(values,CYCLIST+2),StringUtils.getLong(values,CYCLIST+3),
                StringUtils.getLong(values,CYCLIST+4),StringUtils.getLong(values,CYCLIST+5),StringUtils.getLong(values,CYCLIST+6),
                StringUtils.getLong(values,CYCLIST+7),StringUtils.getLong(values,CYCLIST+8),StringUtils.getLong(values,CYCLIST+9),
                StringUtils.getLong(values,CYCLIST+10),StringUtils.getLong(values,CYCLIST+11),StringUtils.getLong(values,CYCLIST+12),
                StringUtils.getLong(values,CYCLIST+13),StringUtils.getLong(values,CYCLIST+14),StringUtils.getLong(values,CYCLIST+15),
                StringUtils.getLong(values,CYCLIST+16),StringUtils.getLong(values,CYCLIST+17),StringUtils.getLong(values,CYCLIST+18),
                StringUtils.getLong(values,CYCLIST+19),StringUtils.getLong(values,CYCLIST+20))) {
            return badRequest("cyclists must be different");
        }
        EntityManager em = JPA.em();
        c1 = em.find(CyclistRace.class,StringUtils.getLong(values,CYCLIST+1));
        c2 = em.find(CyclistRace.class,StringUtils.getLong(values,CYCLIST+2));
        c3 = em.find(CyclistRace.class,StringUtils.getLong(values,CYCLIST+3));
        c4 = em.find(CyclistRace.class,StringUtils.getLong(values,CYCLIST+4));
        c5 = em.find(CyclistRace.class,StringUtils.getLong(values,CYCLIST+5));
        c6 = em.find(CyclistRace.class,StringUtils.getLong(values,CYCLIST+6));
        c7 = em.find(CyclistRace.class,StringUtils.getLong(values,CYCLIST+7));
        c8 = em.find(CyclistRace.class,StringUtils.getLong(values,CYCLIST+8));
        c9 = em.find(CyclistRace.class,StringUtils.getLong(values,CYCLIST+9));
        c10 = em.find(CyclistRace.class,StringUtils.getLong(values,CYCLIST+10));
        c11 = em.find(CyclistRace.class,StringUtils.getLong(values,CYCLIST+11));
        c12 = em.find(CyclistRace.class,StringUtils.getLong(values,CYCLIST+12));
        c13 = em.find(CyclistRace.class,StringUtils.getLong(values,CYCLIST+13));
        c14 = em.find(CyclistRace.class,StringUtils.getLong(values,CYCLIST+14));
        c15 = em.find(CyclistRace.class,StringUtils.getLong(values,CYCLIST+15));
        c16 = em.find(CyclistRace.class,StringUtils.getLong(values,CYCLIST+16));
        c17 = em.find(CyclistRace.class,StringUtils.getLong(values,CYCLIST+17));
        c18 = em.find(CyclistRace.class,StringUtils.getLong(values,CYCLIST+18));
        c19 = em.find(CyclistRace.class,StringUtils.getLong(values,CYCLIST+19));
        c20 = em.find(CyclistRace.class,StringUtils.getLong(values,CYCLIST+20));
        stage = em.find(Stage.class,StringUtils.getLong(values,STAGE));

        //Check all ids exist
        if (c1 == null || c2 == null || c3 == null || c4==null || c5 == null || c6 == null || c7 == null || c8 == null ||
                c9 == null || c10 == null || c11 == null || c12 == null || c13 == null || c14 == null || c15 == null ||
                c16 == null || c17 == null || c18 == null || c19 == null || c20 == null || stage == null) {
            return badRequest("Some id not exists in DB");
        }
        ss.setCyclist1(c1);
        ss.setCyclist2(c2);
        ss.setCyclist3(c3);
        ss.setCyclist4(c4);
        ss.setCyclist5(c5);
        ss.setCyclist6(c6);
        ss.setCyclist7(c7);
        ss.setCyclist8(c8);
        ss.setCyclist9(c9);
        ss.setCyclist10(c10);
        ss.setCyclist11(c11);
        ss.setCyclist12(c12);
        ss.setCyclist13(c13);
        ss.setCyclist14(c14);
        ss.setCyclist15(c15);
        ss.setCyclist16(c16);
        ss.setCyclist17(c17);
        ss.setCyclist18(c18);
        ss.setCyclist19(c19);
        ss.setCyclist20(c20);
        ss.setStage(stage);

        try {
            em.persist(ss);
        } catch (PersistenceException e) {
            return status(400,"Persisting error");
        }
        em.flush();
        return ok(Json.toJson(ss));
    }

    @Transactional
    public static Result createStageAllClassifications() {
        General general = null;
        TeamTimeTrialClassification tttClass = null;
        StageSprint stageClass = null;
        Mountain mountain = null;
        Points points = null;
        List<MountainFirst> m1s = null;
        List<MountainSecond> m2s = null;
        List<MountainThird> m3s = null;
        List<MountainFourth> m4s = null;
        List<MountainHC> mhcs = null;
        List<IntermediateSprint> sprints = null;

        Http.RequestBody body = request().body();
        JsonNode json = body.asJson();
        if (!json.has("id")) {
            return badRequest("Missing stage id");
        }
        long stageId = json.get("id").asLong();
        JsonNode gc = json.get("gc");
        JsonNode rc = json.get("rc");
        JsonNode mc = json.get("mc");
        JsonNode ttt = json.get("ttt");
        JsonNode stage = json.get("stage");
        JsonNode sInt = json.get("sInt");
        JsonNode m1 = json.get("m1");
        JsonNode m2 = json.get("m2");
        JsonNode m3 = json.get("m3");
        JsonNode m4 = json.get("m4");
        JsonNode mhc = json.get("mhc");
        if (gc == null || (ttt == null && stage == null)) {
            return badRequest("General or stage cannot be empty");
        }
        if (gc.size() != 10) {
            return badRequest("General classification has to be 10 cyclist length");
        }
        if (ttt != null && ttt.size() != 10) {
            return badRequest("TTT has to be 10 teams length");
        }
        if (stage != null && stage.size() != 20) {
            return badRequest("Stage has to be 20 cyclist length");
        }
        EntityManager em = JPA.em();
        Stage stageModel = em.find(Stage.class,stageId);
        if (stageModel == null) {
            return badRequest("Stage for id " + stageId + " doesnt exist");
        }
        general = new General();

        general.setCyclist1(em.find(CyclistRace.class, gc.get(0).asLong()));
        general.setCyclist2(em.find(CyclistRace.class, gc.get(1).asLong()));
        general.setCyclist3(em.find(CyclistRace.class, gc.get(2).asLong()));
        general.setCyclist4(em.find(CyclistRace.class, gc.get(3).asLong()));
        general.setCyclist5(em.find(CyclistRace.class, gc.get(4).asLong()));
        general.setCyclist6(em.find(CyclistRace.class, gc.get(5).asLong()));
        general.setCyclist7(em.find(CyclistRace.class, gc.get(6).asLong()));
        general.setCyclist8(em.find(CyclistRace.class, gc.get(7).asLong()));
        general.setCyclist9(em.find(CyclistRace.class, gc.get(8).asLong()));
        general.setCyclist10(em.find(CyclistRace.class, gc.get(9).asLong()));
        general.setStage(stageModel);

        if (ttt != null) {
            tttClass = new TeamTimeTrialClassification();
            tttClass.setTeam1(em.find(Team.class,ttt.get(0).asLong()));
            tttClass.setTeam2(em.find(Team.class, ttt.get(1).asLong()));
            tttClass.setTeam3(em.find(Team.class, ttt.get(2).asLong()));
            tttClass.setTeam4(em.find(Team.class, ttt.get(3).asLong()));
            tttClass.setTeam5(em.find(Team.class, ttt.get(4).asLong()));
            tttClass.setTeam6(em.find(Team.class, ttt.get(5).asLong()));
            tttClass.setTeam7(em.find(Team.class, ttt.get(6).asLong()));
            tttClass.setTeam8(em.find(Team.class, ttt.get(7).asLong()));
            tttClass.setTeam9(em.find(Team.class, ttt.get(8).asLong()));
            tttClass.setTeam10(em.find(Team.class, ttt.get(9).asLong()));
            tttClass.setStage(stageModel);
        }
        if (stage != null) {
            stageClass = new StageSprint();
            stageClass.setStage(stageModel);
            stageClass.setCyclist1(em.find(CyclistRace.class, gc.get(0).asLong()));
            stageClass.setCyclist2(em.find(CyclistRace.class, gc.get(1).asLong()));
            stageClass.setCyclist3(em.find(CyclistRace.class, gc.get(2).asLong()));
            stageClass.setCyclist4(em.find(CyclistRace.class, gc.get(3).asLong()));
            stageClass.setCyclist5(em.find(CyclistRace.class, gc.get(4).asLong()));
            stageClass.setCyclist6(em.find(CyclistRace.class, gc.get(5).asLong()));
            stageClass.setCyclist7(em.find(CyclistRace.class, gc.get(6).asLong()));
            stageClass.setCyclist8(em.find(CyclistRace.class, gc.get(7).asLong()));
            stageClass.setCyclist9(em.find(CyclistRace.class, gc.get(8).asLong()));
            stageClass.setCyclist10(em.find(CyclistRace.class, gc.get(9).asLong()));
            stageClass.setCyclist11(em.find(CyclistRace.class, gc.get(10).asLong()));
            stageClass.setCyclist12(em.find(CyclistRace.class, gc.get(11).asLong()));
            stageClass.setCyclist13(em.find(CyclistRace.class, gc.get(12).asLong()));
            stageClass.setCyclist14(em.find(CyclistRace.class, gc.get(13).asLong()));
            stageClass.setCyclist15(em.find(CyclistRace.class, gc.get(14).asLong()));
            stageClass.setCyclist16(em.find(CyclistRace.class, gc.get(15).asLong()));
            stageClass.setCyclist17(em.find(CyclistRace.class, gc.get(16).asLong()));
            stageClass.setCyclist18(em.find(CyclistRace.class, gc.get(17).asLong()));
            stageClass.setCyclist19(em.find(CyclistRace.class, gc.get(18).asLong()));
            stageClass.setCyclist20(em.find(CyclistRace.class, gc.get(19).asLong()));
        }
        if (rc != null) {
            if (rc.size() != 3) {
                return badRequest("Points classification has to be length 3");
            }
            points = new Points();
            points.setStage(stageModel);
            points.setCyclist1(em.find(CyclistRace.class, rc.get(0)));
            points.setCyclist2(em.find(CyclistRace.class, rc.get(1)));
            points.setCyclist3(em.find(CyclistRace.class, rc.get(2)));
        }
        if (mc != null) {
            if (mc.size() != 3) {
                return badRequest("Mountain classification has to be length 3");
            }
            mountain = new Mountain();
            mountain.setStage(stageModel);
            mountain.setCyclist1(em.find(CyclistRace.class,mc.get(0)));
            mountain.setCyclist2(em.find(CyclistRace.class,mc.get(1)));
            mountain.setCyclist3(em.find(CyclistRace.class,mc.get(2)));
        }
        if (sInt != null) {
            sprints = new ArrayList<IntermediateSprint>();
            for (int i = 0; i<sInt.size();i++){
                JsonNode jsonSInt = sInt.get(i);
                if (jsonSInt.size() != 3) {
                    return badRequest("Intermediate sprints must be 3 length");
                }
                IntermediateSprint sprint = new IntermediateSprint();
                sprint.setStage(stageModel);
                sprint.setCyclist1(em.find(CyclistRace.class, jsonSInt.get(0)));
                sprint.setCyclist2(em.find(CyclistRace.class, jsonSInt.get(1)));
                sprint.setCyclist3(em.find(CyclistRace.class, jsonSInt.get(2)));
                sprints.add(sprint);
            }
        }
        if (m1 != null) {
            m1s = new ArrayList<MountainFirst>();
            for (int i = 0; i<m1.size();i++){
                JsonNode jsonM1 = m1.get(i);
                if (jsonM1.size() != MountainFirst.LENGTH) {
                    return badRequest("Mountain first must be " + MountainFirst.LENGTH +" length");
                }
                MountainFirst mFirst = new MountainFirst();
                mFirst.setStage(stageModel);
                mFirst.setCyclist1(em.find(CyclistRace.class, jsonM1.get(0)));
                mFirst.setCyclist2(em.find(CyclistRace.class, jsonM1.get(1)));
                mFirst.setCyclist3(em.find(CyclistRace.class, jsonM1.get(2)));
                mFirst.setCyclist4(em.find(CyclistRace.class, jsonM1.get(3)));
                mFirst.setCyclist5(em.find(CyclistRace.class, jsonM1.get(4)));
                m1s.add(mFirst);
            }
        }
        if (mhc != null) {
            mhcs = new ArrayList<MountainHC>();
            for (int i = 0; i<mhc.size();i++){
                JsonNode jsonMHC = mhc.get(i);
                if (jsonMHC.size() != MountainHC.LENGTH) {
                    return badRequest("Mountain hc must be " + MountainHC.LENGTH +" length");
                }
                MountainHC mountainHC = new MountainHC();
                mountainHC.setStage(stageModel);
                mountainHC.setCyclist1(em.find(CyclistRace.class, jsonMHC.get(0)));
                mountainHC.setCyclist2(em.find(CyclistRace.class, jsonMHC.get(1)));
                mountainHC.setCyclist3(em.find(CyclistRace.class, jsonMHC.get(2)));
                mountainHC.setCyclist4(em.find(CyclistRace.class, jsonMHC.get(3)));
                mountainHC.setCyclist5(em.find(CyclistRace.class, jsonMHC.get(4)));
                mountainHC.setCyclist6(em.find(CyclistRace.class, jsonMHC.get(5)));
                mountainHC.setCyclist7(em.find(CyclistRace.class, jsonMHC.get(6)));
                mountainHC.setCyclist8(em.find(CyclistRace.class, jsonMHC.get(7)));
                mhcs.add(mountainHC);
            }
        }
        if (m2 != null) {
            m2s = new ArrayList<MountainSecond>();
            for (int i = 0; i<m2.size();i++){
                JsonNode jsonM2 = m2.get(i);
                if (jsonM2.size() != MountainSecond.LENGTH) {
                    return badRequest("Mountain second must be " + MountainSecond.LENGTH +" length");
                }
                MountainSecond mountainSecond = new MountainSecond();
                mountainSecond.setStage(stageModel);
                mountainSecond.setCyclist1(em.find(CyclistRace.class, jsonM2.get(0)));
                mountainSecond.setCyclist2(em.find(CyclistRace.class, jsonM2.get(1)));
                mountainSecond.setCyclist3(em.find(CyclistRace.class, jsonM2.get(2)));
                m2s.add(mountainSecond);
            }
        }
        if (m3 != null) {
            m3s = new ArrayList<MountainThird>();
            for (int i = 0; i<m3.size();i++){
                JsonNode jsonM3 = m3.get(i);
                if (jsonM3.size() != MountainThird.LENGTH) {
                    return badRequest("Mountain second must be " + MountainThird.LENGTH +" length");
                }
                MountainThird mountainThird = new MountainThird();
                mountainThird.setStage(stageModel);
                mountainThird.setCyclist1(em.find(CyclistRace.class, jsonM3.get(0)));
                mountainThird.setCyclist2(em.find(CyclistRace.class, jsonM3.get(1)));
                m3s.add(mountainThird);
            }
        }
        if (m4 != null) {
            m4s = new ArrayList<MountainFourth>();
            for (int i = 0; i<m4.size();i++){
                JsonNode jsonM4 = m4.get(i);
                if (jsonM4.size() != MountainFourth.LENGTH) {
                    return badRequest("Mountain second must be " + MountainFourth.LENGTH +" length");
                }
                MountainFourth mountainFourth = new MountainFourth();
                mountainFourth.setStage(stageModel);
                mountainFourth.setCyclist1(em.find(CyclistRace.class, jsonM4.get(0)));
                m4s.add(mountainFourth);
            }
        }
        try {
            em.persist(general);
            if (points != null) {
                em.persist(points);
            }
            if (mountain != null) {
                em.persist(mountain);
            }
            if (tttClass != null) {
                em.persist(tttClass);
            }
            if (stageClass != null) {
                em.persist(stageClass);
            }
            if (mhcs != null) {
                for (MountainHC mountainHC: mhcs) {
                    em.persist(mountainHC);
                }
            }
            if (m1s != null) {
                for (MountainFirst mountainFirst: m1s) {
                    em.persist(mountainFirst);
                }
            }
            if (m2s != null) {
                for (MountainSecond mountainSecond: m2s) {
                    em.persist(mountainSecond);
                }
            }
            if (m3s != null) {
                for (MountainThird mountainThird: m3s) {
                    em.persist(mountainThird);
                }
            }
            if (m4s != null) {
                for (MountainFourth mountainFourth: m4s) {
                    em.persist(mountainFourth);
                }
            }
            if (sprints != null) {
                for (IntermediateSprint intermediateSprint: sprints) {
                    em.persist(intermediateSprint);
                }
            }
            return ok("Stored correctly");
        } catch (Exception e) {
            System.out.println("Message " + e.getMessage());
            return badRequest("Error storing in db");
        }
    }

    private static Boolean uniqueElements(Long... ids) {
        Set<Long> set = new HashSet<Long>();
        for (Long id : ids) {
            set.add(id);
        }
        return set.size() == ids.length;
    }

}
