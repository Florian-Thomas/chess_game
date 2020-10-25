package pieces;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import game.Engine;

public class King extends Pieces{
	
	// Images
	private ImageIcon iconWhite = new ImageIcon(this.getClass().getResource("white_king.png"));
	private Image whitePiece = iconWhite.getImage();
	private ImageIcon iconBlack = new ImageIcon(this.getClass().getResource("black_king.png"));
	private Image blackPiece = iconBlack.getImage();
	
	// Getters and setters
	public Image getImage() {
		if (this.getPlayer()==1) {
			return blackPiece;
		}
		else {
			return whitePiece;
		}
	}

	// Constructor
	public King(int player, int i, int j, Engine g) {
		super(player, i, j, g);
		this.value = 25;
	}

	// Possible moves
	public ArrayList<int []> canGo() {
		ArrayList<int[]> r = new ArrayList<int[]>();
		if(i+1<=7) {
			if (j+1<=7 && (engine.getBoard()[this.i+1][this.j+1]==null || engine.getBoard()[this.i+1][this.j+1].getPlayer()!=this.getPlayer())){
				r.add(new int [] {i+1, j+1});
			}
			if (j-1>=0 && (engine.getBoard()[this.i+1][this.j-1]==null || engine.getBoard()[this.i+1][this.j-1].getPlayer()!=this.getPlayer())){
				r.add(new int [] {i+1, j-1});
			}
			if (engine.getBoard()[this.i+1][this.j]==null || engine.getBoard()[this.i+1][this.j].getPlayer()!=this.getPlayer()){
				r.add(new int[] {i+1, j});			}
		}
		if (i-1>=0){
			if (j+1<=7 && (engine.getBoard()[this.i-1][this.j+1]==null || engine.getBoard()[this.i-1][this.j+1].getPlayer()!=this.getPlayer())){
				r.add(new int[] {i-1,j+1});
			}
			if (j-1>=0 && (engine.getBoard()[this.i-1][this.j-1]==null || engine.getBoard()[this.i-1][this.j-1].getPlayer()!=this.getPlayer())){
				r.add(new int[] {i-1,j-1});
			}
			if (engine.getBoard()[this.i-1][this.j]==null || engine.getBoard()[this.i-1][this.j].getPlayer()!=this.getPlayer()){
				r.add(new int[] {i-1,j});
			}
		}
		if (j-1>=0 && (engine.getBoard()[this.i][this.j-1]==null || engine.getBoard()[this.i][this.j-1].getPlayer()!=this.getPlayer())){
			r.add(new int[] {i,j-1});
		}
		if (j+1<=7 && (engine.getBoard()[this.i][this.j+1]==null || engine.getBoard()[this.i][this.j+1].getPlayer()!=this.getPlayer())){
			r.add(new int[] {i,j+1});
		}
		if (engine.smallCastling().get(this.getPlayer()-1)){
			if (this.getPlayer()==1){
				r.add(new int[] {0,6});
			}
			else{
				r.add(new int[] {7,6});
			}
		}
		if (engine.bigCastling().get(this.getPlayer()-1)){
			if (this.getPlayer()==1){
				r.add(new int[] {0,2});
			}
			else{
				r.add(new int[] {7,2});
			}
		}
		return r;
	}

	// Possible targets
	public ArrayList<int[]> canEat() {
		ArrayList<int[]> r = new ArrayList<int[]>();
		if(i+1<=7) {
			if (j+1<=7 && engine.getBoard()[this.i+1][this.j+1]!=null && engine.getBoard()[this.i+1][this.j+1].getPlayer()!=this.getPlayer()){
				r.add(new int [] {i+1, j+1});
			}
			if (j-1>=0 && engine.getBoard()[this.i+1][this.j-1]!=null && engine.getBoard()[this.i+1][this.j-1].getPlayer()!=this.getPlayer()){
				r.add(new int [] {i+1, j-1});
			}
			if (engine.getBoard()[this.i+1][this.j]!=null && engine.getBoard()[this.i+1][this.j].getPlayer()!=this.getPlayer()){
				r.add(new int[] {i+1, j});			}
		}
		if (i-1>=0){
			if (j+1<=7 && engine.getBoard()[this.i-1][this.j+1]!=null && engine.getBoard()[this.i-1][this.j+1].getPlayer()!=this.getPlayer()){
				r.add(new int[] {i-1,j+1});
			}
			if (j-1>=0 && engine.getBoard()[this.i-1][this.j-1]!=null && engine.getBoard()[this.i-1][this.j-1].getPlayer()!=this.getPlayer()){
				r.add(new int[] {i-1,j-1});
			}
			if (engine.getBoard()[this.i-1][this.j]!=null && engine.getBoard()[this.i-1][this.j].getPlayer()!=this.getPlayer()){
				r.add(new int[] {i-1,j});
			}
		}
		if (j-1>=0 && engine.getBoard()[this.i][this.j-1]!=null && engine.getBoard()[this.i][this.j-1].getPlayer()!=this.getPlayer()){
			r.add(new int[] {i,j-1});
		}
		if (j+1<=7 && engine.getBoard()[this.i][this.j+1]!=null && engine.getBoard()[this.i][this.j+1].getPlayer()!=this.getPlayer()){
			r.add(new int[] {i,j+1});
		}
		return r;
	}

	

}