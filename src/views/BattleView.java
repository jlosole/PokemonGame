package views;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Game;
import model.Battle.Battle;
import model.Pokemon.Pokemon;

public class BattleView extends JPanel implements Observer {
	
	private Game theGame;
	private Battle battle;
	private Pokemon pokemon;
	private int width, height;
	
	
	public BattleView(Game game, int width, int height){
		theGame = game;
		battle = theGame.getBattle();
		pokemon = null;
		this.width = width; 
		this.height = height;
		this.setSize(width, height);
	}
	
	public void setPokemon(Pokemon pokemon){
		this.pokemon = pokemon;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
	}
}
