package models;

import java.util.*;
import javax.persistence.*;
import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class CosmicUser extends Model {

    @Constraints.Required
    @Column(unique = true)
    public String alias;

    @Constraints.Required
    public String password;

    public String name;

    public boolean deleted;
    public boolean disponible;

    @Constraints.Email
    @Constraints.Required
    @Column(unique = true)
    public String email;

    @Formats.DateTime(pattern="yyyy/MM/dd")
    public Date created_at = new Date();

    @Id
    @Constraints.Min(10)
    public Long id;

    public String url;

    public String company;

    public String country;

    public String state;

    public String city;

    public String location;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    public List<Organisation> organisations = new ArrayList<Organisation>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<Certification> certifications = new ArrayList<Certification>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<Email> emails = new ArrayList<Email>();


    public static Finder<Long, CosmicUser> find = new Finder<Long,CosmicUser>(CosmicUser.class);


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}


	public boolean isDeleted() {
		return deleted;
	}


	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}


	public boolean isDisponible() {
		return disponible;
	}


	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getCreated_at() {
		return created_at;
	}


	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}

  public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}

	public List<Organisation> getOrganisations() {
		return organisations;
	}


	public void setOrganisations(List<Organisation> organisations) {
		this.organisations = organisations;
	}


	public List<Certification> getCertifications() {
		return certifications;
	}


	public void setCertifications(List<Certification> certifications) {
		this.certifications = certifications;
	}


	public List<Email> getEmails() {
		return emails;
	}


	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}


}
