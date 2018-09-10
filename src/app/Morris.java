package app;

import java.util.Scanner;

public class Morris {
	
	private Boolean running = true;
	private Player Player1;
	private Player Player2;
	private int fase;

	public Morris()
	{
		System.out.println("Give the name of Player 1 or type 'C' for a computer");
		
			Scanner inPlayer1 = new Scanner(System.in);
			String NamePlayer1 = inPlayer1.nextLine();
			this.Player1 = new Player(NamePlayer1);

        System.out.println("Give the name of Player 2 or type 'C' for a computer");
			Scanner inPlayer2 = new Scanner(System.in);

	        while(true) {
				String NamePlayer2 = inPlayer2.nextLine();
				
				if(NamePlayer2.equals(this.Player1.getUsername())) {
			        System.out.println("You have to choose a different name!");
				}else {
					this.Player2 = new Player(NamePlayer2);
					break;
				}
	        }
	        
        inPlayer1.close();
		inPlayer2.close();

        int turn = (int)(Math.random() * 2) + 1;
    	System.out.println("The draw was done.");

        if(turn == 1) {
        	this.Player1.setColor("W");
        	this.Player2.setColor("Z");
        	System.out.println(Player1.getUsername() + " has White");
        	System.out.println(Player2.getUsername() + " has Black");


        }else {
        	this.Player1.setColor("Z");
        	this.Player2.setColor("W");
          	System.out.println(Player1.getUsername() + " has Black");
        	System.out.println(Player2.getUsername() + " has White");

        }

        	this.fase = 1;
			Play();
	}
	
	
	private void Play()
	{
		Board Board = new Board();
		Board.printBoard();
		
        System.out.println( Player1.getUsername() + " (" + Player1.getColor() + ") ----------Morris Fase " + this.fase +"------ " + Player2.getUsername() + " (" + Player2.getColor() + ")");


		
		while(running) {

			
		}
	}


	/**
	 * @return the player2
	 */

}
