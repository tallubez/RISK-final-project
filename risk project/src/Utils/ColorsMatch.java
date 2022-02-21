package Utils;

import java.util.HashMap;

import board.Territory;
import board.WorldMap;

public class ColorsMatch {
	public HashMap<Integer, Territory> colorToTerritoryMap;
	public WorldMap worldMap;

	public ColorsMatch() {
		colorToTerritoryMap = new HashMap<>();
	}

	public Territory geTerritory(int RGB) {
		return colorToTerritoryMap.get(RGB);
	}

	public void setTerritory(int rgb, Territory t) {
		colorToTerritoryMap.put(rgb, t);
	}

	public boolean ConatinsRGB(int key) {
		return colorToTerritoryMap.containsKey(key);
	}

}
