package views;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

import model.Pokemon.Pokemon;

public class PokedexView extends JPanel implements Observer {

	private int width, height, pokemonIndex = 0; 
	private ArrayList<Pokemon> pokedexList;
	
	public PokedexView(int width, int height){
		this.width = width;
		this.height = height;
		pokedexList = new ArrayList<Pokemon>();
	}
	
	public void addToPokedexList(Pokemon newPokemon){
		pokedexList.add(newPokemon);
	}
	
	public void incrementIndex(){
		pokemonIndex++;
		if(pokemonIndex == pokedexList.size())
			pokemonIndex = 0;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
	
	
	
	

}
