package model.Pokemon;

import model.Items.Bait;
import model.Items.Item;
import model.Items.SuperPotion;

//RARE
public class MewTwo extends Pokemon {

	private static final int HP = 150;
	private static final int MAXHP = 140;
	private static final int RUNCHANCE = 4;
	private static final int CATCHCHANCE = 7;
	private static final Item SUPERPOTION = new SuperPotion(false, 50);
	
	public MewTwo (){
		super(HP, MAXHP, RUNCHANCE, CATCHCHANCE, SUPERPOTION);
	}

	@Override
	public PokemonType getPokemonType() {
		return PokemonType.MewTwo;
	}
}
