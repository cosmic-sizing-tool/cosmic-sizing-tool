package models;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.avaje.ebean.Model;

public class Project extends Model {
    Date timeStamp;
    UUID uniqueId;
    String name;
    String purposeOfMeasurement;
    List<String> functionalUsers;
    String granularity;
    String levelOfDecomposition;
    enum ProjectType { NEWPROJECT, IMPROVEMENT };
    public Set<FunctionalProcess> functionalProcesses;

    public Project() {
        this.timeStamp = new Date();
        this.uniqueId = UUID.randomUUID();
    }


}
