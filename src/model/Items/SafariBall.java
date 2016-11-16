package model.Items;

public class SafariBall extends Item {
	
	private static final int DAMAGE = 2;
	
	public SafariBall(boolean isUsed, int hpRestore) {
		super(isUsed, hpRestore);
	}
	
	@Override
	public ItemType getItemType(){
		return ItemType.SafariBall;
	}
	
	public static int getDamage(){
		return DAMAGE;
	}
}
