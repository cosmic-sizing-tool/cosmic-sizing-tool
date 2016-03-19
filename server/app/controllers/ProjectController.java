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

    public Result index(Long projectId) {
        
        Project projectModel;
        
        Form<Project> projectForm = form(Project.class);
        
        if(projectId != 0) {
            projectModel = Project.find.byId(projectId);
            if(projectModel == null) {
                return ok(project.render(projectModel));
            } else {
                return ok(project.render(projectModel));
            }
            
        }
        
        return ok(project.render(null));

    }
    
    public Result fetch(Long projectId) {
        Project projectModel = Project.find.byId(projectId);
        return ok(Json.toJson(projectModel));
    }
    
    
    public Result submit() {
        
        Form<Project> formData = Form.form(Project.class).bindFromRequest();
        
        Project projectModel= formData.get();
        
        projectModel.save();
        
        flash("Saved");
        
        return ok(project.render(projectModel));
    }
    
}


