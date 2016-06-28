package ai;

import beans.Node;

/**
 * This class is developed to perform the minimax search algorithm.
 * 
 * @author Reina Villanueva
 * 
 */

public class Search {
	
	public static Node minimax(Node node, int depth, boolean maximizingPlayer) {
		
		double bestValue = -1;
		Node bestNode = null;
		
		if (depth == 0 || node.getChildren().isEmpty()) {
			//return node.getHeuristicValue();
			return node;
		}
		
		if (maximizingPlayer) {
			bestValue = Double.NEGATIVE_INFINITY;
			for (Node child : node.getChildren()) {
				double value = minimax(child, depth - 1, false).getHeuristicValue();
				bestValue = Math.max(bestValue, value);
				
				if (bestValue == value)
					bestNode = minimax(child, depth - 1, false);
			}
			return bestNode;
			
		} else {	// minimizing player
			bestValue = Double.POSITIVE_INFINITY;
			for (Node child : node.getChildren()) {
				double value = minimax(child, depth - 1, true).getHeuristicValue();
				bestValue = Math.min(bestValue, value);
				
				if (bestValue == value)
					bestNode = minimax(child, depth - 1, true);
			}
			return bestNode;
		}
	}
}
