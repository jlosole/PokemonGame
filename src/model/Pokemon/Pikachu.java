package model.Pokemon;

//RARE 
public class Pikachu extends Pokemon {

	private static final int HP = 150;
	private static final int MAXHP = 140;
	private static final int RUNCHANCE = 4;
	private static final int CATCHCHANCE = 7;
	
	public Pikachu() {
		super(HP, MAXHP, RUNCHANCE, CATCHCHANCE);
	}

	@Override
	public PokemonType getPokemonType() {
		return PokemonType.Pikachu;
	}
}
