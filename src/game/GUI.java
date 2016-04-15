package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import listeners.LoadGameListener;
import listeners.NewGameListener;
import piece.AbstractPiece;

public class GUI {
	public String p1Name;
	public String p2Name;
	public ArrayList<JFrame> activeFrames;
	public Game game;
	public ImagePanel gameBoardPanel = null;
	public ImagePanel[][] boardPieces;
	public JTextField p1TextField;
	public JTextField p2TextField;
	public JComboBox<Integer> timerComboBox;
	public JLabel moveCountLabel;
	public JLabel turnCountLabel;
	public JLabel turnIndicatorLabel;
	public JLabel timerLabel;
	public TimePanel timer;

	public GUI() {
		this.p1Name = "Player 1";
		this.p2Name = "Player 2";
		p2TextField = null;
		p1TextField = null;
		this.game = new Game();
		this.boardPieces = new ImagePanel[8][8];
		this.activeFrames = new ArrayList<JFrame>();
		JFrame mainMenuFrame = new JFrame();
		this.activeFrames.add(mainMenuFrame);
		mainMenuFrame.setTitle("Welcome to Arimaa!");
		mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		GUI g = new GUI();

		// Add MAIN MENU panel with appropriate background image
		ImagePanel panel = new ImagePanel(new ImageIcon("resources/BoardStoneBig.jpg").getImage());
		g.activeFrames.get(0).getContentPane().add(panel);
		g.activeFrames.get(0).pack();
		panel.setVisible(true);

		// Add the NEW GAME button to the Main Menu
		JButton newGameButton = new JButton();
		newGameButton.setSize(150, 75);
		newGameButton.setText("New Game");
		Font newGameFont = newGameButton.getFont();
		newGameButton.setFont(new Font(newGameFont.getName(), 4, 20));
		newGameButton.setLocation((panel.getWidth() / 4) - 35, (panel.getHeight() / 2) - 37);
		panel.add(newGameButton);
		newGameButton.setVisible(true);

		// Setup ActionListener for NEW GAME button
		newGameButton.addActionListener(new NewGameListener(g));

		// Add the LOAD GAME button to the Main Menu
		JButton loadGameButton = new JButton();
		loadGameButton.setSize(150, 75);
		loadGameButton.setText("Load Game");
		Font loadGameFont = loadGameButton.getFont();
		loadGameButton.setFont(new Font(loadGameFont.getName(), 4, 20));
		loadGameButton.setLocation((panel.getWidth() / 4) * 3 - 110, (panel.getHeight() / 2) - 37);
		panel.add(loadGameButton);
		loadGameButton.setVisible(true);

		// Setup ActionListener for the LOAD GAME button
		loadGameButton.addActionListener(new LoadGameListener(g));

		g.activeFrames.get(0).setVisible(true);
	}

	public String getP1name() {
		return p1Name;
	}

	public void setP1name(String p1name) {
		this.p1Name = p1name;
	}

	public String getP2name() {
		return p2Name;
	}

	public void setP2name(String p2name) {
		this.p2Name = p2name;
	}

	public ArrayList<JFrame> getActiveFrames() {
		return activeFrames;
	}

	public void setActiveFrames(ArrayList<JFrame> frames) {
		this.activeFrames = frames;
	}

	public void renderInitialBoard() {
		if (game.getWinner() != 0) {
			createWinWindow();
		}
		BoardState boardState = this.game.getBoardState();
		for (Coordinate coor : boardState.getAllCoordinates()) {
			AbstractPiece piece = boardState.getPieceAt(coor);
			ImagePanel imgPanel = new ImagePanel(piece.getImage());
			this.gameBoardPanel.add(imgPanel);
			imgPanel.setRow(7 - coor.getY());
			imgPanel.setColumn(coor.getX());
			imgPanel.setLocation(imgPanel.getPixelX(), imgPanel.getPixelY());
			imgPanel.setVisible(true);
			this.boardPieces[7 - coor.getY()][coor.getX()] = imgPanel;
			
		}
	}

	public void renderBoard() {
		for (int i = 0; i < 8; i++) {
			for (int k = 0; k < 8; k++) {
				if (boardPieces[i][k] != null)
					this.gameBoardPanel.remove(this.boardPieces[i][k]);
				this.boardPieces[i][k] = null;
			}
		}
		moveCountLabel.setText("<html> <b>" + "Moves Left: \n" + game.getNumMoves() + "</b></html>");
		turnCountLabel.setText("<html> <b>" + "Turn: " + game.getTurnCounter() + "</b></html>");
		if (game.getPlayerTurn() == 1) {
			turnIndicatorLabel.setText("<html> <b>" + game.getP1Name() + "'s turn" + "</b></html>");
		} else {
			turnIndicatorLabel.setText("<html> <b>" + game.getP2Name() + "'s turn" + "</b></html>");
		}
		renderInitialBoard();
	}

	// ACTION LISTENERS
	public class CancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame settingsFrame = activeFrames.get(1);
			activeFrames.remove(1);
			settingsFrame.dispose();
		}
	}

	public class SaveGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			File selectedFile = null;
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = fileChooser.showOpenDialog(gameBoardPanel);
			if (result == JFileChooser.APPROVE_OPTION) {
				selectedFile = fileChooser.getSelectedFile();
				FileWriter fw = null;
				try {
					fw = new FileWriter(selectedFile);
				} catch (IOException e) {
					// Shouldn't ever happen...
					System.out.println("No file selected!");
				}
				game.saveFile(fw);
			}
		}
	}

	public class UndoListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			game.undoMove();
			renderBoard();
		}
	}

	public void createWinWindow() {
		String playerName = "";
		if (this.game.getWinner() == 1)
			playerName = game.getP1Name();
		else if (this.game.getWinner() == 2)
			playerName = game.getP2Name();

		JFrame winnerFrame = new JFrame();
		activeFrames.add(winnerFrame);
		winnerFrame.setTitle("Winner!");
		winnerFrame.setLocation(650 / 2 - 324 / 2 + 5, 650 / 2 - 324 / 2 + 44);
		winnerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		winnerFrame.setVisible(true);

		ImagePanel panel = new ImagePanel(new ImageIcon("resources/BoardStoneBigCropped.jpg").getImage());
		winnerFrame.getContentPane().add(panel);
		winnerFrame.pack();
		panel.setVisible(true);

		// Set Up winner name Label
		JLabel winnerLabel = new JLabel();
		winnerLabel.setText("<html> <div style=\"text-align: center;\"> <b>" + playerName + " Wins!" + "</b></html>");
		winnerLabel.setForeground(Color.WHITE);
		Font winnerFont = winnerLabel.getFont();
		winnerLabel.setFont(new Font(winnerFont.getName(), 4, 24));
		winnerLabel.setSize(150, 150);
		panel.add(winnerLabel);
		winnerLabel.setLocation(winnerFrame.getWidth() / 2 - 75, winnerFrame.getHeight() / 2 - 87);
		winnerLabel.setVisible(true);
	}
}
