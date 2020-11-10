package pieces;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import game.Engine;

/**
 * Pawn piece.
 */
public class Pawn extends Pieces{

	// Images
	private ImageIcon iconWhite = new ImageIcon(this.getClass().getResource("white_pawn.png"));
	private Image pieceBlanche = iconWhite.getImage();
	private ImageIcon iconBlack= new ImageIcon(this.getClass().getResource("black_pawn.png"));
	private Image pieceNoire = iconBlack.getImage();

	/**
	 * Return the image of the piece.
	 * @return Image of the piece.
	 */
	public Image getImage() {
		if (this.getPlayer()==1) {
			return pieceNoire;
		}
		else {
			return pieceBlanche;
		}
	}
	
	/**
	 * Constructor
	 * @param player	Player controlling the piece
	 * @param i			First coordinate of the piece
	 * @param j			Second coordinate of the piece
	 * @param g			Game board
	 */	
	public Pawn(int player, int i, int j, Engine g) {
		super(player, i, j, g);
		this.value = 1;
	}

	/**
	 * Possible moves
	 */
	public ArrayList <int[]> canGo() {
		ArrayList<int[]> r = new ArrayList<int[]>();
		
		
		if (this.getPlayer()==1 && this.i+1<=7) {
			
			if (engine.getBoard()[this.i+1][this.j]==null) {
				r.add(new int[] {this.i+1,this.j});
				
			}
			if (this.j+1<=7 && engine.getBoard()[this.i+1][this.j+1]!=null && engine.getBoard()[this.i+1][this.j+1].getPlayer()!=this.getPlayer() && this.j+1<=7) {
				r.add(new int[] {this.i+1,this.j+1});
				
			}
			if (this.j-1>=0 && engine.getBoard()[this.i+1][this.j-1]!=null && engine.getBoard()[this.i+1][this.j-1].getPlayer()!=this.getPlayer()&& this.j-1>=0) {
				r.add(new int[] {this.i+1,this.j-1});
				
			}

		}
		
		else if (this.getPlayer()==2 && this.i-1>=0) {			
			if (engine.getBoard()[this.i-1][this.j]==null) {
				r.add(new int[] {this.i-1,this.j});				
			}
			if (this.j+1<=7 && engine.getBoard()[this.i-1][this.j+1]!=null && engine.getBoard()[this.i-1][this.j+1].getPlayer()!=this.getPlayer() && this.j+1<=7) {
				r.add(new int[] {this.i-1,this.j+1});				
			}
			if (this.j-1>=0 && engine.getBoard()[this.i-1][this.j-1]!=null && engine.getBoard()[this.i-1][this.j-1].getPlayer()!=this.getPlayer()&& this.j-1>=0) {
				r.add(new int[] {this.i-1,this.j-1});		
			}

		}
		if (this.getPlayer()==1 && this.i+2<=7 && this.hasMove()==false && engine.getBoard()[this.i+1][this.j]==null) {
			if (engine.getBoard()[this.i+2][this.j]==null) {
				r.add(new int[] {this.i+2,this.j});			}
		}
		if (this.getPlayer()==2 && this.i-2>=0 && this.hasMove()==false && engine.getBoard()[this.i-1][this.j]==null) {
			if (engine.getBoard()[this.i-2][this.j]==null) {
				r.add(new int[] {this.i-2,this.j});
			}
		}
		
		if (this.getPlayer() == 1 && engine.enPassant().get(0).size()>0 && this.getI()==4 && ((this.getJ()-1>=0 && engine.getBoard()[this.getI()][this.getJ()-1]!=null && engine.getBoard()[this.getI()][this.getJ()-1].getPlayer()==2) || (this.getJ()+1<8 && engine.getBoard()[this.getI()][this.getJ()+1]!=null && engine.getBoard()[this.getI()][this.getJ()+1].getPlayer()==2))){
			for (int i=0; i<engine.enPassant().get(0).size();i++){
				r.add(engine.enPassant().get(0).get(i));
			}	
		}
		if (this.getPlayer() == 2 && engine.enPassant().get(1).size()>0 && this.getI()==3 && ((this.getJ()-1>=0 && engine.getBoard()[this.getI()][this.getJ()-1]!=null && engine.getBoard()[this.getI()][this.getJ()-1].getPlayer()==1) || (this.getJ()+1<8 && engine.getBoard()[this.getI()][this.getJ()+1]!=null && engine.getBoard()[this.getI()][this.getJ()+1].getPlayer()==1))){
			for (int i=0; i<engine.enPassant().get(1).size();i++){
				r.add(engine.enPassant().get(1).get(i));
			}
		}
		return r;
	}
	
	/**
	 * Promote the pawn to queen when reached the end of the board.
	 */
	public void promote() {
		if (this.i==7 && this.getPlayer()==1) {
			engine.changePiece(new int[] {this.i,this.j}, new Queen(1, i, j, engine));
		}
		if (this.i==0 && this.getPlayer()==2) {
			engine.changePiece(new int[] {this.i,this.j}, new Queen(2, i, j, engine));
		}
	}
	/**
	 * Possible targets.
	 */
	public ArrayList<int[]> canEat() {
		ArrayList<int[]> r = new ArrayList<int[]>();

		if (this.getPlayer()==1 && this.i+1<=7) {
			if (this.j+1<=7 && engine.getBoard()[this.i+1][this.j+1]!=null && engine.getBoard()[this.i+1][this.j+1].getPlayer()!=this.getPlayer()) {
				r.add(new int[] {this.i+1,this.j+1});
			}
			if (this.j-1>=0 && engine.getBoard()[this.i+1][this.j-1]!=null && engine.getBoard()[this.i+1][this.j-1].getPlayer()!=this.getPlayer()) {
				r.add(new int[] {this.i+1,this.j-1});
			}

		}
		else if (this.getPlayer()==2 && this.i+1<=7 && this.j+1<=7 && this.j-1>=0 && this.i-1>=0) {
			if (this.j+1<=7 && engine.getBoard()[this.i-1][this.j+1]!=null && engine.getBoard()[this.i-1][this.j+1].getPlayer()!=this.getPlayer()) {
				r.add(new int[] {this.i-1,this.j+1});
			}
			if (this.j-1>=0 && engine.getBoard()[this.i-1][this.j-1]!=null && engine.getBoard()[this.i-1][this.j-1].getPlayer()!=this.getPlayer()) {
				r.add(new int[] {this.i-1,this.j-1});
			}


		}


		return r;

	}

}
