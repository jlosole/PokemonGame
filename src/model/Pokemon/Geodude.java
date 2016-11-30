package model.Pokemon;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import model.Items.*;

//COMMON
public class Geodude extends Pokemon {
	
	private static final int HP = 100;
	private static final int MAXHP = 94;
	private static final int RUNCHANCE = 7;
	private static final int CATCHCHANCE = 5;
	private static final Item ITEM = getRandomItem();
	
	public Geodude () {
		super(HP, MAXHP, RUNCHANCE, CATCHCHANCE, ITEM);
	}
	
	@Override 
	public PokemonType getPokemonType(){
		return PokemonType.Geodude;
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
			return ImageIO.read(new File("cut_sprites/geodude.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
