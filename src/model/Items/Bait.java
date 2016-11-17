package model.Items;

public class Bait extends Item {
	
	public Bait(Boolean isUsed, int hpDamage){
		super(isUsed, hpDamage);
	}
	
	@Override
	public ItemType getItemType(){
		return ItemType.Bait;
	}
}
