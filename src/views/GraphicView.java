package views;

import java.awt.Color;
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
		waterTopLeft, waterTopRight, waterTop, safariBall, dirt,
		tree, bait, superPotion, rock, scatteredStone, stoneBot, stoneBotLeft,
		stoneBotRight, stoneLeft, stoneRight, stoneTopLeft, stoneTopRight,
		stoneWalk, stoneWalk2, hBotLeft, hBotRight, hDoor, hRoofBot,
		hRoofBotLeft, hRoofBotRight, hRoofMid, hRoofMidLeft, hRoofMidRight,
		hRoofTop, hRoofTopLeft, hRoofTopRight, hWindowLeft, hWindowRight,
		hillLeft, hillRight, hillBotLeft, hillBotRight, hillTopLeft,
		hillTopRight, hillDirt, hillDirtTop, dirtLeft, dirtRight, dirtBot,
		dirtTop, dirtTopRight, dirtTopLeft, dirtBotLeft, dirtBotRight,
		woodpegs, stairsLeft, stairsRight;
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
		trainerUp1 = new ImageIcon("trainerImages/trainer_up_1.png");
		trainerUp2 = new ImageIcon("trainerImages/trainer_up_2.png");
		trainerUp3 = new ImageIcon("trainerImages/trainer_up_3.png");
		
		trainerDown1 = new ImageIcon("trainerImages/trainer_down_1.png");
		trainerDown2 = new ImageIcon("trainerImages/trainer_down_2.png");
		trainerDown3 = new ImageIcon("trainerImages/trainer_down_3.png");
		
		trainerLeft1 = new ImageIcon("trainerImages/trainer_left_1.png");
		trainerLeft2 = new ImageIcon("trainerImages/trainer_left_2.png");
		trainerLeft3 = new ImageIcon("trainerImages/trainer_left_3.png");

		trainerRight1 = new ImageIcon("trainerImages/trainer_right_1.png");
		trainerRight2 = new ImageIcon("trainerImages/trainer_right_2.png");
		trainerRight3 = new ImageIcon("trainerImages/trainer_right_3.png");
        /////////////////////////////////////////////////////////////////////////
		
		/////////////////////////LANDSCAPE IMAGES		
		shortGrass = new ImageIcon("safariSheet/shortGrass.png");
		water = new ImageIcon("safariSheet/water.png");
		waterTop = new ImageIcon("safariSheet/water_top_middle.png");
		waterBottom = new ImageIcon("safariSheet/water_bottom_middle.png");
		waterLeft = new ImageIcon("safariSheet/water_left.png");
		waterRight = new ImageIcon("safariSheet/water_right.png");
		waterTopLeft = new ImageIcon("safariSheet/water_top_left.png");
		waterTopRight = new ImageIcon("safariSheet/water_top_right.png");
		waterBottomLeft = new ImageIcon("safariSheet/water_bottom_left.png");
		waterBottomRight = new ImageIcon("safariSheet/water_bottom_right.png");
		safariBall = new ImageIcon("cut_sprites/safari-ball.png");
		dirt = new ImageIcon("safariSheet/dirt.png");
		tree = new ImageIcon("cut_sprites/tree.png");
		tallGrass = new ImageIcon("safariSheet/deepGrass.png");
		bush = new ImageIcon("safariSheet/bush.png");
		scatteredStone = new ImageIcon("houseTiles/scatteredStone.png");
