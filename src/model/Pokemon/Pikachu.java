package model.Pokemon;

import javax.swing.ImageIcon;

import model.Items.Bait;
import model.Items.Item;
import model.Items.SuperPotion;

//UNCOMMON 
public class Pikachu extends Pokemon {
	private static final int HP = 115;
	private static final int MAXHP = 107;
	private static final int RUNCHANCE = 5;
	private static final int CATCHCHANCE = 6;
	private static final Item BAIT = new Bait(false, 3);
	
	public Pikachu() {
		super(HP, MAXHP, RUNCHANCE, CATCHCHANCE, BAIT);
	}

	@Override
	public PokemonType getPokemonType() {
		return PokemonType.Pikachu;
	}
	
	@Override
	public ImageIcon getImage() {
		return new ImageIcon("cut_sprites/pikachu.png");
	}
}
