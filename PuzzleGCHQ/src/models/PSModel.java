package models;
//import java.awt.Color;
//import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import javax.swing.border.Border;
//import javax.swing.border.LineBorder;

//import views.LoadView;

public class PSModel extends Observable {

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
	
	private String[] columnNames;
	private String[][] dataValues;
	
	public PSModel() {

		squares = new ArrayList<Square>();
		blackSquares = new ArrayList<Square>();
		solutionSquares = new ArrayList<Square>();
		
		connection = SQLiteConnection.dbConnector();
	}
	
	public void createSquares(Grid grid)
	{
		reset();
		
		this.grid = grid;
		int i = 0;
		for (int r = 0; r < grid.getRowSize(); r++)
		{
			for (int c = 0; c < grid.getColumnSize(); c++)
			{
				JButton btn = new JButton();
				Square s = new Square(i, false, r, c, btn);	
				squares.add(s);
				
				i++;
			}
		}
		generateRandomPuzzle();
		saveSolution();

		setChanged();
		notifyObservers(squares);
	}
		
	public void generateLoadList(JFrame frame, JPanel panel)
	{
		// Get the list of custom puzzles from the database
		
		try {
			String query = "select title,difficulty,date from CustomPuzzles";			
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
		    ResultSetMetaData rsmd = rs.getMetaData();
		    
		    int columnCount = rsmd.getColumnCount();
		    columnNames = new String[columnCount];

		    // need to get the row count to initialize dataValues[][] before storing any data in it
		    int rowCount = 0;
		    while (rs.next())
		    {
		    	rowCount++;
		    }

		    // initialize dataValues[][]
		    dataValues = new String[rowCount][columnCount];
		    
		    // set the ResultSet 'rs' pointer back to the beginning of the list of data retrieved
		    rs = pst.executeQuery();
		    rsmd = rs.getMetaData();

		    String dataStr = "";
		    int row = 0;
		    while (rs.next()) 
		    {
		        for (int i = 1; i <= columnCount; i++) {

		            if (i > 1)
		            	dataStr += ",";

		        	String columnValue = rs.getString(i);		            
		            dataStr += columnValue;
		            
		            // Save column names into an array
		            columnNames[i-1] = rsmd.getColumnLabel(i);
		        
		        }
		        		        
		        // Each data element will contain "title, difficulty, date"
		        String data[] = dataStr.split(",", columnCount);
		        
		        // Add the data[] into the dataValues[][] array
		        dataValues[row] = data;
		        		        	            
	            dataStr = "";
	            row++;
		    }
		    
			pst.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		// notify LoadView update()
		setChanged();
		notifyObservers(dataValues);

	}
	
	public String getDifficulty(String title) {
		
		String difficulty = null;
			
		try {
			String query = "select * from CustomPuzzles where title=?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, title.toLowerCase());
			
			ResultSet rs = pst.executeQuery();

			if (rs.next())
			{
				difficulty = rs.getString(3);
			}
			
			pst.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return difficulty;
	}
	
	public void loadPuzzle(Grid grid, String title) {
		
		reset();
		
		this.grid = grid;
		
		try {
			String query = "select * from CustomPuzzles where title=?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, title.toLowerCase());
			
			ResultSet rs = pst.executeQuery();
			
			String solution = null;
			String[] arrSolution = null;
			
		    if (rs.next()) {
		    	solution = rs.getString(5);
				arrSolution = solution.split(",|\\[|\\]");

//				for (int i = 0; i < arrSolution.length; i++)
//					System.out.println(arrSolution[i]);
			}
		    
			int i = 1;
			while(i < arrSolution.length-1)
			{				
				JButton btnNewButton = new JButton();
				int id = Integer.parseInt(arrSolution[i]);
				boolean check = Boolean.parseBoolean(arrSolution[i+1]);
				int row = Integer.parseInt(arrSolution[i+2]);
				int col = Integer.parseInt(arrSolution[i+3]);

				Square s = new Square(id, check, row, col, btnNewButton);
				
				if(check == true) {
					s.setFlag(true);
				}
				
				squares.add(s);
				
				i += 4;
			}
	
		    pst.close();
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		setChanged();
		notifyObservers(squares);
	}
	
	private void generateRandomPuzzle()
	{
		double fillPercentage = 0.50;		// %50 of the grid squares will be filled in
		
		// the total number of squares to fill in
		int totalSquaresFilled = (int) Math.floor(squares.size() * fillPercentage);
		
		// counter to keep track of filled in squares
		int ctr = 0;
		
		while (ctr < totalSquaresFilled)
		{
			// Choose a random square
			int index = (int) Math.floor(Math.random() * squares.size());
			Square selectedSquare = squares.get(index);
		
			// Check if the selected square is white
			if (selectedSquare.getFlag() == false)
			{
				selectedSquare.setFlag(true);			// turn the square black
				ctr++;									// increase the counter
				
				blackSquares.add(selectedSquare);		// add the black square to the list of black squares
			}
		}				
	}
		
	// Helper method
	private void reset()
	{
		//  Clear all panels, objects and arrays
		grid = null;
		
		if (squares != null)
			squares.clear();
		
		if (blackSquares != null)
			blackSquares.clear();
		
		if (solutionSquares != null)
			solutionSquares.clear();
		
	}

	private void saveSolution()
	{
		for (Square s : squares)
		{
			Square square = new Square(s.getID(), s.getFlag(), s.getRowPosition(), s.getColumnPosition(), new JButton());
			solutionSquares.add(square);
		}
	}

}
