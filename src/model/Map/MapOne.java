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
		setTrees();
		setWater();
		setBushes();
		setShortGrass();
		setDeepGrass();
		setItems();
		setTrees();
		setEntranceAndExits();
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
	
	public void setEntranceAndExits() {
		//top row
		map[0][9] = ObstacleType.woodpegs;
		map[0][10] = ObstacleType.dirtLeft;
		map[1][10] = ObstacleType.dirtLeft;
		map[2][10] = ObstacleType.dirtBotLeft;
		map[2][11] = ObstacleType.dirtBot;
		map[2][12] = ObstacleType.dirtBotRight;
		map[1][12] = ObstacleType.dirtRight;
		map[0][12] = ObstacleType.dirtRight;
		map[0][11] = ObstacleType.Dirt;
		map[1][11] = ObstacleType.Dirt;
		map[0][13] = ObstacleType.woodpegs;
		
		//bottom entrance
		map[SIZE-1][9] = ObstacleType.woodpegs;
		map[SIZE-1][10] = ObstacleType.dirtLeft;
		map[SIZE-2][10] = ObstacleType.dirtLeft;
		map[SIZE-3][10] = ObstacleType.dirtTopLeft;
		map[SIZE-3][11] = ObstacleType.dirtTop;
		map[SIZE-3][12] = ObstacleType.dirtTopRight;
		map[SIZE-1][11] = ObstacleType.Dirt;
		map[SIZE-2][11] = ObstacleType.Dirt;
		map[SIZE-1][12] = ObstacleType.dirtRight;
		map[SIZE-2][12] = ObstacleType.dirtRight;
		map[SIZE-1][13] = ObstacleType.woodpegs;
		
		//RIGHT EXIT
		map[9][22] = ObstacleType.dirtTop;
		map[9][21] = ObstacleType.dirtTop;
		map[9][20] = ObstacleType.dirtTop;
		map[9][19] = ObstacleType.dirtTopLeft;
		map[10][22] = ObstacleType.Dirt;
		map[10][21] = ObstacleType.Dirt;
		map[10][20] = ObstacleType.Dirt;
		map[10][19] = ObstacleType.dirtLeft;
		map[11][22] = ObstacleType.Dirt;
		map[11][21] = ObstacleType.Dirt;
		map[11][20] = ObstacleType.Dirt;
		map[11][19] = ObstacleType.dirtLeft;
		map[12][19] = ObstacleType.dirtBotLeft;
		map[12][20] = ObstacleType.dirtBot;
		map[12][21] = ObstacleType.dirtBot;
		map[12][22] = ObstacleType.dirtBot;

	}
	
	public void setTrees() {
		for(int i = 0; i < SIZE; i++) {
			//top row
			if(i < 9 || i > 13) {
				map[0][i] = ObstacleType.Tree;
			} 
			//bottom row
			if(i < 9 || i > 13) {
				map[SIZE-1][i] = ObstacleType.Tree;
			} 
//			//left column
			map[i][0] = ObstacleType.Tree;
			//right column
			if(i < 9 || i > 13) {
				map[i][SIZE-1] = ObstacleType.Tree;
			}
		}
	}
	
	public void setItems() {
		map[10][2] = new Bait(false, 2);
		map[17][5] = new Rock(false, 6);
		map[9][18] = new Potion(false, 10);
//		map[5][12] = new SafariBall(false, 3);
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
