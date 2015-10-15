/** Bad.java
 * @author Carter Buce | cmb9400
 * 
 * Version:
 * 		$Id: Bad.java,v 1.7 2015/09/27 15:31:11 cmb9400 Exp $
 * 
 * Revisions:
 * 		$Log: Bad.java,v $
 * 		Revision 1.7  2015/09/27 15:31:11  cmb9400
 * 		fixed checking possible moves (was size^2 - 1, now  only size^2)
 *
 * 		Revision 1.6  2015/09/27 03:33:52  cmb9400
 * 		fixed getmove
 *
 * 		Revision 1.5  2015/09/22 15:30:21  cmb9400
 * 		wrote getmove algorithm
 *
 * 		Revision 1.4  2015/09/21 03:16:17  cmb9400
 * 		added more documentation
 *
 * 		Revision 1.3  2015/09/20 23:31:03  cmb9400
 * 		*** empty log message ***
 *
 * 		Revision 1.2  2015/09/20 03:55:43  cmb9400
 * 		added methods
 *
 * 		Revision 1.1  2015/09/18 03:41:32  cmb9400
 * 		created
 *
 * 
 */

public class Bad extends Player {

	@Override
	public int getMove(Board b, char player) {
		int requestedMove = -1;
		int size = b.getSize();
		for(int i = 0; i < (size*size); i++){
			if(b.canPlace(player, i)){
				requestedMove = i;
				return requestedMove;
			}
		
		}
		return requestedMove;
	}
	
	
	/**gives the type of player
	 * @return the type of player
	 */
	@Override
	public String getPlayerType() {
		return "bad";
	}

}
