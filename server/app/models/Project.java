package models;

import java.util.Date;
import java.util.Set;

/**
 * Created by louis on 15-11-28.
 */
public class Project {
    Date timeStamp;
    //Enum type = { newProject, improvement}
    Set<FunctionalProcess> functionalProcesses;
}
