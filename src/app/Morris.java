package app;

import java.util.Scanner;

public class Morris {
	
	private Boolean running = true;
	private int fase = 1;;
	private boolean skipRound = false;
	private Player White;
	private Player Black;
	private Scanner SC = new Scanner(System.in);
	private int rounds = 0;
	private boolean GameOver = false;
	
	
	public Morris()
	{
		Player Player2;
		
		System.out.println("Give the name of Player 1 or type 'C' for a computer");
		
			String NamePlayer1 = SC.nextLine();
			Player Player1 = new Player(NamePlayer1);

			if(NamePlayer1.compareTo("test") == 0) {
				this.skipRound = true;
			}
			
        System.out.println("Give the name of Player 2 or type 'C' for a computer");

	        while(true) {
				String NamePlayer2 = SC.nextLine();
				
				if(NamePlayer2.equals(Player1.getUsername())) {
			        System.out.println("You have to choose a different name!");
				}else {
				    Player2 = new Player(NamePlayer2);
					break;
				}
	        }
	        

        int turn = (int)(Math.random() * 2) + 1;
        
    	System.out.println("The draw was done.");

        if(turn == 1) {     	
        	Black = Player2;
        	White = Player1;
        }else {   	
        	Black = Player1;
        	White = Player2;
        }
        
    	White.setColor("W");
    	Black.setColor("Z");
       	System.out.println(Black.getUsername() + " has Black");
    	System.out.println(White.getUsername() + " has White");
    	
    	
		Play();
	}
		
	
	private void Play()
	{		
		Board Board = new Board();
		this.Test(Board);
		
		GAME:
		while(running) {
        	System.out.println("*** Fase " + this.fase + " van het spel begint nu *** ");
			while(this.fase > 0) {
				       					
				this.playersTurn(Board, White, Black);
								
				this.playersTurn(Board, Black, White);
				
				if(this.isNextRound()) {
					continue GAME;
				}
				
				if(this.GameOver) {
					break GAME;
				}
			}				
				
		}
	}

	private void playersTurn(Board Board, Player player, Player oppenent) {
		
		if(this.noOptionsPlayer(Board, player)) {
			this.GameOver(oppenent);
			return;
		}
		
		while(!GameOver) {

			boolean playerMove = false;
			
			String WPoint = "";
			
			Board.printBoard();
			
			if(this.fase == 1) {		
			
	        	System.out.println(player.getUsername() + ", geef het punt waar je een pion wilt zetten ");					
				
	        	WPoint = SC.nextLine().toUpperCase();
	
				playerMove = this.faseOne(Board, player, WPoint);
			
			}else if(this.fase == 2) {
				
	        	System.out.println(player.getUsername() + ", geef aan welke pion je wilt verzetten en waarheen");					
				
	        	String PPoints = SC.nextLine().toUpperCase();
				
				playerMove = this.faseTwo(Board, player, PPoints);
				
				WPoint = Character.toString(PPoints.charAt(1));
			}
		
			if(playerMove) {
				
				char ch = returnChar(WPoint);
				
				checkMill(Board.hasMill(player.getColor(), ch), player, oppenent, Board);
				
				break;
			}
				
		}
	}
	
	private boolean isNextRound() {
		this.rounds++;
		
		if(this.fase == 1 && this.rounds == 9) {
			this.fase++;			
			return true;
		}
		
		return false;
	}
	
	private boolean faseOne(Board Board, Player player, String PPoint) {
		
		char ch = this.returnChar(PPoint);
		
		if(Board.checkIfValidPoint(PPoint) && Board.checkIfPointEmpty(ch)){
			if(Board.FillPoint(ch, player.getColor())) {
				return true;
			}
        	return false;
		}				
		
		return false;
	}
	
	private boolean faseTwo(Board Board, Player player, String PPoints) {
		
		if(PPoints.length() == 2) {
			if(Board.checkIfValidPoint(PPoints)) {				
				char PointArr[] = PPoints.toCharArray();
				if(Board.movePoint(PointArr[0], PointArr[1], player.getColor())) {
					return true;
				}				
			}
		}else {
	    	System.out.println("*** Je antwoord voldoet niet aan de eisen ***");
		}
		
		return false; 
	}
	
	private void checkMill(boolean hasMill, Player player, Player opponent, Board Board) {
		while(hasMill) {
			
        	System.out.println(player.getUsername() + ", je hebt een molentje! Geef de pion die je wilt pakken");
        	
        	String MillPoint = SC.nextLine().toUpperCase();
			
			char mp = this.returnChar(MillPoint);
			
			if(Board.useMill(mp, opponent.getColor())) {
				
				opponent.minusOnepiece();
				
	        	break;
			}
		}
	}
	
	
	private void Test(Board Board) {
		if(this.fase == 1) {
			System.out.println(this.skipRound);

			if(this.skipRound) {
				
				char WhitePoints[] = { 'A', 'B', 'F', 'H', 'K', 'P', 'Q', 'U', 'W'};
				char BlackPoints[] = {'C', 'D', 'G', 'I', 'J', 'N', 'S', 'V', 'X'};

				for(int i = 0; i < WhitePoints.length; i++) {
					Board.FillPoint(WhitePoints[i], White.getColor());
					Board.FillPoint(BlackPoints[i], Black.getColor());
				}
				
				this.fase++;				
			}
		}
	}
	
	private char returnChar(String userInput) {
		
		char charactr = 'Z';
		
		for (char ch: userInput.toCharArray()) {
			charactr = ch;			
			break;
		}
		
		return charactr;
	}
	
	private boolean noOptionsPlayer(Board Board, Player player) {	
		
		if(player.getPieces() == 2 || !Board.playerCanMove(player.getColor())) {		
			return true;
		}
		
		return false;	
	}
	
	private void GameOver(Player Winner) {
		
    	System.out.println("***" + Winner.getUsername() + " heeft gewonnen. Gefeliciteerd! ***");

    	this.GameOver = true;
	}
	
	/**
	 * @return the player2
	 */

}
