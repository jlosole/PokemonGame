package model.Pokemon;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import model.Items.*;

//COMMON
public class Drowzee extends Pokemon {

	private static final int HP = 62;
	private static final int MAXHP = 62;
	private static final int RUNCHANCE = 6;
	private static final int CATCHCHANCE = 3;
	private static final Item ITEM = getRandomItem();
	
	public Drowzee(){
		super(HP, MAXHP, RUNCHANCE, CATCHCHANCE, ITEM);
	}
	
	@Override
	public PokemonType getPokemonType(){
		return PokemonType.Drowzee;
	}
	
	public static Item getRandomItem(){
		Random rand = new Random();
		int num = rand.nextInt(3);
		if(num == 0) return null;
		else if(num == 1) return new Rock(false, 10);
		else return new Potion(false, 15);
	}
	
	@Override
	public Image getImage() {
		try {
			return ImageIO.read(new File("cut_sprites/drowzee.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public Image getInventoryImage() {
		try {
			return ImageIO.read(new File("inventory_pokemon/drowzee.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;	
	}
}
