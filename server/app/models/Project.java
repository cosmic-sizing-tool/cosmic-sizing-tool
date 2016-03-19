package models;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;

import com.avaje.ebean.Model;

@Entity
public class Project extends Model {
    
    public static Finder<Long,Project> find = new Finder<Long,Project>(Project.class);
    @Id
    public Long id;
    
    static private Long nextId = 0L;
    
    static Long nextId(){
        return ++nextId;
    }
    //1.
    public String contact_person;
    public String organisation;
    public String country;
    public String email;
    
    //2.
    public String projectID;
    public Date dateSubmitted;
    
    //3.
    enum RolePerson{analystProgrammer,customerUser,developmentManager,independentReviewer,iTManager
    ,metricsManager,projectManager,projectOfficeSupport };
    public RolePerson rolePersonSubmittedProject;
    public String rolePersonOther;
    
    //5.
    enum SoftwareType{newDevelopment,redevelopment,enhancement};
    public SoftwareType typeSoftwareProject;
    public String softwareTypeOther;
    
    
    //6.
    enum ProjectDescribes {businessApplication,realTimeApplication,mathematicallyIntensiveApplication,infrastructureSoftware};
    public ProjectDescribes projectDescribesDomain;
    
    
    //7.souhaitable
    enum PrjtReusable {custom,reusable};
    public PrjtReusable projectReusable ;
    
    //9.important
    public int numberOfSprints;
    public int lengthSprint;
    public String storyPoints;
    
    //12.souhaitable
    public boolean teamProcessImprovement;
    enum PrcsStndrs {softwareCMM,sPICE,tICKIT,cmmi,iso9002};
    public PrcsStndrs processStandards;
    public String prcsStndrsOther;
    
    
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
    public EnvironmentSoftwareDeveloped environmentSoftwareDevelopedOther;
    public String envrnmntSftreDvlopedOther;
    
    //63.
    public boolean sameImplementationPlatform ;
    
    //63.
    enum Platform{mobile,pc,midRange,mainframe,multiPlatform};
    public Platform primaryImplementationPlatform;
    public String primImplPlatformOther;
    
    //63.
    enum MblDvcEmbd{Automotive,Aviation,DomesticAppliance,MachineTool,MobilePhone,PDA,GamesDevice};
    public MblDvcEmbd mobileDeviceEmbedded;
    public String mblDvcEmbdOther;
    
    
    
    
    
    //64.
    public String developmentCountry;
    public String implementedCountry;
    
    //70.
    public int peoplePlan;
    public int peopleSpecify;
    public int peopleDesign;
    public int peopleBuild;
    public int peopleTest;
    public int peopleImpl;
    
    public int effortPlan;
    public int effortSpecify;
    public int effortDesign;
    public int effortBuild;
    public int effortTest;
    public int effortImpl;
    
    public int  peopleSummary;
    public int  effortSummary;
    
    //71.
    enum IndstrySftwre{aerospace,chemicals,computers,defence,electronics,government,media,oil,realEstate,wholesale,
    agriculture,communications,construction,education,food,insurance,medical,professional,telecommunications,banking,
    community,consumer,electricity,finance,manufacturing,mining,recreation,transport};

    public IndstrySftwre industrySoftware;
    public String industrySoftwareOther;
    
    
    //75.important
    
    public int CustomerPlan;
    public int CustomerSpecify;
    public int CustomerDesign;
    public int CustomerBuild;
    public int CustomerTest;
    public int CustomerImpl;
    
    public int UserPlan;
    public int UserSpecify;
    public int UserDesign;
    public int UserBuild;
    public int UserTest;
    public int UserImpl;
    
    public int  CustomerSummary;
    public int  UserSummary;
    
    
    
    //78.
    enum PrcdrDvlpmntTem{NoTimesheets,RecordedOnly,RecordedHours,RecordedThe};
    public PrcdrDvlpmntTem procedureDevelopmentTeam ;
    public String prcdrDvlpmntTemOther;
    
    
    //81.
    public Boolean HasAllTheWorkDone;
    
    //82.
    enum PrevQuestion{lessThan5,fRecordedEffort,unable}
    public PrevQuestion prvqst;
    public String prevQuestionOther;
    
    
    //85.
    enum RatetheQualityWork{poor,adequate,good,excellent}
    public RatetheQualityWork rtqltwork;
    
    
    
    //86.
    public String assignAboveQuality;
    
    
    
    //87.
    enum BusinessApplication{Catalogue,Customer,Document,Financial,Logistic,Online,Stock,Workflow
    ,Customerbilling,DataWarehouse,Electronic,Jobcase,Management,Reservation,Trading
    };
    public BusinessApplication bsnisApp;
    
    enum RealTimeApplication{Automatic,Command,RobotControl,TransportationControl,Embedded,Complex,Telecom};
    public RealTimeApplication reltmeapp;
    
   
    enum MathematicallyIntensiveApplication{threeDmodelling,Imagevideo,Scientific,Geographic,Mathematical,Statistical};
    public MathematicallyIntensiveApplication matintessapp;
    
    
    enum InfrastructureSoftware{Dataordatabase,GraphicsPublishing,Personal,Device,Operating,Software};
    public InfrastructureSoftware infrsoft ;
    
    
    
    public boolean MinorComponent;
    
    
    
    //123.
    public Date dateSoftwareOperation;
    
    //124.
    public int totalProjectElapsedDuration;
    
    //125.
    
    public int timeTotalInactivity;
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
  
   
    
}
