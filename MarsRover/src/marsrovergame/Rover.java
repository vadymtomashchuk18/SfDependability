package marsrovergame;

public class Rover {

	private int posX;
	private int posY;

	private String position;
	private Direction direction;
	private Mars field;

	public Rover(String pos, Mars field) {
		this.field = field;
		this.position = parsePositionFromString(pos);
	}

	private String parsePositionFromString(String pos) {
		if (pos.isEmpty())
			pos = "(0,0,N)";
//			return "(0,0,N)";
		String[] parsed = pos.split("[(,)]");
		try {
			this.posX = Integer.parseInt(parsed[0]);
			this.posY = Integer.parseInt(parsed[1]);
		} catch (NumberFormatException e) {
			this.posX = 0;
			this.posY = 0;
		}
		char dirShortName = parsed[2].charAt(0);
		direction = findByShortName(dirShortName);
		return "(" + posX + "," + posY + "," + direction.getShortName() + ")";
	}

	public String landedRover(String position) {
		if (position.isEmpty())
			return "(0,0,N)";
		return null;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	private Direction findByShortName(char val) {
		for (Direction direction : Direction.values()) {
			if (val == direction.getShortName()) {
				return direction;
			}
		}
		return Direction.NORTH;
	}

	@Override
	public String toString() {
		return "(" + posX + "," + posY + "," + direction + ")";
	}
}
