package main;

import javax.swing.JFrame;

import SetUpGame.SelectCountries;
import board.InitiateWorldMap;
import board.WorldMap;

public class UI {
	public JFrame gameWindow;
	public StartMenu startMenu;
	public WorldMap worldMap;

	public UI() {
		worldMap = new WorldMap();
		gameWindow = new JFrame();
		startMenu = new StartMenu(this);
		InitiateWorldMap initiateWorldMap = new InitiateWorldMap(this);
	}

	public void SelectCountries() {
		SelectCountries sCountries = new SelectCountries(this);
	}

}
