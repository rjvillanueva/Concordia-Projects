package models;
//import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JOptionPane;
//import javax.swing.JPanel;


public class PMModel extends Observable {
	
	// easy grid 4x4
	public static final int EASY_COLUMN_SIZE = 4;
	public static final int EASY_ROW_SIZE = 4;
	
	// medium grid 6x6
	public static final int MEDIUM_COLUMN_SIZE = 6;
	public static final int MEDIUM_ROW_SIZE = 6;
	
	// hard grid 8x8
	public static final int HARD_COLUMN_SIZE = 8;
	public static final int HARD_ROW_SIZE = 8;
	
	private Connection connection = null;
	private Grid grid;
	private ArrayList<Square> squares;
	private ArrayList<Square> blackSquares;
	private ArrayList<Square> solutionSquares;
	
	
	public PMModel() {
		
		squares = new ArrayList<Square>();
		
		connection = SQLiteConnection.dbConnector();

	}
	
	public void createSquares(Grid grid)
	{
		reset();
		
		this.grid = grid;
		
		int i = 1;
		for (int r = 1; r <= grid.getRowSize(); r++)
		{
			for (int c = 1; c <= grid.getColumnSize(); c++)
			{
				JButton btn = new JButton();
				Square s = new Square(i, false, r, c, btn);	
				squares.add(s);
				
				i++;
			}
		}

		setChanged();
		notifyObservers(squares);
	}
	
	public void savePuzzle(String title) {

		// count the number of black squares to check if the puzzle is blank or not
		int blackSquaresCount = 0;
		for (Square s : squares)
		{
			if (s.getFlag())
				blackSquaresCount++;
		}
		
		// Check if the puzzle is not given a title
		if (title.length() == 0)
		{
			JOptionPane.showMessageDialog(null, "Please enter a title for your puzzle.", "", JOptionPane.ERROR_MESSAGE);
		} 
		
		else 
		{
			// If the title is not unique before adding the puzzle into the database
			if (!isUnique(title))
				JOptionPane.showMessageDialog(null, "That title already exists. Please enter a new one.", "", JOptionPane.ERROR_MESSAGE);
		
			// If the puzzleboard does not have Square objects or if there are no black squares
			else if (blackSquaresCount == 0)
				JOptionPane.showMessageDialog(null, "The puzzle is blank.", "", JOptionPane.ERROR_MESSAGE);
		
			// If the title is unique and the puzzle is not blank, add the new puzzle to the database
			else
			{
				addNewPuzzle(title);
				reset();
				
				setChanged();
				notifyObservers("Reset");
			}
		}
	}
	
	/**
	 * Checks if the given title for the puzzle is unique.
	 * @return
	 */
	public boolean isUnique(String title) {
		
		boolean unique = true;
		
		try {
			
			String query = "select * from CustomPuzzles where title=?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, title.toLowerCase());
			
			ResultSet rs = pst.executeQuery();
			
		    // If the query finds at least 1 result with the same title, then the title is not unique.
		    if (rs.next())
		    {
		    	unique = false;
		    }
		    		
			pst.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	    return unique;
	}
	
	
	// Helper method
	private void reset() {
		
		//  Clear all panels, objects and arrays
		grid = null;
		
		if (squares != null)
			squares.clear();
		
		if (blackSquares != null)
			blackSquares.clear();
		
		if (solutionSquares != null)
			solutionSquares.clear();
		

	}
	
	// Helper method
	private void addNewPuzzle(String title) {
		
		// variable to hold solution string
		String solutionStr = getSolutionString();
		
		try {
			
			String query = "insert into CustomPuzzles (Title, Difficulty, Solution) values (?, ?, ?)";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, title.toLowerCase());
			pst.setString(2, grid.getDifficulty());
			pst.setString(3, solutionStr);
			
			pst.execute();
			
			JOptionPane.showMessageDialog(null, "Puzzle saved!", "", JOptionPane.PLAIN_MESSAGE);
			
			pst.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private String getSolutionString() {
		String str = "[";
		
		for (int i = 0; i < squares.size(); i++)
		{
			if (i != 0)
				str += ",";
			
			str += squares.get(i).toString();
		}
		
		return str + "]";
	}

}
