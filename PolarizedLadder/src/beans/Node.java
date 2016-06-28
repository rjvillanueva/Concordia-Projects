package beans;

import java.util.ArrayList;

/**
 *  This class is developed to instantiate Node objects.
 *  
 * @author Reina Villanueva
 *
 */

public class Node {

	private double heuristicValue = -1;
	private Disc lastPositionSet = null;
	private Node parent = null;
	private ArrayList<Disc> state = null;
	private ArrayList<Node> children = new ArrayList<Node>();
	
	public Node(ArrayList<Disc> state) {
		
		this.state = state;
	}
	
	public void setLastPositionSet(Disc lastPositionSet) {
		this.lastPositionSet = lastPositionSet;
	}
	
	public Disc getLastPositionSet() {
		return lastPositionSet;
	}
	
	public void setState(ArrayList<Disc> state) {
		this.state = state;
	}
	
	public void setHeuristicValue(double heuristicValue) {
		this.heuristicValue = heuristicValue;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public void setChildrent(ArrayList<Node> children)
	{
		this.children = children;
	}
	
	public ArrayList<Disc> getState() {
		return state;
	}
	
	public double getHeuristicValue() {
		return heuristicValue;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public ArrayList<Node> getChildren() {
		return children;
	}
	
	public void addChild(Node child) {
		children.add(child);
	}
}
