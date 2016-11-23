package model.Map;

import java.io.Serializable;
import java.util.Random;
import model.Items.Bait;
import model.Items.Potion;
import model.Items.Rock;
import model.Items.SafariBall;
import model.ObstacleType.ObstacleType;

public class MapTwo implements _Map, Serializable {
	
	private Object[][] map;
	private final int SIZE = 23;
	private ObstacleType obstacleType = null;
	private char lastPosition;
	private Random random;
	public static MapTwo mapTwo;
	
	private MapTwo() {
		map = new Object[SIZE][SIZE];
		random = new Random();
		initializeGrid();
		setTreesAndExits();
		setWater();
		setBushes();
		setShortGrass();
		setDeepGrass();
		setItems();
	}
	
	public static MapTwo getInstanceOf() {
		if(mapTwo == null) {
			mapTwo = new MapTwo();
			return mapTwo;
		} else {
			return mapTwo;
		}
	}
	
	public void initializeGrid() {
		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < SIZE; y++) {
				map[x][y] = null;
			}
		}
	}
	
	public void setShortGrass() {
		for(int i = 1; i < SIZE; i++) {
			for(int j = 1; j < SIZE; j++) {
				if(map[i][j] == null) {
					map[i][j] = ObstacleType.ShortGrass;
				}
			}
		}
	}
	
	public void setDeepGrass() {

		//southwest patch
		for(int i = 17; i < SIZE-3; i++) {
			for(int j = 1; j < 5; j++) {
				map[i][j] = ObstacleType.DeepGrass;
			}
		}
		//northeast patch
		for(int i = 1; i < 4; i++) {
			for(int j = 16; j < SIZE-2; j++) {
				map[i][j] = ObstacleType.DeepGrass;
			}
		}
		//northeast patch extended
		for(int i = 4; i < 7; i++) {
			for(int j = 19; j < SIZE-2; j++) {
				map[i][j] = ObstacleType.DeepGrass;
			}
		}
		//entrance deep grass
		for(int i = 9; i < 13; i++) {
			for(int j = 5; j < 8; j++) {
				map[i][j] = ObstacleType.DeepGrass;
			}
		}
	}
	
	public void setWater() {
		for(int i = 12; i < SIZE-3; i++) {
			for(int j = 16; j < SIZE-3; j++) {
				map[i][j] = ObstacleType.Water;
			}
		}
		for(int i = 16; i < SIZE-3; i++) {
			for(int j = 10; j < 16; j++) {
				map[i][j] = ObstacleType.Water;
			}
		}	
	}
	
	public void setBushes() {
		//bushes surrounding southwest deep grass
		for(int j = 1; j < 6; j++) {
			map[16][j] = ObstacleType.Bush;
		}
		for(int i = 16; i < SIZE-3; i++) {
			map[i][5] = ObstacleType.Bush;
		}
		
		//north middle bushes
		for(int i = 1; i < 10; i++) {
			map[i][14] = ObstacleType.Bush;
		}
		
		//bushes leading from entrance
		for(int j = 1; j < 8; j++) {
			map[8][j] = ObstacleType.Bush;
		}
		for(int i = 8; i < 16; i++) {
			map[i][8] = ObstacleType.Bush;
		}
	}
	
	public void setTreesAndExits() {
		for(int i = 0; i < SIZE; i++) {
			//top row
			map[0][i] = ObstacleType.Tree;
			//bottom row
			map[SIZE-1][i] = ObstacleType.Tree;
			//right column
			map[i][SIZE-1] = ObstacleType.Tree;
			//left column
			map[i][0] = ObstacleType.Tree;
			//left column
			if((i < 2) || (i > 5 && i < 9) || (i > 13 && i < SIZE)) {
				map[i][0] = ObstacleType.Tree;
			} else {
				map[i][0] = ObstacleType.Dirt;
			}
		}
	}
	
	public void setItems() {
		map[3][8] = new Bait(false, 2);
		map[SIZE-2][SIZE-2] = new Rock(false, 6);
		map[9][18] = new Potion(false, 10);
	}
	
	public Object [][] getObjMap(){
		return map;
	}
	
	public int getSize(){
		return SIZE;
	}
	
	public boolean isSafe(int x, int y) {
		return map[x][y] == null;
	}

}
