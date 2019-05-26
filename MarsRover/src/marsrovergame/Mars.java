package marsrovergame;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Mars {
	
	private int xSize;
	private int ySize;
	private List<Obstacle> obstacles;
	
	public Mars(int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
		this.obstacles = Collections.<Obstacle>emptyList();
	}
	
	public Mars(int xSize, int ySize, List<Obstacle> obstacles) throws InvalidObstaclesException {
		if (!validObstacles(xSize, ySize, obstacles))
			throw new InvalidObstaclesException();
		this.xSize = xSize;
		this.ySize = ySize;
		this.obstacles = obstacles;
	}
	
	private boolean validObstacles(int x, int y, List<Obstacle> obs) {
		for (Obstacle obstacle : obs) {
			if (obstacle.getxCoord() < 0 ||
					obstacle.getyCoord() < 0 ||
					obstacle.getxCoord() > x-1 ||
					obstacle.getyCoord() > y-1) {
				return false;
			}
		}
		return true;
	}
	public int getxSize() {
		return xSize;
	}
	
	public void setxSize(int xSize) {
		this.xSize = xSize;
	}
	
	public int getySize() {
		return ySize;
	}
	
	public void setySize(int ySize) {
		this.ySize = ySize;
	}
	
	public List<Obstacle> getObstacles() {
		return obstacles;
	}
	
	public void setObstacles(List<Obstacle> obstacles) {
		this.obstacles = obstacles;
	}

	@Override
	public String toString() {
		return "Mars [xSize=" + xSize + ", ySize=" + ySize + ", obstacles=" + obstacles + "]";
	}

	

}
