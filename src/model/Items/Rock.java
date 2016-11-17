package model.Items;

public class Rock extends Item {
		
	public Rock(Boolean isUsed, int hpDamage){
		super(isUsed, hpDamage);
	}
	
	@Override
	public ItemType getItemType(){
		return ItemType.Rock;
	}
}
