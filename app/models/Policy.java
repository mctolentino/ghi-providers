package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Policy extends WithHistory{

	public static final long serialVersionUID = 1L;
	
	public Long id;
	public Long policyStatusCode;
	public Long billingCycleId;
	public String policyNumber;
	public Date startDate;
	public Date endDate;
	public Date reinstatementDate;
	public Boolean inRenewalFlag = new Boolean(false);
	public Date lastRenewalDate;
	public Date nextRenewalDate;
	public Date applicationReceivedDate;
	public Date underwrittenDate;
	public String previousPolicyNumber;
	public Boolean existingConditionsFlag = new Boolean(false);
	public Boolean congenitalConditionsFlag = new Boolean(false);
	public Long sumInsuredAmount;
	public Long sortOrder;
	public Boolean activeFlag;
	public List<PolicyMember> policyMembers = new ArrayList<PolicyMember>(0);
	public String arrearsHoldReasonCode;
	public Date arrearsHoldFrom;
	public Date arrearsHoldTo;
	public Long currentArrearsLevel;
	
}
