package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Game;

public class InstructionsView extends JPanel {
	
	private int width, height;
	private BufferedImage safariLogo, pikachu, mewtwo; 
	private JButton mapOneButton, mapTwoButton;
	
	public InstructionsView(int width, int height) {
		this.width = width;
		this.height = height;
		this.setSize(width, height);
		this.setLayout(null);
		
		mapOneButton = new JButton("Map One");
		mapOneButton.setSize(120, 40);
		mapOneButton.setLocation((this.width/4), this.height-90);
		
		mapTwoButton = new JButton("Map Two");
		mapTwoButton.setSize(120, 40);
		mapTwoButton.setLocation(width-300, height-90);
		
		this.add(mapOneButton);
		this.add(mapTwoButton);
		
		try {
			safariLogo = ImageIO.read(new File("safariSheet/safariLogo.png"));
			pikachu = ImageIO.read(new File("cut_sprites/pikachu.png"));
			mewtwo = ImageIO.read(new File("cut_sprites/mewtwo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		g.drawImage(safariLogo, 230, 30, null);
		g.drawImage(pikachu, 40, height-150, null);
		g.drawImage(mewtwo, 500, height-150, null);
		
		// "instructions title"
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier", Font.BOLD, 34));
		g.drawString("Instructions", 200, 210);

		// "Numbered instructions"
		g.setFont(new Font("Courier", Font.BOLD, 16));
		g.drawString("1. To move your trainer use the arrow keys", 20, 250);
		g.drawString("2. To open and close the inventory press 'I' on your keyboard", 20, 290);
		g.drawString("3. To use a potion on a pokemon first click the item", 20, 320);
		g.drawString("   button you want to use then click the pokemon you", 20, 340);
		g.drawString("   want to use it on", 20, 360);
		g.drawString("4. To exit a battle, click the button that appears in", 20, 400);
		g.drawString("   place of the pokemon, or click the 'Run Away' button", 20, 420);
		g.drawString("5. To open and close the Pokedex press 'P' on your keyboard", 20, 460);
		g.drawString("Choose the map to start on below:", 150, 560);
	}
	
	public JButton getButtonOne() {
		return mapOneButton;
	}
	
	public JButton getButtonTwo() {
		return mapTwoButton;
	}
	
	public void update(){
		repaint();
	}
}
