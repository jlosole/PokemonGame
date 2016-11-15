package model.Pokemon;

//UNCOMMON
public class Magmar extends Pokemon {

	private static final int HP = 120;
	private static final int MAXHP = 112;
	private static final int RUNCHANCE = 4;
	private static final int CATCHCHANCE = 6;
	
	public Magmar(){
		super(HP, MAXHP, RUNCHANCE, CATCHCHANCE);
	}
	
	@Override
	public PokemonType getPokemonType(){
		return PokemonType.Magmar;
	}
	
}