//		stoneBot = new ImageIcon("landscapeTiles/stoneBottom.png");
//		stoneBotLeft = new ImageIcon("landscapeTiles/stoneBotLeft.png");
//		stoneBotRight = new ImageIcon("landscapeTiles/stoneBotRight.png");
//		stoneLeft = new ImageIcon("landscapeTiles/stoneLeft.png");
//		stoneRight = new ImageIcon("landscapeTiles/stoneRight.png");
//		stoneTopLeft = new ImageIcon("landscapeTiles/stoneTopLeft.png");
//		stoneTopRight = new ImageIcon("landscapeTiles/stoneTopRight.png");
//		stoneWalk = new ImageIcon("landscapeTiles/stoneWalk.png");
//		stoneWalk2 = new ImageIcon("landscapeTiles/stoneWalk2.png");
        /////////////////////////////////////////////////////////////////////////
		
		///////////////////////// HILL/DIRT/STAIRS IMAGES
		hillLeft = new ImageIcon("safariSheet/hillLeft.png");
		hillRight = new ImageIcon("safariSheet/hillRight.png");
		hillBotLeft = new ImageIcon("safariSheet/hillBotLeft.png");
		hillBotRight = new ImageIcon("safariSheet/hillBotRight.png");
		hillTopLeft = new ImageIcon("safariSheet/hillTopLeft.png");
		hillTopRight = new ImageIcon("safariSheet/hillTopRight.png");
		hillDirt = new ImageIcon("safariSheet/hillDirt.png");
		hillDirtTop = new ImageIcon("safariSheet/hillDirtTop.png");
		
		//dirt
		dirtLeft = new ImageIcon("safariSheet/dirtLeft.png");
		dirtRight = new ImageIcon("safariSheet/dirtRight.png");
		dirtBot = new ImageIcon("safariSheet/dirtBot.png");
		dirtTop = new ImageIcon("safariSheet/dirtTop.png");
		dirtTopRight = new ImageIcon("safariSheet/dirtTopRight.png");
		dirtTopLeft = new ImageIcon("safariSheet/dirtTopLeft.png");
		dirtBotLeft = new ImageIcon("safariSheet/dirtBotLeft.png");
		dirtBotRight = new ImageIcon("safariSheet/dirtBotRight.png");
		woodpegs = new ImageIcon("safariSheet/woodpegs.png");
		
		//stairs
		stairsLeft = new ImageIcon("safariSheet/stairsLeft.png");
		stairsRight = new ImageIcon("safariSheet/stairsRight.png");

		/////////////////////////ITEM IMAGES
		bait = new ImageIcon("cut_sprites/bait.png");
		rock = new ImageIcon("cut_sprites/throwing_rock.png");
		superPotion = new ImageIcon("cut_sprites/super_potion.png");
		
        ////////////////////////HOUSING IMAGES
		hBotLeft = new ImageIcon("houseTiles/botLeft.png");
		hBotRight = new ImageIcon("houseTiles/botRight.png");
		hDoor = new ImageIcon("houseTiles/door.png"); 
		hRoofBot = new ImageIcon("houseTiles/roofBot.png");
		hRoofBotLeft = new ImageIcon("houseTiles/roofBotLeft.png"); 
		hRoofBotRight = new ImageIcon("houseTiles/roofBotRight.png"); 
		hRoofMid = new ImageIcon("houseTiles/roofMid.png"); 
		hRoofMidLeft = new ImageIcon("houseTiles/roofMidLeft.png"); 
		hRoofMidRight = new ImageIcon("houseTiles/roofMidRight.png");
		hRoofTop = new ImageIcon("houseTiles/roofTop.png"); 
		hRoofTopLeft = new ImageIcon("houseTiles/roofTopLeft.png"); 
		hRoofTopRight = new ImageIcon("houseTiles/roofTopRight.png"); 
		hWindowLeft = new ImageIcon("houseTiles/windowLeft.png"); 
		hWindowRight = new ImageIcon("houseTiles/windowRight.png");
        /////////////////////////////////////////////////////////////////////////
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		objBoard = theGame.getObjBoard();

		ImageIcon trainer;
		
		for(int i = 0; i < 23; i++) {
			for(int j = 0; j < 23; j++) {
				g.setColor(Color.BLACK);
				g.fillRect(j*32, i*32, 32, 32);
			}
		}
		
		trainerPos = theGame.getTrainerPos();
		int x = trainerPos.x;
		int y = trainerPos.y;
		
		for(int i = x-4; i < x+4; i++) {
			for(int j = y-4; j < y+4; j++) {
				if(i >= 0 && i < 23 &&  j >= 0 && j < 23) {
					
					shortGrass.paintIcon(this,g, j*32, i *32);
					
					if(objBoard[i][j].equals(ObstacleType.Tree)) {
						tree.paintIcon(this, g, j*32, i*32);
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
					
					//all stone painting vvvvvvvvvv
//					else if(objBoard[i][j].equals(ObstacleType.ScatteredStone)) {
//						scatteredStone.paintIcon(this, g, j*32, i*32);
//					}
//					else if(objBoard[i][j].equals(ObstacleType.StoneBot)) {
//						stoneBot.paintIcon(this, g, j*32, i*32);
//					}
//					else if(objBoard[i][j].equals(ObstacleType.StoneBotLeft)) {
//						stoneBotLeft.paintIcon(this, g, j*32, i*32);
//					}
//					else if(objBoard[i][j].equals(ObstacleType.StoneBotRight)) {
//						stoneBotRight.paintIcon(this, g, j*32, i*32);
//					}
//					else if(objBoard[i][j].equals(ObstacleType.StoneLeft)) {
//						stoneLeft.paintIcon(this, g, j*32, i*32);
//					}
//					else if(objBoard[i][j].equals(ObstacleType.StoneRight)) {
//						stoneRight.paintIcon(this, g, j*32, i*32);
//					}
//					else if(objBoard[i][j].equals(ObstacleType.StoneTopLeft)) {
//						stoneTopLeft.paintIcon(this, g, j*32, i*32);
//					}
//					else if(objBoard[i][j].equals(ObstacleType.StoneTopRight)) {
//						stoneTopRight.paintIcon(this, g, j*32, i*32);
//					}
//					else if(objBoard[i][j].equals(ObstacleType.StoneWalk)) {
//						stoneWalk.paintIcon(this, g, j*32, i*32);
//					}
//					else if(objBoard[i][j].equals(ObstacleType.StoneWalk2)) {
//						stoneWalk2.paintIcon(this, g, j*32, i*32);
//					}
					//all stone painting ^^^^^^^^^^^
					
					else if(objBoard[i][j].equals(ObstacleType.Bush)) {
						bush.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.Dirt)) {
						dirt.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j] instanceof Bait) {
						bait.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j] instanceof Rock) {
						rock.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j] instanceof Potion) {
						superPotion.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j] instanceof SuperPotion) {
						superPotion.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j] instanceof SafariBall) {
						safariBall.paintIcon(this, g, j*32, i*32);
					}
					
					//house painting
					else if(objBoard[i][j].equals(ObstacleType.hBotLeft)) {
						hBotLeft.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.hBotRight)) {
						hBotRight.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.hDoor)) {
						hDoor.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.hWindowLeft)) {
						hWindowLeft.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.hWindowRight)) {
						hWindowRight.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.hRoofBot)) {
						hRoofBot.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.hRoofBotLeft)) {
						hRoofBotLeft.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.hRoofBotRight)) {
						hRoofBotRight.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.hRoofMid)) {
						hRoofMid.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.hRoofMidLeft)) {
						hRoofMidLeft.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.hRoofMidRight)) {
						hRoofMidRight.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.hRoofTopLeft)) {
						hRoofTopLeft.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.hRoofTopRight)) {
						hRoofTopRight.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.hRoofTop)) {
						hRoofTop.paintIcon(this, g, j*32, i*32);
					}
					///////////////////////////////////////////////////////
					
					////////////////// DIRT BORDERS AND HILLS W/ STAIRS
					else if(objBoard[i][j].equals(ObstacleType.hillLeft)) {
						hillLeft.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.hillRight)) {
						hillRight.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.hillBotLeft)) {
						hillBotLeft.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.hillBotRight)) {
						hillBotRight.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.hillTopLeft)) {
						hillTopLeft.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.hillTopRight)) {
						hillTopRight.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.hillDirt)) {
						hillDirt.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.hillDirtTop)) {
						hillDirtTop.paintIcon(this, g, j*32, i*32);
					}
					//dirt borders
					else if(objBoard[i][j].equals(ObstacleType.dirtLeft)) {
						dirtLeft.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.dirtRight)) {
						dirtRight.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.dirtBot)) {
						dirtBot.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.dirtTop)) {
						dirtTop.paintIcon(this, g, j*32, i*32);
					}
					
					else if(objBoard[i][j].equals(ObstacleType.dirtTopRight)) {
						dirtTopRight.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.dirtTopLeft)) {
						dirtTopLeft.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.dirtBotLeft)) {
						dirtBotLeft.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.dirtBotRight)) {
						dirtBotRight.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.woodpegs)) {
						woodpegs.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.stairsLeft)) {
						stairsLeft.paintIcon(this, g, j*32, i*32);
					}
					else if(objBoard[i][j].equals(ObstacleType.stairsRight)) {
						stairsRight.paintIcon(this, g, j*32, i*32);
					}
					///////////////////////////////////////////////////////
	
					//Trainer faces whichever direction we're walking
					if (theGame.getDirection() == 0) {
						trainer = new ImageIcon("cut_sprites/trainer_up_2.png");
					} else if (theGame.getDirection() == 1) {
						trainer= new ImageIcon("cut_sprites/trainer_down_2.png");
					} else if (theGame.getDirection() == 2) {
						trainer= new ImageIcon("cut_sprites/trainer_left_2.png");
					} else {
						trainer= new ImageIcon("cut_sprites/trainer_right_2.png");
					}
					trainer.paintIcon(this, g, y*32, x*32);
					timer.start();
				}
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
