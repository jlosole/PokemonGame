package model.Pokemon;

import model.Items.Item;
import model.Items.Potion;

//UNCOMMON
public class Charmander extends Pokemon {

	private static final int HP = 125;
	private static final int MAXHP = 117;
	private static final int RUNCHANCE = 4;
	private static final int CATCHCHANCE = 6;
	private static final Item POTION = new Potion(false, 25);
	
	public Charmander() {
		super(HP, MAXHP, RUNCHANCE, CATCHCHANCE, POTION);
	}

	@Override
	public PokemonType getPokemonType() {
		return PokemonType.Charmander;
	}

}
