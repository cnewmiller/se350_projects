package shapeHeirarchy;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Dot implements Component{
	private Container parent;
	private Circle circle;
	@Override
	public void move(double dx, double dy) {
		circle.setCenterY(circle.getCenterY()+dy);
		circle.setCenterX(circle.getCenterX()+dx);
	}

	@Override
	public boolean ContainsPoint(Point2D point) {
		
		return circle.contains(point);
	}

	
	public Dot(Point2D p, Color color, Boolean movable){
		circle = new Circle();
		circle.setCenterX( p.getX());
		circle.setCenterY( p.getY());
		circle.setRadius(10);
		circle.setFill(color);
		circle.setStroke(Color.BLACK);
		circle.setStrokeWidth(1);
	}
	public Circle getCircle() {
		return this.circle;
	}
	public void addParent(Container c) {
		this.parent = c;
	}
	public Container getParent() {
		return this.parent;
	}
	public void setColor(Paint c) {
		this.circle.setFill(c);
	}
	public Paint getColor() {
		return this.circle.getFill();
	}
	
	
}
