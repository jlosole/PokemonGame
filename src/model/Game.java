package model;

import java.awt.Point;
import java.io.Serializable;
import java.util.Observable;
import java.util.Random;

import javax.swing.JOptionPane;

import model.Battle.Battle;
import model.Items.Item;
import model.Map.*;
import model.ObstacleType.ObstacleType;
import model.Pokemon.*;
import songplayer.BattleMusic;
import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.MapMusic;
import songplayer.SongPlayer;

public class Game extends Observable implements Serializable{
	
	private Trainer trainer;
	private _Map currentMap;
	private _Map mapOne = MapOne.getInstanceOf();
	private _Map mapTwo = MapTwo.getInstanceOf();
	private Object [][] objBoard;
	private Point trainerPos;
	/*
	 * @Lanre: added this variable so that GraphicView knows which
	 * direction-facing trainer to draw. 
	 * 0 = up
	 * 1 = down
	 * 2 = left
	 * 3 = right
	 */
	private int trainerFacing;
	private boolean gameOver;
	private int size;
	private Battle battle;
	private String direction = "";
	
	public Game(){
		trainer = new Trainer();
		currentMap = mapOne;      //Initialize game to start on MapOne
		size = currentMap.getSize();
		Point pt = new Point(size-1, size/2);
		trainer.setCurrentPosition(pt);
		trainerPos = trainer.getCurrentPos();
		trainerFacing = 0;
		objBoard = currentMap.getObjMap();
		gameOver = false;
		//start music
		MapMusic.play();
	}
	
	public Trainer getTrainer(){
		return trainer;
	}
	
	public void setMap(_Map newMap){
		currentMap = newMap;
		objBoard = currentMap.getObjMap();
	}
	
	public void setWinCondition(String string) {
		
	}
		
	public _Map getMap(){
		return currentMap;
	}
	
	public int getDirection() {
		return trainerFacing;
	}	
	
	public int move(int row, int col, String direction) {
		int r = row, c = col;

		Pokemon pokemon = null;
		this.direction = direction;
		//Moves in new direction
		if(direction.equals("Up")) {
			r -= 1;
			trainerFacing = 0;
			System.out.println(trainerFacing);
		}
		else if(direction.equals("Down")) {
			r += 1;
			trainerFacing = 1;
			System.out.println(trainerFacing);
		}
		else if(direction.equals("Left")) {
			c -= 1;
			trainerFacing = 2;
			System.out.println(trainerFacing);
		}
		else if(direction.equals("Right")) {
			c += 1;
			trainerFacing = 3;
			System.out.println(trainerFacing);
		}
		
		Point newPoint = new Point(r, c);
		//if trainer walks to east exit on MapOne
		if(((r > 9 || r < 13) && (c == size)
				&& currentMap.equals(mapOne))) {
			setMap(mapTwo);
			if(r == 9) r += 1;
			else if(r == 13) r -= 1;
			Point point = new Point(r, 0);
			if(trainer.stepMade(point)) {
				trainer.setCurrentPosition(point);
				trainerPos = trainer.getCurrentPos();
				setChanged();
				notifyObservers();
			}
			return 1;
		}
		//if trainer walks to north exit on MapOne
		if(currentMap.equals(mapOne) && (c >= 10 && c <= 13) && (r == -1)) {
			setMap(mapTwo);
			Point point = new Point(3, 0);
			if(trainer.stepMade(point)) {
				trainer.setCurrentPosition(point);
				trainerPos = trainer.getCurrentPos();
				setChanged();
				notifyObservers();
			}
			return 1;
		}
		//if trainer walks to west exit on MapTwo
		if(currentMap.equals(mapTwo) && (r >= 9 && r <= 13) && (c == -1)) {
			setMap(mapOne);
			Point point = new Point(r, size-1);
			if(trainer.stepMade(point)) {
				trainer.setCurrentPosition(point);
				trainerPos = trainer.getCurrentPos();
				setChanged();
				notifyObservers();
			}
			return 1;
		}
		//if trainer walks to northwest exit on MapTwo
		if(currentMap.equals(mapTwo) && (r >= 2 && r <= 5) && (c == -1)) {
			setMap(mapOne);
			Point point = new Point(0, 11);
			if(trainer.stepMade(point)) {
				trainer.setCurrentPosition(point);
				trainerPos = trainer.getCurrentPos();
				setChanged();
				notifyObservers();
			}
			return 1;
		}

		if(canMoveHere(r, c)) {
			if(trainer.stepMade(newPoint)){
				trainer.setCurrentPosition(newPoint);
				trainerPos = trainer.getCurrentPos();
				if(didStepOnItem()) {
					removeItem(r, c);
				}
				setChanged();
				notifyObservers();
				return 2;
			}
			else {
				gameOver = true;
			}
		} else {
			//hit a border
			SongPlayer.playFile(new DefaultEndListener(), "music/border.wav");
			//trainer still faces that way
			setChanged();
			notifyObservers();
			return 3;
		}
		return 3;
	}
	
	
	public Boolean didStepOnItem(){
		Point trainerPos = trainer.getCurrentPos();
		if(objBoard[trainerPos.x][trainerPos.y] instanceof Item){
			Item collected = (Item)objBoard[trainerPos.x][trainerPos.y];
			trainer.collectedItem(collected);
			SongPlayer.playFile(new DefaultEndListener(), "music/item_obtained.wav");
			JOptionPane.showMessageDialog(null, "Obtained " + collected.toString() + "!");
			return true;
		}
		return false;
	}
	
