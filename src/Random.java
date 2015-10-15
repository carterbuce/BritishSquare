import java.util.ArrayList;

/** random.java
 * @author Carter Buce | cmb9400
 * 
 * Version:
 * 		$Id: Random.java,v 1.5 2015/09/30 02:09:52 cmb9400 Exp $
 * 
 * Revisions:
 * 		$Log: Random.java,v $
 * 		Revision 1.5  2015/09/30 02:09:52  cmb9400
 * 		optimized algorithm
 *
 * 		Revision 1.4  2015/09/21 03:16:32  cmb9400
 * 		wrote getmove method
 *
 * 		Revision 1.3  2015/09/20 23:31:04  cmb9400
 * 		*** empty log message ***
 *
 * 		Revision 1.2  2015/09/20 03:55:44  cmb9400
 * 		added methods
 *
 * 		Revision 1.1  2015/09/18 03:41:37  cmb9400
 * 		created
 *
 * 
 */

public class Random extends Player{

	/**  generates a random  move
	 * @return random integer that is a valid move
	 */
	@Override
	public int getMove(Board b, char player) {//get list of possible moves, generate random number from that list
		ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
		int size = b.getSize();
		for(int i = 0; i < (size*size); i++){
			if(b.canPlace(player, i)){
				possibleMoves.add(i);
			}
		}
		int index = (int) (Math.random() * (possibleMoves.size()));
		return possibleMoves.get(index);
		
	}
	
	
	/**gives the type of player
	 * @return the type of player
	 */
	@Override
	public String getPlayerType() {
		return "random";
	}

}
