package pieces;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import game.Engine;

public class Queen extends Pieces{
	
	// Images
	private ImageIcon iconWhite = new ImageIcon(this.getClass().getResource("white_queen.png"));
	private Image whitePiece = iconWhite.getImage();
	private ImageIcon iconBlack = new ImageIcon(this.getClass().getResource("black_queen.png"));
	private Image blackPiece = iconBlack.getImage();
	
	// Getters and Setters
	public Image getImage() {
		if (this.getPlayer()==1) {
			return blackPiece;
		}
		else {
			return whitePiece;
		}
	}

	// Constructors
	public Queen(int player, int i, int j, Engine g) {
		super(player, i, j, g);
		this.value = 9;
	}

	// Possible moves
	public ArrayList<int []> canGo() {
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
		k = 1;
		t = true;
		while(t) {
			if(i+k<=7) {
				if (engine.getBoard()[this.i+k][this.j]==null) {
					r.add(new int[] {i+k,j});
					k++;

				}
				else if (engine.getBoard()[this.i+k][this.j].getPlayer()!=this.getPlayer()) {
					r.add(new int[] {i+k,j});
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
			if(i-k>=0) {
				if (engine.getBoard()[this.i-k][this.j]==null) {
					r.add(new int[] {i-k,j});
					k++;

				}
				else if (engine.getBoard()[this.i-k][this.j].getPlayer()!=this.getPlayer()) {
					r.add(new int[] {i-k,j});
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
			if(j+k<=7) {
				if (engine.getBoard()[this.i][this.j+k]==null) {
					r.add(new int[] {i,j+k});
					k++;

				}
				else if (engine.getBoard()[this.i][this.j+k].getPlayer()!=this.getPlayer()) {
					r.add(new int[] {i,j+k});
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
			if(j-k>=0) {
				if (engine.getBoard()[this.i][this.j-k]==null) {
					r.add(new int[] {i,j-k});
					k++;

				}
				else if (engine.getBoard()[this.i][this.j-k].getPlayer()!=this.getPlayer()) {
					r.add(new int[] {i,j-k});
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

	// Possible targets
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
		k = 1;
		t = true;
		while(t) {
			if(i+k<=7) {
				if (engine.getBoard()[this.i+k][this.j]==null) {
					k++;

				}
				else if (engine.getBoard()[this.i+k][this.j].getPlayer()!=this.getPlayer()) {
					r.add(new int[] {i+k,j});
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
			if(i-k>=0) {
				if (engine.getBoard()[this.i-k][this.j]==null) {
					k++;

				}
				else if (engine.getBoard()[this.i-k][this.j].getPlayer()!=this.getPlayer()) {
					r.add(new int[] {i-k,j});
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
			if(j+k<=7) {
				if (engine.getBoard()[this.i][this.j+k]==null) {
					k++;

				}
				else if (engine.getBoard()[this.i][this.j+k].getPlayer()!=this.getPlayer()) {
					r.add(new int[] {i,j+k});
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
			if(j-k>=0) {
				if (engine.getBoard()[this.i][this.j-k]==null) {
					k++;

				}
				else if (engine.getBoard()[this.i][this.j-k].getPlayer()!=this.getPlayer()) {
					r.add(new int[] {i,j-k});
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
