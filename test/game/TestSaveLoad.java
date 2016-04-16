package game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import piece.AbstractPiece;
import piece.Camel;
import piece.Cat;
import piece.Dog;
import piece.Elephant;
import piece.Owner;
import piece.Rabbit;

public class TestSaveLoad {
	private Game g;

	@Before
	public void setup() {
		g = new Game();
	}

	@Test
	public void testSaveFile() throws IOException {
		HashMap<Coordinate, AbstractPiece> p1 = new HashMap<Coordinate, AbstractPiece>();
		p1.put(new Coordinate(6, 7), new Cat(Owner.Player1));
		p1.put(new Coordinate(7, 7), new Rabbit(Owner.Player1));
		p1.put(new Coordinate(3, 6), new Dog(Owner.Player1));
		p1.put(new Coordinate(3, 4), new Camel(Owner.Player1));
		p1.put(new Coordinate(4, 4), new Elephant(Owner.Player1));
		BoardState b1 = new BoardState(p1);
		Game g1 = new Game(b1);

		FileWriter fw = null;
		File saveFile = new File("Resources/SaveTest1.txt");
		try {
			fw = new FileWriter(saveFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(g1.saveFile(fw));
	}

	@Test
	public void testSaveFile2() throws IOException {
		assertFalse(g.saveFile(null));
	}

	// Testing loadFile
	@Test
	public void testLoadFileLoadsBoardState() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadTest1.txt"));
		BoardState board = new BoardState(
				new char[][] { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
						{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, },
				0);
		assertTrue(g.loadFile(scanner));
		for (int i = 0; i < 8; i++) {
			for (int k = 0; k < 8; k++) {
				assertEquals(board.getBoardArray()[i][k], g.currentBoard.getBoardArray()[i][k]);
			}
		}
		scanner.close();
	}

	@Test
	public void testLoadFileLoadsTurnCounter1() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadTest1.txt"));
		int turnCounter = 7;
		assertTrue(g.loadFile(scanner));
		assertEquals(turnCounter, g.getTurnCounter());
		scanner.close();
	}

	@Test
	public void testLoadFileLoadsTurnCounter2() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadTest2.txt"));
		int turnCounter = 10;
		assertTrue(g.loadFile(scanner));
		assertEquals(turnCounter, g.getTurnCounter());
		scanner.close();
	}

	@Test
	public void testLoadFileLoadsTurnTimer() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadTest1.txt"));
		int turnTimer = 120;
		assertTrue(g.loadFile(scanner));
		assertEquals(turnTimer, g.getTurnTimer());
		scanner.close();
	}

	@Test
	public void testLoadFileLoadsPlayer1Name() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadTest1.txt"));
		String name = "Jesse";
		assertTrue(g.loadFile(scanner));
		assertEquals(name, g.getP1Name());
		scanner.close();
	}

	@Test
	public void testLoadFileLoadsPlayer2Name() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadTest1.txt"));
		String name = "Tayler";
		assertTrue(g.loadFile(scanner));
		assertEquals(name, g.getP2Name());
		scanner.close();
	}

	@Test
	public void testLoadFileReturnsFalseOnFailure1() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadFailure1.txt"));
		assertFalse(g.loadFile(scanner));
		scanner.close();
	}

	@Test
	public void testLoadFileReturnsFalseOnFailure2() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadFailure2.txt"));
		assertFalse(g.loadFile(scanner));
		scanner.close();
	}

	@Test
	public void testLoadFileReturnsFalseOnFailure3() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadFailure3.txt"));
		assertFalse(g.loadFile(scanner));
		scanner.close();
	}

	@Test
	public void testLoadFileReturnsFalseOnFailure4() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadFailure4.txt"));
		assertFalse(g.loadFile(scanner));
		scanner.close();
	}

	@Test
	public void testLoadFileReturnsFalseOnFailure5() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadFailure5.txt"));
		assertFalse(g.loadFile(scanner));
		scanner.close();
	}

	@Test
	public void testLoadFileReturnsFalseOnFailure6() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadFailure6.txt"));
		assertFalse(g.loadFile(scanner));
		scanner.close();
	}

	@Test
	public void testLoadFileReturnsFalseOnFailure7() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadFailure7.txt"));
		assertFalse(g.loadFile(scanner));
		scanner.close();
	}

	@Test
	public void testLoadFileInvalidBoardCharacters1() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadInvalidBoard1.txt"));
		assertFalse(g.loadFile(scanner));
		scanner.close();
	}

	@Test
	public void testLoadFileInvalidBoardCharacters2() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resources/LoadInvalidBoard2.txt"));
		assertFalse(g.loadFile(scanner));
		scanner.close();
	}
}
