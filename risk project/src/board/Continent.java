package board;

import java.util.Collection;
import java.util.HashMap;

public class Continent {
	private String name;
	private HashMap<String, Territory> territorysList;
	private int size;
	private int value;
	private int player_controling;
	public WorldMap worldMap;
	public int[] TerritorysControlledByPlayer;

	public Continent(String name, WorldMap worldMap, int value) {
		this.worldMap = worldMap;
		this.name = name;
		this.value = value;
		territorysList = new HashMap<>();
		size = 0;
		player_controling = -1;
		TerritorysControlledByPlayer = new int[2];
		TerritorysControlledByPlayer[0] = 0;
		TerritorysControlledByPlayer[1] = 0;
		worldMap.AddContinent(this);

	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void TerrChosen(int playerNum) {
		TerritorysControlledByPlayer[playerNum - 1]++;
		if (TerritorysControlledByPlayer[playerNum - 1] == size) {
			player_controling = playerNum;
		}
	}

	public void TerrConquered(int playerNum) {
		TerritorysControlledByPlayer[playerNum - 1]++;
		TerritorysControlledByPlayer[playerNum % 2]--;

		if (TerritorysControlledByPlayer[playerNum % 2] != size) {
			player_controling = -1;
		}
		if (TerritorysControlledByPlayer[playerNum - 1] == size) {
			player_controling = playerNum;
		}
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

	public Territory getTerritory(String name) {
		return territorysList.get(name);
	}

	public Collection<Territory> getAllTerritories() {
		return territorysList.values();
	}

}
