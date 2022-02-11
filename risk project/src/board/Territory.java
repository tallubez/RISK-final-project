package board;

public class Territory {

	private String name;
	private Continent continent;
	private int reinforcement_amount;
	private int player_controling;
	private int unit_anoumt;

	public Territory(String name, Continent continent, int reinforcment_amount) {
		this.name = name;
		this.continent = continent;
		this.reinforcement_amount = reinforcment_amount;
		unit_anoumt = 0;
		player_controling = -1;
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

	public int getUnit_anoumt() {
		return unit_anoumt;
	}

	public void setUnit_anoumt(int unit_anoumt) {
		this.unit_anoumt = unit_anoumt;
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

	public void setContinent(Continent continent) {
		this.continent = continent;
		continent.AddToTerritorysList(this);
	}

}
