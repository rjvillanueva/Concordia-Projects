package beans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * This class is developed to instantiate and manipulate the contents of the current state of the board.
 * 
 * @author Reina Villanueva
 *
 */

public class Board {

	private ArrayList<Disc> board;						// list of all the discs
	private ArrayList<Disc> validPositions;				// list of available positions (ie. "A1", "B1", "B2", etc.)	
	private ArrayList<Disc> player1Discs;				// list of Player 1 discs
	private ArrayList<Disc> player2Discs;				// list of Player 2 discs
	private String boardStr;							// String representation of the board

	
	/**
	 * Instantiates a new empty board by creating a list of Disc objects with properties set according to their id and positions.
	 */
	public Board()
	{
		board = new ArrayList<Disc>();
		validPositions = new ArrayList<Disc>();
		player1Discs = new ArrayList<Disc>();
		player2Discs = new ArrayList<Disc>();
		
		for (int id = 0; id < 135; id++)
		{
			// row 1 of available positions
			if (id >= 16 && id <= 28) {
								
				int offset = id - 16;
				char letter = (char)(65 + offset);
				String position = letter + "" + 1;
				
				Disc disc = new Disc(id, 0, position, ".", 0, 0);
				
				board.add(disc);
				validPositions.add(disc);
				
			// row 2 of available positions
			} else if (id >= 32 && id <= 42) {
				
				int offset = id - 32;
				char letter = (char)(66 + offset);
				String position = letter + "" + 2;
				
				Disc disc = new Disc(id, 0, position, ".", 0, 0);
				
				board.add(disc);
				validPositions.add(disc);
					
			// row 3 of available positions
			} else if (id >= 48 && id <= 56) {
				
				int offset = id - 48;
				char letter = (char)(67 + offset);
				String position = letter + "" + 3;
				
				Disc disc = new Disc(id, 0, position, ".", 0, 0);
				
				board.add(disc);
				validPositions.add(disc);
	
			// row 4 of available positions
			} else if (id >= 64 && id <= 70) {
				
				int offset = id - 64;
				char letter = (char)(68 + offset);
				String position = letter + "" + 4;
				
				Disc disc = new Disc(id, 0, position, ".", 0, 0);
				
				board.add(disc);
				validPositions.add(disc);
	
			// row 5 of available positions
			} else if (id >= 80 && id <= 84) {
				
				int offset = id - 80;
				char letter = (char)(69 + offset);
				String position = letter + "" + 5;
				
				Disc disc = new Disc(id, 0, position, ".", 0, 0);
				
				board.add(disc);
				validPositions.add(disc);
	
			// row 6 of available positions
			} else if (id >= 96 && id <= 98) {
				
				int offset = id - 96;
				char letter = (char)(70 + offset);
				String position = letter + "" + 6;
				
				Disc disc = new Disc(id, 0, position, ".", 0, 0);
				
				board.add(disc);
				validPositions.add(disc);
	
			// row 7 of available positions
			} else if (id == 112) {
				
				int offset = id - 112;
				char letter = (char)(71 + offset);
				String position = letter + "" + 7;
				
				Disc disc = new Disc(id, 0, position, ".", 0, 0);
				
				board.add(disc);
				validPositions.add(disc);
	
			// unavailable positions
			} else {
				
				Disc disc = new Disc(id, -1, null, null, -1, -1);
				board.add(disc);
				
			}
		}
		
		boardStr = "\n7	       " + board.get(112).getValue() + "\n"
				+ "	      /|\\\n"
				+ "6	     " + board.get(96).getValue() + "_" + board.get(97).getValue() + "_" + board.get(98).getValue()+ "\n"
				+ "	    /| | |\\\n"
				+ "5	   " + board.get(80).getValue() + "_" + board.get(81).getValue() + "_" + board.get(82).getValue() + "_" + board.get(83).getValue() + "_" + board.get(84).getValue() + "\n"
				+ "	  /| | | | |\\\n"
				+ "4	 " + board.get(64).getValue() + "_" + board.get(65).getValue() + "_" + board.get(66).getValue() + "_" + board.get(67).getValue() + "_" + board.get(68).getValue() + "_" + board.get(69).getValue() + "_" + board.get(70).getValue() + "\n"
				+ "	/| | | | | | |\\\n"
				+ "3      " + board.get(48).getValue() + "_" + board.get(49).getValue() + "_" + board.get(50).getValue() + "_" + board.get(51).getValue() + "_" + board.get(52).getValue() + "_" + board.get(53).getValue() + "_" + board.get(54).getValue() + "_" + board.get(55).getValue() + "_" + board.get(56).getValue() + "\n"
				+ "      /| | | | | | | | |\\\n"
				+ "2    " + board.get(32).getValue() + "_" + board.get(33).getValue() + "_" + board.get(34).getValue() + "_" + board.get(35).getValue() + "_" + board.get(36).getValue() + "_" + board.get(37).getValue() + "_" + board.get(38).getValue() + "_" + board.get(39).getValue() + "_" + board.get(40).getValue() + "_" + board.get(41).getValue() + "_" + board.get(42).getValue() + "\n"
				+ "    /| | | | | | | | | | |\\\n"
				+ "1  " + board.get(16).getValue() + "_" + board.get(17).getValue() + "_" + board.get(18).getValue() + "_" + board.get(19).getValue() + "_" + board.get(20).getValue() + "_" + board.get(21).getValue() + "_" + board.get(22).getValue() + "_" + board.get(23).getValue() + "_" + board.get(24).getValue() + "_" + board.get(25).getValue() + "_" + board.get(26).getValue() + "_" + board.get(27).getValue() + "_" + board.get(28).getValue() + "\n"
				+ "   A B C D E F G H I J K L M\n";

		
		System.out.println(boardStr);
	}
	
