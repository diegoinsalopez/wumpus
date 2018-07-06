package wumpus;

import java.awt.Point;

public class Game {
    private Point playerPosition;
    private int arrows;
    private String compass;
    private Board board;
    private boolean gameStatus = true;
    private Joystick joystick;
    
    Game(int size, int arrows, int waterHoles){
        this.arrows = arrows;
        this.board = new Board(size, waterHoles);
        this.playerPosition = new Point(this.board.getSize(), 0);
        this.compass = "ESTE";
        this.joystick = new Joystick();
    }
    
    Game (int arrows, Board board){
        this.arrows = arrows;
        this.playerPosition = new Point(board.getSize(), 0);
        this.board = board;
        this.compass = "ESTE";
        this.joystick = new Joystick();
    }
    
    public String move(String action){
    	if (action.equals("avanzar")) {
    		if (this.compass == "NORTE") {
    			if (this.playerPosition.x - 1 < 0) return "Detectada pared";
    			this.playerPosition.x--;
    		}
    		if (this.compass == "ESTE") {
    			if (this.playerPosition.y + 1 > this.board.getSize()) return "Detectada pared";
    			this.playerPosition.y++;
    		}
    		if (this.compass == "SUR") {
    			if (this.playerPosition.x + 1 > this.board.getSize()) return "Detectada pared";
    			this.playerPosition.x++;
    		}
    		if (this.compass == "OESTE") {
    			if (this.playerPosition.y - 1 < 0) return "Detectada pared";
    			this.playerPosition.y--;
    		}
    		return "Avanzo";
    	}
    	if (action.equals("disparar")) {
    		return this.shoot();
    	}
    	if (action.equals("girar izquierda")) {
    		if (this.compass.equals("NORTE")) {
    			this.compass = "OESTE";
    			return "Miro hacia " + this.compass;
    		}
    		if (this.compass.equals("ESTE")) {
    			this.compass = "NORTE";
    			return "Miro hacia " + this.compass;
    		}
    		if (this.compass.equals("SUR")) {
    			this.compass = "ESTE";
    			return "Miro hacia " + this.compass;
    		}
    		if (this.compass.equals("OESTE")) {
    			this.compass = "SUR";
    			return "Miro hacia " + this.compass;
    		}
     	}
    	if (action.equals("girar derecha")) {
    		if (this.compass.equals("NORTE")) {
    			this.compass = "ESTE";
    			return "Miro hacia " + this.compass;
    		}
    		if (this.compass.equals("ESTE")) {
    			this.compass = "SUR";
    			return "Miro hacia " + this.compass;
    		}
    		if (this.compass.equals("SUR")) {
    			this.compass = "OESTE";
    			return "Miro hacia " + this.compass;
    		}
    		if (this.compass.equals("OESTE")) {
    			this.compass = "NORTE";
    			return "Miro hacia " + this.compass;
    		}
    	}
    	return "";
    }
    
    public void gameLoop(){
    	this.joystick.writeLine("Empiezo el juego, estas mirando al ESTE");
    	
    	while (this.gameStatus) {    	
    		String action = this.joystick.actionLine();
    		if (action.equals("error")) {
    			this.joystick.writeLine("No te entiendo :_( ");
    		} else {
    			this.joystick.writeLine(this.move(action));
    			this.joystick.writeLine(this.checkActualPostion(this.playerPosition));
    			this.joystick.writeLine(this.checkSenses(new Point(this.playerPosition.x-1, this.playerPosition.y)));
    			this.joystick.writeLine(this.checkSenses(new Point(this.playerPosition.x+1, this.playerPosition.y)));
    			this.joystick.writeLine(this.checkSenses(new Point(this.playerPosition.x, this.playerPosition.y-1)));
    			this.joystick.writeLine(this.checkSenses(new Point(this.playerPosition.x, this.playerPosition.y+1)));
    		}
    	}
    	
    	this.joystick.writeLine("Gracias por jugar !!!");
    }
    
    public String shoot(){
    	if (this.arrows == 0) return "No te quedan flechas";
    	
        this.arrows--;
        
        if (this.compass.equals("NORTE")){
        	if (this.playerPosition.x > this.board.getWumpus().getPosition().x && 
        		this.playerPosition.y == this.board.getWumpus().getPosition().y) {
        		this.board.getWumpus().setAlive(false);
        		return "Se escucha grito!!!";
        	}
        }
        if (this.compass.equals("SUR")) {
        	if (this.playerPosition.x < this.board.getWumpus().getPosition().x && 
            	this.playerPosition.y == this.board.getWumpus().getPosition().y) {
        		this.board.getWumpus().setAlive(false);
        		return "Se escucha grito!!!";
        	}
        }
        if (this.compass.equals("ESTE")){
        	if (this.playerPosition.x == this.board.getWumpus().getPosition().x && 
                this.playerPosition.y < this.board.getWumpus().getPosition().y) {
        		this.board.getWumpus().setAlive(false);
        		return "Se escucha grito!!!";
        	}
        }
        if (this.compass.equals("OESTE")) {
        	if (this.playerPosition.x == this.board.getWumpus().getPosition().x && 
            	this.playerPosition.y > this.board.getWumpus().getPosition().y) {
        		this.board.getWumpus().setAlive(false);
        		return "Se escucha grito!!!";
        	}
        }

        return "La flecha se pierde";
    }
    
    public String checkActualPostion(Point point) {

    	if (this.board.getGold().isFound() && point.x == this.board.getSize() && point.y == 0) {
    		this.gameStatus = false;
    		return "Has ganado !!!";
    	}   		
    	
    	Element element = this.board.getElement(point);
    	
    	if (element instanceof Wumpus) {
    		if (((Wumpus) element).isAlive()) {
    			this.gameStatus = false;
    			return "Has topado con el Wumpus y has muerto";
    		} else {
    			return "Has encontrado al Wumpus muerto";
    		}
    	}
    	if (element instanceof Gold) {
    		if (!((Gold) element).isFound()) {
    			this.board.getGold().setFound(true);
    			return "Has encontrado el oro";
    		}
    	}
    	if (element instanceof WaterHole) {
    		this.gameStatus = false;
    		return "Has topado con un pozo y has muerto";
    	}
    	
    	return "Nada";
    }
    
    public String checkSenses(Point point){
    	if (point.x < 0 || point.x > this.board.getSize() || point.y < 0 || point.y > this.board.getSize()) return "";
    	Element element = this.board.getElement(point);
    	if (element instanceof Wumpus) {
    		return "Hedor detectado";
    	}
    	if (element instanceof Gold &&  !((Gold) element).isFound()) {
    		return "Brillo detectado";
    	}
    	if (element instanceof WaterHole) {
    		return "Brisa detectada";
    	}
    	  	
    	return "";
    }
}
