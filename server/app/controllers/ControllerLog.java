package controllers;

import play.*;
import play.mvc.*;
import views.html.*;
import java.util.*;
import models.*;

public class ControllerLog extends Controller {
    private ModelLog m;
    private ViewLog v;

    // Default constructor
    public Log () {
        this->m = new ModelLog();
    }

    // method to show all logs (ie, general log page)
    public showAllLogs(){
        // get logs from the model
        Logs logs = this->m.getLogs();

        //send logs to the view
        this->v.setLogs(logs);
    }

    // method to show logs from a filter
    public showLogsFromFilter() {
        
    }
}