	/**
	 * Displays the current state of the grid.
	 */
	public void refresh()
	{	
		boardStr = "\n7	       " + board.get(112).getValue() + "\n"
				+ "	      /|\\\n"
				+ "6	     " + board.get(96).getValue() + "_" + board.get(97).getValue() + "_" + board.get(98).getValue()+ "\n"
				+ "	    /| | |\\\n"
				+ "5	   " + board.get(80).getValue() + "_" + board.get(81).getValue() + "_" + board.get(82).getValue() + "_" + board.get(83).getValue() + "_" + board.get(84).getValue() + "\n"
				+ "	  /| | | | |\\\n"
				+ "4	 " + board.get(64).getValue() + "_" + board.get(65).getValue() + "_" + board.get(66).getValue() + "_" + board.get(67).getValue() + "_" + board.get(68).getValue() + "_" + board.get(69).getValue() + "_" + board.get(70).getValue() + "\n"
				+ "	/| | | | | | |\\\n"
				+ "3      " + board.get(48).getValue() + "_" + board.get(49).getValue() + "_" + board.get(50).getValue() + "_" + board.get(51).getValue() + "_" + board.get(52).getValue() + "_" + board.get(53).getValue() + "_" + board.get(54).getValue() + "_" + board.get(55).getValue() + "_" + board.get(56).getValue() + "\n"
				+ "      /| | | | | | | | |\\\n"
				+ "2    " + board.get(32).getValue() + "_" + board.get(33).getValue() + "_" + board.get(34).getValue() + "_" + board.get(35).getValue() + "_" + board.get(36).getValue() + "_" + board.get(37).getValue() + "_" + board.get(38).getValue() + "_" + board.get(39).getValue() + "_" + board.get(40).getValue() + "_" + board.get(41).getValue() + "_" + board.get(42).getValue() + "\n"
				+ "    /| | | | | | | | | | |\\\n"
				+ "1  " + board.get(16).getValue() + "_" + board.get(17).getValue() + "_" + board.get(18).getValue() + "_" + board.get(19).getValue() + "_" + board.get(20).getValue() + "_" + board.get(21).getValue() + "_" + board.get(22).getValue() + "_" + board.get(23).getValue() + "_" + board.get(24).getValue() + "_" + board.get(25).getValue() + "_" + board.get(26).getValue() + "_" + board.get(27).getValue() + "_" + board.get(28).getValue() + "\n"
				+ "   A B C D E F G H I J K L M\n";
		
		System.out.println(boardStr);
	}
	
	/**
	 * Execute the move of the player on the board, if the move is valid and available to be played.
	 * @param player
	 * @param move
	 * @throws IOException
	 */
	public void makeMove(int player, String move) throws IOException
	{
		// find the move's id
		int moveIndex = findID(move);
		
		// check if the move is available
		boolean moveAvailable = isAvailable(moveIndex);
		
		// if the move is invalid or unavailable,
		// keep asking for input until it is
		while (moveIndex == -1 || !moveAvailable)
		{
			if (moveIndex == -1) {
				System.out.print("Invalid move! Please enter another move:\n");				
			} else {
				System.out.print("Move already taken! Please enter another move:\n");
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));			
			
			move = br.readLine();
			
			moveIndex = findID(move);
			moveAvailable = isAvailable(moveIndex);
		}		
	
