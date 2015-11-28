package models;

import java.util.Date;
import java.util.List;

import com.avaje.ebean.Model;

/**
 * Created by louis on 15-11-28.
 */
public class Organization extends Model {
    Date timeStamp;
    static List<Project> projects;

    public Organization() {
        this.timeStamp = new Date();
    }

    public static void add(Project proj)
    {
        projects.add(proj);
    }

}