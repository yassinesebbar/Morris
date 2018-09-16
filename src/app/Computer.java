package app;

import java.util.HashSet;

public class Computer extends Player {

	static private int AmountPC = 0;
	
	public Computer(String name) {
		
		super(name);
		// TODO Auto-generated constructor stub
		
		String namePC = "Computer";
		
		if(Computer.AmountPC > 0) {
			namePC = "Computer 2";
		} 
		
		this.username = namePC;

		Computer.AmountPC++;
		this.isHuman = false;
	}
	
	public String CpPlacePoint(String emptyPoints) {
		return Character.toString(emptyPoints.charAt(0));
	}
	
	public String useMill(String[] PointsAndMills) {
		
		String chMill = "";
		boolean noHit = true;
		
		if(PointsAndMills[0].length() == PointsAndMills[1].length()) {
			chMill = Character.toString(PointsAndMills[0].charAt(0));			
		}else {
			for(char ch: PointsAndMills[0].toCharArray()) {
				for(char hh: PointsAndMills[1].toCharArray()) {
					if(ch == hh) {
						noHit = false;
					}
				}
				if(noHit) {
					chMill = ch + "";
				}
			}		
		}	
		return chMill;
	}
	
	public String movePoint(String PcPoints, String emptyPoints, HashSet<String> PossibleConnects) {		
		for(char ch: PcPoints.toCharArray()) {
			for(int i = 0; i < emptyPoints.length(); i++) {				
				if(PossibleConnects.contains("" + ch + emptyPoints.toCharArray()[i])) {
					return ch + "" + emptyPoints.toCharArray()[i];
				}
			}
		}
		return "";
	}
}
