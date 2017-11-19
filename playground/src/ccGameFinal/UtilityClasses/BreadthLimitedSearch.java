package ccGameFinal.UtilityClasses;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * A BFS implementation that limits its depth. Works because the "cost" variable increments by one every time, and overriding the heuristic to 0 means it
 * 	ONLY increases by one. 
 * 
 * @author Clay
 *
 */
public class BreadthLimitedSearch extends SearchStrategy{
	
	private int depth;
	public BreadthLimitedSearch(boolean[][] map, int depth) {
		grid = map;
		this.checker = new SimpleChecker();
		this.depth = depth;
	}
	
	public Directions getBestChoice(Point start, Point end) {
		this.goal = end;
		
		seen = new HashMap<Point, Boolean>();
				
		Queue<SearchNode> q = new LinkedList<SearchNode>();
		SearchNode st = new SearchNode(start, end, 0, null, null, 1);
		
		q.add(st);
		
		while(!q.isEmpty()) {
			SearchNode current = q.poll();
			
			seen.put(current.p, true);
			
			if (current.p.equals(end)){
				return backTrack(current);
			}
			else {
				current.expandChildren();
				for(int i = 0 ; i < 4 ; i++) {
					if (current.children[i] != null && !q.contains(current.children[i]) && current.getCost() < depth) {
						q.add(current.children[i]);
					}
				}
			}			
		}
		return null; //not found
	}

	
	/**
	 * Heuristic
	 */
	@Override
	int evaluatePoint(Point start, Point end) {
		return 0;
	}
	
	
	
}
