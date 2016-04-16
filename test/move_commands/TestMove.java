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

public class TestMove {
	private Game g;
	private Game freezingGame;

	@Before
	public void setup() {
		g = new Game();

		HashMap<Coordinate, AbstractPiece> fp = new HashMap<Coordinate, AbstractPiece>();
		fp.put(new Coordinate(6, 7), new Cat(Owner.Player1));
		fp.put(new Coordinate(7, 7), new Rabbit(Owner.Player1));
		fp.put(new Coordinate(3, 4), new Rabbit(Owner.Player1));
		fp.put(new Coordinate(4, 3), new Rabbit(Owner.Player1));
		fp.put(new Coordinate(5, 3), new Elephant(Owner.Player1));
		fp.put(new Coordinate(4, 4), new Camel(Owner.Player2));
		BoardState fb = new BoardState(fp);
		freezingGame = new Game(fb);
	}
	
	@Test
	public void testMoveLegal() {
		HashMap<Coordinate, AbstractPiece> p1 = new HashMap<Coordinate, AbstractPiece>();
		p1.put(new Coordinate(6, 7), new Cat(Owner.Player1));
		p1.put(new Coordinate(7, 7), new Rabbit(Owner.Player1));
		p1.put(new Coordinate(3, 6), new Dog(Owner.Player1));
		p1.put(new Coordinate(3, 4), new Camel(Owner.Player1));
		p1.put(new Coordinate(4, 4), new Elephant(Owner.Player1));
		BoardState b1 = new BoardState(p1);
		Game g1 = new Game(b1);

		assertEquals(new Rabbit(Owner.Player1), g1.getSpace(7, 7));
		assertTrue(g1.move(7, 7, 0));
		assertEquals(new Rabbit(Owner.Player1), g1.getSpace(6, 7));
		assertFalse(g1.checkCoor(7, 7));
	}

	@Test
	public void testMoveIllegalNotYourTurn() {
		assertFalse(g.move(7, 7, 2));
	}

	@Test
	public void testMoveIllegalDown() {
		g.setPlayerTurn(2);
		assertFalse(g.move(7, 7, 2));
	}

	@Test
	public void testMoveIllegalUp() {
		assertFalse(g.move(0, 0, 0));

	}

	@Test
	public void testMoveIllegalRight() {
		g.setPlayerTurn(2);
		assertFalse(g.move(7, 7, 1));
	}

	@Test
	public void testMoveIllegalLeft() {
		assertFalse(g.move(0, 0, 3));
	}

	@Test
	public void testCannotMoveUpIntoOccupiedSpace() {
		assertEquals(new Rabbit(Owner.Player1), g.getSpace(1, 0));
		assertFalse(g.move(1, 0, 0));
		assertEquals(new Rabbit(Owner.Player1), g.getSpace(1, 0));
	}

	@Test
	public void testCannotMoveRightIntoOccupiedSpace() {
		assertEquals(new Dog(Owner.Player1), g.getSpace(0, 1));
		assertFalse(g.move(0, 1, 1));
		assertEquals(new Dog(Owner.Player1), g.getSpace(0, 1));
	}

	@Test
	public void testCannotMoveDownIntoOccupiedSpace() {
		assertEquals(new Dog(Owner.Player1), g.getSpace(0, 1));
		assertFalse(g.move(0, 1, 2));
		assertEquals(new Dog(Owner.Player1), g.getSpace(0, 1));
	}

	@Test
	public void testCannotMoveLeftIntoOccupiedSpace() {
		assertEquals(new Dog(Owner.Player1), g.getSpace(0, 1));
		assertFalse(g.move(0, 1, 3));
		assertEquals(new Dog(Owner.Player1), g.getSpace(0, 1));
	}

	@Test
	public void testCannotMoveIfFrozenByStrongerOpposingPiece() {
		assertFalse(freezingGame.move(4, 3, 0));
	}

	@Test
	public void testCanMoveIfFrozenByStrongerOpposingPieceButThawedByFriendlyPiece() {
		assertTrue(freezingGame.move(3, 4, 0));
	}

	@Test
	public void testNullMove() {
		assertFalse(g.move(4, 4, 0));
	}

	@Test
	public void testInvalidMoveDirection() {
		assertFalse(g.move(1, 0, 5));
	}
}
