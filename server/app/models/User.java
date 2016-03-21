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
    Date created_at = new Date();
    
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

}
