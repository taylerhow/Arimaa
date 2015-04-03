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
	 * @param x
	 * @param y
	 * @param dir 0: up, 1: right, 2: down, 3: left
	 * @return
	 */
	public boolean move(int x, int y, int dir){
		switch(dir){
		case 0:
			switchPiece(x,y,x,y-1);
			return true;
		case 1:
			switchPiece(x,y,x+1,y);
			return true;
		case 2:
			switchPiece(x,y,x,y+1);
			return true;
		case 3:
			switchPiece(x,y,x+1,y);
			return true;
		default:
			return false;
		}
	}
	//helper for move
	private void switchPiece(int x, int y, int x2, int y2) {
		//System.out.println(x+" "+ y+"  "+x2+" "+y2);
		char[][] boardArray = currentBoard.getBoardArray();
		//System.out.println(Arrays.deepToString(boardArray));
		char temp = boardArray[y][x];
		//System.out.println(temp+" "	+boardArray[y][x]+" "+boardArray[y2][x2]);
		boardArray[y][x]=boardArray[y2][x2];
		boardArray[y2][x2]=temp;

		//System.out.println(Arrays.deepToString(boardArray));
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
