package wheeloffortune;

public class Wheel {
	
	String[] wheelPrizes = {"LOSE A TURN", "$800", "$350",
			"$450", "$700", "$300", "$600", "$5000", "$300",
			"$600", "$300", "$500", "$800", "$550", "$400",
			"$300", "$900", "$500", "FREE SPIN", "$900", 
			"BANKRUPT", "$600", "$400", "$300"};
	
	public void spinWheel (Player player) {
		int spin = (int)(Math.random() * 24);
		String prize = wheelPrizes[spin];
		
		System.out.println(spin); //test
		
		System.out.println("\n" + player.getName() + " spins the wheel." +
						"\nIt lands on: " + prize);
		
		//Put methods to determine if it's money or something else
		//If it's money, erase the dollar sign, convert to int,
		//then add that value to the player score.
	}

}
