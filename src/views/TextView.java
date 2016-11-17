package views;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import model.Map.MapOne;

public class TextView extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	
	private MapOne 			theMap;
	private JTextArea 		textArea;
	
	public TextView(MapOne map) {
		theMap = map;
		initializeView();
	}
	
	private void initializeView() {
		setLayout(new BorderLayout());
		textArea = new JTextArea(10, 10);
		textArea.setEditable(false);
		textArea.setText(theMap.toString());
		add(textArea);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
