package model.races.classification.general;

import model.cyclists.Cyclist;
import model.points.CyclistRace;
import model.races.Stage;
import model.races.classification.Classification;

import javax.persistence.*;

/**
 * Created by Jon on 14/02/2015.
 */
@Entity(name = "generals")
public class General implements Classification{

    public static final int FIRST_POINTS = 15;
    public static final int SECOND_POINTS = 10;
    public static final int THIRD_POINTS = 8;
    public static final int FOURTH_POINTS = 7;
    public static final int FIFTH_POINTS = 6;
    public static final int SIXTH_POINTS = 5;
    public static final int SEVENTH_POINTS = 4;
    public static final int EIGHTH_POINTS = 3;
    public static final int NINTH_POINTS = 2;
    public static final int TENTH_POINTS = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "stageId")
    private Stage stage;
    @ManyToOne
    @JoinColumn(name = "cyclist1")
    private CyclistRace cyclist1;
    @ManyToOne
    @JoinColumn(name = "cyclist2")
    private CyclistRace cyclist2;
    @ManyToOne
    @JoinColumn(name = "cyclist3")
    private CyclistRace cyclist3;
    @ManyToOne
    @JoinColumn(name = "cyclist4")
    private CyclistRace cyclist4;
    @ManyToOne
    @JoinColumn(name = "cyclist5")
    private CyclistRace cyclist5;
    @ManyToOne
    @JoinColumn(name = "cyclist6")
    private CyclistRace cyclist6;
    @ManyToOne
    @JoinColumn(name = "cyclist7")
    private CyclistRace cyclist7;
    @ManyToOne
    @JoinColumn(name = "cyclist8")
    private CyclistRace cyclist8;
    @ManyToOne
    @JoinColumn(name = "cyclist9")
    private CyclistRace cyclist9;
    @ManyToOne
    @JoinColumn(name = "cyclist10")
    private CyclistRace cyclist10;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public CyclistRace getCyclist1() {
        return cyclist1;
    }

    public void setCyclist1(CyclistRace cyclist1) {
        this.cyclist1 = cyclist1;
    }

    public CyclistRace getCyclist2() {
        return cyclist2;
    }

    public void setCyclist2(CyclistRace cyclist2) {
        this.cyclist2 = cyclist2;
    }

    public CyclistRace getCyclist3() {
        return cyclist3;
    }

    public void setCyclist3(CyclistRace cyclist3) {
        this.cyclist3 = cyclist3;
    }

    public CyclistRace getCyclist4() {
        return cyclist4;
    }

    public void setCyclist4(CyclistRace cyclist4) {
        this.cyclist4 = cyclist4;
    }

    public CyclistRace getCyclist5() {
        return cyclist5;
    }

    public void setCyclist5(CyclistRace cyclist5) {
        this.cyclist5 = cyclist5;
    }

    public CyclistRace getCyclist6() {
        return cyclist6;
    }

    public void setCyclist6(CyclistRace cyclist6) {
        this.cyclist6 = cyclist6;
    }

    public CyclistRace getCyclist7() {
        return cyclist7;
    }

    public void setCyclist7(CyclistRace cyclist7) {
        this.cyclist7 = cyclist7;
    }

    public CyclistRace getCyclist8() {
        return cyclist8;
    }

    public void setCyclist8(CyclistRace cyclist8) {
        this.cyclist8 = cyclist8;
    }

    public CyclistRace getCyclist9() {
        return cyclist9;
    }

    public void setCyclist9(CyclistRace cyclist9) {
        this.cyclist9 = cyclist9;
    }

    public CyclistRace getCyclist10() {
        return cyclist10;
    }

    public void setCyclist10(CyclistRace cyclist10) {
        this.cyclist10 = cyclist10;
    }

    @Override
    public void calculatePoints() {

    }
}
