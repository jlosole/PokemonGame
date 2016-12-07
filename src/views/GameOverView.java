package views;

import javax.swing.JPanel;
import model.Game;

public class GameOverView extends JPanel {
	
	private Game game;
	private int width, height;
	
	public GameOverView(Game game, int width, int height) {
		this.game = game;
		this.width = width;
		this.height = height;
		
		
	}

}
