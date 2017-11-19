package ccGameFinal.junitTests;


import org.junit.Test;

import ccGameFinal.UtilityClasses.AdjacencyChecker;
import ccGameFinal.UtilityClasses.Point;


/**
 * Tests for the AdjacencyChecker library. I needed to see if two things were next to each other easily.
 * 
 * @author Clay
 *
 */
public class AdjacencyCheckerTest {

	@Test
	public void leftTest() {
		Point p = new Point(5,5);
		Point q = new Point(4,5);
		assert(AdjacencyChecker.isToTheLeft(p, q));
		assert(!AdjacencyChecker.isToTheRight(p, q));
		assert(!AdjacencyChecker.isAbove(p, q));
		assert(!AdjacencyChecker.isBelow(p, q));
		assert(AdjacencyChecker.isNextToAnywhere(p, q));
		
	}
	public void rightTests() {
		Point p = new Point(5,5);
		Point q = new Point(6,5);
		assert(!AdjacencyChecker.isToTheLeft(p, q));
		assert(AdjacencyChecker.isToTheRight(p, q));
		assert(!AdjacencyChecker.isAbove(p, q));
		assert(!AdjacencyChecker.isBelow(p, q));
		assert(AdjacencyChecker.isNextToAnywhere(p, q));
	}
	public void aboveTests() {
		Point p = new Point(5,5);
		Point q = new Point(5,4);
		assert(!AdjacencyChecker.isToTheLeft(p, q));
		assert(!AdjacencyChecker.isToTheRight(p, q));
		assert(AdjacencyChecker.isAbove(p, q));
		assert(!AdjacencyChecker.isBelow(p, q));
		assert(AdjacencyChecker.isNextToAnywhere(p, q));
	}
	public void belowTests() {
		Point p = new Point(5,5);
		Point q = new Point(5,6);
		assert(!AdjacencyChecker.isToTheLeft(p, q));
		assert(!AdjacencyChecker.isToTheRight(p, q));
		assert(!AdjacencyChecker.isAbove(p, q));
		assert(AdjacencyChecker.isBelow(p, q));
		assert(AdjacencyChecker.isNextToAnywhere(p, q));
	}
	public void apartTests() {
		Point p = new Point(5,5);
		Point q = new Point(6,6);
		assert(!AdjacencyChecker.isToTheLeft(p, q));
		assert(!AdjacencyChecker.isToTheRight(p, q));
		assert(!AdjacencyChecker.isAbove(p, q));
		assert(!AdjacencyChecker.isBelow(p, q));
		assert(!AdjacencyChecker.isNextToAnywhere(p, q));
	}
	
}
