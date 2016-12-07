package model.Pokemon;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import model.Items.Bait;
import model.Items.Item;
import model.Items.Rock;

public class Krabby extends Pokemon {
	
	private static final int HP = 60;
	private static final int MAXHP = 60;
	private static final int RUNCHANCE = 8;
	private static final int CATCHCHANCE = 3;
	private static final Item ITEM = getRandomItem();
	
	public Krabby(){
		super(HP, MAXHP, RUNCHANCE, CATCHCHANCE, ITEM);
	}
	
	@Override
	public PokemonType getPokemonType(){
		return PokemonType.Krabby;
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
			return ImageIO.read(new File("cut_sprites/krabby.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public Image getInventoryImage() {
		try {
			return ImageIO.read(new File("inventory_pokemon/krabby.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
}
