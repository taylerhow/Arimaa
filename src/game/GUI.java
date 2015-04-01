package game;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class GUI {
	private String p1name;
	private String p2name;
	private JFrame mainFrame;
	
	public GUI(){
		this.p1name = "Player 1";
		this.p2name = "Player 2";
		this.mainFrame = new JFrame();
		this.mainFrame.setTitle("Welcome to Arimaa!");
		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
	    ImagePanel panel = new ImagePanel(new ImageIcon("resources/board.jpg").getImage());

	    JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setTitle("Welcome to Arimaa!");
	    frame.getContentPane().add(panel);
	    frame.pack();
	    
	    JButton newGameButton = new JButton();
	    newGameButton.setBackground(Color.DARK_GRAY);
	    newGameButton.setSize(100, 50);
	    newGameButton.setText("New Game");
	    newGameButton.setLocation((panel.getWidth()/4)-35, (panel.getHeight()/2)-25);
	    panel.add(newGameButton);
	    
	    JButton loadGameButton = new JButton();
	    loadGameButton.setBackground(Color.DARK_GRAY);
	    loadGameButton.setSize(100, 50);
	    loadGameButton.setText("Load Game");
	    loadGameButton.setLocation((panel.getWidth()/4)*3-65, (panel.getHeight()/2)-25);
	    panel.add(loadGameButton);
	    
	    frame.setVisible(true);
	    panel.setVisible(true);
	    newGameButton.setVisible(true);
	    loadGameButton.setVisible(true);
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

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
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