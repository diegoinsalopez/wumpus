package wumpus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.Arrays;

import org.junit.Test;

public class GameTest {
	
	@Test
	public void testWalls() {
		Game g = new Game(5,0,0);
		g.move("avanzar");
		g.move("avanzar");
		g.move("avanzar");
		g.move("avanzar");
		assertEquals("Test pared", "Detectada pared", g.move("avanzar"));
	}
	
	@Test
	public void testShoot() {		
		Game g = new Game(5,0,0);
		assertTrue(g.shoot().equals("No te quedan flechas"));
		
		Board b = new Board(5, new Wumpus(new Point(4,4)),new Gold(new Point(4,3)), Arrays.asList());
		g = new Game(99,b);
		assertEquals("Test caza wumpus","Se escucha grito!!!",g.shoot());
		g.hashCode();
	}
	
	@Test
	public void testHolesDeath() {		
		Board b = new Board(5, new Wumpus(new Point(4,4)),new Gold(new Point(4,3)), Arrays.asList(new WaterHole(new Point(4,1))));
		Game g = new Game(99,b);
		assertEquals("Test muerte por caida","Has topado con un pozo y has muerto", g.checkActualPostion(new Point(4,1)));
	}	
	
	@Test
	public void testHolesSense() {		
		Board b = new Board(5, new Wumpus(new Point(4,4)),new Gold(new Point(4,3)), Arrays.asList(new WaterHole(new Point(4,1))));
		Game g = new Game(99,b);
		assertEquals("Test brisa detectada","Brisa detectada", g.checkSenses(new Point(4,1)));
	}
	
	@Test
	public void testGoldFound() {		
		Board b = new Board(5, new Wumpus(new Point(4,4)),new Gold(new Point(4,3)), Arrays.asList(new WaterHole(new Point(4,1))));
		Game g = new Game(99,b);
		assertEquals("Test oro encontrado","Has encontrado el oro", g.checkActualPostion(new Point(4,3)));
	}	
	
	@Test
	public void testGoldSense() {		
		Board b = new Board(5, new Wumpus(new Point(4,4)),new Gold(new Point(4,3)), Arrays.asList(new WaterHole(new Point(4,2))));
		Game g = new Game(99,b);
		assertEquals("Test percibir oro","Brillo detectado", g.checkSenses(new Point(4,3)));
	}
	
	@Test
	public void testWumpusDeath() {		
		Board b = new Board(5, new Wumpus(new Point(4,4)),new Gold(new Point(4,3)), Arrays.asList(new WaterHole(new Point(4,1))));
		Game g = new Game(99,b);
		assertEquals("Test muerte por wumpus","Has topado con el Wumpus y has muerto", g.checkActualPostion(new Point(4,4)));
	}	
	
	@Test
	public void testWumpusSense() {		
		Board b = new Board(5, new Wumpus(new Point(4,4)),new Gold(new Point(4,3)), Arrays.asList(new WaterHole(new Point(4,2))));
		Game g = new Game(99,b);
		assertEquals("Test hedor detectado","Hedor detectado", g.checkSenses(new Point(4,4)));
	}
	
	@Test
	public void testMovimientos() {		
		Board b = new Board(5, new Wumpus(new Point(4,4)),new Gold(new Point(4,2)), Arrays.asList());
		Game g = new Game(99,b);
		assertEquals("Test rotacion Norte", "Miro hacia NORTE", g.move("girar izquierda"));
		assertEquals("Test rotacion Oeste", "Miro hacia OESTE", g.move("girar izquierda"));
		assertEquals("Test rotacion Sur", "Miro hacia SUR", g.move("girar izquierda"));
		
		g = new Game(99,b);
		assertEquals("Test rotacion Sur", "Miro hacia SUR", g.move("girar derecha"));
		assertEquals("Test rotacion Oeste", "Miro hacia OESTE", g.move("girar derecha"));
		assertEquals("Test rotacion Norte", "Miro hacia NORTE", g.move("girar derecha"));
	}
}
