package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

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
import piece.Piece;
import piece.Piece.PieceType;
import piece.Rabbit;

public class TestGame {
	private Game g;
	private Game g1;
	private Game g2;
	private Game freezingGame;
	private Game pushingGame;
	private Game pullingGame;

	@Before
	public void setup() {
		g = new Game();
		
		HashMap<Coordinate, AbstractPiece> p1 = new HashMap<Coordinate, AbstractPiece>();
		p1.put(new Coordinate(6, 0), new Cat(Owner.Player1));
		p1.put(new Coordinate(7, 0), new Rabbit(Owner.Player1));
		p1.put(new Coordinate(3, 1), new Dog(Owner.Player1));
		p1.put(new Coordinate(3, 3), new Camel(Owner.Player1));
		p1.put(new Coordinate(4, 3), new Elephant(Owner.Player1));
		BoardState b1 = new BoardState(p1);
		g1 = new Game(b1);
		
		HashMap<Coordinate, AbstractPiece> p2 = new HashMap<Coordinate, AbstractPiece>();
		p2.put(new Coordinate(6, 0), new Cat(Owner.Player1));
		p2.put(new Coordinate(7, 0), new Rabbit(Owner.Player1));
		p2.put(new Coordinate(3, 1), new Dog(Owner.Player1));
		p2.put(new Coordinate(3, 3), new Camel(Owner.Player1));
		p2.put(new Coordinate(4, 3), new Elephant(Owner.Player1));
		
		p2.put(new Coordinate(5, 0), new Rabbit(Owner.Player2));
		p2.put(new Coordinate(4, 2), new Rabbit(Owner.Player2));
		p2.put(new Coordinate(5, 3), new Dog(Owner.Player2));
		p2.put(new Coordinate(4, 4), new Cat(Owner.Player2));
		BoardState b2 = new BoardState(p1);
		g2 = new Game(b2);
		
		HashMap<Coordinate, AbstractPiece> fp = new HashMap<Coordinate, AbstractPiece>();
		fp.put(new Coordinate(6, 0), new Cat(Owner.Player1));
		fp.put(new Coordinate(7, 0), new Rabbit(Owner.Player1));
		fp.put(new Coordinate(3, 3), new Rabbit(Owner.Player1));
		fp.put(new Coordinate(4, 4), new Rabbit(Owner.Player1));
		fp.put(new Coordinate(5, 4), new Elephant(Owner.Player1));
		fp.put(new Coordinate(4, 3), new Camel(Owner.Player2));
		BoardState fb = new BoardState(fp);
		freezingGame = new Game(fb);
		
		HashMap<Coordinate, AbstractPiece> pp = new HashMap<Coordinate, AbstractPiece>();
		pp.put(new Coordinate(0, 0), new Elephant(Owner.Player1));
		pp.put(new Coordinate(1, 0), new Rabbit(Owner.Player2));
		pp.put(new Coordinate(3, 0), new Camel(Owner.Player1));
		pp.put(new Coordinate(0, 1), new Rabbit(Owner.Player2));
		pp.put(new Coordinate(2, 1), new Camel(Owner.Player1));
		pp.put(new Coordinate(3, 1), new Rabbit(Owner.Player2));
		pp.put(new Coordinate(4, 1), new Camel(Owner.Player1));
		pp.put(new Coordinate(3, 2), new Camel(Owner.Player1));
		pp.put(new Coordinate(5, 2), new Cat(Owner.Player2));
		pp.put(new Coordinate(4, 3), new Cat(Owner.Player2));
		pp.put(new Coordinate(5, 3), new Elephant(Owner.Player1));
		pp.put(new Coordinate(6, 3), new Camel(Owner.Player2));
		pp.put(new Coordinate(2, 4), new Rabbit(Owner.Player1));
		pp.put(new Coordinate(5, 4), new Dog(Owner.Player2));
		pp.put(new Coordinate(1, 5), new Rabbit(Owner.Player1));
		pp.put(new Coordinate(2, 5), new Elephant(Owner.Player1));
		pp.put(new Coordinate(3, 5), new Rabbit(Owner.Player1));
		pp.put(new Coordinate(0, 6), new Rabbit(Owner.Player2));
		pp.put(new Coordinate(2, 6), new Rabbit(Owner.Player1));
		pp.put(new Coordinate(4, 6), new Elephant(Owner.Player1));
		pp.put(new Coordinate(6, 6), new Elephant(Owner.Player1));
		pp.put(new Coordinate(7, 6), new Rabbit(Owner.Player1));
		pp.put(new Coordinate(1, 7), new Rabbit(Owner.Player2));
		pp.put(new Coordinate(6, 7), new Rabbit(Owner.Player1));
		pp.put(new Coordinate(7, 7), new Elephant(Owner.Player2));
		pushingGame = new Game(new BoardState(pp));
		
		HashMap<Coordinate, AbstractPiece> pullp = new HashMap<Coordinate, AbstractPiece>();
		pullp.put(new Coordinate(0, 0), new Elephant(Owner.Player1));
		pullp.put(new Coordinate(1, 0), new Rabbit(Owner.Player2));
		pullp.put(new Coordinate(3, 0), new Rabbit(Owner.Player1));
		pullp.put(new Coordinate(4, 0), new Elephant(Owner.Player2));
		pullp.put(new Coordinate(0, 1), new Rabbit(Owner.Player2));
		pullp.put(new Coordinate(1, 1), new Elephant(Owner.Player2));
		pullp.put(new Coordinate(3, 1), new Elephant(Owner.Player1));
		pullp.put(new Coordinate(4, 1), new Elephant(Owner.Player2));
		pullp.put(new Coordinate(5, 1), new Rabbit(Owner.Player2));
		pullp.put(new Coordinate(5, 2), new Elephant(Owner.Player1));
		pullp.put(new Coordinate(6, 2), new Rabbit(Owner.Player2));
		pullp.put(new Coordinate(3, 3), new Rabbit(Owner.Player2));
		pullp.put(new Coordinate(4, 3), new Elephant(Owner.Player1));
		pullp.put(new Coordinate(6, 3), new Elephant(Owner.Player1));
		pullp.put(new Coordinate(7, 3), new Rabbit(Owner.Player2));
		pullp.put(new Coordinate(2, 4), new Rabbit(Owner.Player2));
		pullp.put(new Coordinate(5, 4), new Elephant(Owner.Player1));
		pullp.put(new Coordinate(1, 5), new Elephant(Owner.Player1));
		pullp.put(new Coordinate(2, 5), new Elephant(Owner.Player1));
		pullp.put(new Coordinate(3, 5), new Rabbit(Owner.Player2));
		pullp.put(new Coordinate(5, 5), new Rabbit(Owner.Player2));
		pullp.put(new Coordinate(6, 5), new Rabbit(Owner.Player2));
		pullp.put(new Coordinate(0, 6), new Rabbit(Owner.Player2));
		pullp.put(new Coordinate(1, 6), new Elephant(Owner.Player1));
		pullp.put(new Coordinate(2, 6), new Elephant(Owner.Player1));
		pullp.put(new Coordinate(4, 6), new Elephant(Owner.Player1));
		pullp.put(new Coordinate(6, 6), new Elephant(Owner.Player1));
		pullp.put(new Coordinate(7, 6), new Rabbit(Owner.Player1));
		pullp.put(new Coordinate(1, 7), new Rabbit(Owner.Player2));
		pullp.put(new Coordinate(6, 7), new Rabbit(Owner.Player1));
		pullp.put(new Coordinate(7, 7), new Elephant(Owner.Player2));
		pullingGame = new Game(new BoardState(pullp));
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
	@Deprecated
	public void testGetPieceExists() {
		assertEquals(new Camel(Owner.Player1), g1.getSpace(3, 4));
	}

	@Test
	@Deprecated
	public void testGetPieceNotExists() {
		assertEquals(g1.getSpace(0, 0), null);
	}

	@Test
	@Deprecated
	// what does this even do?
	public void testGetPieceExistsAgain() {
		assertEquals(g1.getSpace(7, 7), new Rabbit(Owner.Player1));
		assertEquals(g1.getSpace(7, 7), new Rabbit(Owner.Player1));
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

	//testing Move
	@Test
	// probably depercated
	public void testMoveLegal() {
		assertEquals(new Rabbit(Owner.Player1),
				g1.getSpace(7, 7));
		assertTrue(g1.move(7, 7, 0));
		assertEquals(new Rabbit(Owner.Player1),
				g1.getSpace(6, 7));
		assertEquals(null, g1.getSpace(7, 7));
	}
	
	@Test
	public void testMoveIllegalNotYourTurn() {
		assertFalse(g.move(7, 7, 2));
	}

	@Test
	public void testMoveIllegalDown() {
		g.setPlayerTurn(2);
		assertFalse(g.move(7, 7, 2));
	}

	@Test
	public void testMoveIllegalUp() {
		assertFalse(g.move(0, 0, 0));

	}

	@Test
	public void testMoveIllegalRight() {
		g.setPlayerTurn(2);
		assertFalse(g.move(7, 7, 1));
	}

	@Test
	public void testMoveIllegalLeft() {
		assertFalse(g.move(0, 0, 3));
	}

	@Test
	public void testCannotMoveUpIntoOccupiedSpace() {
		assertEquals(new Rabbit(Owner.Player1),
				g.getSpace(1, 0));
		assertFalse(g.move(1, 0, 0));
		assertEquals(new Rabbit(Owner.Player1),
				g.getSpace(1, 0));
	}

	@Test
	public void testCannotMoveRightIntoOccupiedSpace() {
		assertEquals(new Dog(Owner.Player1),
				g.getSpace(0, 1));
		assertFalse(g.move(0, 1, 1));
		assertEquals(new Dog(Owner.Player1),
				g.getSpace(0, 1));
	}

	@Test
	public void testCannotMoveDownIntoOccupiedSpace() {
		assertEquals(new Dog(Owner.Player1),
				g.getSpace(0, 1));
		assertFalse(g.move(0, 1, 2));
		assertEquals(new Dog(Owner.Player1),
				g.getSpace(0, 1));
	}

	@Test
	public void testCannotMoveLeftIntoOccupiedSpace() {
		assertEquals(new Dog(Owner.Player1),
				g.getSpace(0, 1));
		assertFalse(g.move(0, 1, 3));
		assertEquals(new Dog(Owner.Player1),
				g.getSpace(0, 1));
	}
	
	@Test
	public void testCannotMoveIfFrozenByStrongerOpposingPiece(){
		assertFalse(freezingGame.move(4, 3, 0));
	}
	
	@Test
	public void testCanMoveIfFrozenByStrongerOpposingPieceButThawedByFriendlyPiece(){
		assertTrue(freezingGame.move(3, 4, 0));
	}

	@Test
	public void testNullMove() {
		assertFalse(g.move(4, 4, 0));
	}
	
	@Test 
	public void testInvalidMoveDirection(){
		assertFalse(g.move(1,0,5));
	}

	// Testing push method
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
		assertTrue(pushingGame.getSpace(4, 6).equals(
				new Elephant(Owner.Player1)));
		assertTrue(pushingGame.getSpace(3, 6).equals(
				new Camel(Owner.Player2)));
	}

	@Test
	public void testPushUpWithSamePlayersPieces() {
		assertFalse(pushingGame.push(2, 2, 0, 0));
		assertTrue(pushingGame.getSpace(2, 2).equals(
				new Elephant(Owner.Player1)));
		assertTrue(pushingGame.getSpace(1, 2).equals(
				new Rabbit(Owner.Player1)));
	}

	@Test
	public void testPushRightWithSamePlayersPieces() {
		assertFalse(pushingGame.push(2, 2, 1, 1));
		assertTrue(pushingGame.getSpace(2, 2).equals(
				new Elephant(Owner.Player1)));
		assertTrue(pushingGame.getSpace(2, 3).equals(
				new Rabbit(Owner.Player1)));
	}

	@Test
	public void testPushDownWithSamePlayersPieces() {
		assertFalse(pushingGame.push(2, 2, 2, 2));
		assertTrue(pushingGame.getSpace(2, 2).equals(
				new Elephant(Owner.Player1)));
		assertTrue(pushingGame.getSpace(3, 2).equals(
				new Rabbit(Owner.Player1)));
	}

	@Test
	public void testPushLeftWithSamePlayersPieces() {
		assertFalse(pushingGame.push(2, 2, 3, 3));
		assertTrue(pushingGame.getSpace(2, 2).equals(
				new Elephant(Owner.Player1)));
		assertTrue(pushingGame.getSpace(2, 1).equals(
				new Rabbit(Owner.Player1)));
	}

	@Test
	public void testThatPiecesMustBeStronpushingGameerToPushUp() {
		assertFalse(pushingGame.push(6, 3, 0, 0));
		assertTrue(pushingGame.getSpace(6, 3).equals(
				new Rabbit(Owner.Player2)));
		assertTrue(pushingGame.getSpace(5, 3).equals(
				new Camel(Owner.Player1)));
	}

	@Test
	public void testThatPiecesMustBeStronpushingGameerToPushRipushingGameht() {
		assertFalse(pushingGame.push(6, 3, 1, 1));
		assertTrue(pushingGame.getSpace(6, 3).equals(
				new Rabbit(Owner.Player2)));
		assertTrue(pushingGame.getSpace(6, 4).equals(
				new Camel(Owner.Player1)));
	}

	@Test
	public void testThatPiecesMustBeStronpushingGameerToPushDown() {
		assertFalse(pushingGame.push(6, 3, 2, 0));
		assertFalse(pushingGame.push(6, 3, 2, 1));
		assertFalse(pushingGame.push(6, 3, 2, 2));
		assertFalse(pushingGame.push(6, 3, 2, 3));
		assertTrue(pushingGame.getSpace(6, 3).equals(
				new Rabbit(Owner.Player2)));
		assertTrue(pushingGame.getSpace(7, 3).equals(
				new Camel(Owner.Player1)));
	}

	@Test
	public void testThatPiecesMustBeStronpushingGameerToPushLeft() {
		assertFalse(pushingGame.push(6, 3, 3, 0));
		assertFalse(pushingGame.push(6, 3, 3, 1));
		assertFalse(pushingGame.push(6, 3, 3, 2));
		assertFalse(pushingGame.push(6, 3, 3, 3));
		assertTrue(pushingGame.getSpace(6, 3).equals(
				new Rabbit(Owner.Player2)));
		assertTrue(pushingGame.getSpace(6, 2).equals(
				new Camel(Owner.Player1)));
	}

	// Testing the pull method
	@Test
	public void testBasicPullUp() {
		assertTrue(pullingGame.pull(5, 5, 6, 5, 0));
		assertTrue(pullingGame.getSpace(5, 5).equals(
				new Rabbit(Owner.Player2)));
		assertTrue(pullingGame.getSpace(4, 5).equals(
				new Elephant(Owner.Player1)));
	}

	@Test
	public void testBasicPullRight() {
		assertTrue(pullingGame.pull(4, 4, 4, 3, 1));
		assertTrue(pullingGame.getSpace(4, 4).equals(
				new Rabbit(Owner.Player2)));
		assertTrue(pullingGame.getSpace(4, 5).equals(
				new Elephant(Owner.Player1)));
	}

	@Test
	public void testBasicPullDown() {
		// pullingGame.currentBoard.printBoard();
		assertTrue(pullingGame.pull(3, 5, 2, 5, 2));
		// pullingGame.currentBoard.printBoard();
		assertTrue(pullingGame.getSpace(3, 5).equals(
				new Rabbit(Owner.Player2)));
		assertTrue(pullingGame.getSpace(4, 5).equals(
				new Elephant(Owner.Player1)));
	}

	@Test
	public void testBasicPullLeft() {
		assertTrue(pullingGame.pull(4, 6, 4, 7, 3));
		assertTrue(pullingGame.getSpace(4, 6).equals(
				new Rabbit(Owner.Player2)));
		assertTrue(pullingGame.getSpace(4, 5).equals(
				new Elephant(Owner.Player1)));
	}

	@Test
	public void testPullWithNullPiece() {
		assertFalse(pullingGame.pull(0, 4, 0, 5, 3));
	}

	@Test
	public void testPullUpOffBoard() {
		assertFalse(pullingGame.pull(0, 7, 1, 7, 0));
	}

	@Test
	public void testPullRightOffBoard() {
		assertFalse(pullingGame.pull(0, 7, 0, 6, 1));
	}

	@Test
	public void testPullDownOffBoard() {
		assertFalse(pullingGame.pull(7, 0, 6, 0, 2));
	}

	@Test
	public void testPullLeftOffBoard() {
		assertFalse(pullingGame.pull(7, 0, 7, 1, 3));
	}

	@Test
	public void testPullUpIntoOccupiedSpace() {
		assertFalse(pullingGame.pull(2, 2, 3, 2, 0));
	}

	@Test
	public void testPullRightIntoOccupiedSpace() {
		assertFalse(pullingGame.pull(1, 1, 1, 0, 1));
	}

	@Test
	public void testPullDownIntoOccupiedSpace() {
		assertFalse(pullingGame.pull(1, 1, 0, 1, 2));
	}

	@Test
	public void testPullLeftIntoOccupiedSpace() {
		assertFalse(pullingGame.pull(2, 2, 2, 3, 3));
	}

	@Test
	public void testPullNothingUp() {
		assertFalse(pullingGame.pull(1, 4, 2, 4, 0));
	}

	@Test
	public void testPullNothingRight() {
		assertFalse(pullingGame.pull(1, 4, 1, 3, 1));
	}

	@Test
	public void testPullNothingDown() {
		assertFalse(pullingGame.pull(1, 4, 0, 4, 2));
	}

	@Test
	public void testPullNothingLeft() {
		assertFalse(pullingGame.pull(1, 4, 1, 5, 3));
	}

	@Test
	public void testPullOwnPieceUp() {
		assertFalse(pullingGame.pull(6, 1, 7, 1, 0));
		assertTrue(pullingGame.getSpace(5, 1) == null);
	}

	@Test
	public void testPullOwnPieceRight() {
		assertFalse(pullingGame.pull(6, 1, 6, 0, 1));
		assertTrue(pullingGame.getSpace(6, 2) == null);
	}

	@Test
	public void testPullOwnPieceDown() {
		// pullingGame.currentBoard.printBoard();
		assertFalse(pullingGame.pull(1, 6, 0, 6, 2));
		// pullingGame.currentBoard.printBoard();
		assertFalse(pullingGame.getSpace(2, 6) == null);
	}

	@Test
	public void testPullOwnPieceLeft() {
		assertFalse(pullingGame.pull(1, 6, 1, 7, 3));
		assertTrue(pullingGame.getSpace(1, 5) == null);
	}

	@Test
	public void testBidirectionalPullUpRight() {
		AbstractPiece p1 = pullingGame.getSpace(4, 4);
		AbstractPiece p2 = pullingGame.getSpace(4, 3);
		assertTrue(pullingGame.pull(4, 4, 4, 3, 0));
		assertTrue(pullingGame.getSpace(3, 4).equals(p1));
		assertTrue(pullingGame.getSpace(4, 4).equals(p2));
	}

	@Test
	public void testBidirectionalPullRightDown() {
		AbstractPiece p1 = pullingGame.getSpace(3, 5);
		AbstractPiece p2 = pullingGame.getSpace(2, 5);
		assertTrue(pullingGame.pull(3, 5, 2, 5, 1));
		assertTrue(pullingGame.getSpace(3, 6).equals(p1));
		assertTrue(pullingGame.getSpace(3, 5).equals(p2));
	}

	@Test
	public void testBidirectionalPullDownLeft() {
		AbstractPiece p1 = pullingGame.getSpace(4, 6);
		AbstractPiece p2 = pullingGame.getSpace(4, 7);
		// pullingGame.currentBoard.printBoard();
		assertTrue(pullingGame.pull(4, 6, 4, 7, 0));
		// pullingGame.currentBoard.printBoard();
		assertTrue(pullingGame.getSpace(3, 6).equals(p1));
		assertTrue(pullingGame.getSpace(4, 6).equals(p2));
	}

	@Test
	public void testBidirectionalPullLeftUp() {
		// pullingGame.currentBoard.printBoard();
		AbstractPiece p1 = pullingGame.getSpace(5, 5);
		AbstractPiece p2 = pullingGame.getSpace(6, 5);
		assertTrue(pullingGame.pull(5, 5, 6, 5, 3));
		// pullingGame.currentBoard.printBoard();
		assertTrue(pullingGame.getSpace(5, 4).equals(p1));
		assertTrue(pullingGame.getSpace(5, 5).equals(p2));
	}

	@Test
	public void testPullPieceOfGreaterStrength() {
		assertFalse(pullingGame.pull(7, 3, 7, 4, 3));
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

	// Testing saveFile
	@Test
	public void testSaveFile() throws IOException {
		FileWriter fw = null;
		File saveFile = new File("Resources/SaveTest1.txt");
		try {
			fw = new FileWriter(saveFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(g1.saveFile(fw));
	}
	
	@Test
	public void testSaveFile2() throws IOException{
		assertFalse(g.saveFile(null));
	}

	// Testing loadFile
	@Test
	public void testLoadFileLoadsBoardState() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadTest1.txt"));
		BoardState board = new BoardState(new char[][] {
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, }, 0);
		assertTrue(g.loadFile(scanner));
		for (int i = 0; i < 8; i++) {
			for (int k = 0; k < 8; k++) {
				assertEquals(board.getBoardArray()[i][k],
						g.currentBoard.getBoardArray()[i][k]);
			}
		}
		scanner.close();
	}

	@Test
	public void testLoadFileLoadsTurnCounter1() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadTest1.txt"));
		int turnCounter = 7;
		assertTrue(g.loadFile(scanner));
		assertEquals(turnCounter, g.getTurnCounter());
		scanner.close();
	}
	
	@Test
	public void testLoadFileLoadsTurnCounter2() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadTest2.txt"));
		int turnCounter = 10;
		assertTrue(g.loadFile(scanner));
		assertEquals(turnCounter, g.getTurnCounter());
		scanner.close();
	}

	@Test
	public void testLoadFileLoadsTurnTimer() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadTest1.txt"));
		int turnTimer = 120;
		assertTrue(g.loadFile(scanner));
		assertEquals(turnTimer, g.getTurnTimer());
		scanner.close();
	}

