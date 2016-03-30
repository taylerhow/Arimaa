package move_commands;

import game.BoardState;

public interface MoveCommand {

	public BoardState execute(int row, int col);
	
	public BoardState getOriginalBoard();
	
	public boolean isValidMove(int row, int column);
	
	public static boolean validMove(BoardState originalBoard, int row, int column) {
		if (row >= 0 && row < 8 && column >= 0 && column < 8
				&& originalBoard.getBoardArray()[row][column] == ' ')
			return true;
		return false;
	}
	
}
