package ccGameFinal;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;


/**
 * Specific implementation of SearchStrategy. Uses a priority queue  to search nodes and defines the heuristic as a Manhattan distance comparison.
 * 
 * getBestChoice() returns the direction of the current best move to get to where the target point is located.
 * 
 * @author Clay
 *
 */
public class AStarSearch extends SearchStrategy{
	
	public AStarSearch(boolean[][] map) {
		grid = map;
		this.checker = new SimpleChecker();
		}
	
	public Directions getBestChoice(Point start, Point end) {
		this.goal = end;
		
		seen = new HashMap<Point, Boolean>();
				
		Queue<SearchNode> q = new PriorityQueue<SearchNode>();
		SearchNode st = new SearchNode(start, end, 0, null, null, 0);
		
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
					if (current.children[i] != null && !q.contains(current.children[i])) {
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
	int evaluatePoint(Point start, Point end) {//Manhattan distance, basic heuristic
		int xdiff = end.getX() - start.getX();
		int ydiff = end.getY() - start.getY();
		return xdiff + ydiff;
	}
	
	
	
}
