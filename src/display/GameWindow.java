package display;
import java.awt.Dimension;

import javax.swing.JFrame;

import game.Engine;

public class GameWindow extends JFrame{
	private GamePanel gamePanel;
	private MouseHandler mouseHandler;
	private Engine engine;
	
	// Main window
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
