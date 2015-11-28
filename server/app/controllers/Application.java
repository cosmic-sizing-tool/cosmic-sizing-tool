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
    
    public Result counter() {
        return ok(counter.render());
    }

    public Result team(){
        TeamMember member = new TeamMember();
        member.name = "bob";
        member.save();

        List<TeamMember> members = TeamMember.find.all();
        return ok(team.render(members));
    }

}
