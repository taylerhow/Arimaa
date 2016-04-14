package testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import game.TestBoardState;
import game.TestCoordinate;
import game.TestEndGame;
import game.TestGame;
import game.TestSaveLoad;
import game.TestTimePanel;
import game.TestUndo;
import move_commands.TestMove;
import move_commands.TestPull;
import move_commands.TestPush;
import piece.TestPiece;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestBoardState.class, TestBoardState.class, TestCoordinate.class, TestEndGame.class,
		TestGame.class, TestSaveLoad.class, TestTimePanel.class, TestUndo.class, TestMove.class, TestPull.class,
		TestPush.class, TestPiece.class, })
public class AllTests {
	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("testing.AllTests");
	}
}
