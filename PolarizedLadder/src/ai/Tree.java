package ai;

import java.util.ArrayList;
import beans.Node;

/**
 * This class is developed to instantiate a Tree structure, which will be composed of Node objects.
 * 
 * @author Reina Villanueva
 * 
 */

public class Tree {

	@SuppressWarnings("unused")
	private Node root;
	private ArrayList<Node> nodes;
	
	public Tree(Node root)
	{
		this.root = root;
		nodes = new ArrayList<Node>();
	}
	
	public void addNode(Node newNode, Node parent)
	{
		newNode.setParent(parent);
		parent.addChild(newNode);
		
		nodes.add(newNode);
	}
	
	public ArrayList<Node> getNodes() {
		return nodes;
	}

}
