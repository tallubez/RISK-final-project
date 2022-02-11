package main;

import javax.swing.JFrame;

import SetUpGame.SelectCountries;

public class UI {
	public JFrame gameWindow;
	public StartMenu startMenu;

	public UI() {
		gameWindow = new JFrame();
		startMenu = new StartMenu(this);
	}

	public void SelectCountries() {
		SelectCountries sCountries = new SelectCountries(this);
	}

}
