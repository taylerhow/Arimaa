package game;
import game.Piece.PieceType;

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
	 */
	public void render(){}
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Piece getSpace(int x, int y){
		if(currentBoard.getBoardArray()[x][y]==' ')
			return null;
		return new Piece(currentBoard.getBoardArray()[x][y]);
		
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
			if (row - 1 >= 0 && currentBoard.getBoardArray()[row-1][column] == '_') {
				switchPiece(row, column, row-1, column);
				return true;
			}
			return false;
		case 1:
			//Moving RIGHT
			if (column + 1 <= 7 && currentBoard.getBoardArray()[row][column+1] == '_') {
				switchPiece(row, column, row, column+1);
				return true;
			}
			return false;
		case 2:
			//Moving DOWN
			if (row + 1 <= 7) {
				switchPiece(row, column, row + 1, column);
				return true;
			}
			return false;
		case 3:
			//Moving LEFT
			if (column - 1 >= 0 && currentBoard.getBoardArray()[row][column-1] == '_') {
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
	 * 
	 * @param x
	 * @param y
	 * @param dir1
	 * @param dir2
	 * @return
	 */
	public boolean push(int x, int y, int dir1, int dir2){
		return false;
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @param dir1
	 * @param dir2
	 * @return
	 */
	public boolean pull(int x, int y, int dir1, int dir2){
		return false;
	}
}
