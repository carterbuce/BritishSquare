/** BritishSquare.java
 * @author Carter Buce | cmb9400
 * 
 * Version:
 * 		$Id: BritishSquare.java,v 1.17 2015/09/27 21:06:07 cmb9400 Exp $
 * 
 * Revisions:
 * 		$Log: BritishSquare.java,v $
 * 		Revision 1.17  2015/09/27 21:06:07  cmb9400
 * 		changed error printing
 *
 * 		Revision 1.16  2015/09/27 21:04:16  cmb9400
 * 		changed error printing
 *
 * 		Revision 1.15  2015/09/27 04:50:22  cmb9400
 * 		close scanner before exit
 *
 * 		Revision 1.14  2015/09/27 03:40:03  cmb9400
 * 		updated printing methods
 *
 * 		Revision 1.13  2015/09/27 03:25:40  cmb9400
 * 		exit game after quit message
 *
 * 		Revision 1.12  2015/09/27 01:26:07  cmb9400
 * 		print board before declaring player move
 *
 * 		Revision 1.11  2015/09/26 23:59:39  cmb9400
 * 		added printing board
 *
 * 		Revision 1.10  2015/09/26 23:53:54  cmb9400
 * 		changed error printing
 *
 * 		Revision 1.9  2015/09/26 23:53:02  cmb9400
 * 		fixed catching board size
 *
 * 		Revision 1.8  2015/09/26 23:51:35  cmb9400
 * 		updated error printing
 *
 * 		Revision 1.7  2015/09/21 15:27:47  cmb9400
 * 		changed error printing to conform to guidelines
 *
 * 		Revision 1.6  2015/09/21 14:10:49  cmb9400
 * 		small changes
 *
 * 		Revision 1.5  2015/09/21 01:33:45  cmb9400
 * 		added a check for board size
 *
 * 		Revision 1.4  2015/09/20 23:30:26  cmb9400
 * 		worked on run method
 *
 * 		Revision 1.3  2015/09/20 04:50:09  cmb9400
 * 		added to main method
 *
 * 		Revision 1.2  2015/09/18 03:41:14  cmb9400
 * 		*** empty log message ***
 *
 * 		Revision 1.1  2015/09/13 18:25:09  cmb9400
 * 		Created preliminary files
 *
 * 
 */

public class BritishSquare {
	private static int score_x = 0;
	private static int score_o = 0;
	static Player xPlayer;
	static Player oPlayer;
	static Board gameBoard;
	
	/**
	 * @param args Usage: java BritishSquare player-X player-O [board_size]
	 */
	public static void main(String[] args) {
		if(args.length < 2 || args.length > 3){
			System.err.println("Usage: java BritishSquare player-X player-O [brdSize]");
			System.err.println("where player-X and player-O are one of: human, bad, good, random");
			System.err.println("[brdSize] is optional, if provided it must be in the range from: 3 to 7");
			Player.closeScanner();
			System.exit(1);
		}
		
		
		if(args[0].equals("human")){ //determine x player
			xPlayer = new Human();
		}
		else if (args[0].equals("bad")){
			xPlayer = new Bad();
		}
		else if (args[0].equals("random")){
			xPlayer =  new Random();
		}
		else if (args[0].equals("good")){
			xPlayer = new Good();
		}
		else{
			System.err.println("Usage: java BritishSquare player-X player-O [brdSize]");
			System.err.println("where player-X and player-O are one of: human, bad, good, random");
			System.err.println("[brdSize] is optional, if provided it must be in the range from: 3 to 7");
			Player.closeScanner();
			System.exit(1);
		}
		
		if(args[1].equals("human")){ //determine o player
			oPlayer = new Human();
		}
		else if (args[1].equals("bad")){
			oPlayer = new Bad();
		}
		else if (args[1].equals("random")){
			oPlayer =  new Random();
		}
		else if (args[1].equals("good")){
			oPlayer = new Good();
		}
		else{
			System.err.println("Usage: java BritishSquare player-X player-O [brdSize]");
			System.err.println("where player-X and player-O are one of: human, bad, good, random");
			System.err.println("[brdSize] is optional, if provided it must be in the range from: 3 to 7");
			Player.closeScanner();
			System.exit(1);
		}
		
		
		if(args.length == 3){ //create the board
			try{
				int size = Integer.parseInt(args[2]);
				if(size > 7 || size < 3){
					System.err.println("Usage: java BritishSquare player-X player-O [brdSize]");
					System.err.println("where player-X and player-O are one of: human, bad, good, random");
					System.err.println("[brdSize] is optional, if provided it must be in the range from: 3 to 7");
					Player.closeScanner();
					System.exit(1);
				}
				gameBoard = new Board(size);
			}
			catch(NumberFormatException e){
				System.err.println("Usage: java BritishSquare player-X player-O [brdSize]");
				System.err.println("where player-X and player-O are one of: human, bad, good, random");
				System.err.println("[brdSize] is optional, if provided it must be in the range from: 3 to 7");
				Player.closeScanner();
				System.exit(1);
			}
		}
		else{
			gameBoard = new Board(5);
		}
		
		run();
		
	}
	
	
	/**
	 * run a loop to get player moves and place them on the board
	 */
	public static void run(){
		gameBoard.print();
		while(xPlayer.canMakeMove(gameBoard, 'x') || oPlayer.canMakeMove(gameBoard, 'o')){
			
			
			System.out.println(xPlayer.getPlayerType() + " player X moving..."); //move x
			if(xPlayer.canMakeMove(gameBoard, 'x')){
				int move = xPlayer.getMove(gameBoard, 'x');
				if(move == -1){
					System.out.println("X quits the game");
					Player.closeScanner();
					System.exit(1);
				}
				System.out.println("Player places an X piece at location: " + move);
				gameBoard.place('x', move);
				score_x += 1;
				gameBoard.print();
			}
			else{
				System.out.println("X has no more moves and must skip turn.");
			}
			
			if(!(xPlayer.canMakeMove(gameBoard, 'x') || oPlayer.canMakeMove(gameBoard, 'o'))){ //check again that another move can be made, else end the game
				break;
			}
			
			
			System.out.println(oPlayer.getPlayerType() + " player O moving..."); //move o
			if (oPlayer.canMakeMove(gameBoard, 'o')){
				int move = oPlayer.getMove(gameBoard, 'o');
				if(move == -1){
					System.out.println("O quits the game");
					Player.closeScanner();
					System.exit(1);
				}
				gameBoard.place('o', move);
				System.out.println("Player places an O piece at location: " + move);
				score_o += 1;
				gameBoard.print();
			}
			else{
				System.out.println("O has no more moves and must skip turn.");
			}
		}
		
		
		
		System.out.println("No more legal moves available, the game is over");
		System.out.println("Final Score: X = " + score_x + " : O = " + score_o);
		if(score_x > score_o){
			System.out.println("Player X won");
		}
		else if(score_o > score_x){
			System.out.println("Player O won");
		}
		else if(score_o == score_x){
			System.out.println("Its a tie, no one wins");
		}
		Player.closeScanner();
		
	}
	
	

}
