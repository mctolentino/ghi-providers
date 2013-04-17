package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.ebean.Model;

@Entity
public class Member extends WithHistory {

    private static final long serialVersionUID = 1L;
    @Id
    public Integer id;
    @OneToOne
    public Person person;
    public Long payeeId;
    public Long customerAccountId;
    public Long providerId;
    public String emailCorrespondenceFlag;
    public String smokerFlag;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<SecurityQuestion> securityQuestions;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public VerificationDetails verificationDetails;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public SearchLog log;

    public static Model.Finder<Integer, Member> find = new Model.Finder<Integer, Member>(Integer.class, Member.class);

    public static List<Member> allMembers() {
        return Member.find.all();
    }

    public static Member searchMemberById(Integer id) {
        return Member.find.byId(id);
    }

    public void saveNewVerificationDetails() {
        this.verificationDetails = new VerificationDetails(this, "N", null, "N", null);
        this.update();
    }

    public void saveNewSecurityQuestion() {
        this.securityQuestions = new ArrayList<SecurityQuestion>();
        this.securityQuestions.add(new SecurityQuestion(this, "No Security Question", " "));
        this.update();
    }

    public void saveSearchAndVerificationDetails() {

        SearchLog log = SearchLog.find.fetch("member")
                                      .where()
                                      .eq("member", this)
                                      .findUnique();

        if (log == null) {
            this.log = new SearchLog();
            this.log.searchDate = new Date();
            this.update();
        } else {
            this.log = log;
            this.log.searchDate = new Date();
            this.update();
        }

        if (this.verificationDetails == null || this.verificationDetails.member == null) {
            this.saveNewVerificationDetails();
        }

        if (this.securityQuestions == null || this.securityQuestions.size() == 0) {
            this.saveNewSecurityQuestion();
        }
    }
}
