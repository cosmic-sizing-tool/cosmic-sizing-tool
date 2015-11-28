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
    public Set<DataGroup> dataGroups;

    public FunctionalProcess(int documentId, int sectionId, String description) {
        this.timeStamp = new Date();
        this.documentId = documentId;
        this.sectionId = sectionId;
        this.description = description;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public int getDocumentId() {
        return documentId;
    }

    public int getSectionId() {
        return sectionId;
    }

    public int getQualityRating() {
        return qualityRating;
    }

    public void setQualityRating(int qualityRating) {
        this.qualityRating = qualityRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
