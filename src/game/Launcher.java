package game;


import javax.swing.JOptionPane;

import AI.MCTS;
import display.GameWindow;

/**
 * Launch the game
 */
public class Launcher {

	public static void main(String[] args) {
		int time=0;
		int time1=0;
		int time2=0;
		int player = 0;
		String[] choice = {"Player vs Player", "Player vs AI", "AI vs AI"}; // Game modes
		JOptionPane jop = new JOptionPane();
		String mode = (String)jop.showInputDialog(null, "Select game mode", "Game mode", JOptionPane.QUESTION_MESSAGE, null, choice, choice[2]);
		if (mode.equals("Player vs AI")) {
			JOptionPane jop1 = new JOptionPane();
		    time = Integer.parseInt(jop1.showInputDialog(null, "Time given to the AI? (second)", "Time", JOptionPane.QUESTION_MESSAGE));
		    JOptionPane jop4 = new JOptionPane();
		    String[] starterChoice = {"yes", "no"};
		    String starter = (String)jop4.showInputDialog(null, "Do you want to start?", "First player", JOptionPane.QUESTION_MESSAGE, null, starterChoice, starterChoice[0]);
		    if (starter.equals("yes")) {
		    	player = 2;
		    }
		    else {
		    	player = 1;
		    }

		}
		if (mode.equals("AI vs AI")) {
			JOptionPane jop2 = new JOptionPane();
		    time1 = Integer.parseInt(jop2.showInputDialog(null, "Time given to black player? (seconds)", "Black player duration", JOptionPane.QUESTION_MESSAGE));
		    JOptionPane jop3 = new JOptionPane();
		    time2 = Integer.parseInt(jop3.showInputDialog(null, "Time given to white player? (seconds)", "White player duration", JOptionPane.QUESTION_MESSAGE));

		}
		// Create the game
		Engine g = new Engine();
		GameWindow t = new GameWindow(g, mode, player, time);
		
		// If the game mode is AI vs AI, play the game. In the other case, the game is controlled in the MouseHandler class 
		if(mode.equals("AI vs AI")){
				while(!g.isOver()) {
					// Plays the turn
					if(g.getTurn() == 2) {
						int [][] mcts = MCTS.infer(MCTS.canDo(g, g.getTurn()), time2 , g, g.getTurn(), 2);
						g.move(mcts[0][0], mcts[0][1], mcts[1][0], mcts[1][1]);
						g.moves.get(g.getTurn()-1).add(new int[][] {{ mcts[1][0], mcts[1][1]},{mcts[0][0], mcts[0][1]}});
						g.endTurn();
						t.repaint();
					}
					else {
						int [][] mcts=MCTS.infer(MCTS.canDo(g, g.getTurn()), time1 , g, g.getTurn(), 2);
						g.move(mcts[0][0], mcts[0][1], mcts[1][0], mcts[1][1]);
						g.moves.get(g.getTurn()-1).add(new int[][] {{ mcts[1][0], mcts[1][1]},{mcts[0][0], mcts[0][1]}});
						g.endTurn();
						t.repaint();
					}
				}

	}
}

}
