package wheeloffortune;
import java.util.Scanner;

public class Player {
	
	String name;
	int score = 0;
	int playerNum;
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
		System.out.println("\n\nPlayer " + this.playerNum + ": " +
							this.name + ", it's your turn!");
		System.out.println("Score: " + this.score);
		
		System.out.println("\nWhat would you like to do?" +
						"\n(Type a letter.) \n\nA. Spin Wheel" + 
						"\nB. Choose Vowel \nC. Solve");
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

}
