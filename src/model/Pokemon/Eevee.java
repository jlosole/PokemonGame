package model.Pokemon;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import model.Items.*;

//COMMON
public class Eevee extends Pokemon {

	private static final int HP = 65;
	private static final int MAXHP = 65;
	private static final int RUNCHANCE = 8;
	private static final int CATCHCHANCE = 4;
	private static final Item ITEM = getRandomItem();
	
	public Eevee() {
		super(HP, MAXHP, RUNCHANCE, CATCHCHANCE, ITEM);
	}
	
	@Override 
	public PokemonType getPokemonType(){
		return PokemonType.Eevee;
	}
	private static Item getRandomItem() {
		Random rand = new Random();
		int num = rand.nextInt(3);
		if(num == 0) return null;
		else if(num == 1) return new Bait(false, 3);
		else return new Potion(false, 15);	
	}	
	
	@Override
	public Image getImage() {
		try {
			return ImageIO.read(new File("cut_sprites/eevee.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public Image getInventoryImage() {
		try {
			return ImageIO.read(new File("inventory_pokemon/eevee.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;	
	}

	@Override
	public Image getPokedexImage() {
		try {
			return ImageIO.read(new File("pokedex_images/eevee.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getHeight() {
		return "1'00''";
	}

	@Override
	public String getWeight() {
		return "14.3 lbs";
	}

	@Override
	public String typeOfPokemon() {
		return "Normal";
	}

	@Override
	public String getFact() {
		return "Eevee has a naive personality.";
	}
}
