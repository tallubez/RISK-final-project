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
		northAmerica = new Continent("North America", worldMap, 5);
		southAmerica = new Continent("South America", worldMap, 2);
		Africa = new Continent("Africa", worldMap, 3);
		Asia = new Continent("Asia", worldMap, 7);
		Europe = new Continent("Europe", worldMap, 5);
		Australia = new Continent("Australia", worldMap, 2);
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

		Territory mexico = new Territory("Mexico", northAmerica, 3, -126, worldMap);
		Territory westUSA = new Territory("WestUSA", northAmerica, 9, -4078976, worldMap);
		Territory eastUSA = new Territory("EastUSA", northAmerica, 4, -8421376, worldMap);
		Territory alaska = new Territory("Alaska", northAmerica, 1, -5130750, worldMap);
		Territory alberta = new Territory("Alberta", northAmerica, 2, -7170745, worldMap);
		Territory northCanada = new Territory("NorthCanada", northAmerica, 6, -11579352, worldMap);
		Territory eastCanada = new Territory("EastCanada", northAmerica, 8, -77, worldMap);
		Territory onterio = new Territory("Onterio", northAmerica, 7, -256, worldMap);
		Territory greenland = new Territory("Greenland", northAmerica, 5, -74240, worldMap);
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
		// Europe
		Territory iceland = new Territory("Iceland", Europe, 2, -3620889, worldMap);
		Territory britain = new Territory("Britain", Europe, 1, -5188649, worldMap);
		Territory norway = new Territory("Norway", Europe, 4, -5384193, worldMap);
		Territory westEurope = new Territory("WestEurope", Europe, 7, -16744193, worldMap);
		Territory germany = new Territory("Germany", Europe, 3, -16776962, worldMap);
		Territory southEurope = new Territory("SouthEurope", Europe, 6, -7760385, worldMap);
		Territory eastEurope = new Territory("EastEurope", Europe, 6, -16772991, worldMap);
		setBorder(iceland, greenland);
		setBorder(iceland, britain);
		setBorder(iceland, norway);
		setBorder(norway, britain);
		setBorder(norway, eastEurope);
		setBorder(norway, germany);
		setBorder(britain, germany);
		setBorder(britain, westEurope);
		setBorder(westEurope, germany);
		setBorder(westEurope, southEurope);
		setBorder(germany, southEurope);
		setBorder(germany, eastEurope);
		setBorder(eastEurope, southEurope);

		// Africa
		Territory northAfrica = new Territory("NorthAfrica", Africa, 3, -11195392, worldMap);
		Territory westAfrica = new Territory("WestAfrica", Africa, 5, -28070, worldMap);
		Territory eastAfrica = new Territory("EastAfrica", Africa, 2, -33024, worldMap);
		Territory southAfrica = new Territory("SouthAfrica", Africa, 6, -8372224, worldMap);
		Territory centralAfrica = new Territory("CentalAfrica", Africa, 1, -5221116, worldMap);
		Territory madagascar = new Territory("Madagascar", Africa, 4, -16269, worldMap);
		setBorder(westAfrica, brasil);
		setBorder(westAfrica, westEurope);
		setBorder(westAfrica, southEurope);
		setBorder(westAfrica, northAfrica);
		setBorder(westAfrica, eastAfrica);
		setBorder(westAfrica, centralAfrica);
		setBorder(northAfrica, southEurope);
		setBorder(northAfrica, eastAfrica);
		setBorder(eastAfrica, centralAfrica);
		setBorder(eastAfrica, madagascar);
		setBorder(eastAfrica, southAfrica);
		setBorder(southAfrica, centralAfrica);
		setBorder(southAfrica, madagascar);
		// Asia
		Territory middleEast = new Territory("MiddleEast", Asia, 7, -16619008, worldMap);
		Territory india = new Territory("India", Asia, 3, -16744319, worldMap);
		Territory viatnam = new Territory("Viatnam", Asia, 9, -8388739, worldMap);
		Territory southChina = new Territory("SouthChina", Asia, 2, -13535970, worldMap);
		Territory northChina = new Territory("NorthChina", Asia, 8, -16761856, worldMap);
		Territory afaganistan = new Territory("Afganistan", Asia, 1, -4390982, worldMap);
		Territory Oral = new Territory("Oral", Asia, 11, -16755967, worldMap);
		Territory yakutsk = new Territory("Yakutsk", Asia, 12, -16724322, worldMap);
		Territory EastRussia = new Territory("EastRussia", Asia, 6, -16744384, worldMap);
		Territory japan = new Territory("Japan", Asia, 5, -8126969, worldMap);
		Territory siberia = new Territory("Siberia", Asia, 10, -16737024, worldMap);
		Territory mongolia = new Territory("Mongolia", Asia, 4, -8126974, worldMap);
		// Australia
		Territory indonesia = new Territory("Indonesia", Australia, 2, -8453889, worldMap);
		Territory newGuinea = new Territory("NewGuinea", Australia, 3, -130561, worldMap);
		Territory WestAustralia = new Territory("WestAustralia", Australia, 4, -8323008, worldMap);
		Territory EastAustralia = new Territory("EastAustralia", Australia, 1, -12582849, worldMap);

	}

	public void setBorder(Territory t1, Territory t2) {
		t1.addBorder(t2);
		t2.addBorder(t1);
	}

}
