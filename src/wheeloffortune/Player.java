package wheeloffortune;
import java.util.Scanner;

public class Player {
	Scanner scan = new Scanner(System.in);
	
	String name;
	int score;
	int playerNum;
	boolean turn = false;
	
	//Create new player
	public  void setPlayer(int i) {
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
