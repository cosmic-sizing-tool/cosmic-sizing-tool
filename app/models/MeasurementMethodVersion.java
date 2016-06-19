package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class MeasurementMethodVersion extends Model {

  @Id
  public int id;

  @Column
  int methodNumber;

  @ManyToOne(cascade = CascadeType.ALL)
  public MeasurementMethod measurementMethod;

  @OneToMany(cascade = CascadeType.ALL)
  public List<CosmicUserMeasurementMethodVersion> cosmicUserMeasurementMethodVersions = new ArrayList<CosmicUserMeasurementMethodVersion>();
}
