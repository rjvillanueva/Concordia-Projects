package controllers;

import models.*;
import views.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PMController implements ActionListener {
	
	private PMModel model;
	private PMView view;
	
	public PMController() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "GenerateEasyPuzzle")
		{
			view.resetBoard();
			
			Grid grid = view.generateGrid("easy");
			model.createSquares(grid);
		}
		
		if (e.getActionCommand() == "GenerateMediumPuzzle")
		{
			view.resetBoard();
			
			Grid grid = view.generateGrid("medium");
			model.createSquares(grid);
		}
		
		if (e.getActionCommand() == "GenerateHardPuzzle")
		{
			view.resetBoard();
			
			Grid grid = view.generateGrid("hard");
			model.createSquares(grid);
		}
				
//		if (e.getActionCommand() == "GenerateSmallGrid")
//		model.generateGrid("small");

//		if (e.getActionCommand() == "GenerateMediumGrid")
//		model.generateGrid("medium");

//		if (e.getActionCommand() == "GenerateLargeGrid")
//		model.generateGrid("large");
		
		if (e.getActionCommand() == "SavePuzzle") {
			String title = view.getTitle();
			model.savePuzzle(title);
			//view.resetBoard();
		}

	}
	
	public void addModel(PMModel model) {
		this.model = model;
	}
	
	public void addView(PMView view) {
		this.view = view;
	}

}
