package models;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.avaje.ebean.Model;

public class Project extends Model {
    public static Finder<Long,Project> find = new Finder<Long,Project>(Project.class);
    //1.
    public String contact_person;
    public String organisation;
    public String country;
    public String email;
    
    //2.
    public String projectID;
    public Date dateSubmitted;
    
    //3.
    enum RolePerson{analyst_Programmer,customerUser,developmentManager,independentReviewer,iTManager
    ,metricsManager,projectManager,projectOfficeSupport };
    public RolePerson rolePersonSubmittedProject;
    
    //5.
    enum SoftwareType{newDevelopment,redevelopment,enhancement};
    public SoftwareType typeSoftwareProject;
    
    //6.
    enum ProjectDescribes {businessApplication,realTimeApplication,mathematicallyIntensiveApplication,infrastructureSoftware};
    public ProjectDescribes projectDescribesDomain;
    
    //58.
    public boolean programmingLanguage;
    public boolean operatingSystem;
    public boolean integratedDevEnvironment;
    public boolean debugging;
    public boolean database;
    public boolean objectServer;
    public boolean HtmlWebServer;
    public boolean EMailMessageServer;
    public String primaryTechnologyOther;
    
    //60.
    enum EnvironmentSoftwareDeveloped {pc,mainFrame,midRange,multiPlatform};
    public String environmentSoftwareDevelopedOther;
    
    //63.
    public boolean sameImplementationPlatform ;
    
    //63.
    enum Platform{mobile,pc,midRange,mainframe,multiPlatform};
    public Platform primaryImplementationPlatform;
    
    //63.
    enum MblDvcEmbd{Automotive,Aviation,DomesticAppliance,MachineTool,MobilePhone,PDA,GamesDevice};
    public MblDvcEmbd mobileDeviceEmbedded;
    
    /*
    enum projectReusable {custom,reusable};
    public int numberOfSprints;
    public int lengthSprint;
    public String storyPoints;
    public boolean teamProcessImprovement;
    enum processStandards {SoftwareCMM,SPICE,TICKIT,CMMI,ISO9002};
 
    Date timeStamp;
    String name;
    List<String> functionalUsers;
    enum ProjectType { NEWPROJECT, IMPROVEMENT };
    public Set<FunctionalProcess> functionalProcesses;
    */
   
    
}
