package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

//import javax.swing.UIManager;

public class CreditsView {
	
	private JFrame frame;
	private JPanel creditsPanel;
	private JButton btnBack;
	
	public CreditsView(JFrame frame, JPanel creditsPanel) {
	
		this.frame = frame;
		this.creditsPanel = creditsPanel;
		
		// initialize the GUI components for this panel
		initializeComponents();
	}
	
	private void initializeComponents() {
		
		JPanel mainMenuPanel = (JPanel) frame.getContentPane().getComponent(0);
		
		// title
		JLabel lblAbout = new JLabel("Credits");
		lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout.setForeground(Color.BLUE);
		lblAbout.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAbout.setBounds(183, 55, 182, 14);
		
		// subtitle
		JLabel lblTeamW = new JLabel("COMP 354 - Team W");
		lblTeamW.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTeamW.setBounds(68, 120, 182, 14);
		creditsPanel.add(lblTeamW);
		
		// Logo image
		JLabel logo = new JLabel("");
		Image imgLogo = new ImageIcon(this.getClass().getResource("/wlogo.png")).getImage();
		logo.setIcon(new ImageIcon(imgLogo));
		logo.setBounds(324, 188, 159, 144);
		creditsPanel.add(logo);
		
		// team members
		JTextArea textArea = new JTextArea();
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setBounds(68, 145, 206, 133);
		textArea.setText("Mohamed-Amine Badraoui\n" 
						+ "Jing Chen\n"
						+ "Sean Canlas\n"
						+ "Brandon Kim\n"
						+ "Antonio Morello\n"
						+ "Maksym Perepichka\n"
						+ "Reina Villanueva");
		creditsPanel.add(textArea);
		
		// background layout
		JPanel backgroundPanel = new JPanel();
		backgroundPanel.setBackground(Color.LIGHT_GRAY);
		backgroundPanel.setBounds(36, 96, 478, 250);
		creditsPanel.add(backgroundPanel);
		
		// 'Back' button
		btnBack = new JButton("Back");
		btnBack.setBounds(419, 378, 95, 23);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mainMenuPanel.setVisible(true);
				creditsPanel.setVisible(false);
			}
		});
		
		// attach components to the panel
		creditsPanel.add(lblAbout);
		creditsPanel.add(btnBack);
		
	}
}
