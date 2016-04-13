package testing;

import static org.junit.Assert.*;
import game.BoardState;
import game.Coordinate;
import piece.AbstractPiece;
import piece.Elephant;
import piece.Owner;

import org.junit.Test;

/**
 * @author shellajt
 *
 */
public class TestBoardState {
	BoardState b = new BoardState(new char[][] {
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
			{ ' ', ' ', ' ', 'C', 'E', ' ', ' ', ' ' },
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
			{ ' ', ' ', ' ', 'D', ' ', ' ', ' ', ' ' },
			{ ' ', ' ', ' ', ' ', ' ', ' ', 'K', 'R' }, }, 0);

	@Test
	public void testInitializes() {
		BoardState bs = new BoardState(new char[8][8], 0);
		assertNotNull(bs);
	}

	@Test
	public void testInializesWithCorrectValuesUsingBoardState() {
		char[][] map = new char[8][8];
		map[1][1] = 'E';
		map[2][2] = 'C';
		map[3][3] = 'D';
		map[4][4] = 'c';
		BoardState bs = new BoardState(map, 1);
		assertEquals('E', bs.getBoardArray()[1][1]);
		assertEquals('C', bs.getBoardArray()[2][2]);
		assertEquals('D', bs.getBoardArray()[3][3]);
		assertEquals('c', bs.getBoardArray()[4][4]);
		assertEquals(1, bs.getTurnNumber());
	}

	@Test
	public void testTurnNumberIncrements() {
		b.incrementTurn();
		assertEquals(1, b.getTurnNumber());
		b.incrementTurn();
		b.incrementTurn();
		assertEquals(3, b.getTurnNumber());
	}
	
	@Test
	public void testSetTurnNumber(){
		b.setTurnNumber(5);
		assertEquals(5, b.getTurnNumber());
	}
//	for next pull request
//	@Test
//	public void testGetCoordinate() {
//		Coordinate coor = new Coordinate(0, 0);
//		AbstractPiece p = new Elephant(Owner.Player1, coor);	
//		assertEquals(coor, p.getCoordinate());
//	}
//	
//	@Test
//	public void canSetValidCoordinate() {
//		AbstractPiece p = new Elephant(Owner.Player1);
//		Coordinate newCoor = new Coordinate(1, 2);
//		p.setCoordinate(newCoor);
//		assertEquals(newCoor, p.getCoordinate());
//	}
//	
//	@Test
//	public void canNotSetInValidCoordinate() {
//		Coordinate coor = new Coordinate(0, 0);
//		AbstractPiece p = new Elephant(Owner.Player1, coor);	
//		Coordinate newCoor = new Coordinate(-1, -2);
//		p.setCoordinate(newCoor);
//		assertEquals(coor, p.getCoordinate());
//	}
//

}
