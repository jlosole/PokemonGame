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
		setTrees();
		setBushes();
		setShortGrass();
		setDeepGrass();
		setItems();
		setWater();
		setEntranceAndExits();
		setHill();
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
				map[x][y] = ObstacleType.ShortGrass;
			}
		}
	}
	
	public void setHill() {
		map[11][10] = ObstacleType.hillTopLeft;
		for(int i = 12; i < 20; i++) {
			map[i][10] = ObstacleType.hillLeft;
		}
		map[20][10] = ObstacleType.hillBotLeft;
		map[20][11] = ObstacleType.stairsLeft;
		map[20][12] = ObstacleType.stairsRight;
		map[20][13] = ObstacleType.hillBotRight;
		
		for(int i = 15; i < 20; i++) {
			map[i][13] = ObstacleType.hillRight;
			map[i][18] = ObstacleType.hillLeft;
		}
		map[19][18] = ObstacleType.hillBotLeft;
		map[19][19] = ObstacleType.stairsLeft;
		map[19][20] = ObstacleType.stairsRight;
		map[19][21] = ObstacleType.hillBot;
		map[19][22] = ObstacleType.hillBotRight;
		for(int i = 4; i < 19; i++) {
			map[i][22] = ObstacleType.hillRight;
		}
		
		for(int i = 11; i < 19; i++) {
			map[11][i] = ObstacleType.hillTop;
		}
		for(int i = 7; i < 11; i++) {
			map[i][18] = ObstacleType.hillLeft;
		}
		map[6][18] = ObstacleType.inHillTopRight;
		map[14][13] = ObstacleType.inHillTopLeft;
		map[14][14] = ObstacleType.hillBot;
		map[14][15] = ObstacleType.hillBot;
		map[14][16] = ObstacleType.hillBot;
		map[14][17] = ObstacleType.hillBot;
		map[14][18] = ObstacleType.inHillTopRight;
		
		map[6][17] = ObstacleType.hillBot;
		map[6][16] = ObstacleType.stairsRight;
		map[6][15] = ObstacleType.stairsLeft;
		map[6][13] = ObstacleType.hillBotLeft;
		map[6][14] = ObstacleType.hillBot;
		map[5][13] = ObstacleType.hillLeft;
		map[4][13] = ObstacleType.hillLeft;
		map[3][13] = ObstacleType.hillTopLeft;
		
		for(int i = 14; i < 22; i++) {
			map[3][i] = ObstacleType.hillTop;
		}
		map[3][22] = ObstacleType.hillTopRight;

		//fill in dirt
		for(int i = 12; i < 20; i++) {
			for(int j = 11; j < 13; j++) {
				map[i][j] = ObstacleType.hillDirt;
			}
		}
		for(int i = 12; i < 14; i++) {
			for(int j = 13; j < 19; j++) {
				map[i][j] = ObstacleType.hillDirt;
			}
		}
		for(int i =  6; i < 19; i++) {
			for(int j = 19; j < 22; j++) {
				map[i][j] = ObstacleType.hillDirt;
			}
		}
		
		for(int i = 4; i < 6; i++) {
			for(int j = 14; j < 22; j++) {
				map[i][j] = ObstacleType.hillDirt;
			}
		}
	}
	
	public void setShortGrass() {
//		for(int i = 1; i < SIZE; i++) {
//			for(int j = 1; j < SIZE; j++) {
//				if(map[i][j] == null) {
//					map[i][j] = ObstacleType.ShortGrass;
//				}
//			}
//		}
	}
	
	public void setDeepGrass() {
		//southwest 
		for(int i = 21; i < 23; i++) {
			for(int j = 4; j < 14; j++) {
				map[i][j] = ObstacleType.DeepGrass;
			}
		}
		
		//southeast
		for(int i = 20; i < 23; i++) {
			for(int j = 18; j < 23; j++) {
				map[i][j] = ObstacleType.DeepGrass;
			}
		}
		
		//mid north patch
		for(int i = 7; i < 11; i++) {
			for(int j = 13; j < 18; j++) {
				map[i][j] = ObstacleType.DeepGrass;
			}
		}
		map[8][12] = ObstacleType.DeepGrass;
		map[9][12] = ObstacleType.DeepGrass;
		map[10][12] = ObstacleType.DeepGrass;
		map[9][11] = ObstacleType.DeepGrass;
		map[10][11] = ObstacleType.DeepGrass;
		map[10][10] = ObstacleType.DeepGrass;
		
		//north east
		for(int i = 1; i < 3; i++) {
			for(int j = 16; j < 21; j++) {
				map[i][j] = ObstacleType.DeepGrass;
			}
		}
		map[2][21] = ObstacleType.DeepGrass;
		map[2][15] = ObstacleType.DeepGrass;
	}
	
	public void setWater() {
		
		//south west water
		map[14][0] = ObstacleType.WaterTopLeft;
		map[14][1] = ObstacleType.WaterTop;
		map[14][2] = ObstacleType.WaterTop;
		map[14][3] = ObstacleType.WaterTopRight;
		
		for(int i = 15; i < 22; i++) {
			map[i][0] = ObstacleType.WaterLeft;
			map[i][3] = ObstacleType.WaterRight;
			map[i][1] = ObstacleType.Water;
			map[i][2] = ObstacleType.Water;
		}
		
		map[22][0] = ObstacleType.WaterBottomLeft;
		map[22][1] = ObstacleType.WaterBottom;
		map[22][2] = ObstacleType.WaterBottom;
		map[22][3] = ObstacleType.WaterBottomRight;

		
		//south east water
		map[15][14] = ObstacleType.WaterTopLeft;
		map[15][15] = ObstacleType.WaterTop;
		map[15][16] = ObstacleType.WaterTop;
		map[15][17] = ObstacleType.WaterTopRight;
		for(int i = 16; i < 22; i++) {
			map[i][14] = ObstacleType.WaterLeft;
			map[i][15] = ObstacleType.Water;
			map[i][16] = ObstacleType.Water;
			map[i][17] = ObstacleType.WaterRight;
		}
		map[22][14] = ObstacleType.WaterBottomLeft;
		map[22][15] = ObstacleType.WaterBottom;
		map[22][16] = ObstacleType.WaterBottom;
		map[22][17] = ObstacleType.WaterBottomRight;
	}
	
	public void setBushes() {
		for(int i = 0; i < 10; i++) {
			map[8][i] = ObstacleType.Bush;
		}
		for(int i = 9; i < 21; i++) {
			for(int j = 8; j < 10; j++) {
				map[i][j] = ObstacleType.Bush;
			}
		}
	}
	
	public void setTrees() {
		for(int i = 0; i < SIZE; i++) {
			//top row
			map[0][i] = ObstacleType.Tree;
			//bottom row
			map[SIZE-1][i] = ObstacleType.Tree;
			//right column
			map[i][SIZE-1] = ObstacleType.Tree;
			//left column
			if((i < 1) || (i > 6 && i < 9) || (i > 13 && i < SIZE)) {
				map[i][0] = ObstacleType.Tree;
			} 
		}
	}
	
	public void setItems() {
		map[7][3] = new Bait(false, 2);
		map[1][21] = new Rock(false, 6);
		map[1][13] = new Potion(false, 15);
		map[7][11] = new SafariBall(false, 8);
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
	public void setEntranceAndExits() {
		// TODO Auto-generated method stub
		
		//west-southern entrance
		for(int i = 0; i < 8; i++) {
			map[9][i] = ObstacleType.woodpegs;
		}
		for(int i = 0; i < 4; i++) {
			map[13][i] = ObstacleType.woodpegs;
		}
		
		for(int i = 0; i < 7; i++) {
			map[10][i] = ObstacleType.dirtTop;
		}
		map[10][7] = ObstacleType.dirtTopRight;
		for(int i = 11; i < 20; i++) {
			map[i][7] = ObstacleType.dirtRight;
		}
		map[20][7] = ObstacleType.dirtBotRight;
		map[20][6] = ObstacleType.dirtBot;
		map[20][5] = ObstacleType.dirtBot;
		map[20][4] = ObstacleType.dirtBotLeft;
		
		for(int i = 13; i < 20; i++) {
			map[i][4] = ObstacleType.dirtLeft;
		}
		map[12][4] = ObstacleType.g2dBotLeft;
		
		for(int i = 0; i < 4; i++) {
			map[12][i] = ObstacleType.dirtBot;
		}
		for(int i = 0; i < 7; i++) {
			map[11][i] = ObstacleType.Dirt;
		}
		
		for(int i = 12; i < 20; i++) {
			for(int j = 5; j < 7; j++) {
				map[i][j] = ObstacleType.Dirt;
			}
		}
		
		//northern west entrance
		map[1][0] = ObstacleType.woodpegs;
		map[2][0] = ObstacleType.dirtTop;
		map[2][1] = ObstacleType.dirtTop;
		map[2][2] = ObstacleType.dirtTopRight;
		map[3][2] = ObstacleType.dirtRight;
		map[4][2] = ObstacleType.dirtRight;
		map[5][2] = ObstacleType.dirtBotRight;
		map[5][1] = ObstacleType.dirtBot;
		map[5][0] = ObstacleType.dirtBot;
		map[6][0] = ObstacleType.woodpegs;
		for(int i = 3 ; i < 5; i++) {
			for(int j = 0; j < 2; j++) {
				map[i][j] = ObstacleType.Dirt;
			}
		}
		
		//north entrance
		map[1][7] = ObstacleType.woodpegs;
		map[1][12] = ObstacleType.woodpegs;
		for(int i = 0; i < 5; i++) {
			map[i][8] = ObstacleType.dirtLeft;
			map[i][11] = ObstacleType.dirtRight;
		}
		for(int i = 0; i < 5; i++) {
			for(int j = 9; j < 11; j++) {
				map[i][j] = ObstacleType.Dirt;
			}
		}
		map[5][8] = ObstacleType.dirtBotLeft;
		map[5][9] = ObstacleType.dirtBot;
		map[5][10] = ObstacleType.dirtBot;
		map[5][11] = ObstacleType.dirtBotRight;
		map[2][7] = ObstacleType.woodsign;
	}

}
