package models;

import static org.junit.Assert.assertEquals;
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

public class ModelsTest extends WithApplication {
	
	@Before
	public void setUp() {
		 start(fakeApplication(inMemoryDatabase(), fakeGlobal()));
		 Ebean.save((Collection<?>) Yaml.load("initial-data.yml"));
	}
	
	@Test
	public void findPerson(){
	    assertEquals("Marla", Person.find.byId(1L).firstName);
	}
	
	@Test
	public void fullTest() {
		assertEquals(2, Person.find.findRowCount());
	}

}
