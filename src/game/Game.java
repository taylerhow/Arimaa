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
		//System.out.println(x+" "+ y+"  "+x2+" "+y2);
		char[][] boardArray = currentBoard.getBoardArray();
//		System.out.println(Arrays.deepToString(boardArray));
		char temp = boardArray[row1][column1];
		//System.out.println(temp+" "	+boardArray[y][x]+" "+boardArray[y2][x2]);
		boardArray[row1][column1]=boardArray[row2][column2];
		boardArray[row2][column2]=temp;

//		System.out.println(Arrays.deepToString(boardArray));
		//System.out.println("SWITCH "+temp+" "	+boardArray[x][y]+" "+boardArray[x2][y2]);
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
		//System.out.println(Arrays.deepToString(this.currentBoard.getBoardArray()));
		if (getSpace(row, column) == null){
			return false; // trying to push with an empty square
		}
		switch (dir1) {
		case 0:
//			 System.out.println("Case 0");
			if (move(row - 1, column, dir2)) {
				// System.out.println("("+row+","+y+") trying move "+dir2);
				// should always be true
				boolean move = move(row, column, dir1);
				// System.out.println(move);
				// System.out.println(Arrays.deepToString(this.currentBoard.getBoardArray()));
				return move;
			}
			break;
		case 1:
//			 System.out.println("Case 1");
			if (move(row, column + 1, dir2)) {
				// System.out.println("("+row+","+y+"trying move "+dir2);
				// should always be true
				boolean move = move(row, column, dir1);
				// System.out.println(move);
				return move;
			}
			break;
		case 2:
//			System.out.println("Case 2");
			if (move(row + 1, column, dir2)) {
				// System.out.println("("+row+","+column+"trying move "+dir2);
				// should always be true
				boolean move = move(row, column, dir1);
				// System.out.println(move);
				return move;
			}
			break;
		case 3:
//			 System.out.println("Case 3");
			if (move(row, column - 1, dir2)) {
				// System.out.println("("+row+","+column+") trying move "+dir2);
				// should always be true
				boolean move = move(row, column, dir1);
				// System.out.println(move);
				return move;
			}
			break;
		}
		return false;
	}
	
	/**
	 * 0: up, 1: right, 2: down, 3: left
	 * @param row: row that contains the pulling piece
	 * @param column: column that contains the pulling piece
	 * @param direction: direction the pulling piece will move
	 * @return True if pull succeeds, False if it fails
	 */
	//Still need to test piece strength comparison
	public boolean pull(int row, int column, int direction){
		//There's no piece here to pull with, nimrod
		if(getSpace(row, column)==null) return false;
		
		//Congratulations, you selected a valid piece!
		switch(direction){
		case 0:
			if (getSpace(row+1, column)!=null && getSpace(row, column).getOwner() != getSpace(row+1, column).getOwner() && move(row, column, direction)) {
				move(row + 1, column, direction);
				return true;
			}
			break;
		case 1:
			if (getSpace(row, column-1)!=null && getSpace(row, column).getOwner() != getSpace(row, column-1).getOwner() && move(row, column, direction)) {
				move(row, column - 1, direction);
				return true;
			}
			break;
		case 2:
			if(getSpace(row-1, column)!=null && getSpace(row, column).getOwner() != getSpace(row-1, column).getOwner() && move(row, column, direction)){
				move(row - 1, column, direction);
				return true;
			}
			break;
		case 3:
			if (getSpace(row, column+1)!=null && getSpace(row, column).getOwner() != getSpace(row, column+1).getOwner() && move(row, column, direction)) {
				move(row, column + 1, direction);
				return true;
			}
			break;
		}
		return false;
	}
}
