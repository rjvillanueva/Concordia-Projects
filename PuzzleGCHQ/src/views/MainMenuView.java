package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class MainMenuView {
	
	private JFrame frame;
	private JPanel mainMenuPanel;
	private JButton btnAbout;
	private JButton btnCredits;
	private JButton btnPlayGame;
	private JButton btnCreatePuzzle;
	private JButton btnQuitGame;

	public MainMenuView(JFrame frame, JPanel mainMenuPanel) {

		this.frame = frame;
		this.mainMenuPanel = mainMenuPanel;
		
		// initialize the GUI components for this panel
		initializeComponents();
	}
	
	private void initializeComponents() {
		
		JPanel aboutPanel = (JPanel) frame.getContentPane().getComponent(1);
		JPanel creditsPanel = (JPanel) frame.getContentPane().getComponent(2);
		JPanel puzzleSolverPanel = (JPanel) frame.getContentPane().getComponent(3);
		JPanel puzzleMakerPanel = (JPanel) frame.getContentPane().getComponent(4);
		
		// version
		JLabel lblVersion = new JLabel("Version 2.0");
		lblVersion.setBounds(499, 11, 39, 10);
		lblVersion.setFont(new Font("Tahoma", Font.PLAIN, 8));
		
		// title
		JLabel lblMainMenu = new JLabel("Puzzle GCHQ");
		lblMainMenu.setBounds(158, 110, 231, 20);
		lblMainMenu.setForeground(Color.BLUE);
		lblMainMenu.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
		
		// 'Play Game' button
		btnPlayGame = new JButton("Play Game");
		btnPlayGame.setBounds(209, 191, 129, 23);
		btnPlayGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenuPanel.setVisible(false);
				puzzleSolverPanel.setVisible(true);
			}
		});
		
		// 'Create Puzzle' button
		btnCreatePuzzle = new JButton("Create Puzzle");
		btnCreatePuzzle.setBounds(209, 225, 129, 23);
		btnCreatePuzzle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenuPanel.setVisible(false);
				puzzleMakerPanel.setVisible(true);
			}
		});

		// 'About' button
		btnAbout = new JButton("About");
		btnAbout.setBounds(419, 327, 95, 23);
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenuPanel.setVisible(false);
				aboutPanel.setVisible(true);
			}
		});
		
		// 'Credits' button
		btnCredits = new JButton("Credits");
		btnCredits.setBounds(40, 378, 95, 23);
		btnCredits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenuPanel.setVisible(false);
				creditsPanel.setVisible(true);
			}
		});

		// 'Quit' button
		btnQuitGame = new JButton("Quit");
		btnQuitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnQuitGame.setBounds(419, 378, 95, 23);
		
		// box line
		JPanel mainMenuBox = new JPanel();
		mainMenuBox.setBorder(new TitledBorder(null, "Main Menu", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		mainMenuBox.setBounds(37, 153, 474, 123);
		
		// attach components to the panel
		mainMenuPanel.add(lblVersion);
		mainMenuPanel.add(lblMainMenu);
		mainMenuPanel.add(btnPlayGame);
		mainMenuPanel.add(btnCreatePuzzle);
		mainMenuPanel.add(btnAbout);
		mainMenuPanel.add(btnCredits);
		mainMenuPanel.add(btnQuitGame);
		mainMenuPanel.add(mainMenuBox);
	}

}
