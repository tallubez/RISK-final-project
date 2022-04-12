package RunGame;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Utils.GenerateFrameService;
import board.Territory;
import main.Player;
import main.UI;

public class AttackTerritory {
	public UI ui;
	private JFrame attackWindow;
	private JPanel attackPanel;

	public AttackTerritory(UI ui) {
		this.ui = ui;
	}

	public static void Attack(Player attacker, Player defender, int attUints, int defUnits, Territory attTerritory,
			Territory defTerritory) {

	}

	public void InitAttackWindow() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) screenSize.getHeight() / 2;
		int width = (int) screenSize.getWidth() / 2;
		GenerateFrameService.createWindow(attackWindow, height, width);
		GenerateFrameService.setGameWindow(panel, gameWindow, 0, 0, gameWindow.getWidth(), gameWindow.getHeight());

	}

}
