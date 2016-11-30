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
	
	private int width, height;
	private int actions = 1;  				 //Will be a counter to see which images to draw
	private Boolean pokemonCaught = false;   //False is pokemon isnt caught true otherwise
	
	private JPanel buttonPanel;
	private BufferedImage background, rockImage, baitImage, ballImage, currentItemImage = null; 
	private BufferedImage currentTrainer, trainer1, trainer2, trainer3, trainer4, trainer5;		  
	private JButton bait, rock, run, ball;
	
	private final int ENTER_DELAY_IN_MILLS = 20;
	private final int THROW_DELAY_IN_MILLS = 125;
	private Timer timer = new Timer(ENTER_DELAY_IN_MILLS, new MyBattleStartListener());
	private Timer throwTimer = new Timer(THROW_DELAY_IN_MILLS, new MyBattleThrowingListener());
	private final int MOVEMENT_PIXELS = 2;
	
	//Trainer instance variables for battle starting animations
	private int trainerX = 0;
	private int trainerFinalX;
	private int trainerY;
	private Boolean trainerSet = false;
	
	//Pokemon instance variables for battle starting animations
	private int pokemonX = 675;
	private int pokemonFinalX;
	private int pokemonY;
	private Boolean pokemonSet = false;
	
	public BattleView(Game game, int width, int height) {
		theGame = game;
		pokemon = null;
		this.width = width; 
		this.height = height;
		
		trainerFinalX = width/4-40;
		trainerY = height - 175;
		pokemonFinalX = width-300;
		pokemonY = height/2-25;
		
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
	
	//Creates all images that might be used in a battle
	public void setupImages(){
		try {
			background = ImageIO.read(new File("cut_sprites/battle_background.png"));			
			ballImage = ImageIO.read(new File("cut_sprites/pokeball.png"));
			rockImage = ImageIO.read(new File("cut_sprites/throwing_rock.png"));
			baitImage = ImageIO.read(new File("cut_sprites/bait.png"));
			
			//Different trainer images for throwing motion
			trainer1 = ImageIO.read(new File("cut_sprites/throw_1.png"));
			trainer2 = ImageIO.read(new File("cut_sprites/throw_2.png"));
			trainer3 = ImageIO.read(new File("cut_sprites/throw_3.png"));
			trainer4 = ImageIO.read(new File("cut_sprites/throw_4.png"));
			trainer5 = ImageIO.read(new File("cut_sprites/throw_5.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void startTimer(){
		timer.start();
	}
	
	public void startThrowTimer(){
		throwTimer.start();
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
	
	public void switchTrainerImage(){
		if(actions == 1)
			currentTrainer = trainer1;
		else if(actions == 2)
			currentTrainer = trainer2;
		else if(actions == 3)
			currentTrainer = trainer3;
		else if(actions == 4) 
			currentTrainer = trainer4;
		else if(actions == 5) 
			currentTrainer = trainer5;
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
	
	public void updateStartAnimations(){
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
		if(trainerX < trainerFinalX){
			trainerX += MOVEMENT_PIXELS;
		}
		else {
			trainerSet = true;
		}
	}
	
	public void movePokemon(){
		if(pokemonX > pokemonFinalX){
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
		
		//Always draw the trainer
		switchTrainerImage(); //Switches trainer image 
		g.drawImage(currentTrainer, trainerX, trainerY, null);
		moveTrainer();
		
		//If the pokemon is not caught draw it
		if(!pokemonCaught){
			Image poke = pokemon.getImage();
			g.drawImage(poke, pokemonX, pokemonY, null);
			movePokemon();
		}
	}
	
	private class MyBattleStartListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			updateStartAnimations();
			repaint();
		}

	}
	
	private class MyBattleThrowingListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			actions++;
			if(actions == 6) {
				actions = 1;
				throwTimer.stop();
			}
			repaint();
		}
	}
}
