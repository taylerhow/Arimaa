package game;

import java.util.HashMap;

import piece.AbstractPiece;
import piece.Camel;
import piece.Cat;
import piece.Coordinate;
import piece.Dog;
import piece.Elephant;
import piece.Horse;
import piece.Owner;
import piece.Rabbit;

/**
 * This class represents the states of the board.
 * 
 * @author shellajt
 *
 */
public class BoardState {
	// Fields
	public static final int MAX_BOARD_SIZE = 8;
	private char[][] boardArray = new char[8][8]; // Represents the current state of the board
	private HashMap<Coordinate, AbstractPiece> pieces;

	public BoardState() {
		this.pieces = new HashMap<Coordinate, AbstractPiece>();
		
		this.pieces.put(new Coordinate(0, 0), new Cat(Owner.Player1, new Coordinate(0, 0)));
		this.pieces.put(new Coordinate(1, 0), new Dog(Owner.Player1, new Coordinate(1, 0)));
		this.pieces.put(new Coordinate(2, 0), new Horse(Owner.Player1, new Coordinate(2, 0)));
		this.pieces.put(new Coordinate(3, 0), new Camel(Owner.Player1, new Coordinate(3, 0)));
		this.pieces.put(new Coordinate(4, 0), new Elephant(Owner.Player1, new Coordinate(4, 0)));
		this.pieces.put(new Coordinate(5, 0), new Horse(Owner.Player1, new Coordinate(5, 0)));
		this.pieces.put(new Coordinate(6, 0), new Dog(Owner.Player1, new Coordinate(6, 0)));
		this.pieces.put(new Coordinate(7, 0), new Cat(Owner.Player1, new Coordinate(7, 0)));
		
		this.pieces.put(new Coordinate(0, 1), new Rabbit(Owner.Player1, new Coordinate(0, 1)));
		this.pieces.put(new Coordinate(1, 1), new Rabbit(Owner.Player1, new Coordinate(1, 1)));
		this.pieces.put(new Coordinate(2, 1), new Rabbit(Owner.Player1, new Coordinate(2, 1)));
		this.pieces.put(new Coordinate(3, 1), new Rabbit(Owner.Player1, new Coordinate(3, 1)));
		this.pieces.put(new Coordinate(4, 1), new Rabbit(Owner.Player1, new Coordinate(4, 1)));
		this.pieces.put(new Coordinate(5, 1), new Rabbit(Owner.Player1, new Coordinate(5, 1)));
		this.pieces.put(new Coordinate(6, 1), new Rabbit(Owner.Player1, new Coordinate(6, 1)));
		this.pieces.put(new Coordinate(7, 1), new Rabbit(Owner.Player1, new Coordinate(7, 1)));

		this.pieces.put(new Coordinate(0, 7), new Cat(Owner.Player2, new Coordinate(0, 7)));
		this.pieces.put(new Coordinate(1, 7), new Dog(Owner.Player2, new Coordinate(1, 7)));
		this.pieces.put(new Coordinate(2, 7), new Horse(Owner.Player2, new Coordinate(2, 7)));
		this.pieces.put(new Coordinate(3, 7), new Camel(Owner.Player2, new Coordinate(3, 7)));
		this.pieces.put(new Coordinate(4, 7), new Elephant(Owner.Player2, new Coordinate(4, 7)));
		this.pieces.put(new Coordinate(5, 7), new Horse(Owner.Player2, new Coordinate(5, 7)));
		this.pieces.put(new Coordinate(6, 7), new Dog(Owner.Player2, new Coordinate(6, 7)));
		this.pieces.put(new Coordinate(7, 7), new Cat(Owner.Player2, new Coordinate(7, 7)));
		
		this.pieces.put(new Coordinate(0, 6), new Rabbit(Owner.Player2, new Coordinate(0, 6)));
		this.pieces.put(new Coordinate(1, 6), new Rabbit(Owner.Player2, new Coordinate(1, 6)));
		this.pieces.put(new Coordinate(2, 6), new Rabbit(Owner.Player2, new Coordinate(2, 6)));
		this.pieces.put(new Coordinate(3, 6), new Rabbit(Owner.Player2, new Coordinate(3, 6)));
		this.pieces.put(new Coordinate(4, 6), new Rabbit(Owner.Player2, new Coordinate(4, 6)));
		this.pieces.put(new Coordinate(5, 6), new Rabbit(Owner.Player2, new Coordinate(5, 6)));
		this.pieces.put(new Coordinate(6, 6), new Rabbit(Owner.Player2, new Coordinate(6, 6)));
		this.pieces.put(new Coordinate(7, 6), new Rabbit(Owner.Player2, new Coordinate(7, 6)));
}

	// maybe make this private?
	public BoardState(HashMap<Coordinate, AbstractPiece> pieces) {
		// for(int i=0; i<8; i++){
		// for(int k=0; k<8; k++){
		// this.boardArray[i][k] = map[i][k];
		// }
		// }
		this.pieces = pieces;
	}

	public char[][] getBoardArray() {
		return boardArray;
	}

//	public void setBoardArray(char[][] boardArray) {
//		this.boardArray = boardArray;
//	}
//
//	public void setBoardSpace(int row, int column, String piece) {
//		this.boardArray[row][column] = piece.charAt(0);
//	}
	
	public boolean pieceAt(int row, int column) {
		return this.pieces.containsKey(new Coordinate(row, column));
	}

	public AbstractPiece getPieceAt(int row, int column) {
		return this.pieces.get(new Coordinate(row, column));
	}

	@Override
	public BoardState clone() {
		// return new BoardState(boardArray, turnNumber);
		HashMap<Coordinate, AbstractPiece> copiedPieces = new HashMap<Coordinate, AbstractPiece>();
		for (Coordinate key : this.pieces.keySet()) {
			copiedPieces.put(key, this.pieces.get(key).clone());
		}
		return new BoardState(copiedPieces);
	}
}
