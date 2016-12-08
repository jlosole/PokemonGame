package model.Pokemon;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import model.Items.Bait;
import model.Items.Item;
import model.Items.Rock;

//COMMON
public class Caterpie extends Pokemon {
	
	private static final int HP = 60;
	private static final int MAXHP = 60;
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
	public Image getImage() {
		try {
			return ImageIO.read(new File("cut_sprites/caterpie.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	@Override
	public Image getInventoryImage() {
		try {
			return ImageIO.read(new File("inventory_pokemon/caterpie.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;	
	}

	@Override
	public Image getPokedexImage() {
		return null;
	}

	@Override
	public String getHeight() {
		return null;
	}

	@Override
	public String getWeight() {
		return null;
	}

	@Override
	public String typeOfPokemon() {
		return null;
	}

	@Override
	public String getFact() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
