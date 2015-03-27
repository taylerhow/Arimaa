package testing;

import static org.junit.Assert.*;
import game.Piece;
import game.Piece.PieceType;

import org.junit.Test;

public class TestPiece {

	@Test
	public void testThatPieceInitializes() {
		Piece p = new Piece(PieceType.Camel, null);
		assertNotNull(p);
	}

}
