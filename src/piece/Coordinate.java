package piece;

import game.BoardState;

public class Coordinate {
	private int x;
	private int y;

	public Coordinate(int x, int y) throws InvalidCoordinateException {
		if (!isValidValue(x) || !isValidValue(y)) {
			throw new InvalidCoordinateException();
		}
		this.x = x;
		this.y = y;
	}

	private boolean isValidValue(int value) {
		return value < BoardState.MAX_BOARD_SIZE && value > -1;
	}

	public void setXAndY(int x, int y) {
		// ignore garbage values
		if (isValidValue(x) && isValidValue(y)) {
			this.x = x;
			this.y = y;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Coordinate) {
			return false;
		}
		Coordinate other = (Coordinate) obj;
		return this.x == other.getX() && this.y == other.getY();
	}
	
	public int hashCode() {
		return Integer.hashCode(this.x) + Integer.hashCode(this.y);
	}
}
