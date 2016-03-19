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
        
        return ok(project.render(null));
    }
    
    
}


