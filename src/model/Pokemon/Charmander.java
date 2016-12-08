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
	private static final int MAXHP = 125;
	private static final int RUNCHANCE = 4;
	private static final int CATCHCHANCE = 5;
	private static final Item POTION = new Potion(false, 15);
	
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

	@Override
	public Image getPokedexImage() {
		try {
			return ImageIO.read(new File("pokedex_images/charmander.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getHeight() {
		return "2'00''";
	}

	@Override
	public String getWeight() {
		return "18.7 lbs";
	}

	@Override
	public String typeOfPokemon() {
		return "Fire";
	}

	@Override
	public String getFact() {
		return "Charmander exhibits pack behavior, calling others of its species if it finds food.";
	}
}
