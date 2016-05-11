package model.races;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.races.classification.TeamTimeTrialClassification;
import model.races.classification.general.General;
import model.races.classification.general.Mountain;
import model.races.classification.general.Points;
import model.races.classification.mountain.*;
import model.races.classification.sprint.IntermediateSprint;
import model.races.classification.sprint.StageSprint;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Jon on 30/01/2015.
 */
@Entity(name = "stages")
public class Stage implements Comparable<Stage>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name="raceId")
    @JsonIgnore
    private Race race;
    private String description;
    private int mHC,m1,m2,m3,m4,sInt,stageNumber;
    private boolean isTTT,hasMC,hasRC;

    @Transient
    private General generalClassification;
    @Transient
    private StageSprint stageClassification;
    @Transient
    private TeamTimeTrialClassification tttClassification;
    @Transient
    private Mountain mountainClassification;
    @Transient
    private Points pointsClassification;
    @ElementCollection
    private List<MountainHC> mhcClassificationList;
    @ElementCollection
    private List<MountainFirst> m1ClassificationList;
    @ElementCollection
    private List<MountainSecond> m2ClassificationList;
    @ElementCollection
    private List<MountainThird> m3ClassificationList;
    @ElementCollection
    private List<MountainFourth> m4ClassificationList;
    @ElementCollection
    private List<IntermediateSprint> sIntClassificationList;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getmHC() {
        return mHC;
    }

    public void setmHC(int mHC) {
        this.mHC = mHC;
        if (this.mhcClassificationList == null) {
            this.mhcClassificationList = new ArrayList<MountainHC>(mHC);
        }
    }

    public int getM1() {
        return m1;
    }

    public void setM1(int m1) {
        this.m1 = m1;
        if (this.m1ClassificationList == null) {
            this.m1ClassificationList = new ArrayList<MountainFirst>(m1);
        }
    }

    public int getM2() {
        return m2;
    }

    public void setM2(int m2) {
        this.m2 = m2;
        if (this.m2ClassificationList == null) {
            this.m2ClassificationList = new ArrayList<MountainSecond>(m2);
        }
    }

    public int getM3() {
        return m3;
    }

    public void setM3(int m3) {
        this.m3 = m3;
        if (this.m3ClassificationList == null) {
            this.m3ClassificationList = new ArrayList<MountainThird>(m3);
        }
    }

    public int getM4() {
        return m4;
    }

    public void setM4(int m4) {
        this.m4 = m4;
        if (this.m4ClassificationList == null) {
            this.m4ClassificationList = new ArrayList<MountainFourth>(m4);
        }
    }

    public int getsInt() {
        return sInt;
    }

    public void setsInt(int sInt) {
        this.sInt = sInt;
        if (this.sIntClassificationList == null) {
            this.sIntClassificationList = new ArrayList<IntermediateSprint>(sInt);
        }
    }

    public boolean isTTT() {
        return isTTT;
    }

    public void setTTT(boolean isTTT) {
        this.isTTT = isTTT;
    }

    public int getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(int stageNumber) {
        this.stageNumber = stageNumber;
    }

    public boolean hasMC() {
        return hasMC;
    }

    public void setMC(boolean hasMC) {
        this.hasMC = hasMC;
    }

    public boolean hasRC() {
        return hasRC;
    }

    public void setRC(boolean hasRC) {
        this.hasRC = hasRC;
    }

    public General getGeneralClassification() {
        return generalClassification;
    }

    public void setGeneralClassification(General generalClassification) {
        this.generalClassification = generalClassification;
    }

    public TeamTimeTrialClassification getTttClassification() {
        return tttClassification;
    }

    public void setTttClassification(TeamTimeTrialClassification tttClassification) {
        this.tttClassification = tttClassification;
    }

    public Mountain getMountainClassification() {
        return mountainClassification;
    }

    public void setMountainClassification(Mountain mountainClassification) {
        this.mountainClassification = mountainClassification;
    }

    public Points getPointsClassification() {
        return pointsClassification;
    }

    public void setPointsClassification(Points pointsClassification) {
        this.pointsClassification = pointsClassification;
    }

    public List<MountainHC> getMhcClassificationList() {
        return mhcClassificationList;
    }

    public void setMhcClassificationList(List<MountainHC> mhcClassificationList) {
        this.mhcClassificationList = mhcClassificationList;
    }

    public List<MountainFirst> getM1ClassificationList() {
        return m1ClassificationList;
    }

    public void setM1ClassificationList(List<MountainFirst> m1ClassificationList) {
        this.m1ClassificationList = m1ClassificationList;
    }

    public List<MountainSecond> getM2ClassificationList() {
        return m2ClassificationList;
    }

    public void setM2ClassificationList(List<MountainSecond> m2ClassificationList) {
        this.m2ClassificationList = m2ClassificationList;
    }

    public List<MountainThird> getM3ClassificationList() {
        return m3ClassificationList;
    }

    public void setM3ClassificationList(List<MountainThird> m3ClassificationList) {
        this.m3ClassificationList = m3ClassificationList;
    }

    public List<MountainFourth> getM4ClassificationList() {
        return m4ClassificationList;
    }

    public void setM4ClassificationList(List<MountainFourth> m4ClassificationList) {
        this.m4ClassificationList = m4ClassificationList;
    }

    public List<IntermediateSprint> getsIntClassificationList() {
        return sIntClassificationList;
    }

    public void setsIntClassificationList(List<IntermediateSprint> sIntClassificationList) {
        this.sIntClassificationList = sIntClassificationList;
    }

    public StageSprint getStageClassification() {
        return stageClassification;
    }

    public void setStageClassification(StageSprint stageClassification) {
        this.stageClassification = stageClassification;
    }

    @Override
    public int compareTo(Stage other) {
        return stageNumber-other.getStageNumber();
    }
}
