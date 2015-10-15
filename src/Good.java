import java.util.ArrayList;

/** Good.java
 * @author Carter Buce | cmb9400
 * 
 * Version:
 * 		$Id: Good.java,v 1.16 2015/09/30 02:16:53 cmb9400 Exp $
 * 
 * Revisions:
 * 		$Log: Good.java,v $
 * 		Revision 1.16  2015/09/30 02:16:53  cmb9400
 * 		removed debug code
 *
 * 		Revision 1.15  2015/09/30 02:09:43  cmb9400
 * 		algorithm fixed
 *
 * 		Revision 1.14  2015/09/29 03:52:54  cmb9400
 * 		fixed(hopefully) algorithm
 *
 * 		Revision 1.13  2015/09/29 03:27:34  cmb9400
 * 		tried to fix algorithm
 *
 * 		Revision 1.12  2015/09/28 20:33:35  cmb9400
 * 		fixed creating getspaces and getblocks
 *
 * 		Revision 1.11  2015/09/27 15:56:00  cmb9400
 * 		fixed checking diagonal bottom left piece in getblocks
 *
 * 		Revision 1.10  2015/09/27 15:51:09  cmb9400
 * 		fixed arraylist -- was trying to set an index that didn't exist
 *
 * 		Revision 1.9  2015/09/27 15:31:12  cmb9400
 * 		fixed checking possible moves (was size^2 - 1, now  only size^2)
 *
 * 		Revision 1.8  2015/09/27 05:11:02  cmb9400
 * 		removed unused import
 *
 * 		Revision 1.7  2015/09/27 03:33:11  cmb9400
 * 		fixed getpossiblemoves
 *
 * 		Revision 1.6  2015/09/24 02:00:14  cmb9400
 * 		finished algorithm
 *
 * 		Revision 1.5  2015/09/24 01:13:27  cmb9400
 * 		wrote information gathering part of algorithm
 *
 * 		Revision 1.4  2015/09/21 03:16:18  cmb9400
 * 		added more documentation
 *
 * 		Revision 1.3  2015/09/20 23:31:03  cmb9400
 * 		*** empty log message ***
 *
 * 		Revision 1.2  2015/09/20 03:55:44  cmb9400
 * 		added methods
 *
 * 		Revision 1.1  2015/09/18 03:41:35  cmb9400
 * 		created
 *
 * 
 */

public class Good extends Player{

	/**
	 * calculate the best possible move
	 */
	@Override
	public int getMove(Board b, char player) { // place in most open space, diagonally?
		ArrayList<Integer> possibleMoves = getPossibleMoves(b, player); //(index vs space able to move)
		ArrayList<Integer> emptySpaces = getEmptySpaces(b, player, possibleMoves); //(index vs number empty spaces)
		ArrayList<Integer> blockingMoves = getBlocks(b, player, possibleMoves); // (index vs number of blocks)
		
		int maxSpaces = 0;
		for(int i = 0; i < possibleMoves.size(); i++){ // find max amount of empty spaces
			int spaces = emptySpaces.get(i);
			if(spaces > maxSpaces){
				maxSpaces = spaces;
			}
		}
		
		ArrayList<Integer> bestBySpaces = new ArrayList<Integer>(); //get list of moves that have maximum amount of empty spaces
		for(int i = 0; i < possibleMoves.size(); i++){
			if(emptySpaces.get(i) == maxSpaces){
				bestBySpaces.add(i); //fills array with indexes of possibleMoves
			}
		}
		if(bestBySpaces.size() == 1){ //if only one move with max spaces return it
			return possibleMoves.get(bestBySpaces.get(0));
		}
		
		
		
		
		int maxBlocks = 0;
		for(int i = 0; i < bestBySpaces.size(); i++){ //find max amount of blocks for the maximum empty space moves
			int blocks = blockingMoves.get(bestBySpaces.get(i));
			if(blocks > maxBlocks){
				maxBlocks = blocks; 
			} 
		}
		
		ArrayList<Integer>bestByBlocking = new ArrayList<Integer>();
		for(int i = 0; i < possibleMoves.size(); i++){
			if(emptySpaces.get(i) == maxSpaces && blockingMoves.get(i) == maxBlocks){ //bestBySpaces contains indexes of possibleMoves (and its parallel arrays)
				bestByBlocking.add(i); //add index of move
			}
		}
		return possibleMoves.get(bestByBlocking.get(0)); //return most blocking move with highest number of spaces around (if duplicates, returns lowest number)
	}
	
	
	/**
	 * get a list of all the possible moves
	 * @param b the board
	 * @param player the current player
	 * @return list of all possible moves that can be made
	 */
	private ArrayList<Integer> getPossibleMoves(Board b, char player){
		ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
		int size = b.getSize();
		for(int i = 0; i < (size*size); i++){
			if(b.canPlace(player, i)){
				possibleMoves.add(i);
			}
		}
		return possibleMoves;
	}
	
	
	/**
	 * returns parallel array of amount of empty spaces around the possible move
	 * @param b the board
	 * @param player current player
	 * @param possibleMoves list of possible spaces to place the piece
	 * @return array of numbers of empty spaces
	 */
	private ArrayList<Integer> getEmptySpaces(Board b, char player, ArrayList<Integer> possibleMoves){
		//parallel array to find amount of empty spaces around the move
		ArrayList<Integer>emptySpaces = new ArrayList<Integer>(possibleMoves.size());
		int size = b.getSize();
		int[][]board = b.getBoard();
		
		for(int j = 0; j < possibleMoves.size(); j++){
			int i = possibleMoves.get(j); //j is the index, i is the actual space
			emptySpaces.add(0);
			int row = i / size;
			int col = i % size;
			int freeSpaces = 0;
			
			if(board[row][Math.min(col+1, size-1)] >= 0){ //one to the right
				if(col+1 <= size -1){
					freeSpaces += 1;
				}
			}
			if(board[row][Math.max(col-1, 0)] >= 0){ //one to the left
				if(col-1 >= 0){
					freeSpaces += 1;
				}
			}
			if(board[Math.min(row+1, size-1)][col] >=0){ //one below
				if(row+1 <= size-1){
					freeSpaces += 1;
				}
			}
			if(board[Math.max(row-1, 0)][col] >= 0){ //one above
				if(row - 1 >= 0){
					freeSpaces += 1;
				}
			}
			
			emptySpaces.set(j, freeSpaces); //number of free spaces orthogonal
		}
		//we now have two parallel arrays: one with the available spots to play, another with the
		//number of empty spaces around that spot
		return emptySpaces;
	}
	
	
	
