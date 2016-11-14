package model;

public abstract class Item {
	
	private boolean isUsed;
	private int hpRestore;
	
	public Item(boolean isUsed, int hp) {
		this.isUsed = isUsed;
		hpRestore = hp;
	}
	
	public  boolean isUsed() {
		return this.isUsed;
	}
	
	public int getHP() {
		return this.hpRestore;
	}

}
