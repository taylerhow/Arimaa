package testing;

import static org.junit.Assert.*;

import javax.swing.JLabel;

import game.GUI;
import game.Game;
import game.TimePanel;

import org.junit.Test;

public class TestTimePanel {

	@Test
	public void testInitializes() {
		TimePanel tp = new TimePanel(new GUI(), new Game(), 15, new JLabel());
		assertNotNull(tp);
	}

}
