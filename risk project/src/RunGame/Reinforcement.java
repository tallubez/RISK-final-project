package RunGame;

import Utils.JActions;
import board.Continent;
import main.Player;
import main.UI;

public class Reinforcement {

	public Reinforcement() {
	}

	/**
	 * calc the reinforcement amout
	 * 
	 * @param currentPlayer
	 * @param ui
	 * @return the amount of reinforcement
	 */
	public static int CalcReinforcement(Player currentPlayer, UI ui) {
		int total;
		total = currentPlayer.amount_controling / 3;
		if (total < 3) {
			total = 3;
		}
		for (Continent c : ui.worldMap.continents.values()) {
			if (c.getPlayer_controling() == currentPlayer.getPlayerNum()) {
				total += c.getValue();
			}
		}
		return total;
	}

	/**
	 * position the reinforcement
	 * 
	 * @param p  player
	 * @param ui
	 */
	public static void PositionReinforcementUnits(Player p, UI ui) {
		int unitAmout = CalcReinforcement(p, ui);
		JActions.PositionUnits(p, ui, unitAmout);
	}

}
