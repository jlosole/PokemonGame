package model.Pokemon;

import model.Items.Item;
import model.Items.SafariBall;

//UNCOMMON
public class Magmar extends Pokemon {

	private static final int HP = 120;
	private static final int MAXHP = 112;
	private static final int RUNCHANCE = 4;
	private static final int CATCHCHANCE = 6;
	private static final Item SAFARIBALL = new SafariBall(false, 2);
	
	public Magmar(){
		super(HP, MAXHP, RUNCHANCE, CATCHCHANCE, SAFARIBALL);
	}
	
	@Override
	public PokemonType getPokemonType(){
		return PokemonType.Magmar;
	}
	
}
