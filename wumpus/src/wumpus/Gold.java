package wumpus;

import java.awt.Point;

public class Gold implements Element{
	private Point position;
	private boolean found;
	
	Gold(){
		this.found = false;
	}
	
	Gold(Point point){
		this.found = false;
		this.position = point;
	}
	
	public boolean isFound() {
		return found;
	}

	public void setFound(boolean found) {
		this.found = found;
	}

	@Override
	public void setPosition(Point point) {
		this.position = point;		
	}

	@Override
	public Point getPosition() {
		return position;
	}
    
}
