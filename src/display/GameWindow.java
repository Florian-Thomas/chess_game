package display;
import java.awt.Dimension;

import javax.swing.JFrame;

import game.Engine;

/**
 * Main window of the game.
 */
public class GameWindow extends JFrame{
	private GamePanel gamePanel;
	private MouseHandler mouseHandler;
	private Engine engine;
	
	/**
	 * Constructor of the main window.
	 * @param engine	Engine containing the game
	 * @param mode		Game mode
	 * @param player	Player's turn
	 * @param time		Time for the AI
	 */
	public GameWindow(Engine engine, String mode, int player, int time) {
		this.gamePanel = new GamePanel(engine);
		this.mouseHandler = new MouseHandler(engine, this, mode, player, time);
		this.engine = engine;
		this.setPreferredSize(new Dimension(830,860));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addMouseListener(mouseHandler);
		this.add(gamePanel);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);

	}
}
