package models;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.avaje.ebean.Model;


/**
 * Created by louis on 15-11-28.
 */
public class Project extends Model {
    Date timeStamp;
    String name;
    String purposeOfMeasurement;
    List<String> functionalUsers;
    String granularity;
    String levelOfDecomposition;
    enum ProjectType { NEWPROJECT, IMPROVEMENT };
    public Set<FunctionalProcess> functionalProcesses;

    public Project() {
        this.timeStamp = new Date();
    }
}
