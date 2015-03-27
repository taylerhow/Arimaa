package testing;
import static org.junit.Assert.*;
import game.Game;

import org.junit.Test;


public class TestGame {

	@Test
	public void testInitializes() {
		Game g =new Game();
		assertNotNull(g);
	}

}
