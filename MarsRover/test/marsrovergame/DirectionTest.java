package marsrovergame;

import static org.junit.Assert.*;

import org.junit.Test;

public class DirectionTest {

	@Test
	public void testNorthDirection() {
		Direction dir = Direction.NORTH;
		assertEquals(Direction.NORTH, dir);
	}
	
	public void testNextDirection() {
		Direction dir = Direction.NORTH;
		dir.nextDir();
		assertEquals(Direction.EAST, dir);
	}
	
	public void testNextCornerDirection() {
		Direction dir = Direction.WEST;
		dir.nextDir();
		assertEquals(Direction.NORTH, dir);
	}
	
	public void testPrevDirection() {
		Direction dir = Direction.EAST;
		dir.prevDir();
		assertEquals(Direction.NORTH, dir);
	}
	
	public void testPrevCornerDirection() {
		Direction dir = Direction.NORTH;
		dir.nextDir();
		assertEquals(Direction.WEST, dir);
	}
	
	public void testNorthValueDirection() {
		Direction dir = Direction.NORTH;
		int value = dir.getValue();
		assertEquals(1, value);
	}
	
	public void testWestValueDirection() {
		Direction dir = Direction.WEST;
		int value = dir.getValue();
		assertEquals(4, value);
	}

	public void testNorthShNameDirection() {
		Direction dir = Direction.NORTH;
		int value = dir.getShortName();
		assertEquals("N", value);
	}
	
	public void testWestShNameDirection() {
		Direction dir = Direction.WEST;
		int value = dir.getShortName();
		assertEquals("W", value);
	}
	
//	public void testFindByShortName() {
//		Direction dir = Direction.NORTH;
//		Direction value = findByShortName('W');
//		assertEquals(Direction.WEST, value);
//	}
}
