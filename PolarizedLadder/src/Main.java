import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import gameModes.GameMode1;
import gameModes.GameMode2;
import gameModes.GameMode3;

/**
 * This is the main class of the game.
 * 
 * @author Reina Villanueva
 * 
 */

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		boolean quit = false;
		
		while (!quit) {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int mode = 0;
			
			System.out.println("\n\n"
					+ "       The Polarized Ladder Game\n"
					+ "------------------------------------\n");
			
			System.out.println("Select game mode:\n"
					+ "\t1 - Player 1 (X) vs Player 2 (O)\n"
					+ "\t2 - Player (X) vs AI (O)\n"
					+ "\t3 - AI (X) vs Player (O)\n"
					+ "\t4 - Quit");
			
			// get mode input from user
			try {
				mode = Integer.parseInt(br.readLine());
			} catch(NumberFormatException e) {
				System.err.println("Error! Please enter '1', '2', or '3'.\n");
			}
	
			// Player (X) vs Player (O)
			if (mode == 1) {
				GameMode1 game = new GameMode1();
				game.play();	
			}
			
			// Player (X) vs AI (O)
			if (mode == 2) {
				GameMode2 game = new GameMode2();
				game.play();
			}
			
			// AI (X) vs Player (O)
			if (mode == 3) {
				 GameMode3 game = new GameMode3();
				 game.play();
			}
			
			// Quit game
			if (mode == 4) {
				quit = true;
			}
			
		}

	}

}
