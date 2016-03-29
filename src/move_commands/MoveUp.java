package move_commands;

import game.BoardState;

public class MoveUp implements MoveCommand {
	private BoardState newBoard;
	private BoardState originalBoard;
	
	public MoveUp(BoardState board) {
		this.originalBoard = board.clone();
		this.newBoard = board;
	}
	
	@Override
	public BoardState execute(int row, int col) {
		char[][] boardArray = newBoard.getBoardArray();
		char temp = boardArray[row][col];
	
		boardArray[row][col] = boardArray[row - 1][col];
		boardArray[row - 1][col] = temp;
	
		newBoard.setBoardArray(boardArray);
		return newBoard;
	}

	@Override
	public BoardState getOriginalBoard() {
		return this.originalBoard;
	}

	@Override
	public boolean isValidMove(int row, int column) {
		return MoveCommand.validMove(originalBoard, row - 1, column);
	}

}
