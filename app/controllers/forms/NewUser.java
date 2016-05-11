package controllers.forms;

import play.data.validation.Constraints;
import play.data.validation.ValidationError;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 03/02/2015.
 */
public class NewUser {

    @Constraints.Email
    private String email;
    private String password;
    private String repeatPassword;

    public NewUser() {
    }

    public NewUser(String email, String password, String repeatPassword) {
        this.email = email;
        this.password = StringUtils.md5(password);
        this.repeatPassword = StringUtils.md5(repeatPassword);
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

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = StringUtils.md5(repeatPassword);
    }

    public List<ValidationError> validate() {

        List<ValidationError> errors = new ArrayList<ValidationError>();

        if (email == null || email.length() == 0) {
            errors.add(new ValidationError("email", "No email was given."));
        }

        if (!password.equals(repeatPassword)) {
            errors.add(new ValidationError("password","Two passwords are different"));
        }

        if (password == null || password.length() == 0) {
            errors.add(new ValidationError("password", "No password was given."));
        } else if (password.length() < 5) {
            errors.add(new ValidationError("password", "Given password is less than five characters."));
        }

        if(errors.size() > 0) {
            return errors;
        } else {
            return null;
        }
    }
}
