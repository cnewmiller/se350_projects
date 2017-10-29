package expandableShapeHeirarchy;

import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ShapeGame extends Application {

	private AnchorPane root;
	private boolean inDragMode = false;
	private Point2D clickPoint = null, lastPosition = null;
	private Component currentComponent = null;
	private ArrayList<Component> components = new ArrayList<>();
	private Random r = new Random();

	/*
	 * This is the function that gets run on every mouse event for this GUI assignment.
	 * 
	 * I kind of think I should've split this into three functions, but this is how I did it in the lab.
	 * 
	 */
	EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>(){
		@Override
		public void handle(MouseEvent mouseEvent){

			clickPoint = new Point2D(mouseEvent.getX(), mouseEvent.getY());
			String eventName = mouseEvent.getEventType().getName();
			
			if(!inDragMode){
				currentComponent = getCurrentShape();
			}
			
			switch (eventName){
			
			case("MOUSE_DRAGGED"):
				
				if(lastPosition != null && currentComponent!=null){
					double deltaX = clickPoint.getX()-lastPosition.getX();
					double deltaY = clickPoint.getY()-lastPosition.getY();
					currentComponent.move(deltaX, deltaY);
				}
				inDragMode = true;
				break;
			
			case("MOUSE_RELEASED"):
				for (Component c : components) {
					if (c instanceof Container) {
						Container container = (Container) c;
						if (container.withinBounds(clickPoint) && currentComponent instanceof ChildNode) {
							ChildNode child = (ChildNode) currentComponent;
							
							child.addParent(container);
							container.removeChild(child);
							container.addChild(child);
							
							if (child instanceof Colorable) {
								( (Colorable)child).setColor( container.getColor()  );
							}
							break;
						}
					}
				}
				inDragMode = false;
				break;
			
			case("MOUSE_PRESSED"):
				if (currentComponent == null) {
					if (mouseEvent.isPrimaryButtonDown()) {
						if (currentComponent == null) {
							Dot newDot = new Dot(clickPoint, Color.BLACK);
							components.add(newDot);
							root.getChildren().add(newDot.getCircle());
							currentComponent = newDot;
						}
					}
					else if (mouseEvent.isSecondaryButtonDown()) {
						if (currentComponent == null) {
							Container newContainer = new Container(clickPoint, Color.color(r.nextDouble(), r.nextDouble(), r.nextDouble()));
							components.add(newContainer);
							root.getChildren().add(newContainer.getRectangle());	
							currentComponent = newContainer;
						}
					}
				}
				else {
					if (currentComponent instanceof ChildNode) {
						ChildNode d = (ChildNode) currentComponent;
						ParentNode c = d.getParent();
						if (c != null) {
							c.removeChild(d);
						}
						d.addParent(null);
						if (d instanceof Colorable) {
							((Colorable)d).resetColor();
						}
					}
				}
				inDragMode = true;
				break;
			}
			lastPosition = clickPoint;	
		}
	};
	
	//checks for collisions, not boundary containment. Undefined behavior for when there is an overlap.
	private Component getCurrentShape(){
		currentComponent = null;
		for(Component c : components){
			if (c.ContainsPoint(clickPoint)){
				currentComponent = c;
				break;
			}
		}
		
		return currentComponent;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		root  = new AnchorPane();
		Scene scene = new Scene(root, 500, 500);
			
		scene.setOnMousePressed(mouseHandler);
		scene.setOnMouseDragged(mouseHandler);
		scene.setOnMouseReleased(mouseHandler);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main (String[] args){
		launch(args);
	}

}
