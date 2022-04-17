package board;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import Utils.ColorsMatch;

public class WorldMap {
	private int TerritoryAmount;
	public HashMap<String, Continent> continents;
	public ColorsMatch colorsMatch;

	public WorldMap() {
		continents = new HashMap<>();
		colorsMatch = new ColorsMatch();
		TerritoryAmount = 0;

	}

	public void AddContinent(Continent c) {
		continents.put(c.getName(), c);
		TerritoryAmount += c.getSize();
	}

	public int getTerritoryAmount() {
		return TerritoryAmount;
	}

	public void setTerritoryAmount(int territoryAmount) {
		TerritoryAmount = territoryAmount;
	}

	public Continent getContinent(String name) {
		return continents.get(name);
	}

	public Territory getTerritory(String name) {
		for (Continent c : continents.values()) {
			if (c.getTerritory(name) != null) {
				return c.getTerritory(name);
			}
		}
		return null;
	}

	public Collection<Territory> getAllTerritories() {
		Collection<Territory> territories = new HashSet<Territory>();
		for (Continent c : continents.values()) {
			territories.addAll(c.getAllTerritories());
		}
		return territories;

	}

}
