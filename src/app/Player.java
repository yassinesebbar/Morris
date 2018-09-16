package app;

public class Player {
	protected String username;
	private String color;
	private int pieces = 9;
	protected boolean isHuman = true;
	
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
	
	public void resetPlayer() {
		this.pieces = 9;
	}
	
	public int minusOnepiece() {
		return pieces--;
	}
	
	public boolean getHumanity() {
		return this.isHuman;
	}
	
}
