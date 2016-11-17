package views;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Map.MapOne;

public class GraphicView extends JPanel {
	
	private MapOne map = new MapOne();
	private char[][] textMap = map.getTextMap();
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i = 0; i < 23; i++) {
			for(int j = 0; j < 23; j++) {
				if(textMap[i][j] == 'T') {
					g.setColor(Color.DARK_GRAY);
					g.fillRect(j*10, i*10, 10, 10);
				}
				else if(textMap[i][j] == 'G') {
					g.setColor(Color.GREEN);
					g.fillRect(j*10, i*10, 10, 10);
				}
				else if(textMap[i][j] == 'W') {
					g.setColor(Color.BLUE);
					g.fillRect(j*10, i*10, 10, 10);
				}
				else if(textMap[i][j] == 'B') {
					g.setColor(Color.YELLOW);
					g.fillRect(j*10, i*10, 10, 10);
				}
				else if(textMap[i][j] == 'D') {
					g.setColor(Color.ORANGE);
					g.fillRect(j*10, i*10, 10, 10);
				}
				else if(textMap[i][j] == 'g') {
					g.setColor(Color.RED);
					g.fillRect(j*10, i*10, 10, 10);
				}
				else if(textMap[i][j] == 'P') {
					g.setColor(Color.WHITE);
					g.fillRect(j*10, i*10, 10, 10);
				}
				else if(textMap[i][j] == 'I') {
					g.setColor(Color.BLACK);
					g.fillRect(j*10, i*10, 10, 10);
				}
			}
		}
	}
}
