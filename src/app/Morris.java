package app;

import java.util.Scanner;

public class Morris {
	
	private Boolean running = true;
	private int fase;
	private Player White;
	private Player Black;
	private Scanner SC = new Scanner(System.in);
	
	
	public Morris()
	{
		Player Player2;
		
		System.out.println("Give the name of Player 1 or type 'C' for a computer");
		
			String NamePlayer1 = SC.nextLine();
			Player Player1 = new Player(NamePlayer1);

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
        	White.setColor("W");
        	Black.setColor("Z");
        	System.out.println(White.getUsername() + " has White");
        	System.out.println(Black.getUsername() + " has Black");

        }else {
        	
        	Black = Player1;
        	White = Player2;
        	Black.setColor("Z");
        	White.setColor("W");
          	System.out.println(Black.getUsername() + " has Black");
        	System.out.println(White.getUsername() + " has White");

        }

        	this.fase = 1;
			Play();
	}
	
	
	
	
	
	
	private void Play()
	{
		Board Board = new Board();

		GAME:
		while(running) {

	        System.out.println("*** Fase " + fase + " van het spel begint nu *** ");
			while(fase > 0) {
		        
					WHITE:
					while(White.getPieces() > 0) {
						Board.printBoard();
						
						if(fase == 1) {
				        	System.out.println(White.getUsername() + ", geef het punt waar je een pion wilt zetten ");					
						}	
						
						String WPoint = SC.nextLine().toUpperCase();
						if(WPoint.equals("") || WPoint.equals(" ")) {
							continue;
						}
						
						if(Board.checkIfValidPoint(WPoint)) {
							if(fase == 1) {
								for (char ch: WPoint.toCharArray()) {									
									if(Board.checkIfPointEmpty(ch)) {
										if(Board.FillPoint(ch, White.getColor())) {
											White.minusOnepiece();
											while(Board.hasMill(White.getColor(), ch)) {
												
									        	System.out.println(White.getUsername() + ", je hebt een molentje! Geef de pion die je wilt pakken");
									        	
									        	String MillPoint = SC.nextLine().toUpperCase();
												
												char mp = this.returnChar(MillPoint);
												
												if(Board.useMill(mp, Black.getColor())) {
													break;
												}
											}
										}
										break;
									}
									break;
								}
							}
						}else {
				        	System.out.println("*** Dat punt bestaat niet ***");					
						}					
						
						break;
					}
	        	
	        		BLACK:
					while(Black.getPieces() > 0) {
						Board.printBoard();
						
						if(fase == 1) {
				        	System.out.println(Black.getUsername() + ", geef het punt waar je een pion wilt zetten ");					
						}	
						
						String BPoint = SC.nextLine().toUpperCase();
						if(BPoint.equals("") || BPoint.equals(" ")) {
							continue;
						}
						
						if(Board.checkIfValidPoint(BPoint)) {
							if(fase == 1) {
								for (char ch: BPoint.toCharArray()) {
									
									if(Board.checkIfPointEmpty(ch)) {
										Board.FillPoint(ch, Black.getColor());
										Black.minusOnepiece();
										while(Board.hasMill(Black.getColor(), ch)) {
											
								        	System.out.println(Black.getUsername() + ", je hebt een molentje! Geef de pion die je wilt pakken");
								        	
											String MillPoint = SC.nextLine().toUpperCase();
									
											char mp = this.returnChar(MillPoint);
											
											if(Board.useMill(mp, White.getColor())) {
												break;
											}
											
										}
									}
									
									break;
								}
							}

						}else {
				        	System.out.println("*** Dat punt bestaat niet ***");					
						}
						
					
						
						if(fase == 1 && Black.getPieces() == 0) {
							fase++;
							continue GAME;
						}
						
						break;
						
					}
			}
			
		}
	}

	private void firstFase(Board Board, Player player, Player oponent) {
		if(fase == 1) {
        	System.out.println(White.getUsername() + ", geef het punt waar je een pion wilt zetten ");					
		}	
		
		String WPoint = SC.nextLine().toUpperCase();
		if(WPoint.equals("") || WPoint.equals(" ")) {
			continue;
		}
		
		if(Board.checkIfValidPoint(WPoint)) {
			if(fase == 1) {
				for (char ch: WPoint.toCharArray()) {									
					if(Board.checkIfPointEmpty(ch)) {
						if(Board.FillPoint(ch, White.getColor())) {
							White.minusOnepiece();
							while(Board.hasMill(White.getColor(), ch)) {
								
					        	System.out.println(White.getUsername() + ", je hebt een molentje! Geef de pion die je wilt pakken");
					        	
					        	String MillPoint = SC.nextLine().toUpperCase();
								
								char mp = this.returnChar(MillPoint);
								
								if(Board.useMill(mp, Black.getColor())) {
									break;
								}
							}
						}
						break;
					}
					break;
				}
			}
		}else {
        	System.out.println("*** Dat punt bestaat niet ***");					
		}					
		
		break;
	}
	
	
	private char returnChar(String userInput) {
		
		char charactr = 'Z';
		
		for (char ch: userInput.toCharArray()) {
			charactr = ch;			
			break;
		}
		
		return charactr;
	}
	
	/**
	 * @return the player2
	 */

}
