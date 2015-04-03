package game;
/**
 * This class represents the states of the board.
 * 
 * @author shellajt
 *
 */
public class BoardState {
	//Fields
	private char[][] boardArray; //Represents the current state of the board
	private int turnNumber;
	
	public BoardState(char[][] map, int turnNumber){
		this.boardArray = map;
		this.turnNumber = turnNumber;
	}

	public char[][] getBoardArray() {
		return boardArray;
	}

	public void setBoardArray(char[][] boardArray) {
		this.boardArray = boardArray;
	}

	public int getTurnNumber() {
		return turnNumber;
	}

	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}
	
	public void incrementTurn(){
		this.turnNumber++;
	}
}
