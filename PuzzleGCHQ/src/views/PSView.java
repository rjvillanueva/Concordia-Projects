package views;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import models.Grid;
import models.Square;

//import javax.swing.JTextField;
//import controllers.LoadController;
//import models.PMModel;
//import models.PSModel;

public class PSView implements Observer {

	// easy grid 4x4
	public static final int EASY_COLUMN_SIZE = 4;
	public static final int EASY_ROW_SIZE = 4;
	
	// medium grid 6x6
	public static final int MEDIUM_COLUMN_SIZE = 6;
	public static final int MEDIUM_ROW_SIZE = 6;
	
	// hard grid 8x8
	public static final int HARD_COLUMN_SIZE = 8;
	public static final int HARD_ROW_SIZE = 8;
	
	private Grid grid;
	
	private JFrame frame;
	
	private JPanel puzzleSolverPanel;
	private JPanel puzzleboard;
	private JPanel rowHeader;
	private JPanel colHeader;

	private JButton btnEasy;
	private JButton btnMedium;
	private JButton btnHard;
	private JButton btnBack;
	private JButton btnLoad;
	private JButton btnShowSolution;
	private JButton btnCheckSolution;
	private JButton btnRestart;
	private JButton btnHint;
	private JButton btnPriority;
	
	private ArrayList<Square> squares;
	private ArrayList<Square> blackSquares;
	private ArrayList<Square> solutionSquares;
	private ArrayList<Square> initialBlackSquares = new ArrayList<Square>();
	
	private int priority[] = {-1,-1,-1};
	
	public PSView(JFrame frame, JPanel puzzleSolverPanel) {
		
		this.frame = frame;
		this.puzzleSolverPanel = puzzleSolverPanel;
		
		// initialize the GUI components for this panel
		initializeComponents();
		
	}
	
