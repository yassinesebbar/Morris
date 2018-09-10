package app;

public class Player {
	private String username;
	private String color;
	
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
	
}
