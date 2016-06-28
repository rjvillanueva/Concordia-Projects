package gameModes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ai.PlayerAI;
import beans.Board;

/**
 * This class is to instantiate Game Mode 3, where the game will be played by AI vs human.
 * 
 * @author Reina Villanueva
 *
 */

public class GameMode3 {

	private PlayerAI ai;
	private Board board;
	private int player;
	private int numPositionsLeft;
	private int winner;
	
	/**
	 * Instantiates a GameMode3 instance.
	 */
	public GameMode3()
	{ 
		ai = new PlayerAI("gamemode3");
	}
	
	/**
	 * Starts the game in this mode.
	 * @throws IOException
	 */
	public void play() throws IOException
	{	
		System.out.println("-------------------");
		System.out.println("\nAI (X) vs Player (O)\n");

		board = new Board();
		player = 1;					// AI starts
		numPositionsLeft = 49;		// 49 playable positions in the grid
		winner = 0;
		
		// while there are still positions to play and there is no winner
		while (numPositionsLeft > 0 && winner == 0)
		{	
			String move = null;
			
			// AI's turn (player 1)
			if (player == 1)
			{
				move = ai.decideNextMove(board);
			}
			
			// player's turn (player 2)
			else {				
				System.out.print("Player's turn!");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));			
				System.out.print("  Enter move:\n");
				
				// player enters a move
				move = br.readLine();
			}
			
			// grid executes the move
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
			String w = (winner == 1? "AI": "Player");
			System.out.println(w + " wins!\n");
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
