package wheeloffortune;
import java.util.Scanner;

public class Game {
	
	Scanner scan = new Scanner(System.in);
	
	boolean newGame = true; //maybe
	int round;
	int tempNum;
	int numPlayers;
	int boardLength;
	int halfBoard;
	String category;
	String answer;
	String letter;
	String letterMsg;
	String[] storedAnswer;
	String[] boardAnswers;
	String[] consonants;
	String[] vowels;
	
	Player[] player = new Player[4];
	
	public void newGame() {
		//Welcome message - Make fancier!
		System.out.println("Welcome to Wheel of Fortune!");
		
		//Ask to start game
		System.out.println("\nWould you like to start a new game? (y/n)");
		this.newGame = isYesOrNo(scan.nextLine());
		
		if (this.newGame) {
			this.round = 1;
			//get players
			System.out.println("\nAlright, how many players?");
			while (true) {
				this.numPlayers = isInt(scan.nextLine());
				if (this.numPlayers <=4) {
					break;
				} else {
					System.out.println("\nYou may have up to 4 players." +
										"\nPlease re-enter: ");
				}
			}
			for (int i = 0; i < this.numPlayers; i++) {
				this.player[i] = new Player();
				this.player[i].setPlayer(i + 1);
			}
			this.newRound();
			
		} else {
			System.out.println("Goodbye!");
			scan.close();
		}
	}
	
	//Start the next round!
	public void newRound() {
		System.out.println("\nLet's start Round " + this.round + "!"); //test
		this.consonants = resetCon();
		this.vowels = resetVow();
		
		CategoryAnswer catAns = Answer.pickCategory();
		Answer.pickAnswer(catAns);
		this.category = catAns.category();
		this.answer = catAns.answer();
		this.halfBoard = (int)(this.answer.length() / 2 + 1);
		//System.out.println(halfBoard); //TEST
		this.storedAnswer = Answer.storeAnswer(catAns.answer());
		this.boardAnswers = Answer.setNewDisplay(storedAnswer, halfBoard);
		this.boardLength = Answer.calcBoardLength(boardAnswers);
		//NOTE THE "THIS"
		
		//SHOW THE BOARD
		Answer.showBoard(boardAnswers, category, consonants, vowels, letterMsg); //TEST
		
		//MOVE THIS STUFF TO DIFFERENT METHOD OR LOCATION
		//FOR NOW IT'S A TEST
		
		chooseConsonant();
		
		
	}
	
	//Reset consonants and vowels
	public String[] resetVow() {
		String[] vowels = {"A", "E", "I", "O", "U"};
		return vowels;
	}
	
	public String[] resetCon() {
		String[] c = {"B", "C", "D", "F", "G", "H", "J", "K", "L", "M",
				"N", "O", "P", "Q", "R", "S", "T", "V", "W", "X", "Y", "Z"};
		return c;
	}
	
	//Pick a consonant
	public void chooseConsonant() {
		String newBlank;
		int numLetters = 0;
		System.out.println("\n\nPick a consonant:");
		this.letter = isConsonantLeft(this.scan.nextLine(), this.consonants);
		
		//Replace letters on board;
		newBlank = "[" + this.letter + "]";
		for (int a = 0; a < this.storedAnswer.length; a++) {
			if (this.letter.equalsIgnoreCase(storedAnswer[a])) {
				this.boardAnswers[a] = newBlank.toUpperCase();
				numLetters += 1;
			}
		}
		for (int i = 0; i < this.consonants.length; i++) {
			if (this.letter.equalsIgnoreCase(this.consonants[i])) {
				this.consonants[i] = " ";
			}
		}
		
		//Display result
		if (numLetters > 1) {
			this.letterMsg = "\nThere are " + numLetters + " of the " + this.letter + "'s.\n";
		} else if (numLetters == 1) {
			this.letterMsg = "\nThere is one " + this.letter + ".\n";
		} else {
			this.letterMsg = "\nThere are no " + this.letter + "'s.\n";
		}
		Answer.showBoard(this.boardAnswers, this.category, this.consonants, this.vowels, this.letterMsg);
		
	}

	
	//Check if player entered a consonant that is available
	public String isConsonantLeft(String letter, String[] cons) {
		//Add fields to make sure it's not
		Scanner scan = new Scanner(System.in);
		boolean status = true;
		while (status = true) {
			letter.toUpperCase();
			for (int i = 0; i <21 ; i++) {
				if ((i == 24 && !letter.contentEquals(cons[i])) ||
						letter == " ") {
					System.out.println("Please choose an available consonant.");
					letter = scan.nextLine();
					break;
				} else {
					if (cons[i] == letter) {
						this.consonants[i] = " ";
						status = false;
						break;
					}
				}
			
			} break;
		}
	return letter;
	}
	
	//Check if player entered a vowel that is available
		public static boolean isVowelLeft(String letter, String[] vows) {
			boolean vowLeft = true;
			
			return vowLeft;
		}
	
	
	
	//check if player entered an integer
	public int isInt(String a) {
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			try
			{
				int i = Integer.parseInt(a);
				return i;
			}
			catch (NumberFormatException ex)
			{
				System.out.println("Please enter an integer: ");
				a = scan.nextLine();
			}
		}
		
	}

	
	//Check if player said yes or no
	public boolean isYesOrNo(String a) {
		
		do {
			a.toLowerCase();
			if (a.startsWith("y")) {
				return true;
			} else if (a.startsWith("n")) {
				return false;
			} else {
				System.out.print("Please indicate \"yes\" or \"no\": ");
				a = scan.nextLine();
			}
		} while (true);
	}
}
