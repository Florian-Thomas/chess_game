package display;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Timer;

import AI.MCTS;
import game.Engine;

// Mouse Listener
public class MouseHandler implements MouseListener, ActionListener{
	private Engine engine;
	private GameWindow gameWindow;
	private String mode;
	private int player;
	private int time;
	private Timer timer;


	// Constructor
	public MouseHandler(Engine engine, GameWindow gameWindow, String mode, int player, int time) {
		this.engine = engine;
		this.gameWindow = gameWindow;
		this.mode=mode;
		this.player=player;
		this.time=time;
		// The timer is used to send an event every 0.1s to check if it is time for the AI to play
		this.timer = new Timer(100, this);
		this.timer.start();
	}

	// On click event
	@Override
	public void mouseReleased(MouseEvent e) {
		// Mouse should not work in AI vs AI mode
		if (!mode.equals("AI vs AI")) {
			// PvP mode
			if(mode.equals("Player vs Player")) {
				// Player 2 turn 
				if(engine.getTurn()==2) {
					// Get mouse position
					int j = (int)((e.getX()-9)/100);
					int i = (int)((e.getY()-38)/100);
					// Click on piece and no piece selected
					if (engine.isSelected()==false && e.getButton()==1 && engine.getBoard()[i][j]!=null && engine.getBoard()[i][j].canGo().isEmpty()==false && engine.getTurn()==engine.getBoard()[i][j].getPlayer()) {
						engine.setSelected(true);
						engine.setSelectedCoord(new int[] {i,j});
						gameWindow.repaint();
					}
					// Release selected piece
					else if (engine.isSelected() == true && e.getButton()==3){
						engine.setSelected(false);
						engine.setSelectedCoord(null);
						gameWindow.repaint();
					}
					// Move selected piece if allowed
					else if(engine.isSelected()==true && e.getButton()==1 ) {
						int k = 0;
						while (engine.isSelected() && k<engine.getBoard()[engine.getSelectedCoord()[0]][engine.getSelectedCoord()[1]].canGo().size()) {
							int [] l=engine.getBoard()[engine.getSelectedCoord()[0]][engine.getSelectedCoord()[1]].canGo().get(k); 
							k++;
							if (l[0]==i && l[1]==j) {
								engine.move(engine.getSelectedCoord()[0], engine.getSelectedCoord()[1], i, j);
								engine.moves.get(engine.getTurn()-1).add(new int[][] {{i,j},{engine.getSelectedCoord()[0], engine.getSelectedCoord()[1]}});
								engine.endTurn();
								gameWindow.repaint();
							}
						}
					}
				}
				// Player 1 turn
				else {
					// Get mouse position
					int j = (int)((e.getX()-9)/100);
					int i = (int)((e.getY()-38)/100);
					// Click on piece and no piece selected
					if (engine.isSelected()==false && e.getButton()==1 && engine.getBoard()[i][j]!=null && engine.getBoard()[i][j].canGo().isEmpty()==false && engine.getTurn()==engine.getBoard()[i][j].getPlayer()) {
						engine.setSelected(true);
						engine.setSelectedCoord(new int[] {i,j});
						gameWindow.repaint();
					}
					// Release selected piece
					else if (engine.isSelected()==true && e.getButton()==3){
						engine.setSelected(false);
						engine.setSelectedCoord(null);

						gameWindow.repaint();
					}
					// Move selected piece if allowed
					else if(engine.isSelected()==true && e.getButton()==1 ) {
						int k = 0;
						while (engine.isSelected() && k<engine.getBoard()[engine.getSelectedCoord()[0]][engine.getSelectedCoord()[1]].canGo().size()) {
							int [] l=engine.getBoard()[engine.getSelectedCoord()[0]][engine.getSelectedCoord()[1]].canGo().get(k); 
							k++;
							if (l[0]==i && l[1]==j) {
								engine.move(engine.getSelectedCoord()[0], engine.getSelectedCoord()[1], i, j);
								engine.moves.get(engine.getTurn()-1).add(new int[][] {{i,j},{engine.getSelectedCoord()[0], engine.getSelectedCoord()[1]}});
								engine.endTurn();
								gameWindow.repaint();
							}
						}
					}
				}
			}
			// Player vs AI mode
			else if (mode.equals("Player vs AI")) {
				// Player 2 turn
				if(engine.getTurn()==2) {
					// Player 2 is human
					if (player==2) {
						// Get mouse position
						int j = (int)((e.getX()-9)/100);
						int i = (int)((e.getY()-38)/100);
						// Click on piece and no piece selected
						if (engine.isSelected()==false && e.getButton()==1 && engine.getBoard()[i][j]!=null && engine.getBoard()[i][j].canGo().isEmpty()==false && engine.getTurn()==engine.getBoard()[i][j].getPlayer()) {
							engine.setSelected(true);
							engine.setSelectedCoord(new int[] {i,j});
							gameWindow.repaint();
						}
						// Release selected piece
						else if (engine.isSelected()==true && e.getButton()==3){
							engine.setSelected(false);
							engine.setSelectedCoord(null);
							gameWindow.repaint();
						}
						// Move selected piece if allowed
						else if(engine.isSelected()==true && e.getButton()==1 ) {
							int k = 0;
							while (engine.isSelected() && k<engine.getBoard()[engine.getSelectedCoord()[0]][engine.getSelectedCoord()[1]].canGo().size()) {
								int [] l=engine.getBoard()[engine.getSelectedCoord()[0]][engine.getSelectedCoord()[1]].canGo().get(k); 
								k++;
								if (l[0]==i && l[1]==j) {
									engine.move(engine.getSelectedCoord()[0], engine.getSelectedCoord()[1], i, j); 
									engine.moves.get(engine.getTurn()-1).add(new int[][] {{i,j},{engine.getSelectedCoord()[0], engine.getSelectedCoord()[1]}});
									engine.endTurn();
									gameWindow.repaint();
									
								}
							}
						}
					}
				}
				// Player 1 turn
				else {
					// Player 1 is human
					if (player==1) {
						// Get mouse position
						int j = (int)((e.getX()-9)/100);
						int i = (int)((e.getY()-38)/100);

						// Click on piece and no piece selected
						if (engine.isSelected()==false && e.getButton()==1 && engine.getBoard()[i][j]!=null && engine.getBoard()[i][j].canGo().isEmpty()==false && engine.getTurn()==engine.getBoard()[i][j].getPlayer()) {
							engine.setSelected(true);
							engine.setSelectedCoord(new int[] {i,j});
							gameWindow.repaint();
						}
						// Release selected piece
						else if (engine.isSelected()==true && e.getButton()==3){
							engine.setSelected(false);
							engine.setSelectedCoord(null);
							gameWindow.repaint();
						}
						// Move selected piece if allowed
						else if(engine.isSelected()==true && e.getButton()==1 ) {
							int k = 0;
							while (engine.isSelected() && k<engine.getBoard()[engine.getSelectedCoord()[0]][engine.getSelectedCoord()[1]].canGo().size()) {
								int [] l=engine.getBoard()[engine.getSelectedCoord()[0]][engine.getSelectedCoord()[1]].canGo().get(k); 
								k++;
								if (l[0]==i && l[1]==j) {
									engine.move(engine.getSelectedCoord()[0], engine.getSelectedCoord()[1], i, j);
									engine.moves.get(engine.getTurn()-1).add(new int[][] {{i,j},{engine.getSelectedCoord()[0], engine.getSelectedCoord()[1]}});
									engine.endTurn();
									gameWindow.repaint();
									
								}
							}
						}
					}
				}
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}
	// Play the AI turn, triggered every 0.1s to repaint correctly the panel
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==timer){
			if (mode.equals("Player vs AI")) {
				if (engine.getTurn()==2 && player==1) {
					int [][] mcts=MCTS.infer(MCTS.canDo(engine, engine.getTurn()), time , engine, engine.getTurn(), 2);
					engine.move(mcts[0][0], mcts[0][1], mcts[1][0], mcts[1][1]);
					engine.moves.get(engine.getTurn()-1).add(new int[][] {{ mcts[1][0], mcts[1][1]},{mcts[0][0], mcts[0][1]}});
					engine.endTurn();
					gameWindow.repaint();
				}
				else if (engine.getTurn()==1 && player==2) {
					int [][] mcts=MCTS.infer(MCTS.canDo(engine, engine.getTurn()), time , engine, engine.getTurn(), 2);
					engine.move(mcts[0][0], mcts[0][1], mcts[1][0], mcts[1][1]);
					engine.moves.get(engine.getTurn()-1).add(new int[][] {{ mcts[1][0], mcts[1][1]},{mcts[0][0], mcts[0][1]}});
					engine.endTurn();
					gameWindow.repaint();
				}
				
			}
		}

	}

}
