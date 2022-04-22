package CPU;

import java.util.HashMap;

import board.Continent;
import board.Territory;
import main.Player;
import main.UI;

public class PositionTroops {

	public PositionTroops() {

	}

	public static void DefensivePosition(UI ui, Player cpu, int amount) {
		HashMap<Territory, Double> territroyMap = new HashMap<>();
		double sum = 0;
		for (Territory t : territroyMap.keySet()) {
			territroyMap.put(t, territroyMap.get(t) / sum);

		}

	}

	public static double CalcTerritoryImportance(UI ui, Player cpu, Territory territory,
			HashMap<Territory, Double> territoryMap, double sum) {
		double amountInTerritory = territory.getUnitAmount();
		double enemyAmount = 0;
		double grade;
		for (Territory t : territory.borderingTerritories) {
			if (!cpu.territories_controling.contains(t)) {
				enemyAmount += t.getUnitAmount();
			}
		}
		grade = enemyAmount / amountInTerritory;
		if (grade > 0) {
			grade = grade * CalcValueLost(ui, cpu, territory) * CalcValueNow(ui, cpu, territory);
			territoryMap.put(territory, grade);
			sum += grade;
		}
		return sum;

	}

	public static double CalcValueNow(UI ui, Player cpu, Territory territory) {
		double value = 0.33;
		Continent cont = territory.getContinent();
		if (cont.getPlayer_controling() == cpu.getPlayerNum()) {
			value += cont.getValue();
		}
		return value;

	}

	public static double CalcValueLost(UI ui, Player cpu, Territory territory) {
		double value = 0.33;
		Continent cont = territory.getContinent();
		if (cont.TerritorysControlledByPlayer[cpu.getPlayerNum() - 1] == 1) {
			value += cont.getValue();
		}
		return value;
	}

}
