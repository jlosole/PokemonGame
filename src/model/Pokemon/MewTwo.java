package model.Pokemon;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.Items.Bait;
import model.Items.Item;
import model.Items.SuperPotion;

//RARE
public class MewTwo extends Pokemon {

	private static final int HP = 150;
	private static final int MAXHP = 150;
	private static final int RUNCHANCE = 4;
	private static final int CATCHCHANCE = 7;
	private static final Item SUPERPOTION = new SuperPotion(false, 25);
	
	public MewTwo (){
		super(HP, MAXHP, RUNCHANCE, CATCHCHANCE, SUPERPOTION);
	}

	@Override
	public PokemonType getPokemonType() {
		return PokemonType.MewTwo;
	}
	
	@Override
	public Image getImage() {
		try {
			return ImageIO.read(new File("cut_sprites/mewtwo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public Image getInventoryImage() {
		try {
			return ImageIO.read(new File("inventory_pokemon/mewtwo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;	
	}

	@Override
	public Image getPokedexImage() {
		try {
			return ImageIO.read(new File("pokedex_images/mewtwo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getHeight() {
		return "6'7''";
	}

	@Override
	public String getWeight() {
		return "269 lbs";
	}

	@Override
	public String typeOfPokemon() {
		return "Psychic";
	}

	@Override
	public String getFact() {
		return "It was created by a scientist.";
	}
}
