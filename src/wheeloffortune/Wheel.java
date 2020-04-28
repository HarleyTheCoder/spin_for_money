package wheeloffortune;
import java.util.Scanner;

public class Wheel {
	
	String[] wheelPrizes = {"LOSE A TURN", "800", "350",
			"450", "700", "300", "600", "5000", "300",
			"600", "300", "500", "800", "550", "400",
			"300", "900", "500", "FREE SPIN", "900", 
			"BANKRUPT", "600", "400", "300"};
	
	public void spinWheel (Scanner scan, Player player, Game game) {
		int spin = (int)(Math.random() * 24);
		boolean isInAnswer = false;
		String prize = wheelPrizes[spin];
		
		//System.out.println(spin); //test
		
		System.out.print("\n" + player.getName() + " spins the wheel!" +
						"\nIt lands on: ");
		if (prize.equals("LOSE A TURN") || prize.equals("FREE SPIN") ||
					prize.equals("BANKRUPT")) { 
			System.out.println(prize);
		} else {
			System.out.println("$" + prize);
		}
		
		
		//Display again if they landed on something
		if (!prize.contentEquals("LOSE A TURN") && !prize.contentEquals("BANKRUPT")) {
			game.pause(scan);
			Answer.showBoard(game.boardAnswers, game.category, "Here is the board again.");
			Answer.showLetters(game.consonants, game.vowels);
		}
		

		//Determine where it landed, do action depending
		if (prize.equals("LOSE A TURN")) {
			player.isNotTurn();
			System.out.println("\nSorry, " + player.getNum() +
								", your turn has ended.");
			
		} else if (prize.equals("BANKRUPT")) {
			player.isNotTurn();
			player.setScore(0);
			System.out.println("\nThat sucks, " + player.getNum() +
								". You've lost your prizes and your turn has ended.");
			
		} else if (prize.equals("FREE SPIN")) {
			player.addFreeSpin();
			if (player.checkTurn()) {
				isInAnswer = game.chooseConsonant(scan);
			}
			if (!isInAnswer) {
				player.isNotTurn();
			}
			
		} else { //Add monetary prize to player's score
			int money = Integer.parseInt(prize);
			isInAnswer = game.chooseConsonant(scan);
			if (isInAnswer) {
				player.setScore(player.getScore() + money);
				game.pause(scan);
			} else {
				player.isNotTurn();
			}
		}
		
		
		
	}

}
