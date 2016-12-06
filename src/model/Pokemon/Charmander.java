package model.Pokemon;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
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
	
	@Override
	public Image getImage() {
		try {
			return ImageIO.read(new File("cut_sprites/charmander.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	
	@Override
	public Image getInventoryImage() {
		try {
			return ImageIO.read(new File("inventory_pokemon/charmander.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;	
	}
}
