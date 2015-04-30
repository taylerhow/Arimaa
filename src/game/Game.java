package game;
import java.util.ArrayList;
import java.util.Arrays;


public class Game {
	//fields
	ArrayList<BoardState> boards= new ArrayList<BoardState>();
	public BoardState currentBoard=null;
	int numMovesLeft =0;
	int moveTimer =0;
	int p1TimeBank=0;
	int p2TimeBank =0;
	
	public Game(BoardState b) {
		currentBoard = b;
	}
	/**
	 * Creates a board with a default starting layout
	 */
	public Game(){
		currentBoard=new BoardState(new char[][]{
				{'K','D','H','C','E','H','D','K'},
				{'R','R','R','R','R','R','R','R'},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{'r','r','r','r','r','r','r','r'},
				{'k','d','h','c','e','h','d','k'},
		},0);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Piece getSpace(int row, int column){
		if(currentBoard.getBoardArray()[row][column]==' ')
			return null;
		return new Piece(currentBoard.getBoardArray()[row][column]);
		
	}
	/**
	 * 
	 * @param row
	 * @param column
	 * @param dir 0: up, 1: right, 2: down, 3: left
	 * @return
	 */
	public boolean move(int row, int column, int dir){
		if(getSpace(row,column) == null) return false;
		boards.add(currentBoard);
		currentBoard=currentBoard.clone();
		switch(dir){
		case 0:
			//Moving UP
			if (row - 1 >= 0 && currentBoard.getBoardArray()[row-1][column] == ' ') {
				switchPiece(row, column, row-1, column);
				return true;
			}
			return false;
		case 1:
			//Moving RIGHT
			if (column + 1 <= 7 && currentBoard.getBoardArray()[row][column+1] == ' ') {
				switchPiece(row, column, row, column+1);
				return true;
			}
			return false;
		case 2:
			//Moving DOWN
			if (row + 1 <= 7 && currentBoard.getBoardArray()[row+1][column] == ' ') {
				switchPiece(row, column, row + 1, column);
				return true;
			}
			return false;
		case 3:
			//Moving LEFT
			if (column - 1 >= 0 && currentBoard.getBoardArray()[row][column-1] == ' ') {
				switchPiece(row, column, row, column - 1);
				return true;
			}
			return false;
		default:
			return false;
		}
	}
	//helper for move
	private void switchPiece(int row1, int column1, int row2, int column2) {
		char[][] boardArray = currentBoard.getBoardArray();
		char temp = boardArray[row1][column1];

		boardArray[row1][column1]=boardArray[row2][column2];
		boardArray[row2][column2]=temp;

		currentBoard.setBoardArray(boardArray);
	}
	/**
	 * 0: up, 1: right, 2: down, 3: left
	 * @param row
	 * @param column
	 * @param dir1 the direction the pushing piece will move
	 * @param dir2 the direction the pushed piece will move
	 * @return
	 */
	public boolean push(int row, int column, int dir1, int dir2){
		if (getSpace(row, column) == null){
			return false; // trying to push with an empty square
		}
		switch (dir1) {
		case 0:
			Piece pushingPiece = getSpace(row,column);
			Piece pushedPiece = getSpace(row - 1, column);
			if (pushingPiece.isStrongerThan(pushedPiece)) {
				if (row - 1 >= 0) {
					if (getSpace(row, column).getOwner() != getSpace(row - 1,column).getOwner()
							&& move(row - 1, column, dir2)) {
						// should always be true
						return move(row, column, dir1);
					}
				}
			}
			break;
		case 1:
			if (column + 1 <= 7) {
				if (getSpace(row, column).getOwner() != getSpace(row,column + 1).getOwner()
						&& move(row, column + 1, dir2)) {
					return move(row, column, dir1);
				}
			}
			break;
		case 2:
			if (row + 1 <= 7) {
				if (getSpace(row, column).getOwner() != getSpace(row + 1,column).getOwner()
						&& move(row + 1, column, dir2)) {
					// should always be true
					return move(row, column, dir1);
				}
			}
			break;
		case 3:
			if (column - 1 >= 0) {
				if (getSpace(row, column).getOwner() != getSpace(row,column - 1).getOwner()
						&& move(row, column - 1, dir2)) {
					// should always be true
					return move(row, column, dir1);
				}
			}
			break;
		}
		return false;
	}
	
	/**
	 * 0: up, 1: right, 2: down, 3: left
	 * @param row: row that contains the pulling piece
	 * @param column: column that contains the pulling piece
	 * @param direction1: direction the pulling piece will move
	 * @param direction2: direction the piece being pulled will move
	 * @return True if pull succeeds, False if it fails
	 */
	//Still need to test piece strength comparison
	public boolean pull(int row1, int column1, int row2, int column2, int direction1){
		//There's no piece here to pull with, nimrod
		if(getSpace(row1, column1)==null || getSpace(row2, column2)==null) return false;
		
		//Get direction that pulled piece will move
		int direction2 = getDirection(row2, column2, row1, column1);
		if(direction2 == -1) return false;
		
		//Attempt to perform move operations on pulling
		switch(direction1){
		case 0:
			if (getSpace(row1, column1).getOwner() != getSpace(row2, column2).getOwner() && move(row1, column1, direction1)) {
				move(row2, column2, direction2);
				return true;
			}
			break;
		case 1:
			if (getSpace(row1, column1).getOwner() != getSpace(row2, column2).getOwner() && move(row1, column1, direction1)) {
				move(row2, column2, direction2);
				return true;
			}
			break;
		case 2:
			if(getSpace(row1, column1).getOwner() != getSpace(row2, column2).getOwner() && move(row1, column1, direction1)){
				move(row2, column2, direction2);
				return true;
			}
			break;
		case 3:
			if (getSpace(row1, column1).getOwner() != getSpace(row2, column2).getOwner() && move(row1, column1, direction1)) {
				move(row2, column2, direction2);
				return true;
			}
			break;
		}
		return false;
	}
	
	/**
	 * 0: up, 1: right, 2: down, 3: left
	 * @param row1: row of space1
	 * @param column1: column of space1
	 * @param row2: row of space2
	 * @param column2: column of space2
	 * @return integer representing the direction required to move from space1 to space2
	 */
	public int getDirection(int row1, int column1, int row2, int column2){
		if(row1 == row2){
			if(column1 - 1 == column2) return 3;
			if(column1 + 1 == column2) return 1;
		}
		if(column1 == column2){
			if(row1 - 1 == row2) return 0;
			if(row1 + 1 == row2) return 2;
		}
		return -1;
	}
}
