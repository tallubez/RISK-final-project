package CPU;

import java.util.HashSet;

import board.Continent;
import board.Territory;
import main.Player;
import main.UI;

public class Attack {

	public Attack() {
	}

	public static double calcConquerOdds(double att, double def) {
		return 0;

	}

	public static void name() {

	}

	public static double valueOfconquer(UI ui, Territory t, Player cpu) {
		Continent c = t.getContinent();
		// does it help player conquer continent?
		int amountOfEnemyUnitsInCont = 0;
		int amountOfEnemyTersInCont = 0;
		int amountOfUnitsClose = 0;
		HashSet<Territory> visited = new HashSet<>();
		for (Territory tempEnemy : c.getAllTerritories()) {
			if (tempEnemy.getPlayer_controling() != cpu.getPlayerNum() && tempEnemy != t) {
				amountOfEnemyUnitsInCont += tempEnemy.getUnitAmount();
				amountOfEnemyTersInCont++;
				for (Territory tempCpu : tempEnemy.borderingTerritories) {
					if (!visited.contains(tempCpu)) {
						amountOfUnitsClose += tempCpu.getUnitAmount();
						visited.add(tempCpu);
					}

				}

			}

		}
		int AvgEnemyUnits = amountOfEnemyTersInCont / amountOfEnemyUnitsInCont;
		int AvgCloseUnits = amountOfUnitsClose / amountOfEnemyTersInCont;
		double totalValue = Math.pow(calcConquerOdds(AvgCloseUnits, AvgEnemyUnits), amountOfEnemyTersInCont)
				* c.getValue();

		// does it stops opponent from conquering a continent
		if (c.getPlayer_controling() != cpu.getPlayerNum() && c.getPlayer_controling() != -1) {
			totalValue += c.getValue();
		}
		return totalValue;
	}

	public static double[] HowHardWillItBeToKeep(UI ui, Territory t, Player cpu, double conqurResult) {
		double[] howHardToKeep = new double[2];
		double enemyUnitsBordering = 0;
		for (Territory temp : t.borderingTerritories) {
			if (temp.getPlayer_controling() != cpu.getPlayerNum()) {
				enemyUnitsBordering += temp.getUnitAmount() - 1;
			}
		}
		double UnitsInT;
		if (conqurResult >= 3) {
			UnitsInT = 3;
		} else {
			UnitsInT = conqurResult;
		}
		howHardToKeep[0] = calcConquerOdds(enemyUnitsBordering, UnitsInT);

	}

	public static double HowHardWillItBeToKeepWithMovement(UI ui, Territory t, Player cpu) {
		for (Territory temp : t.borderingTerritories) {

		}

	}

	public static double calcAttackOdds(int att, int def) {
		double value = 0;
		switch (att) {
		case 1: {
			if (def == 1) {
				value = 0.4167 - 0.5833;

			} else {
				value = 0.2546 - 0.7454;

			}
			break;

		}
		case 2: {
			if (def == 1) {
				value = 0.5787 - 0.4213;

			} else {
				value = 2 * 0.2276 - 2 * 0.4483;

			}
			break;

		}
		case 3: {
			if (def == 1) {
				value = 0.6597 - 0.3403;

			} else {
				value = 2 * 0.3717 - 2 * 0.2926;

			}
			break;

		}
		}
		return value;
	}

}
