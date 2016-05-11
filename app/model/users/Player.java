package model.users;

import utils.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Jon on 30/01/2015.
 */
@Entity(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String email;
    private String password;
    private Date signInDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setAlreadyMd5Password(String password) {this.password = password;}

    public Date getSignInDate() {
        return signInDate;
    }

    public void setSignInDate(Date signInDate) {
        this.signInDate = signInDate;
    }
}
