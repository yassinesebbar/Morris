package app;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Board {
	
	private Map<Character, Point> MapPoints = new HashMap<Character, Point>();
	private String[] boardGame;
	private int Wpieces = 0;
	private int Bpieces = 0;
	private String[] Mills;
	
	public Board()
	{	
		for(char Char = 'A'; Char <= 88; Char++) {
			MapPoints.put(Char, Point.EMPTY);
		}
		
		boardGame = BoardGeometric.LAYOUT;	
		Mills = BoardGeometric.MILLS;
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
	
	public boolean checkIfPointEmpty(char ch) {
		boolean pointEmpty = false;
		
		if((Point)MapPoints.get(ch) == Point.EMPTY) {
			pointEmpty = true;
		}

		return pointEmpty;
	}
	
	public void FillPoint(char selectedPoint, String value) {
		Point valuePoint = Point.EMPTY;
		
		if(value == "W") {
			valuePoint = Point.W;
		}else if(value == "Z"){
			valuePoint = Point.Z;
		}
		
		MapPoints.put(selectedPoint, valuePoint);
	}
	
	public boolean hasMill(String playerColor) {
		int count;

		for (int i = 0; i < Mills.length; i++) {
			
			count = 0;
			
			for (char ch: Mills[i].toCharArray()) {
				
				Point valuePoint = (Point)MapPoints.get(ch);
				
				if(playerColor == "Z") {
					if(valuePoint == Point.Z) {
						count++;
					}
				}else if(playerColor == "W") {
					if(valuePoint == Point.W) {
						count++;
					}
				}
			}
			
			if(count == 3) {
				return true;
			}
		}
		
		return false;
	}
	
	
		
}
