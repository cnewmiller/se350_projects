package ccGamePirates;

import java.util.Map;


/**
 * 
 * Abstract superclass for any grid searching algorithm, such as best-first, uniform-cost, depth-first, etc.
 * 
 * @author Clay
 *
 */
public abstract class SearchStrategy  {
	boolean[][] grid;
	Map<Point, Boolean> seen;
	Point goal;
	public static enum Directions{UP, DOWN, LEFT, RIGHT};
	protected GridChecker checker;
	
	protected class SearchNode implements Comparable<Object>{

		Point p;
		private int score;
		private int pathCost;
		SearchNode[] children = {null, null, null, null};
		SearchNode parent;
		Directions moveToHere;
		
		SearchNode(Point p, Point goal, int score, SearchNode parent, Directions move, int cost){
			this.p = p;			
			this.parent = parent;
			this.moveToHere = move;
			this.pathCost = (parent != null ? parent.pathCost + cost : 0);
			this.score = score + pathCost;	//score is a combination of how many moves away it is, plus how far from the CC ship it is
		}
		//successor function in tandem with createChildren
		public void expandChildren(){
			Point[] childs = createChildren(this.p);
			for (Directions d: Directions.values()) {
				if (childs[d.ordinal()] != null && !seen.containsKey(childs[d.ordinal()])) {
					children[d.ordinal()] = new SearchNode(childs[d.ordinal()], goal, evaluatePoint(p, goal), this, d, 1);
				}
			}
		}

		public int compareTo(Object o) {
			if (null == o || !(o instanceof SearchNode)) return -10;
			
			SearchNode n = (SearchNode)o;
			
			if (n.equals(this) || n.score==this.score) {
				return 0;
			}
			else if (n.score > this.score) {
				return -1;
			}
			else if(n.score < this.score) {
				return 1;
			}
			
			return 0;
		}
		
		@Override
		public boolean equals(Object o) {
			if (null == o || !(o instanceof SearchNode)) return false;
			if (this == o) return true;
			return (this.score == ((SearchNode) o).score  && this.p == ((SearchNode) o).p);
		}
		
		
		
	}
	
	//can return 0 if you don't want any heuristic searching, but I require it to be implemented rather than defaulting to 0
	abstract int evaluatePoint(Point start, Point end);
	
	private Point[] createChildren(Point start) {
		Point[] childs = {null, null, null, null};
		if (checker.checkUp(start, grid) || (start.getX() == goal.getX() && start.getY()-1 == goal.getY()) ) {
			childs[Directions.UP.ordinal()] = new Point(start.getX(), start.getY()-1);
		}
		if (checker.checkDown(start, grid) || (start.getX() == goal.getX() && start.getY()+1 == goal.getY())) {
			childs[Directions.DOWN.ordinal()] = new Point(start.getX(), start.getY()+1);
		}
		if (checker.checkLeft(start, grid) || (start.getX()-1 == goal.getX() && start.getY() == goal.getY())) {
			childs[Directions.LEFT.ordinal()] = new Point(start.getX()-1, start.getY());
		}
		if (checker.checkRight(start, grid) || (start.getX()+1 == goal.getX() && start.getY() == goal.getY())) {
			childs[Directions.RIGHT.ordinal()] = new Point(start.getX()+1, start.getY());
		}
		return childs;
	}
	
	protected Directions backTrack(SearchNode s) {
		while (s.parent != null) {
			if (s.parent.moveToHere == null)
				return s.moveToHere;
			
			s = s.parent;
		}
		return null;
	}
	
}
