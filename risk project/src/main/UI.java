package main;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UI {
	private GameManager gm;
	private JFrame gameWindow;
	public JPanel panel;
	public JLabel label;

	public UI(GameManager gm) {
		this.gm = gm;
		createGameWindow();
		createBackround();
		gameWindow.setVisible(true);

	}

	public void createGameWindow() {
		gameWindow = new JFrame();
		gameWindow.setSize(1150, 700);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.getContentPane().setBackground(Color.black);
		gameWindow.setLayout(null);
	}

	public void createBackround() {
		panel = new JPanel();
		panel.setBounds(0, 0, 1150, 700);
		panel.setBackground(null);
		panel.setLayout(null);
		gameWindow.add(panel);
		label = new JLabel();
		label.setBounds(0, 0, 1150, 700);
		ImageIcon bgIcon = new ImageIcon(getClass().getClassLoader().getResource("boxBackround1150x700.jpg"));
		label.setIcon(bgIcon);
		panel.add(label);

	}
}
