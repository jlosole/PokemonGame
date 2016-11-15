package model.Pokemon;

//UNCOMMON
public class MewTwo extends Pokemon {

	private static final int HP = 115;
	private static final int MAXHP = 107;
	private static final int RUNCHANCE = 5;
	private static final int CATCHCHANCE = 6;
	
	public MewTwo (){
		super(HP, MAXHP, RUNCHANCE, CATCHCHANCE);
	}

	@Override
	public PokemonType getPokemonType() {
		return PokemonType.MewTwo;
	}
	
}
