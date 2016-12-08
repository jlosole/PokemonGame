package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Game;

public class LoadingView extends JPanel{

	private int width, height;
	private Image image;
	private JButton yes;
	private JButton no;
	private JButton winBySteps, winByNoBalls, winByCaptures;
	private Boolean firstWindowDone = false;
	
	public LoadingView(int width, int height){
		this.width = width;
		this.height = height;
		this.setSize(width, height);
		this.setLayout(null);
		
		try {
			image = ImageIO.read(new File("safariSheet/biggerSafariLogo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setUpButtons();
		repaint();
	}
	
	public void update(){
		repaint();
	}
	
	public void setUpButtons(){
		//Buttons for the first loading screen
		yes = new JButton("YES");
		yes.setSize(100, 20);
		yes.setLocation((width/4) - 54 , height-115);
		no = new JButton("NO");
		no.setSize(100, 20);
		no.setLocation(width-200, height-115);
		
		//Buttons for the second loading screen
		winBySteps = new JButton("Run out of steps");
		winBySteps.setSize(200, 20);
		winBySteps.setLocation(width/4-100, height-115);
		
		winByCaptures = new JButton("Capture 8 Pokemon");
		winByCaptures.setSize(200, 20);
		winByCaptures.setLocation(width-250, height-115);
		
		winByNoBalls = new JButton("Run out of balls");
		winByNoBalls.setSize(200, 20);
		winByNoBalls.setLocation(width/2-70, height-65);
	}
	
	public void setFirstWindowDone(){
		firstWindowDone = true;
		this.remove(no);
		this.remove(yes);
	}
	
	public Boolean getFirstWindowDone(){
		return firstWindowDone;
	}
	
	
	public void paintComponent(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		g.drawImage(image, 40, 40, null);
		g.setColor(Color.black);
		g.setFont(new Font("Courier", Font.BOLD, 16));
		if(!firstWindowDone){
			this.add(no);
			this.add(yes);
			g.drawString("Would you like to start from a previously saved game?", width/6-50,
					height-155);
		}
		else{
			this.add(winByCaptures);
			this.add(winBySteps);
			this.add(winByNoBalls);
			g.drawString("Select your win condition below!", width/4, height-155);
		}
	}
	
	public JButton getYesButton(){
		return yes;
	}
	public JButton getNoButton(){
		return no;
	}
	public JButton getStepsButton(){
		return winBySteps;
	}
	public JButton getCatchesButton(){
		return winByCaptures;
	}
	public JButton getNoBallsButton(){
		return winByNoBalls;
	}

}
