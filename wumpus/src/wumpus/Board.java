package wumpus;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Board {
    private int size;
    private Element[][] board;
    private Wumpus wumpus;
    private Gold gold;
    private List<WaterHole> waterHoles;
    
    Board(int size, int waterHoles) {
    	this.size = size-1;
    	this.board = new Element[size][size];
    	this.wumpus = (Wumpus) setElement(new Wumpus());
    	this.gold = (Gold) setElement(new Gold());
    	this.waterHoles = new ArrayList<WaterHole>();
    	for (int i = 0; i < waterHoles; i++) {
    		this.waterHoles.add((WaterHole) setElement(new WaterHole()));
    	}
    }
    
    Board(int size, Wumpus wumpus, Gold gold, List<WaterHole> waterHoles){
    	this.size = size-1;
    	this.board = new Element[size][size];
    	this.wumpus = wumpus;
    	this.board[this.wumpus.getPosition().x][this.wumpus.getPosition().y] = this.wumpus;
    	this.gold = gold;
    	this.board[this.gold.getPosition().x][this.gold.getPosition().y] = this.gold;
    	this.waterHoles = waterHoles;
    	for (int i = 0; i < this.waterHoles.size(); i++) {
    		this.board[this.waterHoles.get(i).getPosition().x][this.waterHoles.get(i).getPosition().y] = this.waterHoles.get(i);
    	}
    }  
    
    public Wumpus getWumpus() {
    	return this.wumpus;
    }
    
    public Gold getGold() {
    	return this.gold;
    }
    
    public Element getElement(Point point) {
    	return this.board[point.x][point.y];
    }
    
    private Element setElement(Element element) {
    	Point point = this.getCoords();
    	while(this.board[point.x][point.y] != null) {
    		point = this.getCoords();
    	}
    	this.board[point.x][point.y] = element;
    	element.setPosition(point);
    	return element;
    }
    
    public int getSize() {
    	return this.size;
    }
    
    private Point getCoords() {
    	return new Point(new Random().nextInt(this.size), new Random().nextInt(this.size));
    }
   
}
