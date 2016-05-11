package model.points;

import model.races.Stage;
import org.omg.CosNaming._NamingContextExtStub;

import javax.persistence.*;

/**
 * Created by Jon on 15/02/2015.
 */
@Entity(name = "cyclist_stage_points")
public class CyclistRacePoints {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "cyclistId")
    private CyclistRace cyclist;
    @ManyToOne
    @JoinColumn(name = "stageId")
    private Stage stage;
    @JoinColumn(name = "points")
    private int points;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CyclistRace getCyclist() {
        return cyclist;
    }

    public void setCyclist(CyclistRace cyclist) {
        this.cyclist = cyclist;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
