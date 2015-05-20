package testing;

import static org.junit.Assert.*;
import game.BoardState;

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

}
