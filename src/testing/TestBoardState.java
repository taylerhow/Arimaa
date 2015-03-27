package testing;

import static org.junit.Assert.*;
import game.BoardState;

import org.junit.Test;

/**
 * @author shellajt
 *
 */
public class TestBoardState {

	@Test
	public void testInitializes() {
		BoardState bs = new BoardState(new char[1][1], 0);
		assertNotNull(bs);
	}

}
