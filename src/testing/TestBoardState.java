package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

import game.BoardState;
import piece.AbstractPiece;
import piece.Camel;
import piece.Cat;
import piece.Coordinate;
import piece.Dog;
import piece.Horse;
import piece.Owner;
import piece.Rabbit;

/**
 * @author shellajt
 *
 */
public class TestBoardState {
	@Test
	public void testInitializes() {
		BoardState b = new BoardState();
		assertNotNull(b);
		int[] ys = new int[] { 0, 1, 6, 7 };
		for (int y : ys) {
			for (int x = 0; x < BoardState.MAX_BOARD_SIZE; x++) {
				assertTrue(b.pieceAt(x, y));
			}
		}
		assertEquals(new Rabbit(Owner.Player1, new Coordinate(4, 1)), b.getPieceAt(4, 1));
		assertEquals(new Camel(Owner.Player1, new Coordinate(3, 0)), b.getPieceAt(3, 0));

		assertEquals(new Rabbit(Owner.Player2, new Coordinate(4, 6)), b.getPieceAt(4, 6));
		assertEquals(new Camel(Owner.Player2, new Coordinate(3, 7)), b.getPieceAt(3, 7));
	}
	
	@Test
	public void testPieceAt() {
		HashMap<Coordinate, AbstractPiece> pieces = new HashMap<Coordinate, AbstractPiece>();
		pieces.put(new Coordinate(0, 0), new Cat(Owner.Player1, new Coordinate(0, 0)));
		pieces.put(new Coordinate(1, 1), new Cat(Owner.Player1, new Coordinate(1, 1)));
		pieces.put(new Coordinate(2, 2), new Cat(Owner.Player1, new Coordinate(2, 2)));
		pieces.put(new Coordinate(3, 3), new Cat(Owner.Player2, new Coordinate(3, 3)));

		BoardState b = new BoardState(pieces);
		
		assertTrue(b.pieceAt(0, 0));
		assertTrue(b.pieceAt(1, 1));
		assertTrue(b.pieceAt(2, 2));
		assertTrue(b.pieceAt(3, 3));
		
		assertFalse(b.pieceAt(0, 1));
		assertFalse(b.pieceAt(4, 2));
		assertFalse(b.pieceAt(7, 3));
		assertFalse(b.pieceAt(10, 1));
		assertFalse(b.pieceAt(-2, -2));
	}
	
	@Test
	public void testGetPieceAt() {
		HashMap<Coordinate, AbstractPiece> pieces = new HashMap<Coordinate, AbstractPiece>();
		AbstractPiece p1 = new Cat(Owner.Player1, new Coordinate(0, 0));
		AbstractPiece p2 = new Camel(Owner.Player1, new Coordinate(1, 1));
		AbstractPiece p3 = new Horse(Owner.Player2, new Coordinate(2, 2));
		AbstractPiece p4 = new Dog(Owner.Player2, new Coordinate(3, 3));
				
		pieces.put(new Coordinate(0, 0), p1);
		pieces.put(new Coordinate(1, 1), p2);
		pieces.put(new Coordinate(2, 2), p3);
		pieces.put(new Coordinate(3, 3), p4);

		BoardState b = new BoardState(pieces);
		assertEquals(p1, b.getPieceAt(0, 0));
		assertEquals(p2, b.getPieceAt(1, 1));
		assertEquals(p3, b.getPieceAt(2, 2));
		assertEquals(p4, b.getPieceAt(3, 3));
	}
	

}
