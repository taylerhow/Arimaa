package testing;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import game.GUI;

import org.junit.Test;

public class TestGUI {

	@Test
	public void testThatGUIInitializes() {
		GUI testGUI = new GUI();
		assertNotEquals(testGUI, null);
	}
	
	@Test
	public void testThatGUIInitializesWithCorrectDefaultValues() {
		GUI testGUI = new GUI();
		assertEquals(testGUI.getP1name(), "Player 1");
		assertEquals(testGUI.getP2name(), "Player 2");
		assertNotNull(testGUI.getMainFrame());
		assertEquals(testGUI.getMainFrame().getTitle(), "Welcome to Arimaa!");
		assertEquals(testGUI.getMainFrame().getDefaultCloseOperation(), JFrame.EXIT_ON_CLOSE);
	}
}
