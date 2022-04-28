package CPU;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import RunGame.MoveUnits;
import board.Territory;
import main.Player;
import main.UI;

public class MoveUnitsCpu {

	public MoveUnitsCpu() {
		// TODO Auto-generated constructor stub
	}

	public static double MoveAfterConq(UI ui, Territory org, Territory dest) {
		double orgOptimizeUnits, destOptimizeUnits;
		double maxMovement = org.getUnitAmount() - 1;
		if (maxMovement == 0) {
			return 0;
		}
		double orgEnemy = Attack.GetEnemyBordering(org, null);
		if (orgEnemy == 0) {
			return maxMovement;
		}
		double destEnemy = Attack.GetEnemyBordering(dest, null);
		if (destEnemy == 0) {
			return 0;
		}
		double totalUnits = org.getUnitAmount() + dest.getUnitAmount();
		orgEnemy = orgEnemy / (orgEnemy + destEnemy);
		destEnemy = destEnemy / (orgEnemy + destEnemy);
		orgOptimizeUnits = Math.floor(totalUnits * orgEnemy);
		destOptimizeUnits = totalUnits - orgOptimizeUnits;
		if (destOptimizeUnits <= dest.getUnitAmount()) {
			return 0;
		}
		return Math.min(maxMovement, destOptimizeUnits - dest.getUnitAmount());
	}

	public static void moveEndOfTurn(UI ui, Player cpu) {
		Territory dest = null, org = null;
		double maxMovement = 0, temp;
		ArrayList<Territory> possibilities = new ArrayList<>();
		for (Territory t : cpu.territories_controling) {
			possibilities.clear();
			MoveUnits.GetTerritorysConected(ui, cpu, t, possibilities);
			for (Territory t_n : possibilities) {
				temp = MoveAfterConq(ui, t, t_n);
				if (temp > maxMovement) {
					maxMovement = temp;
					org = t;
					dest = t_n;
				}

			}
		}
		if (maxMovement > 0) {
			JOptionPane.showMessageDialog(null,
					"computer moved " + maxMovement + " units from " + org.getName() + " to " + dest.getName());
			org.subUnits(maxMovement);
			dest.addUnits(maxMovement);
			cpu.getTerritoryList().updateText(dest);
			cpu.getTerritoryList().updateText(org);
		} else {
			JOptionPane.showMessageDialog(null, "computer didn't move units");
		}

	}

}
