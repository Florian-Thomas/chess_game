package pieces;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import game.Engine;

/**
 * Knight piece.
 */
public class Knight extends Pieces {
	
	// Images
	private ImageIcon iconWhite = new ImageIcon(this.getClass().getResource("white_knight.png"));
	private Image whitePiece = iconWhite.getImage();
	private ImageIcon iconBlack = new ImageIcon(this.getClass().getResource("black_knight.png"));
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
	public Knight(int player, int i, int j, Engine g) {
		super(player, i, j, g);
		this.value = 3;
	}
	
	/**
	 * Possible moves
	 */
	public ArrayList <int[]> canGo() {
		ArrayList<int[]> r = new ArrayList<int[]>();
		ArrayList<int[]> r1 = new ArrayList<int[]>();
		r.add(new int[] {i+2,j+1});
		r.add(new int[] {i+1,j+2});
		r.add(new int[] {i-1,j+2});
		r.add(new int[] {i-2,j+1});
		r.add(new int[] {i-2,j-1});
		r.add(new int[] {i-1,j-2});
		r.add(new int[] {i+1,j-2});
		r.add(new int[] {i+2,j-1});
		
		for(int k = 0;k<r.size();k++) {
			
			if (r.get(k)[0]>=0 && r.get(k)[0]<=7 && r.get(k)[1]<=7 && r.get(k)[1]>=0 && ((engine.getBoard()[r.get(k)[0]][r.get(k)[1]]!=null && engine.getBoard()[r.get(k)[0]][r.get(k)[1]].getPlayer()!=this.getPlayer()) | engine.getBoard()[r.get(k)[0]][r.get(k)[1]]==null)) {
				r1.add(r.get(k));
			}
		}
		return r1;
	}
	
	/**
	 * Possible targets.
	 */
	public ArrayList<int[]> canEat() {
		
		ArrayList<int[]> r = new ArrayList<int[]>();
		ArrayList<int[]> r1 = new ArrayList<int[]>();
		r.add(new int[] {i+2,j+1});
		r.add(new int[] {i+1,j+2});
		r.add(new int[] {i-1,j+2});
		r.add(new int[] {i-2,j+1});
		r.add(new int[] {i-2,j-1});
		r.add(new int[] {i-1,j-2});
		r.add(new int[] {i+1,j-2});
		r.add(new int[] {i+2,j-1});
		
		for(int k = 0;k<r.size();k++) {
			if (r.get(k)[0]>=0 && r.get(k)[0]<=7 && r.get(k)[1]<=7 && r.get(k)[1]>=0 && ((engine.getBoard()[r.get(k)[0]][r.get(k)[1]]!=null && engine.getBoard()[r.get(k)[0]][r.get(k)[1]].getPlayer()!=this.getPlayer()))) {
				r1.add(r.get(k));
			}
		}
		return r1;
	}
}
