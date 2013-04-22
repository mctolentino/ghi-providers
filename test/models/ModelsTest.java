package models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeGlobal;
import static play.test.Helpers.inMemoryDatabase;

import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import play.libs.Yaml;
import play.test.WithApplication;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;

public class ModelsTest extends WithApplication {

    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase(), fakeGlobal()));
        Ebean.save((Collection<?>) Yaml.load("initial-data.yml"));
    }

    @Test
    public void findPerson() {
        assertEquals("Scott", Person.find.byId(1L).firstName);
    }

    @Test
    public void testQuestions() {
        Question q = Question.find.byId(004L);
        assertEquals("Who's your first crush?", q.question);
    }

    @Test
    public void countQuestions() {
        assertEquals(4, Question.find.findRowCount());
    }

    @Test
    public void findMember() {
        Member m = Member.find.fetch("person")
                              .where()
                              .eq("id", 001)
                              .findUnique();
        assertEquals("Darrow", m.person.surname);
        assertEquals("Scott", m.person.firstName);

        Member m2 = Member.find.fetch("person")
                               .where()
                               .eq("id", 002)
                               .findUnique();
        assertEquals("Agbada", m2.person.surname);
        assertThat("Marla", not(equalTo(m2.person.firstName)));
    }

    @Test
    public void countMembers() {
        assertEquals(5, Member.find.findRowCount());
    }
    
    @Test
    public void saveExistingChallengeCode(){
        Member member = Ebean.find(Member.class).fetch("person").where().eq("id", 4).findUnique();
        System.out.println("FN " + member.person.firstName);
        
        ChallengeCode test = new ChallengeCode();
        test.member = member;
        test.challengeCode = "XXX";
        test.save();
        
        String sql 
         = " select id, member_id, challenge_code "
         + " from challenge_code cc ";
        
        RawSql rawSql = 
                RawSqlBuilder.parse(sql)
                 // map result columns to bean properties
                 .columnMapping("id", "cc.id")
                 .columnMapping("member_id", "cc.member.id")
                 .columnMapping("challenge_code", "cc.challenge_code")
                .create();
        
        Query<ChallengeCode> query = Ebean.find(ChallengeCode.class);
        query.setRawSql(rawSql);
        
        
        for(ChallengeCode cc : query.findList()){
            System.out.println("CC ID: " + cc.id);
            System.out.println("CC ID: " + cc.challengeCode);
            System.out.println("CC ID: " + cc.member.id);   
        }

    }
    
    //@Test
    public void saveNewChallengeCode(){
        Member member = Member.find.byId(4);
        ChallengeCode cc = ChallengeCode.find.where().eq("member", member).findUnique();
        
        if(cc == null) {
            cc = new ChallengeCode();
            cc.challengeCode = "000000";
        }else{
            cc.challengeCode = "000001";
        }
        
        assertEquals("000000",cc.challengeCode);
    }

}
