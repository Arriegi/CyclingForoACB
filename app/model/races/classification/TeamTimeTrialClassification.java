package model.races.classification;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.cyclists.Cyclist;
import model.cyclists.Team;
import model.races.Stage;
import play.db.jpa.Transactional;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Jon on 31/01/2015.
 */
@Entity(name = "stagettt")
public class TeamTimeTrialClassification implements Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "stageId")
    @JsonIgnore
    private Stage stage;
    @ManyToOne
    @JoinColumn(name = "team1")
    private Team team1;
    @ManyToOne
    @JoinColumn(name = "team2")
    private Team team2;
    @ManyToOne
    @JoinColumn(name = "team3")
    private Team team3;
    @ManyToOne
    @JoinColumn(name = "team4")
    private Team team4;
    @JoinColumn(name = "team5")
    @ManyToOne
    private Team team5;
    @ManyToOne
    @JoinColumn(name = "team6")
    private Team team6;
    @ManyToOne
    @JoinColumn(name = "team7")
    private Team team7;
    @ManyToOne
    @JoinColumn(name = "team8")
    private Team team8;
    @ManyToOne
    @JoinColumn(name = "team9")
    private Team team9;
    @ManyToOne
    @JoinColumn(name = "team10")
    private Team team10;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Team getTeam3() {
        return team3;
    }

    public void setTeam3(Team team3) {
        this.team3 = team3;
    }

    public Team getTeam4() {
        return team4;
    }

    public void setTeam4(Team team4) {
        this.team4 = team4;
    }

    public Team getTeam5() {
        return team5;
    }

    public void setTeam5(Team team5) {
        this.team5 = team5;
    }

    public Team getTeam6() {
        return team6;
    }

    public void setTeam6(Team team6) {
        this.team6 = team6;
    }

    public Team getTeam7() {
        return team7;
    }

    public void setTeam7(Team team7) {
        this.team7 = team7;
    }

    public Team getTeam8() {
        return team8;
    }

    public void setTeam8(Team team8) {
        this.team8 = team8;
    }

    public Team getTeam9() {
        return team9;
    }

    public void setTeam9(Team team9) {
        this.team9 = team9;
    }

    public Team getTeam10() {
        return team10;
    }

    public void setTeam10(Team team10) {
        this.team10 = team10;
    }

    @Override
    @Transactional
    public void calculatePoints() {
        //TODO obtener ciclistas puntuados de cada equipo, leer si estan metidos->Si lo estan add puntos y si no crea entidad y guardar
    }
}
