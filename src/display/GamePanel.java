package display;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import game.Engine;

// Main panel
public class GamePanel extends JPanel{
	private Engine engine;
	public GamePanel(Engine engine) {
		this.engine = engine;
	}

	// Function that needs to be called after any change in the game
	public void paint(Graphics g) {
		// Game is over
		if(engine.isOver()) {
			//Draw the board
			for(int i = 0; i<8;i++) {
				for(int j=0;j<8;j++) {
					if ((i+j)%2 !=0) {
						g.setColor(new Color(0xB35900));
						g.fillRect(j*100, i*100, 100, 100);
					}
					else {
						g.setColor(new Color(0xffe6cc));
						g.fillRect(j*100, i*100, 100, 100);
					}

					if (engine.getBoard()[i][j]!=null) {
						g.drawImage(engine.getBoard()[i][j].getImage(), j*100+10,i*100, 80 ,95 ,null); //Add 10 to center
					}
				}
			}
			g.setColor(Color.red);
			g.setFont(g.getFont().deriveFont(Font.BOLD, 125.0f));
			// Print winner
			if(engine.getWinner()==2) {
				g.setColor(Color.white);
				g.fillRect(15, 325, 775, 100);
				g.setColor(Color.black);
				g.setFont(g.getFont().deriveFont(Font.BOLD, 65.0f));
				g.drawString("White player won", 20, 400);
			}
			else if(engine.getWinner()==1) {
				g.setFont(g.getFont().deriveFont(Font.BOLD, 65.0f));
				g.setColor(Color.white);
				g.fillRect(15, 325, 775, 100);
				g.setColor(Color.black);
				g.drawString("Black player won", 30, 400);
			}
		}
		// The game is not over
		else {
			// Draw the board
			for(int i = 0; i<8;i++) {
				for(int j=0;j<8;j++) {
					if ((i+j)%2 !=0) {
						g.setColor(new Color(0xB35900));
						g.fillRect(j*100, i*100, 100, 100);
					}
					else {
						g.setColor(new Color(0xffe6cc));
						g.fillRect(j*100, i*100, 100, 100);
					}

					if (engine.getBoard()[i][j]!=null) {
						g.drawImage(engine.getBoard()[i][j].getImage(), j*100+20,i*100, 60 ,95 ,null); // Add 20 to center
					}
				}
			}

			// Check if a piece is selected
			if (engine.isSelected()) {
				// Draw the possible moves with the selected piece
				for (int k=0;k<engine.getBoard()[engine.getSelectedCoord()[0]][engine.getSelectedCoord()[1]].canGo().size();k++) {
					g.setColor(Color.RED);
					g.fillOval(engine.getBoard()[engine.getSelectedCoord()[0]][engine.getSelectedCoord()[1]].canGo().get(k)[1]*100+35, engine.getBoard()[engine.getSelectedCoord()[0]][engine.getSelectedCoord()[1]].canGo().get(k)[0]*100+35, 25, 25);
				}
			}
		}
	}
}
