package tests;
import static org.junit.Assert.*;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import model.Map.*;

import java.util.Map;

import javax.swing.JButton;

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
		Game game = new Game(1, "Steps");
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
		ArrayList<Pokemon> pokemon = new ArrayList<Pokemon>();
		
		
		Pokemon pika = new Pikachu();
		pokemon.add(pika);
		Pokemon pika2 = new Pikachu();
		
		Pokemon charmander = new Charmander();
		pokemon.add(charmander);
		Pokemon mag = new Magmar();
		pokemon.add(mag);
		Pokemon mew = new MewTwo();
		pokemon.add(mew);
		Pokemon dod = new Doduo();
		pokemon.add(dod);
		Pokemon cat = new Caterpie();
		pokemon.add(cat);
		Pokemon drow = new Drowzee();
		pokemon.add(drow);
		Pokemon eev = new Eevee();
		pokemon.add(eev);
		Pokemon geo = new Geodude();
		pokemon.add(geo);
		Pokemon krab = new Krabby();
		pokemon.add(krab);
		for (Pokemon p : pokemon) {
			Image image = p.getImage();
			image = p.getInventoryImage();
			//Cover the consumeItem method
			p.consumeItem(new SafariBall(false, 0));
			p.consumeItem(new SuperPotion(false,0));
			//Cover didCatch
			Boolean b = p.didCatch();
			//Bring Pokemon's HP to 0
			p.tookDamage(p.getHP());
			b = p.didCatch();
			p.setPokemonItemButton(new JButton());
			JButton jb = p.getPokemonItemButton();
			p.setDrawnHeight(20);
			int i = p.getDrawnHeight();
		}
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
			Pokemon pokemon1 = (Pokemon) itr.next();
			System.out.println(pokemon1.getPokemonType().toString());
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
