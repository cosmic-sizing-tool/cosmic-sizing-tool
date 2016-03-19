package controllers;

import play.*;
import play.data.*;
import play.mvc.*;
import views.html.*;
import java.util.*;
import models.*;
import play.data.Form;
import static play.data.Form.*;

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
        
        return ok(project.render());
    }
    
    
}


