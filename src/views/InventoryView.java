package views;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Game;
import model.Items.ItemType;
import model.Pokemon.Pokemon;
import model.Pokemon.PokemonType;

public class InventoryView extends JPanel implements Observer {
	
	private Game theGame;
	private int width, height, spacing = 20;
	private HashMap<Pokemon, Integer> pokemonList;
	private HashMap<ItemType, Integer> itemList;
	private BufferedImage textBox;
	private JLabel pokemonLabel;
	private JLabel itemLabel;
	private final int IMAGE_SIZE = 50;
	
	public InventoryView(Game game, int width, int height){
		theGame = game;
		this.width = width;
		this.height = height;
		this.setSize(width, height);
		this.setLayout(null);
		try {
			textBox = ImageIO.read(new File("safariSheet/inventory.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setupLabels();
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
		itemLabel.setLocation((width/4)*3-100, 100);
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
		
		//Add the labels
		this.add(pokemonLabel);
		this.add(itemLabel);
		
		//Setup font
		g.setFont(new Font("Courier", Font.BOLD, 16));
		
		//Draw pokemon list
		Set<Pokemon> pokemonSet = pokemonList.keySet();
		for(Pokemon pokemon : pokemonSet){
			int numPokemon = pokemonList.get(pokemon);
			for(int i = 0; i < numPokemon; i++) {
				g.drawImage(pokemon.getInventoryImage(), width/7-10, 100+spacing, null);
				g.drawString(pokemon.getPokemonType().toString(), width/7+IMAGE_SIZE, 125+spacing);
				spacing += IMAGE_SIZE;
			}
		}
		
		//Reset spacing for y-axis 
		spacing = 40;
		
		//Draw items list
		Set<ItemType> itemSet = itemList.keySet();
		for(ItemType item : itemSet) {
			int numItem = itemList.get(item);
			String numItemAsString = Integer.toString(numItem);
			g.drawString(item.toString() + " x " + numItemAsString, (width/4)*3-100, 100+spacing);
			spacing += 20;
		}
	}
}
