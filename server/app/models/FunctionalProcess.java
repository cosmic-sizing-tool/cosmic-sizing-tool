package models;

import com.avaje.ebean.Model;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class FunctionalProcess extends Model {
    Date timeStamp;
    UUID uniqueId;
    String documentId;
    String sectionId;
    enum QualityRating { NONE, A, B, C, D, E };
    QualityRating qualityRating;
    String description;
    public Set<DataGroup> dataGroups;

    public FunctionalProcess(String documentId, String sectionId, String description) {
        this.timeStamp = new Date();
        this.uniqueId = UUID.randomUUID();
        this.documentId = documentId;
        this.sectionId = sectionId;
        this.description = description;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public QualityRating getQualityRating() {
        return qualityRating;
    }

    public void setQualityRating(QualityRating qualityRating) {
        this.qualityRating = qualityRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        return uniqueId.hashCode();
    }

}
