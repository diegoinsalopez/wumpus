package wumpus;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTesst {
	
	@Test
	void test() {
		Board board = new Board(10, 1);
		board.hashCode();
	}

}
