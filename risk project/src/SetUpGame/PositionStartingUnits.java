package SetUpGame;

import Utils.JActions;
import board.Territory;
import main.Player;
import main.UI;

public class PositionStartingUnits {
	public UI ui;

	public PositionStartingUnits(UI ui) {
		this.ui = ui;
		SetUnitsTo1(ui.getPlayer(1));
		SetUnitsTo1(ui.getPlayer(2));
		PositionStratingUnits(ui.getPlayer(1));
		PositionStratingUnits(ui.getPlayer(2));

	}

	public void SetUnitsTo1(Player p) {
		for (Territory t : p.territories_controling) {
			t.setUnitAmount(1);
		}
	}

	public void PositionStratingUnits(Player p) {
		int remainig_units = 40 - p.getAmount_controling();
		JActions.PositionUnits(p, ui, remainig_units);
		ui.RunTurns();
	}

}
