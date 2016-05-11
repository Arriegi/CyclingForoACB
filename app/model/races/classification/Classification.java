package model.races.classification;

import play.db.jpa.Transactional;

/**
 * Created by lenovo on 03/02/2015.
 */
public interface Classification {



    @Transactional
    public void calculatePoints();
}
