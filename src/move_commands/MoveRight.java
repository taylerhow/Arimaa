package move_commands;

import game.BoardState;

public class MoveRight implements MoveCommand {
	private BoardState newBoard;
	private BoardState originalBoard;

	public MoveRight(BoardState board) {
		this.originalBoard = board.clone();
		this.newBoard = board;
	}

	@Override
	public BoardState execute(int row, int col) {
		char[][] boardArray = newBoard.getBoardArray();
		char temp = boardArray[row][col];
	
		boardArray[row][col] = boardArray[row][col + 1];
		boardArray[row][col + 1] = temp;
	
		newBoard.setBoardArray(boardArray);
		return newBoard;

	}

	@Override
	public BoardState getOriginalBoard() {
		return this.originalBoard;
	}
	
	@Override
	public boolean isValidMove(int row, int column) {
		return MoveCommand.validMove(originalBoard, row, column + 1);
	}


}