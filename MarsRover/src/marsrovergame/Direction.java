package marsrovergame;

public enum Direction {
	NORTH(0, 'N'), EAST(1, 'E'), SOUTH(2, 'S'), WEST(3, 'W');

	private int value;
	private char shortName;
	private static Direction[] dirs = values();

	private Direction(int newValue, char shortNameValue) {
		value = newValue;
		shortName = shortNameValue;
	}
	
	private Direction() {
		this.value = 1;
		this.shortName = 'N';
	}

	public Direction nextDir() {
		return dirs[(this.ordinal() + 1) % dirs.length];
	}

	public Direction prevDir() {
		return dirs[(this.ordinal() + 3) % dirs.length];
	}

	public int getValue() {
		return value;
	}

	public char getShortName() {
		return shortName;
	}
	
//	public Direction findByShortName(char val) {
//		Direction current = NORTH;
//		for (Direction direction : dirs) {
//			if (val == direction.getShortName()) {
//				return direction;
//			}
//		}
//		return current;
//	}

}
