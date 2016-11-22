package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.*;
import model.Items.Bait;
import model.Items.ItemType;
import model.Items.Potion;
import model.Items.Rock;
import model.Items.SafariBall;
import model.Items.SuperPotion;
import model.Map.*;
import model.ObstacleType.ObstacleType;

public class GraphicView extends JPanel implements Observer {
	
	private Game theGame;
	private int width, height, size, wSpacing, hSpacing;
	private _Map theMap;
	private Object [][] objBoard;
	private Point trainerPos;
	
	public GraphicView(Game theGame, int width, int height){
		this.theGame = theGame;
		this.width = width;
		this.height = height;
		objBoard = theGame.getObjBoard();
		size = theGame.getSize();
		trainerPos = theGame.getTrainerPos();
		wSpacing = width/size;
		hSpacing = height/size;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		objBoard = theGame.getObjBoard();
		
		ImageIcon shortGrass = new ImageIcon("cut_sprites/grass.png");
		ImageIcon tallGrass = new ImageIcon("cut_sprites/tall_grass.png");
		ImageIcon bush = new ImageIcon("cut_sprites/bush.png");
		ImageIcon water = new ImageIcon("cut_sprites/water_middle.png");
		ImageIcon pokeball = new ImageIcon("cut_sprites/pokeball.png");
		ImageIcon dirt = new ImageIcon("landscape/dirt.png");
		ImageIcon trainer = new ImageIcon("landscape/trainer.png");

		for(int i = 0; i < 23; i++) {
			for(int j = 0; j < 23; j++) {
				if(objBoard[i][j].equals(ObstacleType.Tree)) {
					bush.paintIcon(this, g, j*16, i*16);
				}
				else if(objBoard[i][j].equals(ObstacleType.DeepGrass)) {
					tallGrass.paintIcon(this, g, j*16, i*16);
				}
				else if(objBoard[i][j].equals(ObstacleType.Water)) {
					water.paintIcon(this, g, j*16, i*16);
				}
				else if(objBoard[i][j].equals(ObstacleType.Bush)) {
					bush.paintIcon(this, g, j*16, i*16);
				}
				else if(objBoard[i][j].equals(ObstacleType.Dirt)) {
					dirt.paintIcon(this, g, j*16, i*16);
				}
				else if(objBoard[i][j].equals(ObstacleType.ShortGrass)) {
					shortGrass.paintIcon(this, g, j*16, i*16);
				}
				else if(objBoard[i][j] instanceof Bait) {
					pokeball.paintIcon(this, g, j*16, i*16);
				}
				else if(objBoard[i][j] instanceof Rock) {
					pokeball.paintIcon(this, g, j*16, i*16);
				}
				else if(objBoard[i][j] instanceof Potion) {
					pokeball.paintIcon(this, g, j*16, i*16);
				}
				else if(objBoard[i][j] instanceof SuperPotion) {
					pokeball.paintIcon(this, g, j*16, i*16);
				}
				else if(objBoard[i][j] instanceof SafariBall) {
					pokeball.paintIcon(this, g, j*16, i*16);
				}
				trainerPos = theGame.getTrainerPos();
				int x = trainerPos.x;
				int y = trainerPos.y;
				trainer.paintIcon(this, g, y*16, x*16);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
}
