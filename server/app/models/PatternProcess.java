package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

import util.*;

@Entity
public class PatternProcess extends Model implements JsonSerializable{

    @Id
    private Long id;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(length = 1, nullable = false)
    private String qualityRating;

    /* foreign key vers Pattern */
    @Column(nullable = false)
    private long process;

    @Column(nullable = false)
    private int fAdd;

    @Column(nullable = false)
    private int fModify;

    @Column(nullable = false)
    private int fDelete;

    @Column(nullable = false)
    private int fUnknown;

    @Column(nullable = false)
    private int fTemplate;

    public PatternProcess(long parentId) {
        id=null;
        qualityRating = "";
        name="";
        process=parentId;
        fAdd=0;
        fModify=0;
        fDelete=0;
        fUnknown=0;
        fTemplate=0;
    }

    public PatternProcess(long parentId, boolean isTemplate) {
        id=null;
        qualityRating = "";
        name="";
        process=parentId;
        fAdd=0;
        fModify=0;
        fDelete=0;
        fUnknown=0;
        fTemplate = isTemplate ? 1 : 0;
    }

    @Override
    public String toJson()
    {
        JsonBuilder json = new JsonBuilder();
        json.add("id", id);
        json.add("name", name);
        json.add("layer_id", process);
        json.add("add", fAdd);
        json.add("modify", fModify);
        json.add("delete", fDelete);
        json.add("unknown", fUnknown);
        json.add("quality", getQualityRating());
        json.add("data_groups", getDataGroup());

        return json.toString();
    }

    @Override
    public void delete()
    {
        List<DataGroup> dgs = getDataGroup();
        for(DataGroup dg : dgs) dg.delete();

        super.delete();
    }

    public long getId(){return id;}
    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public boolean getAdd(){ return fAdd == 1; }
    public boolean getDelete(){ return fDelete == 1; }
    public boolean getModify(){ return fModify == 1; }
    public boolean getUnknown(){ return fUnknown == 1; }

    public void setAdd(boolean val){ fAdd = val ? 1 : 0; }
    public void setDelete(boolean val){ fDelete = val ? 1 : 0; }
    public void setModify(boolean val){ fModify = val ? 1 : 0; }
    public void setUnknown(boolean val){ fUnknown = val ? 1 : 0; }

    public void setQualityRating(String qr){
        if(qr.length() == 0) throw new RuntimeException("Quality rating cannot be empty");
        if(qr.length() > 1) throw new RuntimeException("Quality rating must be a single letter between 'a' and 'e'");
        switch(qr.charAt(0))
        {
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
                qualityRating = qr;
                break;
            default:
                throw new RuntimeException("Quality rating must be a single letter between 'a' and 'e'");
        }
    }

    public String getQualityRating(){ return qualityRating; }

    public List<DataGroup> getDataGroup()
    {
        return DataGroup.find.where().eq("process", id).findList();
    }

    public static Finder<Long,PatternProcess> find = new Finder<Long,PatternProcess>(Long.class, PatternProcess.class);
}
