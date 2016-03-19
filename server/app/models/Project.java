package models;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.avaje.ebean.Model;

public class Project extends Model {
    public static Finder<Long,Project> find = new Finder<Long,Project>(Project.class);
    
    public String contact_person;
    public String organisation;
    public String country;
    public String email;
    public String projectID;
    public Date dateSubmitted;
    enum rolePerson {analyst_Programmer,customerUser,developmentManager,independentReviewer,iTManager
    ,metricsManager,projectManager,projectOfficeSupport };
    enum software_type {newDevelopment,redevelopment,enhancement};
    enum projectdescribes {businessApplication,realTimeApplication,mathematicallyIntensiveApplication,infrastructureSoftware};
    
    public boolean programmingLanguage;
    public boolean operatingSystem;
    public boolean integratedDevEnvironment;
    public boolean debugging;
    public boolean database;
    public boolean objectServer;
    public boolean HtmlWebServer;
    public boolean EMailMessageServer;
    public String primaryTechnologyOther;
    enum environmentSoftwareDeveloped {pc,mainFrame,midRange,multiPlatform};
    public String environmentSoftwareDevelopedOther;
    public boolean sameImplementationPlatform ;
    enum Platform{mobile,pc,midRange,mainframe,multiPlatform};
    public Platform primaryImplementationPlatform;
    
    
    
    
    
    enum projectReusable {custom,reusable};
    public int numberOfSprints;
    public int lengthSprint;
    public String storyPoints;
    public boolean teamProcessImprovement;
    enum processStandards {SoftwareCMM,SPICE,TICKIT,CMMI,ISO9002};
    // public string 
    // public string 
    // public string 
    
    Date timeStamp;
    String name;
    List<String> functionalUsers;
    enum ProjectType { NEWPROJECT, IMPROVEMENT };
    public Set<FunctionalProcess> functionalProcesses;

   
    
}
