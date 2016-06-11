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
public class City extends Model{

    @Id
    public int idCity;

    public String name;

    public List<CountryDivisionType> divisionType = new ArrayList<CountryDivisionType>();

    // @ManyToOne(cascade = CascadeType.ALL)
    // public List<Adresse> adresses = new ArrayList<Adresse>();
}