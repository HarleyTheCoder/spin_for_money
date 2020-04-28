package wheeloffortune;
import java.util.Scanner;

public class WheelOfFortune {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean newGame = true;
		Game game = new Game();
		
		String[] consonants = {"B", "C", "D", "F", "G", "H", "J", "K", "L", "M",
								"N", "O", "P", "Q", "R", "S", "T", "V", "W", "X", "Y", "Z"};
		String[] vowels = {"A", "E", "I", "O", "U"};
		
		game.consonants = consonants;
		game.vowels = vowels;
		
		//Beginning of new game
		while (newGame) {
			game.newGame(scan);
			//String[] storedAnswer = game.storedAnswer;
			//String[] boardAnswers = game.boardAnswers;
			
			
			
			
			
			
			//New Game?
			System.out.println("\nWould you like to play a new game?");
			if (!game.isYesOrNo(scan, scan.nextLine())) {
				newGame = false;
			}
		}
		

		
		/* MOVED THIS INTO GAME - SEE IF WORKS
		System.out.println("Hello"); //test
		
		//Choose category and answer, then set it all up for game
		CategoryAnswer catAns = Answer.pickCategory();
		Answer.pickAnswer(catAns);
		storedAnswer = Answer.storeAnswer(catAns.answer());
		boardAnswers = Answer.setNewDisplay(storedAnswer);
		//answer = catAns.
		 */
		
		/* DON'T ERASE THIS YET, MAY NEED IT
		while(newGame) {
		
			System.out.println("How many players?:");
			
			int i = 1;
			int numPlayers = (scanner.nextInt() - 1); //minus one for simplicity
			Player player[] = new Player[numPlayers];
			Answer answer = new Answer();
			int valWheel;
			int wheelNumber;
		
			//assign values to players
			while (i <= numPlayers) {
				player[i] = new Player();
				
				i++;
			}
			
			//ask to play again
			System.out.println("/nWould you like to play again? (y/n):");
			if (scanner.next().startsWith("n")) {
				newGame = false;
			}
			
		}
		
		// Creating an empty HashMap 
		 */
		scan.close();
	}

}
