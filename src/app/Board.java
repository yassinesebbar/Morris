package app;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Board {
	
	private Map<Character, Point> MapPoints = new HashMap<Character, Point>();
	private String[] boardGame;
	
	public Board()
	{	
		for(char Char = 'A'; Char <= 88; Char++) {
			MapPoints.put(Char, Point.EMPTY);
		}
		
		boardGame = BoardGeometric.LAYOUT;	
	}
	
	public void printBoard()
	{
		String Board = "";
		String LBoard = "";
		String RBoard = "";
		String space = "                ";
		char SpecialChar;

		for (int i = 0; i < boardGame.length; i++) {
			RBoard = "";
			LBoard = "";
			
			for (char ch: boardGame[i].toCharArray()) {
				SpecialChar = ch;
				RBoard += ch;
				
				for (Map.Entry<Character, Point> entry : MapPoints.entrySet()) {
					if(entry.getKey() == ch) {
						if(entry.getValue() == Point.EMPTY) {
							SpecialChar = '.';
						}else if(entry.getValue() == Point.W || entry.getValue() == Point.Z) {
							if(entry.getValue() == Point.W) {
								SpecialChar = 'W';
							}else {
								SpecialChar = 'Z';
							}
						}						
					}
				}
				
				LBoard += SpecialChar;

			}
			Board += LBoard + space + RBoard + "\n";
		}
		
	    System.out.println(Board);

	}
	
	public boolean checkIfValidPoint(String Point) {
		boolean valid = true;
		
		for (char ch: Point.toCharArray()) {
			
			if(ch < 65 || ch > 88 ) {
				valid = false;
			}
		}

		return valid;
	}
	
	public boolean checkIfPointEmpty() {
		return true;
	}
		
}
