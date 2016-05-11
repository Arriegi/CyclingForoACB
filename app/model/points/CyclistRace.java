package model.points;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.cyclists.Cyclist;
import model.races.Race;

import javax.persistence.*;

/**
 * Created by lenovo on 03/02/2015.
 */
@Entity(name = "cyclistraces")
public class CyclistRace {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "raceId")
    private Race race;
    @ManyToOne
    @JoinColumn(name = "cyclistId")
    private Cyclist cyclist;
    private int points,price;

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

    public Cyclist getCyclist() {
        return cyclist;
    }

    public void setCyclist(Cyclist cyclist) {
        this.cyclist = cyclist;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
