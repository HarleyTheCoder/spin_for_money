package wheeloffortune;
import java.util.Scanner;

//Codes that can be typed to do different things
//Like on the Sims 2.

public class SpecialCodes {
	
	//Player Score
	//Board
	
	
	//All method commands go here to be called
	public static void doNow(Scanner scan, String command, Player player, 
							Wheel wheel, CategoryAnswer catAns, Game game) {
		
		//Spin Wheel
		if (command.equalsIgnoreCase("1") || command.equalsIgnoreCase("1.")) {
			wheel.spinWheel(scan, player, game);	
		} else if (command.equalsIgnoreCase("2") || command.equalsIgnoreCase("2.")) {
			System.out.println("\nCommand not yet established.");
		} else if (command.equalsIgnoreCase("3") || command.equalsIgnoreCase("3.")) {
			System.out.println("\nCommand not yet established.");
		}
		
	}

}
