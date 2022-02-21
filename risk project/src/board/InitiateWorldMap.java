package board;

import main.UI;

public class InitiateWorldMap {

	public WorldMap worldMap;
	public Continent northAmerica;
	public Continent southAmerica;
	public Continent Africa;
	public Continent Asia;
	public Continent Europe;
	public Continent Australia;

	public InitiateWorldMap(UI ui) {
		worldMap = ui.worldMap;
		InitiateContinents();
		InitiateTerritorys();

	}

	public void InitiateContinents() {
		northAmerica = new Continent("North America", worldMap);
		southAmerica = new Continent("South America", worldMap);
		Africa = new Continent("Africa", worldMap);
		Asia = new Continent("Asia", worldMap);
		Europe = new Continent("North America", worldMap);
		Australia = new Continent("North America", worldMap);
	}

	public void InitiateTerritorys() {
		// South America
		Territory brasil = new Territory("Brazil", southAmerica, 2, -8372160, worldMap);
		Territory argentina = new Territory("Argentina", southAmerica, 1, -131072, worldMap);
		Territory peru = new Territory("Peru", southAmerica, 3, -8388608, worldMap);
		Territory venezuela = new Territory("Venezuela", southAmerica, 4, -98175, worldMap);
		// South America borders
		setBorder(argentina, peru);
		setBorder(argentina, brasil);
		setBorder(brasil, peru);
		setBorder(brasil, venezuela);
		setBorder(peru, venezuela);
		// North America
		Territory mexico = new Territory("Mexico", northAmerica, 3, 0, worldMap);
		Territory westUSA = new Territory("WestUSA", northAmerica, 9, 0, worldMap);
		Territory eastUSA = new Territory("EastUSA", northAmerica, 4, 0, worldMap);
		Territory alaska = new Territory("Alaska", northAmerica, 1, 0, worldMap);
		Territory alberta = new Territory("Alberta", northAmerica, 2, 0, worldMap);
		Territory northCanada = new Territory("NorthCanada", northAmerica, 6, 0, worldMap);
		Territory eastCanada = new Territory("EastCanada", northAmerica, 8, 0, worldMap);
		Territory onterio = new Territory("Onterio", northAmerica, 7, 0, worldMap);
		Territory greenland = new Territory("Greenland", northAmerica, 5, 0, worldMap);
		// North America borders
		setBorder(mexico, venezuela);
		setBorder(mexico, westUSA);
		setBorder(mexico, eastUSA);
		setBorder(westUSA, alberta);
		setBorder(westUSA, eastUSA);
		setBorder(westUSA, onterio);
		setBorder(eastUSA, onterio);
		setBorder(eastUSA, eastCanada);
		setBorder(onterio, eastCanada);
		setBorder(onterio, northCanada);
		setBorder(onterio, alberta);
		setBorder(alberta, alaska);
		setBorder(alberta, northCanada);
		setBorder(northCanada, alaska);
		setBorder(greenland, northCanada);
		setBorder(greenland, eastCanada);
		setBorder(greenland, onterio);
		// Europe
		Territory iceland = new Territory("Iceland", Europe, 2, 0, worldMap);
		Territory britain = new Territory("Britain", Europe, 1, 0, worldMap);
		Territory norway = new Territory("Norway", Europe, 4, 0, worldMap);
		Territory westEurope = new Territory("WestEurope", Europe, 7, 0, worldMap);
		Territory germany = new Territory("Germany", Europe, 3, 0, worldMap);
		Territory southEurope = new Territory("SouthEurope", Europe, 6, 0, worldMap);
		Territory eastEurope = new Territory("EastEurope", Europe, 6, 0, worldMap);
		// Africa
		Territory northAfrica = new Territory("NorthAfrica", Africa, 3, 0, worldMap);
		Territory westAfrica = new Territory("WestAfrica", Africa, 5, 0, worldMap);
		Territory eastAfrica = new Territory("EastAfrica", Africa, 2, 0, worldMap);
		Territory southAfrica = new Territory("SouthAfrica", Africa, 6, 0, worldMap);
		Territory centralAfrica = new Territory("CentalAfrica", Africa, 1, 0, worldMap);
		Territory madagascar = new Territory("Madagascar", Africa, 4, 0, worldMap);
		// Asia
		Territory middleEast = new Territory("MiddleEast", Asia, 7, 0, worldMap);
		Territory india = new Territory("India", Asia, 3, 0, worldMap);
		Territory viatnam = new Territory("Viatnam", Asia, 9, 0, worldMap);
		Territory southChina = new Territory("SouthChina", Asia, 2, 0, worldMap);
		Territory northChina = new Territory("NorthChina", Asia, 8, 0, worldMap);
		Territory afaganistan = new Territory("Afganistan", Asia, 1, 0, worldMap);
		Territory Oral = new Territory("Oral", Asia, 11, 0, worldMap);
		Territory yakutsk = new Territory("Yakutsk", Asia, 12, 0, worldMap);
		Territory EastRussia = new Territory("EastRussia", Asia, 6, 0, worldMap);
		Territory japan = new Territory("Japan", Asia, 5, 0, worldMap);
		Territory siberia = new Territory("Siberia", Asia, 10, 0, worldMap);
		// Australia
		Territory indonesia = new Territory("Indonesia", Australia, 2, 0, worldMap);
		Territory newGuinea = new Territory("NewGuinea", Australia, 3, 0, worldMap);
		Territory WestAustralia = new Territory("WestAustralia", Australia, 4, 0, worldMap);
		Territory EastAustralia = new Territory("EastAustralia", Australia, 1, 0, worldMap);

	}

	public void setBorder(Territory t1, Territory t2) {
		t1.addBorder(t2);
		t2.addBorder(t1);
	}

}
