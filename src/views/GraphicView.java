package views;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.*;
import model.Items.Bait;
import model.Items.Potion;
import model.Items.Rock;
import model.Items.SafariBall;
import model.Items.SuperPotion;
import model.ObstacleType.ObstacleType;

public class GraphicView extends JPanel implements Observer {
	
	private Game theGame;
	private Object [][] objBoard;
	private Point trainerPos;
	private int width, height;
	private boolean starting;
	private ImageIcon 
		trainerUp1, trainerUp2, trainerUp3,
		trainerLeft1, trainerLeft2, trainerLeft3,
		trainerRight1, trainerRight2, trainerRight3,
		trainerDown1, trainerDown2, trainerDown3,
		shortGrass, tallGrass, bush, water, waterBottomLeft,
		waterBottomRight, waterBottom, waterLeft, waterRight,
		waterTopLeft, waterTopRight, waterTop, safariBall, dirt;
	private Timer timer;
	
	public GraphicView(Game theGame, int width, int height){
		this.theGame = theGame;
		objBoard = theGame.getObjBoard();
		this.width = width;
		this.height = height;
		this.starting = true;
		this.setSize(width, height);
		timer = new Timer(100, new MoveListener());
		loadImages();
	}
	
	public void loadImages() {
		/////////////////////////TRAINER IMAGES
		trainerUp1 = new ImageIcon("cut_sprites/trainer_up_1.png");
		trainerUp2 = new ImageIcon("cut_sprites/trainer_up_2.png");
		trainerUp3 = new ImageIcon("cut_sprites/trainer_up_3.png");
		
		trainerDown1 = new ImageIcon("cut_sprites/trainer_down_1.png");
		trainerDown2 = new ImageIcon("cut_sprites/trainer_down_2.png");
		trainerDown3 = new ImageIcon("cut_sprites/trainer_down_3.png");
		
		trainerLeft1 = new ImageIcon("cut_sprites/trainer_left_1.png");
		trainerLeft2 = new ImageIcon("cut_sprites/trainer_left_2.png");
		trainerLeft3 = new ImageIcon("cut_sprites/trainer_left_3.png");

		trainerRight1 = new ImageIcon("cut_sprites/trainer_right_1.png");
		trainerRight2 = new ImageIcon("cut_sprites/trainer_right_2.png");
		trainerRight3 = new ImageIcon("cut_sprites/trainer_right_3.png");
        /////////////////////////////////////////////////////////////////////////
		
		/////////////////////////LANDSCAPE IMAGES		
		shortGrass = new ImageIcon("cut_sprites/grass.png");
		tallGrass = new ImageIcon("tempSprites/tall_grass_new.png");
		bush = new ImageIcon("tempSprites/bush.png");
		water = new ImageIcon("cut_sprites/water_middle.png");
		waterTop = new ImageIcon("cut_sprites/water_top_middle.png");
		waterBottom = new ImageIcon("cut_sprites/water_bottom_middle.png");
		waterLeft = new ImageIcon("cut_sprites/water_left.png");
		waterRight = new ImageIcon("cut_sprites/water_right.png");
		waterTopLeft = new ImageIcon("cut_sprites/water_top_left.png");
		waterTopRight = new ImageIcon("cut_sprites/water_top_right.png");
		waterBottomLeft = new ImageIcon("cut_sprites/water_bottom_left.png");
		waterBottomRight = new ImageIcon("cut_sprites/water_bottom_right.png");
		safariBall = new ImageIcon("landscape/safari-ball.png");
		dirt = new ImageIcon("cut_sprites/dirt.png");
        /////////////////////////////////////////////////////////////////////////
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		objBoard = theGame.getObjBoard();
		
		for(int i = 0; i < 23; i++) {
			for(int j = 0; j < 23; j++) {
				shortGrass.paintIcon(this,g, j*32, i *32);
				if(objBoard[i][j].equals(ObstacleType.Tree)) {
					bush.paintIcon(this, g, j*32, i*32);
				}
				else if(objBoard[i][j].equals(ObstacleType.DeepGrass)) {
					tallGrass.paintIcon(this, g, j*32, i*32);
				}
				// all water painting
				else if(objBoard[i][j].equals(ObstacleType.Water)) {
					water.paintIcon(this, g, j*32, i*32);
				}
				else if(objBoard[i][j].equals(ObstacleType.WaterTop)) {
					waterTop.paintIcon(this, g, j*32, i*32);
				}
				else if(objBoard[i][j].equals(ObstacleType.WaterLeft)) {
					waterLeft.paintIcon(this, g, j*32, i*32);
				}
				else if(objBoard[i][j].equals(ObstacleType.WaterRight)) {
					waterRight.paintIcon(this, g, j*32, i*32);
				}
				else if(objBoard[i][j].equals(ObstacleType.WaterBottom)) {
					waterBottom.paintIcon(this, g, j*32, i*32);
				}
				else if(objBoard[i][j].equals(ObstacleType.WaterTopLeft)) {
					waterTopLeft.paintIcon(this, g, j*32, i*32);
				}
				else if(objBoard[i][j].equals(ObstacleType.WaterTopRight)) {
					waterTopRight.paintIcon(this, g, j*32, i*32);
				}
				else if(objBoard[i][j].equals(ObstacleType.WaterBottomLeft)) {
					waterBottomLeft.paintIcon(this, g, j*32, i*32);
				}
				else if(objBoard[i][j].equals(ObstacleType.WaterBottomRight)) {
					waterBottomRight.paintIcon(this, g, j*32, i*32);
				}
				// all water painting ^^^^^^^^^^^^
				
				else if(objBoard[i][j].equals(ObstacleType.Bush)) {
					bush.paintIcon(this, g, j*32, i*32);
				}
				else if(objBoard[i][j].equals(ObstacleType.Dirt)) {
					dirt.paintIcon(this, g, j*32, i*32);
				}
				else if(objBoard[i][j].equals(ObstacleType.ShortGrass)) {
					shortGrass.paintIcon(this, g, j*32, i*32);
				}
				else if(objBoard[i][j] instanceof Bait) {
					shortGrass.paintIcon(this, g, j*32, i*32);
					safariBall.paintIcon(this, g, j*32, i*32);
				}
				else if(objBoard[i][j] instanceof Rock) {
					shortGrass.paintIcon(this, g, j*32, i*32);
					safariBall.paintIcon(this, g, j*32, i*32);
				}
				else if(objBoard[i][j] instanceof Potion) {
					shortGrass.paintIcon(this, g, j*32, i*32);
					safariBall.paintIcon(this, g, j*32, i*32);
				}
				else if(objBoard[i][j] instanceof SuperPotion) {
					shortGrass.paintIcon(this, g, j*32, i*32);
					safariBall.paintIcon(this, g, j*32, i*32);
				}
				else if(objBoard[i][j] instanceof SafariBall) {
					shortGrass.paintIcon(this, g, j*32, i*32);
					safariBall.paintIcon(this, g, j*32, i*32);
				}
				
				trainerPos = theGame.getTrainerPos();
				int x = trainerPos.x;
				int y = trainerPos.y;
				String dir = theGame.getDirection();
//				ImageIcon tempTrainer = null;
//				switch(dir) {
//					case "Up": tempTrainer = trainerUp2;
//					case "Down": tempTrainer = trainerDown2;
//					case "Right": tempTrainer = trainerRight2;
//					case "Left": tempTrainer = trainerLeft2;
//				}
//				ImageIcon trainerToPaint = adjustTrainerImage(tempTrainer, x, y);
				if(starting) {				
					trainerUp2.paintIcon(this, g, y*32, x*32);
					starting = false;
				}
				else {
					switch(dir) {
						case "Up": trainerUp2.paintIcon(this, g, y*32, x*32);
						break;
						case "Left": trainerLeft2.paintIcon(this, g, y*32, x*32);
						break;
						case "Right": trainerRight2.paintIcon(this, g, y*32, x*32);
						break;
						case "Down": trainerDown2.paintIcon(this, g, y*32, x*32);
							default: break;
					}
				}
//				timer.start();
			}
		}
	}
	
	public void updateAnimations() {
		// What do I need to draw? (check state)
		if (starting) {
			repaint();
		} else if (!theGame.gameOver()) {
			
		} else {
			
		}
	}
	
	public ImageIcon adjustTrainerImage(String dir, int trainerX, int trainerY) {
//		switch(dir) {
//			case "Up": return trainerUp2;
//			case "Left": return trainerLeft2;
//			case "Right": return trainerRight2;
//			case "Down": return trainerDown2;
//			default: return null;
//		}
		return null;
	}
	
	private class MoveListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
}
