package RunGame;

import javax.swing.JButton;

import SetUpGame.SelectCountries;
import Utils.GenerateFrameService;
import main.UI;

public class CreateRuningGameMap {
	public UI ui;
	public JButton finishTurnButton;
	public SelectCountries sCountries;

	public CreateRuningGameMap(UI ui) {
		this.ui = ui;
		sCountries = ui.sCountries;
		GenerateFrameService generateFrameService = new GenerateFrameService();
		finishTurnButton = sCountries.finishTurnButton;

	}

}
