package ai;

import java.util.ArrayList;
import beans.Board;
import beans.Disc;
import beans.Node;

/**
 * This class is developed to instantiate the AI.
 * 
 * @author Reina Villanueva
 *
 */

public class PlayerAI {
	
	private String move;
	private int player;
	private int opponent;
	private int depth = 2;
	private boolean maximizing;
	private ArrayList<Disc> boardState;
	private ArrayList<Disc> openPositions;
	private Tree tree;
	
	public PlayerAI(String mode) {
		
		if (mode.equalsIgnoreCase("gamemode2")) {
			player = 2;
			opponent = 1;
			maximizing = false;
		}
		
		if (mode.equalsIgnoreCase("gamemode3")) {
			player = 1;
			opponent = 2;
			maximizing = true;
		}
	}
	
	public String decideNextMove(Board board)
	{
		move = null;
		
		boardState = board.getCurrentState();
		
		// prevent any change from happening to the original current board state by making a copy
		ArrayList<Disc> rootCopy = copyList(boardState);
		Node root = new Node(rootCopy);
		tree = new Tree(root);
		
		createStateSpace(root, depth, false);
		
		Node n = Search.minimax(root, depth, maximizing);
		move = n.getParent().getLastPositionSet().getPosition();

		System.out.println("AI plays: " + move);
				
		return move;
	}	
		
	private void createStateSpace(Node node, int depth, boolean opponentTurn)
	{
		// the node is a leaf
		if (depth == 0)
		{
			Heuristics h = new Heuristics(node);
			h.calculateHeuristicValue();
			return;
		}
		
		openPositions = getOpenList(node);
		
		int ctr = 0;
		while (ctr < openPositions.size())
		{			
			ArrayList<Disc> possibleState = copyList(node.getState());
			Disc newDisc = openPositions.get(ctr);
			
			setPosition(possibleState, newDisc, opponentTurn);

			Node newNode = new Node(possibleState);
			newNode.setLastPositionSet(newDisc);
			tree.addNode(newNode, node);
			
			createStateSpace(newNode, depth - 1, !(opponentTurn));
			
			ctr++;
		}
	}

	private ArrayList<Disc> getOpenList(Node root)
	{
		ArrayList<Disc> list = new ArrayList<Disc>();
		
		for (Disc d: root.getState())
		{
			if (d.getPosition() != null && d.getPlayer() == 0)
			{
				list.add(d);
			}
		}
		
		return list;
	}
	
	private void setPosition(ArrayList<Disc> possibleState, Disc newDisc, boolean opponentTurn)
	{
		if (opponentTurn) {
			possibleState.get(newDisc.getID()).setPlayer(opponent);
		} else {
			possibleState.get(newDisc.getID()).setPlayer(player);
		}
	}
	
	private ArrayList<Disc> copyList(ArrayList<Disc> boardState)
	{
		ArrayList<Disc> copy = new ArrayList<Disc>();

		for (int id = 0; id < 135; id++)
		{
			// row 1 of available positions
			if (id >= 16 && id <= 28) {
								
				int offset = id - 16;
				char letter = (char)(65 + offset);
				String position = letter + "" + 1;
				
				Disc disc = new Disc(id, 0, position, ".", 0, 0);
				
				if (boardState.get(id).getPlayer() != 0)
					disc.setPlayer(boardState.get(id).getPlayer());
				
				copy.add(disc);
				
			// row 2 of available positions
			} else if (id >= 32 && id <= 42) {
				
				int offset = id - 32;
				char letter = (char)(66 + offset);
				String position = letter + "" + 2;
				
				Disc disc = new Disc(id, 0, position, ".", 0, 0);
				
				if (boardState.get(id).getPlayer() != 0)
					disc.setPlayer(boardState.get(id).getPlayer());
				
				copy.add(disc);

			// row 3 of available positions
			} else if (id >= 48 && id <= 56) {
				
				int offset = id - 48;
				char letter = (char)(67 + offset);
				String position = letter + "" + 3;
				
				Disc disc = new Disc(id, 0, position, ".", 0, 0);
				
				if (boardState.get(id).getPlayer() != 0)
					disc.setPlayer(boardState.get(id).getPlayer());
				
				copy.add(disc);
	
			// row 4 of available positions
			} else if (id >= 64 && id <= 70) {
				
				int offset = id - 64;
				char letter = (char)(68 + offset);
				String position = letter + "" + 4;
				
				Disc disc = new Disc(id, 0, position, ".", 0, 0);
				
				if (boardState.get(id).getPlayer() != 0)
					disc.setPlayer(boardState.get(id).getPlayer());
				
				copy.add(disc);
	
			// row 5 of available positions
			} else if (id >= 80 && id <= 84) {
				
				int offset = id - 80;
				char letter = (char)(69 + offset);
				String position = letter + "" + 5;
				
				Disc disc = new Disc(id, 0, position, ".", 0, 0);
				
				if (boardState.get(id).getPlayer() != 0)
					disc.setPlayer(boardState.get(id).getPlayer());
				
				copy.add(disc);
	
			// row 6 of available positions
			} else if (id >= 96 && id <= 98) {
				
				int offset = id - 96;
				char letter = (char)(70 + offset);
				String position = letter + "" + 6;
				
				Disc disc = new Disc(id, 0, position, ".", 0, 0);
				
				if (boardState.get(id).getPlayer() != 0)
					disc.setPlayer(boardState.get(id).getPlayer());
				
				copy.add(disc);
	
			// row 7 of available positions
			} else if (id == 112) {
				
				int offset = id - 112;
				char letter = (char)(71 + offset);
				String position = letter + "" + 7;
				
				Disc disc = new Disc(id, 0, position, ".", 0, 0);
				
				if (boardState.get(id).getPlayer() != 0)
					disc.setPlayer(boardState.get(id).getPlayer());
				
				copy.add(disc);
	
			// unavailable positions
			} else {
				
				Disc disc = new Disc(id, -1, null, null, -1, -1);
				copy.add(disc);
				
			}
		}
		
		return copy;
	}

}
