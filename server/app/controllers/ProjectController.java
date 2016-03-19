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
        
        Project project;
        
        Form<Project> projectForm = form(Project.class);
        
        if(projectId != 0) {
            project = Project.find.byId(projectId);
            if(project == null) {
                return ok(project.render());
            } else {
                return ok(project.render());
            }
            
        }
        
        return ok(project.render());
    }
    
    
}


