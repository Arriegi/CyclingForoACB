package model.races.classification.general;

import model.points.CyclistRace;
import model.races.Stage;
import model.races.classification.Classification;

import javax.persistence.*;

/**
 * Created by Jon on 14/02/2015.
 */
@Entity(name = "points")
public class Points implements Classification {
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

    @Override
    public void calculatePoints() {

    }
}
