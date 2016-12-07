package model.Map;

import java.io.Serializable;
import java.util.Random;

import model.Items.Bait;
import model.Items.Item;
import model.Items.ItemType;
import model.Items.Potion;
import model.Items.Rock;
import model.Items.SafariBall;
import model.Items.SuperPotion;
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
		setWater();
		setBushes();
		setShortGrass();
		setDeepGrass();
		setItems();
		setTrees();
		setEntranceAndExits();
		setHill();
		setDirt();
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
	
	public void setHill() {
		//right border
		for(int i = 5; i <= 19; i++) {
			map[i][18] = ObstacleType.hillRight;
		}
		map[20][18] = ObstacleType.hillBotRight;
		map[4][18] = ObstacleType.hillTopRight;
		
		//top border
		for(int j = 6; j <= 17; j++) {
			map[4][j] = ObstacleType.hillTop;
		}
		map[4][5] = ObstacleType.hillTopLeft;
		
		//bottom border
		map[20][3] = ObstacleType.hillBotLeft;
		map[20][4] = ObstacleType.hillBot;
		map[20][5] = ObstacleType.stairsLeft;
		map[20][6] = ObstacleType.stairsRight;
		map[20][7] = ObstacleType.hillBot;
		map[20][8] = ObstacleType.hillBot;
		map[20][9] = ObstacleType.hillBotRight;
		map[19][9] = ObstacleType.hillRight;
		map[19][10] = ObstacleType.stairsLeft;
		map[19][11] = ObstacleType.stairsRight;
		map[19][12] = ObstacleType.hillLeft;
		map[20][12] = ObstacleType.hillBotLeft;
		map[20][13] = ObstacleType.hillBot;
		map[20][14] = ObstacleType.hillBot;
		map[20][15] = ObstacleType.hillBot;
		map[20][16] = ObstacleType.stairsLeft;
		map[20][17] = ObstacleType.stairsRight;

		//east inner border
		for(int i = 7; i <= 14; i++) {
			map[i][15] = ObstacleType.hillLeft;
		}
		map[6][15] = ObstacleType.inHillTopRight;
		
		//south top border
		for(int j = 8; j <= 15; j++) {
			map[15][j] = ObstacleType.hillTop;
		}
		map[15][7] = ObstacleType.hillTopLeft;
		map[16][7] = ObstacleType.hillLeft;
		
		//southwest top border
		for(int j = 4; j <= 7; j++) {
			map[17][j] = ObstacleType.hillTop;
		}
		map[17][3] = ObstacleType.hillTopLeft;
		map[18][3] = ObstacleType.hillLeft;
		map[19][3] = ObstacleType.hillLeft;
		
		//north bottom border
		map[5][5] = ObstacleType.hillLeft;
		map[6][5] = ObstacleType.hillBotLeft;
		map[6][6] = ObstacleType.hillBot;
		map[6][7] = ObstacleType.hillBot;
		map[6][8] = ObstacleType.stairsLeft;
		map[6][9] = ObstacleType.stairsRight;
		for(int j = 10; j <= 14; j++) {
			map[6][j] = ObstacleType.hillBot;
		}
		
		//fill dirt
		for(int i = 5; i <= 19; i++) {
			for(int j = 16; j <= 17; j++) {
				map[i][j] = ObstacleType.hillDirt;
			}
		}
		for(int j = 4; j <= 8; j++) {
			map[19][j] = ObstacleType.hillDirt;
		}
		for(int j = 13; j <= 15; j++) {
			map[19][j] = ObstacleType.hillDirt;
		}
		for(int j = 4; j <= 15; j++) {
			map[18][j] = ObstacleType.hillDirt;
		}
		for(int i = 16; i <= 17; i++) {
			for(int j = 8; j <= 15; j++) {
				map[i][j] = ObstacleType.hillDirt;
			}
		}
		for(int j = 6; j <= 15; j++) {
			map[5][j] = ObstacleType.hillDirt;
		}
	}
	
	public void setDirt() {
		map[7][2] = ObstacleType.dirtTopLeft;
		map[7][3] = ObstacleType.dirtTopRight;
		for(int i = 8; i <= 11; i++) {
			map[i][2] = ObstacleType.dirtLeft;
			map[i][3] = ObstacleType.dirtRight;
		}
		map[12][2] = ObstacleType.dirtBotLeft;
		map[12][3] = ObstacleType.dirtBotRight;
	}
	
	public void setDeepGrass() {
		//southwest patch
		for(int i = 17; i <= 21; i++) {
			for(int j = 1; j <= 2; j++) {
				map[i][j] = ObstacleType.DeepGrass;
			}
		}
		for(int j = 3; j <= 8; j++) {
			map[21][j] = ObstacleType.DeepGrass;
		}
		
		//southeast patch
		for(int j = 14; j <= 21; j++) {
			map[21][j] = ObstacleType.DeepGrass;
		}
		map[20][19] = ObstacleType.DeepGrass;
		map[20][20] = ObstacleType.DeepGrass;
		map[20][21] = ObstacleType.DeepGrass;
		map[19][20] = ObstacleType.DeepGrass;
		map[19][21] = ObstacleType.DeepGrass;
		map[18][21] = ObstacleType.DeepGrass;
		
		//west patch
		for(int i = 14; i <= 15; i++) {
			for(int j = 1; j <= 5; j++) {
				map[i][j] = ObstacleType.DeepGrass;
			}
		}
		
		//north east patch
		for(int i = 4; i <= 7; i++) {
			for(int j = 20; j <= 21; j++) {
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
		for(int j = 1; j < 7; j++) {
			map[16][j] = ObstacleType.Bush;
		}
		map[14][6] = ObstacleType.Bush;
		map[15][6] = ObstacleType.Bush;
	}
	
	public void setEntranceAndExits() {
		
		//top entrance
		map[0][9] = ObstacleType.woodpegs;
		map[0][10] = ObstacleType.dirtLeft;
		map[1][10] = ObstacleType.dirtLeft;
		map[2][10] = ObstacleType.dirtBotLeft;
		map[2][9] = ObstacleType.graysign;
		map[2][11] = ObstacleType.dirtBot;
		map[2][12] = ObstacleType.dirtBotRight;
		map[1][12] = ObstacleType.dirtRight;
		map[0][12] = ObstacleType.dirtRight;
		map[0][11] = ObstacleType.Dirt;
		map[1][11] = ObstacleType.Dirt;
		map[0][13] = ObstacleType.woodpegs;
		
		//bottom entrance
		map[SIZE-1][9] = ObstacleType.woodpegs;
		map[SIZE-2][9] = ObstacleType.woodpegs;
		map[SIZE-1][10] = ObstacleType.dirtLeft;
		map[SIZE-2][10] = ObstacleType.dirtTopLeft;
		map[21][11] = ObstacleType.dirtTop;
		map[21][12] = ObstacleType.dirtTopRight;
		map[SIZE-1][11] = ObstacleType.Dirt;
		map[SIZE-1][12] = ObstacleType.dirtRight;
		map[SIZE-1][13] = ObstacleType.woodpegs;
		map[SIZE-2][13] = ObstacleType.woodpegs;
		
		//RIGHT EXIT
		map[8][20] = ObstacleType.woodpegs;
		map[8][21] = ObstacleType.woodpegs;

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
		
		map[12][19] = ObstacleType.dirtLeft;
		map[12][20] = ObstacleType.Dirt;
		map[12][21] = ObstacleType.Dirt;
		map[12][22] = ObstacleType.Dirt;
		
		map[13][19] = ObstacleType.dirtBotLeft;
		map[13][20] = ObstacleType.dirtBot;
		map[13][21] = ObstacleType.dirtBot;
		map[13][22] = ObstacleType.dirtBot;
		map[14][20] = ObstacleType.woodpegs;
		map[14][21] = ObstacleType.woodpegs;

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

//		map[17][5] = new Rock(false, 6);
//		map[9][18] = new Potion(false, 15);
//		map[17][14] = new SafariBall(false, 3);
//		map[15][5] = new SuperPotion(false, 25);
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
