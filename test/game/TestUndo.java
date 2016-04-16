package game;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import piece.Owner;
import piece.Rabbit;

public class TestUndo {
	private Game g;

	@Before
	public void setup() {
		g = new Game();
	}
	
	@Test
	public void testBaseUndoCase() {
		Game standardStart = new Game();
		g.move(1, 0, 2);
		g.undoMove();
		assertEquals(standardStart.getSpace(1, 0), g.getSpace(1, 0));
	}

	@Test
	public void testUndoTwoMoves() {
		g.move(1, 0, 2);
		g.move(2, 0, 2);
		g.undoMove();
		assertEquals(new Rabbit(Owner.Player1), g.getSpace(1, 0));
	}

	@Test
	public void testUndoThreeMoves() {
		g.move(1, 0, 2);
		g.move(2, 0, 2);
		g.move(3, 0, 2);
		g.undoMove();
		assertEquals(new Rabbit(Owner.Player1), g.getSpace(1, 0));
	}

	@Test
	public void testThatUndoCantCrossTurns() {
		g.move(1, 0, 2);
		g.move(2, 0, 2);
		g.move(3, 0, 2);
		g.move(4, 0, 2);
		g.undoMove();
		assertEquals(new Rabbit(Owner.Player1), g.getSpace(5, 0));
	}

	@Test
	public void testThatUndoGrantsMoves() {
		g.move(1, 0, 2);
		g.undoMove();
		assertEquals(4, g.getNumMoves());
	}

	@Test
	public void testSetWinner() {
		g.setWinner(1);
		assertEquals(1, g.getWinner());
	}
}
