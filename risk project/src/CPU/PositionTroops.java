package CPU;

import java.util.ArrayList;
import java.util.HashMap;

import board.Continent;
import board.Territory;
import main.Player;
import main.UI;

public class PositionTroops {

	public PositionTroops() {

	}

	public static HashMap<Territory, Double> DefensivePosition(UI ui, Player cpu, int amount) {
		HashMap<Territory, Double> territroyMap = new HashMap<>();
		double sum = 0;
		double temp;
		double left = amount;
		ArrayList<Object> tempList;
		for (Territory t : cpu.territories_controling) {
			tempList = CalcTerritoryImportance(ui, cpu, t, territroyMap, sum);
			territroyMap = (HashMap<Territory, Double>) tempList.get(0);
			sum = (double) tempList.get(1);
		}
		for (Territory t : cpu.territories_controling) {
			temp = Math.floor((territroyMap.get(t) / sum) * amount);
			territroyMap.remove(t);
			territroyMap.put(t, temp);
			left -= temp;
		}
		for (Territory t : cpu.territories_controling) {
			if (left > 0) {
				temp = territroyMap.get(t);
				territroyMap.remove(t);
				territroyMap.put(t, temp + 1);
				left--;
			}
		}
		return territroyMap;

	}

	public static ArrayList<Object> CalcTerritoryImportance(UI ui, Player cpu, Territory territory,
			HashMap<Territory, Double> territoryMap, double sum) {
		ArrayList<Object> list = new ArrayList<>(); // list[0] = hashmap, list[1] = sum
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
		territoryMap.put(territory, grade);
		list.add(territoryMap);
		list.add(sum);
		return list;
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
