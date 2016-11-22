package model.Map;

public interface _Map {
	public void initializeGrid();
	public void setShortGrass();
	public void setDeepGrass();
	public void setWater();
	public void setBushes();
	public void setTreesAndExits();
	public void setItems();
	public void setPokemon();
	public void printMap();
	public boolean isSafe(int x, int y);
	public char [][] getTextMap();
	public Object [][] getObjMap();
	public int getSize();
	public boolean canMoveHere(int row, int col);
}
