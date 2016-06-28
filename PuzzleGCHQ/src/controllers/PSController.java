package controllers;

import models.*;
import views.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PSController implements ActionListener{
	
	private PSModel model;
	private PSView view;
	
	private JFrame frame = null;
	private JPanel loadPanel = null;	
	
	public PSController() {
		
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

		if (e.getActionCommand() == "Load")
		{
			if (frame == null && loadPanel == null)
			{
				frame = view.getFrame();
				loadPanel = (JPanel) frame.getContentPane().getComponent(5);
				LoadView loadView = new LoadView(frame, loadPanel);

				LoadController lc = new LoadController();
				lc.addView(view);
				lc.addModel(model);
				
				loadView.addController(lc);
				
				model.addObserver(loadView);
			}
			
			model.generateLoadList(frame, loadPanel);
		}
	}
	
	public void addModel(PSModel model) {
		this.model = model;
	}
	
	public void addView(PSView view) {
		this.view = view;
	}
	

}
