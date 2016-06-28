package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class AboutView {
	
	private JFrame frame;
	private JPanel aboutPanel;
	private JButton btnBack;
	private JTextArea description;
	
	public AboutView(JFrame frame, JPanel aboutPanel) {
	
		this.frame = frame;
		this.aboutPanel = aboutPanel;
		
		// initialize the GUI components for this panel
		initializeComponents();
	}
	
	private void initializeComponents() {
		
		JPanel mainMenuPanel = (JPanel) frame.getContentPane().getComponent(0);
		
		// title
		JLabel lblAbout = new JLabel("About");
		lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout.setForeground(Color.BLUE);
		lblAbout.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAbout.setBounds(183, 55, 182, 14);
		
		// content area
		description = new JTextArea("\n Puzzle project created for COMP 354 Intro to Software Engineering by Team-W. © 2016");
		description.setEditable(false);
		description.setBackground(UIManager.getColor("Panel.background"));
		description.setBounds(34, 91, 480, 258);
		aboutPanel.add(description);
		
		// 'Back' button
		btnBack = new JButton("Back");
		btnBack.setBounds(419, 378, 95, 23);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mainMenuPanel.setVisible(true);
				aboutPanel.setVisible(false);
			}
		});
		
		// attach components to the panel
		aboutPanel.add(lblAbout);
		aboutPanel.add(btnBack);
		
	}

}
