package views;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Game;
import model.Trainer;
import model.Items.ItemType;
import model.Items.Potion;
import model.Items.SuperPotion;
import model.Pokemon.Pokemon;
import model.Pokemon.PokemonType;

public class InventoryView extends JPanel implements Observer {
	
	//Instance Variables needed from the game
	private Game theGame;
	private Trainer trainer;
	private int width, height, spacing = 20;
	private ArrayList<Pokemon> pokemonList;
	private HashMap<ItemType, Integer> itemList;
	
	//Components needed for drawing
	private BufferedImage textBox, ashImage;
	private JLabel pokemonLabel;
	private JLabel itemLabel;
	private final int IMAGE_SIZE = 50;
	
	//Components needed for healing pokemon
	private JButton potionButton = null, superPotionButton = null;
	private final Potion potion = new Potion(false, 15);
	private final SuperPotion sPotion = new SuperPotion(false, 25);
	private String itemSelected = null;
		
	public InventoryView(Game game, int width, int height){
		theGame = game;
		trainer = theGame.getTrainer();
		this.width = width;
		this.height = height;
		this.setSize(width, height);
		this.setLayout(null);
		try {
			textBox = ImageIO.read(new File("safariSheet/inventory.png"));
			ashImage = ImageIO.read(new File("safariSheet/ashGameOver.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setupLabels();
		setupButtons();
	}
	
	public void setupLabels(){
		pokemonLabel = new JLabel();
		pokemonLabel.setFont(new Font("Courier", Font.BOLD, 24));
		pokemonLabel.setText("Pokemon");
		pokemonLabel.setSize(100, 20);
		pokemonLabel.setLocation(width/7, 100);
		itemLabel = new JLabel();
		itemLabel.setFont(new Font("Courier", Font.BOLD, 24));
		itemLabel.setText("Items");
		itemLabel.setSize(100, 20);
		itemLabel.setLocation(width-200, 100);
	}
	
	public void setupButtons(){
		potionButton = new JButton("Use Potion");
		potionButton.setSize(90, 20);
		
		superPotionButton = new JButton("Use SuperPotion");
		superPotionButton.setSize(110, 20);
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
	
	public void paintComponent(Graphics g){
		spacing = 40;
		
		//Get updated hash maps
		pokemonList = theGame.getTrainer().getPokemon();
		itemList = theGame.getTrainer().getItems();
		
		//Draw the pokemon text box
		g.drawImage(textBox, 0, 0, null);
		g.drawImage(ashImage, 450, 430, null);
		
		//Add the labels
		if(theGame.gameOver()) {
			pokemonLabel.setLocation(width/7, 120);
			itemLabel.setLocation(width-200, 120);
			
			g.setFont(new Font("Courier", Font.BOLD, 24));
			g.drawString("GAME OVER!", 248, 100);
			
			g.setFont(new Font("Courier", Font.BOLD, 14));
			String winCondition = theGame.getWinCondition();
			if(theGame.getTrainer().getStepsRemaining()== 0 && !winCondition.equals("Stpes")){
				g.drawString("Game ended because you're out of steps!", 175, 115);
			}
			else if(winCondition.equals("Catches")) {
				g.drawString("You caught 8 Pokemon!", 231, 115);
			}
			else if(winCondition.equals("noBalls")) {
				g.drawString("You used all of your Safari Balls!", 190, 115);
			}
			else if(winCondition.equals("Steps")) {
				g.drawString("You used all of your steps!", 205, 115);
			}
		}
		this.add(pokemonLabel);
		this.add(itemLabel);
		
		//Setup font
		g.setFont(new Font("Courier", Font.BOLD, 16));
		
		//Draw pokemon list
		for(Pokemon pokemon : pokemonList){
			//Create use item button for each pokemon
			JButton itemButton = pokemon.getPokemonItemButton();
			itemButton.setSize(80, 20);
			itemButton.setLocation(width/7-10, 115+spacing);
			this.add(itemButton);
			pokemon.setDrawnHeight(115+spacing);
			
			//Get pokemon's hp as string
			int hp = pokemon.getHP();
			String hpAsString = Integer.toString(hp);
			
			//Draw pokemon, pokemon name, and hp
			g.drawImage(pokemon.getInventoryImage(), width/7+85, 100+spacing, null);
			g.drawString(pokemon.getPokemonType().toString() + "'s HP = " + 
						hpAsString, width/7+85+IMAGE_SIZE, 125+spacing);
			spacing += IMAGE_SIZE;		
		}
		
		//Reset height spacing for items list 
		spacing = 40;
		
		//Draw items list
		Set<ItemType> itemSet = itemList.keySet();
		for(ItemType item : itemSet) {
			int numItem = itemList.get(item);
			String numItemAsString = Integer.toString(numItem);
			g.drawString(item.toString() + " x " + numItemAsString, width-200, 125+spacing);
			if(item.equals(ItemType.Potion)){
				potionButton.setLocation(width-200, 135+spacing);
				this.add(potionButton);
				spacing += IMAGE_SIZE;
			}
			else if(item.equals(ItemType.SuperPotion)){
				superPotionButton.setLocation(width-200, 135+spacing);
				this.add(superPotionButton);
				spacing += IMAGE_SIZE;
			}
			else {
				spacing += IMAGE_SIZE;
			}
		}
	}
	
	public JButton getPotionButton(){
		return potionButton;
	}
	
	public JButton getSuperPotionButton(){
		return superPotionButton;
	}
}
