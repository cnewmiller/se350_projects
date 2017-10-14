package shapeHeirarchy;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Container implements Component{

ArrayList<Component> contents = new ArrayList<>();
	
	private Rectangle rect;
	ArrayList<Component> children;
	
	
	public Rectangle getRectangle() {
		return this.rect;
	}
	
	public Container(Point2D p, Color color, Boolean movable){
		rect = new Rectangle();
		children = new ArrayList<>();
		rect.setX(p.getX());
		rect.setY(p.getY());
		rect.setWidth(100);
		rect.setHeight(100);
		rect.setFill(null);
		rect.setStroke(color);
		rect.setStrokeWidth(5);
	}
	
	@Override
	public void move(double dx, double dy) {
		rect.setX(rect.getX() + dx);
		rect.setY(rect.getY() + dy);
		for (Component c : children) {
			c.move(dx, dy);
		}
	}
	
	public void addChild(Component c) {
		children.add(c);
	}
	public void removeChild(Component c) {
		children.remove(c);
	}
	
	@Override
	public boolean ContainsPoint(Point2D point) {
		return rect.contains(point);		
	}
	
	public boolean withinBounds(Point2D point) {
		return ((this.rect.getX() < point.getX() && (this.rect.getX() + this.rect.getWidth()) > point.getX() ) 
				&&
				(this.rect.getY() < point.getY() && (this.rect.getY() + this.rect.getHeight()) > point.getY() ));
	}
	
	public void setColor(Paint c) {
		this.rect.setFill(c);
	}
	public Paint getColor() {
		return this.rect.getStroke();
	}
	

}
