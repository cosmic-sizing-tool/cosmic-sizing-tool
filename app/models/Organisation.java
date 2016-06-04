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
    public String urlImage;
    public String nomContact;
    public String telContact;
    public String courrielContact;
    public String adresse1;
    public String adresse2;
    public String pays;
    public String etat;
    public String ville;

    public long id_admin;



    @ManyToMany(cascade = CascadeType.ALL)
    public List<CosmicUser> users = new ArrayList<CosmicUser>();





    public static Finder<Long, Organisation> find = new Finder<Long,Organisation>(Organisation.class);

}
