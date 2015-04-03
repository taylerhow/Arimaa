package game;
import game.Piece.PieceType;

import java.util.ArrayList;


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
		case 1:
			switchPiece(x,y,x+1,y);
		case 2:
			switchPiece(x,y,x,y+1);
		case 3:
			switchPiece(x,y,x+1,y);
		default:
			return false;
		}
	}
	//helper for move
	private void switchPiece(int x, int y, int x2, int i) {
		char[][] boardArray = currentBoard.getBoardArray();
		char temp = boardArray[x][y];
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
