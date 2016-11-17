package tests;
import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Test;
import model.Map.*;
import java.util.Map;
import model.Trainer;
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
		assertEquals(10, potion.getHP());
		assertEquals(0, sBall.getHP());
	}
	
	@Test
	public void testItemList(){
		Item sBall = new SafariBall(false, 0);
		Item potion = new Potion(false, 10);
		Item superPotion = new SuperPotion(false, 20);
		Trainer trainer = new Trainer();
		trainer.collectedItem(sBall);
		trainer.collectedItem(potion);
		trainer.collectedItem(superPotion);
		
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
		
		Trainer trainer = new Trainer();
		trainer.caughtPokemon(pika);
		trainer.caughtPokemon(pika2);
		trainer.caughtPokemon(charmander);
		trainer.caughtPokemon(mag);
		Iterator itr = trainer.getPokemon().entrySet().iterator();
		while(itr.hasNext()){
			Map.Entry pair = (Map.Entry) itr.next();
			System.out.println(pair.getKey() + " " + pair.getValue());
		}
	}
	
	@Test
	public void testToString() {
		MapOne map = new MapOne();
		System.out.println(map.mapToString());
	}
	
}
