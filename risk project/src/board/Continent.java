package board;

import java.util.ArrayList;

public class Continent {
	private String name;
	private ArrayList<Territory> territorysList;
	private int size;
	private int player_controling;

	public Continent(String name) {
		this.name = name;
		territorysList = new ArrayList<Territory>();
		size = 0;
		player_controling = -1;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Territory> getTerritorysList() {
		return territorysList;
	}

	public void AddToTerritorysList(Territory t) {
		territorysList.add(t);
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

}
