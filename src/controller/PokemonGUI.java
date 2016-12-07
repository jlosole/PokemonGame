package controller;
import java.awt.Point;
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
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import views.BattleView;
import views.GraphicView;
import views.InstructionsView;
import views.InventoryView;
import views.LoadingView;
import views.TextView;
import model.Game;
import model.Trainer;
import model.Battle.Battle;
import model.Battle.Outcome;
import model.Items.Potion;
import model.Items.SuperPotion;
import model.Music.BattleMusic;
import model.Music.CaughtMusic;
import model.Music.MapMusic;
import model.Pokemon.*;

public class PokemonGUI extends JFrame {
	
	//Instance variables for persistence
	private static FileInputStream fis;
	private static ObjectInputStream oIStream;
	private static int DELAY_IN_MILLS = 1000;
	private static PokemonGUI startGUI;
	private static PokemonGUI gameGUI;
	public static boolean playFromSavedGame;

	public static void main(String [] args) {
		startGUI = new PokemonGUI();
		startGUI.setVisible(true);
	}
	
	//Game instance variables
	private final int WIDTH = 644, HEIGHT = 688;
	private Game theGame;
	private Battle battle;
	private Trainer trainer;
	
	//Instance variables of all the different views
	private GraphicView gView;
	private TextView tView;
	private BattleView bView;
	private LoadingView lView;
	private InstructionsView instructionView;
	private InventoryView iView;
	private JPanel currentView, oldView = null;
	
	private String winCondition;
	
	//Battle Buttons
	private JButton rockB, baitB, ballB, runB, gameOverB;
	
	//Buttons for LoadingScreen
	private JButton yesButton, noButton;
	private JButton steps, catches;
	
	//Components for InventoryView
	private boolean openedInventory = false;
	private JButton potionB, superPotionB;
	private String inventoryItemSelected;
	private ArrayList<Pokemon> pokemonList;
	private Potion potion = new Potion(false, 15);
	private SuperPotion sPotion = new SuperPotion(false, 25);
	
	//Buttons for InstructionView
	private JButton startMapOneButton;
	private JButton startMapTwoButton;

	
	public PokemonGUI(Game game) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(WIDTH, HEIGHT);
	    this.setLocation(100, 40);
	    this.setTitle("Pokemon");
	    this.setLayout(null);
	    
	    theGame = game;
	    battle = theGame.getBattle();
	    theGame.setWinCondition(winCondition);

	    gView = new GraphicView(theGame, WIDTH, HEIGHT);
	    tView = new TextView(theGame, WIDTH, HEIGHT); 
	    bView = new BattleView(theGame, WIDTH, HEIGHT);
	    iView = new InventoryView(theGame, WIDTH, HEIGHT);
	    
