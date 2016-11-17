package model.Map;

import java.util.Random;

import model.Items.Bait;
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

public class MapOne {

	private Object[][] map;
	private char[][] textMap;
	private final int SIZE = 23;
	private ObstacleType obstacleType = null;
	private char lastPosition;
	private Random random;
	
	public static void main(String[] args) {
		MapOne mp = new MapOne();
	}
	
	public MapOne() {
		textMap = new char[SIZE][SIZE];
		map = new Object[SIZE][SIZE];
		random = new Random();
		initializeGrid();
		setTreesAndExits();
		setWater();
		setBushes();
		setShortGrass();
		setDeepGrass();
		setPokemon();
		setItems();
		printMap();
	}
	
	private void initializeGrid() {
		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < SIZE; y++) {
				map[x][y] = null;
				textMap[x][y] = 'O';
			}
		}
	}
	
	private void setShortGrass() {
		for(int i = 1; i < SIZE; i++) {
			for(int j = 1; j < SIZE; j++) {
				if(textMap[i][j] == 'O') {
					textMap[i][j] = 'g';
				}
			}
		}
	}
	
	private void setDeepGrass() {
		//northwest patch
		for(int i = 2; i < 6; i++) {
			for(int j = 2; j < 5; j++) {
				map[i][j] = ObstacleType.DeepGrass;
				textMap[i][j] = 'G';
			}
		}
		//southwest patch
		for(int i = 17; i < SIZE-2; i++) {
			for(int j = 1; j < 5; j++) {
				map[i][j] = ObstacleType.DeepGrass;
				textMap[i][j] = 'G';
			}
		}
		//southeast patch
		for(int i = 18; i < SIZE-2; i++) {
			for(int j = 16; j < SIZE-2; j++) {
				map[i][j] = ObstacleType.DeepGrass;
				textMap[i][j] = 'G';
			}
		}
		
		//northeast patch
		for(int i = 1; i < 8; i++) {
			for(int j = 16; j < SIZE-2; j++) {
				map[i][j] = ObstacleType.DeepGrass;
				textMap[i][j] = 'G';
			}
		}
	}
	
	private void setWater() {
		for(int i = 8; i < 14; i++) {
			for(int j = 6; j < 16; j++) {
				map[i][j] = ObstacleType.Water;
				textMap[i][j] = 'W';
			}
		}
	}
	
	private void setBushes() {
		for(int i = 1; i < 8; i++) {
			map[i][8] = ObstacleType.Bush;
			textMap[i][8] = 'B';
		}
		
		for(int j = 1; j < 7; j++) {
			map[16][j] = ObstacleType.Bush;
			textMap[16][j] = 'B';
		}
		map[14][6] = ObstacleType.Bush;
		map[15][6] = ObstacleType.Bush;
		textMap[14][6] = 'B';
		textMap[15][6] = 'B';
		
		for(int i = 7; i > 0; i--) {
			map[i][15] = ObstacleType.Bush;
			textMap[i][15] = 'B';
		}
	}
	
	private void setTreesAndExits() {
		for(int i = 0; i < SIZE; i++) {
			//top row
			if( i < 10 || i > 13) {
				map[0][i] = ObstacleType.Tree;
				textMap[0][i] = 'T';
			} else {
				map[0][i] = ObstacleType.Dirt;
				textMap[0][i] = 'D';
			}
			//bottom row
			if(i < 9 || i > 13) {
				map[SIZE-1][i] = ObstacleType.Tree;
				textMap[SIZE-1][i] = 'T';
			} else {
				map[SIZE-1][i] = ObstacleType.Dirt;
				textMap[SIZE-1][i] = 'D';
			}
			//left column
			if(i < 9 || i > 13) {
				map[i][0] = ObstacleType.Tree;
				textMap[i][0] = 'T';
			} else {
				map[i][0] = ObstacleType.Dirt;
				textMap[i][0] = 'D';
			}
			//right column
			if(i < 9 || i > 13) {
				map[i][SIZE-1] = ObstacleType.Tree;
				textMap[i][SIZE-1] = 'T';
			} else {
				map[i][SIZE-1] = ObstacleType.Dirt;
				textMap[i][SIZE-1] = 'D';
			}
		}
	}
	
	private void setItems() {
		map[10][4] = new Bait(false, 2);
		textMap[10][4] = 'I';
		map[17][9] = new Rock(false, 6);
		textMap[17][9] = 'I';
		map[9][18] = new Potion(false, 10);
		textMap[9][18] = 'I';
		map[4][10] = new SafariBall(false, 3);
		textMap[4][10] = 'I';
		map[15][19] = new SafariBall(false, 3);
		textMap[15][19] = 'I';
	}
	
	private void setPokemon() {
		map[2][3] = new Charmander();
		textMap[2][3] = 'P';
		map[3][17] = new Pikachu();
		textMap[3][17] = 'P';
		map[19][17] = new MewTwo();
		textMap[19][17] = 'P';
	}
	
	private void printMap() {
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				System.out.print("[ " + textMap[i][j] + " ]");
			}
			System.out.println();
		}
	}
	
	public String mapToString() {
		String result = "";
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				result += "[ " + textMap[i][j] + " ]";
			}
			result += "\n\n";
		}
		return result;
	}
	
	public char[][] getTextMap() {
		return textMap;
	}
	
	private boolean isSafe(int x, int y) {
		return map[x][y] == null;
	}
}