		// set the value and player properties of the move
		String token = (player == 1 ? "X" : "O");
		board.get(moveIndex).setValue(token);
		board.get(moveIndex).setPlayer(player);
	
		// add the new disc to the list of the player currently in play
		if (player == 1) {			
			player1Discs.add(board.get(moveIndex));
		} else {
			player2Discs.add(board.get(moveIndex));
		}
		
		refresh();
	}
	
	/**
	 * Checks if the move has not been played yet, thus making it available.
	 * @param moveIndex
	 * @return available
	 */
	private boolean isAvailable(int moveIndex) {
		
		boolean available = false;
		
		if (moveIndex == -1)
			return false;
		
		if (board.get(moveIndex).getPlayer() == 0)
			available = true;
		
		return available;
	}
	
	/**
	 * Checks if a move is valid by searching its index number within the list of valid positions.
	 * @param move
	 * @return
	 */
	private int findID(String move) {
		
		int moveIndex = -1;
		
		int index = 0;
		while (moveIndex == -1 && index < validPositions.size())
		{
			if (move.equalsIgnoreCase(validPositions.get(index).getPosition()))
			{
				moveIndex = validPositions.get(index).getID();
			}
			
			index++;
		}
		
		return moveIndex;
	}

	/**
	 * Checks if the last move made by the current player is a winning move.
	 * @param player
	 * @param move
	 * @return
	 */
	public int checkForWinner(int player, String move) {
		
		int winner = 0;
		
		int numOfDiscs;
		numOfDiscs = (player == 1 ? player1Discs.size() : player2Discs.size());
		
		int moveID = findID(move);
		
		// a winning ladder needs at least 5 connected discs for there to have any possibility of winning
		if (numOfDiscs >= 5)
		{
			// if the move has a left allied disc, check for these win types
			if (hasLeft(player, moveID))
			{
				if (checkWinType1(player, moveID)
						|| checkWinType2(player, moveID)
						|| checkWinType3(player, moveID)
						|| checkWinType4(player, moveID))
				{
					winner = player;		// winner!
				}
			}
			
			// if there is still no winner
			if (winner == 0)
			{	// if the move has a right allied disc, check for these win types
				if (hasRight(player, moveID))
				{
					if (checkWinType5(player, moveID)
							|| checkWinType6(player, moveID)
							|| checkWinType7(player, moveID)
							|| checkWinType8(player, moveID))
					{
						winner = player;	// winner!
					}
				}
			}
			
			// if there is still no winner
			if (winner == 0)
			{	// if the move has a bottom allied disc, check for these win types
				if (hasBottom(player, moveID))
				{
					if (checkWinType9(player, moveID)
							|| checkWinType10(player, moveID))
					{
						winner = player;	// winner!
					}
				}
			}
		}
		
		return winner;
	}
	
	/**
	 * Checks if the "winning" ladder is neutralized.
	 * @param player
	 * @param moveID
	 * @param winType the calling checkWinType() method
	 * @return true if the "winning" ladder is neutralized, else false
	 */
	private boolean isNeutralized(int player, int moveID, int winType) {
		
		boolean val = false;
		
		int opponent = (player == 1? 2 : 1);
		
		switch (winType)
		{
			// winType1
			case 1:
					if (board.get(moveID - 1 - 1).getPlayer() == opponent			// left-left
						&& board.get(moveID + 15 + 15).getPlayer() == opponent)		// up-up
					{
						val = true;
					}
					break;
			
			// winType2
			case 2:
					if (board.get(moveID - 15 - 1).getPlayer() == opponent			// down-left
						&& board.get(moveID + 1 + 15).getPlayer() == opponent)		// right-up
					{
						val = true;
					}
					break;

			// winType3
			case 3:
					if (board.get(moveID - 15).getPlayer() == opponent				// down
						&& board.get(moveID - 1 - 1 + 15).getPlayer() == opponent)	// left-left-up
					{
						val = true;
					}
					break;

			// winType4
			case 4:
					if (board.get(moveID + 1).getPlayer() == opponent				// right
						&& board.get(moveID - 1 + 15 + 15).getPlayer() == opponent)	// left-up-up
					{
						val = true;
					}
					break;

			// winType5
			case 5:
					if (board.get(moveID + 1 + 1).getPlayer() == opponent			// right-right
						&& board.get(moveID + 15 + 15).getPlayer() == opponent)		// up-up
					{
						val = true;
					}
					break;
					
			// winType6
			case 6:
					if (board.get(moveID - 15 + 1).getPlayer() == opponent			// down-right
						&& board.get(moveID - 1 + 15).getPlayer() == opponent)		// left-up
					{
						val = true;
					}
					break;

			// winType7
			case 7:
					if (board.get(moveID - 15).getPlayer() == opponent				// down
						&& board.get(moveID + 1 + 1 + 15).getPlayer() == opponent)	// right-right-up
					{
						val = true;
					}
					break;

			// winType8
			case 8:
					if (board.get(moveID - 1).getPlayer() == opponent				// left
						&& board.get(moveID + 1 + 15 + 15).getPlayer() == opponent)	// right-up-up
					{
						val = true;
					}
					break;
		
			// winType9
			case 9:
					if (board.get(moveID - 1 - 1).getPlayer() == opponent			// left-left
						&& board.get(moveID - 15 - 15).getPlayer() == opponent)		// down-down
					{
						val = true;
					}
					break;

			// winType10
			case 10:
					if (board.get(moveID + 1 + 1).getPlayer() == opponent			// right-right
						&& board.get(moveID - 15 - 15).getPlayer() == opponent)		// down-down
					{
						val = true;
					}
					break;

			default:
					break;
		}
		
		return val;
	}
	
	/**
	 * Searches for the position name of the move.
	 * @param moveID
	 * @return position name of the move
	 */
	private String findPosition(int moveID) {
		
		String val = null;
		
		int index = 0;
		while (val == null & index < validPositions.size())
		{
			if (moveID == validPositions.get(index).getID())
			{
				val = validPositions.get(index).getPosition();
			}
			
			index++;
		}
			
		return val;
	}
	
	/**
	 * Checks if the last move is a win type 1.
	 * @param player
	 * @param moveID
	 * @return true if the move is a winning ladder, else false
	 */
	private boolean checkWinType1(int player, int moveID) {
		
		boolean win = false;
		
		if ((board.get(moveID - 1).getPlayer() == player)					// left
			&& (board.get(moveID - 1 + 15).getPlayer() == player)			// left + up
			&& (board.get(moveID - 1 + 15 - 1).getPlayer() == player)		// left + up + left
			&& (board.get(moveID - 1 + 15 - 1 + 15).getPlayer() == player))	// left + up + left + up
			{
				// the win is good if it is NOT neutralized
				win = !(isNeutralized(player, moveID, 1));
				
				String start = findPosition(moveID);
				String end = findPosition(moveID - 1 + 15 - 1 + 15);

				if (!win)
					System.out.println("Ladder " + start + " to " + end + " is neutralized!\n");
				else 
					System.out.println("Win type 1 - " + start + " to " + end);
			}
		
		return win;
	}
	
	/**
	 * Checks if the last move is a win type 2.
	 * @param player
	 * @param moveID
	 * @return true if the move is a winning ladder, else false
	 */
	private boolean checkWinType2(int player, int moveID) {
		
		boolean win = false;
		
		if ((board.get(moveID - 1).getPlayer() == player)					// left
			&& (board.get(moveID - 1 + 15).getPlayer() == player)			// left + up
			&& (board.get(moveID - 15).getPlayer() == player)				// down
			&& (board.get(moveID - 15 + 1).getPlayer() == player))			// down + right
			{
				// the win is good if it is NOT neutralized
				win = !(isNeutralized(player, moveID, 2));
				
				String start = findPosition(moveID - 1 + 15);
				String end = findPosition(moveID - 15 + 1);

				if (!win)
					System.out.println("Ladder " + start + " to " + end + " is neutralized!");
				else
					System.out.println("Win type 2 - " + start + " to " + end);
			}
		
		return win;
	}
	
	
	/**
	 * Checks if the last move is a win type 3.
	 * @param player
	 * @param moveID
	 * @return true if the move is a winning ladder, else false
	 */
	private boolean checkWinType3(int player, int moveID) {
		
		boolean win = false;
		
		if ((board.get(moveID + 15).getPlayer() == player)					// up
			&& (board.get(moveID - 1).getPlayer() == player)					// left
			&& (board.get(moveID - 1 - 15).getPlayer() == player)			// left + down
			&& (board.get(moveID - 1 - 15 - 1).getPlayer() == player))		// left + down + left
			{
				// the win is good if it is NOT neutralized
				win = !(isNeutralized(player, moveID, 3));
				
				String start = findPosition(moveID + 15);
				String end = findPosition(moveID - 1 - 15 - 1);

				if (!win)
					System.out.println("Ladder " + start + " to " + end + " is neutralized!");
				else
					System.out.println("Win type 3 - " + start + " to " + end);
			}
		
		return win;
	}

	/**
	 * Checks if the last move is a win type 4.
	 * @param player
	 * @param moveID
	 * @return true if the move is a winning ladder, else false
	 */
	private boolean checkWinType4(int player, int moveID) {
		
		boolean win = false;
		
		if ((board.get(moveID - 1).getPlayer() == player)					// left
			&& (board.get(moveID + 15).getPlayer() == player)				// up
			&& (board.get(moveID + 15 + 1).getPlayer() == player)			// up + right
			&& (board.get(moveID + 15 + 1 + 15).getPlayer() == player))		// up + right + up
			{
				// the win is good if it is NOT neutralized
				win = !(isNeutralized(player, moveID, 4));
				
				String start = findPosition(moveID - 1);
				String end = findPosition(moveID + 15 + 1 + 15);
				
				if (!win)
					System.out.println("Ladder " + start + " to " + end + " is neutralized!");
				else
					System.out.println("Win type 4 - " + start + " to " + end);
			}
		
		return win;
	}
	
	/**
	 * Checks if the last move is a win type 5.
	 * @param player
	 * @param moveID
	 * @return true if the move is a winning ladder, else false
	 */
	private boolean checkWinType5(int player, int moveID) {
		
		boolean win = false;
		
		if ((board.get(moveID + 1).getPlayer() == player)					// right
			&& (board.get(moveID + 1 + 15).getPlayer() == player)			// right + up
			&& (board.get(moveID + 1 + 15 + 1).getPlayer() == player)		// right + up + right
			&& (board.get(moveID + 1 + 15 + 1 + 15).getPlayer() == player))	// right + up + right + up
			{
				// the win is good if it is NOT neutralized
				win = !(isNeutralized(player, moveID, 5));
				
				String start = findPosition(moveID);
				String end = findPosition(moveID  + 1 + 15 + 1 + 15);
				
				if (!win)
					System.out.println("Ladder " + start + " to " + end + " is neutralized!");
				else
					System.out.println("Win type 5 - " + start + " to " + end);
			}
		
		return win;
	}
	
	/**
	 * Checks if the last move is a win type 6.
	 * @param player
	 * @param moveID
	 * @return true if the move is a winning ladder, else false
	 */
	private boolean checkWinType6(int player, int moveID) {
		
		boolean win = false;
		
		if ((board.get(moveID - 15).getPlayer() == player)					// down
			&& (board.get(moveID - 15 - 1).getPlayer() == player)			// down + left
			&& (board.get(moveID + 1).getPlayer() == player)				// right
			&& (board.get(moveID + 1 + 15).getPlayer() == player))			// right + up
			{
				// the win is good if it is NOT neutralized
				win = !(isNeutralized(player, moveID, 6));
				
				String start = findPosition(moveID - 15 - 1);
				String end = findPosition(moveID  + 1 + 15);
				
				if (!win)
					System.out.println("Ladder " + start + " to " + end + " is neutralized!");
				else
					System.out.println("Win type 6 - " + start + " to " + end);
			}
		
		return win;
	}
	
	/**
	 * Checks if the last move is a win type 7.
	 * @param player
	 * @param moveID
	 * @return true if the move is a winning ladder, else false
	 */
	private boolean checkWinType7(int player, int moveID) {
		
		boolean win = false;
		
		if ((board.get(moveID + 15).getPlayer() == player)					// up
			&& (board.get(moveID + 1).getPlayer() == player)				// right
			&& (board.get(moveID + 1 - 15).getPlayer() == player)			// right + down
			&& (board.get(moveID + 1 - 15 + 1).getPlayer() == player))		// right + down + right
			{
				// the win is good if it is NOT neutralized
				win = !(isNeutralized(player, moveID, 7));
				
				String start = findPosition(moveID + 15);
				String end = findPosition(moveID  + 1 - 15 + 1);
				
				if (!win)
					System.out.println("Ladder " + start + " to " + end + " is neutralized!");
				else
					System.out.println("Win type 7 - " + start + " to " + end);
			}
		
		return win;
	}

	/**
	 * Checks if the last move is a win type 8.
	 * @param player
	 * @param moveID
	 * @return true if the move is a winning ladder, else false
	 */
	private boolean checkWinType8(int player, int moveID) {
		
		boolean win = false;
		
		if ((board.get(moveID + 1).getPlayer() == player)					// right
			&& (board.get(moveID + 15).getPlayer() == player)				// up
			&& (board.get(moveID + 15 - 1).getPlayer() == player)			// up + left
			&& (board.get(moveID + 15 - 1 + 15).getPlayer() == player))		// up + left + up
			{
				// the win is good if it is NOT neutralized
				win = !(isNeutralized(player, moveID, 8));
				
				String start = findPosition(moveID + 1);
				String end = findPosition(moveID + 15 - 1 + 15);
				
				if (!win)
					System.out.println("Ladder " + start + " to " + end + " is neutralized!");
				else
					System.out.println("Win type 8 - " + start + " to " + end);
			}
		
		return win;
	}

	/**
	 * Checks if the last move is a win type 9.
	 * @param player
	 * @param moveID
	 * @return true if the move is a winning ladder, else false
	 */
	private boolean checkWinType9(int player, int moveID) {
		
		boolean win = false;
		
		if ((board.get(moveID - 15).getPlayer() == player)					// down
			&& (board.get(moveID - 15 - 1).getPlayer() == player)			// down + left
			&& (board.get(moveID - 15 - 1 - 15).getPlayer() == player)		// down + left
			&& (board.get(moveID - 15 - 1 - 15 - 1).getPlayer() == player))	// down + left + down + left
			{
				// the win is good if it is NOT neutralized
				win = !(isNeutralized(player, moveID, 9));
				
				String start = findPosition(moveID);
				String end = findPosition(moveID - 15 - 1 - 15 - 1);
				
				if (!win)
					System.out.println("Ladder " + start + " to " + end + " is neutralized!");
				else
					System.out.println("Win type 9 - " + start + " to " + end);
			}
		
		return win;
	}

	/**
	 * Checks if the last move is a win type 10.
	 * @param player
	 * @param moveID
	 * @return true if the move is a winning ladder, else false
	 */
	private boolean checkWinType10(int player, int moveID) {
		
		boolean win = false;
		
		if ((board.get(moveID - 15).getPlayer() == player)					// down
			&& (board.get(moveID - 15 + 1).getPlayer() == player)			// down + right
			&& (board.get(moveID - 15 + 1 - 15).getPlayer() == player)		// down + right + down
			&& (board.get(moveID - 15 + 1 - 15 + 1).getPlayer() == player))	// down + right + down + right
			{
				// the win is good if it is NOT neutralized
				win = !(isNeutralized(player, moveID, 10));
				
				String start = findPosition(moveID);
				String end = findPosition(moveID - 15 + 1 - 15 + 1);
				
				if (!win)
					System.out.println("Ladder " + start + " to " + end + " is neutralized!");
				else
					System.out.println("Win type 10 - " + start + " to " + end);
			}
		
		return win;
	}
	
	/**
	 * Checks if the move has a left allied disc.
	 * @param player
	 * @param moveID
	 * @return true if it does, false if it doesn't
	 */
	private boolean hasLeft(int player, int moveID) {

		boolean val = false;

		// left disc ID
		int leftDisc = moveID - 1;
		
		if (board.get(leftDisc).getPlayer() == player)
			val = true;
		
		return val;
	}
	
	/**
	 * Checks if the move has a right allied disc.
	 * @param player
	 * @param moveID
	 * @return true if it does, false if it doesn't
	 */
	private boolean hasRight(int player, int moveID) {
		boolean val = false;

		// right disc ID
		int rightDisc = moveID + 1;
		
		if (board.get(rightDisc).getPlayer() == player)
			val = true;
		
		return val;
	}
	
	/**
	 * Checks if the move has a bottom allied disc.
	 * @param player
	 * @param moveID
	 * @return true if it does, false if it doesn't
	 */
	private boolean hasBottom(int player, int moveID) {
		boolean val = false;

		// bottom disc ID
		int bottomDisc = moveID - 15;
		
		if (board.get(bottomDisc).getPlayer() == player)
			val = true;
		
		return val;
	}
	
	public ArrayList<Disc> getCurrentState() {

		
		return board;
	}
	
	
}
