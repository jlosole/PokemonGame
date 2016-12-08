package model.Pokemon;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.Items.Item;
import model.Items.SafariBall;

//UNCOMMON
public class Magmar extends Pokemon {

	private static final int HP = 120;
	private static final int MAXHP = 120;
	private static final int RUNCHANCE = 5;
	private static final int CATCHCHANCE = 6;
	private static final Item SAFARIBALL = new SafariBall(false, 2);
	
	public Magmar(){
		super(HP, MAXHP, RUNCHANCE, CATCHCHANCE, SAFARIBALL);
	}
	
	@Override
	public PokemonType getPokemonType(){
		return PokemonType.Magmar;
	}
	
	@Override
	public Image getImage() {
		try {
			return ImageIO.read(new File("cut_sprites/magmar.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public Image getInventoryImage() {
		try {
			return ImageIO.read(new File("inventory_pokemon/magmar.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;	
	}

	@Override
	public Image getPokedexImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHeight() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWeight() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String typeOfPokemon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFact() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
