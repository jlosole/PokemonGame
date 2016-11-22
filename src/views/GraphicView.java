package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.*;
import model.Map.*;

public class GraphicView extends JPanel implements Observer {
	
	private Game theGame;
	private int width, height, size, wSpacing, hSpacing;
	private _Map theMap;
	private char [][] textBoard;
	private Object [][] objBoard;
	private Trainer trainer;
	private Point trainerPos;
	
	public GraphicView(Game theGame, int width, int height){
		this.theGame = theGame;
		this.width = width;
		this.height = height;
		theMap = theGame.getMap();
		textBoard = theMap.getTextMap();
		objBoard = theMap.getObjMap();
		size = theGame.getSize();
		trainer = theGame.getTrainer();
		trainerPos = trainer.getCurrentPos();
		
		wSpacing = width/size;
		hSpacing = height/size;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println("in paint");
		for(int i = 0; i < 23; i++) {
			for(int j = 0; j < 23; j++) {

				if(textBoard[i][j] == 'T') {
					g.setColor(Color.DARK_GRAY);
					g.fillRect(j*30, i*30, 30, 30);
				}
				else if(textBoard[i][j] == 'G') {
					g.setColor(Color.GREEN);
					g.fillRect(j*30, i*30, 30, 30);
				}
				else if(textBoard[i][j] == 'W') {
					g.setColor(Color.BLUE);
					g.fillRect(j*30, i*30, 30, 30);
				}
				else if(textBoard[i][j] == 'B') {
					g.setColor(Color.YELLOW);
					g.fillRect(j*30, i*30, 30, 30);
				}
				else if(textBoard[i][j] == 'D') {
					g.setColor(Color.ORANGE);
					g.fillRect(j*30, i*30, 30, 30);
				}
				else if(textBoard[i][j] == 'g') {
					g.setColor(Color.RED);
					g.fillRect(j*30, i*30, 30, 30);
				}
				else if(textBoard[i][j] == 'P') {
					g.setColor(Color.WHITE);
					g.fillRect(j*30, i*30, 30, 30);
				}
				else if(textBoard[i][j] == 'I') {
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
