package app;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum Point
{
	A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X;	
}

Map<Points, String> MapPoints = new HashMap<Points, String>();


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
			
		
	}
		
}
