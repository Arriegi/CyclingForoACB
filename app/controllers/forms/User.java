package controllers.forms;

import model.users.Player;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;
import play.db.jpa.JPA;
import utils.StringUtils;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 03/02/2015.
 */
public class User {

    @Constraints.Email
    private String email;
    private String password;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = StringUtils.md5(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = StringUtils.md5(password);
    }

    public List<ValidationError> validate() {

        List<ValidationError> errors = new ArrayList<ValidationError>();

        if (email == null || email.length() == 0) {
            errors.add(new ValidationError("email", "No email was given."));
        }

        if (password == null || password.length() == 0) {
            errors.add(new ValidationError("password", "No password was given."));
        } else if (password.length() < 5) {
            errors.add(new ValidationError("password", "Given password is less than five characters."));
        }

        Player player;
        EntityManager em = JPA.em();
        List<Player> players = em.createQuery("select p from players p where p.email = '" + email + "'", Player.class).getResultList();
        if (players != null && players.size()>0) {
            player = players.get(0);
            if (!player.getPassword().equals(this.password)) {
                errors.add(new ValidationError("password","Incorrect password for: " + player.getEmail() + "-" + player.getPassword() + " (giving " + email + "-" + password + ")"));
            }
        } else {
            errors.add(new ValidationError("email","This email doesn't exist in our database"));
        }
        if(errors.size() > 0) {
            return errors;
        } else {
            return null;
        }
    }
}
