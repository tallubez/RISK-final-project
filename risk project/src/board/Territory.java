package board;

import java.util.HashSet;

public class Territory {

	private String name;
	private Continent continent;
	private int reinforcement_amount;
	private int player_controling;
	private int unit_anoumt;
	private int RGB;
	public HashSet<Territory> borderingTerritories;
	public WorldMap worldMap;

	public int getRGB() {
		return RGB;
	}

	public void setRGB(int rGB) {
		RGB = rGB;
	}

	public Territory(String name, Continent continent, int reinforcment_amount, int RGB, WorldMap worldMap) {
		this.worldMap = worldMap;
		this.name = name;
		this.continent = continent;
		continent.AddToTerritorysList(this);
		this.reinforcement_amount = reinforcment_amount;
		this.RGB = RGB;
		worldMap.colorsMatch.setTerritory(RGB, this);
		unit_anoumt = 0;
		player_controling = 0;
		borderingTerritories = new HashSet<>();
	}

	public int getReinforcement_amount() {
		return reinforcement_amount;
	}

	public void setReinforcement_amount(int reinforcement_amount) {
		this.reinforcement_amount = reinforcement_amount;
	}

	public int getPlayer_controling() {
		return player_controling;
	}

	public void setPlayer_controling(int player_controling) {
		this.player_controling = player_controling;
	}

	public int getUnitAmount() {
		return unit_anoumt;
	}

	public void setUnitAmount(int unit_anoumt) {
		this.unit_anoumt = unit_anoumt;
	}

	public void addUnits(int unit_amount) {
		this.unit_anoumt += unit_amount;
	}

	public void subUnits(int unit_amount) {
		this.unit_anoumt -= unit_amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Continent getContinent() {
		return continent;
	}

	public void addBorder(Territory t) {
		borderingTerritories.add(t);
	}

	@Override
	public String toString() {
		return name + ", units: " + unit_anoumt;
	}

}
