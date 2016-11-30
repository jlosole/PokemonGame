package views;

import model.Game;
import model.Trainer;
import model.Pokemon.MewTwo;

public class BattleViewTest {
	static Trainer 				t = new Trainer();
	static Game 				g = new Game();
	static MewTwo 				m = new MewTwo();
	static BattleView			b;
	public static void main(String [] args) {
		b = new BattleView(g, 500, 500);
	}
}
