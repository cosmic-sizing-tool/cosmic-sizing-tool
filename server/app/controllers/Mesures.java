package controllers;

import play.*;
import play.mvc.*;
import views.html.*;
import java.util.*;
import models.*;

public class Mesures extends Controller {

    public Result add() {
        return ok(mesures_add.render());
    }

}
