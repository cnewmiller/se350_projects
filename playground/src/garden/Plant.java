package garden;

 


import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Plant implements GardenStuff{

	private Circle circle;
		
	
	@Override
	public void move(double dx, double dy) {
		circle.setCenterY(circle.getCenterY()+dy);
		circle.setCenterX(circle.getCenterX()+dx);
	}

	Plant (Point2D p, Color color, Boolean movable){
		circle = new Circle();
		circle.setCenterX( p.getX());
		circle.setCenterY( p.getY());
		circle.setRadius(10);
		circle.setFill(Color.ANTIQUEWHITE);
		circle.setStroke(Color.BLACK);
		circle.setStrokeWidth(1);
	}

	

	
	public Circle getCircle(){
		return this.circle;
	}

	@Override
	public boolean ContainsPoint(Point2D point) {
		return this.circle.contains(point);
	}
	
}
