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
		g2.drawImage(backImage, 40, 40, null);
		g2.setFont(new Font("Courier", Font.BOLD, 24));
		g2.setColor(Color.WHITE);
		g2.drawString("GAME OVER!", width/6-50, height-155);
		g2.drawString(winCondition, width/6-150, height-120);
		
	}

}
