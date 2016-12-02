package model.Map;

public interface _Map {
	public void initializeGrid();
	public void setShortGrass();
	public void setDeepGrass();
	public void setWater();
	public void setBushes();
	public void setTrees();
	public void setItems();
	public void setEntranceAndExits();
	public boolean isSafe(int x, int y);
	public Object [][] getObjMap();
	public int getSize();
}
