package game;

import static org.junit.Assert.assertEquals;
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

public class TestEndGame {
	private Game game;

	@Before
	public void setup() {
		HashMap<Coordinate, AbstractPiece> winP = new HashMap<Coordinate, AbstractPiece>();
		winP.put(new Coordinate(6, 0), new Cat(Owner.Player1));
		winP.put(new Coordinate(1, 1), new Rabbit(Owner.Player1));
		winP.put(new Coordinate(3, 1), new Dog(Owner.Player1));
		winP.put(new Coordinate(4, 2), new Rabbit(Owner.Player2));
		winP.put(new Coordinate(3, 3), new Camel(Owner.Player1));
		winP.put(new Coordinate(4, 3), new Elephant(Owner.Player1));
		winP.put(new Coordinate(5, 3), new Dog(Owner.Player2));
		winP.put(new Coordinate(4, 4), new Cat(Owner.Player2));
		winP.put(new Coordinate(2, 5), new Camel(Owner.Player1));
		winP.put(new Coordinate(5, 5), new Camel(Owner.Player1));
		winP.put(new Coordinate(1, 6), new Rabbit(Owner.Player2));
		winP.put(new Coordinate(5, 6), new Dog(Owner.Player1));
		game = new Game(new BoardState(winP));
		
	}
	
	@Test
	public void testPlayer2Win() {
		game.setPlayerTurn(2);
		assertTrue(game.move(1, 1, 0));
		assertEquals(2, game.getWinner());
	}

	@Test
	public void testPlayer1Win() {
		HashMap<Coordinate, AbstractPiece> winP = new HashMap<Coordinate, AbstractPiece>();
		winP.put(new Coordinate(6, 0), new Cat(Owner.Player1));
		winP.put(new Coordinate(1, 1), new Rabbit(Owner.Player1));
		winP.put(new Coordinate(3, 1), new Dog(Owner.Player1));
		winP.put(new Coordinate(4, 2), new Rabbit(Owner.Player2));
		winP.put(new Coordinate(3, 3), new Camel(Owner.Player1));
		winP.put(new Coordinate(4, 3), new Elephant(Owner.Player1));
		winP.put(new Coordinate(5, 3), new Dog(Owner.Player2));
		winP.put(new Coordinate(4, 4), new Cat(Owner.Player2));
		winP.put(new Coordinate(2, 5), new Camel(Owner.Player1));
		winP.put(new Coordinate(5, 5), new Camel(Owner.Player1));
		winP.put(new Coordinate(1, 6), new Rabbit(Owner.Player2));
		winP.put(new Coordinate(5, 6), new Dog(Owner.Player1));
		Game game = new Game(new BoardState(winP));
		assertTrue(game.move(6, 1, 2));
		assertEquals(1, game.getWinner());
	}

	@Test
	public void testWinWhenP1HasNoRabbits() {
		HashMap<Coordinate, AbstractPiece> winP = new HashMap<Coordinate, AbstractPiece>();
		winP.put(new Coordinate(6, 0), new Cat(Owner.Player1));
		winP.put(new Coordinate(3, 1), new Dog(Owner.Player1));
		winP.put(new Coordinate(4, 2), new Rabbit(Owner.Player2));
		winP.put(new Coordinate(3, 3), new Camel(Owner.Player1));
		winP.put(new Coordinate(4, 3), new Elephant(Owner.Player1));
		winP.put(new Coordinate(5, 3), new Dog(Owner.Player2));
		winP.put(new Coordinate(4, 4), new Cat(Owner.Player2));
		winP.put(new Coordinate(2, 5), new Camel(Owner.Player1));
		winP.put(new Coordinate(5, 5), new Camel(Owner.Player1));
		winP.put(new Coordinate(1, 6), new Camel(Owner.Player1));
		winP.put(new Coordinate(4, 6), new Elephant(Owner.Player2));
		winP.put(new Coordinate(5, 6), new Dog(Owner.Player1));
		Game game = new Game(new BoardState(winP));
		game.move(1, 1, 2);
		assertEquals(2, game.getWinner());
	}

	@Test
	public void testCheckFriendlyAdjacentDownCase() {
		HashMap<Coordinate, AbstractPiece> winP = new HashMap<Coordinate, AbstractPiece>();
		winP.put(new Coordinate(6, 0), new Cat(Owner.Player1));
		winP.put(new Coordinate(3, 1), new Dog(Owner.Player1));
		winP.put(new Coordinate(4, 2), new Rabbit(Owner.Player2));
		winP.put(new Coordinate(3, 3), new Camel(Owner.Player1));
		winP.put(new Coordinate(4, 3), new Elephant(Owner.Player1));
		winP.put(new Coordinate(5, 3), new Dog(Owner.Player2));
		winP.put(new Coordinate(4, 4), new Cat(Owner.Player2));
		winP.put(new Coordinate(2, 5), new Camel(Owner.Player1));
		winP.put(new Coordinate(5, 5), new Camel(Owner.Player1));
		winP.put(new Coordinate(1, 6), new Camel(Owner.Player1));
		winP.put(new Coordinate(4, 6), new Elephant(Owner.Player2));
		winP.put(new Coordinate(5, 6), new Dog(Owner.Player1));
		Game game = new Game(new BoardState(winP));
		assertTrue(game.move(1, 5, 1));
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
