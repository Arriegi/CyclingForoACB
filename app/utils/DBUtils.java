package utils;

import model.points.PlayerTeam;
import model.races.Race;
import model.races.Stage;
import model.users.Player;
import org.hibernate.annotations.NotFound;
import play.Logger;
import play.db.jpa.JPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by Jon on 05/02/2015.
 */
public class DBUtils {

    public static Player getPlayerByEmail(String email) {
        EntityManager em = JPA.em();
        List<Player> players = em.createQuery("select p from players p where p.email = :email",Player.class)
                .setParameter("email", email).getResultList();
        if (players == null || players.size() == 0) {
            return null;
        } else {
            return players.get(0);
        }
    }

    public static PlayerTeam getPlayerTeam(Race race, Player player) {
        EntityManager em = JPA.em();
        List<PlayerTeam> teams = null;
        try {
            teams = em.createQuery("select t from playerteams t where t.race = :race and t.player = :player", PlayerTeam.class)
                    .setParameter("race", race).setParameter("player", player).getResultList();
        } catch (EntityNotFoundException e) {
            Logger.error(e.getMessage());
        }
        if (teams == null || teams.size() == 0) {
            return null;
        } else {
            return teams.get(0);
        }
    }

    public static int getNumberOfSprints(String tableName, Stage stage) {
        EntityManager em = JPA.em();
        List<Object> models = em.createQuery("select t from " + tableName + " t where t.stage = :stage", Object.class).setParameter("stage",stage)
                .getResultList();
        return models.size();
    }

}
