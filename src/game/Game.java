package game;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import move_commands.MoveCommand;
import move_commands.MoveDown;
import move_commands.MoveLeft;
import move_commands.MoveRight;
import move_commands.MoveUp;
import piece.AbstractPiece;
import piece.Owner;
import piece.Rabbit;

public class Game {
	private ArrayList<MoveCommand> moves = new ArrayList<MoveCommand>();
	public BoardState currentBoard = null;
	private int turnNumber;

	int moveTimer = 0;
	int p1TimeBank = 0;
	int p2TimeBank = 0;
	int turnCounter = 0;

	String p1Name = "Player1";
	String p2Name = "Player2";
	// 0 is nobody, 1 is player1, 2 is player2
	private int winner = 0;
	private int numMoves = 4;
	private int playerTurn = 1;
	private boolean isPushPull;

	/**
	 * Creates a board with a default starting layout
	 */
	public Game() {
		currentBoard = new BoardState();
	}

	public Game(BoardState b) {
		currentBoard = b;
	}
	
	public BoardState getBoardState() {
		return this.currentBoard;
	}

	public int getMoveTimer() {
		return moveTimer;
	}

	public void setMoveTimer(int moveTimer) {
		this.moveTimer = moveTimer;
	}

	public void setP1Name(String p1Name) {
		this.p1Name = p1Name;
	}

	public void setP2Name(String p2Name) {
		this.p2Name = p2Name;
	}

