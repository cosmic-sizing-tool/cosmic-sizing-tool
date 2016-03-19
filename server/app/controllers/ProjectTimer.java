package controllers;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Organisation;
import models.Project;
import models.Timer;
import models.User;
//import models.User;
import play.*;
import play.db.ebean.Transactional;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

import com.avaje.ebean.*;


import java.util.List;
import java.util.Map;

@Transactional
public class ProjectTimer extends Controller {

    public Result getTimers(Long organizationId, Long projectId) {


        List<Timer> timers = Timer.getAllTimersForProject(organizationId, projectId);

        ObjectNode response = Json.newObject();
        response.set("projectTime", LongNode.valueOf(Timer.sumTimers(timers)));


        ArrayNode jsonTimers = new ArrayNode(new JsonNodeFactory(false));


        timers.addAll(timers);
        response.set("timers", jsonTimers);


        return ok(response.toString());
    }

    public Result getCurrentlyRunningTimer(Long organizationId, Long projectId) {
        Timer timer = Timer.getRunningTimer(organizationId, projectId);

        if (timer == null) {
            return noContent();
        }
        else {
            return ok(Json.toJson(timer));
        }
    }

    public Result timerCtrl(Long organizationId, Long projectId) {

        Map<String, String[]> queryParams = Controller.request().queryString();


        if (queryParams.get("action").length == 0) {
            return internalServerError("Missing query param 'action'");
        }

        switch (queryParams.get("action")[0]) {

            case "start":

                ObjectMapper mapper = new ObjectMapper();

                    Timer timer = Timer.startTimer(organizationId, projectId);
                    System.err.println(timer);
                    return ok(Json.toJson(timer));


            case "stop":

                Timer stoppedTimer = Timer.stopTimer(organizationId, projectId);

                if (stoppedTimer == null) {
                    return badRequest("{\"error\": \"no chrono was started\"}");
                }
                return ok(Json.toJson(stoppedTimer));

            default:
                return internalServerError("Action not understood. Must either be start or stop.");

        }
    }


}



