package model;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import model.Items.Item;
import model.Items.ItemType;
import model.Pokemon.Pokemon;
import model.Pokemon.PokemonType;

public class Trainer implements Serializable {
	
	private int numSteps;
	private ArrayList<Pokemon> myPokemon;
	private HashMap<ItemType, Integer> myItems;
	private static Point currentPosition;
	
	public Trainer() {			
		numSteps = 500;										// Number of steps
		myPokemon = new ArrayList<Pokemon>();	// Initializes hash map of pokemon
		myItems = new HashMap<ItemType, Integer>();			// Initializes hash map of items
		myItems.put(ItemType.SafariBall, 30);				// Player starts with 30 Safari balls
		myItems.put(ItemType.Rock, 15);						// player starts with 15 rocks
		myItems.put(ItemType.Bait, 10);						// player starts with 10 bait
		currentPosition = null;								// Players current position
		myItems.put(ItemType.SuperPotion, 2);
		myItems.put(ItemType.Potion, 2);
	}
	
	public void setCurrentPosition(Point point){
		currentPosition = point;
	}
	
	//Checks to make sure we have steps to use and if we do we sub one from our number of steps
	public Boolean stepMade(Point point) {
		if(numSteps > 0){
			numSteps--;
			return true;
		}
		return false;
	}
	
	//Checks to make sure we have balls to throw and if we do we sub one from our number of balls
	public Boolean throwBall(){
		if(myItems.containsKey(ItemType.SafariBall)){
			if(myItems.get(ItemType.SafariBall) > 0) {
				myItems.put(ItemType.SafariBall, myItems.get(ItemType.SafariBall)-1);
				return true;
			}
		}
		return false;
	}
	
	//Checks to make sure we have bait to throw and if we do we sub one from our number of bait
	public Boolean throwBait(){
		if(myItems.containsKey(ItemType.Bait)){
			if(myItems.get(ItemType.Bait) > 0) {
				myItems.put(ItemType.Bait, myItems.get(ItemType.Bait)-1);
				return true;
			}
		}
		return false;
	}
	
	//Checks to make sure we have rocks to throw and if we do we sub one from our number of rocks
	public Boolean throwRock(){
		if(myItems.containsKey(ItemType.Rock)){
			
			if(myItems.get(ItemType.Rock) > 0) {
				myItems.put(ItemType.Rock, myItems.get(ItemType.Rock)-1);
				return true;
			}
		}
		return false;
	}
	
	//Checks to make sure we have potion to use and if we do we sub one from our number of potions
	public Boolean usePotion(){
		if(myItems.containsKey(ItemType.Potion)){
			if(myItems.get(ItemType.Potion) > 0){
				myItems.put(ItemType.Potion, myItems.get(ItemType.Potion)-1);
				return true;
			}
		}
		return false;
	}
	
	//Checks to make sure we have super potion to use and if we do we sub one from our number of super potions
	public Boolean useSuperPotion(){
		if(myItems.containsKey(ItemType.SuperPotion)){
			if(myItems.get(ItemType.SuperPotion) > 0){
				myItems.put(ItemType.SuperPotion, myItems.get(ItemType.SuperPotion)-1);
				return true;
			}
		}
		return false;
	}

	public void caughtPokemon(Pokemon newPokemon) {
		
		//If the captured Pokemon has an item add it to our items
		if(newPokemon.getItem() != null) {
			collectedItem(newPokemon.getItem());
		}
		myPokemon.add(newPokemon);
	}
	
	public void collectedItem(Item newItem) {
		ItemType newItemType = newItem.getItemType();
		
		if(myItems.containsKey(newItemType))
			myItems.put(newItemType, myItems.get(newItemType)+1);
		else 
			myItems.put(newItemType, 1);
	}
	
	public HashMap<ItemType, Integer> getItems(){
		return myItems;
	}
	
	public ArrayList<Pokemon> getPokemon(){
		return myPokemon;
	}
	
	public Point getCurrentPos(){
		return currentPosition;
	}
	
	public String getBallsRemainingSTR(){
		return myItems.get(ItemType.SafariBall).toString();
	}
	
	public int getBallsRemaining(){
		return myItems.get(ItemType.SafariBall);
	}
	
	public int getStepsRemaining(){
		return numSteps;
	}
	
}
