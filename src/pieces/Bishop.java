package pieces;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import game.Engine;

/**
 * Bishop piece.
 */
public class Bishop extends Pieces{

	// Images
	private ImageIcon iconWhite = new ImageIcon(this.getClass().getResource("white_bishop.png"));
	private Image whitePiece = iconWhite.getImage();
	private ImageIcon iconBlack = new ImageIcon(this.getClass().getResource("black_bishop.png"));
	private Image blackPiece = iconBlack.getImage();

	/**
	 * Return the image of the piece.
	 * @return Image of the piece.
	 */
	public Image getImage() {
		if (this.getPlayer()==1) {
			return blackPiece;
		}
		else {
			return whitePiece;
		}
	}
	/**
	 * Constructor
	 * @param player	Player controlling the piece
	 * @param i			First coordinate of the piece
	 * @param j			Second coordinate of the piece
	 * @param g			Game board
	 */	
	public Bishop(int player, int i, int j, Engine g) {

		super(player, i, j, g);
		this.value = 3;
	}
	
	
	/**
	 * Possible moves
	 */
	public ArrayList <int[]> canGo() {
		ArrayList<int[]> r = new ArrayList<int[]>();
		int k = 1;
		boolean t = true;
		while(t) {
			if(i+k>=0 && i+k<=7 && j+k<=7 && j+k>=0) {
				if (engine.getBoard()[this.i+k][this.j+k]==null) {
					r.add(new int[] {i+k,j+k});
					k++;

				}
				else if (engine.getBoard()[this.i+k][this.j+k].getPlayer()!=this.getPlayer()) {
					r.add(new int[] {i+k,j+k});
					t=false;

				}
				else {
					t=false;
				}
			}
			else {
				t=false;
			}
		}
		
		k = 1;
		t = true;
		while(t) {
			if(i-k>=0 && i-k<=7 && j+k<=7 && j+k>=0) {
				if (engine.getBoard()[this.i-k][this.j+k]==null) {
					r.add(new int[] {i-k,j+k});
					k++;

				}
				else if (engine.getBoard()[this.i-k][this.j+k].getPlayer()!=this.getPlayer()) {
					r.add(new int[] {i-k,j+k});
					t=false;

				}
				else {
					t=false;
				}
			}
			else {
				t=false;
			}
		}
		
		k = 1;
		t = true;
		while(t) {
			if(i+k>=0 && i+k<=7 && j-k<=7 && j-k>=0) {
				if (engine.getBoard()[this.i+k][this.j-k]==null) {
					r.add(new int[] {i+k,j-k});
					k++;

				}
				else if (engine.getBoard()[this.i+k][this.j-k].getPlayer()!=this.getPlayer()) {
					r.add(new int[] {i+k,j-k});
					t=false;

				}
				else {
					t=false;
				}
			}
			else {
				t=false;
			}
		}
		
		k = 1;
		t = true;
		while(t) {
			if(i-k>=0 && i-k<=7 && j-k<=7 && j-k>=0) {
				if (engine.getBoard()[this.i-k][this.j-k]==null) {
					r.add(new int[] {i-k,j-k});
					k++;

				}
				else if (engine.getBoard()[this.i-k][this.j-k].getPlayer()!=this.getPlayer()) {
					r.add(new int[] {i-k,j-k});
					t=false;

				}
				else {
					t=false;
				}
			}
			else {
				t=false;
			}
		}
		return r;
	}
	
	/**
	 * Possible targets.
	 */
	public ArrayList<int[]> canEat() {
		
		ArrayList<int[]> r = new ArrayList<int[]>();
		int k = 1;
		boolean t = true;
		while(t) {
			if(i+k>=0 && i+k<=7 && j+k<=7 && j+k>=0) {
				if (engine.getBoard()[this.i+k][this.j+k]==null) {
					k++;

				}
				else if (engine.getBoard()[this.i+k][this.j+k].getPlayer()!=this.getPlayer()) {
					r.add(new int[] {i+k,j+k});
					t=false;

				}
				else {
					t=false;
				}
			}
			else {
				t=false;
			}
		}
		
		k = 1;
		t = true;
		while(t) {
			if(i-k>=0 && i-k<=7 && j+k<=7 && j+k>=0) {
				if (engine.getBoard()[this.i-k][this.j+k]==null) {
					k++;

				}
				else if (engine.getBoard()[this.i-k][this.j+k].getPlayer()!=this.getPlayer()) {
					r.add(new int[] {i-k,j+k});
					t=false;

				}
				else {
					t=false;
				}
			}
			else {
				t=false;
			}
		}
		
		k = 1;
		t = true;
		while(t) {
			if(i+k>=0 && i+k<=7 && j-k<=7 && j-k>=0) {
				if (engine.getBoard()[this.i+k][this.j-k]==null) {
					k++;

				}
				else if (engine.getBoard()[this.i+k][this.j-k].getPlayer()!=this.getPlayer()) {
					r.add(new int[] {i+k,j-k});
					t=false;

				}
				else {
					t=false;
				}
			}
			else {
				t=false;
			}
		}
		
		k = 1;
		t = true;
		while(t) {
			if(i-k>=0 && i-k<=7 && j-k<=7 && j-k>=0) {
				if (engine.getBoard()[this.i-k][this.j-k]==null) {
					k++;

				}
				else if (engine.getBoard()[this.i-k][this.j-k].getPlayer()!=this.getPlayer()) {
					r.add(new int[] {i-k,j-k});
					t=false;

				}
				else {
					t=false;
				}
			}
			else {
				t=false;
			}
		}

		return r;
	
	}

}
