package controllers;

import play.*;
import play.mvc.*;
import views.html.*;
import java.util.*;
import models.*;

public class Application extends Controller {

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public Result team(){
        User member = new User();
        member.name = "bob";
        member.save();

        List<User> members = User.find.all();
        return ok(team.render(members));
    }

}