	    this.addKeyListener(new MyArrowKeyListener(theGame));
	    this.addWindowListener(new MyWindowListener());
		this.setFocusable(true);
		this.requestFocus();
		setupBattleButtons();
		setupInventoryButtons();
		addObservers();
		addMenus();
		setView(gView);
		
	}
	
	public PokemonGUI(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(WIDTH, HEIGHT);
	    this.setLocation(100, 40);
	    this.setTitle("Pokemon");
	    this.setLayout(null);
	    lView = new LoadingView(theGame, WIDTH, HEIGHT);
	    setUpLoadingButtons();
	    this.setContentPane(lView);
	}
	
	public void setUpLoadingButtons(){
	    yesButton = lView.getYesButton();
	    yesButton.addActionListener(new LoadingScreenListener());
	    noButton = lView.getNoButton();
	    noButton.addActionListener(new LoadingScreenListener());
	    
	    steps = lView.getStepsButton();
	    steps.addActionListener(new LoadingScreenListener());
	    catches = lView.getCatchesButton();
	    catches.addActionListener(new LoadingScreenListener());
	}
	
	public void setInstructionButtons() {
		startMapOneButton = instructionView.getButtonOne();
		startMapOneButton.addActionListener(new InstructionScreenListener());
		startMapTwoButton = instructionView.getButtonTwo();
		startMapTwoButton.addActionListener(new InstructionScreenListener());
	}
	
	public void setupBattleButtons(){
		rockB = bView.getRockButton();
		runB = bView.getRunButton();
		baitB = bView.getBaitButton();
		ballB = bView.getBallButton();
		gameOverB = bView.getGameOverButton();
		rockB.addActionListener(new MyBattleActionListener());
		baitB.addActionListener(new MyBattleActionListener());
		runB.addActionListener(new MyBattleActionListener());
		ballB.addActionListener(new MyBattleActionListener());
		gameOverB.addActionListener(new MyBattleActionListener());
	}
	
	public void setupInventoryButtons(){
		potionB = iView.getPotionButton();
		potionB.addActionListener(new InventoryButtonListener());
		superPotionB = iView.getSuperPotionButton();
		superPotionB.addActionListener(new InventoryButtonListener());

	}
	
	//Adds the menus to the frame so you can switch between views
	public void addMenus(){
		JMenuItem menu = new JMenu("Menu");
		JMenuItem views = new JMenu("Views");
		JMenuItem graphic = new JMenuItem("Graphic View");
		JMenuItem text = new JMenuItem("Text View");
		menu.add(views);
		views.add(graphic);
		views.add(text);
		
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
		theGame.addObserver(iView);
	}
	
	public void setView(JPanel newView) {
		if(currentView != null){
			remove(currentView);
			oldView = currentView;
		}
		currentView = newView;
		this.setContentPane(currentView);
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
					System.out.println("hit1");
					int moved;
					//User moved up
					if(keyCode == KeyEvent.VK_UP) {
						moved = theGame.move(row, col, "Up");
						if(moved == 1) 
							gView.updateTrainerPos();
						else if(moved == 2){
							gView.trainerNotSet();
							gView.setFinalPositions(trainerPos, "Up");
							gView.startTimer();
							if(theGame.isInDeepGrass()){
								pokemonFound = theGame.getRandomPokemon();
							}
						}
					}
					
					//User moved down
					else if(keyCode == KeyEvent.VK_DOWN){
						moved = theGame.move(row, col, "Down");
						if(moved == 1) 
							gView.updateTrainerPos();
						else if(moved == 2){
							gView.trainerNotSet();
							gView.setFinalPositions(trainerPos, "Down");
							gView.startTimer();
							if(theGame.isInDeepGrass()){
								pokemonFound = theGame.getRandomPokemon();
							}
						}
					}
					
					//User moved left
					else if(keyCode == KeyEvent.VK_LEFT){
						moved = theGame.move(row, col, "Left");
						if(moved == 1) 
							gView.updateTrainerPos();
						else if(moved == 2) {
							gView.trainerNotSet();
							gView.setFinalPositions(trainerPos, "Left");
							gView.startTimer();
							if(theGame.isInDeepGrass()){
								pokemonFound = theGame.getRandomPokemon();
							}
						}
					}
					
					//User moved right
					else if(keyCode == KeyEvent.VK_RIGHT){
						moved = theGame.move(row, col, "Right");
						if(moved == 1)
							gView.updateTrainerPos();
						else if(moved == 2){
							gView.trainerNotSet();
							gView.setFinalPositions(trainerPos, "Right");
							gView.startTimer();
							if(theGame.isInDeepGrass()){
								pokemonFound = theGame.getRandomPokemon();
							}
						}
					}
					//opening and closing inventory view
					else if(keyCode == KeyEvent.VK_I) {
						System.out.println("hit2");
						if(!openedInventory) {
							openedInventory = true;
							setView(iView);
						} else {
							openedInventory = false;
							setView(gView);
						}
					}
					
					if(pokemonFound != null) {
						MapMusic.stop();
						BattleMusic.play();
						theGame.startBattle(pokemonFound);
						battle = theGame.getBattle();
						bView.setPokemon(pokemonFound);
						setView(bView);
						bView.startTimer();
					}
				}
				else if(theGame.gameOver()){
					JOptionPane.showMessageDialog(null, "Game Over! You're out of steps!");
				}
			}
		}
		@Override
		public void keyReleased(KeyEvent e){}
		@Override
		public void keyTyped(KeyEvent e){}
	}
	
	public class MyBattleActionListener implements ActionListener{
	
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton buttonPressed = (JButton) e.getSource();
			Outcome outcome;
			
			if(currentView.equals(bView)){
				if(buttonPressed.equals(gameOverB) || buttonPressed.equals(runB)){
					if (BattleMusic.on()) BattleMusic.stop();
					if (CaughtMusic.on()) CaughtMusic.stop();
					MapMusic.play();
					setView(oldView);
					bView.resetBattle();
					theGame.endBattle();
				}
				else if(!battle.isOver()){
					bView.resetThrowing();
					
					//User clicked throw rock
					if(buttonPressed.equals(rockB)){
						bView.setCurrentItem("Rock");		  //BattleView knows which image to draw
						
						outcome = battle.throwRock();		  //See what happens after throwing rock
						 
						//No rocks to throw 
						if(outcome.equals(Outcome.NoRocks))  
							JOptionPane.showMessageDialog(null, "You have no rocks!");
						else {
							bView.startThrowTimer();
						}
						//Pokemon ran, currentView now is map
						if(outcome.equals(Outcome.Ran)) { 
							bView.setOutcome(Outcome.Ran);	
						}
						 //Pokemon stayed do nothing
						else {
							bView.setOutcome(Outcome.Stayed);	
						}								 
					}
					
					//User clicked throw bait
					else if(buttonPressed.equals(baitB)){
						bView.setCurrentItem("Bait");		   //BattleView knows which image to draw
						
						outcome = battle.throwBait();   	   //See what happens after throwing bait
						
						//No bait to throw
						if(outcome.equals(Outcome.NoBait))    
							JOptionPane.showMessageDialog(null, "You have no bait!");
						else {
							bView.startThrowTimer();
						}
						
						if (outcome.equals(Outcome.Ran)) {
							bView.setOutcome(Outcome.Ran);
						}
						 //Pokemon stayed do nothing
						else{
							bView.setOutcome(Outcome.Stayed);
						}								 	
					}
					
					//User clicked throw ball
					else if(buttonPressed.equals(ballB)){
						bView.setCurrentItem("Ball");   		//BattleView knows which image to draw
	
						outcome = battle.throwBall();			//See what happens after throwing ball
						
						//No balls to throw
						if(outcome.equals(Outcome.NoBalls)){
							JOptionPane.showMessageDialog(null, "You have no Safari Balls!");
						}
						else {
							bView.startThrowTimer();
						
						}
						
						//We threw a ball and caught the pokemon
						if(outcome.equals(Outcome.Caught)) {
							//Create a button for this pokemon to use in the inventory
							JButton jbutton = new JButton("Use Item");
							jbutton.addActionListener(new InventoryButtonListener());
							
							//Get the current Pokemon that was caught
							Pokemon pokemon = bView.getPokemon();
							pokemon.setPokemonItemButton(jbutton);
							
							System.out.println("caught");
							bView.setOutcome(Outcome.Caught);
							BattleMusic.stop();
							CaughtMusic.play();
						}
						
						//We threw a ball and the pokemon escaped the ball and ran
						else if(outcome.equals(Outcome.Ran)){
							bView.setOutcome(Outcome.Ran);
							System.out.println("ran");
							//BattleMusic.stop();
						}
						
						//Threw a ball and the pokemon escaped and stayed
						else {
							bView.setOutcome(Outcome.Stayed);
							System.out.println("stay");
	
						}
					}
				
					if (bView.caught()) {
						BattleMusic.stop();
						CaughtMusic.play();
					}
				}
				
				else {
					if (BattleMusic.on()) BattleMusic.stop();
					if (CaughtMusic.on()) CaughtMusic.stop();
					MapMusic.play();
					
					bView.resetBattle();
					theGame.endBattle();
					setView(oldView);
				}

				theGame.doNotify();
			}
		}
	}
	
	private class MyWindowListener implements WindowListener {
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
	
	private class LoadingScreenListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Boolean firstWindowDone = lView.getFirstWindowDone();
			JButton buttonPressed = (JButton)e.getSource();
			
			if(!firstWindowDone){
				if(buttonPressed.equals(yesButton)){
					try {
						fis = new FileInputStream("data.txt");
						oIStream = new ObjectInputStream(fis);
						try {
							Game game = (Game) oIStream.readObject();
							PokemonGUI gui = new PokemonGUI(game);
							gui.setVisible(true);
							playFromSavedGame = true;
						} catch (ClassNotFoundException c) {
							c.printStackTrace();
						}
					} catch (FileNotFoundException d) {
						d.printStackTrace();
					} catch (IOException d) {
						d.printStackTrace();
					}
				}
				else if(buttonPressed.equals(noButton)){
					lView.setFirstWindowDone();
					lView.update();
				}
			}
			else {
				if(buttonPressed.equals(steps)){
					winCondition = "Steps";
				}
				else if(buttonPressed.equals(catches)){
					winCondition = "Catches";
				}	
			    instructionView = new InstructionsView(theGame, WIDTH, HEIGHT);
			    setInstructionButtons();
				setContentPane(instructionView);
			}
		}
	}
	
	private class InstructionScreenListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton buttonPressed = (JButton)e.getSource();
			startGUI.setVisible(false);
			if(buttonPressed.equals(startMapOneButton)) 
				theGame = new Game("one");
			else if(buttonPressed.equals(startMapTwoButton))
				theGame = new Game("two");
			
			gameGUI = new PokemonGUI(theGame);
			gameGUI.setVisible(true);
		}
	}	
	
	private class InventoryButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(currentView.equals(iView)){
				trainer = theGame.getTrainer();
				pokemonList = trainer.getPokemon();
				JButton buttonPressed = (JButton) e.getSource();
				if(buttonPressed.equals(potionB)){
					inventoryItemSelected = "Potion";
				}
				else if(buttonPressed.equals(superPotionB)){
					inventoryItemSelected = "SuperPotion";
				}
				else if(inventoryItemSelected != null){
					int y = buttonPressed.getY();
					for(Pokemon pokemon : pokemonList){
						int drawnHeight = pokemon.getDrawnHeight();
						if(drawnHeight == y){
							System.out.println("balls");
							if(inventoryItemSelected.equals("Potion") && trainer.usePotion()) 
								pokemon.consumeItem(potion);
							else if(inventoryItemSelected.equals("SuperPotion") && trainer.useSuperPotion()) 
								pokemon.consumeItem(sPotion);
							inventoryItemSelected = null;
						}
					}
					theGame.doNotify();
				}
			}
		}
	}
}
