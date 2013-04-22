package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.format.Formats;
import play.db.ebean.Model;

@Entity
public class Person extends WithHistory {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;
	public String genderCode;
	public Long maritalStatusId;
	public Long titleId;
	public String surname;
	public String firstName;
	public String middleName;
	public String preferredName;
	@Formats.DateTime(pattern="dd/MM/yyyy")
	public Date dob;
	public String phoneHome;
	public String phoneWork;
	public String phoneMobile;
	public String phoneFax;
	public String fax;
	public String email;
	public String emailConfirmFlag;
	public String occupationCode;
	public String positionTitle;
	public Date deceasedDate;
	public String nhi;
	public String activeFlag;
	public String nationalId;
	
	public Person() {

	}

	public Person(Long id, String genderCode, Long maritalStatusId,
			Long titleId, String surname, String firstName, String middleName,
			String preferredName, Date dob, String phoneHome, String phoneWork,
			String phoneMobile, String phoneFax, String fax, String email,
			String emailConfirmFlag, String occupationCode,
			String positionTitle, Date deceasedDate, String nhi,
			String activeFlag, String nationalId) {
		super();
		this.id = id;
		this.genderCode = genderCode;
		this.maritalStatusId = maritalStatusId;
		this.titleId = titleId;
		this.surname = surname;
		this.firstName = firstName;
		this.middleName = middleName;
		this.preferredName = preferredName;
		this.dob = dob;
		this.phoneHome = phoneHome;
		this.phoneWork = phoneWork;
		this.phoneMobile = phoneMobile;
		this.phoneFax = phoneFax;
		this.fax = fax;
		this.email = email;
		this.emailConfirmFlag = emailConfirmFlag;
		this.occupationCode = occupationCode;
		this.positionTitle = positionTitle;
		this.deceasedDate = deceasedDate;
		this.nhi = nhi;
		this.activeFlag = activeFlag;
		this.nationalId = nationalId;
	}

	public static Model.Finder<Long, Person> find = new Model.Finder<Long, Person>(
			Long.class, Person.class);

}
