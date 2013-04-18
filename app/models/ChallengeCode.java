package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class ChallengeCode extends Model {

    private static final long serialVersionUID = 1L;
    @Id
    public Integer id;
    public Member member;
    public String challengeCode;

    public static Finder<Integer, ChallengeCode> find = new Finder<Integer, ChallengeCode>(Integer.class, ChallengeCode.class);

    public static boolean saveChallengeCode(Member member) {

        //ChallengeCode test = find.where().eq("member.id", member.id).findUnique();
        
        ChallengeCode cc = new ChallengeCode();
        cc.member = member;
        cc.challengeCode = "0000";
        cc.save();    
        
//        if(test == null ){
//            ChallengeCode cc = new ChallengeCode();
//            cc.member = member;
//            cc.challengeCode = "0000";
//            cc.save();    
//        }else{
//            test.challengeCode = "0000";
//            test.update();
//        }
        return true;
    }
}
