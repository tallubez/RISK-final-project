package SetUpGame;

import java.util.HashMap;

import CPU.PositionTroops;
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
		if (ui.VScomputer) {
			Player cpu = ui.getPlayer(2);
			HashMap<Territory, Double> territoryMap = PositionTroops.PositionUnits(ui, cpu, 40 - cpu.amount_controling);
			for (Territory t : territoryMap.keySet()) {
				t.addUnits(territoryMap.get(t));
				cpu.getTerritoryList().updateText(t);

			}

		} else {
			PositionStratingUnits(ui.getPlayer(2));
		}
		ui.RunTurns();

	}

	/**
	 * set all the territory units to 1
	 * 
	 * @param p player
	 */
	public void SetUnitsTo1(Player p) {
		for (Territory t : p.territories_controling) {
			t.setUnitAmount(1);
		}
	}

	/**
	 * position the units to player
	 * 
	 * @param p player
	 */
	public void PositionStratingUnits(Player p) {
		int remainig_units = 40 - p.getAmount_controling();
		JActions.PositionUnits(p, ui, remainig_units);
	}

}
