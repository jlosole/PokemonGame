package views;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Game;
import model.Pokemon.Pokemon;

public class PokedexView extends JPanel implements Observer {

	private int width, height, pokemonIndex; 
	private JButton next, back;
	private BufferedImage background;
	private Game theGame;
	private ArrayList<Pokemon> pokedexList;
	
	public PokedexView(Game theGame, int width, int height){
		this.theGame = theGame;
		pokedexList = theGame.getPokedexList();
		pokemonIndex = theGame.getPokedexIndex();
		this.width = width;
		this.height = height;
		try {
			background = ImageIO.read(new File("safariSheet/inventory.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setupButtons();
	}
		
	public void setupButtons(){
		next = new JButton("Next");
		next.setSize(80, 20);
		
		back = new JButton("Back");
		back.setSize(80, 20);		
	}
	
	public JButton getNextButton(){
		return next;
	}
	
	public JButton getBackButton(){
		return back;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
	
	public void paintComponent(Graphics g){
		pokemonIndex = theGame.getPokedexIndex();
		g.drawImage(background, 0, 0, null);
		if(pokedexList.size() >= 1){
			Pokemon pokemon = pokedexList.get(pokemonIndex);
			g.drawImage(pokemon.getPokedexImage(), 125, 150, null);
			g.setFont(new Font("Courier", Font.BOLD, 36));
			g.drawString("My Pokedex", width/2-100, 100);
			
			g.setFont(new Font("Courier", Font.BOLD, 24));	
			g.drawString(pokemon.getPokemonType().toString(), width/2-10, 150);
			g.drawString("Type: "+pokemon.typeOfPokemon(), width/2-10, 185);
			g.drawString("Height: "+pokemon.getHeight(), width/2-10, 220);
			g.drawString("Weight: "+pokemon.getWeight(), width/2-10, 255);
			
			g.setFont(new Font("Courier", Font.BOLD, 18));	
			g.drawString("Fact: "+pokemon.getFact(), 75, 350);
			
			next.setLocation(width-270, height-200);
			this.add(next);
			back.setLocation(200, height-200);
			this.add(back);
		}
		
	}
}
