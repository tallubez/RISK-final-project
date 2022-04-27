package RunGame;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Utils.ColorsMatch;
import Utils.JActions;
import board.Territory;
import main.Player;
import main.UI;

public class MoveUnits {

	public MoveUnits() {
		// TODO Auto-generated constructor stub
	}

	public static void MoveUnit(UI ui, Player p) {
		int result = JOptionPane.showConfirmDialog(ui.gameWindow,
				"player " + p.getPlayerNum() + " Do you want to move units?", "", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (result == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "Press on the origin territory:");
		} else if (result == JOptionPane.NO_OPTION || result == JOptionPane.CLOSED_OPTION) {
			ui.pTurn.TurnEnd();
		}

		int currentPlayerNum = p.getPlayerNum();
		ui.mouseAdapter = new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				int x = e.getX();
				int y = e.getY();
				String s;

				int rgb = ColorsMatch.getColorAt(ui.mainPanel, new Point(x, y));
				s = (new Color(rgb, true)).toString();
				if (ui.worldMap.colorsMatch.ConatinsColor(new Color(rgb))
						&& ui.worldMap.colorsMatch.getTerritory(new Color(rgb)) != null) {
					Territory temp = ui.worldMap.colorsMatch.getTerritory(new Color(rgb));
					if (temp.getPlayer_controling() == currentPlayerNum) {
						if (temp.getUnitAmount() > 1) {

							int result = JOptionPane.showConfirmDialog(ui.gameWindow,
									"Sure you want to select: " + temp.getName(), "validate the chosen country",
									JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							if (result == JOptionPane.YES_OPTION) {
								SelecetedTer(ui, p, temp);
							}

							else {
								ui.lowerLabel
										.setText("Can't attack from " + temp + ". min 2 units in a territory for move");
							}
						} else {
							ui.lowerLabel.setText("you don't control " + temp.getName());

						}
					} else {
						s = String.valueOf(rgb);
						ui.lowerLabel.setText(s);
					}
				}
			}
		};
		for (int i = 0; i < ui.mainPanel.getMouseListeners().length; i++) {
			ui.mainPanel.removeMouseListener(ui.mainPanel.getMouseListeners()[i]);
		}
		ui.mainPanel.addMouseListener(ui.mouseAdapter);
	}

	public static void SelecetedTer(UI ui, Player p, Territory ter) {

		Object[] bordering = ter.borderingTerritories.toArray();
		Object[] bordering2x;
		ArrayList<Object> possibilities = new ArrayList<>();
		for (Object t : bordering) {

			if (p.territories_controling.contains(t) && !possibilities.contains(t)) {
				possibilities.add(t);
				bordering2x = ter.borderingTerritories.toArray();
				for (Object t2 : bordering2x) {
					if (p.territories_controling.contains(t2) && !possibilities.contains(t2)) {
						possibilities.add(t2);
					}
				}
			}

		}
		if (possibilities.size() == 0)

		{
			JOptionPane.showMessageDialog(null,
					"Territory does not have any territory bordering.\n select different territory");

		} else {
			Territory selected = (Territory) JOptionPane.showInputDialog(ui.gameWindow, "select destination",
					"who to attack", JOptionPane.PLAIN_MESSAGE, null, possibilities.toArray(), "hey");
			while (selected == null) {
				selected = (Territory) JOptionPane.showInputDialog(ui.gameWindow, " PLEASE select destination",
						"who to attack", JOptionPane.PLAIN_MESSAGE, null, possibilities.toArray(), "hey");
			}
			int moved = JActions.GetNumber(ui, 1, ter.getUnitAmount() - 1, ter, p);
			ter.subUnits(moved);
			selected.addUnits((double) moved);
			p.getTerritoryList().updateText(ter);
			p.getTerritoryList().updateText(selected);
			ui.pTurn.TurnEnd();
		}
	}
}
