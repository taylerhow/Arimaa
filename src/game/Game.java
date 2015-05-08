package game;

import game.Piece.Owner;
import game.Piece.PieceType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	// fields
	ArrayList<BoardState> boards = new ArrayList<BoardState>();
	public BoardState currentBoard = null;
	//int numMovesLeft = 0;
	int moveTimer = 0;
	int p1TimeBank = 0;
	int p2TimeBank = 0;
	int turnCounter = 0;
	String p1Name= "";
	String p2Name = "";
	//0 is noone, 1 is player1, 2 is player2
	private int winner=0;
	private int numMoves=4;
	private int playerTurn=1;
	private boolean isPushPull;


	public Game(BoardState b) {
		currentBoard = b;
	}

	/**
	 * Creates a board with a default starting layout
	 */
	public Game() {
		currentBoard = new BoardState(new char[][] {
				{ 'K', 'D', 'H', 'C', 'E', 'H', 'D', 'K' },
				{ 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r' },
				{ 'k', 'd', 'h', 'c', 'e', 'h', 'd', 'k' }, }, 0);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Piece getSpace(int row, int column) {
		if (row < 0 || row > 7 || column < 0 || column > 7)
			return null;
		if (currentBoard.getBoardArray()[row][column] == ' ')
			return null;
		return new Piece(currentBoard.getBoardArray()[row][column]);

	}

	/**
	 * 
	 * @param row
	 * @param column
	 * @param dir
	 *            0: up, 1: right, 2: down, 3: left
	 * @return
	 */
	public boolean move(int row, int column, int dir) {
		if (getSpace(row, column) == null)
			return false;
		// This may cause issues when we implement undo/redo if we try invalid
		// moves before we undo
		if(getSpace(row, column).getOwner()!=Owner.values()[(getPlayerTurn()-1)]&&!isPushPull)
			return false;//not your turn
		
		if((checkStrongerAdjacent(row, column)&&!checkFriendlyAdjacent(row, column))&&!isPushPull)
			return false;//can't move
		boards.add(currentBoard);
		currentBoard = currentBoard.clone();
		switch (dir) {
		case 0:
			// Moving UP
			if (isValidMoveSquare(row-1, column)) {
				switchPiece(row, column, row - 1, column);
				endMove();
				return true;
			}
			return false;
		case 1:
			// Moving RIGHT
			if (isValidMoveSquare(row, column+1)) {
				switchPiece(row, column, row, column + 1);
				endMove();
				return true;
			}
			return false;
		case 2:
			// Moving DOWN
			if (isValidMoveSquare(row+1, column)) {
				switchPiece(row, column, row + 1, column);
				endMove();
				return true;
			}
			return false;
		case 3:
			// Moving LEFT
			if (isValidMoveSquare(row, column-1)) {
				switchPiece(row, column, row, column - 1);
				endMove();
				return true;
			}
			return false;
		default:
			return false;
		}
	}

	private boolean isValidMoveSquare(int row, int column) {
		if(row >= 0&&row<8&&column>=0&&column<8&&currentBoard.getBoardArray()[row][column] == ' ')
			return true;
		return false;
	}

	/**
	 * This methods checks piece death and victory conditions
	 */
	private void endMove() {
		// check(2,2)
		checkDeaths(2, 2);
		checkDeaths(2, 5);
		checkDeaths(5, 2);
		checkDeaths(5, 5);
		checkWin();
		numMoves--;
		if (numMoves <= 0) {
			System.out.print("Switched player move from " + getPlayerTurn());
			if (getPlayerTurn() == 1) {
				setPlayerTurn(2);
			} else {
				setPlayerTurn(1);
			}
			System.out.println(" to " + getPlayerTurn());
			numMoves = 4;
		}
	}

	//This method checks both rows for rabbits of the opposite side
	private void checkWin() {
		//check top row
		for(int i=0;i<8;i++){
			if(getSpace(0, i)!=null){
				if(getSpace(0, i).equals(new Piece(PieceType.Rabbit, null, Piece.Owner.Player2))){
					winner=2;
				}
			}
		}
		//check bottom row
		for(int i=0;i<8;i++){
			if(getSpace(7, i)!=null){
				if(getSpace(7, i).equals(new Piece(PieceType.Rabbit, null, Piece.Owner.Player1))){
					winner=1;
				}
			}
		}
		//noone has won
		if(winner!=0) System.out.println("Winner: "+ winner);
	}

	/**
	 * Piece death occurs when pieces are on the squares (2,2), (2,5), (5,2),
	 * (5,5), and has no friendly adjacent pieces to it
	 * 
	 */
	private void checkDeaths(int row, int col) {
		if(this.getSpace(row, col)==(null))
			return;//an empty piece doesn't need to be checked
		
		if(checkFriendlyAdjacent(row, col)){
			return;
		}
		//no adjacent friendly pieces, remove this one
		char[][] temp= this.currentBoard.getBoardArray();
		temp[row][col]=' ';
		this.currentBoard.setBoardArray(temp);
	}

	private boolean checkFriendlyAdjacent(int row, int col) {
		Piece cen=this.getSpace(row, col);
		Piece up=this.getSpace(row-1, col);
		Piece down=this.getSpace(row+1, col);
		Piece left=this.getSpace(row, col-1);
		Piece right = this.getSpace(row, col+1);
		Owner own =cen.getOwner();
		if(up!=null){
			if(up.getOwner()==own)
				return true;
		}
		if(down!=null){
			if(down.getOwner()==own)
				return true;
		}
		if(right!=null){
			if(right.getOwner()==own)
				return true;
		}
		if(left!=null){
			if(left.getOwner()==own)
				return true;
		}
		return false;
	}
	
	private boolean checkStrongerAdjacent(int row, int col) {
		Piece cen=this.getSpace(row, col);
		Piece up=this.getSpace(row-1, col);
		Piece down=this.getSpace(row+1, col);
		Piece left=this.getSpace(row, col-1);
		Piece right = this.getSpace(row, col+1);
		Owner own =cen.getOwner();
		boolean foo=false;
		if(up!=null){
			foo=checkStrong(up,cen);
		}
		if(down!=null){
			foo=checkStrong(down, cen);
		}
		if(right!=null){
			foo=checkStrong(right,cen);
		}
		if(left!=null){
			foo=checkStrong(left,cen);
		}
		return foo;
	}

	private boolean checkStrong(Piece one, Piece two) {
		if(one.getOwner()!=two.getOwner()&&one.isStrongerThan(two))
			return true;
		return false;
	}

	// helper for move
	private void switchPiece(int row1, int column1, int row2, int column2) {
		char[][] boardArray = currentBoard.getBoardArray();
		char temp = boardArray[row1][column1];

		boardArray[row1][column1] = boardArray[row2][column2];
		boardArray[row2][column2] = temp;

		currentBoard.setBoardArray(boardArray);
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
		if(numMoves<=1)
			return false; //can't push/pull with only one move
		if (getSpace(row, column) == null) {
			return false; // trying to push with an empty square
		}
		if(getSpace(row, column).getOwner()!=Owner.values()[(getPlayerTurn()-1)]&&!isPushPull)
			return false;//not your turn
		isPushPull=true;
		switch (dir1) {
		case 0:
			if (row - 1 >= 0) {
				Piece pushingPiece = getSpace(row, column);
				Piece pushedPiece = getSpace(row - 1, column);
				if (pushedPiece != null
						&& pushingPiece.isStrongerThan(pushedPiece)) {
					if (pushingPiece.getOwner() != pushedPiece.getOwner()
							&& move(row - 1, column, dir2)) {
						isPushPull=false;
						// should always be true
						return move(row, column, dir1);
					}
				}
			}
			break;
		case 1:
			if (column + 1 <= 7) {
				Piece pushingPiece2 = getSpace(row, column);
				Piece pushedPiece2 = getSpace(row, column + 1);
				if (pushedPiece2 != null
						&& pushingPiece2.isStrongerThan(pushedPiece2)) {
					if (pushingPiece2.getOwner() != pushedPiece2.getOwner()
							&& move(row, column + 1, dir2)) {
						isPushPull=false;
						// should always be true
						return move(row, column, dir1);
					}
				}
			}
			break;
		case 2:
			if (row + 1 <= 7) {
				Piece pushingPiece3 = getSpace(row, column);
				Piece pushedPiece3 = getSpace(row + 1, column);
				if (pushingPiece3.isStrongerThan(pushedPiece3)) {
					if (pushingPiece3.getOwner() != pushedPiece3.getOwner()
							&& move(row + 1, column, dir2)) {
						isPushPull=false;
						// should always be true
						return move(row, column, dir1);
					}
				}
			}
			break;
		case 3:
			if (column - 1 >= 0) {
				Piece pushingPiece4 = getSpace(row, column);
				Piece pushedPiece4 = getSpace(row, column - 1);
				if (pushingPiece4.isStrongerThan(pushedPiece4)) {
					if (pushingPiece4.getOwner() != pushedPiece4.getOwner()
							&& move(row, column - 1, dir2)) {
						isPushPull=false;
						// should always be true
						return move(row, column, dir1);
					}
				}
			}
			break;
		}
		return false;
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
	public boolean pull(int row1, int column1, int row2, int column2,
			int direction1) {
		if(numMoves<2)
			return false; //can't push/pull with only one move
		// Check that both pieces exist
		if (getSpace(row1, column1) == null || getSpace(row2, column2) == null){
			//System.out.println("Null piece");
			return false;
			}

		// Check that pulling piece is strong than other piece
		if (!getSpace(row1, column1).isStrongerThan(getSpace(row2, column2))){
			//System.out.println("Weak piece");
			return false;
		}
		
		if(getSpace(row1, column1).getOwner()!=Owner.values()[(getPlayerTurn()-1)]&&!isPushPull)
			return false;//not your turn
		// Get direction that pulled piece will move
		int direction2 = getDirection(row2, column2, row1, column1);

		// Check that getDirection didn't fail
		// if(direction2 == -1) return false;
		isPushPull=true;
		// Attempt to perform move operations on both pieces
		switch (direction1) {
		case 0:
			if (getSpace(row1, column1).getOwner() != getSpace(row2, column2)
					.getOwner() && move(row1, column1, direction1)) {
				move(row2, column2, direction2);
				isPushPull=false;
				return true;
			}
			break;
		case 1:
			if (getSpace(row1, column1).getOwner() != getSpace(row2, column2)
					.getOwner() && move(row1, column1, direction1)) {
				move(row2, column2, direction2);
				isPushPull=false;
				return true;
			}
			break;
		case 2:
			if (getSpace(row1, column1).getOwner() != getSpace(row2, column2)
					.getOwner() && move(row1, column1, direction1)) {
				move(row2, column2, direction2);
				isPushPull=false;
				return true;
			}
			break;
		case 3:
			if (getSpace(row1, column1).getOwner() != getSpace(row2, column2)
					.getOwner() && move(row1, column1, direction1)) {
				move(row2, column2, direction2);
				isPushPull=false;
				return true;
			}
			break;
		}
		return false;
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
	 * @return integer representing the direction required to move from space1
	 *         to space2
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

	public boolean loadFile(Scanner scanner) {
		//Setup to use Scanner
		scanner.useDelimiter(",");
		BoardState boardToSet = new BoardState(new char[][] {
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, }, 0);
		String[] validBoardCharactersArray = {" ", "E", "C", "H", "D", "K", "R", "e", "c", "h", "d", "k", "r"};
		ArrayList<String> vbc = new ArrayList<String>();
		for(String s : validBoardCharactersArray){
			vbc.add(s);
		}
		
		//Parse boardState
		for (int i = 0; i < 8; i++) {
			for (int k = 0; k < 8; k++) {
				if (!scanner.hasNext()) {
					scanner.close();
					System.out.println("1");
					return false;
				}
				String next = scanner.next();
				if (!vbc.contains(next)) {
					scanner.close();
					System.out.println("2");
					return false;
				}
				boardToSet.setBoardSpace(i, k, next);
			}
		}

		//Parse turnCounter, p1Name, p2Name
		if (!scanner.hasNext()) {
			scanner.close();
			System.out.println("3");
			return false;
		}
		 int turnCounter = scanner.nextInt();
		if (!scanner.hasNext()) {
			scanner.close();
			System.out.println("4");
			return false;
		}
		String p1name = scanner.next();
		if (!scanner.hasNext()) {
			scanner.close();
			System.out.println("5");
			return false;
		}
		String p2name = scanner.next();
		scanner.close();
		
		//Successful load! Push all changes to game permanently
		this.currentBoard = boardToSet;
		this.turnCounter = turnCounter;
		this.p1Name = p1name;
		this.p2Name = p2name;
		return true;
	}

	public boolean saveFile(FileWriter fw) {
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
		if(fw == null) return false;
		String s2 = "" + this.turnCounter + ",";

		try {
			fw.write(s2);
			fw.write(this.p1Name + ",");
			fw.write(this.p2Name);
			fw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	// Getters & Setters

	public int getTurnCounter() {
		return this.turnCounter;
	}

	public String getP1Name() {
		return this.p1Name;
	}

	public String getP2Name() {
		return this.p2Name;
	}
	
	public void setWinner(int winner){
		this.winner = winner;
	}

	/**
	 * @return the winner: 0 is noone, 1 is player1, 2 is player2
	 */
	public int getWinner() {
		return winner;
	}

	/**
	 * @return the playerTurn
	 */
	public int getPlayerTurn() {
		return playerTurn;
	}

	/**
	 * @param playerTurn the playerTurn to set
	 */
	public void setPlayerTurn(int playerTurn) {
		this.playerTurn = playerTurn;
	}

}
