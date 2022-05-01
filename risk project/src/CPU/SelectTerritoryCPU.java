package CPU;

import board.Continent;
import board.Territory;
import main.Player;
import main.UI;

public class SelectTerritoryCPU {
	/**
	 * decide what territory to select
	 * 
	 * @param ui
	 * @param cpu
	 * @return
	 */
	public static Territory SelectTerritory(UI ui, Player cpu) {
		Territory selectedTerritory = null;
		double maxValue = 0;
		for (Territory t : ui.worldMap.getAllTerritories()) {
			if (t.getPlayer_controling() == 0) {
				if (GetTerritoryValue(ui, t) >= maxValue) {
					selectedTerritory = t;
					maxValue = GetTerritoryValue(ui, t);
				}
			}
		}
		return selectedTerritory;
	}

	/**
	 * calc territory value
	 * 
	 * @param ui
	 * @param t
	 * @return value
	 */
	public static double GetTerritoryValue(UI ui, Territory t) {
		Continent continent = t.getContinent();
		double terEmpty = 1;
		double terEnemy = 2;
		double totalD = 0;
		for (Territory temp : continent.getAllTerritories()) {
			if (temp != t) {
				if (temp.getPlayer_controling() == 0) {
					totalD += terEmpty;
				}
				if (temp.getPlayer_controling() == 1) {
					totalD += terEnemy;
				}
			}
		}
		return continent.getValue() / totalD;
	}

}
