package game;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import piece.AbstractPiece;
import piece.Camel;
import piece.Cat;
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
	@Deprecated
	private char[][] boardArray = new char[8][8]; // Represents the current state of the board
	private HashMap<Coordinate, AbstractPiece> pieces;

	public BoardState() {
		this.pieces = new HashMap<Coordinate, AbstractPiece>();

		this.pieces.put(new Coordinate(0, 0), new Cat(Owner.Player1));
		this.pieces.put(new Coordinate(1, 0), new Dog(Owner.Player1));
		this.pieces.put(new Coordinate(2, 0), new Horse(Owner.Player1));
		this.pieces.put(new Coordinate(3, 0), new Camel(Owner.Player1));
		this.pieces.put(new Coordinate(4, 0), new Elephant(Owner.Player1));
		this.pieces.put(new Coordinate(5, 0), new Horse(Owner.Player1));
		this.pieces.put(new Coordinate(6, 0), new Dog(Owner.Player1));
		this.pieces.put(new Coordinate(7, 0), new Cat(Owner.Player1));

		this.pieces.put(new Coordinate(0, 1), new Rabbit(Owner.Player1));
		this.pieces.put(new Coordinate(1, 1), new Rabbit(Owner.Player1));
		this.pieces.put(new Coordinate(2, 1), new Rabbit(Owner.Player1));
		this.pieces.put(new Coordinate(3, 1), new Rabbit(Owner.Player1));
		this.pieces.put(new Coordinate(4, 1), new Rabbit(Owner.Player1));
		this.pieces.put(new Coordinate(5, 1), new Rabbit(Owner.Player1));
		this.pieces.put(new Coordinate(6, 1), new Rabbit(Owner.Player1));
		this.pieces.put(new Coordinate(7, 1), new Rabbit(Owner.Player1));

		this.pieces.put(new Coordinate(0, 7), new Cat(Owner.Player2));
		this.pieces.put(new Coordinate(1, 7), new Dog(Owner.Player2));
		this.pieces.put(new Coordinate(2, 7), new Horse(Owner.Player2));
		this.pieces.put(new Coordinate(3, 7), new Camel(Owner.Player2));
		this.pieces.put(new Coordinate(4, 7), new Elephant(Owner.Player2));
		this.pieces.put(new Coordinate(5, 7), new Horse(Owner.Player2));
		this.pieces.put(new Coordinate(6, 7), new Dog(Owner.Player2));
		this.pieces.put(new Coordinate(7, 7), new Cat(Owner.Player2));

		this.pieces.put(new Coordinate(0, 6), new Rabbit(Owner.Player2));
		this.pieces.put(new Coordinate(1, 6), new Rabbit(Owner.Player2));
		this.pieces.put(new Coordinate(2, 6), new Rabbit(Owner.Player2));
		this.pieces.put(new Coordinate(3, 6), new Rabbit(Owner.Player2));
		this.pieces.put(new Coordinate(4, 6), new Rabbit(Owner.Player2));
		this.pieces.put(new Coordinate(5, 6), new Rabbit(Owner.Player2));
		this.pieces.put(new Coordinate(6, 6), new Rabbit(Owner.Player2));
		this.pieces.put(new Coordinate(7, 6), new Rabbit(Owner.Player2));
	}

	// maybe make this private?
	public BoardState(HashMap<Coordinate, AbstractPiece> pieces) {
		this.pieces = pieces;
	}

	// converter
	@Deprecated
	public BoardState(char[][] chars, int turns) {
		System.out.println();
		System.out.println("****************");
		System.out.println();
		int randId = new Random().nextInt(1000);
		System.out.println(
				"HashMap<Coordinate, AbstractPiece> p" + randId + " = new HashMap<Coordinate, AbstractPiece>();");
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				char piece = chars[7-y][x];
				if (piece != ' ') {
					String player = Character.isUpperCase(piece) ? "Owner.Player1" : "Owner.Player2";
					piece = Character.toLowerCase(piece);
					String clazz = "";
					switch (piece) {
					case 'r':
						clazz = "Rabbit";
						break;
					case 'k':
						clazz = "Cat";
						break;
					case 'd':
						clazz = "Dog";
						break;
					case 'h':
						clazz = "Horse";
						break;
					case 'c':
						clazz = "Camel";
						break;
					case 'e':
						clazz = "Elephant";
						break;
					}
					System.out.println("p" + randId + ".put(new Coordinate(" + x + ", " + y + "), new " + clazz + "("
							+ player + "));");
				}
			}
			throw new RuntimeException("using the wrong constuctor");
		}
	}
	
	public Set<Coordinate> getAllCoordinates() {
		return this.pieces.keySet();
	}

	@Deprecated
	public char[][] getBoardArray() {
		return boardArray;
	}

	// this method will only move the piece if there is a piece at the old coor, and no piece at the new coor
	// it will return true if it made a move and false if it did not
	public boolean movePiece(Coordinate oldCoor, Coordinate newCoor) {
		if (oldCoor.isValid() && newCoor.isValid() && this.pieceAt(oldCoor) && !this.pieceAt(newCoor)) {
			this.pieces.put(newCoor, this.getPieceAt(oldCoor));
			this.pieces.remove(oldCoor);
			return true;
		}
		return false;
	}

	public boolean pieceAt(Coordinate coor) {
		if (!coor.isValid()) {
			return false;
		}
		return this.pieces.containsKey(coor);
	}

	public AbstractPiece getPieceAt(Coordinate coor) {
		if (!coor.isValid()) {
			return null;
		}
		return this.pieces.get(coor);
	}

	public boolean removePiece(Coordinate coor) {
		if (coor.isValid()) {
			return this.pieces.remove(coor) != null;
		}
		return false;
	}

	@Override
	public BoardState clone() {
		HashMap<Coordinate, AbstractPiece> copiedPieces = new HashMap<Coordinate, AbstractPiece>();
		for (Coordinate key : this.pieces.keySet()) {
			copiedPieces.put(new Coordinate(key), this.pieces.get(key));
		}
		return new BoardState(copiedPieces);
	}
}
