package models;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class Square {

	private int id;				// ID of the square starting from 1
	private boolean flag;		// toggle the color of the square to black (true) or white (false)
	private int rowPosition;	// row of the square
	private int colPosition;	// column of the square
	private JButton button;		// button for the square
	
	public Square(int id, boolean f, int rowPosition, int colPosition, JButton button)
	{
		this.id = id;
		this.flag = f;
		this.rowPosition = rowPosition;
		this.colPosition = colPosition;
		this.button = button;
		
		button.setBackground(Color.white);		// white is the default color
		// On-click, the button gets filled in
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (flag)
				{
					button.setBackground(Color.white);
					setFlag(false);
				} else {
					button.setBackground(Color.black);
					setFlag(true);
				}

			}
		});
	}
	
	public void setID(int id) 
	{
		this.id = id;
	}
	
	public int getID()
	{
		return id;
	}
	
	public void setFlag(boolean flag)
	{
		this.flag = flag;
		
		// if TRUE, set to BLACK
		// if FALSE, set to WHITE
		
		if (button != null)
			button.setBackground(flag ? Color.black : Color.white);
	}
	
	public boolean getFlag()
	{
		return flag;
	}
	
	public void setRowPosition(int rowPosition)
	{
		this.rowPosition = rowPosition;
	}
	
	public int getRowPosition()
	{
		return rowPosition;
	}
	
	public void setColumnPosition(int colPosition)
	{
		this.colPosition = colPosition;
	}
	
	public int getColumnPosition()
	{
		return colPosition;
	}
	
	public JButton getButton()
	{
		return button;
	}
	
	@Override
	public String toString()
	{
		return id + "," + flag + "," + rowPosition + "," + colPosition;
	}
}
