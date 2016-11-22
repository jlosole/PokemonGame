package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import model.Game;
import model.Items.Item;
import model.Map.MapOne;
import model.Map._Map;
import model.ObstacleType.ObstacleType;

public class TextView extends JPanel implements Observer {
//	private static final long serialVersionUID = 1L;
	
	private JTextArea textArea;
	private char [][] textBoard;
	private Object [][] objBoard;
	private Game theGame;
	private int width, height, size;
	
	public TextView(Game theGame, int width, int height) {
		this.theGame = theGame;
		this.width = width;
		this.height = height;
		size = theGame.getSize();
		textBoard = new char[size][size];
		objBoard = theGame.getObjBoard();
		initializeView();
		updateTextBoard();
	}
	
	private void initializeView() {
		setLayout(new BorderLayout());
		textArea = new JTextArea(width, height);
		textArea.setEditable(false);
		textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		textArea.setText(toString());
		add(textArea);
	}
	
	private void updateTextBoard(){
		objBoard = theGame.getObjBoard();
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++) {
				if(objBoard[i][j].equals(ObstacleType.Tree)) 
					textBoard[i][j] = 'T';
				else if(objBoard[i][j].equals(ObstacleType.DeepGrass)) 
					textBoard[i][j] = 'G';
				else if(objBoard[i][j].equals(ObstacleType.Water)) 
					textBoard[i][j] = 'W';
				else if(objBoard[i][j].equals(ObstacleType.Bush)) 
					textBoard[i][j] = 'B';				
				else if(objBoard[i][j].equals(ObstacleType.Dirt)) 
					textBoard[i][j] = 'D';
				else if(objBoard[i][j].equals(ObstacleType.ShortGrass)) 
					textBoard[i][j] = 'g';
				else if(objBoard[i][j] instanceof Item)
					textBoard[i][j] = 'I';
				Point pt = theGame.getTrainerPos();
				//System.out.println(pt.x + " " + pt.y);
				textBoard[pt.x][pt.y] = 'X';
			}
		}
	}
	
	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				result += "[ " + textBoard[i][j] + " ]";
			}
			result += "\n\n";
		}
		return result;
	}

	@Override
	public void update(Observable o, Object arg) {
		updateTextBoard();
		textArea.setText(toString());
	}

}
