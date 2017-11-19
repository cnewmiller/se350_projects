package ccGameFinal;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import ccGameFinal.Interfaces.Collidable;
import ccGameFinal.Interfaces.CollisionFunction;
import ccGameFinal.Interfaces.GridChecker;
import ccGameFinal.Interfaces.PointHistory;
import ccGameFinal.UtilityClasses.AdjacencyChecker;
import ccGameFinal.UtilityClasses.Point;
import ccGameFinal.UtilityClasses.SimpleChecker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * 
 * Basically a PirateShip that moves randomly.
 * 
 * @author Clay
 *
 */
public class SeaMonster extends Observable implements Observer, PointHistory, Collidable {

	private String imageLocation = "ccGameFinal/images/SeaMonsterIcon.png";
	private Point p;
	private Point prevPoint;
	private ImageView MonsterImage;
	private boolean[][] grid;
	private boolean turn = true;
	private GridChecker checker;
	public static Map<Class<?>, CollisionFunction> collisions = new HashMap<>();
	public void addCollisionClass(Class<?> otherClass, CollisionFunction f) {
		collisions.put(otherClass, f);
	}
	public void removeCollisionClass(Class<?> otherClass) {
		collisions.remove(otherClass);
	}
	
	
	public ImageView getMonsterImage() {return this.MonsterImage;}
	
	
	public SeaMonster() {
		this.grid = OceanMap.getInstance().getMap();
		this.checker = new SimpleChecker();
		p = new Point();
		prevPoint = new Point();
		this.MonsterImage = new ImageView(new Image(imageLocation, OceanExplorer.scale, OceanExplorer.scale, true, true));
		this.setMonsterLocation(OceanExplorer.dimension-1, OceanExplorer.dimension-1);
	}
	
	public SeaMonster(Point p) {
		this.grid = OceanMap.getInstance().getMap();
		this.checker = new SimpleChecker();
		this.p = p;
		prevPoint = null;
		this.MonsterImage = new ImageView(new Image(imageLocation, OceanExplorer.scale, OceanExplorer.scale, true, true));
		this.setMonsterLocation(this.p.getX(), this.p.getY());
	}
	
	public Point getMonsterLocation() {
		return this.p;
	}
	
	private void updatePrev() {
		if (prevPoint == null) {
			prevPoint = new Point();
		}
		prevPoint.setX(p.getX());
		prevPoint.setY(p.getY());
	}
	
	public void setMonsterLocation(int x, int y) {
		updatePrev();
		p.setX(x);
		p.setY(y);
		
		MonsterImage.setX(p.getX()*OceanExplorer.scale);
		MonsterImage.setY(p.getY()*OceanExplorer.scale);
	}
	
	public void goEast() {
		if (checker.checkRight(p, grid)) {
			updatePrev();
			p.setX(p.getX()+1);
		}
		MonsterImage.setX(p.getX()*OceanExplorer.scale);
		MonsterImage.setY(p.getY()*OceanExplorer.scale);
	}
	public void goWest() {
		if (checker.checkLeft(p, grid)) {
			updatePrev();
			p.setX(p.getX()-1);
		}
		MonsterImage.setX(p.getX()*OceanExplorer.scale);
		MonsterImage.setY(p.getY()*OceanExplorer.scale);
	}
	public void goNorth() {
		if (checker.checkUp(p, grid)) {
			updatePrev();
			p.setY(p.getY()-1);
		}
		MonsterImage.setX(p.getX()*OceanExplorer.scale);
		MonsterImage.setY(p.getY()*OceanExplorer.scale);
	}
	public void goSouth() {
		if (checker.checkDown(p, grid)) {
			updatePrev();
			p.setY(p.getY()+1);
		}
		MonsterImage.setX(p.getX()*OceanExplorer.scale);
		MonsterImage.setY(p.getY()*OceanExplorer.scale);
	}

	@Override
	public Point getPrevPoint() {
		return prevPoint;
	}
	
	
	public void randomSwim() {
		
		if (turn) {
			turn = false;
			
			int choice = OceanExplorer.r.nextInt(4);
			
			boolean flag = true;
			while (flag) {
				switch(choice) {
				case 0:
					if (!checker.checkUp(p, grid)) {
						choice = OceanExplorer.r.nextInt(4);
					}
					else {
						flag = false;
					}
					break;
				case 1:
					if (!checker.checkDown(p, grid)) {
						choice = OceanExplorer.r.nextInt(4);
					}
					else {
						flag = false;
					}
					break;
				case 2:
					if (!checker.checkRight(p, grid)) {
						choice = OceanExplorer.r.nextInt(4);
					}
					else {
						flag = false;
					}
					break;
				case 3:
					if (!checker.checkLeft(p, grid)) {
						choice = OceanExplorer.r.nextInt(4);
					}
					else {
						flag = false;
					}
					break;
				default:
					break;
				}
			}
			

			
			switch(choice) {
			case 0:
				goNorth();
				break;
			case 1:
				goSouth();
				break;
			case 2:
				goEast();
				break;
			case 3:
				goWest();
				break;
			default:
				break;
			}
			this.setChanged();
			notifyObservers(this.p);
		}
		else {
			turn = true;
		}
	}

	//Doesn't matter what info it gets, it knows when its turn is
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof ColumbusShip && turn) {
			//check if columbusShip is next to me
			if (AdjacencyChecker.isNextToAnywhere(this.p, ( (ColumbusShip)o).getPoint())) {
				OceanExplorer.getInstance().endGame();
			}
		}
		randomSwim();
	}


	@Override
	public void collideWith(Collidable c) {
		
	}


	@Override
	public Point getPoint() {
		return this.p;
	}
	
}
