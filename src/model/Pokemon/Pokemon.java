package model.Pokemon;

import java.util.Random;

public abstract class Pokemon {
	
	private static int hp;				// Current hp
	private static int maxHP;			// Max hp
	private static int runChance;		// 1 out of runChance is the probability the pokemon runs
	private static int catchChance;     // 1 out of catchChance is the probability the pokemon is caught
	private static int numBallsThrown;  // Counts the number of balls thrown at this pokemon during this battle
	
	public Pokemon(int hp, int maxHP, int run, int capture){
		this.hp = hp;
		this.maxHP = maxHP;
		this.runChance = run;
		this.catchChance = capture;
	}
	
	public int getHP(){
		return this.hp;
	}
	
	public int getMaxHP(){
		return this.maxHP;
	}
	
	//When a player chooses to throw bait this method is called
	public void ateBait(){
		runChance++;
		catchChance++;
	}
	
	//When a player chooses to throw a rock this method is called
	public void hitByRock(){
		tookDamage(10);
		runChance--;
		catchChance--;
	}
	
	//When a Safari ball is thrown this will be called to see if 
	//the pokemon is caught
	public Boolean didCatch(){
		if(hp <= maxHP) {
			tookDamage(3);
			numBallsThrown++;
			Random rand = new Random();
			int num = rand.nextInt(catchChance)+1;
			if(num == 1) return true;
		}
		return false;
	}
	
	//Can use this method to see if the pokemon has ran away
	public Boolean didRun(){
		if(numBallsThrown == runChance) {
			numBallsThrown = 0;
			return true;
		}
		
		Random rand = new Random();
		int num = rand.nextInt(runChance)+1;
		if(num == 1) return true;
		return false;
	}
	
	//When a pokemon gets hit with a rock or a ball is thrown at it
	public void tookDamage(int damage){
		hp -= damage;
	}
	
	//Abstract method to get the pokemon type
	public abstract PokemonType getPokemonType();
}