	@Test
	public void testLoadFileLoadsPlayer1Name() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadTest1.txt"));
		String name = "Jesse";
		assertTrue(g.loadFile(scanner));
		assertEquals(name, g.getP1Name());
		scanner.close();
	}

	@Test
	public void testLoadFileLoadsPlayer2Name() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadTest1.txt"));
		String name = "Tayler";
		assertTrue(g.loadFile(scanner));
		assertEquals(name, g.getP2Name());
		scanner.close();
	}

	@Test
	public void testLoadFileReturnsFalseOnFailure1()
			throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadFailure1.txt"));
		assertFalse(g.loadFile(scanner));
		scanner.close();
	}

	@Test
	public void testLoadFileReturnsFalseOnFailure2()
			throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadFailure2.txt"));
		assertFalse(g.loadFile(scanner));
		scanner.close();
	}

	@Test
	public void testLoadFileReturnsFalseOnFailure3()
			throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadFailure3.txt"));
		assertFalse(g.loadFile(scanner));
		scanner.close();
	}

	@Test
	public void testLoadFileReturnsFalseOnFailure4()
			throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadFailure4.txt"));
		assertFalse(g.loadFile(scanner));
		scanner.close();
	}
	
	@Test
	public void testLoadFileReturnsFalseOnFailure5()
			throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadFailure5.txt"));
		assertFalse(g.loadFile(scanner));
		scanner.close();
	}
	
	@Test
	public void testLoadFileReturnsFalseOnFailure6()
			throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadFailure6.txt"));
		assertFalse(g.loadFile(scanner));
		scanner.close();
	}
	
	@Test
	public void testLoadFileReturnsFalseOnFailure7()
			throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadFailure7.txt"));
		assertFalse(g.loadFile(scanner));
		scanner.close();
	}

	@Test
	public void testLoadFileInvalidBoardCharacters1()
			throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(
				"resources/LoadInvalidBoard1.txt"));
		assertFalse(g.loadFile(scanner));
		scanner.close();
	}

	@Test
	public void testLoadFileInvalidBoardCharacters2()
			throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(
				"resources/LoadInvalidBoard2.txt"));
		assertFalse(g.loadFile(scanner));
		scanner.close();
	}

	// Testing remove piece checks
	@Test
	public void testRemovePieceValid() {
		HashMap<Coordinate, AbstractPiece> removeP = new HashMap<Coordinate, AbstractPiece>();
		removeP.put(new Coordinate(5, 0), new Rabbit(Owner.Player2));
		removeP.put(new Coordinate(6, 0), new Cat(Owner.Player1));
		removeP.put(new Coordinate(7, 0), new Rabbit(Owner.Player1));
		removeP.put(new Coordinate(3, 1), new Dog(Owner.Player1));
		removeP.put(new Coordinate(4, 2), new Rabbit(Owner.Player2));
		removeP.put(new Coordinate(3, 3), new Camel(Owner.Player1));
		removeP.put(new Coordinate(4, 3), new Elephant(Owner.Player1));
		removeP.put(new Coordinate(5, 3), new Dog(Owner.Player2));
		removeP.put(new Coordinate(4, 4), new Cat(Owner.Player2));
		removeP.put(new Coordinate(2, 5), new Camel(Owner.Player1));

		Game game = new Game(new BoardState(removeP));
//		g.currentBoard.printBoard();
		assertTrue(game.move(6, 3, 0));
//		g.currentBoard.printBoard();
		assertEquals(null, game.getSpace(2, 2));
	}

	@Test
	public void testRemovePiece2() {
		HashMap<Coordinate, AbstractPiece> removeP = new HashMap<Coordinate, AbstractPiece>();
		removeP.put(new Coordinate(5, 0), new Rabbit(Owner.Player2));
		removeP.put(new Coordinate(6, 0), new Cat(Owner.Player1));
		removeP.put(new Coordinate(7, 0), new Rabbit(Owner.Player1));
		removeP.put(new Coordinate(3, 1), new Dog(Owner.Player1));
		removeP.put(new Coordinate(4, 2), new Rabbit(Owner.Player2));
		removeP.put(new Coordinate(3, 3), new Camel(Owner.Player1));
		removeP.put(new Coordinate(4, 3), new Elephant(Owner.Player1));
		removeP.put(new Coordinate(5, 3), new Dog(Owner.Player2));
		removeP.put(new Coordinate(4, 4), new Cat(Owner.Player2));
		removeP.put(new Coordinate(2, 5), new Camel(Owner.Player1));
		removeP.put(new Coordinate(5, 5), new Camel(Owner.Player1));
		removeP.put(new Coordinate(5, 6), new Dog(Owner.Player1));
		Game game = new Game(new BoardState(removeP));
		game.move(2, 2, 0);
		assertEquals(game.getSpace(2, 5), game.getSpace(2, 5));
		game.move(1, 5, 0);
		assertEquals(null, game.getSpace(2, 5));
	}

	// Testing win checks
	@Test
	public void testPlayer2Win() {
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
	
	//Testing undoMove
	@Test
	public void testBaseUndoCase(){
		Game standardStart = new Game();
		g.move(1, 0, 2);
		g.undoMove();
		assertEquals(standardStart.getSpace(1, 0), g.getSpace(1,0));
	}
	
	@Test
	public void testUndoTwoMoves() {
		g.move(1, 0, 2);
		g.move(2, 0, 2);
		g.undoMove();
		assertEquals(new Rabbit(Owner.Player1), g.getSpace(1,0));	}
	
	@Test
	public void testUndoThreeMoves() {
		g.move(1, 0, 2);
		g.move(2, 0, 2);
		g.move(3, 0, 2);
		g.undoMove();
		assertEquals(new Rabbit(Owner.Player1), g.getSpace(1,0));	}
	
	@Test
	public void testThatUndoCantCrossTurns(){
		g.move(1, 0, 2);
		g.move(2, 0, 2);
		g.move(3, 0, 2);
		g.move(4, 0, 2);
		g.undoMove();
		assertEquals(new Rabbit(Owner.Player1), g.getSpace(5,0));
	}
	
	@Test
	public void testThatUndoGrantsMoves(){
		g.move(1, 0, 2);
		g.undoMove();
		assertEquals(4, g.getNumMoves());
	}
	
	@Test
	public void testSetWinner(){
		g.setWinner(1);
		assertEquals(1,g.getWinner());
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
		game.move(1,1,2);
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
		assertTrue(game.move(1,5,1));		
	}
	
	@Test
	public void testEndMove(){
		assertTrue(g.move(1,0,2));
		assertTrue(g.move(2,0,2));
		assertTrue(g.move(3,0,2));
		assertTrue(g.move(4,0,2));
		assertTrue(g.move(6,1,0));
		assertTrue(g.move(5,1,0));
		assertTrue(g.move(4,1,0));
		assertTrue(g.move(3,1,0));
	}

	@Test
	public void testCantPushPullWith1Move() {
		HashMap<Coordinate, AbstractPiece> pushPullP = new HashMap<Coordinate, AbstractPiece>();
		pushPullP.put(new Coordinate(6, 0), new Cat(Owner.Player1));
		pushPullP.put(new Coordinate(3, 1), new Dog(Owner.Player1));
		pushPullP.put(new Coordinate(4, 2), new Rabbit(Owner.Player2));
		pushPullP.put(new Coordinate(3, 3), new Camel(Owner.Player1));
		pushPullP.put(new Coordinate(4, 3), new Elephant(Owner.Player1));
		pushPullP.put(new Coordinate(5, 3), new Dog(Owner.Player2));
		pushPullP.put(new Coordinate(4, 4), new Cat(Owner.Player2));
		pushPullP.put(new Coordinate(2, 5), new Camel(Owner.Player1));
		pushPullP.put(new Coordinate(5, 5), new Camel(Owner.Player1));
		pushPullP.put(new Coordinate(1, 6), new Camel(Owner.Player1));
		pushPullP.put(new Coordinate(3, 6), new Rabbit(Owner.Player2));
		pushPullP.put(new Coordinate(7, 6), new Dog(Owner.Player1));
		Game game = new Game(new BoardState(pushPullP));

		game.setPlayerTurn(1);
		assertTrue(game.move(1, 7, 3));
		assertTrue(game.move(1, 6, 3));
		assertTrue(game.move(1, 5, 3));
		assertFalse(game.pull(1, 4, 1, 3, 1));
	}
	
	@Test
	public void testCantPushPullWith1Move2(){
		HashMap<Coordinate, AbstractPiece> pushPullP = new HashMap<Coordinate, AbstractPiece>();
		pushPullP.put(new Coordinate(6, 0), new Cat(Owner.Player1));
		pushPullP.put(new Coordinate(3, 1), new Dog(Owner.Player1));
		pushPullP.put(new Coordinate(4, 2), new Rabbit(Owner.Player2));
		pushPullP.put(new Coordinate(3, 3), new Camel(Owner.Player1));
		pushPullP.put(new Coordinate(4, 3), new Elephant(Owner.Player1));
		pushPullP.put(new Coordinate(5, 3), new Dog(Owner.Player2));
		pushPullP.put(new Coordinate(4, 4), new Cat(Owner.Player2));
		pushPullP.put(new Coordinate(2, 5), new Camel(Owner.Player1));
		pushPullP.put(new Coordinate(5, 5), new Camel(Owner.Player1));
		pushPullP.put(new Coordinate(1, 6), new Camel(Owner.Player1));
		pushPullP.put(new Coordinate(3, 6), new Rabbit(Owner.Player2));
		pushPullP.put(new Coordinate(7, 6), new Dog(Owner.Player1));
		Game game = new Game(new BoardState(pushPullP));

		game.setPlayerTurn(1);
		assertTrue(game.move(1, 7, 3));
		assertTrue(game.move(1, 6, 3));
		assertTrue(game.move(1, 5, 3));
		assertFalse(game.push(1, 4, 3, 3));
	}
}
