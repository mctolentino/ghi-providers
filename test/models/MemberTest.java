package models;

import static org.junit.Assert.*;
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

public class MemberTest extends WithApplication {

    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase(), fakeGlobal()));
        Ebean.save((Collection<?>) Yaml.load("initial-data.yml"));
    }

    @Test
    public void findSearchLatest() {
        List<SearchLog> sl = SearchLog.findLatestSearches();

        for (SearchLog log : sl) {
            System.out.println(log.member.id);
        }

        assertEquals(3, sl.size());

    }

    @Test
    public void addToSearchLatest() {
        Member m = Member.find.byId(4);
        assertNull(m.verificationDetails);
        m.saveSearchAndVerificationDetails();

        assertNotNull(m.log);
        assertNotNull(m.verificationDetails);
        assertNotNull(m.verificationDetails.verifiedFlag);
        assertEquals("N", m.verificationDetails.verifiedFlag);
    }

}
