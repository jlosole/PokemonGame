package model.Battle;

import model.Trainer;
import model.Pokemon.Pokemon;

//The logic behind a battle/encounter
public class Battle {
	
	//Two subject of the battle
	private 		Trainer trainer;
	private 		Pokemon pokemon;
	private 		Boolean over;
	
	public Battle (Trainer trainer, Pokemon pokemon) {
		this.trainer = trainer;
		this.pokemon = pokemon;
		over = false;
	}
	
	public Outcome throwBall() {
		//Either does or doesn't have pokeballs
		if (trainer.throwBall()) {
			//Pokemon either caught or escaped
			if (pokemon.didCatch()) {
				return Outcome.Caught;
			} else {
				//If escaped, either stayed or ran
				if (pokemon.didRun()) {
					return Outcome.EscapedAndRan;
				} else return Outcome.EscapedAndStayed;
			}
		} else return Outcome.NoBalls;
	}
	
	public Outcome throwRock() {
		//Either does or doesn't have rocks
		if (trainer.throwRock()) {
			//Impact the Pokemon
			pokemon.hitByRock();
			//Then check the Pokemon's reaction
			if (pokemon.didRun()) {
				return Outcome.Ran;
			} else return Outcome.Stayed;
		} else return Outcome.NoRocks;
	}
	
	public Outcome throwBait() {
		//Either does or doesn't have bait
		if (trainer.throwBait()) {
			//Pokemon eats bait
			pokemon.ateBait();
			//Check pokemon's reaction
			if (pokemon.didRun()) {
				return Outcome.Ran;
			} else return Outcome.Stayed;
		} else return Outcome.NoBait;
	}
}
