package wumpus;

import java.awt.Point;

public class WaterHole implements Element{
	private Point position;
	
	public WaterHole() {	
	}
	
	public WaterHole(Point point) {
		this.position = point;
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
