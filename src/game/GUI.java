package game;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;

public class GUI {
	private String p1name;
	private String p2name;
	private JFrame activeFrame;

	public GUI() {
		this.p1name = "Player 1";
		this.p2name = "Player 2";
		JFrame mainMenuFrame = new JFrame();
		this.activeFrame = mainMenuFrame;
		mainMenuFrame.setTitle("Welcome to Arimaa!");
		mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		GUI g = new GUI();

		// Add MAIN MENU panel with appropriate background image
		ImagePanel panel = new ImagePanel(
				new ImageIcon("resources/BoardStoneBig.jpg").getImage());
		g.activeFrame.getContentPane().add(panel);
		g.activeFrame.pack();
		panel.setVisible(true);

		// Add the NEW GAME button to the Main Menu
		JButton newGameButton = new JButton();
		newGameButton.setSize(150, 75);
		newGameButton.setText("New Game");
		Font font = newGameButton.getFont();
		newGameButton.setFont(new Font(font.getName(), 4, 20));
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
		Font font2 = loadGameButton.getFont();
		loadGameButton.setFont(new Font(font2.getName(), 4, 20));
		loadGameButton.setLocation((panel.getWidth() / 4) * 3 - 110,
				(panel.getHeight() / 2) - 37);
		panel.add(loadGameButton);
		loadGameButton.setVisible(true);
		
		//Setup ActionListener for the LOAD GAME button
		loadGameButton.addActionListener(g.new loadGameListener());

		g.activeFrame.setVisible(true);
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

	public JFrame getActiveFrame() {
		return activeFrame;
	}

	public void setActiveFrame(JFrame activeFrame) {
		this.activeFrame = activeFrame;
	}

	class newGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame settingsFrame = new JFrame();
//			activeFrame.dispose();
//			activeFrame = gameFrame;
//			gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			settingsFrame.setTitle("New Game Options");
			settingsFrame.setVisible(true);
			
			ImagePanel panel = new ImagePanel(
					new ImageIcon("resources/BoardStoneBigCropped.jpg").getImage());
			settingsFrame.getContentPane().add(panel);
			settingsFrame.pack();
			panel.setVisible(true);
		}
	}
	
	class loadGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = fileChooser.showOpenDialog(activeFrame);
			if (result == JFileChooser.APPROVE_OPTION) {
			    File selectedFile = fileChooser.getSelectedFile();
			    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
			}
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