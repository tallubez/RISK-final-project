package main;

import java.awt.event.MouseAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import RunGame.PlayTurn;
import SetUpGame.PositionStartingUnits;
import SetUpGame.SelectCountries;
import Utils.SideList;
import board.InitiateWorldMap;
import board.WorldMap;

public class UI {
	public JFrame gameWindow;
	public StartMenu startMenu;
	public WorldMap worldMap;
	public Player[] players;
	public SideList leftList;
	public JScrollPane leftSP;
	public SideList rightList;
	public JScrollPane rightSP;
	public JPanel mainPanel;
	public JLabel mainLabel;
	public JLabel lowerLabel;
	public SelectCountries sCountries;
	public PlayTurn pTurn;
	public MouseAdapter mouseAdapter;
	public boolean VScomputer;

	public UI() {
		worldMap = new WorldMap();
		gameWindow = new JFrame();
		startMenu = new StartMenu(this);
		InitiateWorldMap initiateWorldMap = new InitiateWorldMap(this);
		players = new Player[2];
		players[0] = new Player(1);
		players[1] = new Player(2);
		pTurn = new PlayTurn(this);

	}

	public void SelectCountries() {
		sCountries = new SelectCountries(this);
		sCountries.devide();
		mainLabel = sCountries.label;
		mainPanel = sCountries.panel;
		lowerLabel = sCountries.lowerLabel;

	}

	public void PositionStartUnits() {
		PositionStartingUnits pos = new PositionStartingUnits(this);

	}

	public void RunTurns() {
		pTurn.initMouseListener();

	}

	public Player getPlayer(int i) {
		return players[i - 1];
	}

}
