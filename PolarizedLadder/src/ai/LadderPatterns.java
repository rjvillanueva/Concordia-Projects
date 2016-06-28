package ai;

import java.util.ArrayList;
import beans.Disc;

/**
 * This class is developed to help the logic in the Heuristics class.
 * 
 * @author Reina Villanueva
 * 
 */

public class LadderPatterns {

	private static ArrayList<ArrayList<Disc>> winList = null;
	private static int[] weightList = {1, 10, 100, 5000, 100000};
	
	/**
	 * Initialize the list of wins.
	 */
	public static void initialize() {
		winList = new ArrayList<ArrayList<Disc>>();
	}
	
	/**
	 * Checks if the ladder already exists in the list of winning ladders.
	 * @param ladder the ladder being checked
	 */
	public static boolean checkWin(ArrayList<Disc> ladder) {
		
		boolean found = false;
		
		// search through the list of wins
		int ctr = 0;
		while (!found && ctr < winList.size()) {
			
			ArrayList<Disc> curr = winList.get(ctr);
			
			int i = 0;
			if (curr.get(i).getID() == ladder.get(i).getID())
				if (curr.get(i + 1).getID() == ladder.get(i + 1).getID())
					if (curr.get(i + 2).getID() == ladder.get(i + 2).getID())
						if (curr.get(i + 3).getID() == ladder.get(i + 3).getID())
							if (curr.get(i + 4).getID() == ladder.get(i + 4).getID()) 
							{
								// the ladder already exists in the list of wins
								found = true;
							}
			ctr++;
		}
		
		// the ladder doesn't exist in the list of wins yet, so add it
		if (!found) {
			winList.add(ladder);
		}
		
		return found;
	}
	
	public static void updateWeights(Disc d, ArrayList<Disc> ladder, ArrayList<Disc> state) {
		
		int player = d.getPlayer();
		
		int match = 0;
		for (int i = 0; i < ladder.size(); i++) {
			if (ladder.get(i).getPlayer() == player) {
				match++;
			}
		}
		
		int weight = d.getWeight();
		d.setWeight(weight + weightList[match - 1]);
	}
}
