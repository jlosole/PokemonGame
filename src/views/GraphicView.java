package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.*;
import model.Items.ItemType;
import model.Map.*;
import model.ObstacleType.ObstacleType;

public class GraphicView extends JPanel implements Observer {
	
	private Game theGame;
	private int width, height, size, wSpacing, hSpacing;
	private _Map theMap;
	private Object [][] objBoard;
	private Trainer trainer;
	private Point trainerPos;
	
	public GraphicView(Game theGame, int width, int height){
		this.theGame = theGame;
		this.width = width;
		this.height = height;
		theMap = theGame.getMap();
		objBoard = theMap.getObjMap();
		size = theGame.getSize();
		trainerPos = trainer.getCurrentPos();
		
		wSpacing = width/size;
		hSpacing = height/size;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i = 0; i < 23; i++) {
			for(int j = 0; j < 23; j++) {
				if(objBoard[i][j].equals(ObstacleType.Tree)) {
					g.setColor(Color.DARK_GRAY);
					g.fillRect(j*30, i*30, 30, 30);
				}
				else if(objBoard[i][j].equals(ObstacleType.DeepGrass)) {
					g.setColor(Color.GREEN);
					g.fillRect(j*30, i*30, 30, 30);
				}
				else if(objBoard[i][j].equals(ObstacleType.Water)) {
					g.setColor(Color.BLUE);
					g.fillRect(j*30, i*30, 30, 30);
				}
				else if(objBoard[i][j].equals(ObstacleType.Bush)) {
					g.setColor(Color.YELLOW);
					g.fillRect(j*30, i*30, 30, 30);
				}
				else if(objBoard[i][j].equals(ObstacleType.Dirt)) {
					g.setColor(Color.ORANGE);
					g.fillRect(j*30, i*30, 30, 30);
				}
				else if(objBoard[i][j].equals(ObstacleType.ShortGrass)) {
					g.setColor(Color.RED);
					g.fillRect(j*30, i*30, 30, 30);
				}
				else if(objBoard[i][j].equals(ItemType.Bait)) {
					g.setColor(Color.BLACK);
					g.fillRect(j*30, i*30, 30, 30);
				}
				else if(objBoard[i][j].equals(ItemType.Rock)) {
					g.setColor(Color.BLACK);
					g.fillRect(j*30, i*30, 30, 30);
				}
				else if(objBoard[i][j].equals(ItemType.Potion)) {
					g.setColor(Color.BLACK);
					g.fillRect(j*30, i*30, 30, 30);
				}
				else if(objBoard[i][j].equals(ItemType.SuperPotion)) {
					g.setColor(Color.BLACK);
					g.fillRect(j*30, i*30, 30, 30);
				}
				else if(objBoard[i][j].equals(ItemType.SafariBall)) {
					g.setColor(Color.BLACK);
					g.fillRect(j*30, i*30, 30, 30);
				}
				trainerPos = trainer.getCurrentPos();
				int x = trainerPos.x;
				int y = trainerPos.y;
				g.setColor(Color.cyan);
				g.fillRect(y*30, x*30, 30, 30);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
}
