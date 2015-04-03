package testing;
import static org.junit.Assert.*;
import game.*;

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

	@Test
	public void testInitializes() {
		Game g =new Game(null);
		assertNotNull(g);
	}
	
	@Test
	public void testInitializesWithBoardState(){
		BoardState b = new BoardState(new char[][] {
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' '},
				}, 0);
		Game g= new Game(b);
		
		assertEquals(g.currentBoard.getBoardArray()[5][5], ' ');
		assertEquals(g.currentBoard.getBoardArray()[6][4], ' ');
		assertEquals(g.currentBoard.getBoardArray()[7][3], ' ');
		assertEquals(g.currentBoard.getBoardArray()[1][0], ' ');
	}

}

