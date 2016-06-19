package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class CosmicUserMeasurementMethodVersion extends Model {

  @Id
  public UUID id;

  @ManyToOne(cascade = CascadeType.ALL)
  MeasurementMethodVersion measurementMethodVersion;
}
