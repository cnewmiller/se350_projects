package garden;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FlowerBed implements GardenStuff{

	ArrayList<GardenStuff> contents;
	
	private Rectangle rect;
	
	public FlowerBed(Point2D p, Color color, Boolean movable){
		rect = new Rectangle();
		rect.setX(p.getX());
		rect.setY(p.getY());
		rect.setWidth(50);
		rect.setHeight(70);
		rect.setFill(color);
		rect.setStroke(Color.BLACK);
		rect.setStrokeWidth(1);
	}
	public Rectangle getRectangle() {return this.rect;}
	
	@Override
	public void move(double dx, double dy) {
		
		rect.setX(rect.getX() + dx);
		rect.setY(rect.getY() + dy);
	}
	
	public void addToFlowerBed(GardenStuff thing){
		if  (contents == null){
			contents = new ArrayList<>();
		}
		contents.add(thing);
	}

	@Override
	public boolean ContainsPoint(Point2D point) {
		// TODO Auto-generated method stub
		return rect.contains(point);
	}

}
