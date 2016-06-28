package views;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import models.Grid;
import models.Square;

//import models.PMModel;

public class PMView implements Observer {

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
	private JPanel puzzleMakerPanel;
	private JButton btnEasy;
	private JButton btnMedium;
	private JButton btnHard;
	private JButton btnSave;
	private JButton btnBack;
	private JPanel puzzleboard;
	private JPanel rowHeader;
	private JPanel colHeader;
	private JTextField txtTitle;
	
	private ArrayList<Square> squares;
	
	public PMView(JFrame frame, JPanel puzzleSolverPanel) {
		
		this.frame = frame;
		this.puzzleMakerPanel = puzzleSolverPanel;

		// initialize the GUI components for this panel
		initializeComponents();

	}
	
	private void initializeComponents() {
		
		// title
		JLabel lblCreateAPuzzle = new JLabel("Create a Puzzle");
		lblCreateAPuzzle.setForeground(Color.BLUE);
		lblCreateAPuzzle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCreateAPuzzle.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateAPuzzle.setBounds(335, 32, 182, 17);
		puzzleMakerPanel.add(lblCreateAPuzzle);
		
		// size label
		JLabel lblSize = new JLabel("Select a size:");
		lblSize.setBounds(335, 97, 182, 14);
		puzzleMakerPanel.add(lblSize);
		
		// difficulty label
		JLabel lblDifficulty = new JLabel("Difficulty:");
		lblDifficulty.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblDifficulty.setBounds(10, 410, 182, 14);
		puzzleMakerPanel.add(lblDifficulty);

		// EASY size button
		btnEasy = new JButton("4 x 4 (Easy)");
		btnEasy.setBounds(396, 122, 121, 23);
		puzzleMakerPanel.add(btnEasy);
		
		// MEDIUM size button
		btnMedium = new JButton("6 x 6 (Medium)");
		btnMedium.setBounds(396, 156, 121, 23);
		puzzleMakerPanel.add(btnMedium);
		
		// HARD size button
		btnHard = new JButton("8 x 8 (Hard)");
		btnHard.setBounds(396, 190, 121, 23);
		puzzleMakerPanel.add(btnHard);
		
		// line separator
		JSeparator separator = new JSeparator();
		separator.setBounds(335, 264, 181, 2);
		puzzleMakerPanel.add(separator);
		
		// puzzleboard for creating the puzzle
		puzzleboard = new JPanel();
		puzzleboard.setBorder(new LineBorder(new Color(0, 0, 0)));
		puzzleboard.setBackground(Color.WHITE);
		puzzleboard.setBounds(90, 132, 180, 180);
		puzzleMakerPanel.add(puzzleboard);
		
		// input for the puzzle's title
		txtTitle = new JTextField();
		txtTitle.setBounds(396, 224, 121, 20);
		txtTitle.setColumns(10);
		puzzleMakerPanel.add(txtTitle);
		
		
		// title label
		JLabel lblTitle = new JLabel("Set a title:");
		lblTitle.setBounds(335, 227, 68, 14);
		puzzleMakerPanel.add(lblTitle);
		
		// 'Save' button
		btnSave = new JButton("Save");
		btnSave.setBounds(396, 277, 121, 23);
		puzzleMakerPanel.add(btnSave);
		
		// 'Back' button
		btnBack = new JButton("Back");
		btnBack.setBounds(384, 379, 133, 23);
		puzzleMakerPanel.add(btnBack);
		
		// background layout
		JPanel backgroundPanel = new JPanel();
		backgroundPanel.setBounds(304, 0, 244, 435);
		puzzleMakerPanel.add(backgroundPanel);		
		
	}
	
	public void addController(ActionListener controller) {
	
		JPanel mainMenuPanel = (JPanel) frame.getContentPane().getComponent(0);
		
		// Easy button
		btnEasy.setActionCommand("GenerateEasyPuzzle");
		btnEasy.addActionListener(controller);
		
		// Medium button
		btnMedium.setActionCommand("GenerateMediumPuzzle");
		btnMedium.addActionListener(controller);
		
		// Hard button
		btnHard.setActionCommand("GenerateHardPuzzle");
		btnHard.addActionListener(controller);
		
		// Save button
		btnSave.setActionCommand("SavePuzzle");
		btnSave.addActionListener(controller);
		
		// Back Button
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Main Menu Panel
				mainMenuPanel.setVisible(true);
				
				// Puzzle Solver Panel
				puzzleMakerPanel.setVisible(false);
			}
		});
	}
	
	public String getTitle() {
		
		return txtTitle.getText();
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
			
			addButtonsToGrid(squares);
			
			// refresh interface
			frame.revalidate();	
		}
	}
	
	private void addButtonsToGrid(ArrayList<Square> squares) {
		
		for (Square s: squares) {
			puzzleboard.add(s.getButton());
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
		
		if (txtTitle.getText() != null)
			txtTitle.setText("");

		frame.repaint();
		frame.revalidate();
	}
}
