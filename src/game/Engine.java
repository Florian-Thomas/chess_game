package game;
import java.util.ArrayList;
import pieces.Knight;
import pieces.Bishop;
import pieces.Pieces;
import pieces.Pawn;
import pieces.Queen;
import pieces.King;
import pieces.Rook;


/**
 * Contains the game.
 */
public class Engine {
	private boolean isOver;
	private int winner=0;
	private Pieces board[][] = new Pieces[8][8]; // Contains the pieces
	private boolean isSelected; //To know if a piece is selected
	private int[] selectedCoord; // The coordinates of the selected piece
	ArrayList<Pieces> eaten = new ArrayList<Pieces>(); // Contains the eaten pieces
	private int turn; // Current player playing
	private boolean [] castling = new boolean [2]; // To know if a castling has been done
	public ArrayList <ArrayList <int [][]>> moves = new ArrayList <ArrayList <int [][]>>(); // To remember the moves


	// Getters and Setters
	public int[] getSelectedCoord() {
		return selectedCoord;
	}

	public void setSelectedCoord(int[] selectedCoord) {
		this.selectedCoord = selectedCoord;
	}
	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}
	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public Pieces[][] getBoard() {
		return board;
	}

	public void setBoard(Pieces[][] board) {
		this.board = board;
	}

	
	public ArrayList<ArrayList<int[][]>> getMoves() {
		return moves;
	}

	public void setMoves(ArrayList<ArrayList<int[][]>> moves) {
		this.moves = moves;
	}

	public boolean[] getCastling() {
		return castling;
	}

	public void setCastling(boolean[] castling) {
		this.castling = castling;
	}
	public boolean isOver() {
		return isOver;
	}

	public void setOver(boolean isOver) {
		this.isOver = isOver;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}
	
	// Constructors
	
	/**
	 *  Copy a board.
	 * 
	 * @param board			Board to copy
	 * @param castling		Boolean true if a castling has been done in the copied game
	 * @param isSelected	Boolean true if a piece is selected
	 * @param selectedCoord	Selected coord only used is isSelected
	 * @param moves			Previous moves in the game
	 * @param isOver 		Boolean true if the game is over
	 * @param turn			Player's turn (1 or 2)
	 */
	public Engine (Pieces board[][], boolean[] castling, boolean isSelected, int[] selectedCoord, ArrayList <ArrayList <int [][]>> moves, boolean isOver, int turn) {
		this.setOver(isOver);
		this.turn=turn;
		this.castling[0]=castling[0];
		this.castling[1]=castling[1];
		this.moves.add(new ArrayList<int[][]>());
		this.moves.add(new ArrayList<int[][]>());
		for (int i =0; i<moves.size(); i++) {
			for (int j =0; j<moves.get(i).size(); j++) {
				this.moves.get(i).add(new int[moves.get(i).get(j).length][moves.get(i).get(j)[0].length]);
				for (int k =0; k<moves.get(i).get(j).length; k++) {
					for (int l =0; l<moves.get(i).get(j)[k].length; l++) {
						this.moves.get(i).get(j)[k][l]=moves.get(i).get(j)[k][l];
					}
				}
			}
		}

		this.isSelected = isSelected;
		this.selectedCoord = selectedCoord;

		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board[0].length; j++) {
				if(board[i][j]!=null) {
					if(board[i][j].getClass().toString().equals("class pieces.Pawn")) {
						this.board[i][j]=new Pawn(board[i][j].getPlayer(), i, j, this);

					}
					else if(board[i][j].getClass().toString().equals("class pieces.Knight")) {
						this.board[i][j]=new Knight(board[i][j].getPlayer(), i, j, this);
					}
					else if(board[i][j].getClass().toString().equals("class pieces.Bishop")) {
						this.board[i][j]=new Bishop(board[i][j].getPlayer(), i, j, this);
					}
					else if(board[i][j].getClass().toString().equals("class pieces.Queen")) {
						this.board[i][j]=new Queen(board[i][j].getPlayer(), i, j, this);
					}
					else if(board[i][j].getClass().toString().equals("class pieces.King")) {
						this.board[i][j]=new King(board[i][j].getPlayer(), i, j, this);
					}
					else if(board[i][j].getClass().toString().equals("class pieces.Rook")) {
						this.board[i][j]=new Rook(board[i][j].getPlayer(), i, j, this);
					}
				}
				else {
					this.board[i][j]=null;
				}


			}
		}

	}
	
	/**
	 * Create a new game.
	 */
	public Engine () {	
		this.setOver(false);
		this.castling[0]=false;
		this.castling[1]=false;
		this.moves.add(new ArrayList<int[][]>());
		this.moves.add(new ArrayList<int[][]>());
		this.isSelected = false;
		this.selectedCoord = null;
		this.turn = 2;
		for(int j=0;j<8;j++) {
			board[1][j] = new Pawn(1,1,j,this);
			board[6][j] = new Pawn(2,6,j,this);

		}

		board[0][0] = new Rook(1,0,0,this);
		board[0][7] = new Rook(1,0,7,this);
		board[7][0] = new Rook(2,7,0,this);
		board[7][7] = new Rook(2,7,7,this);
		board[0][1] = new Knight(1,0,1,this);
		board[0][6] = new Knight(1,0,6,this);
		board[7][1] = new Knight(2,7,1,this);
		board[7][6] = new Knight(2,7,6,this);
		board[0][2] = new Bishop(1,0,2,this);
		board[0][5] = new Bishop(1,0,5,this);
		board[7][2] = new Bishop(2,7,2,this);
		board[7][5] = new Bishop(2,7,5,this);
		board[0][4] = new King(1,0,4,this);
		board[7][4] = new King(2,7,4,this);
		board[0][3] = new Queen(1,0,3,this);
		board[7][3] = new Queen(2,7,3,this);

		for(int i = 2; i<6;i++) {
			for(int j=0;j<8;j++) {
				board[i][j] = null;
			}
		}
	}

	/**
	 * Move a piece from i,j to i1, j1.
	 * 
	 * @param i		First coordinate of the piece to move
	 * @param j		Second coordinate of the piece to move
	 * @param i1	First coordinate of the cell to go
	 * @param j1	Second coordinate of the cell to go
	*/
	public void move(int i,int j,int i1,int j1) {
		this.board[i][j].setHasMove(true);
		
		if (this.board[i1][j1]==null) {
			if (this.getBoard()[i][j].getClass().toString().equals("class pieces.Pawn") && this.enPassant().get(0).size()>0 && this.getBoard()[i][j].getPlayer()==1 && in(this.enPassant().get(0), new int[] {i1,j1})) {
				
				this.board[i1-1][j1]=null; // Remove the white piece if en passant is done
			}
			else if (this.getBoard()[i][j].getClass().toString().equals("class pieces.Pawn") && this.enPassant().get(1).size()>0 && this.getBoard()[i][j].getPlayer()==2 && in(this.enPassant().get(1), new int[] {i1,j1})) {
				
				this.board[i1+1][j1]=null; // Same for the black one
			}
			else if(this.getBoard()[i][j].getClass().toString().equals("class pieces.King") && this.getBoard()[i][j].getPlayer()==1 && i==0 && j==4 && i1==0 && j1==2) {
				
				this.getBoard()[0][3]=this.getBoard()[0][0];
				this.getBoard()[0][3].setI(0);
				this.getBoard()[0][3].setJ(3);
				this.getBoard()[0][0]=null; // Big castling from black king
			}
			else if(this.getBoard()[i][j].getClass().toString().equals("class pieces.King") && this.getBoard()[i][j].getPlayer()==1 && i==0 && j==4 && i1==0 && j1==6) {
				
				this.getBoard()[0][5]=this.getBoard()[0][7];
				this.getBoard()[0][5].setI(0);
				this.getBoard()[0][5].setJ(5);
				this.getBoard()[0][7]=null; // Small castling from black king
			}
			else if(this.getBoard()[i][j].getClass().toString().equals("class pieces.King") && this.getBoard()[i][j].getPlayer()==2 && i==7 && j==4 && i1==7 && j1==2) {
				
				this.getBoard()[7][3]=this.getBoard()[7][0];
				this.getBoard()[7][3].setI(7);
				this.getBoard()[7][3].setJ(3);
				this.getBoard()[7][0]=null; // Big castling from white king
			}
			
			else if(this.getBoard()[i][j].getClass().toString().equals("class pieces.King") && this.getBoard()[i][j].getPlayer()==2 && i==7 && j==4 && i1==7 && j1==6) {
				
				this.getBoard()[7][5]=this.getBoard()[7][7];
				this.getBoard()[7][5].setI(7);
				this.getBoard()[7][5].setJ(5);
				this.getBoard()[7][7]=null; // Small castling from white king
			}

			
			this.getBoard()[i1][j1]=this.board[i][j];
			this.getBoard()[i][j]=null;
			this.getBoard()[i1][j1].setI(i1);
			this.getBoard()[i1][j1].setJ(j1);


		}
		else if (this.board[i1][j1]!=null && this.board[i1][j1].getPlayer()!=this.board[i][j].getPlayer()) {
			this.eaten.add(this.board[i1][j1]);
			this.getBoard()[i1][j1]=this.board[i][j];
			this.getBoard()[i][j]=null;
			this.getBoard()[i1][j1].setI(i1);
			this.getBoard()[i1][j1].setJ(j1);

		}
		if(this.getBoard()[i1][j1].getClass().toString().equals("class pieces.Pawn")) {
			this.getBoard()[i1][j1].promote();
		}
	}
	
	/**
	 * Used to simulate a move and then reverse it.
	 * 
	 * @param i		First coordinate of the piece to move
	 * @param j		Second coordinate of the piece to move
	 * @param i1	First coordinate of the cell to go
	 * @param j1	Second coordinate of the cell to go
	 * @return 		Any piece eaten during the move
	 */
	public Pieces moveAndBackLater(int i,int j,int i1,int j1) { 
		this.board[i][j].setHasMove(true);
		Pieces eaten = null;
		
		if (this.board[i1][j1]==null) {
			if (this.getBoard()[i][j].getClass().toString().equals("class pieces.Pawn") && this.enPassant().get(0).size()>0 && this.getBoard()[i][j].getPlayer()==1 && in(this.enPassant().get(0), new int[] {i1,j1})) {
				eaten=this.board[i1-1][j1];
				this.board[i1-1][j1]=null; // Remove the white piece if en passant is done
				
			}
			else if (this.getBoard()[i][j].getClass().toString().equals("class pieces.Pawn") && this.enPassant().get(1).size()>0 && this.getBoard()[i][j].getPlayer()==2 && in(this.enPassant().get(1), new int[] {i1,j1})) {
				eaten=this.board[i1+1][j1];
				this.board[i1+1][j1]=null; // Same for the black one
			}
			else if(this.getBoard()[i][j].getClass().toString().equals("class pieces.King") && this.getBoard()[i][j].getPlayer()==1 && i==0 && j==4 && i1==0 && j1==2) {
				
				this.getBoard()[0][3]=this.getBoard()[0][0];
				this.getBoard()[0][3].setI(0);
				this.getBoard()[0][3].setJ(3);
				this.getBoard()[0][0]=null; // Big castling from black king
			}
			else if(this.getBoard()[i][j].getClass().toString().equals("class pieces.King") && this.getBoard()[i][j].getPlayer()==1 && i==0 && j==4 && i1==0 && j1==6) {
				
				this.getBoard()[0][5]=this.getBoard()[0][7];
				this.getBoard()[0][5].setI(0);
				this.getBoard()[0][5].setJ(5);
				this.getBoard()[0][7]=null; // Small castling from black king
			}
			else if(this.getBoard()[i][j].getClass().toString().equals("class pieces.King") && this.getBoard()[i][j].getPlayer()==2 && i==7 && j==4 && i1==7 && j1==2) {
				
				this.getBoard()[7][3]=this.getBoard()[7][0];
				this.getBoard()[7][3].setI(7);
				this.getBoard()[7][3].setJ(3);
				this.getBoard()[7][0]=null; // Big castling from white king
			}
			
			else if(this.getBoard()[i][j].getClass().toString().equals("class pieces.King") && this.getBoard()[i][j].getPlayer()==2 && i==7 && j==4 && i1==7 && j1==6) {
				
				this.getBoard()[7][5]=this.getBoard()[7][7];
				this.getBoard()[7][5].setI(7);
				this.getBoard()[7][5].setJ(5);
				this.getBoard()[7][7]=null; // Small castling from white king
			}

			
			this.getBoard()[i1][j1]=this.board[i][j];
			this.getBoard()[i][j]=null;
			this.getBoard()[i1][j1].setI(i1);
			this.getBoard()[i1][j1].setJ(j1);


		}
		else if (this.board[i1][j1]!=null && this.board[i1][j1].getPlayer()!=this.board[i][j].getPlayer()) {
			this.eaten.add(this.board[i1][j1]);
			eaten=this.board[i1][j1];
			this.getBoard()[i1][j1]=this.board[i][j];
			this.getBoard()[i][j]=null;
			this.getBoard()[i1][j1].setI(i1);
			this.getBoard()[i1][j1].setJ(j1);

		}
		if(this.getBoard()[i1][j1].getClass().toString().equals("class pieces.Pawn")) {
			this.getBoard()[i1][j1].promote();
		}
		return eaten;
	}
	
	
	/**
	 * End the turn and check if the game is over or not 
	 */
	public void endTurn() { 
		if (this.king().size()<2) {
			this.isOver=true;
			this.setWinner(this.king().get(0)[0][0]);
		}
		if (this.getTurn()==1) {
			this.setTurn(2);
		}
		else {
			this.setTurn(1);
		}
		this.setSelected(false);

	}


	
	/**
	 * Check if coord is in the list t.
	 * 
	 * @param t		
	 * @param coord	
	 * @return			True if coord has been found
	 */
	public boolean in(ArrayList<int[]> t, int[] coord ) {
		for (int k = 0; k<t.size();k++) {
			if(t.get(k)[0]==coord[0] &&t.get(k)[1]==coord[1]) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Remove coord from the list t
	 * @param t			
	 * @param coord
	*/
	public void remove(ArrayList<int[]> t, int[] coord) {
		ArrayList<int[]> l =new ArrayList<int[]>();
		for (int k = 0; k<t.size();k++) {
			if (t.get(k)[0]!=coord[0] | t.get(k)[1]!=coord[1]) {
				l.add(t.get(k));
			}
		}
		t.clear();
		for (int k = 0;k<l.size();k++) {
			t.add(l.get(k));
		}
	}
	
	/**
	 * Change a piece at coord with p.
	 * @param coord
	 * @param p
	 */
	public void changePiece(int[] coord, Pieces p) {
		this.board[coord[0]][coord[1]]=p;
	}
	
	
	/**
	 * Retrieves the kings' coordinates
	 * @return 	The coordinates of the kings
	 */
	public ArrayList <int[][]> king(){

		ArrayList<int [][]> r = new ArrayList<int[][]>();
		for (int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if(this.board[i][j]!=null){
					
					if (this.board[i][j].getClass().toString().equals("class pieces.King")){
						
						r.add(new int[][] {{this.board[i][j].getPlayer()},{i,j}});
					}
				}
			}
		}		
		return r;
	}

	
	/**
	 * Check if a king is threatened.
	 * @param i			King's first coordinate
	 * @param j			King's second coordinate
	 * @param player	Player owning the king
	 * @return			True if the king is controlled
	 */
	public boolean pieceIsControlled(int i, int j, int player){
		ArrayList <int []> opponents = new ArrayList <>();
		int line = 0;
		int height = 0;
		while (line<=7){
			height=0;
			while (height<=7){
				if (this.board[line][height]!=null && this.board[line][height].getPlayer()!=player){
					ArrayList<int[]> r = this.board[line][height].canEat();
					for (int k=0; k<r.size(); k++){
						if (r.get(k)[0]==i && r.get(k)[1]==j){
							opponents.add(new int []{line,height});
						}
					}
				}
				height++;
			}
			line++;	
		}
		return !opponents.isEmpty();
	}

	public void setRoque(boolean roque, int p){
		this.castling[p-1] = roque;
	}

	/**
	 * Check if small castlings are possible.
	 * @return	ArrayList of 2 booleans for players 1 and 2 containing booleans set to true if a small castling is possible
	 */
	public ArrayList <Boolean> smallCastling(){
		ArrayList <Boolean> pr = new ArrayList <Boolean>();
		pr.add(false); // No castling done for player 1 (black)
		pr.add(false); // No castling done for player 2 (white)

		// Black pieces
		if (this.getBoard()[0][4]!=null && this.getBoard()[0][7]!=null && this.getBoard()[0][4].getClass().toString().equals("class pieces.King") && this.getBoard()[0][7].getClass().toString().equals("class pieces.Rook") && !this.getBoard()[0][4].hasMove() && !this.getBoard()[0][7].hasMove()){
			if (this.getBoard()[0][5]==null && this.getBoard()[0][6]==null){
				if (!(this.pieceIsControlled(0,4,1) || this.pieceIsControlled(0,5,1) || this.pieceIsControlled(0,6,1))){
					if (this.castling[0]==false){
						pr.set(0, true);
					}
				}
			}
		}			

		// White pieces
		if (this.getBoard()[7][4]!=null && this.getBoard()[7][7]!=null && this.getBoard()[7][4].getClass().toString().equals("class pieces.King") && this.getBoard()[7][7].getClass().toString().equals("class pieces.Rook") && !this.getBoard()[7][4].hasMove() && !this.getBoard()[7][7].hasMove()){
			if (this.getBoard()[7][5]==null && this.getBoard()[7][6]==null){
				if (!(this.pieceIsControlled(7,4,2) || this.pieceIsControlled(7,5,2) || this.pieceIsControlled(7,6,2))){
					if (this.castling[1]==false){
						pr.set(1, true);
					}
				}
			}
		}	
		return pr;
	}

	/**
	 * Check if big castlings are possible.
	 * @return	ArrayList of 2 booleans for players 1 and 2 containing booleans set to true if a big castling is possible
	 */
	public ArrayList <Boolean> bigCastling(){

		ArrayList <Boolean> gr = new ArrayList <Boolean>();
		gr.add(false); // No castling done for player 1 (black)
		gr.add(false); // No castling done for player 2 (white)

		// Black pieces
		if (this.getBoard()[0][4]!=null && this.getBoard()[0][0]!=null && this.getBoard()[0][4].getClass().toString().equals("class pieces.King") && this.getBoard()[0][0].getClass().toString().equals("class pieces.Rook") && !this.getBoard()[0][4].hasMove() && !this.getBoard()[0][0].hasMove()){
			if (this.getBoard()[0][3]==null && this.getBoard()[0][2]==null && this.getBoard()[0][1]==null){

				if (!(this.pieceIsControlled(0,4,1) || this.pieceIsControlled(0,3,1) || this.pieceIsControlled(0,2,1))){
					if (this.castling[0]==false){
						gr.set(0, true);
					}
				}
			}
		}			

		// White pieces
		if (this.getBoard()[7][4]!=null && this.getBoard()[7][0]!=null && this.getBoard()[7][4].getClass().toString().equals("class pieces.King") && this.getBoard()[7][0].getClass().toString().equals("class pieces.Rook") && !this.getBoard()[7][4].hasMove() && !this.getBoard()[7][0].hasMove()){
			if (this.getBoard()[7][3]==null && this.getBoard()[7][2]==null && this.getBoard()[7][1]==null){
				if (!(this.pieceIsControlled(7,4,2) || this.pieceIsControlled(7,3,2) || this.pieceIsControlled(7,2,2))){
					if (this.castling[1]==false){
						gr.set(1, true);
					}
				}
			}
		}	
		return gr;
	}
	
	// First list: coordinates of the black pawns near a white pawn on the 4th line
	// Second list: coordinates of the white pawns near a black pawn on the 3rd line
	public ArrayList <ArrayList<int[][]>> pawn(){
		ArrayList<ArrayList<int[][]>> r = new ArrayList<ArrayList<int[][]>>();
		r.add(new ArrayList<int[][]>());
		r.add(new ArrayList<int[][]>());

		int j=0;
		// 1st list
		while (j<8){
			
			if (this.board[4][j]!=null && this.board[4][j].getClass().toString().equals("class pieces.Pawn") && this.board[4][j].getPlayer()==1){
				if (j-1>=0 && this.board[4][j-1]!=null && this.board[4][j-1].getClass().toString().equals("class pieces.Pawn") && this.board[4][j-1].getPlayer()==2){
					r.get(0).add(new int[][] {{4,j}, {4, j-1}});
				}
				else if (j+1<=7 && this.board[4][j+1]!=null && this.board[4][j+1].getClass().toString().equals("class pieces.Pawn") && this.board[4][j+1].getPlayer()==2){
					r.get(0).add(new int[][] {{4,j}, {4, j+1}});
				}
			}
			j++;
		}

		j=0;
		
		// 2nd list
		while (j<8){
			if (this.board[3][j]!=null && this.board[3][j].getClass().toString().equals("class pieces.Pawn") && this.board[3][j].getPlayer()==2){
				if (j-1>=0 && this.board[3][j-1]!=null && this.board[3][j-1].getClass().toString().equals("class pieces.Pawn") && this.board[3][j-1].getPlayer()==1){
					r.get(1).add(new int[][] {{3,j}, {3, j-1}});
				}
				else if (j+1<=7 && this.board[3][j+1]!=null && this.board[3][j+1].getClass().toString().equals("class pieces.Pawn") && this.board[3][j+1].getPlayer()==1){
					r.get(1).add(new int[][] {{3,j}, {3, j+1}});
				}
			}
			j++;
		}
		return r;
	}

	/**
	 * Check is en passant is possible
	 * @return	The coordinates of the pawns that can do it.
	 */
	public ArrayList<ArrayList <int[]>> enPassant(){
		ArrayList <ArrayList <int[]>> r = new ArrayList<>();
		r.add(new ArrayList<>());
		r.add(new ArrayList<>());

		// Black player can do en passant		
		if (!this.moves.get(1).isEmpty() && this.moves.get(1).get(this.moves.get(1).size()-1)[0][0]==4 && this.moves.get(1).get(this.moves.get(1).size()-1)[1][0]==6 && this.pawn().get(0).size()!=0){
			for (int i=0; i<this.pawn().get(0).size(); i++){ 
				if (this.moves.get(1).get(this.moves.get(1).size()-1)[1][1]==this.pawn().get(0).get(i)[1][1]){ // White pawn near a black pawn
					r.get(0).add(new int []{5,this.pawn().get(0).get(i)[1][1]}); // Add coordinates for en passant 
					

				}
			}
		}
		// White player can do en passant	
		if (!this.moves.get(0).isEmpty() && this.moves.get(0).get(this.moves.get(0).size()-1)[0][0]==3 && this.moves.get(0).get(this.moves.get(0).size()-1)[1][0]==1 && this.pawn().get(1).size()!=0){
			for (int i=0; i<this.pawn().get(1).size(); i++){
				if (this.moves.get(0).get(this.moves.get(0).size()-1)[1][1]==this.pawn().get(1).get(i)[1][1]){
					r.get(1).add(new int[]{2, this.pawn().get(1).get(i)[1][1]});
				}
			}
		}

		return r; 

	}

}
