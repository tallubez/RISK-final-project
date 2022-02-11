package SetUpGame;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.GenerateFrameService;
import main.UI;

public class SelectCountries extends JFrame {

	public JFrame gameWindow;
	public JPanel panel;
	public JLabel label;
	private UI ui;

	public SelectCountries(UI ui) {
		this.ui = ui;
		this.gameWindow = ui.gameWindow;
		ui.startMenu.panel.setVisible(false);
		gameWindow.setSize(1227, 628);
		GenerateFrameService generateFrameService = new GenerateFrameService();
		panel = new JPanel();
		label = new JLabel();
		generateFrameService.setGameWindow(panel, gameWindow, 1227, 628);
		generateFrameService.createBackround(label, "Risk_game_map.png", panel, 1227, 628);
		panel.setBackground(Color.WHITE);
	}

	public void createMapBackround() {

	}

}
