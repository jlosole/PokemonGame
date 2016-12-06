package model.Items;

import java.io.Serializable;

public abstract class Item implements Serializable {
	
	private boolean isUsed;
	private int hp;
	
	//Hp as the parameter will be amount of hp restored from potions
	//and will be damage done by bait or rock
	public Item(boolean isUsed, int hp) {
		this.isUsed = isUsed;
		this.hp = hp;
	}
	
	public Boolean isUsed() {
		return this.isUsed;
	}
	
	public int getHP() {
		return hp;
	}
	
	public abstract ItemType getItemType();
	
	public abstract String toString();
}

