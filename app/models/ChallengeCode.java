package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import play.db.ebean.Model;

@Entity
public class ChallengeCode extends Model {

    private static final long serialVersionUID = 1L;
    @Id
    public Integer id;
    @OneToOne
    public Member member;
    public String challengeCode;

    public static Finder<Integer, ChallengeCode> find = new Finder<Integer, ChallengeCode>(Integer.class, ChallengeCode.class);

    public static boolean saveChallengeCode(Member member, String challengeCode) {

        ChallengeCode c = ChallengeCode.find.where()
                                            .eq("member", member)
                                            .findUnique();

        if (c == null) {
            ChallengeCode cc = new ChallengeCode();
            cc.member = member;
            cc.challengeCode = challengeCode;
            cc.save();
        } else {
            c.challengeCode = challengeCode;
            c.update();
        }

        return true;
    }

    public static boolean validateChallengeCode(Integer id, String challengeCode) {

        ChallengeCode cc = find.where()
                               .eq("member.id", id)
                               .findUnique();

        if (cc != null && cc.challengeCode.equalsIgnoreCase(challengeCode)) {
            cc.delete();
            return true;
        }

        return false;
    }

}
