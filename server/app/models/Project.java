package models;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.avaje.ebean.Model;

public class Cosmic extends Model {
    public string contact_person;
    public string organisation;
    public string country;
    public string e-mail;
    public string projectID;
    public date dateSubmitted;
    enum rolePerson {analyst_Programmer,customerUser,developmentManager,independentReviewer,iTManager
    ,metricsManager,projectManager,projectOfficeSupport };
    enum software_type {newDevelopment,re-development,enhancement};
    enum projectdescribes {businessApplication,realTimeApplication,mathematicallyIntensiveApplication,infrastructureSoftware};
    
    public boolean programmingLanguage;
    public boolean operatingSystem;
    public boolean integratedDevEnvironment;
    public boolean debugging;
    public boolean database;
    public boolean objectServer;
    public boolean HtmlWebServer;
    public boolean EMailMessageServer;
    public string primaryTechnologyOther;
    enum environmentSoftwareDeveloped {pc,mainFrame,midRange,multiPlatform};
    public string environmentSoftwareDevelopedOther;
    
    
    
    
    public boolean programmingLanguage;
    public boolean programmingLanguage;
    
    
    
    
    
    
    
    
    
    
    enum projectReusable {custom,reusable};
    public int numberOfSprints;
    public int lengthSprint;
    public string storyPoints;
    public boolean teamProcessImprovement;
    enum processStandards {SoftwareCMM,SPICE,TICKIT,CMMI,ISO9002};
    public string 
    public string 
    public string 
    
    Date timeStamp;
    String name;
    List<String> functionalUsers;
    enum ProjectType { NEWPROJECT, IMPROVEMENT };
    public Set<FunctionalProcess> functionalProcesses;

   
    
}
