import java.util.Scanner;

/** Player.java
 * @author Carter Buce | cmb9400
 * 
 * Version:
 * 		$Id: Player.java,v 1.11 2015/09/27 15:31:11 cmb9400 Exp $
 * 
 * Revisions:
 * 		$Log: Player.java,v $
 * 		Revision 1.11  2015/09/27 15:31:11  cmb9400
 * 		fixed checking possible moves (was size^2 - 1, now  only size^2)
 *
 * 		Revision 1.10  2015/09/27 05:11:15  cmb9400
 * 		set scanner to final - singleton design pattern
 *
 * 		Revision 1.9  2015/09/27 04:49:55  cmb9400
 * 		close scanner before exit
 *
 * 		Revision 1.8  2015/09/27 03:55:23  cmb9400
 * 		changed scanner to static so it's shared
 *
 * 		Revision 1.7  2015/09/27 03:44:42  cmb9400
 * 		added way to close scanner
 *
 * 		Revision 1.6  2015/09/27 03:28:52  cmb9400
 * 		fixed canmakemove to go through entire board
 *
 * 		Revision 1.5  2015/09/27 03:25:20  cmb9400
 * 		optimized canmakemove method
 *
 * 		Revision 1.4  2015/09/20 23:30:49  cmb9400
 * 		removed getinput method as it only applies to the human player
 *
 * 		Revision 1.3  2015/09/20 04:13:14  cmb9400
 * 		added scanner method
 *
 * 		Revision 1.2  2015/09/20 03:56:06  cmb9400
 * 		created methods, defined canMakeMove
 *
 * 		Revision 1.1  2015/09/18 03:41:34  cmb9400
 * 		created
 *
 * 
 */

public abstract class Player {
	
	public abstract int getMove(Board b, char player);
	public abstract String getPlayerType();
	
	private static final Scanner scan = new Scanner(System.in);
	
	public static Scanner getScanner(){
		return scan;
	}
	
	public static void closeScanner(){
		scan.close();
	}
	
	
	/**
	 * Check if the player can make a move
	 * @param b - the board the game is being played on
	 * @param player - the player to check if can make a move
	 * @return if the player can make a move on the board
	 */
	public boolean canMakeMove(Board b, char player){
		int size = b.getSize();
		for(int i = 0; i < (size*size); i++){
			if(b.canPlace(player, i)){
				return true;
			}
		}
		return false;
	}


}
