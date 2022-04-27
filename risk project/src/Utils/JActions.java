package Utils;

import javax.swing.JOptionPane;

import board.Territory;
import main.Player;
import main.UI;

public class JActions {

	public JActions() {

	}

	public static void PositionUnits(Player p, UI ui, int unitAmount) {
		while (unitAmount > 0) {

			for (Territory t : p.territories_controling) {
				if (unitAmount > 0) {
					int selected = GetNumber(ui, 0, unitAmount, t, p);
					t.addUnits((double) selected);
					p.getTerritoryList().updateText(t);
					unitAmount -= (selected);
					ui.sCountries.lowerLabel.setText(String.valueOf(t.getUnitAmount()));

				}
			}
			if (unitAmount != 0) {
				JOptionPane.showMessageDialog(null, "Must assign all units to territory");
			}
		}
	}

	public static int GetNumber(UI ui, int min, int max, Territory t, Player p) {
		String temp = (String) JOptionPane.showInputDialog(ui.gameWindow,
				"enter number of units to add for " + t.getName(), "player " + p.getPlayerNum() + " Position units",
				JOptionPane.INFORMATION_MESSAGE, null, null, "enter a number between" + min + " and " + max);
		while (temp == null || !temp.matches("[0-9]+") || Integer.parseInt(temp) < min
				|| Integer.parseInt(temp) > max) {
			temp = (String) JOptionPane.showInputDialog(ui.gameWindow,
					"INPUT NO VALID! enter number of units to add for " + t.getName(), null,
					JOptionPane.INFORMATION_MESSAGE, null, null, "enter a number between" + min + " and " + max);
		}
		return Integer.parseInt(temp);

	}

}
