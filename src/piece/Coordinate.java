package piece;

import game.BoardState;

public class Coordinate {
	private int x;
	private int y;
	private boolean valid = false;

	public Coordinate(int x, int y) {
		if (isValidValue(x) && isValidValue(y)) {
			this.valid  = true;
			this.x = x;
			this.y = y;
		}
	}

	private boolean isValidValue(int value) {
		return value < BoardState.MAX_BOARD_SIZE && value > -1;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public boolean isValid() {
		return this.valid;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Coordinate) {
			Coordinate other = (Coordinate) obj;
			return this.valid && other.isValid() && this.x == other.getX() && this.y == other.getY();
		}
		return false;
	}
	
	public int hashCode() {
		return Integer.hashCode(this.x) + Integer.hashCode(this.y) + Boolean.hashCode(this.valid);
	}
}
