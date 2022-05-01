package CPU;

import java.util.HashSet;

import board.Continent;
import board.Territory;
import main.Player;
import main.UI;

public class Attack {

	public Attack() {
	}

	/**
	 * ai decide who to attack
	 * 
	 * @param ui
	 * @param cpu ai player
	 * @return territory selected
	 */
	public static Territory DecideWhoToAttack(UI ui, Player cpu) {
		Territory selectedTerritory = null;
		double maxValue = 0;
		double totalAttackScore;
		Player opp = ui.getPlayer(1);
		for (Territory t : opp.territories_controling) {
			totalAttackScore = GetTotalAttackScore(ui, cpu, t);
			if (totalAttackScore > maxValue) {
				selectedTerritory = t;
				maxValue = totalAttackScore;
			}

		}
		return selectedTerritory;

	}

	/**
	 * calc the attack score of territory
	 * 
	 * @param ui
	 * @param cpu ai player
	 * @param t   territory
	 * @return double the score
	 */
	public static double GetTotalAttackScore(UI ui, Player cpu, Territory t) {
		double conqRes = calcConquerOdds(GetEnemyBordering(t, null), t.getUnitAmount());
		if (conqRes < 0) {
			return 0;
		}
		double value = valueOfconquer(ui, t, cpu);
		value += conqRes;
		value -= HowHardWillItBeToKeep(ui, t, cpu);
		return value;

	}

	/**
	 * calc the avg conquer outcome
	 * 
	 * @param att attacking units
	 * @param def defending units
	 * @return units reaming after conquer
	 */

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

	/**
	 * calc the value of conquering
	 * 
	 * @param ui
	 * @param t   territory to calc
	 * @param cpu
	 * @return the value
	 */
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
		double totalValue = 0;
		if (amountOfEnemyTersInCont == 0) {
			totalValue = c.getValue();
		} else {
			totalValue = c.getValue() * (amountOfUnitsClose / amountOfEnemyUnitsInCont);
		}
		// does it stops opponent from conquering a continent
		if (c.getPlayer_controling() != cpu.getPlayerNum() && c.getPlayer_controling() != -1) {
			totalValue += c.getValue();
		}
		return totalValue;
	}

	/**
	 * calc how hard will it be to keep after
	 * 
	 * @param ui
	 * @param t
	 * @param cpu
	 * @return value of difficult
	 */
	public static double HowHardWillItBeToKeep(UI ui, Territory t, Player cpu) {
		double howHardToKeep;
		double enemyUnitsBordering = 0;
		for (Territory temp : t.borderingTerritories) {
			if (temp.getPlayer_controling() != cpu.getPlayerNum()) {
				enemyUnitsBordering += (temp.getUnitAmount() - 1);
			}
		}
		howHardToKeep = enemyUnitsBordering / 5;
		return howHardToKeep;

	}

	/**
	 * get amount of enemy units bordering a territory
	 * 
	 * @param t            territory to check
	 * @param notToInclude territory not to include
	 * @return amount
	 */
	public static int GetEnemyBordering(Territory t, Territory notToInclude) {
		int enemyUnitsBordering = 0;
		int playerNum = t.getPlayer_controling();
		for (Territory temp : t.borderingTerritories) {
			if (temp.getPlayer_controling() != playerNum) {
				enemyUnitsBordering += (temp.getUnitAmount() - 1);
			}
		}
		if (notToInclude != null) {
			enemyUnitsBordering -= (notToInclude.getUnitAmount() - 1);
		}
		return enemyUnitsBordering;
	}

	/**
	 * get the average loses of attack
	 * 
	 * @param att attacking units
	 * @param def defending units
	 * @return double[] value, in value[0] attacker lost and in value[1] defender
	 *         lost
	 */
	public static double[] calcAttackOdds(int att, int def) {
		// get in value[0] attacker lost and in value[1] defender lost
		if (def > 2) {
			def = 2;
		}
		if (att > 3) {
			att = 3;
		}
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
				value[0] = 0.4213;
				value[1] = 0.5787;

			} else {
				value[0] = 2 * 0.4483 + 0.3241;
				value[1] = 2 * 0.2276 + 0.3241;
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

	/**
	 * select territory to attack from
	 * 
	 * @param ui
	 * @param cpu
	 * @param attacked
	 * @return selected territory
	 */
	public static Territory AttackOrigin(UI ui, Player cpu, Territory attacked) {
		Territory selected = null;
		double max = 1;
		for (Territory t : attacked.borderingTerritories) {
			if (t.getPlayer_controling() == cpu.getPlayerNum()) {
				if (t.getUnitAmount() > max) {
					selected = t;
					max = t.getUnitAmount();
				}
			}
		}
		return selected;
	}
}
