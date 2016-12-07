package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Game;

public class InstructionsView extends JPanel {
	
	private Game game;
	private int width, height;
	private BufferedImage safariLogo, pikachu, mewtwo; 
	private JButton mapOneButton, mapTwoButton;
	
	public InstructionsView(Game game, int width, int height) {
		this.game = game;
		this.width = width;
		this.height = height;
		this.setSize(width, height);
		this.setLayout(null);
		
		mapOneButton = new JButton("Map One");
		mapOneButton.setSize(120, 40);
		mapOneButton.setLocation((this.width/4) - 54 , this.height-115);
		
		mapTwoButton = new JButton("Map Two");
		mapTwoButton.setSize(120, 40);
		mapTwoButton.setLocation(width-200, height-115);
		
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
		g.drawImage(safariLogo, 230, 60, null);
		g.drawImage(pikachu, 40, height-170, null);
		g.drawImage(mewtwo, 500, height-175, null);
		
		// "instructions"
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier", Font.BOLD, 34));
		g.drawString("Instructions", 200, 240);

		// "1,2,3 instructions"
		g.setFont(new Font("Courier", Font.BOLD, 16));
		g.drawString("1. To move your trainer use the arrow keys", 20, 300);
		g.drawString("2. To view your inventory press 'I' on your keyboard", 20, 340);
		g.drawString("3. To exit a battle, click the button that appears in", 20, 380);
		g.drawString("   place of the pokemon", 20, 400);
		g.drawString("Last rule: HAVE FUN!", 20, 420);
		g.drawString("Choose the map to start on below", 200, 460);
	}
	
	public JButton getButtonOne() {
		return mapOneButton;
	}
	
	public JButton getButtonTwo() {
		return mapTwoButton;
	}

}
