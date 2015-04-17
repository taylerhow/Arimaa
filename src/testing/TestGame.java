package testing;
import static org.junit.Assert.*;
import game.*;
import game.Piece.PieceType;

import org.junit.Test;

public class TestGame {
	BoardState b = new BoardState(new char[][] {
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ','C','E',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ','D',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ','K','R'},
			}, 0);
	
	
	@Test
	public void testInitializes() {
		Game g =new Game(null);
		assertNotNull(g);
	}
	
	@Test
	public void testInitializesWithBoardState(){
		Game g= new Game(b);
		
		assertEquals(g.currentBoard.getBoardArray()[4][3], 'C');
		assertEquals(g.currentBoard.getBoardArray()[4][4], 'E');
		assertEquals(g.currentBoard.getBoardArray()[7][3], ' ');
		assertEquals(g.currentBoard.getBoardArray()[7][7], 'R');
	}
	
	@Test
	public void testGetPieceExists() {
		Game g= new Game(b);
		assertEquals(g.getSpace(4, 3), new Piece(PieceType.Camel, null, Piece.Owner.Player1));
	}
	
	@Test
	public void testGetPieceNotExists() {
		Game g= new Game(b);
		assertEquals(g.getSpace(0, 0), null);
	}
	@Test
	public void testGetPieceExistsAgain() {
		Game g= new Game(b);
		assertEquals(g.getSpace(7, 7), new Piece(PieceType.Rabbit, null, Piece.Owner.Player1));
		assertEquals(g.getSpace(7, 7), new Piece(PieceType.Rabbit, null, Piece.Owner.Player1));
	}
	
	@Test
	public void testMoveLegal(){
		Game g = new Game(b);
		assertEquals(new Piece(PieceType.Rabbit, null, Piece.Owner.Player1),g.getSpace(7, 7));
		assertTrue(g.move(7, 7, 0));
		assertEquals(new Piece(PieceType.Rabbit, null, Piece.Owner.Player1),g.getSpace(6, 7));
		assertEquals(null,g.getSpace(7, 7));
	}
	
	@Test
	public void testMoveIllegalDown(){
		Game g = new Game();
		assertFalse(g.move(7, 7, 2));
		
	}
	
	@Test
	public void testMoveIllegalUp(){
		Game g = new Game();
		assertFalse(g.move(0, 0, 0));
		
	}
	
	@Test 
	public void testMoveIllegalRight(){
		Game g = new Game();
		assertFalse(g.move(7, 7, 1));
	}
	
	@Test 
	public void testMoveIllegalLeft(){
		Game g= new Game();
		assertFalse(g.move(0,0,3));
	}
	
	@Test
	public void testCannotMoveRightIntoOccupiedSpace(){
		Game g = new Game();
		assertEquals(new Piece(PieceType.Dog, null, Piece.Owner.Player1), g.getSpace(0, 1));
		assertFalse(g.move(0, 1, 1));
		assertEquals(new Piece(PieceType.Dog, null, Piece.Owner.Player1), g.getSpace(0, 1));
	}
	
	@Test
	public void testCannotMoveLeftIntoOccupiedSpace(){
		Game g = new Game();
		assertEquals(new Piece(PieceType.Dog, null, Piece.Owner.Player1), g.getSpace(0, 1));
		assertFalse(g.move(0, 1, 3));
		assertEquals(new Piece(PieceType.Dog, null, Piece.Owner.Player1), g.getSpace(0, 1));
	}

	@Test
	public void testEmptyCreateConstructor(){
		Game g = new Game();
		assertEquals(new Piece(PieceType.Rabbit, null, Piece.Owner.Player1), g.getSpace(1, 1));
	}
}

