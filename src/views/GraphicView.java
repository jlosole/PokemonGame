package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
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

		shortGrass, tallGrass, bush, water, waterBottomLeft,
		waterBottomRight, waterBottom, waterLeft, waterRight,
		waterTopLeft, waterTopRight, waterTop, safariBall, dirt,
		tree, bait, superPotion, rock, hBotLeft, hBotRight, hDoor, hRoofBot,
		hRoofBotLeft, hRoofBotRight, hRoofMid, hRoofMidLeft, hRoofMidRight,
		hRoofTop, hRoofTopLeft, hRoofTopRight, hWindowLeft, hWindowRight,
		hillBot, hillTop, hillLeft, hillRight, hillBotLeft, hillBotRight, hillTopLeft,
		hillTopRight, hillDirt, hillDirtTop, dirtLeft, dirtRight, dirtBot,
		dirtTop, dirtTopRight, dirtTopLeft, dirtBotLeft, dirtBotRight,
		woodsign, woodpegs, stairsLeft, stairsRight, graysign, inHillTopLeft,
		inHillTopRight, g2dBotLeft, g2dBotRight, g2dTopLeft, g2dTopRight;
	
	private BufferedImage trainerUp1, trainerUp2, trainerUp3,
	trainerLeft1, trainerLeft2, trainerLeft3, trainerRight1, 
	trainerRight2, trainerRight3,trainerDown1, trainerDown2, trainerDown3; 
	private BufferedImage currentTrainerImage;
	private Timer timer;
	private boolean trainerSet;
	private int trainerX, trainerY, trainerFinalX, trainerFinalY;
	private int movementPixels = 2;
	private int times = 0;
	
	public GraphicView(Game theGame, int width, int height){
		this.theGame = theGame;
		objBoard = theGame.getObjBoard();
		this.width = width;
		this.height = height;
		this.starting = true;
		this.setSize(width, height);
		timer = new Timer(100, new MoveListener());
		Point temp = this.theGame.getTrainerPos();
		trainerX = temp.x;
		trainerY = temp.y;
		loadImages();
	}
	
	public void loadImages() {
		
		/////////////////////////TRAINER IMAGES
		try {
			trainerUp1 = ImageIO.read(new File("trainerImages/trainer_up_1.png"));
			trainerUp2 = ImageIO.read(new File("trainerImages/trainer_up_2.png"));
			trainerUp3 = ImageIO.read(new File("trainerImages/trainer_up_3.png"));
			
			trainerDown1 = ImageIO.read(new File("trainerImages/trainer_up_1.png"));
			trainerDown2 = ImageIO.read(new File("trainerImages/trainer_up_2.png"));
			trainerDown3 = ImageIO.read(new File("trainerImages/trainer_up_3.png"));
			
			trainerLeft1 = ImageIO.read(new File("trainerImages/trainer_up_1.png"));
			trainerLeft2 = ImageIO.read(new File("trainerImages/trainer_up_2.png"));
			trainerLeft3 = ImageIO.read(new File("trainerImages/trainer_up_3.png"));

			trainerRight1 = ImageIO.read(new File("trainerImages/trainer_up_1.png"));
			trainerRight2 = ImageIO.read(new File("trainerImages/trainer_up_2.png"));
			trainerRight3 = ImageIO.read(new File("trainerImages/trainer_up_3.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentTrainerImage = trainerUp2;
        /////////////////////////////////////////////////////////////////////////
		
		/////////////////////////LANDSCAPE IMAGES		
		shortGrass = new ImageIcon("safariSheet/shortGrass.png");
		water = new ImageIcon("safariSheet/water.png");
		waterTop = new ImageIcon("safariSheet/water_top_middle.png");
		waterBottom = new ImageIcon("safariSheet/water_bottom.png");
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
        /////////////////////////////////////////////////////////////////////////
		
		///////////////////////// HILL/DIRT/STAIRS IMAGES
		hillTop = new ImageIcon("safariSheet/hillTop.png");
		hillBot = new ImageIcon("safariSheet/hillBot.png");
		hillLeft = new ImageIcon("safariSheet/hillLeft.png");
		hillRight = new ImageIcon("safariSheet/hillRight.png");
		hillBotLeft = new ImageIcon("safariSheet/hillBotLeft.png");
		hillBotRight = new ImageIcon("safariSheet/hillBotRight.png");
		hillTopLeft = new ImageIcon("safariSheet/hillTopLeft.png");
		hillTopRight = new ImageIcon("safariSheet/hillTopRight.png");
		hillDirt = new ImageIcon("safariSheet/hillDirt.png");
		hillDirtTop = new ImageIcon("safariSheet/hillDirtTop.png");
		inHillTopLeft = new ImageIcon("safariSheet/inHillTopLeft.png");
		inHillTopRight = new ImageIcon("safariSheet/inHillTopRight.png");

		//dirt
		g2dBotLeft = new ImageIcon("safariSheet/g2dBotLeft.png");
		g2dBotRight = new ImageIcon("safariSheet/g2dBotRight.png");
		g2dTopLeft = new ImageIcon("safariSheet/g2dTopLeft.png");
		g2dTopRight = new ImageIcon("safariSheet/g2dBotLeft.png");
		
		dirtLeft = new ImageIcon("safariSheet/dirtLeft.png");
		dirtRight = new ImageIcon("safariSheet/dirtRight.png");
		dirtBot = new ImageIcon("safariSheet/dirtBot.png");
		dirtTop = new ImageIcon("safariSheet/dirtTop.png");
		dirtTopRight = new ImageIcon("safariSheet/dirtTopRight.png");
		dirtTopLeft = new ImageIcon("safariSheet/dirtTopLeft.png");
		dirtBotLeft = new ImageIcon("safariSheet/dirtBotLeft.png");
		dirtBotRight = new ImageIcon("safariSheet/dirtBotRight.png");
		woodpegs = new ImageIcon("safariSheet/woodpegs.png");
		woodsign = new ImageIcon("safariSheet/woodsign.png");
		graysign = new ImageIcon("safariSheet/graysign.png");
		
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
		
		for(int i = 0; i < 23; i++) {
			for(int j = 0; j < 23; j++) {
				g.setColor(Color.BLACK);
				g.fillRect(j*28, i*28, 28, 28);
			}
		}

//		for(int i = x-4; i < x+4; i++) {
//			for(int j = y-4; j < y+4; j++) {
		for(int i = 0; i < 23; i++) {
			for(int j = 0; j < 23; j++) {
				if(i >= 0 && i < 23 &&  j >= 0 && j < 23) {
					
					shortGrass.paintIcon(this,g, j*28, i *28);
					
					if(objBoard[i][j].equals(ObstacleType.Tree)) {
						bush.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.DeepGrass)) {
						tallGrass.paintIcon(this, g, j*28, i*28);
					}
					// all water painting
					else if(objBoard[i][j].equals(ObstacleType.Water)) {
						water.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.WaterTop)) {
						waterTop.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.WaterLeft)) {
						waterLeft.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.WaterRight)) {
						waterRight.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.WaterBottom)) {
						waterBottom.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.WaterTopLeft)) {
						waterTopLeft.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.WaterTopRight)) {
						waterTopRight.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.WaterBottomLeft)) {
						waterBottomLeft.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.WaterBottomRight)) {
						waterBottomRight.paintIcon(this, g, j*28, i*28);
					}
					// all water painting ^^^^^^^^^^^^
					
					else if(objBoard[i][j].equals(ObstacleType.Bush)) {
						bush.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.Dirt)) {
						dirt.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.woodsign)) {
						woodsign.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.graysign)) {
						graysign.paintIcon(this, g, j*28, i*28);
					}
					
					//items vvvvvvvvvvvvvvvvvvvvvvvv
					else if(objBoard[i][j] instanceof Bait) {
						bait.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j] instanceof Rock) {
						rock.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j] instanceof Potion) {
						superPotion.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j] instanceof SuperPotion) {
						superPotion.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j] instanceof SafariBall) {
						safariBall.paintIcon(this, g, j*28, i*28);
					}
					
					//house painting
					else if(objBoard[i][j].equals(ObstacleType.hBotLeft)) {
						hBotLeft.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.hBotRight)) {
						hBotRight.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.hDoor)) {
						hDoor.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.hWindowLeft)) {
						hWindowLeft.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.hWindowRight)) {
						hWindowRight.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.hRoofBot)) {
						hRoofBot.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.hRoofBotLeft)) {
						hRoofBotLeft.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.hRoofBotRight)) {
						hRoofBotRight.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.hRoofMid)) {
						hRoofMid.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.hRoofMidLeft)) {
						hRoofMidLeft.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.hRoofMidRight)) {
						hRoofMidRight.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.hRoofTopLeft)) {
						hRoofTopLeft.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.hRoofTopRight)) {
						hRoofTopRight.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.hRoofTop)) {
						hRoofTop.paintIcon(this, g, j*28, i*28);
					}
					///////////////////////////////////////////////////////
					
					////////////////// DIRT BORDERS AND HILLS W/ STAIRS
					else if(objBoard[i][j].equals(ObstacleType.hillTop)) {
						hillTop.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.inHillTopRight)) {
						inHillTopRight.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.inHillTopLeft)) {
						inHillTopLeft.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.hillBot)) {
						hillBot.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.hillLeft)) {
						hillLeft.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.hillRight)) {
						hillRight.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.hillBotLeft)) {
						hillBotLeft.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.hillBotRight)) {
						hillBotRight.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.hillTopLeft)) {
						hillTopLeft.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.hillTopRight)) {
						hillTopRight.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.hillDirt)) {
						hillDirt.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.hillDirtTop)) {
						hillDirtTop.paintIcon(this, g, j*28, i*28);
					}
					//dirt borders
					else if(objBoard[i][j].equals(ObstacleType.dirtLeft)) {
						dirtLeft.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.dirtRight)) {
						dirtRight.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.dirtBot)) {
						dirtBot.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.dirtTop)) {
						dirtTop.paintIcon(this, g, j*28, i*28);
					}
					
					else if(objBoard[i][j].equals(ObstacleType.dirtTopRight)) {
						dirtTopRight.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.dirtTopLeft)) {
						dirtTopLeft.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.dirtBotLeft)) {
						dirtBotLeft.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.dirtBotRight)) {
						dirtBotRight.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.woodpegs)) {
						woodpegs.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.g2dBotLeft)) {
						g2dBotLeft.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.g2dBotRight)) {
						g2dBotRight.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.g2dTopLeft)) {
						g2dTopLeft.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.g2dTopRight)) {
						g2dTopRight.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.stairsLeft)) {
						stairsLeft.paintIcon(this, g, j*28, i*28);
					}
					else if(objBoard[i][j].equals(ObstacleType.stairsRight)) {
						stairsRight.paintIcon(this, g, j*28, i*28);
					}
					
					switchTrainerImage(); //Switches trainer image 
					g.drawImage(currentTrainerImage, trainerX, trainerY, null);
					///////////////////////////////////////////////////////
				}
			}
		}
	}
	
	public void startTimer() {
		timer.start();
	}
	
	public void updateAnimations() {
		if (!trainerSet) {
			moveTrainer();
			updateTimes();
		} 
		else {
			timer.stop();
		}
	}
	
	public void updateTimes(){
		times++;
		if(times == 2){
			times = 0;
		}
	}
	
	public void switchTrainerImage() {
		
		int dir = theGame.getDirection();
		System.out.println(times + "****************");
		
		if(dir == 0) { //walking up
			if(times == 0) {
				currentTrainerImage = trainerUp1;
			}
			else if(times == 1) {
				currentTrainerImage = trainerUp3;
			}
			else {
				currentTrainerImage = trainerUp2;
				timer.stop();
			}
		}
		if(dir == 1) { //walking down
			if(times == 0) {
				currentTrainerImage = trainerDown1;
			}
			else if(times == 1) {
				currentTrainerImage = trainerDown3;
			}
			else {
				currentTrainerImage = trainerDown2;
				timer.stop();
			}
		}
		if(dir == 2) { //walking left
			if(times == 0) {
				currentTrainerImage = trainerLeft1;
			}
			else if(times == 1) {
				currentTrainerImage = trainerLeft3;
			}
			else {
				currentTrainerImage = trainerLeft2;
				timer.stop();
			}
		}
		if(dir == 3) { // walking right
			if(times == 0) {
				currentTrainerImage = trainerRight1;
			}
			else if(times == 1) {
				currentTrainerImage = trainerRight3;
			}
			else {
				currentTrainerImage = trainerRight2;
				timer.stop();
			}
		}
	}
	
	public void setFinalPositions(Point point, String dir) {
		trainerX = point.x * 28;
		trainerY = point.y * 28;
		
		if(dir.equals("Up")) {
			trainerFinalY = trainerY - 28;
		}
		else if(dir.equals("Down")) {
			trainerFinalY = trainerY + 28;
		}
		else if(dir.equals("Left")) {
			trainerFinalX = trainerX - 28;
		}
		else if(dir.equals("Right")) {
			trainerFinalX = trainerX + 28;
		}
	}
	
	public void moveTrainer() {
		
		int dir = theGame.getDirection();
		
		if(dir == 0) { //moving up
			if(trainerY > trainerFinalY) {
				trainerY -= movementPixels;
			}
			else {
				trainerSet = true;
			}
		}
		else if(dir == 1) { //moving up
			if(trainerY < trainerFinalY) {
				trainerY += movementPixels;
			}
			else {
				trainerSet = true;
			}
		}
		else if(dir == 2) { //moving up
			if(trainerX > trainerFinalX) {
				trainerX -= movementPixels;
			}
			else {
				trainerSet = true;
			}
		}
		else if(dir == 3) { //moving up
			if(trainerX < trainerFinalX) {
				trainerX += movementPixels;
			}
			else {
				trainerSet = true;
			}
		}
		
	}

	
//	public ImageIcon adjustTrainerImage() {
//
//		
//	}
	
	private class MoveListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			updateAnimations();
			repaint();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
}
