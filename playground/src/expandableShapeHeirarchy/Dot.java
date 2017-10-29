package expandableShapeHeirarchy;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * The concrete class for the circle shape.
 * @author Clay
 *
 */
public class Dot implements ChildNode, Component, Colorable{
	private ParentNode parent;
	private Circle circle;
	private final Paint originalColor;

	/**
	 * The constructor for Dot.
	 * @param p Where the center of this Dot is in 2D space and where it should be rendered.
	 * @param color What color this Dot is on its own. This color is saved as as this instance's base color.
	 */
	public Dot(Point2D p, Color color){
		originalColor = color;
		circle = new Circle();
		circle.setCenterX( p.getX());
		circle.setCenterY( p.getY());
		circle.setRadius(20);
		circle.setFill(color);
		circle.setStroke(Color.BLACK);
		circle.setStrokeWidth(1);
	}
	
	/**
	 * Returns this Dot's circle object
	 * @return this Dot's circle object
	 */
	public Circle getCircle() {
		return this.circle;
	}
	
	@Override
	public void move(double dx, double dy) {
		circle.setCenterY(circle.getCenterY()+dy);
		circle.setCenterX(circle.getCenterX()+dx);
	}

	@Override
	public boolean ContainsPoint(Point2D point) {
		
		return circle.contains(point);
	}

	
	
	@Override
	public void addParent(ParentNode c) {
		this.parent = c;
	}
	@Override
	public ParentNode getParent() {
		return this.parent;
	}
	
	
	@Override
	public void setColor(Paint c) {
		this.circle.setFill(c);
	}
	@Override
	public Paint getColor() {
		return this.circle.getFill();
	}
	@Override
	public void resetColor() {
		setColor(this.originalColor);
	}
	
}
