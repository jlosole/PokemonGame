package model.Items;

public class SafariBall extends Item {
		
	public SafariBall(boolean isUsed, int hpDamage) {
		super(isUsed, hpDamage);
	}
	
	@Override
	public ItemType getItemType(){
		return ItemType.SafariBall;
	}
}
