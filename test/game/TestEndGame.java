package game;

import static org.junit.Assert.assertEquals;
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

public class TestEndGame {
	private Game game;
	private Game game2;

	@Before
	public void setup() {
		HashMap<Coordinate, AbstractPiece> winP = new HashMap<Coordinate, AbstractPiece>();
		winP.put(new Coordinate(6, 7), new Cat(Owner.Player1));
		winP.put(new Coordinate(1, 6), new Rabbit(Owner.Player1));
		winP.put(new Coordinate(3, 6), new Dog(Owner.Player1));
		winP.put(new Coordinate(4, 5), new Rabbit(Owner.Player2));
		winP.put(new Coordinate(3, 4), new Camel(Owner.Player1));
		winP.put(new Coordinate(4, 4), new Elephant(Owner.Player1));
		winP.put(new Coordinate(5, 4), new Dog(Owner.Player2));
		winP.put(new Coordinate(4, 3), new Cat(Owner.Player2));
		winP.put(new Coordinate(2, 2), new Camel(Owner.Player1));
		winP.put(new Coordinate(5, 2), new Camel(Owner.Player1));
		winP.put(new Coordinate(1, 1), new Rabbit(Owner.Player2));
		winP.put(new Coordinate(5, 1), new Dog(Owner.Player1));
		game = new Game(new BoardState(winP));
		
		HashMap<Coordinate, AbstractPiece> winP2 = new HashMap<Coordinate, AbstractPiece>();
		winP2.put(new Coordinate(6, 7), new Cat(Owner.Player1));
		winP2.put(new Coordinate(3, 6), new Dog(Owner.Player1));
		winP2.put(new Coordinate(4, 5), new Rabbit(Owner.Player2));
		winP2.put(new Coordinate(3, 4), new Camel(Owner.Player1));
		winP2.put(new Coordinate(4, 4), new Elephant(Owner.Player1));
		winP2.put(new Coordinate(5, 4), new Dog(Owner.Player2));
		winP2.put(new Coordinate(4, 3), new Cat(Owner.Player2));
		winP2.put(new Coordinate(2, 2), new Camel(Owner.Player1));
		winP2.put(new Coordinate(5, 2), new Camel(Owner.Player1));
		winP2.put(new Coordinate(1, 1), new Camel(Owner.Player1));
		winP2.put(new Coordinate(4, 1), new Elephant(Owner.Player2));
		winP2.put(new Coordinate(5, 1), new Dog(Owner.Player1));
		game2 = new Game(new BoardState(winP2));
	}
	
	@Test
	public void testPlayer2Win() {
		game.setPlayerTurn(2);
		assertTrue(game.move(1, 1, 0));
		assertEquals(2, game.getWinner());
	}

	@Test
	public void testPlayer1Win() {
		assertTrue(game.move(6, 1, 2));
		assertEquals(1, game.getWinner());
	}

	@Test
	public void testWinWhenP1HasNoRabbits() {
		game2.move(1, 1, 2);
		assertEquals(2, game2.getWinner());
	}

	@Test
	public void testCheckFriendlyAdjacentDownCase() {
		assertTrue(game2.move(1, 5, 1));
	}

	@Test
	public void testEndMove() {
		Game g = new Game();
		assertTrue(g.move(1, 0, 2));
		assertTrue(g.move(2, 0, 2));
		assertTrue(g.move(3, 0, 2));
		assertTrue(g.move(4, 0, 2));
		assertTrue(g.move(6, 1, 0));
		assertTrue(g.move(5, 1, 0));
		assertTrue(g.move(4, 1, 0));
		assertTrue(g.move(3, 1, 0));
	}
}
