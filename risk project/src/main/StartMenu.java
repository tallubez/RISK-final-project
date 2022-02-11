package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartMenu implements ActionListener {

	public JFrame gameWindow;
	public JPanel panel;
	public JLabel label;
	public UI ui;

	public StartMenu(UI ui) {
		this.ui = ui;
		this.gameWindow = ui.gameWindow;
		createGameWindow();
		GenerateFrameService generateFrameService = new GenerateFrameService();
		panel = new JPanel();
		label = new JLabel();
		generateFrameService.setGameWindow(panel, gameWindow, 1150, 700);
		generateFrameService.createButton("1V1-180x67.jpg", 485, 350, 180, 67, "play1v1", panel, this);
		generateFrameService.createBackround(label, "boxBackround1150x700.jpg", panel, 1150, 700);
		gameWindow.setVisible(true);

	}

	public void createGameWindow() {
		gameWindow.setSize(1150, 700);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.getContentPane().setBackground(Color.black);
		gameWindow.setLayout(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ui.SelectCountries();
	}

}
