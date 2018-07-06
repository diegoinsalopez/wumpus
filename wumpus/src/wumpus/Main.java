package wumpus;

public class Main {

	public static void main(String[] args) {
		Game game = new Game(9, 9, 2);
		game.gameLoop();
	}

}
