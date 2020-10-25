package AI;

import java.util.ArrayList;

import game.Engine;
import pieces.Pieces;

public class MCTS {
	// MCTS algorithm
	public static int[][] infer(ArrayList <int[][]>canDo, int time, Engine g,int p, int r) {

		// Select possible moves using a minimax algorithm to remove absurd moves
		ArrayList <int[][]> preSelect=preSelect(canDo, g);

		// Return the only possible move
		if (preSelect.size()==1){
			return preSelect.get(0);
		}

		Pieces [][]table=g.getBoard();
		long t0=System.currentTimeMillis();
		double[][] score = new double[preSelect.size()][2]; 
		for(int k=0;k<score.length;k++) {
			score[k][0]=0;
			score[k][1]=0;
		}
		// Use the maximum allowed time
		while(((System.currentTimeMillis())-t0)<time*1000) {

			// Create a test engine to simulate the moves and copy the real engine
			Pieces[][] tableTest = new Pieces[table.length][table[0].length];
			ArrayList <int[][]>canDoTest = new ArrayList <int[][]>();
			int pTest=p;
			boolean isOver=false;
			for (int i = 0; i<table.length;i++) {
				for (int j = 0; j<table[0].length;j++) {
					tableTest[i][j]=table[i][j];
				}
			}
			for (int k = 0; k<preSelect.size();k++) {
				int[][] o = new int[preSelect.get(k).length][preSelect.get(k)[0].length];
				for (int t = 0; t<preSelect.get(k).length;t++) {
					for (int h=0; h<preSelect.get(k)[t].length; h++) {
						o[t][h]=preSelect.get(k)[t][h];
					}

				}
				canDoTest.add(o);
			}
			Engine engineTest=new Engine(tableTest, g.getCastling(), g.isSelected(), g.getSelectedCoord(), g.getMoves(), g.isOver(), g.getTurn());

			// Get the best child
			int n0=treePolicy(canDoTest, engineTest, score);

			int[][] s = canDoTest.get(n0);
			// Play the move corresponding to this best play
			pTest=expand(tableTest,s, pTest, engineTest);

			// Get the next possible moves
			canDoTest=canDo(engineTest,pTest);

			// Check if the game is over or not
			int[] t = check(engineTest);
			if (t[0]==0) {
				isOver=false;
			}
			else {
				isOver=true;
			}
			// Continue to play the game with this test board
			int delta=defaultPolicy(r, isOver, canDoTest, pTest, engineTest, p, g);

			// Update the score of the selected move with the result of the game
			backUp(score, delta, n0);
		}

		// Get the best move
		int n=0;
		for (int k=0;k<score.length;k++) {
			if (score[k][0]>score[n][0]) {
				n=k;
			}
		}

		/*
		// Print the values of the moves
		for (int k = 0; k<preSelect.size();k++) {
			System.out.println("i0 = "+preSelect.get(k)[0][0]+" j0 = "+preSelect.get(k)[0][1]+" i1 = "+preSelect.get(k)[1][0]+" j1 = "+preSelect.get(k)[1][1]+" score = "+score[k][0]+" n = "+score[k][1]);
		}
		System.out.println();
		 */
		return preSelect.get(n);

	}
	// Moves the piece
	public static int expand(Pieces tableau[][],int[][] s, int p, Engine engineTest) {
		int i=s[0][0];
		int j=s[0][1];
		int i1=s[1][0];
		int j1=s[1][1];
		engineTest.move(i,j,i1,j1);
		return changePlayer(p, engineTest);
	}
	// Moves to get back after (simulation)
	public static Pieces expandAndBackLater(Pieces tableau[][],int[][] s, int p, Engine engineTest) {
		int i=s[0][0];
		int j=s[0][1];
		int i1=s[1][0];
		int j1=s[1][1];
		Pieces eaten =engineTest.moveAndBackLater(i,j,i1,j1);
		return eaten;
	}
	// Cancel the last simulation move
	public static int goBack(Pieces tableau[][],int[][] s, int p, Engine engineTest, boolean hasMove, Pieces eaten) {
		int i=s[0][0];
		int j=s[0][1];
		int i1=s[1][0];
		int j1=s[1][1];
		engineTest.getBoard()[i1][j1].setHasMove(hasMove);
		engineTest.getBoard()[i][j]=engineTest.getBoard()[i1][j1];
		engineTest.getBoard()[i1][j1]=null;
		engineTest.getBoard()[i][j].setI(i);
		engineTest.getBoard()[i][j].setJ(j);
		if (eaten!=null) {
			engineTest.getBoard()[eaten.getI()][eaten.getJ()]=eaten;
		}
		if(engineTest.getBoard()[i][j].getClass().toString().equals("class pieces.King") && engineTest.getBoard()[i][j].getPlayer()==1 && i==0 && j==4 && i1==0 && j1==2) {

			engineTest.getBoard()[0][0]=engineTest.getBoard()[0][3];
			engineTest.getBoard()[0][0].setI(0);
			engineTest.getBoard()[0][0].setJ(0);
			engineTest.getBoard()[0][3]=null; // Black king big castling
		}
		else if(engineTest.getBoard()[i][j].getClass().toString().equals("class pieces.King") && engineTest.getBoard()[i][j].getPlayer()==1 && i==0 && j==4 && i1==0 && j1==6) {

			engineTest.getBoard()[0][7]=engineTest.getBoard()[0][5];
			engineTest.getBoard()[0][7].setI(0);
			engineTest.getBoard()[0][7].setJ(7);
			engineTest.getBoard()[0][5]=null; // Black king small castling
		}
		else if(engineTest.getBoard()[i][j].getClass().toString().equals("class pieces.King") && engineTest.getBoard()[i][j].getPlayer()==2 && i==7 && j==4 && i1==7 && j1==2) {

			engineTest.getBoard()[7][0]=engineTest.getBoard()[7][3];
			engineTest.getBoard()[7][0].setI(7);
			engineTest.getBoard()[7][0].setJ(0);
			engineTest.getBoard()[7][3]=null; // White king big castling
		}

		else if(engineTest.getBoard()[i][j].getClass().toString().equals("class pieces.King") && engineTest.getBoard()[i][j].getPlayer()==2 && i==7 && j==4 && i1==7 && j1==6) {

			engineTest.getBoard()[7][7]=engineTest.getBoard()[7][5];
			engineTest.getBoard()[7][7].setI(7);
			engineTest.getBoard()[7][7].setJ(7);
			engineTest.getBoard()[7][5]=null; // White king small castling
		}
		return changePlayer(p, engineTest);
	}



