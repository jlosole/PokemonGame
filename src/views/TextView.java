package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import model.Game;
import model.Map.MapOne;
import model.Map._Map;

public class TextView extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	
	private _Map 			theMap;
	private Game 			theGame;
	private JTextArea 		textArea;
	
	public TextView(Game theGame) {
		this.theGame = theGame;
		this.theMap = theGame.getMap();
		initializeView();
	}
	
	private void initializeView() {
		setLayout(new BorderLayout());
		textArea = new JTextArea(10, 10);
		textArea.setEditable(false);
		textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		textArea.setText(theMap.toString());
		add(textArea);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		textArea.setText(theMap.toString());
	}

}
