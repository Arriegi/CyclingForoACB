package controllers;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import play.Logger;

/**
 * Created by lenovo on 03/02/2015.
 */
public class AdminSecured extends Security.Authenticator {

    private final static String ADMIN_EMAIL = "jarriaga86@gmail.com";

    @Override
    public String getUsername(Http.Context ctx) {
        if (ctx != null && ctx.session() != null && ctx.session().get("email") != null && ctx.session().get("email").equals(ADMIN_EMAIL)) {
            return ctx.session().get("email");
        } else {
            return null;
        }
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        return redirect("/login");
    }
}
