package models;

import java.util.*;
import javax.persistence.*;
import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class User extends Model {

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String password;

    @Constraints.Required
    @Column(unique = true)
    public String alias;

    public boolean deleted;
    public boolean disponible;

    @Constraints.Email
    @Constraints.Required
    @Column(unique = true)
    public String email;

    @Formats.DateTime(pattern="yyyy/MM/dd")
    public Date created_at = new Date();

    @Id
    @Constraints.Min(10)
    public Long id;

    public String url;

    public String company;

    public String location;


    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    public List<Organisation> organisations = new ArrayList<Organisation>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<Certification> certifications = new ArrayList<Certification>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<Email> emails = new ArrayList<Email>();


    public static Finder<Long, User> find = new Finder<Long,User>(User.class);
    /*Setters*/
    public void setDeleted(boolean d) { this.deleted = d; }
    public void setDisponible(boolean b) { this.disponible = b;}
    public void setAlias(String a) { this.alias = a;}
    public void setPassword(String p) { this.password = p;}
    public void setName(String n) { this.name = n;}
    public void setUrl(String u) { this.url = u;}
    public void setCompany(String c){ this.company = c;}
    public void setLocation(String l){ this.location = l;}
    public void setId(Long i) { this.id = i;}
    public void setEmail(String e) { this.email = e;}
}
