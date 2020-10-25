package pieces;
import java.awt.Image;
import java.util.ArrayList;

import game.Engine;

// Abstract class that needs to be implemented for all the different pieces in the game
public abstract class Pieces {
	// The piece belongs to a player
	private int player;
	// A piece has coordinates
	protected int i; // line
	protected int j; // column
	protected int value; // value of the piece
	Engine engine; // The piece can access the board
	boolean hasMove; // Boolean set to true if the piece has moved since the beginning of the game
	
	
	// Getters and setters
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	public boolean hasMove() {
		return hasMove;
	}
	public void setHasMove(boolean hasMove) {
		this.hasMove=hasMove;
	}

	public int getPlayer() {
		return player;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	// Constructor
	public Pieces(int player, int i, int j, Engine g) {
		this.player = player;
		this.i = i;
		this.j = j;
		this.engine = g;
		this.hasMove=false;
	}
	
	public void promote() {}
	public abstract Image getImage();
	// Returns the possible next locations of the piece
	public abstract ArrayList<int[]> canGo();
	// Returns the possible pieces that can be eaten by this piece
	public abstract ArrayList<int[]> canEat();
	

}
