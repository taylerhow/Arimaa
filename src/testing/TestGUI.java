package testing;

import static org.junit.Assert.*;
import game.GUI;

import org.junit.Test;

public class TestGUI {

	@Test
	public void testThatGUIInitializes() {
		GUI testGUI = new GUI();
		assertNotEquals(testGUI, null);
	}

}
