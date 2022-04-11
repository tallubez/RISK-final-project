package board;

import java.util.Collection;
import java.util.HashMap;

public class Continent {
	private String name;
	private HashMap<String, Territory> territorysList;
	private int size;
	private int player_controling;
	public WorldMap worldMap;

	public Continent(String name, WorldMap worldMap) {
		this.worldMap = worldMap;
		this.name = name;
		territorysList = new HashMap<>();
		size = 0;
		player_controling = -1;
		worldMap.AddContinent(this);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<String, Territory> getTerritorysList() {
		return territorysList;
	}

	public void AddToTerritorysList(Territory t) {
		territorysList.put(t.getName(), t);
		size++;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPlayer_controling() {
		return player_controling;
	}

	public void setPlayer_controling(int player_controling) {
		this.player_controling = player_controling;
	}

	public Territory geTerritory(String name) {
		return territorysList.get(name);
	}

	public Collection<Territory> getAllTerritories() {
		return territorysList.values();
	}

}
