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

public class TestPush {
	private Game g2;
	private Game pushingGame;

	@Before
	public void setup() {
		HashMap<Coordinate, AbstractPiece> p2 = new HashMap<Coordinate, AbstractPiece>();
		p2.put(new Coordinate(6, 7), new Cat(Owner.Player1));
		p2.put(new Coordinate(7, 7), new Rabbit(Owner.Player1));
		p2.put(new Coordinate(3, 6), new Dog(Owner.Player1));
		p2.put(new Coordinate(3, 4), new Camel(Owner.Player1));
		p2.put(new Coordinate(4, 4), new Elephant(Owner.Player1));

		p2.put(new Coordinate(5, 7), new Rabbit(Owner.Player2));
		p2.put(new Coordinate(4, 5), new Rabbit(Owner.Player2));
		p2.put(new Coordinate(5, 4), new Dog(Owner.Player2));
		p2.put(new Coordinate(4, 3), new Cat(Owner.Player2));
		BoardState b2 = new BoardState(p2);
		g2 = new Game(b2);

		HashMap<Coordinate, AbstractPiece> pp = new HashMap<Coordinate, AbstractPiece>();
		pp.put(new Coordinate(0, 7), new Elephant(Owner.Player1));
		pp.put(new Coordinate(1, 7), new Rabbit(Owner.Player2));
		pp.put(new Coordinate(3, 7), new Camel(Owner.Player1));
		pp.put(new Coordinate(0, 6), new Rabbit(Owner.Player2));
		pp.put(new Coordinate(2, 6), new Camel(Owner.Player1));
		pp.put(new Coordinate(3, 6), new Rabbit(Owner.Player2));
		pp.put(new Coordinate(4, 6), new Camel(Owner.Player1));
		pp.put(new Coordinate(3, 5), new Camel(Owner.Player1));
		pp.put(new Coordinate(5, 5), new Cat(Owner.Player2));
		pp.put(new Coordinate(4, 4), new Cat(Owner.Player2));
		pp.put(new Coordinate(5, 4), new Elephant(Owner.Player1));
		pp.put(new Coordinate(6, 4), new Camel(Owner.Player2));
		pp.put(new Coordinate(2, 3), new Rabbit(Owner.Player1));
		pp.put(new Coordinate(5, 3), new Dog(Owner.Player2));
		pp.put(new Coordinate(1, 2), new Rabbit(Owner.Player1));
		pp.put(new Coordinate(2, 2), new Elephant(Owner.Player1));
		pp.put(new Coordinate(3, 2), new Rabbit(Owner.Player1));
		pp.put(new Coordinate(0, 1), new Rabbit(Owner.Player2));
		pp.put(new Coordinate(2, 1), new Rabbit(Owner.Player1));
		pp.put(new Coordinate(4, 1), new Elephant(Owner.Player1));
		pp.put(new Coordinate(6, 1), new Elephant(Owner.Player1));
		pp.put(new Coordinate(7, 1), new Rabbit(Owner.Player1));
		pp.put(new Coordinate(1, 0), new Rabbit(Owner.Player2));
		pp.put(new Coordinate(6, 0), new Rabbit(Owner.Player1));
		pp.put(new Coordinate(7, 0), new Elephant(Owner.Player2));
		pushingGame = new Game(new BoardState(pp));
	}
	
	@Test
	public void testPushNotEnoughMoves() {
		assertTrue(pushingGame.move(1, 7, 2));
		assertTrue(pushingGame.move(2, 7, 2));
		assertTrue(pushingGame.move(3, 7, 2));
		assertFalse(pushingGame.push(4, 5, 0, 0));
	}

	@Test
	public void testPushInvalid() {
		assertFalse(g2.push(3, 3, 0, 0));
		assertFalse(g2.push(4, 3, 1, 1));
		assertFalse(g2.push(4, 3, 1, 0));
	}

	@Test
	public void testPushUp() {
		assertTrue(g2.push(4, 4, 0, 0));
		assertTrue(g2.push(3, 4, 0, 0));
		g2.setPlayerTurn(1);
		assertTrue(g2.push(2, 4, 0, 0));
		assertFalse(g2.push(1, 4, 0, 0));
	}

	@Test
	public void testPushDown() {
		assertTrue(g2.push(4, 4, 2, 2));
		assertTrue(g2.push(5, 4, 2, 2));
		assertFalse(g2.push(6, 4, 2, 2));
		assertFalse(g2.push(7, 4, 2, 2));
	}

