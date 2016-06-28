import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import models.*;
import views.*;
import controllers.*;
import javax.swing.JList;

public class Main {

	private JFrame frame;
	
	private JPanel mainMenuPanel;
	private JPanel aboutPanel;
	private JPanel creditsPanel;
	private JPanel loadPanel;
	private JPanel puzzleSolverPanel;
	private JPanel puzzleMakerPanel;
	//MAKSYM ADDED
	private JPanel hintPanel;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {

		initialize();
		
		MainMenuView mainMenuView = new MainMenuView(frame, mainMenuPanel);
		AboutView aboutView = new AboutView(frame, aboutPanel);
		CreditsView creditsView = new CreditsView(frame, creditsPanel);
		//MAKSYM Added
		//HintsView hintsView = new HintsView(frame, hintPanel);
		
		// PUZZLE SOLVER - create Model and View
		PSModel psModel = new PSModel();
		PSView psView = new PSView(frame, puzzleSolverPanel);

		// PUZZLE SOLVER - tell the Model about the View
		psModel.addObserver(psView);
		
		// PUZZLE SOLVER - create Controller, and tell it about the Model and the View
		PSController psController = new PSController();
		psController.addModel(psModel);
		psController.addView(psView);
		
		// PUZZLE SOLVER - tell View about the Controller
		psView.addController(psController);
		
		// PUZZLE MAKER - create Model and View
		PMModel pmModel = new PMModel();
		PMView pmView = new PMView(frame, puzzleMakerPanel);
		
		// PUZZLE MAKER - tell the Model about the View
		pmModel.addObserver(pmView);
		
		// PUZZLE MAKER - create Controller, and tell it about the Model and the View
		PMController pmController = new PMController();
		pmController.addModel(pmModel);
		pmController.addView(pmView);
		
		// PUZZLE MAKER - tell View about the Controller
		pmView.addController(pmController);
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// Frame
		frame = new JFrame();
		frame.setTitle("Team W - COMP 354 Project Puzzle GCHQ");
		frame.setBounds(100, 100, 564, 474);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Main Menu Panel
		mainMenuPanel = new JPanel();
		mainMenuPanel.setLayout(null);
		
		// About Panel
		aboutPanel = new JPanel();
		aboutPanel.setLayout(null);
		aboutPanel.setVisible(false);
		
		// Puzzle Maker Panel
		puzzleMakerPanel = new JPanel();
		puzzleMakerPanel.setLayout(null);
		puzzleMakerPanel.setBackground(Color.LIGHT_GRAY);
		puzzleMakerPanel.setVisible(false);
		
		// Credits Panel
		creditsPanel = new JPanel();
		creditsPanel.setLayout(null);
		creditsPanel.setVisible(false);
		
		// Load Panel
		loadPanel = new JPanel();
		loadPanel.setLayout(null);
		loadPanel.setVisible(false);

		// Puzzle Solver Panel
		puzzleSolverPanel = new JPanel();
		puzzleSolverPanel.setLayout(null);
		puzzleSolverPanel.setVisible(false);
		
		// MAKSYM ADDED Hint panel
		hintPanel = new JPanel();
		hintPanel.setLayout(null);
		hintPanel.setVisible(false);
		
		// Attach panels to the frame
		frame.getContentPane().add(mainMenuPanel, "name_28455569164628");
		frame.getContentPane().add(aboutPanel, "name_114000946688104");
		frame.getContentPane().add(creditsPanel, "name_190561452930038");
		frame.getContentPane().add(puzzleSolverPanel, "name_28221176910956");
		frame.getContentPane().add(puzzleMakerPanel, "name_33240040171680");
		frame.getContentPane().add(loadPanel, "name_173509736327299");
		//MAKSYM ADDED
		frame.getContentPane().add(hintPanel, "name_348403809823002");
	}
}
