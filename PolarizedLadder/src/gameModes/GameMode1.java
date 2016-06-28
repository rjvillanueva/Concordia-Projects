package gameModes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import beans.Board;

/**
 * This class is to instantiate Game Mode 1, where the game will be played by human vs human.
 * 
 * @author Reina Villanueva
 *
 */

public class GameMode1 {

	private Board board;
	private int player;
	private int numPositionsLeft;
	private int winner;
	
	/**
	 * Instantiates a GameMode1 instance.
	 */
	public GameMode1()
	{ }
	
	/**
	 * Starts the game in this mode.
	 * @throws IOException
	 */
	public void play() throws IOException
	{	
		System.out.println("-------------------");
		System.out.println("\nPlayer 1 (X) vs Player 2 (O)\n");

		board = new Board();
		player = 1;					// player 1 starts
		numPositionsLeft = 49;		// 49 playable positions in the grid
		winner = 0;
		
		// while there are still positions to play and there is no winner
		while (numPositionsLeft > 0 && winner == 0)
		{
			System.out.print("Player" + player + "'s turn!");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));			
			System.out.print("  Enter move:\n");
			
			// player enters a move
			String move = br.readLine();

			// grid executes the player's move
			board.makeMove(player, move);
			
			// grid checks for winner
			winner = board.checkForWinner(player, move);
			
			// other player's turn next
			player = (player == 1 ? 2: 1);
			
			numPositionsLeft--;
		}

		// check if the end-game is from a PLAYER WIN
		if (winner != 0)
		{
			System.out.println("Player" + (winner == 1 ? "1" : "2") + " wins!\n");
		}
		
		// check if the end-game is from a TIE
		if (numPositionsLeft == 0)
		{
			System.out.println("GAME TIE!\n");
		}
		
		// check if the user wants to restart the game
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));			
		System.out.print("Restart? Y/N\n");
		String restart = br.readLine();

		if (restart.equalsIgnoreCase("Y"))
		{
			play();		// restart
		}
	}
}
