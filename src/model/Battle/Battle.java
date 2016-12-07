package model.Battle;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import model.Trainer;
import model.Music.BattleMusic;
import model.Music.EndOfSongEvent;
import model.Music.EndOfSongListener;
import model.Music.MapMusic;
import model.Music.SongPlayer;
import model.Pokemon.Pokemon;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

//The logic behind a battle/encounter
public class Battle implements Serializable {
	
	//Two subject of the battle
	private 		Trainer trainer;
	private 		Pokemon pokemon;
	private 		Boolean over;
	
	//Music variables
	AudioStream battleStream = null;
	AudioStream caughtStream = null;
	
	public Battle (Trainer trainer, Pokemon pokemon) {
		this.trainer = trainer;
		this.pokemon = pokemon;
		over = false;
		//Start playing the music
		//BattleMusic.play();
	}
	
	private void playCaughtMusic() {
		try {
			FileInputStream is = new FileInputStream("music/battle.wav");
			caughtStream = new AudioStream(is);
			AudioPlayer.player.start(caughtStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void stopCaughtMusic() {
		if (caughtStream != null) {
			AudioPlayer.player.stop(caughtStream);
		}
	}
	
	public Outcome throwBall() {
		//Either does or doesn't have pokeballs
		if (trainer.throwBall()) {
			//Pokemon either caught or escaped
			if (pokemon.didCatch()) {
				trainer.caughtPokemon(pokemon);
				over = true;
				return Outcome.Caught;
			} 
			
			else {
				//If escaped, either stayed or ran
				if (pokemon.didRun()) {
					over = true;
					return Outcome.Ran;
				} 
				else return Outcome.Stayed;
			}
		} 
		else return Outcome.NoBalls;
	}
	
	public Outcome throwRock() {
		//Either does or doesn't have rocks
		if (trainer.throwRock()) {
			pokemon.hitByRock();			//Impact the Pokemon
			//Then check the Pokemon's reaction
			if (pokemon.didRun()) {
				over = true;
				return Outcome.Ran;
			} else return Outcome.Stayed;
		} else return Outcome.NoRocks;
	}
	
	public Outcome throwBait() {
		//Either does or doesn't have bait
		if (trainer.throwBait()) {
			pokemon.ateBait();				//Pokemon eats bait
			//Check pokemon's reaction
			if (pokemon.didRun()) {
				over = true;
				return Outcome.Ran;
			} else return Outcome.Stayed;
		} else return Outcome.NoBait;
	}
	
	
	public void trainerRan(){
		over = true;
	}
	
	public Pokemon getPokemon(){
		return pokemon;
	}
	
	public Boolean isOver(){
		
		return over;
	}
}
