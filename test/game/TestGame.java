package game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import piece.AbstractPiece;
import piece.Camel;
import piece.Cat;
import piece.Dog;
import piece.Elephant;
import piece.Owner;
import piece.Rabbit;

public class TestGame {
	private Game g;
	private Game g1;

	@Before
	public void setup() {
		g = new Game();

		HashMap<Coordinate, AbstractPiece> p1 = new HashMap<Coordinate, AbstractPiece>();
		p1.put(new Coordinate(6, 7), new Cat(Owner.Player1));
		p1.put(new Coordinate(7, 7), new Rabbit(Owner.Player1));
		p1.put(new Coordinate(3, 6), new Dog(Owner.Player1));
		p1.put(new Coordinate(3, 4), new Camel(Owner.Player1));
		p1.put(new Coordinate(4, 4), new Elephant(Owner.Player1));
		BoardState b1 = new BoardState(p1);
		g1 = new Game(b1);
	}

	@Test
	public void testInitializes() {
		Game g = new Game(null);
		assertNotNull(g);
	}

	@Test
	public void testInitializesWithBoardState() {
		assertEquals(new Camel(Owner.Player1), g1.getSpace(3, 4));
		assertEquals(new Elephant(Owner.Player1), g1.getSpace(4, 4));
		assertNull(g1.getSpace(3, 7));
		assertEquals(new Rabbit(Owner.Player1), g1.getSpace(7, 7));
	}

	@Test
	public void testTurnNumberIncrements() {
		g.incrementTurn();
		assertEquals(1, g.getTurnNumber());
		g.incrementTurn();
		g.incrementTurn();
		assertEquals(3, g.getTurnNumber());
	}

	@Test
	public void testSetTurnNumber() {
		g.setTurnNumber(5);
		assertEquals(5, g.getTurnNumber());
	}

	@Test
	public void testGetPieceExists() {
		assertTrue(g1.checkCoor(3, 4));
		assertEquals(new Camel(Owner.Player1), g1.getSpace(3, 4));
	}

	@Test
	public void testGetPieceNotExists() {
		assertFalse(g1.checkCoor(0, 0));
		assertNull(g1.getSpace(0, 0));
	}

	@Test
	@Deprecated
	// what does this even do?
	public void testGetPieceExistsAgain() {
		assertEquals(new Rabbit(Owner.Player1), g1.getSpace(7, 7));
		assertEquals(new Rabbit(Owner.Player1), g1.getSpace(7, 7));
	}

	@Test
	@Deprecated
	public void testGetSpaceInvalidRow1() {
		assertNull(g.getSpace(-1, 0));
	}

	@Test
	@Deprecated
	public void testGetSpaceInvalidRow2() {
		assertNull(g.getSpace(8, 0));
	}

	@Test
	@Deprecated
	public void testGetSpaceInvalidColumn1() {
		assertNull(g.getSpace(0, -1));
	}

	@Test
	@Deprecated
	public void testGetSpaceInvalidColumn2() {
		assertNull(g.getSpace(0, 8));
	}

	// Testing getDirection
	@Test
	public void testGetDirectionUp() {
		assertEquals(0, g.getDirection(1, 1, 0, 1));
	}

	@Test
	public void testGetDirectionRight() {
		assertEquals(1, g.getDirection(1, 1, 1, 2));
	}

	@Test
	public void testGetDirectionDown() {
		assertEquals(2, g.getDirection(1, 1, 2, 1));
	}

	@Test
	public void testGetDirectionLeft() {
		assertEquals(3, g.getDirection(1, 1, 1, 0));
	}

	@Test
	public void testGetDirectionNonAdjacent1() {
		assertEquals(-1, g.getDirection(1, 1, 7, 7));
	}

	@Test
	public void testGetDirectionNonAdjacent2() {
		assertEquals(-1, g.getDirection(1, 1, 1, 7));
	}

	@Test
	public void testGetDirectionNonAdjacent3() {
		assertEquals(-1, g.getDirection(1, 1, 7, 1));
	}

	// Testing remove piece checks
	@Test
	public void testRemovePieceValid() {
		HashMap<Coordinate, AbstractPiece> removeP = new HashMap<Coordinate, AbstractPiece>();
		removeP.put(new Coordinate(5, 7), new Rabbit(Owner.Player2));
		removeP.put(new Coordinate(6, 7), new Cat(Owner.Player1));
		removeP.put(new Coordinate(7, 7), new Rabbit(Owner.Player1));
		removeP.put(new Coordinate(3, 6), new Dog(Owner.Player1));
		removeP.put(new Coordinate(4, 5), new Rabbit(Owner.Player2));
		removeP.put(new Coordinate(3, 4), new Camel(Owner.Player1));
		removeP.put(new Coordinate(4, 4), new Elephant(Owner.Player1));
		removeP.put(new Coordinate(5, 4), new Dog(Owner.Player2));
		removeP.put(new Coordinate(4, 3), new Cat(Owner.Player2));
		removeP.put(new Coordinate(2, 2), new Camel(Owner.Player1));

		Game game = new Game(new BoardState(removeP));
		// g.currentBoard.printBoard();
		assertTrue(game.move(6, 3, 0));
		// g.currentBoard.printBoard();
		assertFalse(game.checkCoor(2, 2));
	}

	@Test
	public void testRemovePiece2() {
		HashMap<Coordinate, AbstractPiece> removeP = new HashMap<Coordinate, AbstractPiece>();
		removeP.put(new Coordinate(5, 7), new Rabbit(Owner.Player2));
		removeP.put(new Coordinate(6, 7), new Cat(Owner.Player1));
		removeP.put(new Coordinate(7, 7), new Rabbit(Owner.Player1));
		removeP.put(new Coordinate(3, 6), new Dog(Owner.Player1));
		removeP.put(new Coordinate(4, 5), new Rabbit(Owner.Player2));
		removeP.put(new Coordinate(3, 4), new Camel(Owner.Player1));
		removeP.put(new Coordinate(4, 4), new Elephant(Owner.Player1));
		removeP.put(new Coordinate(5, 4), new Dog(Owner.Player2));
		removeP.put(new Coordinate(4, 3), new Cat(Owner.Player2));
		removeP.put(new Coordinate(2, 2), new Camel(Owner.Player1));
		removeP.put(new Coordinate(5, 2), new Camel(Owner.Player1));
		removeP.put(new Coordinate(5, 1), new Dog(Owner.Player1));
		Game game = new Game(new BoardState(removeP));
		game.move(2, 2, 0);
		assertEquals(game.getSpace(2, 5), game.getSpace(2, 5));
		game.move(1, 5, 0);
		assertFalse(game.checkCoor(2, 5));
	}
}
