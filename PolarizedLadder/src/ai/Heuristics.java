package ai;

import java.util.ArrayList;
import beans.Disc;
import beans.Node;

/**
 * This class is developed to perform functions to find the heuristic value of the given node.
 * 
 * @author Reina Villanueva
 * 
 */

public class Heuristics {
	
	private ArrayList<Disc> state;
	private Node n;
	
	public Heuristics(Node n) {
		this.n = n;
		state = n.getState();
	}
	
	public void calculateHeuristicValue() {
		
		double H1 = 0;
		double H2 = 0;
		double H = 0;
		
		LadderPatterns.initialize();
		
		// calculate H1
		H1 = calculateTotalWins(n);
		
		// calculate H2
		H2 = calculateWeights(n);

		// calculate heuristic
		H = H1 + H2;
		
		// set H to node
		n.setHeuristicValue(H);
	}
	
	private double calculateWeights(Node n) {
		
		double player = n.getLastPositionSet().getPlayer();
		
		double h2 = 0;
		double player1_H2 = 0;
		double player2_H2 = 0;
		
		boolean alert = false;
		
		for (Disc d: state) {
			if (d.getPlayer() == 1) {
				player1_H2 += d.getWeight();
				if ((player == 2) && d.getID() == n.getLastPositionSet().getID() && d.getWeight() >= 100000) {
					alert = true;
				}
			}
			
			if (d.getPlayer() == 2) {
				player2_H2 += d.getWeight();
				if ((player == 1) && d.getID() == n.getLastPositionSet().getID() && d.getWeight() >= 100000) {
					alert = true;
				}				
			}
		}
		
		h2 = player1_H2 - player2_H2;

		if (alert)
		{
			if (player == 2)
				h2 = Double.POSITIVE_INFINITY;
			else
				h2 = Double.NEGATIVE_INFINITY;
		}
		
		
		return h2;
	}
	
	
	private double calculateTotalWins(Node n) {
		
		double h1 = 0;
		double player1_H1 = 0;
		double player2_H1 = 0;
		
		for (Disc d: state) {
			if (d.getPlayer() == 1) {
				player1_H1 += checkNumOfPossibleWins(d, 1);
			}
			
			if (d.getPlayer() == 2) {
				player2_H1 += checkNumOfPossibleWins(d, 2);
			}
		}
		
		h1 = player1_H1 - player2_H1;
		
		return h1;
	}
	
	private int checkNumOfPossibleWins(Disc d, int player) {
		
		int opponent = (player == 1? 2: 1);
		int totalWins = 0;
		
		
		if (checkWinType1(d, opponent)) {			
			totalWins++;
		}
		
		if (checkWinType2(d, opponent)) {			
			totalWins++;
		}
		
		if (checkWinType3(d, opponent)) {			
			totalWins++;
		}

		if (checkWinType4(d, opponent)) {			
			totalWins++;
		}

		if (checkWinType5(d, opponent)) {			
			totalWins++;
		}

		if (checkWinType6(d, opponent)) {			
			totalWins++;
		}

		if (checkWinType7(d, opponent)) {			
			totalWins++;
		}

		if (checkWinType8(d, opponent)) {			
			totalWins++;
		}

		if (checkWinType9(d, opponent)) {			
			totalWins++;
		}

		if (checkWinType10(d, opponent)) {			
			totalWins++;
		}
		
		return totalWins;
	}
	
	private boolean checkWin(Disc d, ArrayList<Disc> ladder, int opponent, int winType) {
		
		boolean win = false;
		
		Disc d1 = ladder.get(0);
		Disc d2 = ladder.get(1);
		Disc d3 = ladder.get(2);
		Disc d4 = ladder.get(3);
		Disc d5 = ladder.get(4);
		
		if ((d1.getPosition() != null && d1.getPlayer() != opponent)
				&& (d2.getPosition() != null && d2.getPlayer() != opponent)
				&& (d3.getPosition() != null && d3.getPlayer() != opponent)
				&& (d4.getPosition() != null && d4.getPlayer() != opponent)
				&& (d5.getPosition() != null && d5.getPlayer() != opponent)) {
			
			win = !(isNeutralized(opponent, d.getID(), winType));
			
			if (win)
			{
				// update the weights of the discs in the state
				LadderPatterns.updateWeights(d, ladder, state);
				
				// check if the ladder is already counted as a win
				boolean found = LadderPatterns.checkWin(ladder);

				// if the ladder is not found in the list of wins, count the win
				if (!found) {
					win = true;
				} else {
					win = false;
				}
				
				//System.out.println("WIN in " + winType + ": found " + found + " : win " + win);

			}
		}		
		return win;
	}

