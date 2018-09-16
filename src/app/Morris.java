package app;

import java.util.HashSet;
import java.util.Scanner;

public class Morris {
	
	private Boolean running = true;
	private int fase = 1;
	private boolean skipRound = false;
	private Player White;
	private Player Black;
	private Scanner SC = new Scanner(System.in);
	private int rounds = 0;
	private boolean GameOver = false;
	
	
	
	/**
	 * Constructor
	 */
	
	public Morris()
	{
		Player Player1;
		Player Player2;

		System.out.println("Welkom bij het aloude Molenspel!");

		System.out.println("Geef de naam van speler 1 (of een C voor een computer");
		
		String NamePlayer1 = SC.nextLine();
			
		if(!NamePlayer1.equals("C")) {
			
			if(NamePlayer1.equals("test")) {
				this.skipRound = true;
			}				
			 Player1 = new Player(NamePlayer1);				
		}else {				
			 Player1 = new Computer(NamePlayer1);
		}
			
			
			
        System.out.println("Geef de naam van speler 2 (of een C voor een computer");

        while(true) {
			String NamePlayer2 = SC.nextLine();
			
			if(NamePlayer2.equals(Player1.getUsername()) && !NamePlayer2.equals("C")) {
		        System.out.println("Dat naam is al bezet kies een andere naam");
			}else {
				if(!NamePlayer2.equals("C")) {
					Player2 = new Player(NamePlayer2);
					break;
				}else {
					Player2 = new Computer(NamePlayer2);
					break;
				}
			   
			}
        }
        
    	draw(Player1, Player2);
    	
		Play();
		
		while(GameOver) {
        	System.out.println("Wil je nog een keer spelen (ja/nee)? ");					
			String anser = SC.nextLine();
			
			if(anser.equals("ja")) {
				this.resetGame();
				this.Play();
				
			}else {
	        	System.out.println("Bedankt voor het spelen van het aloude Molenspel.");					
	        	System.out.println("Hopelijk tot een volgende keer!");					
			}
		
		}		
	}
		
	/**
	 * return nothing
	 * 
	 * it is for to determine who will begin
	 */
	
	
	private void draw(Player Player1, Player Player2) 
	{
        int turn = (int)(Math.random() * 2) + 1;
        
    	System.out.println("Loting is verricht");

        if(turn == 1) {     	
        	Black = Player2;
        	White = Player1;
        }else {   	
        	Black = Player1;
        	White = Player2;
        }
        
    	White.setColor("W");
    	Black.setColor("Z");
    	System.out.println(White.getUsername() + " heeft Wit");
       	System.out.println(Black.getUsername() + " heeft Zwart");
	}
	
	/**
	 * return nothing
	 * 
	 * Initiates the game
	 */
	
	private void Play()
	{		
		Board Board = new Board();
		this.Test(Board);
		Player[] playersArr = {White, Black};
		int x = 1;
		
		GAME:
		while(running) {
        	System.out.println("*** Fase " + this.fase + " van het spel begint nu *** ");
       	
			while(this.fase > 0) {
				   
				
				for(int i = 0; i < playersArr.length; i++) {
					
					if(i == 0) {
						x = 1;
					}else {
						x = 0;
					}
					
					this.playersTurn(Board, playersArr[i], playersArr[x]);
				}
												
				if(this.isNextRound()) {
					continue GAME;
				}
				
				if(this.GameOver) {
					break GAME;
				}
			}				
				
		}
		
		Board = null;
	}

	/**
	 * return nothing
	 * 
	 * resets all game variables
	 */
	
	private void resetGame() {
		this.GameOver = false;
		this.fase = 1;
		this.rounds = 0;
		White.resetPlayer();
		Black.resetPlayer();
		
	}
	
	/**
	 * return nothing
	 * 
	 * Handles game fases
	 */
	
