package tests;
import model.Item;
import model.Potion;
import model.SafariBall;
import model.SuperPotion;

import static org.junit.Assert.*;

import org.junit.Test;

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

}
