package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import views.GraphicView;
import views.TextView;
import model.Game;
import model.Trainer;
import model.Map.*;
import model.Pokemon.*;

public class PokemonGUI extends JFrame {
		
	public static void main(String [] args) {
		PokemonGUI gui = new PokemonGUI();
		gui.setVisible(true);
	}
	
	private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private final int WIDTH = (int) screenSize.getWidth();
	private final int HEIGHT = (int) screenSize.getHeight();
	private Game theGame;
	private Trainer trainer;
	private GraphicView gView;
	private TextView tView;
	private JPanel currentView;
	private Point trainerPos;
	private String winCondition;
	
	
	public PokemonGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(screenSize);
	    this.setLocation(100, 40);
	    this.setTitle("Pokemon");
	    this.setLayout(new BorderLayout());
	    
	    theGame = new Game();
	    trainer = theGame.getTrainer();
	    gView = new GraphicView(theGame, WIDTH, HEIGHT);
	    tView = new TextView(theGame);
	    
	    this.addKeyListener(new MyArrowKeyListener());
		this.setFocusable(true);
		this.requestFocus();
		addObservers();
		addMenus();
		setView(gView);
	}
	
	//Adds the menus to the frame so you can switch between views
	public void addMenus(){
		JMenuItem menu = new JMenu("Views");
		JMenuItem graphic = new JMenuItem("Graphic View");
		JMenuItem text = new JMenuItem("Text View");
		menu.add(graphic);
		menu.add(text);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(menu);
		
		graphic.addActionListener(new MenuListener());
		text.addActionListener(new MenuListener());
	}
	
	public void addObservers(){
		theGame.addObserver(gView);
		//theGame.addObserver(tView);
	}
	
	public void setView(JPanel newView) {
		if(currentView != null){
			remove(currentView);
		}
		currentView = newView;
		add(currentView, BorderLayout.CENTER);
		currentView.repaint();
		validate();		
	}

	//Class that has listeners for changing between views
	private class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String entered = ((JMenuItem) e.getSource()).getText();
			if(entered.equals("Graphic View"))
				setView(gView);
			else if(entered.equals("Text View"))
				setView(tView);
		}
	}
	
	//Class that has listeners for moving the player with arrow keys
	private class MyArrowKeyListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			trainerPos = trainer.getCurrentPos();
			
			int row = (int) trainerPos.getX();
			int col = (int) trainerPos.getY();
			
			Pokemon pokemonFound;
			
			int keyCode = e.getKeyCode();
			if(!theGame.gameOver()){
				if(keyCode == KeyEvent.VK_UP) {
					pokemonFound = theGame.move(row, col, "Up");
				}
				else if(keyCode == KeyEvent.VK_DOWN){
					pokemonFound = theGame.move(row, col, "Down");
				}
				else if(keyCode == KeyEvent.VK_LEFT){
					pokemonFound = theGame.move(row, col, "Left");
				}
				else if(keyCode == KeyEvent.VK_RIGHT){
					pokemonFound = theGame.move(row, col, "Right");
				}
				//if(pokemonFound != null) 
				//theGame.startBattle(pokemonFound)
			}
		}
		@Override
		public void keyReleased(KeyEvent e){}
		@Override
		public void keyTyped(KeyEvent e){}
	}
}
