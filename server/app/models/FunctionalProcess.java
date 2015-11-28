package models;

import java.util.Date;
import java.util.Set;

/**
 * Created by louis on 15-11-28.
 */
public class FunctionalProcess {
    Date timeStamp;
    int documentId;
    int sectionId;
    int qualityRating;
    String description;

    Set<DataGroup> dataGroups;
}
