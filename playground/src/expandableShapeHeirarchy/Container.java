package expandableShapeHeirarchy;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;


/**
 * The concrete class for the square shape.
 * @author Clay
 *
 */
public class Container implements Component, ParentNode, ChildNode, Colorable{

	private ArrayList<Component> children = new ArrayList<>();
	private ParentNode parent;
	private final Paint originalColor;
	private Rectangle rect;	
	
	/**
	 * The constructor for Container.
	 * @param p Where this Container is in 2D space and where it should be rendered.
	 * @param color What color this container is on its own. This color is saved as as this instance's base color.
	 */
	public Container(Point2D p, Color color){
		rect = new Rectangle();
		parent = null;
		originalColor = color;
		rect.setX(p.getX());
		rect.setY(p.getY());
		rect.setWidth(150);
		rect.setHeight(150);
		rect.setFill(null);
		rect.setStroke(color);
		rect.setStrokeWidth(10);
	}

	/**
	 * Returns this Container's rectangle object
	 * @return this Container's rectangle object
	 */
	public Rectangle getRectangle() {
		return this.rect;
	}
	
	@Override
	public void move(double dx, double dy) {
		rect.setX(rect.getX() + dx);
		rect.setY(rect.getY() + dy);
		for (Component c : children) {
			c.move(dx, dy);
			if (c instanceof Colorable) {
				((Colorable) c).setColor(this.getColor());
			}
		}
	}
	@Override
	public void addChild(ChildNode c) {
		if (c instanceof Component) {
			children.add(((Component)c));
		}
	}
	
	@Override
	public void removeChild(ChildNode c) {
		if (c instanceof Component) {
			children.remove(((Component)c));			
		}
	}
	
	@Override
	public boolean ContainsPoint(Point2D point) {
		return rect.contains(point);		
	}
	
	/**
	 * A square container with no fill can still contain objects, and this method is the difference between being on the Container and within the Container.
	 * @param point the point to check
	 * @return true if the point is within the bounds of this Container, false if outside
	 */
	public boolean withinBounds(Point2D point) {
		return ((this.rect.getX() < point.getX() && (this.rect.getX() + this.rect.getWidth()) > point.getX() ) 
				&&
				(this.rect.getY() < point.getY() && (this.rect.getY() + this.rect.getHeight()) > point.getY() ));
	}
	
	
	/**
	 * In addition to setting this Container's color, propagates the color change down to all this instance's children.
	 */
	@Override
	public void setColor(Paint p) {
		this.rect.setStroke(p);
		for (Component c : children) {
			if (c instanceof Colorable && c != this.parent) {
				((Colorable) c).setColor(this.getColor());
			}
		}
	
	}
	
	@Override
	public Paint getColor() {
		return this.rect.getStroke();
	}
	@Override
	public void resetColor() {
		setColor(this.originalColor);
	}

	@Override
	public void addParent(ParentNode p) {
		parent = p;
	}

	@Override
	public ParentNode getParent() {
		return parent;
	}
	
	

}
