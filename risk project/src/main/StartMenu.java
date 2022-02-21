package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Utils.GenerateFrameService;

public class StartMenu implements ActionListener {

	public JFrame gameWindow;
	public JPanel panel;
	public JLabel label;
	public UI ui;

	public StartMenu(UI ui) {
		this.ui = ui;
		this.gameWindow = ui.gameWindow;
		GenerateFrameService generateFrameService = new GenerateFrameService();
		panel = new JPanel();
		label = new JLabel();
		generateFrameService.createGameWindow(gameWindow);
		generateFrameService.setGameWindow(panel, gameWindow, gameWindow.getWidth(), gameWindow.getHeight());
		generateFrameService.createButton("1V1-180x67.jpg", 485, 350, 180, 67, "play1v1", panel, this);
		generateFrameService.createBackround(label, "boxBackround1150x700.jpg", panel, gameWindow.getWidth(),
				gameWindow.getHeight());
		gameWindow.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ui.SelectCountries();
	}

}
