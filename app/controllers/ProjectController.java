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
            if(projectModel == null) {
                return ok(project.render(projectModel));
            } else {
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
        
        String id = formData.data().get("id");
        
        Project projectModel= formData.get();
        
        System.out.println(projectModel.id);
        
         projectModel.save();

        flash("Saved");
        
        System.out.println(projectModel.contact_person);
        
        
        return redirect("/project?projectId=" + projectModel.projectID);
    }
    
}


