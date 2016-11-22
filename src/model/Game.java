package model;

import java.awt.Point;
import java.util.Observable;

import model.Items.Item;
import model.Map.*;

public class Game extends Observable {
	
	private Trainer trainer;
	private _Map theMap;
	private Point trainerPos;
	private char [][] textBoard;
	private Object [][] objBoard;
	private int size;
	
	public Game(){
		trainer = new Trainer();
		theMap = new MapOne();      //Initialize game to start on MapOne
		size = theMap.getSize();
		trainerPos = new Point(size-1, size/2);
		trainer.setCurrentPosition(trainerPos);
		textBoard = theMap.getTextMap();
		objBoard = theMap.getObjMap();
	}
	
	public void setMap(_Map newMap){
		theMap = newMap;
	}
	
	public _Map getMap(){
		return theMap;
	}
	
	public void move(int row, int col, String direction) {
		int r = row, c = col;
		System.out.println(direction);
		//Moves in new direction
		if(direction.equals("Up")) r -= 1;
		else if(direction.equals("Down")) r += 1;
		else if(direction.equals("Left")) c -= 1;
		else if(direction.equals("Right")) c += 1;
		
		if(theMap.canMoveHere(r, c)){
			Point newPoint = new Point(r, c);
			trainer.setCurrentPosition(newPoint);
			trainerPos = trainer.getCurrentPos();
			didStepOnItem();
			isInDeepGrass();
		}
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
			//Generate random Pokemon
			return true;
		}
		return false;
		
	}
	
	public Trainer getTrainer(){
		return trainer;
	}
	
	public int getSize(){
		return size;
	}
}
