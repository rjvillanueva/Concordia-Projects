package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

//import java.awt.Image;
//import javax.swing.ImageIcon;
//import javax.swing.JTextArea;
//import javax.swing.UIManager;

import models.Grid;
import models.Square;

public class HintsView {

	private JFrame frame;
	private JPanel hintPanel;
	private JButton btnBack;
	
	
	private ArrayList<Square> squares;
	private Grid grid;
	
	public HintsView(JFrame frame, JPanel hintPanel,Grid grid,ArrayList<Square> squares) {
		
		this.frame = frame;
		this.hintPanel = hintPanel;
		this.grid = grid;
		this.squares = squares;
		
		// initialize the GUI components for this panel
		initializeComponents();
	}
	
	public void initializeComponents(){
		
		JPanel puzzleSolverPanel = (JPanel) frame.getContentPane().getComponent(0);
		
		hintPanel.setLayout(null);
		// 'Back' button
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hintPanel.removeAll();
				initializeComponents();
				hintPanel.setVisible(false);
				JPanel solver = (JPanel) frame.getContentPane().getComponent(3);
				solver.setVisible(true);
			}
		});
		btnBack.setBounds(419, 378, 95, 23);
		hintPanel.add(btnBack);
		
		// title
		JLabel lblCustomPuzzle = new JLabel("Solution Hints");
		lblCustomPuzzle.setForeground(Color.BLUE);
		lblCustomPuzzle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCustomPuzzle.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomPuzzle.setBounds(50, 48, 442, 14);
		hintPanel.add(lblCustomPuzzle);
		
	}
	
	/**
	 * Applies the row hints to the puzzle board
	 * @param rowIndex The the row index
	 * @param values The chosen solution values
	 */
	
	public void applyRowHint(int rowIndex,boolean[] values){

		//Loops on the squares, applying the chosen values
		for(int i = (rowIndex*values.length);i<((rowIndex*values.length)+(values.length));i++){
			if(!squares.get(i).getFlag()){
				squares.get(i).setFlag(values[i-(rowIndex*values.length)]);
			}
		}
	}
	
	/**
	 * Applies the col hints to the puzzle board
	 * @param colIndex The the row index
	 * @param values The chosen solution values
	 */
	
	public void applyColHint(int colIndex,boolean[] values){

		//Loops on the squares, applying the chosen values
		for(int i = 0;i<values.length;i++){
			if(!squares.get( (colIndex) + (i * grid.getRowSize())).getFlag()){
				squares.get( (colIndex) + (i * grid.getRowSize())).setFlag(values[i]);
			}
		}
	}
	
	/**
	 * Displays the row hints on the hint page
	 * @param rowIndex
	 * @param finalSolutions
	 */
	
	public void displayRowHints(int rowIndex,ArrayList<boolean[]> finalSolutions){
		//int totalRows = grid.getColumnSize();	
		int totalCols = grid.getColumnSize();
		
		// Generate square objects in the grid
		int id = 1;
		
		//Adds a label to say if there are no possible solutions with the current arrangement
		if(finalSolutions.size() == 0){
			// title
			JLabel noSolutionsLabel = new JLabel("No solutions possible with current arrangement");
			noSolutionsLabel.setForeground(Color.RED);
			noSolutionsLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
			noSolutionsLabel.setHorizontalAlignment(SwingConstants.CENTER);
			noSolutionsLabel.setBounds(50, 200, 442, 14);
			hintPanel.add(noSolutionsLabel);
		}
		
		for (int r = 0; r < finalSolutions.size(); r++)
		{
			JPanel solution = new JPanel();
			solution.setLayout(new GridLayout(1, totalCols, 0, 0));
			
			// puzzleboard for creating the puzzle
			solution.setBorder(new LineBorder(new Color(0, 0, 0)));
			solution.setBackground(Color.WHITE);
			solution.setBounds(90, (132 + (r*(240/totalCols))), 180, (180 / totalCols));
			
			hintPanel.add(solution);
			
			for (int c = 0; c < finalSolutions.get(r).length; c++)
			{
				//Creates buttons within the row with action listners
				JButton btnNewButton = new JButton();
				
				btnNewButton.addActionListener(new RowHintListener(rowIndex,finalSolutions.get(r)));

				Square s = new Square(id, finalSolutions.get(r)[c], r, c, btnNewButton);
				
				s.setFlag(finalSolutions.get(r)[c]);
				
				solution.add(btnNewButton);
				
				id++;
			}
		}
	}
	
	/**
	 * 
	 * @param rowIndex
	 * @param finalSolutions
	 */
	
	
	public void displayColHints(int colIndex,ArrayList<boolean[]> finalSolutions){
		int totalRows = grid.getColumnSize();	
		int totalCols = grid.getColumnSize();
		
		// Generate square objects in the grid
		int id = 1;
		
		//If there are no possible solutions, adds that label to the hint page
		if(finalSolutions.size() == 0){
			// title
			JLabel noSolutionsLabel = new JLabel("No solutions possible with current arrangement");
			noSolutionsLabel.setForeground(Color.RED);
			noSolutionsLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
			noSolutionsLabel.setHorizontalAlignment(SwingConstants.CENTER);
			noSolutionsLabel.setBounds(50, 200, 442, 14);
			hintPanel.add(noSolutionsLabel);
		}
		
		
		for (int r = 0; r < finalSolutions.size(); r++)
		{
			JPanel solution = new JPanel();
			solution.setLayout(new GridLayout(totalCols, totalRows, 0, 0));
			
			// puzzleboard for creating the puzzle
			solution.setBorder(new LineBorder(new Color(0, 0, 0)));
			solution.setBackground(Color.WHITE);
			
			//Sets the proper bounds for the solution columns
			solution.setBounds(90 + (r* (240/totalCols )), 132, (180/totalRows), 180);
			
			hintPanel.add(solution);
			
			for (int c = 0; c < finalSolutions.get(r).length; c++)
			{
				//Creates buttons within the columns, with action listeners
				JButton btnNewButton = new JButton();
				
				btnNewButton.addActionListener(new ColHintListener(colIndex,finalSolutions.get(r)));

				Square s = new Square(id, finalSolutions.get(r)[c], r, c, btnNewButton);
				
				s.setFlag(finalSolutions.get(r)[c]);
				
				solution.add(btnNewButton);
				
				id++;
			}
		}
	}
	
	class RowHintListener implements ActionListener{
	    private int rowIndex;
	    private boolean[] values;

	    RowHintListener(int rowIndex,boolean[] values){
	        super();
	        this.rowIndex = rowIndex;
	        this.values = values;
	    }
	    
		public void actionPerformed(ActionEvent e){
			// Puzzle Solver Panel
			JPanel solver = (JPanel) frame.getContentPane().getComponent(3);
			solver.setVisible(true);
			// Hints panel
			frame.getContentPane().getComponent(6).setVisible(false);;
			
			hintPanel.removeAll();
			initializeComponents();
			
			applyRowHint(rowIndex,values);
			
		}
		
	}
	
	class ColHintListener implements ActionListener{
	    private int colIndex;
	    private boolean[] values;

	    ColHintListener(int colIndex,boolean[] values){
	        super();
	        this.colIndex = colIndex;
	        this.values = values;
	    }
	    
		public void actionPerformed(ActionEvent e){
			// Puzzle Solver Panel
			JPanel solver = (JPanel) frame.getContentPane().getComponent(3);
			solver.setVisible(true);
			// Hints panel
			frame.getContentPane().getComponent(6).setVisible(false);
			
			hintPanel.removeAll();
			initializeComponents();
			
			applyColHint(colIndex,values);
			
		}
		
	    
	}
	
	
	
}
