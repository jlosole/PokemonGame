package model.Pokemon;

import java.util.Random;

import javax.swing.ImageIcon;

import model.Items.Bait;
import model.Items.Item;
import model.Items.Rock;

//COMMON
public class Caterpie extends Pokemon {
	
	private static final int HP = 60;
	private static final int MAXHP = 55;
	private static final int RUNCHANCE = 8;
	private static final int CATCHCHANCE = 3;
	private static final Item ITEM = getRandomItem();
	
	public Caterpie(){
		super(HP, MAXHP, RUNCHANCE, CATCHCHANCE, ITEM);
	}

	@Override
	public PokemonType getPokemonType() {
		return PokemonType.Caterpie;
	}
	
	public static Item getRandomItem(){
		Random rand = new Random();
		int num = rand.nextInt(3);
		if(num == 0) return null;
		else if(num == 1) return new Rock(false, 10);
		else return new Bait(false, 3);
	}
	
	@Override
	public ImageIcon getImage() {
		return new ImageIcon("cut_sprites/caterpie.png");
	}
	
}
