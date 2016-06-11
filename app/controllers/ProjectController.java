package controllers;

import play.*;
import play.data.*;
import play.mvc.*;
import views.html.*;
import java.util.*;
import models.*;
import play.data.Form;
import static play.data.Form.*;
import play.api.mvc.BodyParsers.*;
import play.libs.Json;

public class ProjectController extends Controller {

    public Result index(String projectId) {
        
        Project projectModel;
        
        Form<Project> projectForm = form(Project.class);
        
        if(projectId != null) {
            projectModel = Project.find.byId(projectId);
            if(projectModel != null) {
                return ok(project.render(projectModel));
            }
        }
        
        return ok(project.render(new Project()));
    }
    
    public Result fetch(String projectId) {
        Project projectModel = Project.find.byId(projectId);
        return ok(Json.toJson(projectModel));
    }
    
    
    public Result submit() {

        Form<Project> formData = Form.form(Project.class).bindFromRequest();

        Project projectModel= formData.get();

        try{
            projectModel.save();
            flash("Saved");
            System.out.println("Project: " + projectModel.projectID + " is created!");
        }
        catch (Exception e)
        {
            flash("Updated");
            projectModel.update();
            System.out.println("Project: " + projectModel.projectID + " is updated!");
        }

        return redirect("/project?projectId=" + projectModel.projectID);
    }
    
}


