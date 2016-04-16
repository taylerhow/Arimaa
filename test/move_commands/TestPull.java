package move_commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import game.BoardState;
import game.Coordinate;
import game.Game;
import piece.AbstractPiece;
import piece.Camel;
import piece.Cat;
import piece.Dog;
import piece.Elephant;
import piece.Owner;
import piece.Rabbit;

public class TestPull {
	private Game pullingGame;

	@Before
	public void setup() {
		HashMap<Coordinate, AbstractPiece> pullp = new HashMap<Coordinate, AbstractPiece>();
		pullp.put(new Coordinate(0, 7), new Elephant(Owner.Player1));
		pullp.put(new Coordinate(1, 7), new Rabbit(Owner.Player2));
		pullp.put(new Coordinate(3, 7), new Rabbit(Owner.Player1));
		pullp.put(new Coordinate(4, 7), new Elephant(Owner.Player2));
		pullp.put(new Coordinate(0, 6), new Rabbit(Owner.Player2));
		pullp.put(new Coordinate(1, 6), new Elephant(Owner.Player2));
		pullp.put(new Coordinate(3, 6), new Elephant(Owner.Player1));
		pullp.put(new Coordinate(4, 6), new Elephant(Owner.Player2));
		pullp.put(new Coordinate(5, 6), new Rabbit(Owner.Player2));
		pullp.put(new Coordinate(5, 5), new Elephant(Owner.Player1));
		pullp.put(new Coordinate(6, 5), new Rabbit(Owner.Player2));
		pullp.put(new Coordinate(3, 4), new Rabbit(Owner.Player2));
		pullp.put(new Coordinate(4, 4), new Elephant(Owner.Player1));
		pullp.put(new Coordinate(6, 4), new Elephant(Owner.Player1));
		pullp.put(new Coordinate(7, 4), new Rabbit(Owner.Player2));
		pullp.put(new Coordinate(2, 3), new Rabbit(Owner.Player2));
		pullp.put(new Coordinate(5, 3), new Elephant(Owner.Player1));
		pullp.put(new Coordinate(1, 2), new Elephant(Owner.Player1));
		pullp.put(new Coordinate(2, 2), new Elephant(Owner.Player1));
		pullp.put(new Coordinate(3, 2), new Rabbit(Owner.Player2));
		pullp.put(new Coordinate(5, 2), new Rabbit(Owner.Player2));
		pullp.put(new Coordinate(6, 2), new Rabbit(Owner.Player2));
		pullp.put(new Coordinate(0, 1), new Rabbit(Owner.Player2));
		pullp.put(new Coordinate(1, 1), new Elephant(Owner.Player1));
		pullp.put(new Coordinate(2, 1), new Elephant(Owner.Player1));
		pullp.put(new Coordinate(4, 1), new Elephant(Owner.Player1));
		pullp.put(new Coordinate(6, 1), new Elephant(Owner.Player1));
		pullp.put(new Coordinate(7, 1), new Rabbit(Owner.Player1));
		pullp.put(new Coordinate(1, 0), new Rabbit(Owner.Player2));
		pullp.put(new Coordinate(6, 0), new Rabbit(Owner.Player1));
		pullp.put(new Coordinate(7, 0), new Elephant(Owner.Player2));
		pullingGame = new Game(new BoardState(pullp));
	}

	@Test
	public void testBasicPullUp() {
		assertTrue(pullingGame.pull(5, 5, 6, 5, 0));
		assertEquals(new Rabbit(Owner.Player2), pullingGame.getSpace(5, 5));
		assertEquals(new Elephant(Owner.Player1), pullingGame.getSpace(4, 5));
	}

	@Test
	public void testBasicPullRight() {
		assertTrue(pullingGame.pull(4, 4, 4, 3, 1));
		assertEquals(new Rabbit(Owner.Player2), pullingGame.getSpace(4, 4));
		assertEquals(new Elephant(Owner.Player1), pullingGame.getSpace(4, 5));
	}

	@Test
	public void testBasicPullDown() {
		// pullingGame.currentBoard.printBoard();
		assertTrue(pullingGame.pull(3, 5, 2, 5, 2));
		// pullingGame.currentBoard.printBoard();
		assertEquals(new Rabbit(Owner.Player2), pullingGame.getSpace(3, 5));
		assertEquals(new Elephant(Owner.Player1), pullingGame.getSpace(4, 5));
	}

	@Test
	public void testBasicPullLeft() {
		assertTrue(pullingGame.pull(4, 6, 4, 7, 3));
		assertEquals(new Rabbit(Owner.Player2), pullingGame.getSpace(4, 6));
		assertEquals(new Elephant(Owner.Player1), pullingGame.getSpace(4, 5));
	}

	@Test
	public void testPullWithNullPiece() {
		assertFalse(pullingGame.pull(0, 4, 0, 5, 3));
	}

	@Test
	public void testPullUpOffBoard() {
		assertFalse(pullingGame.pull(0, 7, 1, 7, 0));
	}

	@Test
	public void testPullRightOffBoard() {
		assertFalse(pullingGame.pull(0, 7, 0, 6, 1));
	}

	@Test
	public void testPullDownOffBoard() {
		assertFalse(pullingGame.pull(7, 0, 6, 0, 2));
	}

	@Test
	public void testPullLeftOffBoard() {
		assertFalse(pullingGame.pull(7, 0, 7, 1, 3));
	}

	@Test
	public void testPullUpIntoOccupiedSpace() {
		assertFalse(pullingGame.pull(2, 2, 3, 2, 0));
	}

