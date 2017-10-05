package ccGamePirates;

import java.util.Map;

import ccGamePirates.SearchStrategy.Directions;

public abstract class SearchStrategy  {
	boolean[][] grid;
	Map<Point, Boolean> seen;
	Point goal;
	
	protected class SearchNode implements Comparable<Object>{
		/**
		 * 
		 */
		private SearchStrategy search;
		Point p;
		private int score;
		private int pathCost;
		SearchNode[] children = {null, null, null, null};
		SearchNode parent;
		Directions moveToHere;
		
		SearchNode(SearchStrategy searcher, Point p, Point goal, int score, SearchNode parent, Directions move){
			this.search = searcher;
			this.p = p;			
			this.parent = parent;
			this.moveToHere = move;
			this.pathCost = (parent != null ? parent.pathCost + 1 : 0);
			this.score = score + pathCost;	//score needs to be a combination of how many moves away it is, plus how far from the CC ship it is
		}
		public void expandChildren(){
			Point[] childs = this.search.createChildren(this.p);
			for (Directions d: Directions.values()) {
				if (childs[d.ordinal()] != null && !this.search.seen.containsKey(childs[d.ordinal()])) {
					children[d.ordinal()] = new SearchNode(this.search, childs[d.ordinal()], this.search.goal, this.search.evaluatePoint(p, this.search.goal), this, d);
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
	
	protected int evaluatePoint(Point start, Point end) {//manhattan distance
		int xdiff = end.getX() - start.getX();
		int ydiff = end.getY() - start.getY();
		return xdiff + ydiff;
	}
	
	
	public static enum Directions{UP, DOWN, LEFT, RIGHT};
	private Point[] createChildren(Point start) { //this is the order of moves
		Point[] childs = {null, null, null, null};
		if (checkUp(start)) {
			childs[Directions.UP.ordinal()] = new Point(start.getX(), start.getY()-1);
		}
		if (checkDown(start)) {
			childs[Directions.DOWN.ordinal()] = new Point(start.getX(), start.getY()+1);
		}
		if (checkLeft(start)) {
			childs[Directions.LEFT.ordinal()] = new Point(start.getX()-1, start.getY());
		}
		if (checkRight(start)) {
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
	
	
	
	protected boolean checkLeft(Point p) {
		int xPos = p.getX(), yPos = p.getY();
		if (xPos > 0) {
			
			if (!grid[xPos-1][yPos] || (xPos-1 == goal.getX() && yPos == goal.getY()) ) {
				return true;
			}
		}
		return false;
	}
	protected boolean checkRight(Point p) {
		int xPos = p.getX(), yPos = p.getY();
		if (xPos < grid.length-1) {
			
			if (!grid[xPos+1][yPos] || (xPos+1 == goal.getX() && yPos == goal.getY())) {
				return true;
			}
		}
		return false;
	}
	protected boolean checkUp(Point p) {
		int xPos = p.getX(), yPos = p.getY();
		if (yPos > 0) {
			
			if (!grid[xPos][yPos-1] || (xPos == goal.getX() && yPos-1 == goal.getY())) {
				return true;
			}
		}
		return false;
	}
	protected boolean checkDown(Point p) {
		int xPos = p.getX(), yPos = p.getY();
		if (yPos < grid[0].length-1) {
			
			if (!grid[xPos][yPos+1] || (xPos == goal.getX() && yPos+1 == goal.getY())) {
				return true;
			}

		}
		return false;
	}
	
}
