/** Team Epique
    2016/06/11 */

package models.pattern;

import java.util.*;

import com.avaje.ebean.Model;

public class FunctionalProcess extends Model {
    private Long id;
    private String name;
    
    private Set<DataGroup> dataGroupSet;

    public FunctionalProcess() {
        this.dataGroupSet = new HashSet<>();
    }

    public Set<DataGroup> getDataGroupSet() {
        return dataGroupSet;
    }

    public void setDataGroups(Set<DataGroup> dataGroupSet) {
        this.dataGroupSet = dataGroupSet;
    }
    
    public int getCFPSize() {
        for(FunctionalProcess funcProcess : dataGroups) {
            funcProcessSet
        }
    }
}