	private boolean isWithinRange(int lowerBound, int upperBound) {
		
		boolean val = false;
		
		if (lowerBound >= 0 && upperBound < state.size())
			val = true;
		
		return val;
	}
	
	private boolean checkWinType1(Disc d, int opponent) {
		
		boolean win = false;
		
		if (isWithinRange(d.getID() - 1, d.getID() - 1 + 15 - 1 + 15))
		{			
			Disc left = state.get(d.getID() - 1);						// -1
			Disc leftUpLeft = state.get(d.getID() - 1 + 15 - 1);		// 13
			Disc leftUp = state.get(d.getID() - 1 + 15);				// 14
			Disc leftUpLeftUp = state.get(d.getID() - 1 + 15 - 1 + 15);	// 28
			
			ArrayList<Disc> ladder = new ArrayList<Disc>();
			ladder.add(left);
			ladder.add(d);
			ladder.add(leftUpLeft);
			ladder.add(leftUp);
			ladder.add(leftUpLeftUp);
			
			win = checkWin(d, ladder, opponent, 1);
		}

		return win;
	}	
	
	private boolean checkWinType2(Disc d, int opponent) {
		
		boolean win = false;
	
		if (isWithinRange(d.getID() - 15, d.getID() - 1 + 15))
		{	
			Disc down = state.get(d.getID() - 15);			// -15
			Disc downRight = state.get(d.getID() - 15 + 1);	// -14
			Disc left = state.get(d.getID() - 1);			// -1
			Disc leftUp = state.get(d.getID() - 1 + 15);	// 14
			
			ArrayList<Disc> ladder = new ArrayList<Disc>();
			ladder.add(down);
			ladder.add(downRight);
			ladder.add(left);
			ladder.add(d);
			ladder.add(leftUp);
			
			win = checkWin(d, ladder, opponent, 2);
		}

		return win;
	}
	
	private boolean checkWinType3(Disc d, int opponent) {
		
		boolean win = false;
		
		if (isWithinRange(d.getID() - 1 - 15 - 1, d.getID() + 15))
		{
			Disc leftDownLeft = state.get(d.getID() - 1 - 15 - 1);	// -17
			Disc leftDown = state.get(d.getID() - 1 - 15);			// -16
			Disc left = state.get(d.getID() - 1);					// -1
			Disc up = state.get(d.getID() + 15);					// 15
			
			ArrayList<Disc> ladder = new ArrayList<Disc>();
			ladder.add(leftDownLeft);
			ladder.add(leftDown);
			ladder.add(left);
			ladder.add(d);
			ladder.add(up);
			
			win = checkWin(d, ladder, opponent, 3);
		}

		return win;
	}

	private boolean checkWinType4(Disc d, int opponent) {
		
		boolean win = false;
		
		if (isWithinRange(d.getID() - 1, d.getID() + 15 + 1 + 15))
		{			
			Disc left = state.get(d.getID() - 1);					// -1
			Disc up = state.get(d.getID() + 15);					// 15
			Disc upRight = state.get(d.getID() + 15 + 1);			// 16
			Disc upRightUp = state.get(d.getID() + 15 + 1 + 15);	// 31 
			
			ArrayList<Disc> ladder = new ArrayList<Disc>();
			ladder.add(left);
			ladder.add(d);
			ladder.add(up);
			ladder.add(upRight);
			ladder.add(upRightUp);
			
			win = checkWin(d, ladder, opponent, 4);
		}

		return win;
	}

	private boolean checkWinType5(Disc d, int opponent) {
		
		boolean win = false;

		if (isWithinRange(d.getID() + 1, d.getID() + 1 + 15 + 1 + 15))
		{			
			Disc right = state.get(d.getID() + 1);							// 1
			Disc rightUp = state.get(d.getID() + 1 + 15);					// 16
			Disc rightUpRight = state.get(d.getID() + 1 + 15 + 1);			// 17
			Disc rightUpRightUp = state.get(d.getID() + 1 + 15 + 1 + 15);	// 32 
			
			ArrayList<Disc> ladder = new ArrayList<Disc>();
			ladder.add(d);
			ladder.add(right);
			ladder.add(rightUp);
			ladder.add(rightUpRight);
			ladder.add(rightUpRightUp);
			
			win = checkWin(d, ladder, opponent, 5);
		}

		return win;
	}

