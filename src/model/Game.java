package model;

import java.awt.Point;
import java.util.Observable;
import java.util.Random;

import model.Items.Item;
import model.Map.*;
import model.Pokemon.*;

public class Game extends Observable {
	
	private Trainer trainer;
	private _Map theMap;
	private Point trainerPos;
	private char [][] textBoard;
	private Object [][] objBoard;
	private boolean gameOver;
	private int size;
	
	public Game(){
		trainer = new Trainer();
		theMap = new MapOne();      //Initialize game to start on MapOne
		size = theMap.getSize();
		Point pt = new Point(size-1, size/2);
		trainer.setCurrentPosition(pt);
		trainerPos = trainer.getCurrentPos();
		textBoard = theMap.getTextMap();
		objBoard = theMap.getObjMap();
		gameOver = false;
	}
	
	public void setMap(_Map newMap){
		theMap = newMap;
	}
	
	public _Map getMap(){
		return theMap;
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

		if(trainer.stepMade(newPoint)) {
			if(theMap.canMoveHere(r, c)){
				trainer.setCurrentPosition(newPoint);
				trainerPos = trainer.getCurrentPos();
				didStepOnItem();
				if(isInDeepGrass()){
					pokemon = getRandomPokemon();
				}
			}
		}
		else {
			gameOver = true;
		}
		return pokemon;
	}
	
	
	public Boolean didStepOnItem(){
		if(textBoard[trainerPos.x][trainerPos.y] == 'I'){
			trainer.collectedItem((Item)objBoard[trainerPos.x][trainerPos.y]);
			return true;
		}
		return false;
	}
	
	public Boolean isInDeepGrass(){
		if(textBoard[trainerPos.x][trainerPos.y] == 'G'){
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
	
	public Trainer getTrainer(){
		return trainer;
	}
	
	public int getSize(){
		return size;
	}
	
	public Boolean gameOver(){
		return gameOver;
	}
}
