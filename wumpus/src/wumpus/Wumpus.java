package wumpus;

import java.awt.Point;

public class Wumpus implements Element {
    private Point position;
    private boolean alive;
    
    Wumpus() {
    	this.alive = true;
    }
    
    Wumpus(Point position){
    	this.position = position;
    	this.alive = true;
    }
        
    public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	@Override
    public void setPosition(Point position) {
		this.position = position;
	}

	@Override
    public Point getPosition() {
        return position;
    }
}
