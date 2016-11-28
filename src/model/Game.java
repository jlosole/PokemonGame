package model;

import java.awt.Point;
import java.io.Serializable;
import java.util.Observable;
import java.util.Random;

import model.Items.Item;
import model.Map.*;
import model.ObstacleType.ObstacleType;
import model.Pokemon.*;

public class Game extends Observable implements Serializable{
	
	private Trainer trainer;
	private _Map currentMap;
	private _Map mapOne = MapOne.getInstanceOf();
	private _Map mapTwo = MapTwo.getInstanceOf();
	private Object [][] objBoard;
	private Point trainerPos;
	private boolean gameOver;
	private int size;
	
	public Game(){
		trainer = new Trainer();
		currentMap = mapOne;      //Initialize game to start on MapOne
		size = currentMap.getSize();
		Point pt = new Point(size-1, size/2);
		trainer.setCurrentPosition(pt);
		trainerPos = trainer.getCurrentPos();
		objBoard = currentMap.getObjMap();
		gameOver = false;
	}
	
	public void setMap(_Map newMap){
		currentMap = newMap;
		objBoard = currentMap.getObjMap();
	}
	
	public _Map getMap(){
		return currentMap;
	}
	
	public Pokemon move(int row, int col, String direction) {
		int r = row, c = col;

		Pokemon pokemon = null;
		
		//Moves in new direction
		if(direction.equals("Up")) r -= 1;
		else if(direction.equals("Down")) r += 1;
		else if(direction.equals("Left")) c -= 1;
		else if(direction.equals("Right")) c += 1;
		
		Point newPoint = new Point(r, c);
		System.out.println(c);
		System.out.println(size-1);
		//if trainer walks to east exit on MapOne
		if(((r > 9 || r < 13) && (c == size)
				&& currentMap.equals(mapOne))) {
			setMap(mapTwo);
			Point point = new Point(r, 0);
			if(trainer.stepMade(point)) {
				trainer.setCurrentPosition(point);
				trainerPos = trainer.getCurrentPos();
				setChanged();
				notifyObservers();
			}
			return null;
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
			return null;
		}
		//if trainer walks to west exit on MapTwo
		if(currentMap.equals(mapTwo) && (r >= 9 && r <= 13) && (c == -1)) {
			setMap(mapOne);
			Point point = new Point(10, size-1);
			if(trainer.stepMade(point)) {
				trainer.setCurrentPosition(point);
				trainerPos = trainer.getCurrentPos();
				setChanged();
				notifyObservers();
			}
			return null;
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
			return null;
		}

		if(canMoveHere(r, c)) {
			if(trainer.stepMade(newPoint)){
				trainer.setCurrentPosition(newPoint);
				trainerPos = trainer.getCurrentPos();
				if(didStepOnItem()) {
					removeItem(r, c);
				}
				if(isInDeepGrass()){
					pokemon = getRandomPokemon();
				}
				setChanged();
				notifyObservers();
			}
			else {
				gameOver = true;
			}
		}
		return pokemon;
	}
	
	
	public Boolean didStepOnItem(){
		Point trainerPos = trainer.getCurrentPos();
		if(objBoard[trainerPos.x][trainerPos.y] instanceof Item){
			trainer.collectedItem((Item)objBoard[trainerPos.x][trainerPos.y]);
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
		int num = rand.nextInt(18);
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
				objBoard[row][col] == ObstacleType.Dirt || objBoard[row][col] instanceof Item))
			return true;
		return false;
	}
	
	public void doNotify(){
		setChanged();
		notifyObservers();
	}
}
