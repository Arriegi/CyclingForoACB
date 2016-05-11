package model.points;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.cyclists.Cyclist;
import model.races.Race;
import model.users.Player;

import javax.persistence.*;

/**
 * Created by lenovo on 04/02/2015.
 */
@Entity(name = "playerteams")
public class PlayerTeam {

    public final static int FIRST_DIVISION_COST = 2000;
    public final static int SECOND_DIVISION_COST = 1750;
    public final static int THIRD_DIVISION_COST = 1600;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "raceId")
    private Race race;
    @ManyToOne
    @JoinColumn(name = "playerId")
    private Player player;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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

    public int getCost() {
        if (cyclist1 != null && cyclist2 != null && cyclist3 != null && cyclist4 != null && cyclist5 != null &&
                cyclist6 != null && cyclist7 != null && cyclist8 != null && cyclist9 != null) {
            return cyclist1.getPrice() + cyclist2.getPrice() + cyclist3.getPrice() + cyclist4.getPrice() +
                    cyclist5.getPrice() + cyclist6.getPrice() + cyclist7.getPrice() + cyclist8.getPrice() + cyclist9.getPrice();
        } else {
            //Always will be >1750,2000 or whatever
            return Integer.MAX_VALUE;
        }
    }
}
