package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class MeasurementMethod extends Model {

  String name;

  //(mappedBy = "method", cascade = CascadeType.ALL)
  // @OneToMany(cascade = CascadeType.ALL)
  // public List<MeasurementMethodVersion> measurementMethodVersions = new ArrayList<MeasurementMethodVersion>();
}
