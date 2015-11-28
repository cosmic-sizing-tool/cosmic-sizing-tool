package models;

import java.util.*;
import javax.persistence.*;
import java.util.ArrayList;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

public class ModelGlossary extends Model{
    private String language;
    public final static String pathGlossary = System.getProperty("user.dir")+"/"+"Glossary.txt";
    ArrayList gloss = new ArrayList();

	public String ReadFile (){
		String chaine="";
		String fichier ="Glossary.txt";
		
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				System.out.println(ligne);
				chaine+=ligne+"\n";
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	return chaine;
	}

    void setGlossary(String language) {

        this.language = language;
    }

	
	
    public HashMap<String, String> getGlossary(String language) {
		String chaine = ReadFile();
		while (fichier.hasNextLigne()){
			
		}
        return new HashMap();
    }

    public String getLang() {
        return language;
    }

    public HashMap<String, String> getGlossaryWithFilters(String language, String filter1, String filter2, String filter3, boolean andSlashOr) {
        return new HashMap();
    }
}