	private void initializeComponents() {
		
		puzzleSolverPanel.setBackground(Color.LIGHT_GRAY);
		
		rowHeader = new JPanel();
		puzzleSolverPanel.add(rowHeader);

		colHeader = new JPanel();
		puzzleSolverPanel.add(colHeader);
		
		btnShowSolution = new JButton("Show Solution");
		btnCheckSolution = new JButton("Check Solution");
		btnRestart = new JButton("Restart");
		btnHint = new JButton("Hint");
		btnPriority = new JButton("Priority");
		
		// Load label
		JLabel lblLoadAPuzzle = new JLabel("Load a puzzle:");
		lblLoadAPuzzle.setBounds(335, 97, 83, 14);
		puzzleSolverPanel.add(lblLoadAPuzzle);
		
		// 'Load' button
		btnLoad = new JButton("Load");
		btnLoad.setBounds(428, 93, 89, 23);
		puzzleSolverPanel.add(btnLoad);
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load();
			}
		});
		
		// Line
		JSeparator separator1 = new JSeparator();
		separator1.setBounds(428, 136, 91, 12);
		puzzleSolverPanel.add(separator1);
		
		// Random label
		JLabel lblChooseARandom = new JLabel("Choose a random puzzle:");
		lblChooseARandom.setBounds(335, 159, 184, 14);
		puzzleSolverPanel.add(lblChooseARandom);
		
		// Difficulty label
		JLabel lblDifficulty = new JLabel("Difficulty:");
		lblDifficulty.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblDifficulty.setBounds(10, 410, 182, 14);
		puzzleSolverPanel.add(lblDifficulty);
		
		// Line
		JSeparator separator2 = new JSeparator();
		separator2.setBounds(335, 287, 184, 12);
		puzzleSolverPanel.add(separator2);
		
		// 'Show Solution' button
		btnShowSolution.setEnabled(false);
		btnShowSolution.setBounds(384, 345, 133, 23);
		puzzleSolverPanel.add(btnShowSolution);
		btnShowSolution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSolution();
			}
		});

		// 'Check Solution' button
		btnCheckSolution.setEnabled(false);
		btnCheckSolution.setBounds(90, 322, 180, 23);
		puzzleSolverPanel.add(btnCheckSolution);
		btnCheckSolution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkSolution();
			}
		});


		// 'Hint' button
		btnHint.setEnabled(false);		
		btnHint.setBounds(90, 350, 180, 23);
		puzzleSolverPanel.add(btnHint);
		
		// Hint button
		
		btnHint.setBounds(90, 350, 180, 23);
		puzzleSolverPanel.add(btnHint);
		btnHint.setEnabled(false);
		
		btnHint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for(int i = 0; i < blackSquares.size(); i++) 
				{
					try
					{
						while(blackSquares.get(i).getFlag() || i >= blackSquares.size()) // Only provide the hint to none selected squares. 
						{
							Border defaultBorder = new LineBorder(Color.GRAY, 1);
							squares.get(i).getButton().setBorder(defaultBorder);
							i++;
						}
						Border hintBorder = new LineBorder(Color.DARK_GRAY, 3); // Set the border of the hint button to Cyan so the user knows he/she had some help with the puzzle.
						blackSquares.get(i).getButton().setBorder(hintBorder);
						break;
					}
					catch (IndexOutOfBoundsException e1) 
					{
						JOptionPane.showMessageDialog(null, "Out of hints!");
						btnHint.setEnabled(false);
						btnPriority.setEnabled(false);

					}
						
				}
			}
		});

		// 'Priority' button
		btnPriority.setEnabled(false);		
		btnPriority.setBounds(90, 375, 180, 23);
		puzzleSolverPanel.add(btnPriority);
		
		btnPriority.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				priority();
			}
		});
		
		// 'Restart' button
		btnRestart.setEnabled(false);
		btnRestart.setBounds(384, 311, 133, 23);
		puzzleSolverPanel.add(btnRestart);
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restart();
			}
		});

		// Random EASY button
		btnEasy = new JButton("Easy");
		btnEasy.setBounds(430, 184, 89, 23);
		puzzleSolverPanel.add(btnEasy);
		btnEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnShowSolution.setEnabled(true);
				btnCheckSolution.setEnabled(true);
				btnRestart.setEnabled(true);
				btnHint.setEnabled(true);
				btnPriority.setEnabled(true);
				
				lblDifficulty.setText("Difficulty:  EASY");
			}
		});
		
		// Random MEDIUM button
		btnMedium = new JButton("Medium");
		btnMedium.setBounds(430, 218, 89, 23);
		puzzleSolverPanel.add(btnMedium);
		btnMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnShowSolution.setEnabled(true);
				btnCheckSolution.setEnabled(true);
				btnRestart.setEnabled(true);
				btnHint.setEnabled(true);
				btnPriority.setEnabled(true);
				
				lblDifficulty.setText("Difficulty:  MEDIUM");
			}
		});

		
		// Random HARD button
		btnHard = new JButton("Hard");
		btnHard.setBounds(430, 253, 89, 23);
		puzzleSolverPanel.add(btnHard);
		btnHard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnShowSolution.setEnabled(true);
				btnCheckSolution.setEnabled(true);
				btnRestart.setEnabled(true);
				btnHint.setEnabled(true);
				btnPriority.setEnabled(true);
				
				lblDifficulty.setText("Difficulty:  HARD");
			}
		});


		// 'Back' button
		btnBack = new JButton("Back");
		btnBack.setBounds(384, 379, 133, 23);
		puzzleSolverPanel.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		
		// Puzzleboard, where the puzzle will display
		puzzleboard = new JPanel();
		puzzleboard.setBorder(new LineBorder(new Color(0, 0, 0)));
		puzzleboard.setBackground(Color.WHITE);
		puzzleboard.setBounds(90, 132, 180, 180);
		puzzleSolverPanel.add(puzzleboard);

		// 'OR' label
		JLabel lblOr = new JLabel("OR");
		lblOr.setHorizontalAlignment(SwingConstants.CENTER);
		lblOr.setBounds(400, 125, 26, 23);
		puzzleSolverPanel.add(lblOr);

		// Line
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(335, 136, 65, 12);
		puzzleSolverPanel.add(separator_2);
		
		// Title label
		JLabel lblSolveThePuzzle = new JLabel("Solve the Puzzle");
		lblSolveThePuzzle.setBounds(335, 32, 182, 17);
		lblSolveThePuzzle.setForeground(Color.BLUE);
		lblSolveThePuzzle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSolveThePuzzle.setFont(new Font("Tahoma", Font.BOLD, 14));
		puzzleSolverPanel.add(lblSolveThePuzzle);
		
		// Background panel
		JPanel backgroundPanel = new JPanel();
		backgroundPanel.setBackground(UIManager.getColor("Panel.background"));
		backgroundPanel.setBounds(304, 0, 244, 435);
		puzzleSolverPanel.add(backgroundPanel);
		
	}
	
	public void addController(ActionListener controller) {
			
		// Easy Button
		btnEasy.setActionCommand("GenerateEasyPuzzle");
		btnEasy.addActionListener(controller);
		
		// Medium Button
		btnMedium.setActionCommand("GenerateMediumPuzzle");
		btnMedium.addActionListener(controller);
		
		// Hard Button
		btnHard.setActionCommand("GenerateHardPuzzle");
		btnHard.addActionListener(controller);
		
		// change
		btnLoad.setActionCommand("Load");
		btnLoad.addActionListener(controller);
	}
		
	// called when the model calls notifyObservers()
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable model, Object obj) {
		
		if (obj instanceof String && obj.toString() == "Reset") {
			resetBoard();
		}
		
		// squares
		if (obj instanceof java.util.ArrayList) {
			squares = (ArrayList<Square>) obj;
			setUpPuzzle(squares);
		}
	}
	
	public void setUpPuzzle(ArrayList<Square> squares) {

		blackSquares = new ArrayList<Square>();
		
		// create list of black squares
		for (Square s: squares) {
			if (s.getFlag())
			{
				blackSquares.add(s);
			}
		}
		
		addButtonsToGrid(squares);
		saveSolution(squares);
		calculateHeaders(squares);		
		prepareQuestionGrid(squares);
		colorHintSquares();
		
		btnShowSolution.setEnabled(true);
		btnCheckSolution.setEnabled(true);
		btnRestart.setEnabled(true);
		btnHint.setEnabled(true);
		btnPriority.setEnabled(true);
		
		// refresh interface
		frame.revalidate();

	}
	
	private void addButtonsToGrid(ArrayList<Square> squares) {
		
		for (Square s: squares) {
			puzzleboard.add(s.getButton());
		}
		
	}
	
	private void saveSolution(ArrayList<Square> squares) {
		
		solutionSquares = new ArrayList<Square>();
		
		for (Square s: squares)
		{
			Square square = new Square(s.getID(), s.getFlag(), s.getRowPosition(), s.getColumnPosition(), new JButton());
			solutionSquares.add(square);
		}
		
	}
	
	private void prepareQuestionGrid(ArrayList<Square> squares) {
		
		double removalPercentage;
		
		switch (grid.getDifficulty()) {
		
		case "easy": 
			removalPercentage = 0.75;		// 75%
			break;
		case "medium":
			removalPercentage = 0.55;		// 55%
			break;
		case "hard":
			removalPercentage = 0.40;		// 40%
			break;
		default:
			removalPercentage = 0;
			break;
		}
				
		// the total number of squares to remove from the blackSquares list
		int numOfSquaresToRemove = (int) Math.floor(blackSquares.size() * removalPercentage);
		
		// counter to keep track of the number of squares removed
		int ctr = 0;
		
		while (ctr < numOfSquaresToRemove)
		{
			// Choose a random square from the list of blackSquares
			int index = (int) Math.floor(Math.random() * blackSquares.size());
		
			Square selectedSquare = blackSquares.get(index);
		
			if (selectedSquare.getFlag())
			{
				selectedSquare.setFlag(false);
				ctr++;
			}
		}
	}
	
	private void colorHintSquares() {
		// disable click event for the HINT squares that will remain on the grid
		for (Square s: blackSquares)
		{
			if (s.getFlag())
			{
				s.getButton().setEnabled(false);
				s.getButton().setBackground(Color.darkGray);

				// save the initial black squares in this list for the Restart button to use afterwards
				// note: square ID starts at 1
				initialBlackSquares.add(s);
			}
		}

	}
	
	private void calculateHeaders(ArrayList<Square> squares) {

		// Row Header
		rowHeader.setBounds(15, 132, 62, 180);
		rowHeader.setBackground(Color.LIGHT_GRAY);
		rowHeader.setLayout(new GridLayout(grid.getRowSize(), 10, 0, 0));
		
		int length;
		String str;		

		for (int r = 0; r < grid.getRowSize(); r++)
		{
			length = 0;
			str = "";
			
			for (int c = 0; c < grid.getColumnSize(); c++)
			{
				int offset = grid.getColumnSize() * r;
				
				if (squares.get(offset + c).getFlag())
				{
					length++;
					/*if(length > priority[1])
					{
						priority[1] = length;
						priority[0] = offset + c;
						priority[2] = 0;
					}*/
				} else {
					if (length != 0)
					{
						/*if(length > priority[1])
						{
							priority[1] = length;
							priority[0] = offset + c;
							priority[2] = 0;
						}*/
						str = str + length + "   ";
						length = 0;
					}
				}
			}
			
			if (length != 0)
				str = str + length;
			
			JLabel rowStr = new JLabel(str.trim());
			rowStr.setHorizontalAlignment(SwingConstants.RIGHT);
			//MAKSYM START
			rowStr.addMouseListener(new MouseRowAdapter(r,squares)); 
			//MAKSYM END
			rowHeader.add(rowStr);

		}
						
		// Column Header
		colHeader.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		colHeader.setBounds(90, 66, 180, 55);
		colHeader.setBackground(Color.LIGHT_GRAY);
		colHeader.setLayout(new GridLayout(1, grid.getColumnSize(), 0, 0));

		for (int c = 0; c < grid.getColumnSize(); c++)
		{
			length = 0;
			str = "<html>";		// to be able to use the break-line <br> code
			
			for (int r = 0; r < grid.getRowSize(); r++)
			{
				int offset = grid.getColumnSize() * r;
				
				if (squares.get(offset + c).getFlag())
				{
					length++;
					grid.getDifficulty();
					/*if(length > priority[1])
					{
						priority[1] = length;
						priority[0] = offset + c;
						priority[2] = 1;
					}*/
				} else {
					if (length != 0)
					{
						/*if(length > priority[1])
						{
							priority[1] = length;
							priority[0] = offset + c;
							priority[2] = 1;
						}*/
						str = str + length + "<br> ";
						length = 0;
					}
				}
			}
			
			if (length != 0)
				str = str + length + "<html>";
			
			JLabel colStr = new JLabel(str);
			colStr.setHorizontalAlignment(SwingConstants.CENTER);
			//MAKSYM START
			colStr.addMouseListener(new MouseColAdapter(c,squares)); 
			//MAKSYM END
			colHeader.add(colStr);
		}
	}
	
	public Grid generateGrid(String size) {

		// easy size
		if (size.equalsIgnoreCase("easy")) {
			grid = new Grid(EASY_ROW_SIZE, EASY_COLUMN_SIZE, "easy");
		} 
		// medium size
		else if (size.equalsIgnoreCase("medium")) {
			grid = new Grid(MEDIUM_ROW_SIZE, MEDIUM_COLUMN_SIZE, "medium");
		} 
		// hard size
		else {
			grid = new Grid(HARD_ROW_SIZE, HARD_COLUMN_SIZE, "medium");
		}
		
		puzzleboard.setLayout(new GridLayout(grid.getRowSize(), grid.getColumnSize(), 0, 0));
		
		return grid;

	}

	/**
	 * Helper method to reset the puzzleboard and its headers (row and col).
	 * This sets up the board for generating a new grid.
	 */
	public void resetBoard() {
		
		if (puzzleboard != null)
			puzzleboard.removeAll();
		
		if (rowHeader != null)
			rowHeader.removeAll();
		
		if (colHeader != null)
			colHeader.removeAll();
		
		if (squares != null)
			squares.clear();
		
		if (blackSquares != null)
			blackSquares.clear();
		
		if (solutionSquares != null)
			solutionSquares.clear();
		
		if (initialBlackSquares != null)
			initialBlackSquares.clear();

		frame.repaint();
		frame.revalidate();
	}

	private void showSolution() {
		
		String showSolMsg = "Showing the solution will end the game.";
		
		// input = 0, OK
		// input = 2, CANCEL
		int input = JOptionPane.showConfirmDialog((Component) null, showSolMsg,"", JOptionPane.OK_CANCEL_OPTION);

		if (input == 0)
		{
			// show solution
			for (int i = 0; i < squares.size(); i++)
			{
				squares.get(i).setFlag(solutionSquares.get(i).getFlag());
				squares.get(i).getButton().setEnabled(false);
			}
			
			btnShowSolution.setEnabled(false);
			btnCheckSolution.setEnabled(false);
			btnHint.setEnabled(false);
			btnRestart.setEnabled(false);
			btnPriority.setEnabled(false);
			
		}
	}
	
	private void checkSolution() {
		
		int errors = 0;
		boolean noErrors = true;		// flag for checking if any moves made is wrong
		boolean puzzleDone = true;		// flag for checking if the puzzle is completely done
		
		for (int i = 0; i < squares.size(); i++)
		{
			// If the square is filled in by the user,
			if (squares.get(i).getFlag() == true 
					// and the corresponding solution square is NOT supposed to be filled
					&& !solutionSquares.get(i).getFlag())
			{
				// increment the error count
				errors++;
				
				squares.get(i).getButton().setBackground(Color.red);
				noErrors = false;
			}
			
			// check if the current squares in the puzzleboard matches the solution squares
			if (squares.get(i).getFlag() != solutionSquares.get(i).getFlag())
			{
				puzzleDone = false;
			}
		}
		
		if (errors > 0) {
			JOptionPane.showMessageDialog(null, "You have " + errors 
												+ (errors > 1 ? " errors." : " error."));
		} else if (noErrors && !puzzleDone) {
			JOptionPane.showMessageDialog(null, "You have 0 errors.");
		} else {
			JOptionPane.showMessageDialog(null, "Congratulations! You win!");
			
			for (Square s : squares)
			{
				s.getButton().setEnabled(false);
			}
			
			btnShowSolution.setEnabled(false);
			btnCheckSolution.setEnabled(false);
			btnRestart.setEnabled(false);
			btnHint.setEnabled(false);
			btnPriority.setEnabled(false);
		}
	}
	
	private void restart() {
		
		String restartMsg = "Are you sure you want to restart the puzzle?";

		// input = 0, OK
		// input = 2, CANCEL
		int input = JOptionPane.showConfirmDialog((Component) null, restartMsg,"", JOptionPane.OK_CANCEL_OPTION);
		
		if (input == 0)
		{
			for (Square s : squares)
			{
				if (s.getFlag() == true && initialBlackSquares.contains(s))
				{
					s.getButton().setBackground(Color.darkGray);	
				} else {
					s.setFlag(false);
					Border defaultBorder = new LineBorder(Color.GRAY, 1);
					s.getButton().setBorder(defaultBorder);
				}
			}
		}
	}
	
	private void back() {

		JPanel mainMenuPanel = (JPanel) frame.getContentPane().getComponent(0);
		
		// If the user has a puzzle currently in play
		// and the puzzle has not been solved, then show an alert message.
		if (squares != null && !squares.isEmpty() && btnShowSolution.isEnabled())
		{
			String quitMsg = "Returning to the main menu will make you lose any progress.  Are you sure you want to quit?";
		
			// i = 0, YES
			// i = 1, NO
			// i = 2, CANCEL
			int i = JOptionPane.showConfirmDialog((Component) null, quitMsg,"", JOptionPane.YES_NO_CANCEL_OPTION);
		
			// If YES, then return to the main menu.
			if (i == 0)
			{
				resetBoard();
				
				btnShowSolution.setEnabled(false);
				btnCheckSolution.setEnabled(false);
				btnRestart.setEnabled(false);
				btnHint.setEnabled(false);
				btnPriority.setEnabled(false);
		
				puzzleSolverPanel.setVisible(false);
				mainMenuPanel.setVisible(true);
			}
			
		} else {
			
			// If no puzzle is in play, return to the main menu without an alert message.
			puzzleSolverPanel.setVisible(false);
			mainMenuPanel.setVisible(true);
		}
	}
	
	private void load() {

		// Puzzle Solver Panel
		frame.getContentPane().getComponent(3).setVisible(false);
		
		// Load Panel
		JPanel loadPanel = (JPanel) frame.getContentPane().getComponent(5);
		loadPanel.setVisible(true);
		
//		LoadView loadView = new LoadView(frame, loadPanel);
		
		// change
//		LoadModel loadModel = new LoadModel();
//		LoadController lc = new LoadController();
//		lc.addModel(loadModel);
//		lc.addView(loadView);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	
	//MAKSYM START
	
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
	
	public ArrayList<boolean[]> calculateRowHints(int rowIndex){
		//Gets the hints from the component and converts it into an array of ints
		String hintString = "";
		if (rowHeader.getComponent(rowIndex) instanceof JLabel) {
		    JLabel label = (JLabel) rowHeader.getComponent(rowIndex);
		    hintString = label.getText();
		}	
		String[] hintStringArray = hintString.split("\\s+");	
		int[] hints;
		
		//Checks if the row has no hints (is completely empty)
		if(hintStringArray.length == 0 || hintStringArray[0] == "" || hintStringArray[0].isEmpty()){
			hints = new int[1];
			hints[0] = 0;
		} else {
			hints = new int[hintString.length()];
			
			//Converts the strings into ints
			for(int i = 0;i<hintStringArray.length;i++){
				hints[i] = Integer.parseInt(hintStringArray[i]);
			}
		}
		
		//Gets the square flags into simple boolean form for manipulation
		int rowSize = grid.getRowSize();
		int colSize = grid.getColumnSize();
		
		boolean[] row = new boolean[rowSize];
		boolean[][] cols = new boolean[rowSize][colSize];
		ArrayList<ArrayList<Integer>> colHints = new ArrayList<ArrayList<Integer>>(rowSize);
		
		//Fills the row with values
		for(int r = 0;r<row.length;r++){
			row[r] = squares.get((r+(rowIndex*rowSize))).getFlag();
		}
		
		
		//Fills the columns with values
		for(int c = 0;c<cols.length;c++){
			//Adds the columns into the column 2d array
			for(int r = 0;r<cols[c].length;r++){
				cols[c][r] = squares.get((r*rowSize)+c).getFlag();
			}
			
			//Parses the column hints and adds them to their array
			String colHint = "";
			if (colHeader.getComponent(c) instanceof JLabel) {
			    JLabel label = (JLabel) colHeader.getComponent(c);
			    colHint = label.getText();
			}
			String colHint2 = colHint.replaceAll("<html>|<br>", "");
			String[] colHintStringArray = colHint2.split("\\s+");	
			ArrayList<Integer> colHintArrayList = new ArrayList<Integer>();
			
			//Checks if there are no hints, and if so, adds the int 0
			if(colHintStringArray.length == 0 || colHintStringArray[0] == "" || colHintStringArray[0].isEmpty()){
				colHintArrayList.add(0);
			} else {
				//System.out.println(colHintStringArray[0]);
				for(int i = 0;i<colHintStringArray.length;i++){
					colHintArrayList.add(Integer.parseInt(colHintStringArray[i]));
				}
			}
			colHints.add(colHintArrayList);
		}
		
		//Generates all possible solutions for the row
		
		boolean[] tempBool = new boolean[rowSize];
		ArrayList<boolean[]> allPossible = generateSolutions(tempBool,0);
		ArrayList<boolean[]> reduced = new ArrayList<boolean[]>();
		ArrayList<boolean[]> finalSolutions = new ArrayList<boolean[]>();
		
		
		//Removes the solutions that contradict the hints
		for(int c = 0;c<allPossible.size();c++){
			
			if(isRowSolution(allPossible.get(c),hints)){
				
				//Removes the possibilities where previously black squares have been changed to white
				boolean contradiction = false;
				for(int i = 0;i<rowSize;i++){
					if(row[i] && !(allPossible.get(c)[i])){
						//Do nothing, as the hint generator should not be able to turn black squares white
						contradiction = true;
					}
				}
				if(!contradiction){
					reduced.add(allPossible.get(c));
				}
			}
		}
		
		//Checks the columns to see if they contradict
		
		for(int i = 0;i<reduced.size();i++){
			
			boolean contradicts = false;
		
			//For each square in the given solution, checks if the corresponding column contradicts
			for(int c = 0;c<cols.length;c++){

				boolean[] tempCol = new boolean[cols[c].length];
				for(int j = 0;j<tempCol.length;j++){
					tempCol[j] = cols[c][j];
				}
				tempCol[rowIndex] = reduced.get(i)[c];

				//Converts the hint arrayList into array
				int[] tempHints = new int[colHints.get(c).size()];
				for(int j = 0;j<colHints.get(c).size();j++){
					tempHints[j] = colHints.get(c).get(j);
				}

				if(isContradicting(tempCol,tempHints)){
					//System.out.println(c);
					contradicts = true;
				}

			}
		
			//If there is no contradiction for the solution, adds it the arrayList of final solutions
			if(!contradicts){
				finalSolutions.add(reduced.get(i));
			}
		}
		
		
//		System.out.println("---");
//		for(int c = 0;c<finalSolutions.size();c++){
//			System.out.println(finalSolutions.get(c)[0] + " " + finalSolutions.get(c)[1] + " " + finalSolutions.get(c)[2] + " " + reduced.get(c)[3] + " ");
//		}
//		System.out.println("---");
				
		return finalSolutions;
	}
	
	/**
	 * Calculates the solutions for the row hint panel and returns them
	 */
	public ArrayList<boolean[]> calculateColHints(int colIndex){
		//Gets the hints from the component and converts it into an array of ints
		String hintString = "";
		if (colHeader.getComponent(colIndex) instanceof JLabel) {
		    JLabel label = (JLabel) colHeader.getComponent(colIndex);
		    hintString = label.getText();
		}	
		String hintsTemp = hintString.replaceAll("<html>|<br>", "");
		String[] colHintStringArray = hintsTemp.split("\\s+");	
		
		//Checks if there are no hints, and if so, adds the int 0
		int[] hints;
		if(colHintStringArray.length == 0 || colHintStringArray[0] == "" || colHintStringArray[0].isEmpty()){
			hints = new int[1];
			hints[0] = 0;;
		} else {
			hints = new int[colHintStringArray.length];
			for(int i = 0;i<colHintStringArray.length;i++){
				hints[i] = (Integer.parseInt(colHintStringArray[i]));
			}
		}
		
		//Gets the square flags into simple boolean form for manipulation
		int rowSize = grid.getRowSize();
		int colSize = grid.getColumnSize();
		
		boolean[] col = new boolean[colSize];
		boolean[][] rows = new boolean[colSize][rowSize];
		ArrayList<ArrayList<Integer>> rowHints = new ArrayList<ArrayList<Integer>>(rowSize);
		
		//Fills the row with values
		for(int r = 0;r<col.length;r++){
			col[r] = squares.get(colIndex + (r*rowSize)).getFlag();
		}
		
		//Fills the rows with values
		for(int c = 0;c<rows.length;c++){
			
			//Adds the rows into the column 2d array
			for(int r = 0;r<rows[c].length;r++){
				rows[c][r] = squares.get((c*rowSize) + r).getFlag();
			}
			
			//Parses the column hints and adds them to their array
			String rowHint = "";
			if (rowHeader.getComponent(c) instanceof JLabel) {
			    JLabel label = (JLabel) rowHeader.getComponent(c);
			    rowHint = label.getText();
			}
			String[] rowHintStringArray = rowHint.split("\\s+");	
			ArrayList<Integer> rowHintArrayList = new ArrayList<Integer>();
			//Checks if there are no hints, and if so, adds the int 0
			if(rowHintStringArray.length == 0 || rowHintStringArray[0] == "" || rowHintStringArray[0].isEmpty()){
				rowHintArrayList.add(0);
			} else {
				for(int i = 0;i<rowHintStringArray.length;i++){
					rowHintArrayList.add(Integer.parseInt(rowHintStringArray[i]));
				}
			}
			rowHints.add(rowHintArrayList);
		}
		
		//Generates all possible solutions for the row
		
		boolean[] tempBool = new boolean[colSize];
		ArrayList<boolean[]> allPossible = generateSolutions(tempBool,0);
		ArrayList<boolean[]> reduced = new ArrayList<boolean[]>();
		ArrayList<boolean[]> finalSolutions = new ArrayList<boolean[]>();
		
		
		//Removes the solutions that contradict the hints
		for(int c = 0;c<allPossible.size();c++){
			
			if(isRowSolution(allPossible.get(c),hints)){
				
				//Removes the possibilities where previously black squares have been changed to white
				boolean contradiction = false;
				for(int i = 0;i<colSize;i++){
					if(col[i] && !(allPossible.get(c)[i])){
						//Do nothing, as the hint generator should not be able to turn black squares white
						contradiction = true;
					}
				}
				if(!contradiction){
					reduced.add(allPossible.get(c));
					
				}
			}
		}
		
		//Checks the rows to see if they contradict
		
		for(int i = 0;i<reduced.size();i++){
			
			boolean contradicts = false;
		
			//For each square, checks if it contradicts the corresponding row
			for(int c = 0;c<rows.length;c++){

				boolean[] tempRow = new boolean[rows[c].length];
				for(int j = 0;j<tempRow.length;j++){
					tempRow[j] = rows[c][j];
				}
				tempRow[colIndex] = reduced.get(i)[c];

				//Converts the hint arrayList into array
				int[] tempHints = new int[rowHints.get(c).size()];
				for(int j = 0;j<rowHints.get(c).size();j++){
					tempHints[j] = rowHints.get(c).get(j);
				}

				//If there is 1 contradiction, sets contradiction to true and breaks
				if(isContradicting(tempRow,tempHints)){
					System.out.println(c);
					contradicts = true;
					break; //If there is 1 contradiction, no need to keep checking
				}

			}
		
			if(!contradicts){
				finalSolutions.add(reduced.get(i));
			}
		}
		
//		System.out.println("---");
//		for(int c = 0;c<finalSolutions.size();c++){
//			System.out.println(finalSolutions.get(c)[0] + " " + finalSolutions.get(c)[1] + " " + finalSolutions.get(c)[2] + " " + reduced.get(c)[3] + " ");
//		}
//		System.out.println("---");
				
		return finalSolutions;
	}
	
	/**
	 * Checks if a row or column matches up with given hints
	 */
	
	public static boolean isRowSolution(boolean[] row,int[] hints){
		
		int[] calculation = new int[hints.length];
		int arrayIndex = -1;
		boolean writing = false;
		
		//Loops on the row, calculating the hints
		for(int i = 0;i<row.length;i++){
			
			//Possibly not needed if invalid results are
			//removed prior to this check, but is here
			//just in case
			if((arrayIndex) >= calculation.length){
				return false;
			}
			//---------------
			
			if(row[i] && !writing){
				arrayIndex++;
				if((arrayIndex) >= calculation.length){
					return false;
				}
				writing = true;
				calculation[arrayIndex]++;
			} else if(row[i]) {
				calculation[arrayIndex]++;
			} else {
				writing = false;
			}
			
		}
		
		//Checks if the calculated row hints are identical to the actual row hints
		return (Arrays.equals(hints,calculation));
		
	}
	
	/**
	 * Checks if a row or column contradicts a hint set
	 */
	
	public static boolean isContradicting(boolean[] row,int[] hints){
		//Generates a solution from the hint
		ArrayList<Integer> calculation = new ArrayList<Integer>();
		
		boolean writing = false;
		
		//Loops on the row, calculating the hints for the actual row
		for(int i = 0;i<row.length;i++){
			
			if(row[i] && !writing){
				writing = true;
				calculation.add(1);
			} else if(row[i]) {
				calculation.set((calculation.size()-1), calculation.get((calculation.size()-1))+1);
			} else {
				writing = false;
			}
		}
		
		
		//Converts the hint arrayList into array
		int[] arrayCalculation = new int[calculation.size()];
		for(int i = 0;i<calculation.size();i++){
			arrayCalculation[i] = calculation.get(i);
		}
		
		//Returns true if the sum of calculated hints is larger than the sum of actual ones
		
		int sumHint = 0,sumActual = 0;
		
		//Determines the largest hint
		int biggestHint = 0,biggestActual = 0;
		
		for(int i = 0;i<hints.length;i++){
			sumHint += hints[i];
			if(hints[i] > biggestHint){
				biggestHint = hints[i];
			}
		}
		
		for(int i = 0;i<arrayCalculation.length;i++){
			sumActual += arrayCalculation[i];
			if(arrayCalculation[i] > biggestActual){
				biggestActual = arrayCalculation[i];
			}
		}
		
		
		if(biggestActual > biggestHint){
			return true;
		}
		
		if(sumActual > sumActual){
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * Recursive function used as helper for calculateRowHints, calculateColHints
	 * @return boolean[][]
	 */
	
	public static ArrayList<boolean[]> generateSolutions(boolean[] row,int index){
		
		//Creates a temporary row of the proper length
		boolean[] tempRow = new boolean[row.length];
		
		for(int b = 0;b<tempRow.length;b++){
			tempRow[b] = row[b];
		}
		
		if(index == tempRow.length){
			ArrayList<boolean[]> tempList = new ArrayList<boolean[]>();
			tempList.add(tempRow);
			return tempList;
		}
		
		boolean[] tempRowA = new boolean[tempRow.length];
		
		//Generates tempRowA, with the index value changed to true
		for(int b = 0;b<tempRowA.length;b++){
			if(b == index){
				tempRowA[b] = true;
			} else {
				tempRowA[b] = tempRow[b];
			} 
		}
		
		boolean[] tempRowB = new boolean[tempRow.length];
		
		//Generates tempRowB, with the index value changed to false
		for(int b = 0;b<tempRowB.length;b++){
			if(b == index){
				tempRowB[b] = false;
			} else {
				tempRowB[b] = tempRow[b];
			}
		}
		
		//Calls the same function twice, for tempRowA, tempRowB which correspond to 
		//parts of the possible binary tree of possibilites
		ArrayList<boolean[]> branch1 = generateSolutions(tempRowA,index+1);
		ArrayList<boolean[]> branch2 = generateSolutions(tempRowB,index+1);
		
		branch1.removeAll(branch2);
		
		branch1.addAll(branch2);
		
		return branch1;
		
	}
	
	/**
	 * Mouse listener class for row hints
	 */

	class MouseRowAdapter extends MouseAdapter{
		private int rowIndex;
		private ArrayList<Square> squares;

		MouseRowAdapter(int rowIndex,ArrayList<Square> squares){
			super();
			this.squares = squares;
			this.rowIndex = rowIndex;
		}

		public void mouseClicked(MouseEvent e){
			
			// Puzzle Solver Panel
			frame.getContentPane().getComponent(3).setVisible(false);
			
			// Hint Panel
			JPanel hintPanel = (JPanel) frame.getContentPane().getComponent(6);
			hintPanel.setVisible(true);
			
			HintsView hintView = new HintsView(frame, hintPanel,grid,squares);
			
			hintView.displayRowHints(rowIndex,calculateRowHints(rowIndex));
		}

	}

	/**
	 * Mouse listener class for column hints
	 */

	class MouseColAdapter extends MouseAdapter{
		private int colIndex;
		private ArrayList<Square> squares;

		MouseColAdapter(int colIndex,ArrayList<Square> squares){
			super();
			this.colIndex = colIndex;
			this.squares = squares;
		}

		public void mouseClicked(MouseEvent e){
			
			// Puzzle Solver Panel
			frame.getContentPane().getComponent(3).setVisible(false);
			
			// Hint Panel
			JPanel hintPanel = (JPanel) frame.getContentPane().getComponent(6);
			hintPanel.setVisible(true);
			
			HintsView hintView = new HintsView(frame, hintPanel,grid,squares);
			
			hintView.displayColHints(colIndex,calculateColHints(colIndex));
		}

	}
	
	public boolean priority()
	{
		
		//MAKSYM Addition, stores previous priorities
		
		int[] prevPriority = new int[3];
		prevPriority[0] = priority[0];
		prevPriority[1] = priority[1];
		prevPriority[2] = priority[2];
		
		priority[0] = -1;
		priority[1] = -1;
		priority[2] = -1;
		
		int length;		
		
		// Calculate rows

		for (int r = 0; r < grid.getRowSize(); r++)
		{
			//MAKSYM ADDITION Checks if the row is already solved
			boolean rowIsSolution = true; 
			
			for(int c = 0;c<grid.getColumnSize();c++){
				int offset = grid.getColumnSize() * r;
				
				if(solutionSquares.get(offset + c).getFlag() != squares.get(offset + c).getFlag()){
					rowIsSolution = false;
					break;
				}
			}
			
			if(rowIsSolution){
				//If the row is solved, don't show it as a priority again
				continue;
			}
			
			//END MAKSYM ADDITION
			
			length = 0;
			
			for (int c = 0; c < grid.getColumnSize(); c++)
			{
				int offset = grid.getColumnSize() * r;
				
				if (solutionSquares.get(offset + c).getFlag())
				{
					length++;
					if(length > priority[1])
					{
						priority[1] = length;
						priority[0] = offset + c;
						priority[2] = 0;
					}
				} else {
					if (length != 0)
					{
						if(length > priority[1])
						{
							priority[1] = length;
							priority[0] = offset + c;
							priority[2] = 0;
						}
						length = 0;
					}
				}
			}	
		}
		
		// Calculate columns
		
		for (int c = 0; c < grid.getColumnSize(); c++)
		{
			
			//MAKSYM ADDITION Checks if the col is already solved
			boolean colIsSolution = true; 
			
			for(int r = 0;r<grid.getRowSize();r++){
				int offset = grid.getRowSize() * r;
				
				if(solutionSquares.get(offset + c).getFlag() != squares.get(offset + c).getFlag()){
					colIsSolution = false;
					break;
				}
			}
			
			if(colIsSolution){
				continue;
			}
			
			//END MAKSYM ADDITION
			
			
			
			length = 0;			
			for (int r = 0; r < grid.getRowSize(); r++)
			{
				int offset = grid.getColumnSize() * r;
				
				if (solutionSquares.get(offset + c).getFlag())
				{
					length++;
					grid.getDifficulty();
					if(length > priority[1])
					{
						priority[1] = length;
						priority[0] = offset + c;
						priority[2] = 1;
					}
				} else {
					if (length != 0)
					{
						if(length > priority[1])
						{
							priority[1] = length;
							priority[0] = offset + c;
							priority[2] = 1;
						}
						length = 0;
					}
				}
			}
		}
		
		//MAKSYM ADDITION Checks if the new priority and old one are identical
		
		boolean samePriority = false;
		
		if(priority[0] == prevPriority[0] && priority[1] == prevPriority[1] && priority[2] == prevPriority[2]){
			samePriority = true;
		}

		//Removes old border from the squares
		
		for(int i = 0;i<squares.size();i++){
			Border defaultBorder = new LineBorder(Color.GRAY, 1);
			squares.get(i).getButton().setBorder(defaultBorder);
		}
		
		//MAKSYM Addition, resets priorties to previous ones if they are all at -1
		
		if(priority[0] == -1 && priority[1] == -1 && priority[2] == -1){
			btnCheckSolution.doClick(); 
			priority[0] = prevPriority[0];
			priority[1] = prevPriority[1];
			priority[2] = prevPriority[2];
		}
		
		
		
		// If the priority is a column and not a row.
		
		if(priority[2] == 1)
		{
			int i = grid.getColumnSize();
			int paintSquare = priority[0];
			while(paintSquare >= 0)
			{
				//MAKSYM ADDITION If the priority is repeating, set it to red, else to blue
				
				MatteBorder priorityBorder;
				
				if(samePriority){
					priorityBorder = new MatteBorder(2,1,2,1,Color.RED);
				} else {
					priorityBorder = new MatteBorder(2,1,2,1,Color.BLUE);
				}
				
				squares.get(paintSquare).getButton().setBorder(priorityBorder);
				paintSquare = paintSquare - i;
			}
			
			paintSquare = priority[0];
			i = grid.getColumnSize();
			try
			{
				while(paintSquare >= 0)
				{
					//MAKSYM ADDITION If the priority is repeating, set it to red, else to blue
					
					MatteBorder priorityBorder;
					
					if(samePriority){
						priorityBorder = new MatteBorder(2,1,2,1,Color.RED);
					} else {
						priorityBorder = new MatteBorder(2,1,2,1,Color.BLUE);
					}
					squares.get(paintSquare).getButton().setBorder(priorityBorder);
					paintSquare = paintSquare + i;
				}
			}
			catch (IndexOutOfBoundsException e) 
			{

			}
		}
		else // If the priority is a row and not a column.
		{
			int paintSquare = ((int) Math.floor(priority[0] / grid.getColumnSize()))+1;
			paintSquare = (paintSquare * grid.getRowSize()) - 1;
			int size = grid.getRowSize();
			try
			{
				while(size > 0)
				{
					//MAKSYM ADDITION If the priority is repeating, set it to red, else to blue
					
					MatteBorder priorityBorder;
					
					if(samePriority){
						priorityBorder = new MatteBorder(2,1,2,1,Color.RED);
					} else {
						priorityBorder = new MatteBorder(2,1,2,1,Color.BLUE);
					}
					squares.get(paintSquare).getButton().setBorder(priorityBorder);
					paintSquare--;
					size--;
				}
			}
			catch(IndexOutOfBoundsException e) 
			{
				
			}
		}
		
		
		//JOptionPane.showMessageDialog(null, priority[2]+" Row/Column: "+priority[0]+" Value: "+priority[1], "", JOptionPane.PLAIN_MESSAGE);
		//priority[0] = 0;
		//priority[1] = 0;
		//priority[2] = 0;
		
		return true;
	}


	//MAKSYM END

}
