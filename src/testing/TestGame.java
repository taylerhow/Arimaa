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
		Game g =new Game();
		assertNotNull(g);
	}
	
	@Test
	public void testMove(){
		BoardState b = new BoardState(null, 0);
		Game g= new Game(b);
	}

}

