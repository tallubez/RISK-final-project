package RunGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Utils.GenerateFrameService;
import board.Territory;
import main.Player;
import main.UI;

public class AttackTerritory implements ActionListener {
	public UI ui;
	private JFrame attackWindow;
	private JPanel attackPanel;
	private JLabel attLabel;
	private JLabel defLabel;
	public JButton rollDiceButton;
	private ArrayList<Integer> attRes;
	private ArrayList<Integer> defRes;
	private int attUnits;
	private int defUnits;
	public int[] lost;
	private Player attacker;
	private Player deffender;
	private Territory attTerritory;
	private Territory defTerritory;

	public AttackTerritory(UI ui) {
		this.ui = ui;
		lost = new int[2];
		lost[0] = 0;
		lost[1] = 0;
		attRes = new ArrayList<>();
		defRes = new ArrayList<>();
	}

	public void Attack(Player attacker, Player defender, int attUnits, int defUnits, Territory attTerritory,
			Territory defTerritory) {
		this.attUnits = attUnits;
		this.defUnits = defUnits;
		this.attacker = attacker;
		this.deffender = defender;
		this.attTerritory = attTerritory;
		this.defTerritory = defTerritory;
		InitAttackWindow();
		attLabel.setText("attacker amount of unit: " + attUnits);
		defLabel.setText("deffender amount of unit: " + defUnits);
	}

	public void InitAttackWindow() {
		attackWindow = new JFrame();
		attackPanel = new JPanel();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) screenSize.getHeight() / 2;
		int width = (int) screenSize.getWidth() / 2;
		GenerateFrameService.createWindow(attackWindow, width, height);
		GenerateFrameService.setGameWindow(attackPanel, attackWindow, 0, 0, width, height);
		attackPanel.setBackground(Color.WHITE);
		attLabel = new JLabel();
		attLabel.setBackground(Color.WHITE);
		defLabel = new JLabel();
		defLabel.setBackground(Color.WHITE);
		rollDiceButton = GenerateFrameService.createButton("rolldice.png", width / 2 - 75, height - 200, 150, 150,
				"roll dice", attackPanel, this);
		GenerateFrameService.createLabel(attLabel, attackPanel, 0, (-height / 2) + 30, width / 2, height);// ?
		GenerateFrameService.createLabel(defLabel, attackPanel, width / 2, (-height / 2) + 30, width / 2, height);// ?
		attackWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		attackWindow.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Random rn = new Random();
		for (int i = 0; i < attUnits; i++) {
			attRes.add(rn.nextInt(6) + 1);
		}
		for (int i = 0; i < defUnits; i++) {
			defRes.add(rn.nextInt(6) + 1);

		}
		SetLoses();

	}

	public void SetLoses() {
		String attOutput = "attacker result: ", defOutput = "deffender result: ";
		Collections.sort(attRes, Collections.reverseOrder());
		Collections.sort(defRes, Collections.reverseOrder());
		for (int i = 0; i < defUnits; i++) {
			if (attRes.get(i) > defRes.get(i)) {
				lost[1]++;
			} else {
				lost[0]++;
			}
		}
		attTerritory.subUnits(lost[0]);
		defTerritory.subUnits(lost[1]);
		attacker.getTerritoryList().updateText(attTerritory);
		deffender.getTerritoryList().updateText(defTerritory);
		if (attUnits == 3) {
			attLabel.setText(attOutput + attRes.get(0) + " ," + attRes.get(1) + " ," + attRes.get(2)
					+ ". attacker lost " + lost[0] + " Units");
		} else {

			if (attUnits == 2) {
				attLabel.setText(
						attOutput + attRes.get(0) + " ," + attRes.get(1) + ". attacker lost " + lost[0] + " Units");
			} else {
				attLabel.setText(attOutput + attRes.get(0) + ". attacker lost " + lost[0] + " Units");
			}
		}
		if (defUnits == 2) {
			defLabel.setText(
					defOutput + defRes.get(0) + ", " + defRes.get(1) + ". deffender lost " + lost[1] + " Units");
		} else {
			defLabel.setText(defOutput + defRes.get(0) + ". deffender lost " + lost[1] + " Units");
		}
		if (defTerritory.getUnitAmount() == 0) {
			TerritoryWon(attUnits - lost[0]);

		}

		JOptionPane.showMessageDialog(null, "attack finished");
		if (attacker.getAmount_controling() == ui.worldMap.getTerritoryAmount()) {
			JOptionPane.showMessageDialog(null, attacker.getPlayerNum() + " won the game, Thank You");
			ui.gameWindow.dispose();
		}
		attackWindow.dispose();

	}

	public void TerritoryWon(int attackingUnits) {
		defTerritory.getContinent().TerrConquered(attacker.getPlayerNum());
		deffender.removeTerritory(defTerritory);
		attacker.addTerritory(defTerritory);
		defTerritory.setUnitAmount(attackingUnits);
		attTerritory.subUnits(attackingUnits);
		defTerritory.setPlayer_controling(attacker.getPlayerNum());
		if (attacker.getAmount_controling() == 41) {
			// finished
		}
		String temp;
		int remUnits = attTerritory.getUnitAmount() - 1;
		if (remUnits > 0) {
			temp = (String) JOptionPane.showInputDialog(ui.gameWindow,
					"enter number of units to add from " + attTerritory.getName() + " to " + defTerritory.getName(),
					"player " + attacker.getPlayerNum() + " move units", JOptionPane.INFORMATION_MESSAGE, null, null,
					"enter a number between 0 and " + remUnits);
			while (temp == null || !temp.matches("[0-9]+") || Integer.parseInt(temp) < 0
					|| Integer.parseInt(temp) > attTerritory.getUnitAmount()) {
				temp = (String) JOptionPane.showInputDialog(ui.gameWindow,
						"enter number of units to add from " + attTerritory.getName() + " to " + defTerritory.getName(),
						null, JOptionPane.INFORMATION_MESSAGE, null, null, "enter anumber between 0 and " + remUnits);
			}

		}
		attacker.getTerritoryList().updateText(defTerritory);
		attacker.getTerritoryList().updateText(attTerritory);
	}

}
