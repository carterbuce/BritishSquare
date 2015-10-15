/** Board.java
 * @author Carter Buce | cmb9400
 * 
 * Version:
 * 		$Id: Board.java,v 1.11 2015/09/27 21:12:42 cmb9400 Exp $
 * 
 * Revisions:
 * 		$Log: Board.java,v $
 * 		Revision 1.11  2015/09/27 21:12:42  cmb9400
 * 		removed space printed to right side of board
 *
 * 		Revision 1.10  2015/09/27 03:15:37  cmb9400
 * 		prints space after board
 *
 * 		Revision 1.9  2015/09/27 00:00:19  cmb9400
 * 		debug printing
 *
 * 		Revision 1.8  2015/09/22 01:17:55  cmb9400
 * 		added check for if first move is in center
 *
 * 		Revision 1.7  2015/09/21 18:07:21  cmb9400
 * 		added isempty method
 *
 * 		Revision 1.6  2015/09/21 14:10:22  cmb9400
 * 		added getboard method
 *
 * 		Revision 1.5  2015/09/20 23:30:09  cmb9400
 * 		fixed print method (did not print a line before the board)
 *
 * 		Revision 1.4  2015/09/20 04:49:58  cmb9400
 * 		removed  general  constructor
 *
 * 		Revision 1.3  2015/09/20 03:56:53  cmb9400
 * 		wrote out all the methods
 *
 * 		Revision 1.2  2015/09/19 19:00:37  cmb9400
 * 		made constructor and print method
 *
 * 		Revision 1.1  2015/09/18 03:41:32  cmb9400
 * 		created
 *
 * 
 */

public class Board {
	private static int[][] board;
	private int size;
	
	
	/**
	 * Create a new instance of board for a given size
	 * @param size the height and width size of the square board to create
	 */
	public Board(int size){
		board = new int[size][size];
		this.size = size;
		int count = 0;
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				board[i][j] = count;
				count += 1;
			}
		}
	}
	
	
	/**
	 * given a player and space number, checks if spot is a legal area to place a piece and places it
	 * @param player the player -- x or o to place a piece in a given space
	 * @param space -- the space on the board to place the piece in, from 0 to (size^2 - 1)
	 * @return if the piece was placed or not
	 */
	public void place(char player, int space){
		int playerInt = 0;
		int row = space / size;
		int col = space % size;
		
		if(player == 'X' || player == 'x'){ //x corresponds to -1
			playerInt = -1;
		}
		if(player == 'O' || player == 'o'){ //o corresponds to -2
			playerInt = -2;
		}
		
		
		board[row][col] = playerInt;
	}
	
	
	/**
	 * @return the size of the board
	 */
	public int getSize(){
		return this.size;
	}
	
	
	/**
	 * determine if the board is empty
	 * @return true if the board is empty
	 */
	public boolean isEmpty(){
		boolean boardEmpty = true;
		for(int[] i : board){
			for (int j : i){
				if(j < 0){
					boardEmpty = false;
				}
			}
			
		}
		return boardEmpty;
	}
	
	
	/**
	 * Print the current game board
	 * 
	 */
	public void print(){
		System.out.println();
		
		for(int i = 0;  i < size; i++){
			for(int j = 0; j < size; j++){ // prints the top horizontal separator lines
				System.out.print("+---");
			}
			System.out.print("+\n"); //end of line
			
			for(int j = 0; j < size; j++){ //prints top row of cell
				System.out.print("|");
				if(board[i][j] >= 0){
					System.out.print("   ");
				}
				if(board[i][j] == -1){ //-1 means it's an "x" spot
					System.out.print("XXX");
				}
				if(board[i][j] == -2){ //-2 means it's an "o" spot
					System.out.print("OOO");
				}
			}
			System.out.print("|\n"); //end of line
			
			for(int j = 0; j < size; j++){ //prints bottom row of cell
				System.out.print("|");
				if(board[i][j] == -1){ //-1 means it's an "x" spot
					System.out.print("XXX");
				}
				else if(board[i][j] == -2){ //-2 means it's an "o" spot
					System.out.print("OOO");
				}

				else if(board[i][j] < 10){
					System.out.print(board[i][j] + "  ");
				}
				else if(board[i][j] > 9){
					System.out.print(board[i][j] + " ");
				}

			}
			System.out.print("|\n"); //end of line
		}
		
		for(int i = 0; i < size; i++){ //prints the last horizontal separator
			System.out.print("+---");
		}
		System.out.print("+\n");
		
		System.out.println();
	}
	
	
	
	public boolean canPlace(char player, int space){
		int otherPlayerInt = 0;
		int row = space / size;
		int col = space % size; 
		
		if(player == 'X' || player == 'x'){ //o corresponds to -2
			otherPlayerInt = -2;
		}
		if(player == 'O' || player == 'o'){ //x corresponds to -1
			otherPlayerInt = -1;
		}
		
		
		if(space > (size*size - 1) || space < 0){ //outside of array
			return false;
		}
		else if (isEmpty() && (size % 2 != 0)){//empty and board size is odd
			if(space == (size*size - 1) / 2){ //if center space and first move, return false
				return false;
			}
		}
		else if (board[row][col] < 0){ //already occupied
			return false;
		}
		else if((board[row][Math.min(col+1, size-1)] == otherPlayerInt) 
				|| (board[row][Math.max(col-1, 0)] == otherPlayerInt) 
				|| (board[Math.min(row+1, size-1)][col] ==  otherPlayerInt) 
				|| (board[Math.max(row-1, 0)][col]==otherPlayerInt)){ //if any orthogonal piece is owned by the enemy
			return false;
		}
		
		return true;
	}
	
	public int[][] getBoard(){
		return board;
	}
	
}
