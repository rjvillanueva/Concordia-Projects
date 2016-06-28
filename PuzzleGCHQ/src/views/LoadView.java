package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.util.ArrayList;
//import javax.swing.JList;
//import models.Square;

public class LoadView implements Observer {

	private JFrame frame;
	private JPanel loadPanel;
	private JPanel psPanel;
	private JButton btnOk;
	private JButton btnBack;
	
	private DefaultTableModel model;
	private static JTable table;
	private String[][] dataValues;
	
	public LoadView(JFrame frame, JPanel loadPanel)
	{
		this.frame = frame;
		this.loadPanel = loadPanel;
		
		psPanel = (JPanel) frame.getContentPane().getComponent(3);
		
		initializeComponents();
		
	}
	
	private void initializeComponents() {
		
		// title
		JLabel lblCustomPuzzle = new JLabel("Custom Puzzles");
		lblCustomPuzzle.setForeground(Color.BLUE);
		lblCustomPuzzle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCustomPuzzle.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomPuzzle.setBounds(50, 48, 442, 14);
		loadPanel.add(lblCustomPuzzle);
		
		// label
		JLabel lblChooseAPuzzle = new JLabel("Select a puzzle:");
		lblChooseAPuzzle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChooseAPuzzle.setBounds(50, 83, 308, 23);
		loadPanel.add(lblChooseAPuzzle);
		
		// OK button
		btnOk = new JButton("OK");
		btnOk.setBounds(400, 83, 89, 23);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				psPanel.setVisible(true);				
				loadPanel.setVisible(false);
			}
		});

		loadPanel.add(btnOk);
		
		// 'Back' button
		btnBack = new JButton("Back");
		btnBack.setBounds(384, 379, 133, 23);
		loadPanel.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				psPanel.setVisible(true);				
				loadPanel.setVisible(false);

			}
		});
		loadPanel.add(btnBack);

		String[] columnTitles = {"Title", "Difficulty", "Date Created" };
		model = new DefaultTableModel(0, columnTitles.length);
		model.setColumnIdentifiers(columnTitles);
		table = new JTable(model){
			
			// This code prevents the table from being editable.
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column){
			        return false;
			}
		};
		table.setBounds(50, 131, 308, 176);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 117, 442, 221);
		scrollPane.setBackground(Color.white);
		scrollPane.setViewportView(table);
		
		loadPanel.add(scrollPane);
	}
	
	public void addController(ActionListener controller) {
		btnOk.setActionCommand("Load");
		btnOk.addActionListener(controller);
	}
	
	public static String getSelectedRowFromTable() {
		
		String title = null;
		
		int rowSelected = table.getSelectedRow();
		title = (String) table.getValueAt(rowSelected, 0);
		
		return title;
	}
	
	private void updateTable(String[][] dataValues) {
		
		model.setRowCount(0);
		
		for (String[] s: dataValues)
		{
			model.addRow(s);
		}
	}

	@Override
	public void update(Observable model, Object obj) {
		
		if (obj instanceof String[][])
		{
			dataValues = (String[][]) obj;
		
			updateTable(dataValues);
		}
	}
}
