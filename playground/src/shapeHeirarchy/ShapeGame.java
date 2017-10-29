package shapeHeirarchy;

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

	AnchorPane root;
	boolean inDragMode = false;
	Point2D clickPoint = null, lastPosition = null;
	Component currentComponent = null;
	ArrayList<Component> components = new ArrayList<>();
	Random r = new Random();

	
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
//					System.out.println("Dragging");
					double deltaX = clickPoint.getX()-lastPosition.getX();
					double deltaY = clickPoint.getY()-lastPosition.getY();
					currentComponent.move(deltaX, deltaY);
				}
				inDragMode = true;
				break;
			
			case("MOUSE_RELEASED"):
				for (Component c : components) {
					
					if (c instanceof Container && currentComponent instanceof Dot && c != currentComponent && ((Container)c).withinBounds(clickPoint)) {
//						System.out.println("Adding child...");
						
						Dot d = (Dot) currentComponent;
						Container cont = (Container) c;
						d.addParent(cont);
						cont.removeChild(d);//prevent double adds
						cont.addChild(d);
						d.setColor(cont.getColor());
						break;
						
					}
					
					
				}
				
				
				inDragMode = false;
				break;
			
			case("MOUSE_PRESSED"):
				if (currentComponent == null) {
					if (mouseEvent.isPrimaryButtonDown()) {
						if (currentComponent == null) {
							Dot newDot = new Dot(clickPoint, Color.BLACK, true);
							components.add(newDot);
							root.getChildren().add(newDot.getCircle());
						}
					}
					else if (mouseEvent.isSecondaryButtonDown()) {
						if (currentComponent == null) {
							Container newContainer = new Container(clickPoint, Color.color(r.nextDouble(), r.nextDouble(), r.nextDouble()), true);
							components.add(newContainer);
							root.getChildren().add(newContainer.getRectangle());	
						}
						
					}
				}
				else {
					if (currentComponent instanceof Dot) {
						Dot d = (Dot) currentComponent;
						Container c = d.getParent();
						if (c != null) {
							c.removeChild(d);
						}
						d.setColor(Color.BLACK);
					}
				}
				
			
				inDragMode = false;
				break;
			}
			
			
			lastPosition = clickPoint;	
		}
		
	};
	private Component getCurrentShape(){
		currentComponent = null;
		for(Component c : components){
			if (c.ContainsPoint(clickPoint)){
				currentComponent = c;
				break;
			}
		}
		
		if (currentComponent == null) {
//			System.out.println("empty");
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
