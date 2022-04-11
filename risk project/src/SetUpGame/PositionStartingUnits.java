package SetUpGame;

import javax.swing.JOptionPane;

import board.Territory;
import main.Player;
import main.UI;

public class PositionStartingUnits {
	public UI ui;

	public PositionStartingUnits(UI ui) {
		this.ui = ui;
		SetUnitsTo1(ui.getPlayer(1));
		SetUnitsTo1(ui.getPlayer(2));
		PositionUnits(ui.getPlayer(1));

	}

	public void SetUnitsTo1(Player p) {
		for (Territory t : p.territories_controling) {
			t.setUnitAmount(1);
		}
	}

	public void PositionUnits(Player p) {
		int remainig_units = 100 - p.getAmount_controling();
		String temp;
		int selected_units;
		for (Territory t : p.territories_controling) {
			if (remainig_units > 0) {
				temp = (String) JOptionPane.showInputDialog(ui.gameWindow,
						"enter number of units to add for " + t.getName(), null, JOptionPane.INFORMATION_MESSAGE, null,
						null, "enter a number between 0 and " + remainig_units);
				while (temp == null || !temp.matches("[0-9]+") || Integer.parseInt(temp) < 0
						|| Integer.parseInt(temp) > remainig_units) {
					temp = (String) JOptionPane.showInputDialog(ui.gameWindow,
							"INPUT NO VALID! enter number of units to add for " + t.getName(), null,
							JOptionPane.INFORMATION_MESSAGE, null, null,
							"enter anumber between 0 and " + remainig_units);
				}
				t.addUnits(Integer.parseInt(temp));
				remainig_units -= (Integer.parseInt(temp));
			}
		}
	}

}
