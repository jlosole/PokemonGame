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
//	private static final long serialVersionUID = 1L;
	
	private JTextArea textArea;
	private _Map theMap;
	private Game theGame;
	private int width, height;
	
	public TextView(Game theGame, int width, int height) {
		this.theGame = theGame;
		this.theMap = theGame.getMap();
		this.width = width;
		this.height = height;
		initializeView();
	}
	
	private void initializeView() {
		setLayout(new BorderLayout());
		textArea = new JTextArea(width, height);
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
