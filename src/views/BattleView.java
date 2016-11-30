package views;

<<<<<<< HEAD

=======
>>>>>>> 2d4b87f49246b8059ef3ad4db59bcb27fd141af1
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
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
	private int width, height, actionMade = -1;
	private JPanel buttonPanel;
	private BufferedImage background, trainer, rockI, baitI, ballI;
	private JButton bait;
	private JButton rock;
	private JButton run;
	private JButton ball;
	
	public BattleView(Game game, int width, int height) {
		theGame = game;
		battle = theGame.getBattle();
		pokemon = null;
		this.width = width; 
		this.height = height;
		this.setSize(this.width, this.height);
		this.setLayout(new BorderLayout());
		initializeButtonPanel();
		setupImages();
	}

	public void initializeButtonPanel(){
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2, 2));
		
		bait = new JButton("Throw Bait");
		rock = new JButton("Throw Rock");
		ball = new JButton("Throw Ball");
		run = new JButton("Run Away"); 
		
		buttonPanel.add(bait);
		buttonPanel.add(rock);
		buttonPanel.add(ball);
		buttonPanel.add(run);
		buttonPanel.setSize(232, 232);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void setupImages(){
		try {
			background = ImageIO.read(new File("cut_sprites/battle_background.png"));			
			trainer = ImageIO.read(new File("cut_sprites/throw_1.png"));
			ballI = ImageIO.read(new File("cut_sprites/pokeball.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setPokemon(Pokemon pokemon){
		this.pokemon = pokemon;
	}
	
	public JButton getRockButton(){
		return rock;
	}
	public JButton getBaitButton(){
		return bait;
	}
	public JButton getRunButton(){
		return run;
	}
	public JButton getBallButton(){
		return ball;
	}
	
	public void setActionMade(int action){
		actionMade = action;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
		g.drawImage(trainer, 150, height-340, null);
		
		Image poke = pokemon.getImage();
		g.drawImage(poke, width-200, 100, null);
		
		if(actionMade == 0){
			//Draw rock
		}
		else if(actionMade == 1){
			//Draw bait
		}
		else if(actionMade == 2){
			g.drawImage(ballI, width/2, height/2, null);
		}
		
<<<<<<< HEAD
		
=======
>>>>>>> 2d4b87f49246b8059ef3ad4db59bcb27fd141af1
    	Font myFont = new Font("Courier", Font.BOLD, 22);
    	g.setFont(myFont);
    	g.setColor(Color.BLACK);
		g.drawString("Throw Bait: Left Arrow", 30, 50);
		g.drawString("Throw Rock: Right Arrow", 30, 75);
		g.drawString("Throw Ball: Down Arrow", 30, 100);
		g.drawString("Run: Up Arrow", 30, 125);
	}
}
