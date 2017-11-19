package ccGameFinal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import ccGameFinal.Interfaces.Collidable;
import ccGameFinal.Interfaces.CollisionFunction;
import ccGameFinal.UtilityClasses.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * Entire class for treasure. Contains within it the decorator pattern, but those classes weren't necessary for the rest of the project, so I just wrote them here.
 * 
 * 
 * @author Clay
 *
 */
public class Treasure implements Collidable{
	private static final ArrayList<GenericDecoration> allDecorations = new ArrayList<>();
	
	public static void addGenericDecoration(GenericDecoration g) {
		allDecorations.add(g);
	}
	public static void removeGenericDecoration(GenericDecoration g) {
		allDecorations.remove(g);
	}
	/**
	 * The list of adjectives that I came up with. More can be added dynamically with addGenericDecoration()
	 */
	public static void initializeDefaultList() {
		addGenericDecoration(new Treasure.GenericDecoration(null, "golden", 		1));
		addGenericDecoration(new Treasure.GenericDecoration(null, "lucrative", 	1));
		addGenericDecoration(new Treasure.GenericDecoration(null, "shiny", 		1));
		addGenericDecoration(new Treasure.GenericDecoration(null, "stolen", 		1));
		addGenericDecoration(new Treasure.GenericDecoration(null, "Aztec", 		1));
		addGenericDecoration(new Treasure.GenericDecoration(null, "As Seen On TV",1));
		addGenericDecoration(new Treasure.GenericDecoration(null, "janky", 		1));
		addGenericDecoration(new Treasure.GenericDecoration(null, "Christmasy", 	1));
		addGenericDecoration(new Treasure.GenericDecoration(null, "dog related", 	1));
		addGenericDecoration(new Treasure.GenericDecoration(null, "cute", 		1));
		addGenericDecoration(new Treasure.GenericDecoration(null, "wearable", 	1));
		Collections.shuffle(allDecorations);
	}
	
	//the component
	public static abstract class iTreasure{
		String description = "";
		
		public abstract int getValue();
		
		public String describe() {
			return this.description; 
		};
	}
	
	//the decorator
	public static abstract class iDecoration extends iTreasure{
		@Override
		public abstract String describe();
	}
	
	//base concrete class
	public static class PileOfLoot extends iTreasure {
		@Override
		public String describe(){
			return (" loot!" );
		}

		@Override
		public int getValue() {
			return 1;
		}
	}
	
	//decorator, generalized for convenience
	public static class GenericDecoration extends iDecoration{
		iTreasure decoration;
		String adjective;
		int value;
		public GenericDecoration(iTreasure d, String adj, int val) {
			decoration = d;
			if (adj == null) {
				this.adjective = "pretty okay";
			}
			else {
				this.adjective = adj;
			}
			value = val;
		}
		public GenericDecoration(iTreasure d, GenericDecoration g) {
			decoration = d;
			this.adjective = g.adjective;
			this.value = g.value;
		}
		
		@Override
		public String describe() {
			return ", "+adjective+(decoration != null ? decoration.describe() : "... stuff?" );//catches null
		}

		@Override
		public int getValue() {
			return this.value+decoration.getValue();
		}
	}
	
	private Point location;
	private iTreasure loot;
	public iTreasure getLoot() {return loot;}

	
	private String imageLocation = "ccGameFinal/images/treasureChest.png";
	private ImageView treasureImage;
	public ImageView getImage() {return this.treasureImage;}
		
	public static Map<Class<?>, CollisionFunction> collisions = new HashMap<>();
	public void addCollisionClass(Class<?> otherClass, CollisionFunction f) {
		collisions.put(otherClass, f);
	}
	public void removeCollisionClass(Class<?> otherClass) {
		collisions.remove(otherClass);
	}
	public Treasure(Point p) {
		location = p;
		loot = new PileOfLoot();

		treasureImage = new ImageView(new Image(imageLocation, OceanExplorer.scale, OceanExplorer.scale, true, true));
		treasureImage.setX(location.getX()*OceanExplorer.scale);
		treasureImage.setY(location.getY()*OceanExplorer.scale);
	}
	public void clear() {
		this.loot = new PileOfLoot();
	}
	
	public String describe() {
		return "A pile of wonderful"+(loot != null ? loot.describe() : "... things?" );
	}
	public int cashOut() {
		return loot.getValue();
	}
	//add another decorator
	public void collect() {
		loot = getNextTreasure();
		moveLocation();
	}
	public void moveLocation() {
		Point oldPoint = location;
		location = OceanMap.getInstance().getOpenSquare();
		
		while(location.equals(oldPoint)) {
			location = OceanMap.getInstance().getOpenSquare();
		}
		
		treasureImage.setX(location.getX()*OceanExplorer.scale);
		treasureImage.setY(location.getY()*OceanExplorer.scale);
	}
	
	private iTreasure getNextTreasure() {
		GenericDecoration g = Treasure.allDecorations.get(0);
		Treasure.allDecorations.remove(g);
		Treasure.allDecorations.add(g);
		return new GenericDecoration(loot, g);
	}
	@Override
	public Point getPoint() {
		return location;
	}
	@Override
	public void collideWith(Collidable c) {
		//does nothing, the collision with the player is handled by the player
	}
	
	
}