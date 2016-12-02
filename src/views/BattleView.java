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
import model.Trainer;
import model.Battle.Battle;
import model.Battle.Outcome;
import model.Items.Item;
import model.Pokemon.Pokemon;

public class BattleView extends JPanel implements Observer {
	
	
	private Game theGame;
	private Trainer trainer;
	private Pokemon pokemon;
	
	//Helpers for running battles
	private int actions = 1;  				 //Will be a counter to see which images to draw
	private Boolean pokemonInBall = false;   //False is pokemon isnt caught true otherwise
	private Boolean pokemonBrokeFree = false;//Set to true if the pokemon breaks out of ball
	private Boolean pokemonRunning = false;
	private Outcome outcome;				 //Represents the pokemon's turn after a throw
	private Boolean battleDone;
	
	//Needed components for drawing battle screen
	private int width, height;
	private JPanel buttonPanel;
	private BufferedImage background, rockImage, baitImage, ballImage, currentItemImage = null; 
	private BufferedImage currentTrainer, trainer1, trainer2, trainer3, trainer4, trainer5;		  
	private JButton bait, rock, run, ball;
	
	//Needed components for animations
	private final int ENTER_DELAY_IN_MILLS = 20;
	private final int THROW_DELAY_IN_MILLS = 125;
	private final int ITEM_DELAY_IN_MILLS = 10;
	private Timer startTimer = new Timer(ENTER_DELAY_IN_MILLS, new MyBattleStartListener());
	private Timer pokRanTimer = new Timer(ENTER_DELAY_IN_MILLS, new MyPokemonRanListener());
	private Timer throwTimer = new Timer(THROW_DELAY_IN_MILLS, new MyTrainerThrowingListener());
	private Timer itemTimer = new Timer(ENTER_DELAY_IN_MILLS, new MyItemThrownListener());
	private final int MOVEMENT_PIXELS = 2;
	private final int THROWING_PIXELS = 5;
	
	//Trainer instance variables for battle starting animations
	private int trainerX = 0;
	private int trainerFinalX;
	private int trainerY;
	private Boolean trainerSet = false;
	
	//Pokemon instance variables for battle starting animations
	private int pokemonX = 575;
	private int pokemonFinalX;
	private int pokemonY;
	private Boolean pokemonSet = false;
	
	//Instance variables for throwing animations
	private Boolean trainerDoneThrowing = false;
	private Boolean itemReached = false;
	private Boolean itemXReached = false;
	private Boolean itemYReached = false;
	private int itemX;
	private int itemY;
	
	//Pokemon instance variables for a running pokemon
	private Boolean pokemonReached = false;
	private int pokFinalX = 575;

	
	public BattleView(Game game, int width, int height) {
		theGame = game;
		trainer = theGame.getTrainer();
		pokemon = null;
		this.width = width; 
		this.height = height;
		
		trainerFinalX = width/4-40;
		trainerY = height - 338;
		pokemonFinalX = width-300;
		pokemonY = height/2-200;
		
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
			ballImage = ImageIO.read(new File("cut_sprites/safari-ball-battle.png"));
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
		startTimer.start();
	}
	
	public void startThrowTimer(){
		throwTimer.start();
	}
	
