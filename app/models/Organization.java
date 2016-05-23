package models;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.avaje.ebean.Model;
import javax.persistence.*;

@Entity
public class Organization extends Model {
    Date timeStamp;
    UUID uniqueId;
    String name;
    static List<Project> projects;

    public Organization(String name) {
        this.timeStamp = new Date();
        this.uniqueId = UUID.randomUUID();
        this.name = name;
    }

    public static void add(Project proj)
    {
        projects.add(proj);
    }

    @Override
    public int hashCode() {
        return uniqueId.hashCode();
    }
    public static Finder<Long, Organization> find = new Finder<Long,Organization>(Organization.class);
}