	@Test
	public void testPullRightIntoOccupiedSpace() {
		assertFalse(pullingGame.pull(1, 1, 1, 0, 1));
	}

	@Test
	public void testPullDownIntoOccupiedSpace() {
		assertFalse(pullingGame.pull(1, 1, 0, 1, 2));
	}

	@Test
	public void testPullLeftIntoOccupiedSpace() {
		assertFalse(pullingGame.pull(2, 2, 2, 3, 3));
	}

	@Test
	public void testPullNothingUp() {
		assertFalse(pullingGame.pull(1, 4, 2, 4, 0));
	}

	@Test
	public void testPullNothingRight() {
		assertFalse(pullingGame.pull(1, 4, 1, 3, 1));
	}

	@Test
	public void testPullNothingDown() {
		assertFalse(pullingGame.pull(1, 4, 0, 4, 2));
	}

	@Test
	public void testPullNothingLeft() {
		assertFalse(pullingGame.pull(1, 4, 1, 5, 3));
	}

	@Test
	public void testPullOwnPieceUp() {
		assertFalse(pullingGame.pull(6, 1, 7, 1, 0));
		assertFalse(pullingGame.checkCoor(5, 1));
	}

	@Test
	public void testPullOwnPieceRight() {
		assertFalse(pullingGame.pull(6, 1, 6, 0, 1));
		assertFalse(pullingGame.checkCoor(6, 2));
	}

	@Test
	public void testPullOwnPieceDown() {
		// pullingGame.currentBoard.printBoard();
		assertFalse(pullingGame.pull(1, 6, 0, 6, 2));
		// pullingGame.currentBoard.printBoard();
		assertTrue(pullingGame.checkCoor(2, 6));
	}

	@Test
	public void testPullOwnPieceLeft() {
		assertFalse(pullingGame.pull(1, 6, 1, 7, 3));
		assertFalse(pullingGame.checkCoor(1, 5));
	}

	@Test
	public void testBidirectionalPullUpRight() {
		AbstractPiece p1 = pullingGame.getSpace(4, 4);
		AbstractPiece p2 = pullingGame.getSpace(4, 3);
		assertTrue(pullingGame.pull(4, 4, 4, 3, 0));
		assertEquals(p1, pullingGame.getSpace(3, 4));
		assertEquals(p2, pullingGame.getSpace(4, 4));
	}

	@Test
	public void testBidirectionalPullRightDown() {
		AbstractPiece p1 = pullingGame.getSpace(3, 5);
		AbstractPiece p2 = pullingGame.getSpace(2, 5);
		assertTrue(pullingGame.pull(3, 5, 2, 5, 1));
		assertEquals(p1, pullingGame.getSpace(3, 6));
		assertEquals(p2, pullingGame.getSpace(3, 5));
	}

	@Test
	public void testBidirectionalPullDownLeft() {
		AbstractPiece p1 = pullingGame.getSpace(4, 6);
		AbstractPiece p2 = pullingGame.getSpace(4, 7);
		// pullingGame.currentBoard.printBoard();
		assertTrue(pullingGame.pull(4, 6, 4, 7, 0));
		// pullingGame.currentBoard.printBoard();
		assertEquals(p1, pullingGame.getSpace(3, 6));
		assertEquals(p2, pullingGame.getSpace(4, 6));
	}

	@Test
	public void testBidirectionalPullLeftUp() {
		// pullingGame.currentBoard.printBoard();
		AbstractPiece p1 = pullingGame.getSpace(5, 5);
		AbstractPiece p2 = pullingGame.getSpace(6, 5);
		assertTrue(pullingGame.pull(5, 5, 6, 5, 3));
		// pullingGame.currentBoard.printBoard();
		assertEquals(p1, pullingGame.getSpace(5, 4));
		assertEquals(p2, pullingGame.getSpace(5, 5));
	}

	@Test
	public void testPullPieceOfGreaterStrength() {
		assertFalse(pullingGame.pull(7, 3, 7, 4, 3));
	}

	@Test
	public void testCantPushPullWith1Move() {
		HashMap<Coordinate, AbstractPiece> pushPullP = new HashMap<Coordinate, AbstractPiece>();
		pushPullP.put(new Coordinate(6, 7), new Cat(Owner.Player1));
		pushPullP.put(new Coordinate(3, 6), new Dog(Owner.Player1));
		pushPullP.put(new Coordinate(4, 5), new Rabbit(Owner.Player2));
		pushPullP.put(new Coordinate(3, 4), new Camel(Owner.Player1));
		pushPullP.put(new Coordinate(4, 4), new Elephant(Owner.Player1));
		pushPullP.put(new Coordinate(5, 4), new Dog(Owner.Player2));
		pushPullP.put(new Coordinate(4, 3), new Cat(Owner.Player2));
		pushPullP.put(new Coordinate(2, 2), new Camel(Owner.Player1));
		pushPullP.put(new Coordinate(5, 2), new Camel(Owner.Player1));
		pushPullP.put(new Coordinate(1, 1), new Camel(Owner.Player1));
		pushPullP.put(new Coordinate(3, 1), new Rabbit(Owner.Player2));
		pushPullP.put(new Coordinate(7, 1), new Dog(Owner.Player1));
		Game game = new Game(new BoardState(pushPullP));

		game.setPlayerTurn(1);
		assertTrue(game.move(1, 7, 3));
		assertTrue(game.move(1, 6, 3));
		assertTrue(game.move(1, 5, 3));
		assertFalse(game.pull(1, 4, 1, 3, 1));
	}
}
