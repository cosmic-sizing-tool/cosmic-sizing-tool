package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class CosmicUsersCity extends Model {
    @ManyToOne(cascade = CascadeType.ALL)
    public City city;

    @ManyToOne(cascade = CascadeType.ALL)
    public CosmicUser cosmicUser;
}
