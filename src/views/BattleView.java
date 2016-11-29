package views;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
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
		//this.setSize(width, height);
		//addLabels();
	}
	
	public void addLabels(){
		JLabel bait = new JLabel("Bait: Right Arrow");
		bait.setBounds(30, 30, 50, 25);

		JLabel rock = new JLabel("Rock: Left Arrow");
		rock.setBounds(30, 70, 50, 25);
		
		JLabel run = new JLabel("Run: Up Arrow");
		run.setBounds(30, 110, 50, 25);
		
		JLabel ball = new JLabel("Ball: Down Arrow");
		ball.setBounds(30, 150, 50, 25);
		this.add(bait);
		this.add(rock);
		this.add(run);
		this.add(ball);
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
		ImageIcon battleBack = new ImageIcon("cut_sprites/battle_background.png");
		battleBack.paintIcon(this, g, 0, 0);
	}
}
