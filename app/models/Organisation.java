package models;

import java.util.*;
import javax.persistence.*;
import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Organisation extends Model {
    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public String name;

    public String description;

    public String urlOrgnisation;
    public String nomContact;
    public String phoneNumber;
    public String courrielContact;

    // @ManyToOne(cascade = CascadeType.ALL)
    // public List<Adresse> adresses = new ArrayList<Adresse>();

    public long id_admin;

    public String photo; //Base64 string

    @ManyToMany(cascade = CascadeType.ALL)
    public List<CosmicUser> users = new ArrayList<CosmicUser>();

    public static Finder<Long, Organisation> find = new Finder<Long,Organisation>(Organisation.class);

}
