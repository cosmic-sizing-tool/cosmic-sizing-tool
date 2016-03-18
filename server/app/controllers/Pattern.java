package controllers;

/*
    13-03-2016
    Team Cowabunga :    Dany Deroy
                        Francis Bilodeau
                        Simon Zeni
                        John Béjot
                        Julien Girard
 */

import play.*;
import play.data.*;
import play.mvc.*;
import views.html.pattern.*;
import java.util.*;
import models.*;
import play.data.Form;
import static play.data.Form.*;


public class Pattern extends Controller {
    
    public Result index() {
		return ok(index.render());
    }

    public Result description(long id_pattern) {
        return ok(modification.render());
    }

    public Result delete(long id_pattern) {
        return ok("affichage delete");
    }

    public Result create() {
        return ok("affichage create");
    }

    public Result update(long id_pattern) {
        return ok("affichage update");
    }

}
