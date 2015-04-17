package game;

import game.Piece.Owner;
import game.Piece.PieceType;

import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class GUI {
	private String p1name;
	private String p2name;
	private ArrayList<JFrame> activeFrames;
	private Game game;
	
	private ImagePanel gameBoardPanel = null;
	private ImagePanel[][] boardPieces; 
	

	public GUI() {
		this.p1name = "Player 1";
		this.p2name = "Player 2";
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
		ImagePanel panel = new ImagePanel(new ImageIcon(
				"resources/BoardStoneBig.jpg").getImage());
		g.activeFrames.get(0).getContentPane().add(panel);
		g.activeFrames.get(0).pack();
		panel.setVisible(true);

		// Add the NEW GAME button to the Main Menu
		JButton newGameButton = new JButton();
		newGameButton.setSize(150, 75);
		newGameButton.setText("New Game");
		Font newGameFont = newGameButton.getFont();
		newGameButton.setFont(new Font(newGameFont.getName(), 4, 20));
		newGameButton.setLocation((panel.getWidth() / 4) - 35,
				(panel.getHeight() / 2) - 37);
		panel.add(newGameButton);
		newGameButton.setVisible(true);

		// Setup ActionListener for NEW GAME button
		newGameButton.addActionListener(g.new newGameListener());

		// Add the LOAD GAME button to the Main Menu
		JButton loadGameButton = new JButton();
		loadGameButton.setSize(150, 75);
		loadGameButton.setText("Load Game");
		Font loadGameFont = loadGameButton.getFont();
		loadGameButton.setFont(new Font(loadGameFont.getName(), 4, 20));
		loadGameButton.setLocation((panel.getWidth() / 4) * 3 - 110,
				(panel.getHeight() / 2) - 37);
		panel.add(loadGameButton);
		loadGameButton.setVisible(true);

		// Setup ActionListener for the LOAD GAME button
		loadGameButton.addActionListener(g.new loadGameListener());

		g.activeFrames.get(0).setVisible(true);
	}

	public String getP1name() {
		return p1name;
	}

	public void setP1name(String p1name) {
		this.p1name = p1name;
	}

	public String getP2name() {
		return p2name;
	}

	public void setP2name(String p2name) {
		this.p2name = p2name;
	}

	public ArrayList<JFrame> getActiveFrames() {
		return activeFrames;
	}

	public void setActiveFrames(ArrayList<JFrame> frames) {
		this.activeFrames = frames;
	}
	
	public void renderInitialBoard(){
		char[][] boardArray = this.game.currentBoard.getBoardArray();
		for(int row = 0; row < 8; row++){
			for(int column = 0; column < 8; column++){
				char c = boardArray[row][column];
				switch(c){
				case 'E':
					ImagePanel whiteElephantPanel = new ImagePanel(new ImageIcon("resources/White elephant.png").getImage());
					this.gameBoardPanel.add(whiteElephantPanel);
					whiteElephantPanel.setRow(row);
					whiteElephantPanel.setColumn(column);
					whiteElephantPanel.setLocation(whiteElephantPanel.getPixelX(), whiteElephantPanel.getPixelY());
					whiteElephantPanel.setVisible(true);
					this.boardPieces[row][column] = whiteElephantPanel;
					break;
				case 'C':
					ImagePanel whiteCamelPanel = new ImagePanel(new ImageIcon("resources/White camel.png").getImage());
					this.gameBoardPanel.add(whiteCamelPanel);
					whiteCamelPanel.setRow(row);
					whiteCamelPanel.setColumn(column);
					whiteCamelPanel.setLocation(whiteCamelPanel.getPixelX(), whiteCamelPanel.getPixelY());
					whiteCamelPanel.setVisible(true);
					this.boardPieces[row][column] = whiteCamelPanel;
					break;
				case 'H':
					ImagePanel whiteHorsePanel = new ImagePanel(new ImageIcon("resources/White horse.png").getImage());
					this.gameBoardPanel.add(whiteHorsePanel);
					whiteHorsePanel.setRow(row);
					whiteHorsePanel.setColumn(column);
					whiteHorsePanel.setLocation(whiteHorsePanel.getPixelX(), whiteHorsePanel.getPixelY());
					whiteHorsePanel.setVisible(true);
					this.boardPieces[row][column] = whiteHorsePanel;
					break;
				case 'D':
					ImagePanel whiteDogPanel = new ImagePanel(new ImageIcon("resources/White dog.png").getImage());
					this.gameBoardPanel.add(whiteDogPanel);
					whiteDogPanel.setRow(row);
					whiteDogPanel.setColumn(column);
					whiteDogPanel.setLocation(whiteDogPanel.getPixelX(), whiteDogPanel.getPixelY());
					whiteDogPanel.setVisible(true);
					this.boardPieces[row][column] = whiteDogPanel;
					break;
				case 'K':
					ImagePanel whiteCatPanel = new ImagePanel(new ImageIcon("resources/White cat.png").getImage());
					this.gameBoardPanel.add(whiteCatPanel);
					whiteCatPanel.setRow(row);
					whiteCatPanel.setColumn(column);
					whiteCatPanel.setLocation(whiteCatPanel.getPixelX(), whiteCatPanel.getPixelY());
					whiteCatPanel.setVisible(true);
					this.boardPieces[row][column] = whiteCatPanel;
					break;
				case 'R':
					ImagePanel whiteRabbitPanel = new ImagePanel(new ImageIcon("resources/White rabbit.png").getImage());
					this.gameBoardPanel.add(whiteRabbitPanel);
					whiteRabbitPanel.setRow(row);
					whiteRabbitPanel.setColumn(column);
					whiteRabbitPanel.setLocation(whiteRabbitPanel.getPixelX(), whiteRabbitPanel.getPixelY());
					whiteRabbitPanel.setVisible(true);
					this.boardPieces[row][column] = whiteRabbitPanel;
					break;
				case 'e':
					this.gameBoardPanel.add(this.blackElephantPanel);
//					this.activeFrames.get(0).pack();
					Coordinates coordse = new Coordinates(row, column);
					this.blackElephantPanel.setLocation(coordse.getX(), coordse.getY());
					this.blackElephantPanel.setVisible(true);
					break;
				case 'c':
					this.gameBoardPanel.add(this.blackCamelPanel);
//					this.activeFrames.get(0).pack();
					Coordinates coordsc = new Coordinates(row, column);
					this.blackCamelPanel.setLocation(coordsc.getX(), coordsc.getY());
					this.blackCamelPanel.setVisible(true);
					break;
				case 'h':
					this.gameBoardPanel.add(this.blackHorsePanel);
//					this.activeFrames.get(0).pack();
					Coordinates coordsh = new Coordinates(row, column);
					this.blackHorsePanel.setLocation(coordsh.getX(), coordsh.getY());
					this.blackHorsePanel.setVisible(true);
					break;
				case 'd':
					this.gameBoardPanel.add(this.blackDogPanel);
//					this.activeFrames.get(0).pack();
					Coordinates coordsd = new Coordinates(row, column);
					this.blackDogPanel.setLocation(coordsd.getX(), coordsd.getY());
					this.blackDogPanel.setVisible(true);
					break;
				case 'k':
					this.gameBoardPanel.add(this.blackCatPanel);
//					this.activeFrames.get(0).pack();
					Coordinates coordsk = new Coordinates(row, column);
					this.blackCatPanel.setLocation(coordsk.getX(), coordsk.getY());
					this.blackCatPanel.setVisible(true);
					break;
				case 'r':
					this.gameBoardPanel.add(this.blackRabbitPanel);
//					this.activeFrames.get(0).pack();
					Coordinates coordsr = new Coordinates(row, column);
					this.blackRabbitPanel.setLocation(coordsr.getX(), coordsr.getY());
					this.blackRabbitPanel.setVisible(true);
					break;
				default:
				}
			}
		}
	}

	class newGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame settingsFrame = new JFrame();
			activeFrames.add(settingsFrame);
			settingsFrame.setTitle("New Game Options");
			settingsFrame.setLocation(650 / 2 - 324 / 2 + 5, 650 / 2 - 324 / 2
					+ 44);
			settingsFrame.setVisible(true);

			ImagePanel panel = new ImagePanel(new ImageIcon(
					"resources/BoardStoneBigCropped.jpg").getImage());
			settingsFrame.getContentPane().add(panel);
			settingsFrame.pack();
			panel.setVisible(true);

			// Set up Player 1 Name Label and Text Field
			JLabel p1NameLabel = new JLabel();
			//On Mac, the bolded text causes layout issues
			p1NameLabel.setText("<html><b>Player 1 Name:</b><html>");
			p1NameLabel.setForeground(Color.WHITE);
			Font p1NameFont = p1NameLabel.getFont();
			p1NameLabel.setFont(new Font(p1NameFont.getName(), 4, 14));
			p1NameLabel.setSize(110, 25);
			panel.add(p1NameLabel);
			p1NameLabel.setLocation(
					panel.getWidth() / 2 - p1NameLabel.getWidth(),
					panel.getHeight() / 2 - p1NameLabel.getHeight() * 2);
			p1NameLabel.setVisible(true);

			JTextField p1NameField = new JTextField();
			p1NameField.setSize(110, 25);
			Font p1FieldFont = p1NameField.getFont();
			p1NameLabel.setFont(new Font(p1FieldFont.getName(), 4, 14));
			panel.add(p1NameField);
			p1NameField.setLocation(panel.getWidth() / 2, panel.getHeight() / 2
					- p1NameField.getHeight() * 2);
			p1NameField.setVisible(true);

			// Set up Player 2 Name Label and Text Field
			JLabel p2NameLabel = new JLabel();
			p2NameLabel.setText("<html><b>Player 2 Name:</b></hmtl>");
			p2NameLabel.setForeground(Color.WHITE);
			Font p2NameFont = p2NameLabel.getFont();
			p2NameLabel.setFont(new Font(p2NameFont.getName(), 4, 14));
			p2NameLabel.setSize(110, 25);
			panel.add(p2NameLabel);
			p2NameLabel.setLocation(
					panel.getWidth() / 2 - p2NameLabel.getWidth(),
					panel.getHeight() / 2 - p2NameLabel.getHeight());
			p2NameLabel.setVisible(true);

			JTextField p2NameField = new JTextField();
			p2NameField.setSize(110, 25);
			Font p2FieldFont = p2NameField.getFont();
			p2NameLabel.setFont(new Font(p2FieldFont.getName(), 4, 14));
			panel.add(p2NameField);
			p2NameField.setLocation(panel.getWidth() / 2, panel.getHeight() / 2
					- p2NameField.getHeight());
			p2NameField.setVisible(true);

			// Set up Turn Timer Label and Text Field
			JLabel turnTimerLabel = new JLabel();
			turnTimerLabel.setText("<html><b>Turn Timer:</b></html>");
			turnTimerLabel.setForeground(Color.WHITE);
			Font turnTimerFont = turnTimerLabel.getFont();
			turnTimerLabel.setFont(new Font(turnTimerFont.getName(), 4, 14));
			turnTimerLabel.setSize(110, 25);
			panel.add(turnTimerLabel);
			turnTimerLabel.setLocation(
					panel.getWidth() / 2 - turnTimerLabel.getWidth(),
					panel.getHeight() / 2);
			turnTimerLabel.setVisible(true);

			String[] turnTimerPresets = { "0:30", "0:45", "1:00", "1:15",
					"1:30", "1:45", "2:00", "M:SS" };
			JComboBox<String> turnTimerComboBox = new JComboBox<String>(
					turnTimerPresets);
			turnTimerComboBox.setEditable(true);
			turnTimerComboBox.setSize(110, 25);
			panel.add(turnTimerComboBox);
			turnTimerComboBox.setLocation(panel.getWidth() / 2,
					panel.getHeight() / 2);
			turnTimerComboBox.setVisible(true);

			// Set up Turn Timer Label and Text Field
			JLabel timeBankLabel = new JLabel();
			timeBankLabel.setText("<html> <b>Time Bank Cap:</b></html>");
			timeBankLabel.setForeground(Color.WHITE);
			Font timeBankFont = timeBankLabel.getFont();
			timeBankLabel.setFont(new Font(timeBankFont.getName(), 4, 14));
			timeBankLabel.setSize(110, 25);
			panel.add(timeBankLabel);
			timeBankLabel.setLocation(
					panel.getWidth() / 2 - timeBankLabel.getWidth(),
					panel.getHeight() / 2 + timeBankLabel.getHeight());
			timeBankLabel.setVisible(true);

			String[] timeBankPresets = { "0:30", "0:45", "1:00", "1:15",
					"1:30", "1:45", "2:00", "M:SS" };
			JComboBox<String> timeBankComboBox = new JComboBox<String>(
					timeBankPresets);
			timeBankComboBox.setEditable(true);
			timeBankComboBox.setSize(110, 25);
			panel.add(timeBankComboBox);
			timeBankComboBox.setLocation(panel.getWidth() / 2,
					panel.getHeight() / 2 + timeBankComboBox.getHeight());
			timeBankComboBox.setVisible(true);
			
			//Set up Start Game Button
			JButton startGameButton = new JButton();
			startGameButton.setSize(110, 25);
			startGameButton.setText("Start Game");
			startGameButton.setLocation((panel.getWidth() / 2) - startGameButton.getWidth(),
					(panel.getHeight() / 2) + (2*startGameButton.getHeight()));
			panel.add(startGameButton);
			startGameButton.addActionListener(new startGameListener());
			startGameButton.setVisible(true);
			
			//Set up Cancel Button
			JButton cancelButton = new JButton();
			cancelButton.setSize(110, 25);
			cancelButton.setText("Cancel");
			cancelButton.setLocation((panel.getWidth() / 2),
					(panel.getHeight() / 2) + (2*cancelButton.getHeight()));
			panel.add(cancelButton);
			cancelButton.addActionListener(new cancelListener());
			cancelButton.setVisible(true);
		}
	}

	class loadGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System
					.getProperty("user.home")));
			int result = fileChooser.showOpenDialog(activeFrames.get(0));
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				System.out.println("Selected file: "
						+ selectedFile.getAbsolutePath());
			}
		}
	}

	class cancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame settingsFrame = activeFrames.get(1);
			activeFrames.remove(1);
			settingsFrame.dispose();
		}
	}
	
	class startGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame settings = activeFrames.get(1);
			activeFrames.remove(1);
			settings.dispose();
			
			JFrame mainMenu = activeFrames.get(0);
			activeFrames.remove(0);
			mainMenu.dispose();
			
			JFrame gameFrame = new JFrame();
			activeFrames.add(gameFrame);
			gameFrame.setTitle("Let's Play!");
			gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			ImagePanel panel = new ImagePanel(new ImageIcon(
					"resources/BoardStoneBig.jpg").getImage());
			activeFrames.get(0).getContentPane().add(panel);
			activeFrames.get(0).pack();
			panel.setVisible(true);
			gameBoardPanel = panel;
			
			gameFrame.setVisible(true);
			//Begin placing pieces
			renderBoard();
		}
	}
}

class ImagePanel extends JPanel {

	private Image img;
	private int row;
	private int column;

	public ImagePanel(String img) {
		this(new ImageIcon(img).getImage());
	}

	public ImagePanel(Image img) {
		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
	
	public void setRow(int row){
		this.row = row;
	}
	
	public int getRow(){
		return this.row;
	}
	
	public void setColumn(int column){
		this.column = column;
	}
	
	public int getColumn(){
		return this.column;
	}
	
	public int getPixelX(){
		return this.column*80+10;
	}
	
	public int getPixelY(){
		return this.row*80+10;
	}

}