package models;

import javax.persistence.Id;
import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

/**
 * Created by ethienneroy on 2016-06-11.
 */

@Entity
public class Adresse extends Model {

    @Id
    public int id;
    public String postalCode;
    public String street;
    public String appartment;

    @ManyToOne
    public List<City> city = new ArrayList<City>();

    @ManyToOne
    public List<Organisation> orgs = new ArrayList<Organisation>();

}
