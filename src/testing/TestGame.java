package testing;
import static org.junit.Assert.*;

import java.util.Arrays;

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
	
	BoardState b2 = new BoardState(new char[][] {
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ','k',' ',' ',' '},
			{' ',' ',' ','C','E','d',' ',' '},
			{' ',' ',' ',' ','r',' ',' ',' '},
			{' ',' ',' ','D',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ','r','K','R'},
			}, 0);
	
	BoardState pullTestingBoard = new BoardState(new char[][] {
			{' ','r',' ',' ',' ',' ','R','e'},
			{'r','E','E',' ','E',' ',' ','R'},
			{' ','E','E','r',' ','r',' ',' '},
			{' ',' ','r',' ',' ','E',' ',' '},
			{' ',' ',' ','r','E',' ','E','r'},
			{' ',' ',' ',' ',' ','E',' ',' '},
			{'r',' ',' ',' ',' ','r',' ',' '},
			{'E','r',' ',' ',' ',' ',' ',' '},
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
	public void testCannotMoveUpIntoOccupiedSpace(){
		Game g = new Game();
		assertEquals(new Piece(PieceType.Rabbit, null, Piece.Owner.Player1), g.getSpace(1, 0));
		assertFalse(g.move(1, 0, 0));
		assertEquals(new Piece(PieceType.Rabbit, null, Piece.Owner.Player1), g.getSpace(1, 0));
	}
	
	@Test
	public void testCannotMoveRightIntoOccupiedSpace(){
		Game g = new Game();
		assertEquals(new Piece(PieceType.Dog, null, Piece.Owner.Player1), g.getSpace(0, 1));
		assertFalse(g.move(0, 1, 1));
		assertEquals(new Piece(PieceType.Dog, null, Piece.Owner.Player1), g.getSpace(0, 1));
	}
	
	@Test
	public void testCannotMoveDownIntoOccupiedSpace(){
		Game g = new Game();
		assertEquals(new Piece(PieceType.Dog, null, Piece.Owner.Player1), g.getSpace(0, 1));
		assertFalse(g.move(0, 1, 2));
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
	
	
	@Test
	public void testPushInvalid(){
		Game g=new Game(b2);
		assertFalse(g.push(3, 3, 0, 0));
		assertFalse(g.push(4, 3, 1, 1));
		assertFalse(g.push(4,3,1,0));
	}
	@Test
	public void testPushUp(){
		Game g=new Game(b2);
		assertTrue(g.push(4, 4, 0, 0));
		assertTrue(g.push(3, 4, 0, 0));
		assertTrue(g.push(2, 4, 0, 0));
		assertFalse(g.push(1, 4, 0, 0));
		//System.out.println(Arrays.deepToString(g.currentBoard.getBoardArray()));
	}
	@Test
	public void testPushDown(){
		Game g=new Game(b2);
		assertTrue(g.push(4, 4, 2, 2));
		assertTrue(g.push(5, 4, 2, 2));
		assertFalse(g.push(6, 4, 2, 2));
		assertFalse(g.push(7, 4, 2, 2));
		//System.out.println(Arrays.deepToString(g.currentBoard.getBoardArray()));
	}
	@Test
	public void testPushRight(){
		Game g=new Game(b2);
		assertTrue(g.push(4, 4, 1, 1));
		assertTrue(g.push(4, 5, 1, 1));
		assertFalse(g.push(4, 6, 1,1));
		assertFalse(g.push(4, 7, 1, 1));
	}
	@Test
	public void testPushLeft(){
		Game g=new Game(b2);
		assertTrue(g.push(7, 6, 3, 3));
		assertTrue(g.push(7, 5, 3, 3));
		assertTrue(g.push(7, 4, 3, 3));
		assertTrue(g.push(7, 3, 3, 3));
		assertTrue(g.push(7, 2, 3, 3));
		assertFalse(g.push(7, 1, 3, 3));
	}
	
	@Test
	public void testBasicPullUp(){
		Game g = new Game(pullTestingBoard);
		assertTrue(g.pull(5, 5, 0));
		assertTrue(g.getSpace(5, 5).equals(new Piece(PieceType.Rabbit, null, Piece.Owner.Player2)));
		assertTrue(g.getSpace(4, 5).equals(new Piece(PieceType.Elephant, null, Piece.Owner.Player1)));
	}
	
	@Test
	public void testBasicPullRight(){
		Game g = new Game(pullTestingBoard);
		assertTrue(g.pull(4, 4, 1));
		assertTrue(g.getSpace(4, 4).equals(new Piece(PieceType.Rabbit, null, Piece.Owner.Player2)));
		assertTrue(g.getSpace(4, 5).equals(new Piece(PieceType.Elephant, null, Piece.Owner.Player1)));
	}
	
	@Test
	public void testBasicPullDown(){
		Game g = new Game(pullTestingBoard);
		assertTrue(g.pull(3, 5, 2));
		assertTrue(g.getSpace(3, 5).equals(new Piece(PieceType.Rabbit, null, Piece.Owner.Player2)));
		assertTrue(g.getSpace(4, 5).equals(new Piece(PieceType.Elephant, null, Piece.Owner.Player1)));
	}
	
	@Test
	public void testBasicPullLeft(){
		Game g = new Game(pullTestingBoard);
		assertTrue(g.pull(4, 6, 3));
		assertTrue(g.getSpace(4, 6).equals(new Piece(PieceType.Rabbit, null, Piece.Owner.Player2)));
		assertTrue(g.getSpace(4, 5).equals(new Piece(PieceType.Elephant, null, Piece.Owner.Player1)));
	}
	
	@Test
	public void testPullWithNullPiece(){
		Game g = new Game(pullTestingBoard);
		assertFalse(g.pull(0, 4, 3));
	}
	
	@Test
	public void testPullUpOffBoard(){
		Game g = new Game(pullTestingBoard);
		assertFalse(g.pull(0, 7, 0));
	}
	
	@Test
	public void testPullRightOffBoard(){
		Game g = new Game(pullTestingBoard);
		assertFalse(g.pull(0, 7, 1));
	}
	
	@Test
	public void testPullDownOffBoard(){
		Game g = new Game(pullTestingBoard);
		assertFalse(g.pull(7, 0, 2));
	}
	
	@Test
	public void testPullLeftOffBoard(){
		Game g = new Game(pullTestingBoard);
		assertFalse(g.pull(7, 0, 3));
	}
	
	@Test
	public void testPullUpIntoOccupiedSpace(){
		Game g = new Game(pullTestingBoard);
		assertFalse(g.pull(2, 2, 0));
	}
	
	@Test
	public void testPullRightIntoOccupiedSpace(){
		Game g = new Game(pullTestingBoard);
		assertFalse(g.pull(1, 1, 1));
	}
	
	@Test
	public void testPullDownIntoOccupiedSpace(){
		Game g = new Game(pullTestingBoard);
		assertFalse(g.pull(1, 1, 2));
	}
	
	@Test
	public void testPullLeftIntoOccupiedSpace(){
		Game g = new Game(pullTestingBoard);
		assertFalse(g.pull(2, 2, 3));
	}
	
	@Test
	public void testPullNothingUp(){
		Game g = new Game(pullTestingBoard);
		assertFalse(g.pull(1, 4, 0));
	}
	
	@Test
	public void testPullNothingRight(){
		Game g = new Game(pullTestingBoard);
		assertFalse(g.pull(1, 4, 1));
	}
	
	@Test
	public void testPullNothingDown(){
		Game g = new Game(pullTestingBoard);
		assertFalse(g.pull(1, 4, 2));
	}
	
	@Test
	public void testPullNothingLeft(){
		Game g = new Game(pullTestingBoard);
		assertFalse(g.pull(1, 4, 3));
	}
}

