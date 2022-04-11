package main;

import java.util.HashSet;

import board.Territory;

public class Player {
	public HashSet<Territory> territories_controling;
	public int amount_controling;

	public Player() {
		territories_controling = new HashSet<>();
		amount_controling = 0;
	}

	public int getAmount_controling() {
		return amount_controling;
	}

	public void setAmount_controling(int amount_controling) {
		this.amount_controling = amount_controling;
	}

	public void addTerritory(Territory t) {
		territories_controling.add(t);
		amount_controling++;
	}

	public void removeTerritory(Territory t) {
		territories_controling.remove(t);
		amount_controling--;
	}

}
