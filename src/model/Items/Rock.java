package model.Items;

public class Rock extends Item {
	
	private static final int DAMAGE = 10;
	
	public Rock(Boolean isUsed, int hpDamage){
		super(isUsed, hpDamage);
	}
	
	@Override
	public ItemType getItemType(){
		return ItemType.Rock;
	}
	
	public static int getDamage(){
		return DAMAGE;
	}
}
