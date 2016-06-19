package models;

import javax.persistence.Id;
import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Country extends Model{

    public String name;

    @Id
    public String shortName;

    @OneToMany(cascade = CascadeType.ALL)
    public List<City> cities = new ArrayList<City>();
}