	@Test
	public void testPushRig2ht() {
		assertTrue(g2.push(4, 4, 1, 1));
		assertTrue(g2.push(4, 5, 1, 1));
		assertFalse(g2.push(4, 6, 1, 1));
		assertFalse(g2.push(4, 7, 1, 1));
	}

	@Test
	public void testPushLeft() {
		assertTrue(g2.push(7, 6, 3, 3));
		assertTrue(g2.push(7, 5, 3, 3));
		g2.setPlayerTurn(1);
		assertTrue(g2.push(7, 4, 3, 3));
		assertTrue(g2.push(7, 3, 3, 3));
		g2.setPlayerTurn(1);
		assertTrue(g2.push(7, 2, 3, 3));
		assertFalse(g2.push(7, 1, 3, 3));
	}

	@Test
	public void testPushWithDifferentDirections() {
		assertTrue(pushingGame.push(4, 5, 1, 0));
		assertEquals(new Elephant(Owner.Player1), pushingGame.getSpace(4, 6));
		assertEquals(new Camel(Owner.Player2), pushingGame.getSpace(3, 6));
	}

	@Test
	public void testPushUpWithSamePlayersPieces() {
		assertFalse(pushingGame.push(2, 2, 0, 0));
		assertEquals(new Elephant(Owner.Player1), pushingGame.getSpace(2, 2));
		assertEquals(new Rabbit(Owner.Player1), pushingGame.getSpace(1, 2));
	}

	@Test
	public void testPushRightWithSamePlayersPieces() {
		assertFalse(pushingGame.push(2, 2, 1, 1));
		assertEquals(new Elephant(Owner.Player1), pushingGame.getSpace(2, 2));
		assertEquals(new Rabbit(Owner.Player1), pushingGame.getSpace(2, 3));
	}

	@Test
	public void testPushDownWithSamePlayersPieces() {
		assertFalse(pushingGame.push(2, 2, 2, 2));
		assertEquals(new Elephant(Owner.Player1), pushingGame.getSpace(2, 2));
		assertEquals(new Rabbit(Owner.Player1), pushingGame.getSpace(3, 2));
	}

	@Test
	public void testPushLeftWithSamePlayersPieces() {
		assertFalse(pushingGame.push(2, 2, 3, 3));
		assertEquals(new Elephant(Owner.Player1), pushingGame.getSpace(2, 2));
		assertEquals(new Rabbit(Owner.Player1), pushingGame.getSpace(2, 1));
	}

	@Test
	public void testThatPiecesMustBeStronpushingGameerToPushUp() {
		assertFalse(pushingGame.push(6, 3, 0, 0));
		assertEquals(new Rabbit(Owner.Player2), pushingGame.getSpace(6, 3));
		assertEquals(new Camel(Owner.Player1), pushingGame.getSpace(5, 3));
	}

	@Test
	public void testThatPiecesMustBeStronpushingGameerToPushRipushingGameht() {
		assertFalse(pushingGame.push(6, 3, 1, 1));
		assertEquals(new Rabbit(Owner.Player2), pushingGame.getSpace(6, 3));
		assertEquals(new Camel(Owner.Player1), pushingGame.getSpace(6, 4));
	}

	@Test
	public void testThatPiecesMustBeStronpushingGameerToPushDown() {
		assertFalse(pushingGame.push(6, 3, 2, 0));
		assertFalse(pushingGame.push(6, 3, 2, 1));
		assertFalse(pushingGame.push(6, 3, 2, 2));
		assertFalse(pushingGame.push(6, 3, 2, 3));
		assertEquals(new Rabbit(Owner.Player2), pushingGame.getSpace(6, 3));
		assertEquals(new Camel(Owner.Player1), pushingGame.getSpace(7, 3));
	}

	@Test
	public void testThatPiecesMustBeStronpushingGameerToPushLeft() {
		assertFalse(pushingGame.push(6, 3, 3, 0));
		assertFalse(pushingGame.push(6, 3, 3, 1));
		assertFalse(pushingGame.push(6, 3, 3, 2));
		assertFalse(pushingGame.push(6, 3, 3, 3));
		assertEquals(new Rabbit(Owner.Player2), pushingGame.getSpace(6, 3));
		assertEquals(new Camel(Owner.Player1), pushingGame.getSpace(6, 2));
	}
	
	@Test
	public void testCantPushPullWith1Move2() {
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
		assertFalse(game.push(1, 4, 3, 3));
	}
}
