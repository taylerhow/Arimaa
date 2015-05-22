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
	
	@Test
	public void testUpdate() {
		TimePanel tp = new TimePanel(new GUI(), new Game(), 15, new JLabel());
		tp.update(5, 0);
		assertEquals("<html> <b>" + 0 + ":" + 14 + "</b> </html>", tp.getTimerLabel().getText());
	}
	
	@Test
	public void testCancelTimer(){
		GUI gui=new GUI();
		Game game=new Game();
		TimePanel tp= new TimePanel(gui, game, 3, new JLabel());
		game.setWinner(1);
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(1, game.getWinner());
	}

}
