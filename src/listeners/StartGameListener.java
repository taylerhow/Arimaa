package listeners;

import game.GUI;
import game.Game;
import game.ImagePanel;
import game.TimePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGameListener implements ActionListener {

    private final Game game;
    private GUI gui;

    public StartGameListener(GUI gui) {
        this.gui = gui;
        this.game=gui.game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.setP1Name(gui.p1TextField.getText());
        game.setP2Name(gui.p2TextField.getText());
        game.setMoveTimer((int) gui.timerComboBox.getSelectedItem());

        if (game.getP1Name().equals(""))
            game.setP1Name("Player 1");
        if (game.getP2Name().equals(""))
            game.setP2Name("Player 2");

        JFrame settings = gui.activeFrames.get(1);
        gui.activeFrames.remove(1);
        settings.dispose();

        JFrame mainMenu = gui.activeFrames.get(0);
        gui.activeFrames.remove(0);
        mainMenu.dispose();

        JFrame gameFrame = new JFrame();
        gui.activeFrames.add(gameFrame);
        gameFrame.setTitle("Let's Play!");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImagePanel panel = new ImagePanel(new ImageIcon(
                "resources/board.jpg").getImage());
        gui.activeFrames.get(0).getContentPane().add(panel);
        gui.activeFrames.get(0).pack();
        panel.setVisible(true);
        gui.gameBoardPanel = panel;

        gui.gameBoardPanel.addMouseListener(new MovementListener(gui));
        gui.activeFrames.get(0).setBackground(Color.BLACK);

        gameFrame.setVisible(true);
        // Set Up Player1 Label
        JLabel p1Label = new JLabel();
        p1Label.setText("<html> <b>Player 1: </b></html>");
        p1Label.setForeground(Color.BLACK);
        Font p1Font = p1Label.getFont();
        p1Label.setFont(new Font(p1Font.getName(), 4, 22));
        p1Label.setSize(110, 25);
        gui.gameBoardPanel.add(p1Label);
        p1Label.setLocation(675, 25);
        p1Label.setVisible(true);

        // Set Up Player1 name Label
        JLabel p1NameLabel = new JLabel();
        p1NameLabel.setText("<html> <b>" + game.getP1Name() + "</b></html>");
        p1NameLabel.setForeground(Color.BLACK);
        Font p1NameFont = p1NameLabel.getFont();
        p1NameLabel.setFont(new Font(p1NameFont.getName(), 4, 18));
        p1NameLabel.setSize(110, 100);
        gui.gameBoardPanel.add(p1NameLabel);
        p1NameLabel.setLocation(675, 25);
        p1NameLabel.setVisible(true);

        // Set Up Player2 Label
        JLabel p2Label = new JLabel();
        p2Label.setText("<html> <b>Player 2: </b></html>");
        p2Label.setForeground(Color.BLACK);
        Font p2Font = p2Label.getFont();
        p2Label.setFont(new Font(p2Font.getName(), 4, 22));
        p2Label.setSize(110, 25);
        gui.gameBoardPanel.add(p2Label);
        p2Label.setLocation(675, 550);
        p2Label.setVisible(true);

        // Set Up Player2 name Label
        JLabel p2NameLabel = new JLabel();
        p2NameLabel.setText("<html> <b>" + game.getP2Name() + "</b></html>");
        p2NameLabel.setForeground(Color.BLACK);
        Font p2NameFont = p2NameLabel.getFont();
        p2NameLabel.setFont(new Font(p2NameFont.getName(), 4, 18));
        p2NameLabel.setSize(110, 100);
        gui.gameBoardPanel.add(p2NameLabel);
        p2NameLabel.setLocation(675, 550);
        p2NameLabel.setVisible(true);

        // Set up Turn Counter label
        JLabel turnCounterLabel = new JLabel();
        gui.turnCountLabel = turnCounterLabel;
        turnCounterLabel.setText("<html> <b>" + "Turn: "
                + game.getTurnCounter() + "</b></html>");
        turnCounterLabel.setForeground(Color.BLACK);
        Font turnCounterFont = turnCounterLabel.getFont();
        turnCounterLabel
                .setFont(new Font(turnCounterFont.getName(), 4, 18));
        turnCounterLabel.setSize(110, 25);
        gui.gameBoardPanel.add(turnCounterLabel);
        turnCounterLabel.setLocation(675, 130);
        turnCounterLabel.setVisible(true);

        // Set up Player Turn label
        JLabel playerTurnLabel = new JLabel();
        gui.turnIndicatorLabel = playerTurnLabel;
        if (game.getPlayerTurn() == 1) {
            playerTurnLabel.setText("<html> <b>" + game.getP1Name()
                    + "'s turn" + "</b></html>");
        } else {
            playerTurnLabel.setText("<html> <b>" + game.getP2Name()
                    + "'s turn" + "</b></html>");
        }
        playerTurnLabel.setForeground(Color.BLACK);
        Font playerTurnFont = playerTurnLabel.getFont();
        playerTurnLabel.setFont(new Font(playerTurnFont.getName(), 4, 18));
        playerTurnLabel.setSize(110, 50);
        gui.gameBoardPanel.add(playerTurnLabel);
        playerTurnLabel.setLocation(675, 200);
        playerTurnLabel.setVisible(true);

        // Set up move counter label
        JLabel moveCounterLabel = new JLabel();
        gui.moveCountLabel = moveCounterLabel;
        moveCounterLabel.setText("<html> <b>" + "Moves Left: \n"
                + game.getNumMoves() + "</b></html>");
        moveCounterLabel.setForeground(Color.BLACK);
        Font moveCounterFont = moveCounterLabel.getFont();
        moveCounterLabel
                .setFont(new Font(moveCounterFont.getName(), 4, 18));
        moveCounterLabel.setSize(110, 50);
        gui.gameBoardPanel.add(moveCounterLabel);
        moveCounterLabel.setLocation(675, 370);
        moveCounterLabel.setVisible(true);

        // Set up turn timer name label
        JLabel turnTimerNameLabel = new JLabel();
        turnTimerNameLabel.setText("<html> <b>" + "Turn Time:"
                + "</b></html>");
        turnTimerNameLabel.setForeground(Color.BLACK);
        Font turnTimerNameFont = turnTimerNameLabel.getFont();
        turnTimerNameLabel.setFont(new Font(turnTimerNameFont.getName(), 4,
                18));
        turnTimerNameLabel.setSize(110, 25);
        gui.gameBoardPanel.add(turnTimerNameLabel);
        turnTimerNameLabel.setLocation(675, 450);
        turnTimerNameLabel.setVisible(true);

        // Set up actual timer label
        JLabel turnTimerLabel = new JLabel();
        gui.timerLabel = turnTimerLabel;
        turnTimerLabel.setForeground(Color.BLACK);
        Font turnTimerFont = turnTimerLabel.getFont();
        turnTimerLabel.setFont(new Font(turnTimerFont.getName(), 4, 18));
        turnTimerLabel.setSize(110, 25);
        gui.gameBoardPanel.add(turnTimerLabel);
        turnTimerLabel.setLocation(675, 475);
        turnTimerLabel.setVisible(true);

        // P1 Time Panel
        TimePanel timePanel = new TimePanel(gui, game,
                (int) gui.timerComboBox.getSelectedItem(), gui.timerLabel);
        gui.timer = timePanel;

        // Set up Save Game Button
        JButton saveGameButton = new JButton();
        saveGameButton.setSize(65, 75);
        saveGameButton.setText("Save");
        saveGameButton.setLocation(660, gameFrame.getHeight() / 2 - 75);
        gui.gameBoardPanel.add(saveGameButton);
        saveGameButton.addActionListener(gui.new SaveGameListener());
        saveGameButton.setVisible(true);

        // Set up Undo Button
        JButton undoButton = new JButton();
        undoButton.setSize(65, 75);
        undoButton.setText("Undo");
        undoButton.setLocation(730, gameFrame.getHeight() / 2 - 75);
        gui.gameBoardPanel.add(undoButton);
        undoButton.addActionListener(gui.new UndoListener());
        undoButton.setVisible(true);

        gui.renderInitialBoard();
    }
}