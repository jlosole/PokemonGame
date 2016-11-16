package model.Items;

public class Bait extends Item {
	
	private static final int DAMAGE = 3;
	public Bait(Boolean isUsed, int hpDamage){
		super(isUsed, hpDamage);
	}
	
	@Override
	public ItemType getItemType(){
		return ItemType.Bait;
	}
	
	public static int getDamage(){
		return DAMAGE;
	}
}
