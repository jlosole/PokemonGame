package views;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Game;
import model.Items.ItemType;
import model.Pokemon.PokemonType;

public class InventoryView extends JPanel implements Observer {
	
	private Game theGame;
	private int width, height;
	private HashMap<PokemonType, Integer> pokemonList;
	private HashMap<ItemType, Integer> itemList;
	private BufferedImage textBox;
	private JLabel pokemonLabel;
	private JLabel itemLabel;
	
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
		itemLabel.setLocation((width/4)*3, 100);
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
	
	public void paintComponent(Graphics g){
		
		//Draw the pokemon text box
		g.drawImage(textBox, 0, 0, null);
		
		//Add the labels
		this.add(pokemonLabel);
		this.add(itemLabel);
	}
}
