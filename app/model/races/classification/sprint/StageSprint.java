package model.races.classification.sprint;

import model.cyclists.Cyclist;
import model.points.CyclistRace;
import model.races.Stage;
import model.races.classification.Classification;

import javax.persistence.*;

/**
 * Created by Jon on 31/01/2015.
 */
@Entity(name = "stagesprint")
public class StageSprint implements Classification {

    public final static int LENGTH = 20;

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
    @ManyToOne
    @JoinColumn(name = "cyclist11")
    private CyclistRace cyclist11;
    @ManyToOne
    @JoinColumn(name = "cyclist12")
    private CyclistRace cyclist12;
    @ManyToOne
    @JoinColumn(name = "cyclist13")
    private CyclistRace cyclist13;
    @ManyToOne
    @JoinColumn(name = "cyclist14")
    private CyclistRace cyclist14;
    @ManyToOne
    @JoinColumn(name = "cyclist15")
    private CyclistRace cyclist15;
    @ManyToOne
    @JoinColumn(name = "cyclist16")
    private CyclistRace cyclist16;
    @ManyToOne
    @JoinColumn(name = "cyclist17")
    private CyclistRace cyclist17;
    @ManyToOne
    @JoinColumn(name = "cyclist18")
    private CyclistRace cyclist18;
    @ManyToOne
    @JoinColumn(name = "cyclist19")
    private CyclistRace cyclist19;
    @ManyToOne
    @JoinColumn(name = "cyclist20")
    private CyclistRace cyclist20;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public CyclistRace getCyclist11() {
        return cyclist11;
    }

    public void setCyclist11(CyclistRace cyclist11) {
        this.cyclist11 = cyclist11;
    }

    public CyclistRace getCyclist12() {
        return cyclist12;
    }

    public void setCyclist12(CyclistRace cyclist12) {
        this.cyclist12 = cyclist12;
    }

    public CyclistRace getCyclist13() {
        return cyclist13;
    }

    public void setCyclist13(CyclistRace cyclist13) {
        this.cyclist13 = cyclist13;
    }

    public CyclistRace getCyclist14() {
        return cyclist14;
    }

    public void setCyclist14(CyclistRace cyclist14) {
        this.cyclist14 = cyclist14;
    }

    public CyclistRace getCyclist15() {
        return cyclist15;
    }

    public void setCyclist15(CyclistRace cyclist15) {
        this.cyclist15 = cyclist15;
    }

    public CyclistRace getCyclist16() {
        return cyclist16;
    }

    public void setCyclist16(CyclistRace cyclist16) {
        this.cyclist16 = cyclist16;
    }

    public CyclistRace getCyclist17() {
        return cyclist17;
    }

    public void setCyclist17(CyclistRace cyclist17) {
        this.cyclist17 = cyclist17;
    }

    public CyclistRace getCyclist18() {
        return cyclist18;
    }

    public void setCyclist18(CyclistRace cyclist18) {
        this.cyclist18 = cyclist18;
    }

    public CyclistRace getCyclist19() {
        return cyclist19;
    }

    public void setCyclist19(CyclistRace cyclist19) {
        this.cyclist19 = cyclist19;
    }

    public CyclistRace getCyclist20() {
        return cyclist20;
    }

    public void setCyclist20(CyclistRace cyclist) {
        this.cyclist20 = cyclist;
    }

    @Override
    public void calculatePoints() {

    }
}
