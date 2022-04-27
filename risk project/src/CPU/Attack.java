package CPU;

import java.util.HashSet;

import board.Continent;
import board.Territory;
import main.Player;
import main.UI;

public class Attack {

	public Attack() {
	}

	public static Territory DecideWhoToAttack(UI ui, Player cpu) {
		Territory selectedTerritory = null;
		double maxValue = 0;
		for (Territory t : cpu.territories_controling) {
			if (GetTotalAttackScore(ui, cpu, t) > maxValue) {
				selectedTerritory = t;
				maxValue = GetTotalAttackScore(ui, cpu, t);
			}

		}
		return selectedTerritory;

	}

	public static double GetTotalAttackScore(UI ui, Player cpu, Territory t) {
		double conqRes = calcConquerOdds(GetEnemyBordering(t, null), t.getUnitAmount());
		double UnitsMoves;
		if (conqRes < 1) {
			return 0;
		}
		if (conqRes >= 3) {
			UnitsMoves = 3;
		} else {
			UnitsMoves = conqRes;
		}
		if (HowHardWillItBeToKeep(ui, t, cpu, UnitsMoves)[0] > -1) {
			return 0;
		}
		double value = valueOfconquer(ui, t, cpu);
		value += conqRes;
		value -= HowHardWillItBeToKeep(ui, t, cpu, UnitsMoves)[0];
		return value;

	}

	public static double calcConquerOdds(double att, double def) {
		double[] result;
		while (Math.floor(att) >= 3 && Math.floor(def) >= 2) {
			result = calcAttackOdds(3, 2);
			att -= result[0];
			def -= result[1];

		}
		while (Math.floor(att) > 0 && Math.floor(def) > 0) {
			result = calcAttackOdds((int) Math.floor(att), (int) Math.floor(def));
			att -= result[0];
			def -= result[1];
		}
		return att - def;
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

	public static double[] HowHardWillItBeToKeep(UI ui, Territory t, Player cpu, double unitsInT) {
		double[] howHardToKeep = new double[2];
		double enemyUnitsBordering = GetEnemyBordering(t, null);
		howHardToKeep[0] = calcConquerOdds(enemyUnitsBordering, unitsInT);
		howHardToKeep[1] = calcConquerOdds(enemyUnitsBordering, unitsInT + getMaxMovement(ui, t, cpu));
		return howHardToKeep;

	}

	public static double getMaxMovement(UI ui, Territory t, Player cpu) {
		double max = 0;
		for (Territory temp : t.borderingTerritories) {
			if (temp.getPlayer_controling() == cpu.getPlayerNum()) {
				;
				if (temp.getUnitAmount() - 1 > max && GetEnemyBordering(temp, t) != 0) {
					max = temp.getUnitAmount() - 1;
				}
			}
		}
		return max;
	}

	public static int GetEnemyBordering(Territory t, Territory notToInclude) {
		int enemyUnitsBordering = 0;
		int playerNum = t.getPlayer_controling();
		for (Territory temp : t.borderingTerritories) {
			if (temp.getPlayer_controling() != playerNum) {
				enemyUnitsBordering += temp.getUnitAmount() - 1;
			}
		}
		if (notToInclude != null) {
			enemyUnitsBordering -= (notToInclude.getUnitAmount() - 1);
		}
		return enemyUnitsBordering;
	}

	public static double[] calcAttackOdds(int att, int def) {
		// get in value[0] attacker lost and in value[1] defender lost
		double[] value = new double[2];
		switch (att) {
		case 1: {
			if (def == 1) {
				value[0] = 0.5833;
				value[1] = 0.4167;

			} else {
				value[0] = 0.7454;
				value[1] = 0.2546;

			}
			break;

		}
		case 2: {
			if (def == 1) {
				value[0] = 0.7454;
				value[1] = 0.2546;

			} else {
				value[0] = 2 * 0.4483 + 32.41;
				value[1] = 2 * 0.2276 + 32.41;
			}
			break;

		}
		case 3: {
			if (def == 2) {
				value[0] = 2 * 0.2926 + 0.3358;
				value[1] = 2 * 0.3717 + 0.3358;

			} else {
				value[0] = 0.3403;
				value[1] = 0.6597;

			}
			break;

		}
		}
		return value;
	}

	public static Territory AttackOrigin(UI ui, Player cpu, Territory attacked) {
		Territory selected = null;
		;
		double min = 0, attWith;
		for (Territory t : attacked.borderingTerritories) {
			if (t.getUnitAmount() - 1 >= 3) {
				attWith = 3;
			} else {
				attWith = t.getUnitAmount() - 1;
			}
			if (HowHardWillItBeToKeep(ui, t, cpu, t.getUnitAmount() - attWith)[0] < min) {
				selected = t;
				min = HowHardWillItBeToKeep(ui, t, cpu, t.getUnitAmount() - attWith)[0];
			}
		}
		return selected;

	}

}
