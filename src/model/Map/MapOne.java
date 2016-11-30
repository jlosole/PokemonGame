package model.Map;

import java.io.Serializable;
import java.util.Random;
import model.Items.Bait;
import model.Items.Item;
import model.Items.ItemType;
import model.Items.Potion;
import model.Items.Rock;
import model.Items.SafariBall;
import model.ObstacleType.ObstacleType;
import model.Pokemon.Charmander;
import model.Pokemon.MewTwo;
import model.Pokemon.Pikachu;

public class MapOne implements _Map, Serializable {

	private Object[][] map;
	private final int SIZE = 23;
	private ObstacleType obstacleType = null;
	private char lastPosition;
	private Random random;
	public static MapOne mapOne;
	
	private MapOne() {
		map = new Object[SIZE][SIZE];
		random = new Random();
		initializeGrid();
		setTreesAndExits();
		setWater();
		setBushes();
		setShortGrass();
		setDeepGrass();
		setItems();
		setStone();
	}
	
	public static MapOne getInstanceOf() {
		if(mapOne == null) {
			mapOne = new MapOne();
			return mapOne;
		} else {
			return mapOne;
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
		//northwest patch
		for(int i = 2; i < 6; i++) {
			for(int j = 2; j < 5; j++) {
				map[i][j] = ObstacleType.DeepGrass;
			}
		}
		//southwest patch
		for(int i = 17; i < SIZE-2; i++) {
			for(int j = 1; j < 5; j++) {
				map[i][j] = ObstacleType.DeepGrass;
			}
		}
		//southeast patch
		for(int i = 18; i < SIZE-2; i++) {
			for(int j = 16; j < SIZE-2; j++) {
				map[i][j] = ObstacleType.DeepGrass;
			}
		}
		
		//northeast patch
		for(int i = 1; i < 8; i++) {
			for(int j = 16; j < SIZE-2; j++) {
				map[i][j] = ObstacleType.DeepGrass;
			}
		}
	}
	
	public void setStone() {
		//entrance walk way
		for(int i = SIZE-1; i > 15; i--) {
			map[i][11] = ObstacleType.StoneWalk;
		}
		//corner pieces at end of entrance walkway
		map[16][10] = ObstacleType.StoneBotRight;
		map[17][10] = ObstacleType.StoneTopRight;
		map[16][12] = ObstacleType.StoneBotLeft;
		map[17][12] = ObstacleType.StoneTopLeft;

	}
	
	public void setWater() {
		//inner water
		for(int i = 9; i < 13; i++) {
			for(int j = 5; j < 15; j++) {
				map[i][j] = ObstacleType.Water;
			}
		}
		//top border
		for(int j = 6; j < 14; j++) {
			map[8][j] = ObstacleType.WaterTop;
		}
		//left border
		for(int i = 9; i < 13; i++) {
			map[i][5] = ObstacleType.WaterLeft;
		}
		//right border
		for(int i = 9; i < 13; i++) {
			map[i][14] = ObstacleType.WaterRight;
		}
		//bottom border
		for(int j = 6; j < 14; j++) {
			map[13][j] = ObstacleType.WaterBottom;
		}
		//hard coded corner images
		map[8][5] = ObstacleType.WaterTopLeft;
		map[8][14] = ObstacleType.WaterTopRight;
		map[13][5] = ObstacleType.WaterBottomLeft;
		map[13][14] = ObstacleType.WaterBottomRight;
	}
	
	public void setBushes() {
		for(int i = 1; i < 8; i++) {
			map[i][8] = ObstacleType.Bush;
		}
		
		for(int j = 1; j < 7; j++) {
			map[16][j] = ObstacleType.Bush;
		}
		map[14][6] = ObstacleType.Bush;
		map[15][6] = ObstacleType.Bush;
		
	}
	
	public void setTreesAndExits() {
		for(int i = 0; i < SIZE; i++) {
			//top row
			if( i < 10 || i > 13) {
				map[0][i] = ObstacleType.Tree;
			} else {
				map[0][i] = ObstacleType.Dirt;
			}
			//bottom row
			if(i < 9 || i > 13) {
				map[SIZE-1][i] = ObstacleType.Tree;
			} else {
				map[SIZE-1][i] = ObstacleType.Dirt;
			}
			//left column
			if(i < 9 || i > 13) {
				map[i][0] = ObstacleType.Tree;
			} else {
				map[i][0] = ObstacleType.Dirt;
			}
			//right column
			if(i < 9 || i > 13) {
				map[i][SIZE-1] = ObstacleType.Tree;
			} else {
				map[i][SIZE-1] = ObstacleType.Dirt;
			}
		}
	}
	
	public void setItems() {
		map[10][2] = new Bait(false, 2);
		map[17][5] = new Rock(false, 6);
		map[9][18] = new Potion(false, 10);
		map[5][12] = new SafariBall(false, 3);
		map[17][14] = new SafariBall(false, 3);
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
