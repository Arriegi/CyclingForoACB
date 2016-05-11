package model.races;

/**
 * Created by Jon on 31/01/2015.
 */
public enum RaceName {
    TOUR, GIRO, VUELTA;

    public String toCamelCase() {
        return this.name().substring(0, 1) + this.name().substring(1).toLowerCase();
    }
}
