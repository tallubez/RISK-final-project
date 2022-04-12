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
		panel = new JPanel();
		label = new JLabel();
		GenerateFrameService.createWindow(gameWindow, -1, -1);
		GenerateFrameService.setGameWindow(panel, gameWindow, 0, 0, gameWindow.getWidth(), gameWindow.getHeight());
		GenerateFrameService.createButton("1V1-180x67.jpg", 485, 350, 180, 67, "play1v1", panel, this);
		GenerateFrameService.createBackround(label, "boxBackround1150x700.jpg", panel, 0, 0, gameWindow.getWidth(),
				gameWindow.getHeight());
		gameWindow.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ui.SelectCountries();
	}

}
