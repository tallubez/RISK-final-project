package RunGame;

import board.Continent;
import main.Player;
import main.UI;

public class Reinforcement {
	public UI ui;

	public Reinforcement(UI ui) {
		this.ui = ui;
	}

	public int CalcReinforcement(Player currentPlayer) {
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

}