	// All possible moves for p player
	public static ArrayList <int[][]> canDo(Engine engine, int p) {
		ArrayList <int[][]>canDo = new ArrayList<int[][]>();
		Pieces [][] table = engine.getBoard();
		for (int i = 0; i<table.length; i++) {
			for(int j = 0 ; j<table[i].length;j++) {
				if(table[i][j]!=null) {
					if (table[i][j].getPlayer()==p) {
						for (int k = 0; k<table[i][j].canGo().size();k++) {
							canDo.add(new int[][] {{i,j},{table[i][j].canGo().get(k)[0],table[i][j].canGo().get(k)[1]}});
						}
					}
				}
			}
		}

		return canDo;
	}

	// Update the score with the result of the game
	public static void backUp(double[][] score, int delta, int n0){
		score[n0][1]+=1;
		score[n0][0]=((score[n0][1]-1)*score[n0][0]+delta)/score[n0][1];
	}

	// Default policy of the MCTS algorithm: play a part of the game to evaluate a move
	public static int defaultPolicy(int r,boolean isOver, ArrayList<int[][]> canDoTest, int pTest, Engine engine, int p, Engine g) {
		Pieces[][] tableTest = engine.getBoard();
		int winner = 0;
		int o = 0;

		// Evaluate the value of the board
		int v0=value(tableTest, pTest);

		// Play several moves (limited to 6 moves)
		while (!isOver && value(tableTest, pTest)-v0>-8 && o<=6) {
			// Get possible moves
			canDoTest = canDo(engine,pTest);

			if (canDoTest.size()>0) {
				// Select a random move
				int n = (int)(Math.random()*(canDoTest.size()));
				int[][] s = canDoTest.get(n);
				// Play the move
				pTest = expand(tableTest,s, pTest, engine);
			}

			// Check if the game is over or not
			int[] t = check(engine);
			if (t[0]==0) {
				isOver=false;
			}
			else {
				isOver=true;
			}
			winner=t[1];

			o+=1;
		}

		// Return a high score if win and low if lost
		if(winner!=0) {
			if (winner==p) {
				return 25;
			}
			else if(winner!=p) {
				return -25;
			}
		}
		// Or return the value of the board at the end if the game is not over after 6 moves
		else {
			return value(engine.getBoard(), pTest);
		}
		if(engine.isOver()==true) {
			isOver =true;
		}
		// Default return
		return 0;
	}

	// Change the player currently playing
	public static int changePlayer(int p, Engine engineTest) {
		if (p==1) {
			engineTest.setTurn(2);
			return 2;
		}
		else {
			engineTest.setTurn(1);
			return 1;
		}
	}

	// Tree policy of the MCTS algorithm
	public static int treePolicy(ArrayList <int[][]>canDoTest, Engine g, double[][] score) {		
		int n0= bestChild(canDoTest, g, score);
		return n0;
	}

