package model.Items;

import java.io.Serializable;

public abstract class Item implements Serializable {
	
	private static boolean isUsed;
	private static int hp;
	
	//Hp as the parameter will be amount of hp restored from potions
	//and will be damage done by bait or rock
	public Item(boolean isUsed, int hp) {
		this.isUsed = isUsed;
		this.hp = hp;
	}
	
	public  boolean isUsed() {
		return this.isUsed;
	}
	
	public static int getHP() {
		return hp;
	}
	
	public abstract ItemType getItemType();
}

