package testing;
import static org.junit.Assert.*;
import game.*;
import game.Piece.PieceType;

import java.util.Arrays;
import java.util.Collection;
 



import org.junit.Test;
import org.junit.Before;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

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
	public void testEmptyCreateConstructor(){
		Game g = new Game();
		assertEquals(new Piece(PieceType.Rabbit, null, Piece.Owner.Player1), g.getSpace(1, 1));
	}
}

