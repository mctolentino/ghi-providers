package models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeGlobal;
import static play.test.Helpers.inMemoryDatabase;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import play.libs.Yaml;
import play.test.WithApplication;

import com.avaje.ebean.Ebean;

public class ModelsTest extends WithApplication {

    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase(), fakeGlobal()));
        Ebean.save((Collection<?>) Yaml.load("initial-data.yml"));
    }

    @Test
    public void findPerson() {
        assertEquals("Marla", Person.find.byId(1L).firstName);
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
        assertEquals("Singer", m.person.surname);
        assertEquals("Marla", m.person.firstName);

        Member m2 = Member.find.fetch("person")
                               .where()
                               .eq("id", 002)
                               .findUnique();
        assertEquals("Durden", m2.person.surname);
        assertThat("Marla", not(equalTo(m2.person.firstName)));
    }

    @Test
    public void countMembers() {
        assertEquals(5, Member.find.findRowCount());
    }

}
