package app;

public class Player {
	private String username;
	private String color;
	private int pieces = 9;
	
	public Player(String name) {
		username = name;
	}

	public String getUsername() {
		return username;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}
	
	public int getPieces() {
		return pieces;
	}
	
	public int minusOnepiece() {
		return pieces--;
	}
	
}
