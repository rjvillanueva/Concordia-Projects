package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.Grid;
import models.PSModel;
import views.LoadView;
import views.PSView;

public class LoadController implements ActionListener {

	private PSModel model;
	private PSView view;
	
	public LoadController() {

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getActionCommand() == "Load")
		{
			String title = LoadView.getSelectedRowFromTable();
			String difficulty = model.getDifficulty(title);
			
			view.resetBoard();
			
			Grid grid = view.generateGrid(difficulty);
			model.loadPuzzle(grid, title);
		}
	}
	
	public void addModel(PSModel model) {
		this.model = model;
	}
	
	public void addView(PSView view) {
		this.view = view;
	}

}
