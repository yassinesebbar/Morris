package app;


import java.util.Scanner;


public class Application {
		
	public Application()
	{
		
	    System.out.println("Welcome to Morris");
	    System.out.println("Do you want to play a Game?");
	    
	    System.out.println("y/n");
	    Scanner submitPlayer = new Scanner(System.in);

	    while(true) {
		    String anser = submitPlayer.nextLine();
		    
		    if(anser.equals("y")) {
		    	Morris Morris = new Morris();
		    	
		    	break;
	          
		    }else if(anser.equals("n")) 
		    {
		    	submitPlayer.close();

		    	System.exit(1);
		    }else {
			    System.out.println("please respond with 'y' (yes) or 'n' (no)");
		    }
			   	
	    }
	    
    	submitPlayer.close();


	}
	

}
