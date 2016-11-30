package model.Items;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bait extends Item {
	
	public Bait(Boolean isUsed, int hpDamage){
		super(isUsed, hpDamage);
	}
	
	@Override
	public ItemType getItemType(){
		return ItemType.Bait;
	}
}