	// Get the best child
	public static int bestChild(ArrayList <int[][]>canDoTest, Engine g, double[][] score) {
		double[] scoreUCB=new double[score.length];
		int p=0;
		for(int k=0; k<score.length; k++) {
			p+=score[k][1];
		}
		for (int k=0; k<score.length; k++) {
			scoreUCB[k]=score[k][0]+1*Math.sqrt(2*Math.log(p)/score[k][1]);
		}
		int kmax=0;
		double smax=-10000000;
		for (int k=0; k<score.length; k++) {
			if(scoreUCB[k]>=smax) {
				kmax=k;
				smax=scoreUCB[k];
			}
		}
		return kmax;

	}

	// Minimax algorithm to only keep good moves
	public static ArrayList<int[][]> preSelect(ArrayList <int[][]>canDoTest, Engine g){
		ArrayList<int[][]> preSelect = new ArrayList<int[][]>();

		int v = -100000;
		// Evaluate all the possible moves
		for (int k=0; k<canDoTest.size(); k++) {
			// Copy the engine
			Pieces [][]table=g.getBoard();
			Pieces[][] tableTest = new Pieces[table.length][table[0].length];
			for (int i = 0; i<table.length;i++) {
				for (int j = 0; j<table[0].length;j++) {
					tableTest[i][j]=table[i][j];
				}
			}
			Engine engineTest=new Engine(tableTest, g.getCastling(), g.isSelected(), g.getSelectedCoord(), g.getMoves(), g.isOver(), g.getTurn());
			int[][]s=new int[][] {{canDoTest.get(k)[0][0],canDoTest.get(k)[0][1]}, {canDoTest.get(k)[1][0],canDoTest.get(k)[1][1]}};

			// Play the move
			expand(engineTest.getBoard(), s ,engineTest.getTurn(), engineTest);

			// Get the next possible moves
			ArrayList <int[][]> canDo1=canDo(engineTest, engineTest.getTurn());
			// Evaluate the board
			int m=value(engineTest.getBoard(), g.getTurn());

			// Try all the moves
			for (int l=0; l<canDo1.size(); l++) {

				int[][]s1=new int[][] {{canDo1.get(l)[0][0],canDo1.get(l)[0][1]}, {canDo1.get(l)[1][0],canDo1.get(l)[1][1]}};
				boolean hasMove=engineTest.getBoard()[s1[0][0]][s1[0][1]].hasMove();
				Pieces eaten=expandAndBackLater(engineTest.getBoard(), s1 ,engineTest.getTurn(), engineTest);

				// Find the best move for the player using the algorithm (minimizing the opponent score)
				if(value(engineTest.getBoard(), g.getTurn())<=m) {
					m=value(engineTest.getBoard(), g.getTurn());
				}
				// Reverse the move to avoid creating a new engine for every move
				goBack(engineTest.getBoard(), s1, engineTest.getTurn(), engineTest, hasMove, eaten);
			}

			// Move as good as the previous one, to consider
			if(m==v) {
				preSelect.add(canDoTest.get(k));
				v=m;
			}

			// Better move, replace the previous one
			if(m>v) {
				preSelect.clear();
				preSelect.add(canDoTest.get(k));
				v=m; 
			}
		}

		return preSelect;
	}
	
	// Check if the game is over or not
	public static int[] check(Engine engine) {
		int[] s = new int[] {0,0};
		if (engine.king().size()<2) {

			engine.setOver(true);
			if(engine.king().size()!=0) {
				engine.setWinner(engine.king().get(0)[0][0]);

			}
		}

		if(engine.isOver()==true) {
			s[0]=1;

			if(engine.getWinner()==1) {
				s[1]=1;
			}
			if(engine.getWinner()==2) {
				s[1]=2;
			}
		}

		return s;


	}

	// Get the value of the board. The value of the pieces is standard for minimax algorithms.
	public static int value(Pieces[][] table, int p) {
		int t=0;
		for (int i = 0; i<table.length;i++) {
			for (int j = 0 ; j<table[0].length;j++) {

				if (table[i][j]!=null) {
					if (table[i][j].getPlayer()==p) {
						t+=table[i][j].getValue();
					}
					else {
						t-=table[i][j].getValue();
					}
				}
			}
		}
		return t;

	}

	// Print the engine to debug
	public static void printEngine(Engine engine) {
		Pieces[][] table = engine.getBoard();
		for (int i = 0; i<table.length;i++) {
			for (int j = 0 ; j<table[0].length;j++) {
				if (j!=table.length-1) {
					if (table[i][j]!=null) {
						System.out.print(""+table[i][j].getPlayer()+" "+table[i][j].getClass()+" ");
					}
					else {
						System.out.print("( )");
					}
				}
				else if (j==table.length-1 && i!=table.length-1){

					if (table[i][j]!=null) {
						System.out.println(""+table[i][j].getPlayer()+" "+table[i][j].getClass()+ " ");
					}
					else {
						System.out.println("( )");
					}
				}
				else {
					if (table[i][j]!=null) {
						System.out.println(""+table[i][j].getPlayer()+" "+table[i][j].getClass()+" ");
					}
					else {
						System.out.print("( )");
					}
					System.out.println(" ");
				}
			}
		}
	}
}