	private boolean checkWinType6(Disc d, int opponent) {
		
		boolean win = false;
		
		if (isWithinRange(d.getID() - 15 - 1, d.getID() + 1 + 15))
		{			
			Disc downLeft = state.get(d.getID() - 15 - 1);	// -16
			Disc down = state.get(d.getID() - 15);			// -15
			Disc right = state.get(d.getID() + 1);			// 1
			Disc rightUp = state.get(d.getID() + 1 + 15);	// 16 
			
			ArrayList<Disc> ladder = new ArrayList<Disc>();
			ladder.add(downLeft);
			ladder.add(down);
			ladder.add(d);
			ladder.add(right);
			ladder.add(rightUp);
			
			win = checkWin(d, ladder, opponent, 6);
		}

		return win;
	}

	private boolean checkWinType7(Disc d, int opponent) {
		
		boolean win = false;
		
		if (isWithinRange(d.getID() + 1 - 15, d.getID() + 15))
		{			
			Disc rightDown = state.get(d.getID() + 1 - 15);				// -14
			Disc rightDownRight = state.get(d.getID() + 1 - 15 + 1);	// -13
			Disc right = state.get(d.getID() + 1);						// 1
			Disc up = state.get(d.getID() + 15);						// 15
			
			ArrayList<Disc> ladder = new ArrayList<Disc>();
			ladder.add(rightDown);
			ladder.add(rightDownRight);
			ladder.add(d);
			ladder.add(right);
			ladder.add(up);
			
			win = checkWin(d, ladder, opponent, 7);
		}

		return win;
	}
	
	private boolean checkWinType8(Disc d, int opponent) {
		
		boolean win = false;
		
		if (isWithinRange(d.getID() + 1, d.getID() + 15 - 1 + 15))
		{			
			Disc right = state.get(d.getID() + 1);				// 1
			Disc up = state.get(d.getID() + 15);				// 15
			Disc upLeft = state.get(d.getID() + 15 - 1);		// 14
			Disc upLeftUp = state.get(d.getID() + 15 - 1 + 15);	// 29
			
			ArrayList<Disc> ladder = new ArrayList<Disc>();
			ladder.add(d);
			ladder.add(right);
			ladder.add(upLeft);
			ladder.add(up);
			ladder.add(upLeftUp);
			
			win = checkWin(d, ladder, opponent, 8);
		}

		return win;
	}

	private boolean checkWinType9(Disc d, int opponent) {
		
		boolean win = false;

		if (isWithinRange(d.getID() - 15 - 1 - 15 - 1, d.getID() - 15))
		{			
			Disc downLeftDownLeft = state.get(d.getID() - 15 - 1 - 15 - 1);	// -32
			Disc downLeftDown = state.get(d.getID() - 15 - 1 - 15);			// -31
			Disc downLeft = state.get(d.getID() - 15 - 1);					// -16
			Disc down = state.get(d.getID() - 15);							// -15
			
			ArrayList<Disc> ladder = new ArrayList<Disc>();
			ladder.add(downLeftDownLeft);
			ladder.add(downLeftDown);
			ladder.add(downLeft);
			ladder.add(down);
			ladder.add(d);
			
			win = checkWin(d, ladder, opponent, 9);
		}


		return win;
	}

	private boolean checkWinType10(Disc d, int opponent) {
		
		boolean win = false;
		
		if (isWithinRange(d.getID() - 15 + 1 - 15, d.getID() - 15 + 1))
		{			
			Disc downRightDown = state.get(d.getID() - 15 + 1 - 15);			// -29
			Disc downRightDownRight = state.get(d.getID() - 15 + 1 - 15 + 1);	// -28
			Disc down = state.get(d.getID() - 15);								// -15
			Disc downRight = state.get(d.getID() - 15 + 1);						// -14
			
			ArrayList<Disc> ladder = new ArrayList<Disc>();
			ladder.add(downRightDown);
			ladder.add(downRightDownRight);
			ladder.add(down);
			ladder.add(downRight);
			ladder.add(d);
			
			win = checkWin(d, ladder, opponent, 10);
		}

		return win;
	}
	
