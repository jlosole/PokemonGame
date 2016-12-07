package tests;
import static org.junit.Assert.*;

import java.awt.Point;
import java.util.Iterator;

import org.junit.Test;

import model.Map.*;

import java.util.Map;

import model.Game;
import model.Trainer;
import model.Battle.Battle;
import model.Items.*;
import model.Pokemon.*;
import model.Items.Item;
import model.Items.Potion;
import model.Items.SafariBall;
import model.Items.SuperPotion;
import model.Pokemon.Charmander;
import model.Pokemon.Magmar;
import model.Pokemon.Pikachu;
import model.Pokemon.Pokemon;

public class testModel {
	
	@Test
	public void testItems() {
		Item sBall = new SafariBall(false, 0);
		Item potion = new Potion(false, 10);
		Item superPotion = new SuperPotion(false, 20);
		assertFalse(sBall.isUsed());
		assertEquals(superPotion.getHP(), 20);
	}
	
	@Test
	public void testBattle() {
		Battle battle = new Battle(new Trainer(), new MewTwo());
		assertFalse(battle.isOver());
		battle.throwBait();
		battle.throwRock();
		battle.throwBall();
		battle.trainerRan();
		assertTrue(battle.isOver());
	}
	
	@Test
	public void testGame() {
		Game game = new Game("one");
		assertEquals(null, game.move(21, 11, "Up"));
		assertFalse(game.didStepOnItem());
		assertFalse(game.isInDeepGrass());
		game.startBattle(new MewTwo());
		game.endBattle();
		assertTrue(game.getTrainerPos().x > 0);
		assertTrue(game.getTrainerPos().y > 0);
		game.setMap(MapTwo.getInstanceOf());
		assertEquals(MapTwo.getInstanceOf(), game.getMap());
		game.getDirection();
		game.move(18, 13, "Down");
		game.move(18, 14, "Right");
		game.move(18, 12, "Left");
		game.setMap(MapOne.getInstanceOf());
		game.move(11, 22, "Right");
		game.move(11, 0, "Left");
		game.move(0, 11, "Up");
		game.setMap(MapTwo.getInstanceOf());
		game.move(3, 0, "Left");
		game.setMap(MapOne.getInstanceOf());
		game.move(21, 2, "Right");
		game.isInDeepGrass();
	}
	
	@Test
	public void testItemList(){
		Item sBall = new SafariBall(false, 0);
		Item potion = new Potion(false, 10);
		Item superPotion = new SuperPotion(false, 20);
		Item bait = new Bait(false, 3);
		Item rock = new Rock(false, 10);
		Trainer trainer = new Trainer();
		trainer.collectedItem(sBall);
		trainer.collectedItem(potion);
		trainer.collectedItem(superPotion);
		trainer.collectedItem(bait);
		trainer.collectedItem(rock);
		
		Iterator itr = trainer.getItems().entrySet().iterator();
		
		while(itr.hasNext()){
			Map.Entry pair = (Map.Entry) itr.next();
			System.out.println(pair.getKey() + " " + pair.getValue());
		}
	}
	
	@Test
	public void testPokemonList(){
		Pokemon pika = new Pikachu();
		Pokemon pika2 = new Pikachu();
		Pokemon charmander = new Charmander();
		Pokemon mag = new Magmar();
		Pokemon mew = new MewTwo();
		Pokemon dod = new Doduo();
		Pokemon cat = new Caterpie();
		Pokemon drow = new Drowzee();
		Pokemon eev = new Eevee();
		Pokemon geo = new Geodude();
		Pokemon krab = new Krabby();
		drow.hitByRock();
		drow.didCatch();
		drow.didRun();
		Item item = new Bait(false, 10);
		drow.consumeItem(item);
		drow.getHP();
		drow.tookDamage(10);
		drow.ateBait();
		drow.getMaxHP();
		
		Trainer trainer = new Trainer();
		trainer.caughtPokemon(pika);
		trainer.caughtPokemon(pika2);
		trainer.caughtPokemon(charmander);
		trainer.caughtPokemon(mag);
		trainer.caughtPokemon(mew);
		trainer.caughtPokemon(dod);
		trainer.caughtPokemon(cat);
		trainer.caughtPokemon(drow);
		trainer.caughtPokemon(eev);
		trainer.caughtPokemon(geo);
		trainer.caughtPokemon(krab);

		Iterator itr = trainer.getPokemon().iterator();
		while(itr.hasNext()){
			Pokemon pokemon = (Pokemon) itr.next();
			System.out.println(pokemon.getPokemonType().toString());
		}
	}
	
	@Test
	public void testToString() {
		MapOne map = MapOne.getInstanceOf();
		System.out.println(map.toString());
	}
	
	@Test 
	public void testTrainer(){
		Trainer newTrainer = new Trainer();
		Point pt = new Point();

		newTrainer.throwBait();
		newTrainer.throwRock();
		newTrainer.throwBall();
		newTrainer.stepMade(pt);
		newTrainer.usePotion();
		newTrainer.useSuperPotion();
		
		Item pot = new Potion(false, 3);
		Item suppot = new SuperPotion(false, 10);
		Item rock = new Rock(false, 10);
		Item bait = new Bait(false, 3);
		Item sBall = new SafariBall(false, 0);
		newTrainer.collectedItem(pot);
		newTrainer.collectedItem(suppot);
		newTrainer.collectedItem(rock);
		newTrainer.collectedItem(bait);
		newTrainer.collectedItem(sBall);

		newTrainer.setCurrentPosition(pt);
		newTrainer.throwBait();
		newTrainer.throwRock();
		newTrainer.throwBall();
		newTrainer.stepMade(pt);
		newTrainer.usePotion();
		newTrainer.useSuperPotion();
	}
	
}
