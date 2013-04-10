package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PolicyMember extends WithHistory {

	
	private static final long serialVersionUID = 1L;
	@Id
	public Long id;
	public String relationshipTypeCode;
	public String premiumTypeCode;
	public Policy policy;
	public Member member;
	public Date startDate;
	public Date endDate;
	public Long ageApplied;
	public Boolean suspensionFlag;

	
}
