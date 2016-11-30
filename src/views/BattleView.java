package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.Timer;

import model.Game;
import model.Battle.Battle;
import model.Items.Item;
import model.Pokemon.Pokemon;

public class BattleView extends JPanel implements Observer {
	
	private Game theGame;
	private Pokemon pokemon;
	
	private int width, height, actionMade = -1;
	
	private JPanel buttonPanel;
	private BufferedImage background, trainer, rockImage, 
						  baitImage, ballImage, currentItemImage = null;
	private JButton bait, rock, run, ball;
	
	private final int DELAY_IN_MILLS = 20;
	private Timer timer = new Timer(DELAY_IN_MILLS, new MyBattleAnimationListener());
	private final int MOVEMENT_PIXELS = 2;
	
	public BattleView(Game game, int width, int height) {
		theGame = game;
		pokemon = null;
		this.width = width; 
		System.out.println(width+"!!!!");
		this.height = height;
		this.setSize(this.width, this.height);
		this.setLayout(new BorderLayout());
		initializeButtonPanel();
		setupImages();
	}
	//Trainer instance variables for battle starting animations
	private int trainerX = 0;
	private final int TRAINER_FINAL_X = 150;
	private final int TRAINER_Y = height-400;
	private Boolean trainerSet = false;
	
	
	//Pokemon instance variables for battle starting animations
	private int pokemonX = 675;
	private final int POKEMON_FINAL_X = 475;
	private final int POKEMON_Y = 120;
	private Boolean pokemonSet = false;

	
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
			ballImage = ImageIO.read(new File("cut_sprites/pokeball.png"));
			rockImage = ImageIO.read(new File("cut-sprites/throwing_rock.png"));
			baitImage = ImageIO.read(new File("cut_sprites/bait.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void startTimer(){
		timer.start();
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
	
	public void setPokemon(Pokemon pokemon){
		this.pokemon = pokemon;
	}
	
	public void setCurrentItem(String str){
		if(str.equals("Rock"))
			currentItemImage = rockImage;
		else if(str.equals("Bait"))
			currentItemImage = baitImage;
		else if(str.equals("Ball"))
			currentItemImage = ballImage;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
	
	public void updateAnimations(){
		if(!trainerSet){
			moveTrainer();
		}
		if(!pokemonSet){
			movePokemon();
		}
		if(trainerSet && pokemonSet){
			timer.stop();
		}
	}
	
	public void moveTrainer(){
		if(trainerX < TRAINER_FINAL_X){
			trainerX += MOVEMENT_PIXELS;
		}
		else {
			trainerSet = true;
		}
	}
	
	public void movePokemon(){
		System.out.println(POKEMON_FINAL_X + "*");
		System.out.println(pokemonX+ "**");
		if(pokemonX > POKEMON_FINAL_X){
			pokemonX -= MOVEMENT_PIXELS;
		}
		else {
			pokemonSet = true;
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//Always draw the background
		g.drawImage(background, 0, 0, null);
		g.drawImage(trainer, trainerX, height-400, null);
		moveTrainer();
		
		Image poke = pokemon.getImage();
		g.drawImage(poke, pokemonX, POKEMON_Y, null);
		movePokemon();
		
		
	}
	
	private class MyBattleAnimationListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("HIT");
			updateAnimations();
			repaint();
		}
		
	}
}
