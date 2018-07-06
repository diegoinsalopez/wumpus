package wumpus;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class KeyboardTest {
	@Test
	public void testActions() {
		Joystick j = new Joystick();
		assertTrue("avanzar".equals(j.checkAction(" avanzar ")));
		assertTrue("girar izquierda".equals(j.checkAction("girar izquierda")));
		assertTrue("girar derecha".equals(j.checkAction(" girar derecha")));
		assertTrue("disparar".equals(j.checkAction("disparar ")));
		assertTrue("error".equals(j.checkAction("avvanzar ")));
		assertFalse("avanzddar".equals(j.checkAction(" avanzar ")));
	}

}
