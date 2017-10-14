package garden;





import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class GardenLayout extends Application{

	AnchorPane root;
	Plant flower;
	FlowerBed bed;
	Point2D lastPosition = null;
	GardenStuff currentComponent;
	boolean inDragMode = false;
	ArrayList<GardenStuff> components = new ArrayList<GardenStuff>();
	Point2D clickPoint;
	
	
	
	EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>(){
		@Override
		public void handle(MouseEvent mouseEvent){
			
			
			clickPoint = new Point2D(mouseEvent.getX(), mouseEvent.getY());
			System.out.println(clickPoint.getX()+ " " +clickPoint.getY());
			String eventName = mouseEvent.getEventType().getName();
			
			if(!inDragMode){
				currentComponent = getCurrentShape();
			}
			
			switch (eventName){
			
			case("MOUSE_DRAGGED"):
				
				if(lastPosition != null){
					
					System.out.println("Dragging");
					double deltaX = clickPoint.getX()-lastPosition.getX();
					double deltaY = clickPoint.getY()-lastPosition.getY();
					currentComponent.move(deltaX, deltaY);
				}
				inDragMode = true;
				break;
			
			case("MOUSE_RELEASED"):
				
				inDragMode = false;
				break;
			
			case("MOUSE_PRESSED"):
				
				inDragMode = false;
				break;
			}
			
			
			lastPosition = clickPoint;	
		}
		
	};
	
	
	private GardenStuff getCurrentShape(){
		currentComponent = null;
		for(GardenStuff thing : components){
			if (thing.ContainsPoint(clickPoint)){
				currentComponent = thing;
				break;
			}
		}
		if (currentComponent == null) {
			System.out.println("empty");
		}
		return currentComponent;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		root  = new AnchorPane();
		Scene scene = new Scene(root, 500, 500);
		
		
		
		Point2D flowerP = new Point2D(30, 30);
		flower = new Plant(flowerP, Color.HONEYDEW, true);

		
		components.add(flower);
		root.getChildren().add(flower.getCircle());
		
		bed = new FlowerBed(new Point2D(100,100), Color.AQUAMARINE, true);
		components.add(bed);
		root.getChildren().add(bed.getRectangle());
		
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
