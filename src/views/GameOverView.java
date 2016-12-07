package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import model.Game;

public class GameOverView extends JPanel {
	
	private String winCondition;
	private int width, height;
	private BufferedImage backImage;
	
	public GameOverView(String winCondition, int width, int height) {
		this.winCondition = winCondition;
		this.width = width;
		this.height = height;
		this.setSize(width, height);
		try {
			backImage = ImageIO.read(new File("safariSheet/safariLogo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update(){
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setBackground(Color.BLACK);
		g2.drawImage(backImage, 230, 60, null);
		g2.setFont(new Font("Courier", Font.BOLD, 36));
		g2.drawString("GAME OVER!", 210, 240);
		g2.setFont(new Font("Courier", Font.BOLD, 28));
		if(winCondition.equals("Steps")){
			g2.drawString("You used all of your steps!", 105, 280);
		}
		else if(winCondition.equals("Catches"))
			g2.drawString("You caught 8 Pokemon!", 140, 280);
		else if(winCondition.equals("noBalls")){
			g2.drawString("You used all of your Safari Balls!", 40, 280);
		}
		
	}

}
