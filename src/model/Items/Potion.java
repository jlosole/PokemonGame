package model.Items;

public class Potion extends Item {

	public Potion(boolean isUsed, int hpRestore) {
		super(isUsed, hpRestore);
	}
	
	@Override
	public ItemType getItemType(){
		return ItemType.Potion;
	}
	
	public String toString() {
		return "Potion";
	}
}
