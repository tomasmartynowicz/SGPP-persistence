package sgpp.marty.persistence.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

public abstract class AbstractTest {
	protected void assertNotEmptyList(List list) {
		assertNotNull(list);
		assertFalse(list.isEmpty());
	}
	
	protected void assertEmptyList(List list) {
		assertNotNull(list);
		assertTrue(list.isEmpty());		
	}

	protected void assertListSize(List list, int expectedSize) {
		assertEquals(expectedSize, list.size());
	}
	
	protected void assertContainsElement(List list, Object o) {
		assertNotNull(list);
		assertTrue(list.contains(o));
	}
	
	protected void assertDoesntContainsElement(List list, Object o) {
		if(list != null) {
			assertFalse(list.contains(o));
		}
	}
}
