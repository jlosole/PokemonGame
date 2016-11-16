package model.Map;
/*
 * @Lanre: TODO: We should probably ask Miranda if we're supposed to generate random
 * pokemon locations whenever the trainer moves, or if we just place the pokemon once
 * and leave them there for the duration of the game. I know in the real game if you
 * run back and forth in the same spots eventually a Pokemon will appear, which means
 * it's randomly generated whenever the trainer moves. I'll just do it like that for now
 */

public class Map {
	/*
	 * @Lanre - what I'm thinking is the map will be a 2D Spot array, each
	 * element being either Safe, a Trainer, a Pokemon, an Item, or an Obstacle.
	 */
	private enum Spot {
		Safe, Trainer, Pokemon, Item, Obstacle;
		
		//In case we need a char representation of a spot
		public char getValue() {
			switch(this) {
			case Safe: return 'S';
			case Trainer: return 'T';
			case Pokemon: return 'P';
			case Obstacle: return 'O';
			default: return 'S';
			}
		}
	}
	private Spot[][]	map;
	
	/*
	 * @Lanre - When the trainer moves to a spot, we need to save which char that spot was,
	 * so when the trainer moves again we can restore it. So if the trainer walks on 
	 * an space, the element in the char[][] changes from 'S' to 'T'. When the trainer
	 * keeps moving, that element needs to change back to an 'S'.
	 */
	private char		lastPosition;
	
	private int			trainerX,
						trainerY;
	
	public Map() {
		//Initialize the char array
		map = new Spot[500][500];
		//Set every spot to 'S'
		initializeGrid();
		//Set the trainer positon (TODO: Maybe change, but assuming he starts bottom left)
		trainerX = 1000;
		trainerY = 0;
		map[trainerX][trainerY] = Spot.Trainer;
		//Randomly set obstacles, items, and pokemon
		setItems();
		setPokemon();
	}
	
	private void initializeGrid() {
		for (int x = 0; x < 500; x++) {
			for (int y = 0; y < 500; y++) {
				map[x][y] = Spot.Safe;
			}
		}
	}
	
	private void setObstacles() {
		//TODO: May need to change, but rn there are between 50 and 100 obstacles
		int num = 50 + (int)(Math.random()*((100-50)+1));
		while (num > 0) {
			int x =  (int)(Math.random()*499+1);
			int y =  (int)(Math.random()*499+1);
			//Only add obstacles to safe spots
			if (isSafe(x,y)) {
				map[x][y] = Spot.Obstacle;
				num--;
			}
		}
	}
	
	private void setItems() {
		int num = 50 + (int)(Math.random()*((100-50)+1));
		while (num > 0) {
			int x =  (int)(Math.random()*499+1);
			int y =  (int)(Math.random()*499+1);
			//Only add obstacles to safe spots
			if (isSafe(x,y)) {
				map[x][y] = Spot.Item;
				num--;
			}
		}
	}
	
	private void setPokemon() {
		
	}
	
	private boolean isSafe(int x, int y) {
		return map[x][y] == Spot.Safe;
	}
}
