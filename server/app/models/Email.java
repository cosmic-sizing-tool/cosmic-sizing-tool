package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Email extends Model {

    @Id
    public Long id;

    @Constraints.Required
    public String addresse;

    @Constraints.Required
    public boolean main;

    @Constraints.Required
    public boolean hidden;

    @ManyToOne(cascade = CascadeType.ALL)
    public User user;

    public static Finder<Long,Email> find = new Finder<Long,Email>(Long.class, Email.class);

}
