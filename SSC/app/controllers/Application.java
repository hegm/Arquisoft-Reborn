package controllers;

import play.mvc.*;
import play.data.Form;

import views.formdata.LoginFormData;
import views.html.*;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.Profile;
import views.html.Login;
import views.formdata.LoginFormData;
import play.mvc.Security;

public class Application extends Controller {

    /**
     * Provides the Index page.
     * @return The Index page.
     */
    public  Result index() {
        return ok(index.render("Home", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx())));
    }

    /**
     * Provides the Login page (only to unauthenticated users).
     * @return The Login page.
     */
    public  Result login() {
        Form<LoginFormData> formData = Form.form(LoginFormData.class);
        return ok(Login.render("Login", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), formData));
    }

    /**
     * Processes a login form submission from an unauthenticated user.
     * First we bind the HTTP POST data to an instance of LoginFormData.
     * The binding process will invoke the LoginFormData.validate() method.
     * If errors are found, re-render the page, displaying the error data.
     * If errors not found, render the page with the good data.
     * @return The index page with the results of validation.
     */
    public  Result postLogin() {

        // Get the submitted form data from the request object, and run validation.
        Form<LoginFormData> formData = Form.form(LoginFormData.class).bindFromRequest();

        if (formData.hasErrors()) {
            flash("error", "Login credentials not valid.");
            return badRequest(Login.render("Login", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), formData));
        }
        else {
            // email/password OK, so now we set the session variable and only go to authenticated pages.
            session().clear();
            session("email", formData.get().email);
            return redirect(routes.Application.profile());
        }
    }

    /**
     * Logs out (only for authenticated users) and returns them to the Index page.
     * @return A redirect to the Index page.
     */
    @Security.Authenticated(Secured.class)
    public  Result logout() {
        session().clear();
        return redirect(routes.Application.index());
    }

    /**
     * Provides the Profile page (only to authenticated users).
     * @return The Profile page.
     */
    @Security.Authenticated(Secured.class)
    public  Result profile() {
        return ok(Profile.render("Profile", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx())));
    }
}