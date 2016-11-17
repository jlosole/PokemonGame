package model.Pokemon;

import java.util.Random;

import model.Items.*;

//COMMON
public class Doduo extends Pokemon {

	private static final int HP = 70;
	private static final int MAXHP = 65;
	private static final int RUNCHANCE = 7;
	private static final int CATCHCHANCE = 4;
	private static final Item ITEM = getRandomItem();
	
	public Doduo(){
		super(HP, MAXHP, RUNCHANCE, CATCHCHANCE, ITEM);
	}
	
	@Override
	public PokemonType getPokemonType(){
		return PokemonType.Doduo;
	}
	
	public static Item getRandomItem(){
		Random rand = new Random();
		int num = rand.nextInt(3);
		if(num == 0 || num == 1) return null;
		else return new Potion(false, 15);
	}
	
}
