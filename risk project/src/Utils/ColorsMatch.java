package Utils;

import java.awt.Color;
import java.util.HashMap;

import board.Territory;
import board.WorldMap;

public class ColorsMatch {
	public HashMap<Color, Territory> colorToTerritoryMap;
	public WorldMap worldMap;
	private int d = 25;

	public ColorsMatch() {
		colorToTerritoryMap = new HashMap<>();
	}

	public Territory getTerritory(Color key) {
		if (colorToTerritoryMap.containsKey(key)) {
			return colorToTerritoryMap.get(key);
		}
		return colorToTerritoryMap.get(getCloseColor(key, d));
	}

	public void setTerritory(int rgb, Territory t) {
		colorToTerritoryMap.put(new Color(rgb), t);
	}

	public boolean ConatinsColor(Color key) {
		if (colorToTerritoryMap.containsKey(key)) {
			return true;
		}
		if (getCloseColor(key, d) != null) {
			return true;
		}
		return false;
	}

	public Color getCloseColor(Color c, int d) {
		double distance, r, g, b;
		for (Color color : colorToTerritoryMap.keySet()) {
			r = c.getRed() - color.getRed();
			r = r * r;
			g = c.getGreen() - color.getGreen();
			g = g * g;
			b = c.getBlue() - color.getBlue();
			b = b * b;
			distance = Math.sqrt(b + g + r);
			if (distance < d) {
				return color;
			}
		}
		return null;
	}

}