	private void playersTurn(Board Board, Player player, Player oppenent) {
		
		if(this.noOptionsPlayer(Board, player)) {
			this.GameOver(oppenent);
			return;
		}
		
		while(!GameOver) {

			boolean playerMove = false;
			
			String WPoint = "";
			if(player.getHumanity()) {
				Board.printBoard();
			}
			
			if(this.fase == 1) {		
				if(player.getHumanity()) {
					
		        	System.out.println(player.getUsername() + ", geef het punt waar je een pion wilt zetten ");					
					 
	        		WPoint = SC.nextLine().toUpperCase();
	        		
				}else {
					
					String emptyPoints = Board.giveEmptyPoints();
					
					WPoint = ((Computer)player).CpPlacePoint(emptyPoints);
					
		        	System.out.println(player.getUsername() + ", zet een pion op punt " + WPoint);					

				}

				playerMove = this.faseOne(Board, player, WPoint);
	       
			}else if(this.fase == 2) {
				
				String PPoints = "";
				
				if(player.getHumanity()) {
					System.out.println(player.getUsername() + ", geef aan welke pion je wilt verzetten en waarheen");					
					
		            PPoints = SC.nextLine().toUpperCase();
										
				}else {
					
					HashSet<String> Connects = Board.getConnects();
					String[] PcPoints = Board.getPointsandMillsPlayer(player.getColor());
					String EmptyPoints = Board.giveEmptyPoints();

					
					
					PPoints = ((Computer)player).movePoint(PcPoints[0], EmptyPoints,  Connects);
					
					String pointa = Character.toString(PPoints.charAt(0));
					String pointb = Character.toString(PPoints.charAt(1));
					System.out.println(player.getUsername() + ", zet de pion van "+ pointa +" naar " + pointb);					

				}
				
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
	
	/**
	 * return boolean
	 * 
	 * checks if next round neads to begin so yes add 1 to fase
	 */
	
	private boolean isNextRound() {
		this.rounds++;
		
		if(this.fase == 1 && this.rounds == 9) {
			this.fase++;			
			return true;
		}
		
		return false;
	}
	
	/**
	 * return boolean
	 * 
	 * this is where faseone happens it returns boolean if faseone succeeded or not
	 */
	
	private boolean faseOne(Board Board, Player player, String PPoint) {
		
		char ch = this.returnChar(PPoint);
		
		if(Board.checkIfValidPoint(PPoint) && Board.checkIfPointEmpty(ch)){
			if(Board.FillPoint(ch, player.getColor())) {
				return true;
			}

        	return false;
		}				
    	System.out.println("*** Dat Punt is al bezet ***");

		return false;
	}
	
	/**
	 * returns boolean
	 * 
	 * this is where fasetwo happens it returns boolean if fase succeeded or not
	 */
	
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
	
	/**
	 * returns nothing
	 * 
	 * Handels all the mills
	 */
	
	private void checkMill(boolean hasMill, Player player, Player opponent, Board Board) {
		while(hasMill) {
			
			String MillPoint;
			
			if(player.getHumanity()) {
				System.out.println(player.getUsername() + ", je hebt een molentje! Geef de pion die je wilt pakken");
	        	
	        	 MillPoint = SC.nextLine().toUpperCase();	
			}else {
				
				String[] oppenentMills = Board.getPointsandMillsPlayer(opponent.getColor());
				
				MillPoint = ((Computer)player).useMill(oppenentMills);	
				
				System.out.println(player.getUsername() + ", pakt de pion op punt "+ MillPoint);

			}
      			
			char mp = this.returnChar(MillPoint);
			
			if(Board.useMill(mp, opponent.getColor())) {
				
				opponent.minusOnepiece();
				
	        	break;
			}
		}
	}
	
	
	private void Test(Board Board) {
		if(this.fase == 1) {

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
		
		if(player.getPieces() == 2 || !Board.playerCanMove(player.getColor()) && this.fase == 2) {		
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
