package model.races;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by Jon on 30/01/2015.
 */
@Entity(name = "races")
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int year;
    @Enumerated(EnumType.STRING)
    private RaceName name;
    //@ElementCollection
    @OneToMany(mappedBy = "race", cascade={CascadeType.PERSIST})
    private Set<Stage> stages;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public RaceName getName() {
        return name;
    }

    public void setName(RaceName name) {
        this.name = name;
    }

    public Set<Stage> getStages() {
        return stages;
    }

    public void setStages(Set<Stage> stages) {
        this.stages = stages;
    }

    public List<Stage> getOrderedStages() {
        List<Stage> stageList = new ArrayList<Stage>();
        stageList.addAll(stages);
        Collections.sort(stageList);
        return stageList;
    }
}
