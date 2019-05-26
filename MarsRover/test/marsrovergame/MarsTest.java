package marsrovergame;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class MarsTest {

	private int xSize = 10;
	private int ySize = 10;
	private List<Obstacle> obstacles;
	
	@Test
	public void testXSizeMars() throws Exception {
		obstacles = Arrays.asList(new Obstacle(9, 1), new Obstacle(8,8));
		Mars mars = new Mars(xSize, ySize, obstacles);
		int x = mars.getxSize();
		assertEquals(10, x);
	}
	
	@Test
	public void testYSizeMars() throws Exception {
		obstacles = Arrays.asList(new Obstacle(9, 1), new Obstacle(8,8));
		Mars mars = new Mars(xSize, ySize, obstacles);
		int y = mars.getySize();
		assertEquals(10, y);
	}
	
	@Test
	public void testXObstacle() throws Exception {
		Obstacle obstacle = new Obstacle(10, 1);
		int x = obstacle.getxCoord();
		assertEquals(10, x);
	}
	
	@Test
	public void testYObstacle() throws Exception {
		Obstacle obstacle = new Obstacle(10, 1);
		int y = obstacle.getyCoord();
		assertEquals(1, y);
	}
	
	@Test
	public void testMarsZeroXObstacle() throws Exception {
		obstacles = Arrays.asList(new Obstacle(0, 5), new Obstacle(8, 0));
		Mars mars = new Mars(xSize, ySize, obstacles);
		int valXObs = mars.getObstacles().get(0).getxCoord();
		assertEquals(0, valXObs);
	}
	
	@Test
	public void testMarsZeroYObstacle() throws Exception {
		obstacles = Arrays.asList(new Obstacle(0, 5), new Obstacle(8, 0));
		Mars mars = new Mars(xSize, ySize, obstacles);
		int valYObs = mars.getObstacles().get(1).getyCoord();
		assertEquals(0, valYObs);
	}
	
	@Test(expected=InvalidObstaclesException.class)
	public void testMarsWithXObstaclesGraterThenGrid() throws Exception {
		obstacles = Arrays.asList(new Obstacle(11, 10), new Obstacle(8,8));
		new Mars(xSize, ySize, obstacles);
	}
	
	@Test(expected=InvalidObstaclesException.class)
	public void testMarsWithYObstaclesGraterThenGrid() throws Exception {
		obstacles = Arrays.asList(new Obstacle(9, 20), new Obstacle(8,8));
		new Mars(xSize, ySize, obstacles);
	}
	
	@Test(expected=InvalidObstaclesException.class)
	public void testMarsWithXObstaclesLess0() throws Exception {
		obstacles = Arrays.asList(new Obstacle(9, 6), new Obstacle(-8,8));
		new Mars(xSize, ySize, obstacles);
	}
	
	@Test(expected=InvalidObstaclesException.class)
	public void testMarsWithYObstaclesLess0() throws Exception {
		obstacles = Arrays.asList(new Obstacle(10, 5), new Obstacle(8, -8));
		new Mars(xSize, ySize, obstacles);
	}
	
}
