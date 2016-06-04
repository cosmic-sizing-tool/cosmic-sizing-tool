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

    @Constraints.Email
    @Constraints.Required
    @Column(unique = true)
    public String addresse;

    @Constraints.Required
    public boolean main;

    @Constraints.Required
    public boolean hidden;

    public boolean deleted;

    @ManyToOne(cascade = CascadeType.ALL)
    public CosmicUser user;

    public static Finder<Long,Email> find = new Finder<Long,Email>(Long.class, Email.class);

    public void setDeleted(boolean b) { this.deleted = b;}
    public void setHidden(boolean b) { this.deleted = b;}
    public void setMain(boolean b) { this.deleted = b;}
}
