package ccGamePirates;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class AStarSearch extends SearchStrategy{
	
	public AStarSearch(boolean[][] map) {
		grid = map;
	}
	
	public Directions getBestChoice(Point start, Point end) {
		this.goal = end;
		seen = new HashMap<Point, Boolean>();
		
		
		Queue<SearchNode> q = new PriorityQueue<SearchNode>();
		SearchNode st = new SearchNode(this, start, end, 0, null, null);
		
		q.add(st);
		
		while(!q.isEmpty()) {
			SearchNode current = q.poll();
			
			seen.put(current.p, true);
			
			if (current.p.equals(end)){
				//backtrack
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
		return null;
	}
	
	
	
}
