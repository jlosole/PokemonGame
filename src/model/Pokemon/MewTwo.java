package model.Pokemon;

import model.Items.Bait;
import model.Items.Item;

//UNCOMMON
public class MewTwo extends Pokemon {

	private static final int HP = 115;
	private static final int MAXHP = 107;
	private static final int RUNCHANCE = 5;
	private static final int CATCHCHANCE = 6;
	private static final Item BAIT = new Bait(false, 3);
	
	public MewTwo (){
		super(HP, MAXHP, RUNCHANCE, CATCHCHANCE, BAIT);
	}

	@Override
	public PokemonType getPokemonType() {
		return PokemonType.MewTwo;
	}
}