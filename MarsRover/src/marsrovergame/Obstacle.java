package marsrovergame;

public class Obstacle {

	private int xCoord;
	private int yCoord;
	
	public Obstacle(int xCoord, int yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}

	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	@Override
	public String toString() {
		return "Obstacle [xCoord=" + xCoord + ", yCoord=" + yCoord + "]";
	}

	

}