	/**
	 * returns parallel array of amount of "blocks" each move will cause
	 * @param b the board
	 * @param player current player
	 * @param possibleMoves list of possible moves to make
	 * @return array of numbers of blocks
	 */
	private ArrayList<Integer> getBlocks(Board b, char player, ArrayList<Integer> possibleMoves){
		ArrayList<Integer>blockingMoves = new ArrayList<Integer>(possibleMoves.size());
		int size = b.getSize();
		int[][]board = b.getBoard();
		
		for(int j = 0; j < possibleMoves.size(); j++){
			int i = possibleMoves.get(j); //j is the index, i is the space of possible move
			blockingMoves.add(0);
			int row = i / size;
			int col = i % size; 
			int blocks = 0;
			int otherPlayerInt = 0;
			
			if(player == 'X' || player == 'x'){ //o corresponds to -2
				otherPlayerInt = -2;
			}
			if(player == 'O' || player == 'o'){ //x corresponds to -1
				otherPlayerInt = -1;
			}
			
			
			if(board[row][Math.min(col+2, size-1)] == otherPlayerInt){ //two to the right
				blocks += 1;
			}
			if(board[row][Math.max(col-2, 0)]  == otherPlayerInt){ //two to the left
				blocks += 1;
			}
			if(board[Math.min(row+2, size-1)][col]  == otherPlayerInt){ //two below
				blocks += 1;
			}
			if(board[Math.max(row-2, 0)][col]  == otherPlayerInt){ //two above
				blocks += 1;
			}
			if(board[Math.min(row + 1, size - 1)][Math.min(col + 1, size - 1)] == otherPlayerInt){ //diagonal top right
				blocks += 1;
			}
			if(board[Math.min(row + 1, size - 1)][Math.max(col - 1, 0)] == otherPlayerInt){ //diagonal top left
				blocks += 1;
			}
			if(board[Math.max(row - 1, 0)][Math.min(col + 1, size - 1)] == otherPlayerInt){ //diagonal bottom right
				blocks += 1;
			}
			if(board[Math.max(row - 1,  0)][Math.max(col - 1, 0)] == otherPlayerInt){ //diagonal bottom left
				blocks += 1;
			}
			
			blockingMoves.set(j, blocks);
		}
		//we now know which spots will block the most enemy moves
		return blockingMoves;
	}
	
	
	/**gives the type of player
	 * @return the type of player
	 */
	@Override
	public String getPlayerType() {
		return "good";
	}

}
