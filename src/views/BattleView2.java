package views;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
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

import model.Game;
import model.Battle.Battle;
import model.Pokemon.Pokemon;

public class BattleView2 extends JPanel implements Observer {

	private Game theGame;
	private Battle battle;
	private Pokemon pokemon;
	private int width, height;
	private JPanel buttonPanel;
	private JLabel backLabel;
	private JLabel trainLabel;
	private int action = -1;
	
	public BattleView2(Game game, int width, int height) {
		theGame = game;
		battle = theGame.getBattle();
		pokemon = null;
		this.width = width-20; 
		this.height = height-20;
		this.setSize(this.width, this.height);
		this.setLayout(null);
		
		
		initializeButtonPanel();
		initializeLabels();	
	}
	
	public void initializeLabels() {
		Image image;
		Image image2;
		try {
			image = ImageIO.read(new File("cut_sprites/battle_background.png"));
			backLabel = new JLabel(new ImageIcon(image));
			backLabel.setSize(width, (height/4)*3);
			backLabel.setLocation(0, 0);
			
			image2 = ImageIO.read(new File("cut_sprites/throw_1.png"));
			trainLabel = new JLabel(new ImageIcon(image2));
			trainLabel.setLocation(200, height-200);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void initializeButtonPanel(){
		buttonPanel = new JPanel();
		buttonPanel.setSize(this.width, this.height/4);
		buttonPanel.setLayout(new GridLayout(2, 2));
		buttonPanel.setLocation(0, (height/4)*3+1);
		JButton bait = new JButton("Throw Bait");
		JButton rock = new JButton("Throw Rock");
		JButton ball = new JButton("Throw Ball");
		JButton run = new JButton("Run Away");      
		buttonPanel.add(bait);
		buttonPanel.add(rock);
		buttonPanel.add(ball);
		buttonPanel.add(run);
	}
	
	public void actionMade(String action){
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		this.add(backLabel);
		this.add(buttonPanel);
		this.add(trainLabel);
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		ImageIcon trainer = new ImageIcon("cut_sprites/throw_1.png");
	}

}
