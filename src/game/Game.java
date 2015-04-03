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
		if(x==0){
			return null;
		}
		return new Piece(PieceType.Camel,null);
		
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @param dir 0: up, 1: right, 2: down, 3: left
	 * @return
	 */
	public boolean move(int x, int y, int dir){
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
