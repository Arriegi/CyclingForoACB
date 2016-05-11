package model.cyclists;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Jon on 31/01/2015.
 */

@Entity(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String uciCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUciCode() {
        return uciCode;
    }

    public void setUciCode(String uciCode) {
        this.uciCode = uciCode;
    }
}
