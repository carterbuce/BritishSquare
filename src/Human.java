import java.util.Scanner;

/** human.java
 * @author Carter Buce | cmb9400
 * 
 * Version:
 * 		$Id: Human.java,v 1.9 2015/09/27 15:37:33 cmb9400 Exp $
 * 
 * Revisions:
 * 		$Log: Human.java,v $
 * 		Revision 1.9  2015/09/27 15:37:33  cmb9400
 * 		made getinput private
 *
 * 		Revision 1.8  2015/09/27 04:50:05  cmb9400
 * 		update to fix scanner
 *
 * 		Revision 1.7  2015/09/27 01:25:15  cmb9400
 * 		changed method of asking for a move
 *
 * 		Revision 1.6  2015/09/27 00:09:58  cmb9400
 * 		changed so that prints the request to get move before expecting input
 *
 * 		Revision 1.5  2015/09/21 15:27:47  cmb9400
 * 		changed error printing to conform to guidelines
 *
 * 		Revision 1.4  2015/09/21 03:16:18  cmb9400
 * 		added more documentation
 *
 * 		Revision 1.3  2015/09/20 23:31:19  cmb9400
 * 		worked on getmove method
 *
 * 		Revision 1.2  2015/09/20 03:55:43  cmb9400
 * 		added methods
 *
 * 		Revision 1.1  2015/09/18 03:41:34  cmb9400
 * 		created
 *
 * 
 */

public class Human extends Player {

	@Override
	public int getMove(Board b, char player) {
		int requestedMove = -1;
		while(true){
			System.out.print("Player " + Character.toUpperCase(player) + ": Enter the location to place your piece (-1 to quit): ");
			requestedMove = getInput(player);
			if(b.canPlace(player, requestedMove) || requestedMove == -1){
				return requestedMove;
			}
			else{
				System.out.println("invalid location: " + requestedMove);
			}
		
		}
	}
	
	/**
	 * create a method that can be accessed to get user input
	 * @return string of user input
	 */
	private int getInput(char player){
		int input = 0;
		Scanner scan = Player.getScanner();
		while(true){
			String inputString = scan.next();
			try{
				input = Integer.parseInt(inputString);
				return input;
			}
			catch(NumberFormatException e){
				System.out.println("invalid location: " + inputString);
				System.out.print("Player " + Character.toUpperCase(player) + ": Enter the location to place your piece (-1 to quit): ");
			}
		}
	}
	
	/**gives the type of player
	 * @return the type of player
	 */
	@Override
	public String getPlayerType() {
		return "human";
	}

}
