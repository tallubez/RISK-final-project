package CPU;

import java.util.HashMap;

import board.Continent;
import board.Territory;
import main.Player;
import main.UI;

public class PositionTroops {

	public PositionTroops() {

	}

	public static HashMap<Territory, Double> PositionUnits(UI ui, Player cpu, double amount) {
		double def = Math.floor(amount / 2), off = amount - def;
		HashMap<Territory, Double> territroyMap = DefensivePosition(ui, cpu, amount);
		return territroyMap;
	}

	public static HashMap<Territory, Double> DefensivePosition(UI ui, Player cpu, double amount) {
		HashMap<Territory, Double> territroyMap = new HashMap<>();
		double sum = 0;
		double temp;
		double left = amount;
		for (Territory t : cpu.territories_controling) {
			sum = CalcTerritoryImportanceDefensive(ui, cpu, t, territroyMap, sum);
		}
		for (Territory t : cpu.territories_controling) {
			temp = Math.floor((territroyMap.get(t) / sum) * amount);
			territroyMap.remove(t);
			territroyMap.put(t, temp);
			left -= temp;
		}
		while (left > 0) {
			for (Territory t : cpu.territories_controling) {
				if (left > 0) {
					temp = territroyMap.get(t);
					territroyMap.remove(t);
					territroyMap.put(t, temp + 1);
					left--;
				}
			}
		}
		return territroyMap;
	}

	public static double CalcTerritoryImportanceDefensive(UI ui, Player cpu, Territory territory,
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
			grade = grade * (CalcValueLost(ui, cpu, territory) + CalcValueNow(ui, cpu, territory));
			sum += grade;
		}
		if (territoryMap != null) {
			territoryMap.put(territory, grade);
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
