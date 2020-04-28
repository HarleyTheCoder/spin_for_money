package wheeloffortune;
import java.util.Scanner;

public class Game {
	
	int round;
	int tempNum;
	int numPlayers;
	int boardLength;
	int halfBoard;
	boolean newGame;
	String category;
	String answer;
	String letter;
	String letterMsg = "";
	String[] storedAnswer;
	String[] boardAnswers;
	String[] consonants;
	String[] vowels;
	
	Player[] player = new Player[4];
	Wheel wheel = new Wheel();
	
	public boolean newGame(Scanner scan) {
		//Welcome message - Make fancier!
		System.out.println("Welcome to Wheel of Fortune!");
		
		//Ask to start game
		System.out.println("\n\nWould you like to start a new game? (y/n)");
		this.newGame = isYesOrNo(scan, scan.nextLine());
		
		if (newGame) {
			this.round = 1;
			//get players
			System.out.println("\nAlright, how many players?");
			while (true) {
				this.numPlayers = isInt(scan, scan.nextLine());
				if (this.numPlayers <=4) {
					break;
				} else {
					System.out.println("\nYou may have up to 4 players." +
										"\nPlease re-enter: ");
				}
			}
			for (int i = 0; i < this.numPlayers; i++) {
				this.player[i] = new Player();
				this.player[i].setPlayer(scan, i + 1);
			}
			this.newRound(scan);
			
		} else {
			System.out.println("Goodbye!");
			scan.close();
		}
		return newGame;
	}
	
	//Start the next round!
	public void newRound(Scanner scan) {
		for (int i = 0; i < this.numPlayers; i++) {
			player[i].setFreeSpin(0);
			player[i].setScore(0);
			//ALTER THIS SO WINNER OF LAST ROUND STARTS NEXT ROUND?
			if (i == 0) {
				player[i].isTurn();
			} else {
				player[i].isNotTurn();
			}
		}
		this.consonants = resetCon();
		this.vowels = resetVow();
		
		//Selects the category and answers for the board
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
		
		//Set player turn and active player
		int turnNum = 0;
		boolean solved = false;
		
		System.out.println("Here's the board!");
		//****************************************
		//WHERE THE WHEEL STARTS SPINNING
		//****************************************
		while(!solved) {
			//Set active player
			int actPlay = getPlayerTurn(turnNum);
			
			//SHOW THE BOARD
			if (turnNum == 0) {
				this.letterMsg = "Let's start Round " + this.round + "!";
			}
			Answer.showBoard(this.boardAnswers, this.category, this.letterMsg); //TEST
			//PAUSE
			this.pause(scan);
			Answer.showLetters(this.consonants, this.vowels);
			System.out.println("");
			
			//Start next player's turn
			while (this.player[actPlay].checkTurn()) {
				//PLAYER x: NAME, it's your turn!
				//Score: 0
				this.player[actPlay].startNewTurn();
			
				//Method to choose -- if they choose then this
				SpecialCodes.doNow(scan, scan.nextLine(), player[actPlay], wheel, catAns, this);
			
				//will become where they choose before spinning
				//wheel.spinWheel(scan, this.player[actPlay]);
			
				//Use free spin or set next turn
				if (this.player[actPlay].getFreeSpins() > 0) {
					if (this.player[actPlay].useFreeSpin(scan)) {
						this.player[actPlay].isTurn();
					} else {
						this.player[actPlay].isNotTurn();
					}
				}
				
				//Start next player's turn if current player's turn has ended
				if (!this.player[actPlay].checkTurn()) {
					turnNum++;
				}
			}
			
			//Add up player scores from last round
			for (int i = 0; i < this.numPlayers; i++) {
				player[i].setTotalScore(player[i].getTotalScore() + player[i].getScore());
			}
			
			
			//until further code to break look
			solved = true; //temp
		}
		
		
		
		
		//test
		
		//MOVE THIS STUFF TO DIFFERENT METHOD OR LOCATION
		//FOR NOW IT'S A TEST
		
		
		
		
	}
	
	//determines who's turn it is and then sets booleans to make sure
	public int getPlayerTurn(int turnNum) {
		int playerNum;
		if (turnNum >= numPlayers) {
			playerNum = turnNum % numPlayers;
		} else {
			playerNum = turnNum;
		}
		for (int i = 0; i < this.player.length; i++) {
			if (i == playerNum) {
				this.player[i].isTurn();
			}
		}
		return playerNum;
	}
	
	//Reset consonants and vowels
	public String[] resetVow() {
		String[] vowels = {"A", "E", "I", "O", "U"};
		return vowels;
	}
	
	public String[] resetCon() {
		String[] c = {"B", "C", "D", "F", "G", "H", "J", "K", "L", "M",
				"N", "P", "Q", "R", "S", "T", "V", "W", "X", "Y", "Z"};
		return c;
	}
	
	//Pick a consonant
	public boolean chooseConsonant(Scanner scan) {
		String newBlank;
		String message;
		int numLetters = 0;
		boolean isInAnswer;
		System.out.println("\n\nPick a consonant:");
		//CHECK IF IT'S A VOWEL TOO
		this.letter = isConsonantLeft(scan, scan.nextLine(), this.consonants);
		
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
			message = "There are " + numLetters + " " + this.letter.toUpperCase() + "'s.";
			this.letterMsg = "Congratulations, you scored!";
			isInAnswer = true;
		} else if (numLetters == 1) {
			message = "There is one " + this.letter.toUpperCase() + ".";
			this.letterMsg = "Congratulations, you scored!";
			isInAnswer = true;
		} else {
			message = "There are no " + this.letter.toUpperCase() + "'s.";
			this.letterMsg = "That's too bad!";
			isInAnswer = false;
		}
		Answer.showBoard(this.boardAnswers, this.category, this.letterMsg);
		Answer.showLetters(this.consonants, this.vowels);
		System.out.println("\n\n" + message);
		return isInAnswer;
	}

	
	//Check if player entered a consonant that is available
	public String isConsonantLeft(Scanner scan, String letter, String[] cons) {
		//Add fields to make sure it's not
		boolean status = true;
		boolean isVowel = true;
		while (status = true) {
			letter.toUpperCase();
			if (!letter.equals("A") && !letter.equals("E") &&
					!letter.equals("I") && !letter.equals("O") &
					!letter.equals("U")) {
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
			} else {
				System.out.println("Please choose a consonant, not vowel.");
				letter = scan.nextLine();
			}
			
		}
	return letter;
	}
	
	//Check if player entered a vowel that is available
		public static boolean isVowelLeft(String letter, String[] vows) {
			boolean vowLeft = true;
			
			return vowLeft;
		}
	
	
	
	//check if player entered an integer
	public int isInt(Scanner scan, String a) {
		
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
	public static boolean isYesOrNo(Scanner scan, String a) {
		
		do {
			a = a.toLowerCase();
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
	
	//Pause for a moment
	public void pause(Scanner scan) {
		
		System.out.println("\n(Hit \"enter\" to continue.)");
		String a = scan.nextLine();
	}
}
