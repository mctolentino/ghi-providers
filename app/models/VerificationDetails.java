package models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import play.db.ebean.Model;

@Entity
public class VerificationDetails extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    public Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    public Member member;
    public String verifiedFlag;
    public Date verifiedDate;
    public String watchlistFlag;
    public Date watchlistDate;

    public VerificationDetails() {
    }

    public VerificationDetails(Member member, String verifiedFlag, Date verifiedDate, String watchlistFlag, Date watchlistDate) {
        super();
        this.verifiedFlag = verifiedFlag;
        this.verifiedDate = verifiedDate;
        this.watchlistFlag = watchlistFlag;
        this.watchlistDate = watchlistDate;
        this.member = member;
    }

    public static Model.Finder<Long, VerificationDetails> find = new Model.Finder<Long, VerificationDetails>(Long.class, VerificationDetails.class);

}
