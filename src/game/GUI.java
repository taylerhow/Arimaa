package game;

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
	
	//White pieces belong to Player1, at the top of the board
	private Image whiteRabbitImage = null;
	private Image whiteCatImage = null;
	private Image whiteDogImage = null;
	private Image whiteHorseImage = null;
	private Image whiteCamelImage = null;
	private Image whiteElephantImage = null;
	
	//Black pieces belong to Player2, at the bottom of the board
	private Image blackRabbitImage = null;
	private Image blackCatImage = null;
	private Image blackDogImage = null;
	private Image blackHorseImage = null;
	private Image blackCamelImage = null;
	private Image blackElephantImage = null;
	

	public GUI() {
		this.p1name = "Player 1";
		this.p2name = "Player 2";
		this.activeFrames = new ArrayList<JFrame>();
		JFrame mainMenuFrame = new JFrame();
		this.activeFrames.add(mainMenuFrame);
		mainMenuFrame.setTitle("Welcome to Arimaa!");
		mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Setting images
		try {
			File whiteRabbitFile = new File("resources/White rabbit.png");
			whiteRabbitImage = ImageIO.read(whiteRabbitFile);
			
			File whiteCatFile = new File("resources/White cat.png");
			whiteCatImage = ImageIO.read(whiteCatFile);
			
			File whiteDogFile = new File("resources/White dog.png");
			whiteDogImage = ImageIO.read(whiteDogFile);
			
			File whiteHorseFile = new File("resources/White horse.png");
			whiteHorseImage = ImageIO.read(whiteHorseFile);
			
			File whiteCamelFile = new File("resources/White camel.png");
			whiteCamelImage = ImageIO.read(whiteCamelFile);
			
			File whiteElephantFile = new File("resources/White elephant.png");
			whiteElephantImage = ImageIO.read(whiteElephantFile);
			
			File blackRabbitFile = new File("resources/Black rabbit.png");
			blackRabbitImage = ImageIO.read(blackRabbitFile);
			
			File blackCatFile = new File("resources/Black cat.png");
			blackCatImage = ImageIO.read(blackCatFile);
			
			File blackDogFile = new File("resources/Black dog.png");
			blackDogImage = ImageIO.read(blackDogFile);
			
			File blackHorseFile = new File("resources/Black horse.png");
			blackHorseImage = ImageIO.read(blackHorseFile);
			
			File blackCamelFile = new File("resources/Black camel.png");
			blackCamelImage = ImageIO.read(blackCamelFile);
			
			File blackElephantFile = new File("resources/Black elephant.png");
			blackElephantImage = ImageIO.read(blackElephantFile);
		}
		catch (IOException e){
			System.out.println("Setting Files in GUI Constructor Went Horrible Wrong!");
			e.printStackTrace();
		}
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
			
			gameFrame.setVisible(true);
			//Begin placing pieces
			
		}
	}
}

class ImagePanel extends JPanel {

	private Image img;

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

}