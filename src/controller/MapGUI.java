package controller;

import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JFrame;

import views.GraphicView;
import model.Pokemon.Charmander;
import model.Pokemon.Magmar;
import model.Pokemon.MewTwo;
import model.Pokemon.Pikachu;

public class MapGUI extends JFrame {
	
	private GraphicView graphicView;
	
	public static void main(String[] args) {
		MapGUI mp = new MapGUI();
		mp.setVisible(true);
	}
	
	public MapGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(400, 400);
	    this.setLocation(100, 40);
	    this.setLayout(new BorderLayout());
	    graphicView = new GraphicView();
	    this.add(graphicView, BorderLayout.CENTER);
	}
}
