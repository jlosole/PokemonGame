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
		setHouse();
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
	
	public void setHouse() {
		//top roof border
		map[1][4] = ObstacleType.hRoofTopLeft;
		map[1][5] = ObstacleType.hRoofTop;
		map[1][6] = ObstacleType.hRoofTop;
		map[1][7] = ObstacleType.hRoofTop;
		map[1][8] = ObstacleType.hRoofTopRight;
		//middle roof
		map[2][4] = ObstacleType.hRoofMidLeft;
		map[2][5] = ObstacleType.hRoofMid;
		map[2][6] = ObstacleType.hRoofMid;
		map[2][7] = ObstacleType.hRoofMid;
		map[2][8] = ObstacleType.hRoofMidRight;
		//bottom part of roof
		map[3][4] = ObstacleType.hRoofBotLeft;
		map[3][5] = ObstacleType.hRoofBot;
		map[3][6] = ObstacleType.hRoofBot;
		map[3][7] = ObstacleType.hRoofBot;
		map[3][8] = ObstacleType.hRoofBotRight;
		//font of house
		map[4][4] = ObstacleType.hBotLeft;
		map[4][5] = ObstacleType.hDoor;
		map[4][6] = ObstacleType.hWindowLeft;
		map[4][7] = ObstacleType.hWindowRight;
		map[4][8] = ObstacleType.hBotRight;
		//stone around door
//		map[5][4] = ObstacleType.ScatteredStone;
//		map[5][6] = ObstacleType.ScatteredStone;
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
		
		//right top border
		map[12][16] = ObstacleType.WaterTopLeft;
		map[13][16] = ObstacleType.WaterLeft;
		map[14][16] = ObstacleType.WaterLeft;
		map[15][16] = ObstacleType.WaterLeft;
		map[16][16] = ObstacleType.Water;
		map[12][17] = ObstacleType.WaterTop;
		map[12][18] = ObstacleType.WaterTop;
		map[12][19] = ObstacleType.WaterTopRight;
		
		//right right border
		for(int i = 13; i < SIZE-3; i++) {
			map[i][SIZE-4] = ObstacleType.WaterRight;
		}
		
		//bottom right corner
		map[SIZE-3][SIZE-4] = ObstacleType.WaterBottomRight;
		
		//right middle part of water
		for(int i = 13; i < SIZE-3; i++) {
			for(int j = 17; j < SIZE-4; j++) {
				map[i][j] = ObstacleType.Water;
			}
		}
		//left middle part of water
		for(int i = 17; i < SIZE-3; i++) {
			for(int j = 12; j < 17; j++) {
				map[i][j] = ObstacleType.Water;
			}
		}	
		
		//bottom border of water
		for(int j = 12; j < 19; j++) {
			map[SIZE-3][j] = ObstacleType.WaterBottom;
		}
		//left border left part 
		map[SIZE-4][11] = ObstacleType.WaterLeft;
		map[SIZE-5][11] = ObstacleType.WaterLeft;
		map[SIZE-6][11] = ObstacleType.WaterLeft;
		map[SIZE-7][11] = ObstacleType.WaterTopLeft;
		map[SIZE-7][12] = ObstacleType.WaterTop;
		map[SIZE-7][13] = ObstacleType.WaterTop;
		map[SIZE-7][14] = ObstacleType.WaterTop;
		map[SIZE-7][15] = ObstacleType.WaterTop;
		map[SIZE-7][16] = ObstacleType.Water;
		map[SIZE-3][11] = ObstacleType.WaterBottomLeft;
		
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
//		map[3][8] = new Bait(false, 2);
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

	@Override
	public void setTrees() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEntranceAndExits() {
		// TODO Auto-generated method stub
		
	}

}
