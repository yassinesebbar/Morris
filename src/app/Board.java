package app;

import java.util.Collection;

public class Board {
	
	private String[] boardGame;
	private Point Point;
	public Board()
	{
		boardGame = BoardGeometric.LAYOUT;
		Point = new Point();
		
	}
	
	public void printBoard()
	{
		Point.getPointValues();		
		
	}
		
}
