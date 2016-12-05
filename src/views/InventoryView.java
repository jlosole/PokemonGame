package views;

import javax.swing.JPanel;

import model.Game;

public class InventoryView extends JPanel {
	
	private Game theGame;
	private int width, height;
	
	public InventoryView(Game game, int width, int height){
		theGame = game;
		this.width = width;
		this.height = height;
	}
}
