package views;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Game;

public class LoadingView extends JPanel implements Observer {

	private Game theGame;
	private int width, height;
	private Image image;
	
	public LoadingView(Game game, int width, int height){
		theGame = game;
		this.width = width;
		this.height = height;
		this.setSize(width, height);
		try {
			image = ImageIO.read(new File("cut_sprites/safari_zone.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(image, 0, 0, null);
	}

}