	public Boolean isInDeepGrass(){
		Point trainerPos = trainer.getCurrentPos();
		if(objBoard[trainerPos.x][trainerPos.y] == ObstacleType.DeepGrass){
			return true;
		}
		return false;
		
	}
	
	public Pokemon getRandomPokemon(){
		Random rand = new Random();
		int num = rand.nextInt(32);
		if(num == 0 || num == 1 || num == 2)
			return randomCommonPokemon();
		else if(num == 3 || num == 4) 
			return randomUncommonPokemon();
		else if(num == 5) 
			return new MewTwo();
		else return null;
	}
	
	public Pokemon randomCommonPokemon(){
		Random rand = new Random();
		int num = rand.nextInt(6);
		if(num == 0) return new Caterpie();
		else if(num == 1) return new Doduo();
		else if(num == 2) return new Drowzee();
		else if(num == 3) return new Eevee();
		else if(num == 4) return new Geodude();
		else return new Krabby();
	}
	
	public Pokemon randomUncommonPokemon(){
		Random rand = new Random();
		int num = rand.nextInt(3);
		if(num == 0) return new Charmander();
		else if(num == 1) return new Magmar();
		else return new Pikachu();
	}
	
	public void startBattle(Pokemon pokemon) {
		battle = new Battle(trainer, pokemon);
	}
	
	public void endBattle(){
		battle = null;
	}
	
	public Battle getBattle(){
		return battle;
	}
	public Point getTrainerPos(){
		return trainerPos;
	}
	
	public int getSize(){
		return size;
	}
	
	public Object[][] getObjBoard(){
		return objBoard;
	}
	
	public Boolean gameOver(){
		return gameOver;
	}
	
	public void removeItem(int x, int y){
		objBoard[x][y] = ObstacleType.ShortGrass;
	}
	
	public boolean canMoveHere(int row, int col) {
		boolean rowValid = true;
		boolean colValid = true;
		
		if(row < 0 || row >= size) rowValid = false;
		if(col < 0 || col >= size) colValid = false;
		
		if(rowValid && colValid && (objBoard[row][col] == ObstacleType.ShortGrass || 
				objBoard[row][col] ==  ObstacleType.DeepGrass || 
				objBoard[row][col] == ObstacleType.Dirt || objBoard[row][col] instanceof Item
				|| objBoard[row][col] == ObstacleType.dirtLeft || objBoard[row][col] == ObstacleType.dirtRight
				|| objBoard[row][col] == ObstacleType.dirtTopLeft || objBoard[row][col] == ObstacleType.dirtTopRight
				|| objBoard[row][col] == ObstacleType.dirtBotLeft || objBoard[row][col] == ObstacleType.dirtBotRight
				|| objBoard[row][col] == ObstacleType.dirtBot || objBoard[row][col] == ObstacleType.dirtTop
				|| objBoard[row][col] == ObstacleType.hillDirt || objBoard[row][col] == ObstacleType.stairsLeft
				|| objBoard[row][col] == ObstacleType.stairsRight || objBoard[row][col] == ObstacleType.g2dBotLeft
				|| objBoard[row][col] == ObstacleType.g2dBotRight || objBoard[row][col] == ObstacleType.g2dTopLeft
				|| objBoard[row][col] == ObstacleType.g2dTopRight || objBoard[row][col] == ObstacleType.hillTop))
			return true;
		return false;
	}
	
	public void doNotify(){
		setChanged();
		notifyObservers();
	}
	
	private class DefaultEndListener implements EndOfSongListener {

		@Override
		public void songFinishedPlaying(EndOfSongEvent eventWithFileNameAndDateFinished) {
			// TODO Auto-generated method stub
			//do nothing?
		}
		
	}
	
	
}
