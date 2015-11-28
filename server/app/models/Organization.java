package models;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.avaje.ebean.Model;

public class Organization extends Model {
    Date timeStamp;
    UUID uniqueId;
    String name;
    static List<Project> projects;

    public Organization(Date timeStamp, String name) {
        this.timeStamp = timeStamp;
        this.uniqueId = UUID.randomUUID();
        this.name = name;
    }

    public static void add(Project proj)
    {
        projects.add(proj);
    }

}