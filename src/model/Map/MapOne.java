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

/*
 * @Lanre: TODO: We should probably ask Miranda if we're supposed to generate random
 * pokemon locations whenever the trainer moves, or if we just place the pokemon once
 * and leave them there for the duration of the game. I know in the real game if you
 * run back and forth in the same spots eventually a Pokemon will appear, which means
 * it's randomly generated whenever the trainer moves. I'll just do it like that for now
 */

public class MapOne implements _Map, Serializable {

	private Object[][] map;
	private final int SIZE = 23;
	private ObstacleType obstacleType = null;
	private char lastPosition;
	private Random random;
	
//	public static void main(String[] args) {
//		MapOne mp = new MapOne();
//	}
//	
	public MapOne() {
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
		for(int i = 8; i < 14; i++) {
			for(int j = 6; j < 16; j++) {
				map[i][j] = ObstacleType.Water;
			}
		}
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
		
		for(int i = 7; i > 0; i--) {
			map[i][15] = ObstacleType.Bush;
		}
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
		map[10][4] = new Bait(false, 2);
		map[17][9] = new Rock(false, 6);
		map[9][18] = new Potion(false, 10);
		map[4][10] = new SafariBall(false, 3);
		map[15][19] = new SafariBall(false, 3);
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
