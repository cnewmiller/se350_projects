package ccGameFinal.UtilityClasses;

/**
 * Library class for comparing points relative to each other. Does not enforce being "on the map"
 * 
 * @author Clay
 *
 */
public class AdjacencyChecker {

	public static boolean isToTheLeft(Point relativePoint, Point q) {
		if (relativePoint.getX() == q.getX() + 1 &&
				relativePoint.getY() == q.getY()) {
			return true;
		}
		return false;
	}
	public static boolean isToTheRight(Point relativePoint, Point q) {
		if (relativePoint.getX() == q.getX() - 1 &&
			relativePoint.getY() == q.getY()) {
			return true;
		}
		return false;
	}

	public static boolean isAbove(Point relativePoint, Point q) {
		if (relativePoint.getX() == q.getX() &&
			relativePoint.getY() == q.getY()+1) {
			return true;
		}
		return false;
	}
	public static boolean isBelow(Point relativePoint, Point q) {
		if (relativePoint.getX() == q.getX() &&
			relativePoint.getY() == q.getY()-1) {
			return true;
		}
		return false;
	}
	public static boolean isNextToAnywhere(Point p, Point q) {
		
		return (isToTheLeft(p, q) || isToTheRight(p, q) || isAbove(p,q) || isBelow(p,q));
		
	}
}
