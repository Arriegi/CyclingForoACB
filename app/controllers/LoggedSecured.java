package controllers;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by lenovo on 03/02/2015.
 */
public class LoggedSecured extends Security.Authenticator {

    @Override
    public String getUsername(Http.Context ctx) {
        return ctx.session().get("email");
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        return redirect("/login");
    }
}