	public int getTurnNumber() {
		return turnNumber;
	}

	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}

	public void incrementTurn() {
		this.turnNumber++;
	}

	public int getTurnCounter() {
		return this.turnCounter;
	}

	public String getP1Name() {
		return this.p1Name;
	}

	public String getP2Name() {
		return this.p2Name;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	public int getNumMoves() {
		return numMoves;
	}

	public int getTurnTimer() {
		return moveTimer;
	}

	public int getWinner() {
		return winner;
	}

	public int getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(int playerTurn) {
		this.playerTurn = playerTurn;
	}

	@Deprecated
	public AbstractPiece getSpace(int row, int column) {
		return this.currentBoard.getPieceAt(new Coordinate(row, 7-column));
	}
	
	// refactor for future pull request
	public boolean checkCoor(int row, int column) {
		return this.checkCoor(new Coordinate(row, 7-column));
	}
	
	public boolean checkCoor(Coordinate coor) {
		return this.currentBoard.pieceAt(coor);
	}

	/**
	 * 
	 * @param row
	 * @param column
	 * @param dir
	 *            0: up, 1: right, 2: down, 3: left
	 * @return returns true if the move made successfully, otherwise returns false
	 */
	public boolean move(int row, int column, int dir) {
		MoveCommand moveToMake;
		if (!isValidMoveFromSquare(row, column))
			return false;
		switch (dir) {
		case 0:
			// Moving UP
			moveToMake = new MoveUp(this.currentBoard);
			return makeMove(moveToMake, row, column);
		case 1:
			// Moving RIGHT
			moveToMake = new MoveRight(this.currentBoard);
			return makeMove(moveToMake, row, column);
		case 2:
			// Moving DOWN
			moveToMake = new MoveDown(this.currentBoard);
			return makeMove(moveToMake, row, column);
		case 3:
			// Moving LEFT
			moveToMake = new MoveLeft(this.currentBoard);
			return makeMove(moveToMake, row, column);
		default:
			return false;
		}
	}

	/**
	 * 
	 * @param moveToMake
	 * @param row
	 * @param column
	 * @return returns true if the move made successfully, otherwise returns false
	 */

	private boolean makeMove(MoveCommand moveToMake, int row, int column) {
		if (moveToMake.isValidMove(row, column)) {
			this.currentBoard = moveToMake.execute(row, column);
			this.moves.add(moveToMake);
			endMove();
			return true;
		}
		return false;
	}

	private boolean isValidMoveFromSquare(int row, int column) {
		if (getSpace(row, column) == null)
			return false;
		// This may cause issues when we implement undo/redo if we try invalid
		// moves before we undo
		if (getSpace(row, column).getOwner() != Owner.values()[(getPlayerTurn() - 1)] && !isPushPull) {
			return false;// not your turn
		}
		if ((checkStrongerAdjacent(row, column) && !checkFriendlyAdjacent(row, column)) && !isPushPull) {
			return false;// can't move
		}
		return true;
	}

	/**
	 * This methods checks piece death and victory conditions
	 */
	private void endMove() {
		checkDeaths(2, 2);
		checkDeaths(2, 5);
		checkDeaths(5, 2);
		checkDeaths(5, 5);
		checkWin();
		numMoves--;
		if (numMoves <= 0) {
			if (getPlayerTurn() == 1) {
				setPlayerTurn(2);
			} else {
				setPlayerTurn(1);
			}
			numMoves = 4;
			turnCounter++;
		}
	}

	/**
	 * checks both rows for rabbits of the opposite side, top row first followed by the bottom row
	 */
	private void checkWin() {
		for (int i = 0; i < 8; i++) {
			if (getSpace(0, i) != null) {
				if (getSpace(0, i).equals(new Rabbit(Owner.Player2))) {
					winner = 2;
				}
			}
		}
		for (int i = 0; i < 8; i++) {
			if (getSpace(7, i) != null) {
				if (getSpace(7, i).equals(new Rabbit(Owner.Player1))) {
					winner = 1;
				}
			}
		}

		boolean p1RabbitExists = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				// and short circuits if null preventing nullpointerexception
				if (getSpace(i, j) != null && getSpace(i, j).equals(new Rabbit(Owner.Player1))) {
					p1RabbitExists = true;
				}
			}
		}

		if (!p1RabbitExists) {
			winner = 2;
		}

		boolean p2RabbitExists = false;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (getSpace(i, j) != null && getSpace(i, j).equals(new Rabbit(Owner.Player2))) {
					p2RabbitExists = true;
				}
			}
		}

		if (!p2RabbitExists) {
			winner = 1;
		}
	}

	/**
	 * Piece death occurs when pieces are on the squares (2,2), (2,5), (5,2), (5,5), and has no friendly adjacent pieces
	 * to it
	 * 
	 */
	private void checkDeaths(int row, int col) {
		if (this.getSpace(row, col) == (null))
			return;// an empty piece doesn't need to be checked

		if (checkFriendlyAdjacent(row, col)) {
			return;
		}
		// no adjacent friendly pieces, remove this one
		this.currentBoard.removePiece(new Coordinate(row, col));
		// char[][] temp = this.currentBoard.getBoardArray();
		// temp[row][col] = ' ';
		// this.currentBoard.setBoardArray(temp);
	}

	public boolean checkFriendlyAdjacent(int row, int col) {
		AbstractPiece cen = this.getSpace(row, col);
		AbstractPiece up = this.getSpace(row - 1, col);
		AbstractPiece down = this.getSpace(row + 1, col);
		AbstractPiece left = this.getSpace(row, col - 1);
		AbstractPiece right = this.getSpace(row, col + 1);
		Owner own = cen.getOwner();
		if (up != null) {
			if (up.getOwner() == own)
				return true;
		}
		if (down != null) {
			if (down.getOwner() == own)
				return true;
		}
		if (right != null) {
			if (right.getOwner() == own)
				return true;
		}
		if (left != null) {
			if (left.getOwner() == own)
				return true;
		}
		return false;
	}

	public boolean checkStrongerAdjacent(int row, int col) {
		AbstractPiece cen = this.getSpace(row, col);
		AbstractPiece up = this.getSpace(row - 1, col);
		AbstractPiece down = this.getSpace(row + 1, col);
		AbstractPiece left = this.getSpace(row, col - 1);
		AbstractPiece right = this.getSpace(row, col + 1);
		@SuppressWarnings("unused")
		Owner own = cen.getOwner();
		boolean foo = false;
		if (up != null) {
			foo = checkStrong(up, cen);
		}
		if (down != null) {
			foo = checkStrong(down, cen);
		}
		if (right != null) {
			foo = checkStrong(right, cen);
		}
		if (left != null) {
			foo = checkStrong(left, cen);
		}
		return foo;
	}

	private boolean checkStrong(AbstractPiece one, AbstractPiece two) {
		if (one.getOwner() != two.getOwner() && one.isStrongerThan(two))
			return true;
		return false;
	}

	/**
	 * 0: up, 1: right, 2: down, 3: left
	 * 
	 * @param row
	 * @param column
	 * @param dir1
	 *            the direction the pushing piece will move
	 * @param dir2
	 *            the direction the pushed piece will move
	 * @return
	 */
	public boolean push(int row, int column, int dir1, int dir2) {
		if (!isValidSquareToPushFrom(row, column))
			return false;
		isPushPull = true;
		switch (dir1) {
		case 0:
			if (row - 1 >= 0) {
				AbstractPiece pushingPiece = getSpace(row, column);
				AbstractPiece pushedPiece = getSpace(row - 1, column);
				if (pieceCanPush(pushingPiece, pushedPiece) && move(row - 1, column, dir2)) {
					isPushPull = false;
					return move(row, column, dir1);
				}
			}

			break;
		case 1:
			if (column + 1 <= 7) {
				AbstractPiece pushingPiece2 = getSpace(row, column);
				AbstractPiece pushedPiece2 = getSpace(row, column + 1);
				if (pieceCanPush(pushingPiece2, pushedPiece2) && move(row, column + 1, dir2)) {
					isPushPull = false;
					return move(row, column, dir1);
				}
			}
			break;
		case 2:
			if (row + 1 <= 7) {
				AbstractPiece pushingPiece3 = getSpace(row, column);
				AbstractPiece pushedPiece3 = getSpace(row + 1, column);
				if (pushingPiece3.isStrongerThan(pushedPiece3)) {
					if (pieceCanPush(pushingPiece3, pushedPiece3) && move(row + 1, column, dir2)) {
						isPushPull = false;
						return move(row, column, dir1);

					}
				}
			}
			break;
		case 3:
			if (column - 1 >= 0) {
				AbstractPiece pushingPiece4 = getSpace(row, column);
				AbstractPiece pushedPiece4 = getSpace(row, column - 1);
				if (pieceCanPush(pushingPiece4, pushedPiece4) && move(row, column - 1, dir2)) {
					isPushPull = false;
					return move(row, column, dir1);
				}
			}

			break;
		}
		isPushPull = false;
		return false;
	}

	private boolean pieceCanPush(AbstractPiece pushingPiece, AbstractPiece pushedPiece) {
		return pushedPiece != null && pushingPiece.isStrongerThan(pushedPiece)
				&& pushingPiece.getOwner() != pushedPiece.getOwner();
	}

	private boolean isValidSquareToPushFrom(int row, int column) {
		if (numMoves <= 1)
			return false; // can't push/pull with only one move
		if (getSpace(row, column) == null) {
			return false; // trying to push with an empty square
		}
		if (getSpace(row, column).getOwner() != Owner.values()[(getPlayerTurn() - 1)])
			return false;// not your turn
		return true;
	}

	/**
	 * 0: up, 1: right, 2: down, 3: left
	 * 
	 * @param row
	 *            : row that contains the pulling piece
	 * @param column
	 *            : column that contains the pulling piece
	 * @param direction1
	 *            : direction the pulling piece will move
	 * @param direction2
	 *            : direction the piece being pulled will move
	 * @return True if pull succeeds, False if it fails
	 */
	public boolean pull(int row1, int column1, int row2, int column2, int direction1) {
		if (!isValidSquaretoPullFrom(row1, column1, row2, column2))
			return false;
		// Get direction that pulled piece will move
		int direction2 = getDirection(row2, column2, row1, column1);

		// Check that getDirection didn't fail
		isPushPull = true;
		// Attempt to perform move operations on both pieces
		switch (direction1) {
		case 0:
			if (tryPull(getSpace(row1, column1), getSpace(row2, column2), row1, column1, direction1)) {
				move(row2, column2, direction2);
				isPushPull = false;
				return true;
			}
			break;
		case 1:
			if (tryPull(getSpace(row1, column1), getSpace(row2, column2), row1, column1, direction1)) {
				move(row2, column2, direction2);
				isPushPull = false;
				return true;
			}
			break;
		case 2:
			if (tryPull(getSpace(row1, column1), getSpace(row2, column2), row1, column1, direction1)) {
				move(row2, column2, direction2);
				isPushPull = false;
				return true;
			}
			break;
		case 3:
			if (tryPull(getSpace(row1, column1), getSpace(row2, column2), row1, column1, direction1)) {
				move(row2, column2, direction2);
				isPushPull = false;
				return true;
			}
			break;
		}
		return false;
	}

	private boolean tryPull(AbstractPiece space, AbstractPiece space2, int row1, int column1, int direction1) {
		return pieceCanPush(space, space2) && move(row1, column1, direction1);
	}

	private boolean isValidSquaretoPullFrom(int row1, int column1, int row2, int column2) {
		if (numMoves <= 1)
			return false; // can't push/pull with only one move
		// Check that both pieces exist
		if (getSpace(row1, column1) == null || getSpace(row2, column2) == null) {
			return false;
		}

		// Check that pulling piece is strong than other piece
		if (!getSpace(row1, column1).isStrongerThan(getSpace(row2, column2))) {
			return false;
		}

		if (getSpace(row1, column1).getOwner() != Owner.values()[(getPlayerTurn() - 1)])
			return false;// not your turn
		return true;
	}

	/**
	 * 0: up, 1: right, 2: down, 3: left
	 * 
	 * @param row1
	 *            : row of space1
	 * @param column1
	 *            : column of space1
	 * @param row2
	 *            : row of space2
	 * @param column2
	 *            : column of space2
	 * @return integer representing the direction required to move from space1 to space2
	 */
	public int getDirection(int row1, int column1, int row2, int column2) {
		if (row1 == row2) {
			if (column1 - 1 == column2)
				return 3;
			else if (column1 + 1 == column2)
				return 1;
		}
		if (column1 == column2) {
			if (row1 - 1 == row2)
				return 0;
			else if (row1 + 1 == row2)
				return 2;
		}
		return -1;
	}

	public void undoMove() {
		if (this.numMoves == 4)
			return;

		this.currentBoard = this.moves.get(this.moves.size() - (4 - this.numMoves)).getOriginalBoard();
		this.moves.remove(this.moves.size() - (4 - this.numMoves));

		this.numMoves = 4;
	}

	// doesn't work now, leave for another pull request
	public boolean loadFile(Scanner scanner) {
		scanner.useDelimiter(",");
		// BoardState boardToSet = new BoardState(
		// new char[][] { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
		// { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
		// { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
		// { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, },
		// 0);
		BoardState boardToSet = new BoardState(); // so it compiles
		String[] validBoardCharactersArray = { " ", "E", "C", "H", "D", "K", "R", "e", "c", "h", "d", "k", "r" };
		ArrayList<String> vbc = new ArrayList<String>();
		for (String s : validBoardCharactersArray) {
			vbc.add(s);
		}

		for (int i = 0; i < 8; i++) {
			for (int k = 0; k < 8; k++) {
				if (!scanner.hasNext()) {
					scanner.close();
					return false;
				}
				String next = scanner.next();
				if (!vbc.contains(next)) {
					scanner.close();
					return false;
				}
				// boardToSet.setBoardSpace(i, k, next);
			}
		}

		if (!scanner.hasNext()) {
			scanner.close();
			return false;
		}
		int turnCounter = scanner.nextInt();

		if (!scanner.hasNext()) {
			scanner.close();
			return false;
		}
		int turnTimer = scanner.nextInt();

		if (!scanner.hasNext()) {
			scanner.close();
			return false;
		}
		String p1name = scanner.next();

		if (!scanner.hasNext()) {
			scanner.close();
			return false;
		}
		String p2name = scanner.next();

		scanner.close();

		// Successful load! Push all changes to game permanently
		this.currentBoard = boardToSet;
		this.turnCounter = turnCounter;
		this.moveTimer = turnTimer;
		this.p1Name = p1name;
		this.p2Name = p2name;

		if (this.turnCounter % 2 == 1) {
			this.playerTurn = 2;
		} else {
			this.playerTurn = 1;
		}
		return true;
	}

	public boolean saveFile(FileWriter fw) {
		if (fw == null)
			return false;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				String s = "" + this.currentBoard.getBoardArray()[i][j] + ",";
				try {
					fw.write(s);
				} catch (IOException e) {
					return false;
				}
			}
		}

		String s2 = "" + this.turnCounter + ",";

		try {
			fw.write(s2);
			fw.write(this.moveTimer + ",");
			fw.write(this.p1Name + ",");
			fw.write(this.p2Name);
			fw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
