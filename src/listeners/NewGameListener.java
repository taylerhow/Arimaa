package listeners;

import game.GUI;
import game.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameListener implements ActionListener {
    private GUI gui;

    public NewGameListener(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame settingsFrame = new JFrame();
        gui.activeFrames.add(settingsFrame);
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

        // On Mac, the bolded text causes layout issues
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
        gui.p1TextField = p1NameField;
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
        gui.p2TextField = p2NameField;
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

        Integer[] turnTimerPresets = { 30, 45, 60, 75, 90, 105, 120, 135,
                150, 165, 180 };
        JComboBox<Integer> turnTimerComboBox = new JComboBox<Integer>(
                turnTimerPresets);
        gui.timerComboBox = turnTimerComboBox;
        turnTimerComboBox.setEditable(false);
        turnTimerComboBox.setSize(110, 25);
        panel.add(turnTimerComboBox);
        turnTimerComboBox.setLocation(panel.getWidth() / 2,
                panel.getHeight() / 2);
        turnTimerComboBox.setVisible(true);

        // Set up Start Game Button
        JButton startGameButton = new JButton();
        startGameButton.setSize(110, 25);
        startGameButton.setText("Start Game");
        startGameButton.setLocation((panel.getWidth() / 2)
                - startGameButton.getWidth(), (panel.getHeight() / 2)
                + (2 * startGameButton.getHeight()));
        panel.add(startGameButton);
        startGameButton.addActionListener(new StartGameListener(gui));
        startGameButton.setVisible(true);

        // Set up Cancel Button
        JButton cancelButton = new JButton();
        cancelButton.setSize(110, 25);
        cancelButton.setText("Cancel");
        cancelButton.setLocation((panel.getWidth() / 2),
                (panel.getHeight() / 2) + (2 * cancelButton.getHeight()));
        panel.add(cancelButton);
        cancelButton.addActionListener(gui.new CancelListener());
        cancelButton.setVisible(true);
    }
}
