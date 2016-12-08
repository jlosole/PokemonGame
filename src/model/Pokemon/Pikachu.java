package model.Pokemon;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.Items.Bait;
import model.Items.Item;
 
public class Pikachu extends Pokemon {
	private static final int HP = 115;
	private static final int MAXHP = 115;
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
	public Image getImage() {
		try {
			return ImageIO.read(new File("cut_sprites/pikachu.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public Image getInventoryImage() {
		try {
			return ImageIO.read(new File("inventory_pokemon/pikachu.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;	
	}

	@Override
	public Image getPokedexImage() {
		try {
			return ImageIO.read(new File("pokedex_images/pikachu.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getHeight() {
		return "1'4''";
	}

	@Override
	public String getWeight() {
		return "13.2 lbs";
	}

	@Override
	public String typeOfPokemon() {
		return "Electric";
	}

	@Override
	public String getFact() {
		return "It stores electricity in the pouches" +
				"\n" + "on its cheeks.";
	}
}
