package controller;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import views.BattleView;
import views.GraphicView;
import views.TextView;
import model.Game;
import model.Trainer;
import model.Battle.Battle;
import model.Pokemon.*;

public class PokemonGUI extends JFrame {
		
	private static FileInputStream fis;
	private static ObjectInputStream oIStream;
	
	public static void main(String [] args) {
		int selection = JOptionPane.showConfirmDialog(null, "Start from previous saved game?",
				"Pokemon", JOptionPane.YES_NO_CANCEL_OPTION);
		if(selection == JOptionPane.NO_OPTION){
			PokemonGUI gui = new PokemonGUI(new Game());
			gui.setVisible(true);
		}
		else if(selection == JOptionPane.YES_OPTION){
			//read from data.txt
			try {
				fis = new FileInputStream("data.txt");
				oIStream = new ObjectInputStream(fis);
				try {
					Game game = (Game) oIStream.readObject();
					PokemonGUI gui = new PokemonGUI(game);
					gui.setVisible(true);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private final int WIDTH = 675;
	private final int HEIGHT = 675;
	private Game theGame;
	private Battle battle;
	private GraphicView gView;
	private TextView tView;
	private BattleView bView;
	private JPanel currentView;
	private String winCondition;
	
	
	public PokemonGUI(Game game) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(WIDTH, HEIGHT);
	    this.setLocation(100, 40);
	    this.setTitle("Pokemon");
	    this.setLayout(new BorderLayout());
	    
	    theGame = game;
	    battle = theGame.getBattle();

	    gView = new GraphicView(theGame, WIDTH, HEIGHT);
	    tView = new TextView(theGame, WIDTH, HEIGHT); 
	    bView = new BattleView(theGame, WIDTH, HEIGHT);
	    
	    this.addKeyListener(new MyArrowKeyListener(theGame));
	    this.addWindowListener(new MyWindowListener());
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
		theGame.addObserver(tView);
		theGame.addObserver(bView);
	}
	
	public void setView(JPanel newView) {
		if(currentView != null){
			remove(currentView);
		}
		currentView = newView;
		add(currentView, BorderLayout.CENTER);
		theGame.doNotify();
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
	
	private class MyArrowKeyListener implements KeyListener {
		private Game theGame;
		
		public MyArrowKeyListener(Game game){
			theGame = game;
		}
		@Override
		public void keyPressed(KeyEvent e) {
			
			int keyCode = e.getKeyCode();

			if(!currentView.equals(bView)){
				Point trainerPos = theGame.getTrainerPos();
				int row = (int) trainerPos.getX();
				int col = (int) trainerPos.getY();
				Pokemon pokemonFound = null;
				
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
					if(pokemonFound != null) {
						theGame.startBattle(pokemonFound);
						bView.setPokemon(pokemonFound);
						setView(bView);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Game Over! You're out of steps!");
				}
			}
			else {
				if(keyCode == KeyEvent.VK_UP) {
					battle.trainerRan();
					//set currentView = oldView
				}
				else if(keyCode == KeyEvent.VK_DOWN){
				
				}
				else if(keyCode == KeyEvent.VK_LEFT){
				}
				else if(keyCode == KeyEvent.VK_RIGHT){
				}
			}
		}
		@Override
		public void keyReleased(KeyEvent e){}
		@Override
		public void keyTyped(KeyEvent e){}
	}
	
	public class MyWindowListener implements WindowListener {
		@Override
		public void windowClosing(WindowEvent e) {
			int selection = JOptionPane.showConfirmDialog(null, "Would you like to save the " + 
							"the current state of the game?", "Pokemon", JOptionPane.YES_NO_CANCEL_OPTION);
			if(selection == JOptionPane.NO_OPTION){
				//Do nothing let program exit as normal
			}
			else if(selection == JOptionPane.YES_OPTION){
				try {
					FileOutputStream fos = new FileOutputStream("data.txt");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(theGame);
				}
				catch (FileNotFoundException c) {
					c.printStackTrace();
				} catch (IOException b) {
					b.printStackTrace();
				} 
			}
		}
		@Override
		public void windowClosed(WindowEvent e) {
		}
		@Override
		public void windowIconified(WindowEvent e) {
		}
		@Override
		public void windowDeiconified(WindowEvent e) {
		}
		@Override
		public void windowActivated(WindowEvent e) {
		}
		@Override
		public void windowDeactivated(WindowEvent e) {
		}
		@Override
		public void windowOpened(WindowEvent e) {
		}
	}
}