	private boolean isNeutralized(int opponent, int moveID, int winType) {
		
		boolean val = false;
		
		switch (winType)
		{
			// winType1
			case 1:
					if ((state.get(moveID - 1 - 1) != null && state.get(moveID - 1 - 1).getPlayer() == opponent)			// left-left
						&& (state.get(moveID + 15 + 15) != null && state.get(moveID + 15 + 15).getPlayer() == opponent))		// up-up
					{
						val = true;
					}
					break;
			
			// winType2
			case 2:
					if ((state.get(moveID - 15 - 1) != null && state.get(moveID - 15 - 1).getPlayer() == opponent)			// down-left
						&& (state.get(moveID + 1 + 15) != null && state.get(moveID + 1 + 15).getPlayer() == opponent))		// right-up
					{
						val = true;
					}
					break;

			// winType3
			case 3:				
					if ((state.get(moveID - 15) != null && state.get(moveID - 15).getPlayer() == opponent)						// down
						&& (state.get(moveID - 1 - 1 + 15) != null && state.get(moveID - 1 - 1 + 15).getPlayer() == opponent))	// left-left-up
					{
						val = true;
					}
					break;

			// winType4
			case 4:
					if ((state.get(moveID + 1) != null && state.get(moveID + 1).getPlayer() == opponent)						// right
						&& (state.get(moveID - 1 + 15 + 15) != null && state.get(moveID - 1 + 15 + 15).getPlayer() == opponent))// left-up-up
					{
						val = true;
					}
					break;

			// winType5
			case 5:
					if ((state.get(moveID + 1 + 1) != null && state.get(moveID + 1 + 1).getPlayer() == opponent)			// right-right
						&& (state.get(moveID + 15 + 15) != null && state.get(moveID + 15 + 15).getPlayer() == opponent))	// up-up
					{
						val = true;
					}
					break;
					
			// winType6
			case 6:
					if ((state.get(moveID - 15 + 1) != null && state.get(moveID - 15 + 1).getPlayer() == opponent)			// down-right
						&& (state.get(moveID - 1 + 15) != null && state.get(moveID - 1 + 15).getPlayer() == opponent))		// left-up
					{
						val = true;
					}
					break;

			// winType7
			case 7:
					if ((state.get(moveID - 15) != null && state.get(moveID - 15).getPlayer() == opponent)						// down
						&& (state.get(moveID + 1 + 1 + 15) != null && state.get(moveID + 1 + 1 + 15).getPlayer() == opponent))	// right-right-up
					{
						val = true;
					}
					break;

			// winType8
			case 8:
					if ((state.get(moveID - 1) != null && state.get(moveID - 1).getPlayer() == opponent)							// left
						&& (state.get(moveID + 1 + 15 + 15) != null && state.get(moveID + 1 + 15 + 15).getPlayer() == opponent))	// right-up-up
					{
						val = true;
					}
					break;
		
			// winType9
			case 9:
					if ((state.get(moveID - 1 - 1) != null && state.get(moveID - 1 - 1).getPlayer() == opponent)			// left-left
						&& (state.get(moveID - 15 - 15) != null && state.get(moveID - 15 - 15).getPlayer() == opponent))	// down-down
					{
						val = true;
					}
					break;

			// winType10
			case 10:
					if ((state.get(moveID + 1 + 1) != null && state.get(moveID + 1 + 1).getPlayer() == opponent)			// right-right
						&& (state.get(moveID - 15 - 15) != null && state.get(moveID - 15 - 15).getPlayer() == opponent))	// down-down
					{
						val = true;
					}
					break;

			default:
					break;
		}
		
		return val;
	}

	
//	private static boolean hasLeft(Disc d, int player) {
//
//		boolean val = false;
//
//		Disc leftDisc = state.get(d.getID() - 1);		
//		if (leftDisc != null && leftDisc.getPlayer() == player)
//			val = true;
//		
//		return val;
//	}
//	
//	private static boolean hasRight(Disc d, int player) {
//		boolean val = false;
//		
//		Disc rightDisc = state.get(d.getID() + 1);
//		if (rightDisc != null && rightDisc.getPlayer() == player)
//			val = true;
//		
//		return val;
//	}
//	
//	private static boolean hasBottom(Disc d, int player) {
//		boolean val = false;
//		
//		Disc bottomDisc = state.get(d.getID() - 15);
//		if (bottomDisc != null && bottomDisc.getPlayer() == player)
//			val = true;
//		
//		return val;
//	}

}
