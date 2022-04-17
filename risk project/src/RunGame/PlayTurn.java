package RunGame;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Utils.ColorsMatch;
import board.Territory;
import main.Player;
import main.UI;

public class PlayTurn implements ActionListener {
	public UI ui;
	private int currentPlayerNum;
	private Territory attTerritory;
	private Territory defTerritory;

	public PlayTurn(UI ui) {
		this.ui = ui;
		currentPlayerNum = 1;
		currentPlayerNum = 1;
	}

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
		if (ui.mainPanel.getMouseListeners().length == 0) {

			ui.mainPanel.addMouseListener(ui.mouseAdapter);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (currentPlayerNum == 1) {
			currentPlayerNum = 2;
		} else {
			currentPlayerNum = 1;
		}
		ui.lowerLabel.setText("player" + currentPlayerNum + " turn");
	}

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
			;

			Player deffender;
			if (currentPlayerNum == 1) {
				deffender = ui.getPlayer(2);
			} else {
				deffender = ui.getPlayer(1);
			}
			int attUnits, defUnits;
			if (attTerritory.getUnitAmount() >= 3) {
				attUnits = 3;
			} else {
				attUnits = 2;
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
