package wheeloffortune;
import java.util.Scanner;

public class Player {
	
	String name;
	int score = 0;
	int totalScore = 0;
	int playerNum;
	int freeSpins = 0;
	boolean turn = false;
	
	//Create new player
	public  void setPlayer(Scanner scan, int i) {
			//System.out.print("Player" + 1); //test
			this.setNum(i);
			System.out.println("\nWhat is your name, " + 
					this.getNum() + "?: ");
			this.setName(scan.nextLine());	
			System.out.println("Thank you, " + this.getName() + ".");
			if (this.playerNum == 1) {
				this.turn = true;
			} else {
				this.turn = false;
			}
	}
	
	public void startNewTurn() {
		System.out.println("\nPlayer " + this.playerNum + ": " +
							this.name + ", it's your turn!");
		System.out.println("Score: $" + this.score);
		if (this.freeSpins > 0) {
			System.out.println("Free Spins: " + this.freeSpins);
		}
		this.whatDo();
		
	}
	
	public void whatDo() {
		System.out.println("\nWhat would you like to do?" +
				"\n(Type a number.) \n\n1. Spin Wheel" + 
				"\n2. Choose Vowel \n3. Solve");
	}
	
	public boolean useFreeSpin(Scanner scan) {
		boolean useSpin = false;
		System.out.println("\nWould you like to use a free spin?: ");
		useSpin = Game.isYesOrNo(scan, scan.nextLine());
		if (useSpin) {
			this.freeSpins -= 1;
		}
		return useSpin;
	}
	
	//Player number
	public void setNum(int num) {
		this.playerNum = num;
	}
	
	public String getNum() {
		return "Player " + playerNum;
	}
	
	//Player name
	public void setName(String playerName) {
		this.name = playerName;
	}
	
	public String getName() {
		return name;
	}
	
	//Player score
	public void setScore(int playerScore) {
		this.score = playerScore;
	}
	
	public int getScore() {
		return score;
	}
	
	//Player total game score
		public void setTotalScore(int playerTotalScore) {
			this.totalScore = playerTotalScore;
		}
		
		public int getTotalScore() {
			return totalScore;
		}
	
	//Player turn
	public void isTurn() {
		this.turn = true;
	}
	
	public void isNotTurn() {
		this.turn = false;
	}
	
	public boolean checkTurn() {
		return turn;
	}
	
	public void addFreeSpin() {
		freeSpins += 1;
	}
	public void setFreeSpin(int a) {
		freeSpins = a;
	}
	
	public int getFreeSpins () {
		return freeSpins;
	}

}
