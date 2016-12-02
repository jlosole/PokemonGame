package model.Items;

public class SuperPotion extends Item {

	public SuperPotion(boolean isUsed, int hp) {
		super(isUsed, hp);
	}
	
	@Override
	public ItemType getItemType(){
		return ItemType.SuperPotion;
	}
	
	public String toString() {
		return "Super Potion";
	}
}
