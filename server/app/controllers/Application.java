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
        flash("success", "message de succes");
        flash("error", "message d'erreur");
        TeamMember member = new TeamMember();
        member.name = "bob";
        member.save();

        List<TeamMember> members = TeamMember.find.all();
        return ok(team.render(members));
    }

}