	public void startPokRanTimer(){
		pokRanTimer.start();
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
	
	public void setOutcome(Outcome outcome){
		this.outcome = outcome;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
	
	public void updateStartAnimations(){
		if(!trainerSet || !pokemonSet){
			moveTrainer_Pokemon();
		}
		if(trainerSet && pokemonSet){
			startTimer.stop();
		}
	}
	
	public void moveTrainer_Pokemon(){
		if(trainerX < trainerFinalX){
			trainerX += MOVEMENT_PIXELS;
		}
		else {
			trainerSet = true;
		}
		if(pokemonX > pokemonFinalX){
			System.out.println("no");
			pokemonX -= MOVEMENT_PIXELS;
		}
		else {
			pokemonSet = true;
		}
	}
	
	public void updateTrainerAnimation(){
		if(!trainerDoneThrowing){
			moveTrainer();
		}
		
		if(trainerDoneThrowing){
			throwTimer.stop();
		}
	}
	
	public void moveTrainer(){
		actions++;
		if(actions == 2) {
			itemTimer.start();
		}
		if(actions == 6){
			actions = 1;
			trainerDoneThrowing = true;
		}
	}
	
	public void updateItemAnimation(){
		if(!itemReached){
			moveItem();
		}
	}
	
	public void moveItem(){
		//Move itemX
		if(itemX < pokemonX){
			itemX += THROWING_PIXELS;
		}
		else {
			itemXReached = true;
		}
		
		//Move itemY
		if(itemY > pokemonY){
			itemY -= THROWING_PIXELS;
		}
		else {
			itemYReached = true;
		}
		
		if(itemXReached && itemYReached)
			itemReached = true;
	}
	
	public void updatePokemonRanAnimation(){
		if(!pokemonReached){
			moveRunningPokemon();
		}
		else {
			pokRanTimer.stop();
		}
	}
	
	public void moveRunningPokemon(){
		if(pokemonX < pokFinalX){
			System.out.println("yes");
			pokemonX += MOVEMENT_PIXELS;
		}
		else {
			pokemonReached = true;
		}
	}
	
	public void stopItemTimer(){
		itemTimer.stop();
	}
	
	public Boolean doneThrowing(){
		return itemReached; 
	}
	
	public void resetItemReached(){
		itemReached = false;
	}
	
	public void resetBattle(){
		currentItemImage = null;
		pokemonSet = false;
		pokemonRunning = false;
		trainerSet = false;
		trainerDoneThrowing = false;
		itemReached = false;
		itemXReached = false;
		itemYReached = false;
		trainerX = 0;
		pokemonX = 675;
	}
	
	public void resetThrowing(){
		trainerDoneThrowing = false;
		pokemonInBall = false;
		pokemonBrokeFree = false;
		itemReached = false;
		itemXReached = false;
		itemYReached = false;
		itemX = trainerX;
		itemY = trainerY+50;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//draw the background
		g.drawImage(background, 0, 0, null);
		
		//draw the trainer
		switchTrainerImage(); //Switches trainer image 
		g.drawImage(currentTrainer, trainerX, trainerY, null);
		if(!pokemonRunning)
			moveTrainer_Pokemon();
		
		//Draw the trainers amount of safari balls remaining
		g.setFont(new Font("Courier", Font.BOLD, 24));
		g.drawString("Safari Balls Remaining", trainerFinalX+200, trainerY-50);
		g.drawString(trainer.getBallsRemainingSTR(), trainerFinalX+200, trainerY-10);
		
		
		Image poke = pokemon.getImage();
		//Draw pokemon when: no button is pressed, bait or rock is pressed, or ball is 
		//pressed and the ball has not reached the pokemon yet
		if((currentItemImage == null || !currentItemImage.equals(ballImage) ||
				(currentItemImage.equals(ballImage) && !itemReached)) && !pokemonInBall && 
				!pokemonRunning){
			g.drawImage(poke, pokemonX, pokemonY, null);
		}
		
		//Draw Pokemon's HP + Pokemon's Name
		int hp = pokemon.getHP();
		String hpAsString = Integer.toString(hp);
		g.drawString("HP: "+ hpAsString, 200, height/6);
		
		String name = pokemon.getPokemonType().toString();
		g.drawString(name, 200, height/6-40);
		if(!pokemonRunning)
			moveTrainer_Pokemon();	
		
		//Draw item if its not reached pokemon's position
		if(currentItemImage != null && !itemReached) {
			g.drawImage(currentItemImage, itemX, itemY, null);
		}
		
		//if the item is a ball and it has reached the pokemon, draw the ball w/o the pokemon
		if(currentItemImage != null && currentItemImage.equals(ballImage) && 
				itemReached && pokemonInBall == false){
			g.drawImage(currentItemImage, pokemonX+30, pokemonY+45, null);
			pokemonInBall = true;
		}
				
		//If the pokemon is in the ball and chose to stay, sleep for the pokeball to stay
		//before redrawing the pokemon back
		else if(pokemonInBall == true && (outcome.equals(Outcome.Stayed) || 
				outcome.equals(Outcome.Ran))){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			g.drawImage(pokemon.getImage(), pokemonX, pokemonY, null);
			stopItemTimer();
			pokemonInBall = false;
			pokemonBrokeFree = true;
		}
		
		//If a ball was thrown and the pokemon broke free and ran or if a rock/bait is thrown
		//and the pokemon ran, start the timer
		if(currentItemImage != null && ((currentItemImage.equals(ballImage) && pokemonBrokeFree) ||
				(!currentItemImage.equals(ballImage) && itemReached)) && outcome.equals(Outcome.Ran)){
			pokemonRunning = true;
			currentItemImage = null;
			startPokRanTimer();
		}
		if(pokemonRunning == true){
			if(!pokemonReached){
				g.drawImage(poke, pokemonX, pokemonY, null);
				moveRunningPokemon();
			}
			g.drawString("Oh no!", 75, height-200);
			g.drawString(pokemon.getPokemonType().toString()+
					" ran away!", 75, height-160);
		}
		
		//If outcome is that we caught the pokemon and the pokemon is in the ball
		//Draw the pokeball 
		if(outcome != null && outcome.equals(Outcome.Caught) && pokemonInBall){
			g.drawImage(currentItemImage, pokemonX+30, pokemonY+45, null);
			stopItemTimer();
			g.drawString("Gotcha!", 75, height-200);
			g.drawString(pokemon.getPokemonType().toString() + " "
					+ "has been caught!", 75, height-160);
		}
		
	}
	
	private class MyBattleStartListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			updateStartAnimations();
			repaint();
		}

	}
	
	private class MyTrainerThrowingListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			updateTrainerAnimation();
			repaint();
		}
	}
	
	private class MyItemThrownListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			updateItemAnimation();
			repaint();
		}	
	}
	
	private class MyPokemonRanListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			updatePokemonRanAnimation();
			repaint();
		}
		
	}
}
