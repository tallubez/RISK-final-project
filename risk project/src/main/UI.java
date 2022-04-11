package main;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import SetUpGame.PositionStartingUnits;
import SetUpGame.SelectCountries;
import board.InitiateWorldMap;
import board.WorldMap;

public class UI {
	public JFrame gameWindow;
	public StartMenu startMenu;
	public WorldMap worldMap;
	public Player[] players;
	public JList<String> leftList;
	public JScrollPane leftSP;
	public JList<String> rightList;
	public JScrollPane rightSP;
	public SelectCountries sCountries;

	public UI() {
		worldMap = new WorldMap();
		gameWindow = new JFrame();
		startMenu = new StartMenu(this);
		InitiateWorldMap initiateWorldMap = new InitiateWorldMap(this);
		players = new Player[2];
		players[0] = new Player();
		players[1] = new Player();

	}

	public void SelectCountries() {
		sCountries = new SelectCountries(this);
		sCountries.devide();
	}

	public void PositionStartUnits() {
		PositionStartingUnits pos = new PositionStartingUnits(this);

	}

	public void RunTurns() {

	}

	public Player getPlayer(int i) {
		return players[i - 1];
	}

}
