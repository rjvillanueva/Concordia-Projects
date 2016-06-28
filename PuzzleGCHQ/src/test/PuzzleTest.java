package test;

import static org.junit.Assert.*;
//import java.util.ArrayList;

import javax.swing.JButton;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import models.Grid;
import models.PMModel;
import models.Square;
import views.PSView;

//import PuzzleGCHQ.*;

public class PuzzleTest {

	static Grid grid;
	static Square square;
	static PMModel pmModel;
	static PSView psView;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		grid = new Grid();
		square = new Square(0, false, 0,0, new JButton());
		pmModel = new PMModel();
//		psView = new PSView();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGridConstructor() {
		grid = new Grid(4,4, "easy");
		assertEquals("Expected column: 4.", 4, grid.getColumnSize());
		assertEquals("Expected row: 4.", 4, grid.getRowSize());
		assertEquals("The difficulty should be easy.", "easy", grid.getDifficulty());
	}
	
	@Test
	public void testGridMutators() {
		grid.setColumnSize(10);
		grid.setRowSize(5);
		grid.setDifficulty("hard");
		
		assertEquals("Expected column: 10", 10, grid.getColumnSize());
		assertEquals("Expected row: 5", 5, grid.getRowSize());
		assertEquals("Exptected difficulty: hard", "hard", grid.getDifficulty());
		
	}
	
	@Test
	public void testGridMutators2() {
		grid.setColumnSize(3);
		grid.setRowSize(7);
		grid.setDifficulty("easy");
		
		assertEquals("Expected column: 3", 3, grid.getColumnSize());
		assertEquals("Expected row: 7", 7, grid.getRowSize());
		assertEquals("Exptected difficulty: easy", "easy", grid.getDifficulty());
		
	}
	
	@Test
	public void testGridMutators3() {
		grid.setColumnSize(2);
		grid.setRowSize(2);
		grid.setDifficulty("medium");
		
		assertEquals("Expected column: 2", 2, grid.getColumnSize());
		assertEquals("Expected row: 2", 2, grid.getRowSize());
		assertEquals("Exptected difficulty: medium", "medium", grid.getDifficulty());
		
	}
	
	@Test
	public void testSquareConstructor() {
		square = new Square(0, false, 4, 4, new JButton());
		assertEquals("Expected rowPos: 4", 4, square.getRowPosition());
		assertEquals("Expected colPos: 4", 4, square.getColumnPosition());
	}
	
	@Test
	public void testSquareMutators() {
		square.setFlag(false);
		square.setColumnPosition(5);
		square.setRowPosition(3);
		
		assertEquals("Expected flag is false", false, square.getFlag());
		assertEquals("Expected col is 5", 5, square.getColumnPosition());
		assertEquals("Expected row is 3", 3, square.getRowPosition());
	}
	
	@Test
	public void testSquareMutators2() {
		square.setFlag(true);
		square.setColumnPosition(1);
		square.setRowPosition(1);
		
		assertEquals("Expected flag is false", true, square.getFlag());
		assertEquals("Expected col is 1", 1, square.getColumnPosition());
		assertEquals("Expected row is 1", 1, square.getRowPosition());
	}
	
	@Test
	public void testSquareMutators3() {
		square.setFlag(false);
		square.setColumnPosition(10);
		square.setRowPosition(10);
		square.setFlag(true);
		square.setRowPosition(2);
		
		assertEquals("Expected flag is true", true, square.getFlag());
		assertEquals("Expected col is 10", 10, square.getColumnPosition());
		assertEquals("Expected row is 2", 2, square.getRowPosition());
	}

	@Test
	public void testIsUnique1() {
		pmModel.savePuzzle("PuzzleTest");
		assertEquals("This should be false.", false, pmModel.isUnique("PuzzleTest"));
	}

	@Test
	public void testIsUnique2() {
		assertEquals("This should be true.", true, pmModel.isUnique("The Forest of Elderan"));
	}

	// @Test
	// public void testCalculateHeaders1() {
	// 	square.setFlag(false);

	// 	ArrayList<Square> squares = new ArrayList<Square>();
		
	// 	// adding 4 white squares
	// 	for(int i = 0; i < 4; i++)
	// 		squares.add(square)
		
	// 	assertEquals("", "<html>0<br>0<br>0<br>0<br>", psView.calculateHeaders(squares));
	// }

	// public void testCalculateHeaders2() {
	// 	square.setFlag(false);

	// 	ArrayList<Square> squares = new ArrayList<Square>();
		
	// 	// adding 4 white squares
	// 	for(int i = 0; i < 4; i++)
	// 		squares.add(square)
		
	// 	assertEquals("", "1 1 1 1", psView.calculateHeaders(squares));
	// }

}