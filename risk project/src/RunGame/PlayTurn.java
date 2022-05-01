package RunGame;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import CPU.Attack;
import CPU.MoveUnitsCpu;
import CPU.PositionTroops;
import Utils.ColorsMatch;
import board.Territory;
import main.Player;
import main.UI;

public class PlayTurn implements ActionListener {
	public UI ui;
	private int currentPlayerNum;
	private Territory attTerritory;
	private Territory defTerritory;
	private int[] turnAmount = { 0, 0 };

	public PlayTurn(UI ui) {
		this.ui = ui;
		currentPlayerNum = 1;
	}

	/**
	 * create the mouse listener used to run the turns
	 */
	public void initMouseListener() {
		ui.lowerLabel.setText("player" + currentPlayerNum + " turn");
		ui.mouseAdapter = new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				int x = e.getX();
				int y = e.getY();
				int rgb = ColorsMatch.getColorAt(ui.mainPanel, new Point(x, y));
				if (ui.worldMap.colorsMatch.ConatinsColor(new Color(rgb))
						&& ui.worldMap.colorsMatch.getTerritory(new Color(rgb)) != null) {
					Territory temp = ui.worldMap.colorsMatch.getTerritory(new Color(rgb));
					if (temp.getPlayer_controling() == currentPlayerNum) {
						if (temp.getUnitAmount() < 2) {
							ui.lowerLabel
									.setText("Can't attack from " + temp + ". min 2 units in a territory for attack");
						} else {
							int result = JOptionPane.showConfirmDialog(ui.gameWindow,
									"Sure you want to attack from: " + temp.getName(), "validate the chosen country",
									JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							if (result == JOptionPane.YES_OPTION) {
								attTerritory = temp;
								SelectTerritoryToAttack();
							}
						}

					} else {
						ui.lowerLabel.setText(
								"player" + currentPlayerNum + " turn. " + temp.getName() + "isn't controlled by you");
					}
				}

			}

		};
		for (int i = 0; i < ui.mainPanel.getMouseListeners().length; i++) {
			ui.mainPanel.removeMouseListener(ui.mainPanel.getMouseListeners()[i]);
		}

		ui.mainPanel.addMouseListener(ui.mouseAdapter);

	}

	/**
	 * finish turn
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		MoveUnits.MoveUnit(ui, ui.getPlayer(currentPlayerNum));
	}

	/**
	 * manage the end of a turn
	 */
	public void TurnEnd() {
		if (!ui.VScomputer) {
			turnAmount[currentPlayerNum - 1]++;
			if (currentPlayerNum == 1) {
				currentPlayerNum = 2;
			} else {
				currentPlayerNum = 1;
			}
			if (turnAmount[currentPlayerNum - 1] != 0) {
				Reinforcement.PositionReinforcementUnits(ui.getPlayer(currentPlayerNum), ui);

			}
			ui.lowerLabel.setText("player" + currentPlayerNum + " turn");
			initMouseListener();
		} else {
			cpuTurn();

		}

	}

	/**
	 * manage ai player turn
	 */
	public void cpuTurn() {
		Player cpu = ui.getPlayer(2);
		if (turnAmount[1] == 0) {
			turnAmount[1]++;
			JOptionPane.showMessageDialog(null, "computer turn");
		} else {
			int reinforcementAmount = Reinforcement.CalcReinforcement(cpu, ui);
			JOptionPane.showMessageDialog(null, "computer turn, got reinforcement of " + reinforcementAmount);

			HashMap<Territory, Double> territoryMap = PositionTroops.PositionUnits(ui, cpu, reinforcementAmount);
			for (Territory t : territoryMap.keySet()) {
				t.addUnits(territoryMap.get(t));
				cpu.getTerritoryList().updateText(t);
			}
		}
		cpuAttackTerritory();
	}

	/**
	 * manage the ai player attacks
	 */
	public void cpuAttackTerritory() {
		Player attacker = ui.getPlayer(2);
		Player deffender = ui.getPlayer(1);
		AttackTerritory aTerritory = new AttackTerritory(ui);
		int attUnits, defUnits;
		defTerritory = Attack.DecideWhoToAttack(ui, attacker);
		if (defTerritory != null) {
			attTerritory = Attack.AttackOrigin(ui, attacker, defTerritory);
			if (attTerritory == null) {
				cpuEndTurn();
			}
			if (defTerritory.getUnitAmount() >= 2) {
				defUnits = 2;
			} else {
				defUnits = 1;
			}
			if (attTerritory.getUnitAmount() - 1 >= 3) {
				attUnits = 3;
			} else {
				attUnits = attTerritory.getUnitAmount() - 1;
			}
			JOptionPane.showMessageDialog(null, "computer attacks " + defTerritory + " from " + attTerritory);
			aTerritory.Attack(attacker, deffender, attUnits, defUnits, attTerritory, defTerritory);
		} else {
			cpuEndTurn();
		}
	}

	/**
	 * manage the end of ai player turn
	 */
	public void cpuEndTurn() {
		JOptionPane.showMessageDialog(null, "the cpu has decided to end the turn");
		MoveUnitsCpu.moveEndOfTurn(ui, ui.getPlayer(2));
		Reinforcement.PositionReinforcementUnits(ui.getPlayer(currentPlayerNum), ui);
		ui.lowerLabel.setText("player" + currentPlayerNum + " turn");
		initMouseListener();
	}

	/**
	 * manage when territory is selected to attack from
	 */
	public void SelectTerritoryToAttack() {
		Object[] bordering = attTerritory.borderingTerritories.toArray();
		Player attacker = ui.getPlayer(currentPlayerNum);
		ArrayList<Object> possibilities = new ArrayList<>();
		for (Object t : bordering) {
			if (!attacker.territories_controling.contains(t)) {
				possibilities.add(t);
			}

		}
		if (possibilities.size() == 0) {
			JOptionPane.showMessageDialog(null,
					"Territory does not have anamy territory bordering.\n select different territory");

		} else {
			defTerritory = (Territory) JOptionPane.showInputDialog(ui.gameWindow, "select terittory to attack",
					"who to attack", JOptionPane.PLAIN_MESSAGE, null, possibilities.toArray(), "hey");

			Player deffender;
			if (defTerritory == null) {
				JOptionPane.showMessageDialog(null, "you need to select territory to attack if you want to attack...");
			} else {
				if (currentPlayerNum == 1) {
					deffender = ui.getPlayer(2);
				} else {
					deffender = ui.getPlayer(1);
				}
				int attUnits, defUnits;
				if (attTerritory.getUnitAmount() >= 4) {
					attUnits = 3;
				} else {
					attUnits = attTerritory.getUnitAmount() - 1;
				}
				if (defTerritory.getUnitAmount() >= 2) {
					defUnits = 2;
				} else {
					defUnits = 1;
				}
				AttackTerritory aTerritory = new AttackTerritory(ui);
				aTerritory.Attack(attacker, deffender, attUnits, defUnits, attTerritory, defTerritory);
			}
		}
	}

}
