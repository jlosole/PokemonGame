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
	private static final int MAXHP = 140;
	private static final int RUNCHANCE = 4;
	private static final int CATCHCHANCE = 7;
	private static final Item SUPERPOTION = new SuperPotion(false, 50);
	
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
}
