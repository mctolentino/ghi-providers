package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Member extends WithHistory {

	private static final long serialVersionUID = 1L;
	@Id
	public Long id;
	@OneToOne
	public Person person = new Person();
	public Long payeeId;
	public Long customerAccountId;
	public Long providerId;
	public Boolean emailCorrespondenceFlag = Boolean.FALSE;
	public Boolean smokerFlag;

	@OneToMany
	public List<SecurityQuestion> securityQuestions = new ArrayList<SecurityQuestion>();

	public Member(Long id, Person person, Long payeeId, Long customerAccountId,
			Long providerId, Boolean emailCorrespondenceFlag,
			Boolean smokerFlag, List<SecurityQuestion> securityQuestions) {
		super();
		this.id = id;
		this.person = person;
		this.payeeId = payeeId;
		this.customerAccountId = customerAccountId;
		this.providerId = providerId;
		this.emailCorrespondenceFlag = emailCorrespondenceFlag;
		this.smokerFlag = smokerFlag;
		this.securityQuestions = securityQuestions;
	}

}
