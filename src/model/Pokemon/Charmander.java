package model.Pokemon;

//UNCOMMON
public class Charmander extends Pokemon {

	private static final int HP = 125;
	private static final int MAXHP = 117;
	private static final int RUNCHANCE = 4;
	private static final int CATCHCHANCE = 6;
	
	public Charmander() {
		super(HP, MAXHP, RUNCHANCE, CATCHCHANCE);
	}

	@Override
	public PokemonType getPokemonType() {
		return PokemonType.Charmander;
	}

}
