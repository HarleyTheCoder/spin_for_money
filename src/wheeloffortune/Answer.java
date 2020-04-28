package wheeloffortune;

public class Answer {
	
	//Chooses a random category
	public static CategoryAnswer pickCategory() {
		CategoryAnswer nxtCategory;
		int i = (int)(Math.random() * CategoryAnswer.values().length + 1);
		
		//System.out.print(i); //test
		
		switch(i) {
		case 0: nxtCategory = CategoryAnswer.EVENTS;
				break;
		case 1: nxtCategory = CategoryAnswer.PEOPLE;
				break;
        case 2: nxtCategory = CategoryAnswer.PERSON;
				break;
        case 3: nxtCategory = CategoryAnswer.PHRASE;
				break;
        case 4: nxtCategory = CategoryAnswer.PLACES;
				break;
        case 5: nxtCategory = CategoryAnswer.QUOTATION;
				break;
        case 6: nxtCategory = CategoryAnswer.TITLES;
				break;
        case 7: nxtCategory = CategoryAnswer.THINGS;
				break;
        default: nxtCategory = CategoryAnswer.PHRASE;
				break;
		}
		//System.out.print("\n" + nxtCategory.category()); //test
		
		return nxtCategory;
	}
	
	//Chooses a random answer based on the chosen category.
	//Answers stored in this method for now
	public static void pickAnswer(CategoryAnswer categoryAnswer) {
    	int i;
    	String answer;
    	
    	String phraseAns[] = {"ROME WASN'T BUILT IN A DAY", "IT'S NEVER TOO LATE",
                "IGNORANCE IS BLISS", "A PENNY SAVED IS A PENNY EARNED", "WAIT AND SEE"};
    	
    	String personAns[] = {"ALL-AROUND NICE GUY", "AN INDEPENDENT WOMAN",
    			"ASPIRING ARTIST", "AVID READER", "CAMPAIGN SPOKESPERSON"};
    	
    	String peopleAns[] = {"A CREATURE OF HABIT", "A GOOD KISSER", "A HEAVY SLEEPER",
    			"A PAYING CUSTOMER", "A TOUGH COOKIE"};
    	
    	String placesAns[] = {"A CITY WITH A RICH HISTORY", "A DISTANT LAND",
    			"A STROLL ON THE BEACH", "AMISH COUNTRY", "ART GALLERY"};
    	
    	String eventsAns[] = {"A QUIET EVENING AT HOME", "A CAMPING TRIP", "A GOOD NIGHT'S SLEEP",
    			"HOT SUMMER DAY", "SCIENTIFIC BREAKTHROUGH"};
    	
    	String quotationAns[] = {"A LANNISTER ALWAYS PAYS HIS DEBTS", "I'LL HAVE WHAT SHE'S HAVING",
    			"THE BETTER TO HEAR YOU WITH, MY DEAR", "WE ARE GATHERED HERE TODAY", "DON'T MESS WITH TEXAS"};
    	
    	String thingsAns[] = {"A STROKE OF LUCK", "HOT-AIR BALLOON", "HYPOTHETICAL QUESTION",
    			"IMMUNE SYSTEM", "SUBLIMINAL MESSAGES"};
    	
    	String titlesAns[] = {"BREAKING BAD", "GAME OF THRONES", "GONE WITH THE WIND",
    			"MR. POPPER'S PENGUINS", "WILD HEARTS CAN'T BE BROKEN"};
    	
    	String errorAns[] = {"ERROR"};
    	
    	switch(categoryAnswer) {
    	case EVENTS: i = (int)(Math.random() * eventsAns.length);
    		answer = eventsAns[i];
			break;
    	case PEOPLE: i = (int)(Math.random() * peopleAns.length);
			answer = peopleAns[i];
			break;
    	case PERSON: i = (int)(Math.random() * personAns.length);
			answer = personAns[i];
			break;
    	case PHRASE: i = (int)(Math.random() * phraseAns.length);
			answer = phraseAns[i];
			break;
    	case PLACES: i = (int)(Math.random() * placesAns.length);
			answer = placesAns[i];
			break;
    	case QUOTATION: i = (int)(Math.random() * quotationAns.length);
			answer = quotationAns[i];
			break;
    	case TITLES: i = (int)(Math.random() * titlesAns.length);
			answer = titlesAns[i];
			break;
    	case THINGS: i = (int)(Math.random() * thingsAns.length);
			answer = thingsAns[i];
			break;

    	default: i = (int)(Math.random() * errorAns.length);
    		answer = errorAns[i];
			break;
    	}
    	categoryAnswer.setAnswer(answer);
    	
    	//System.out.println("\n" + i); //test
    	//System.out.println(categoryAnswer.answer()); //test
    }
	
	
	
	//store answer into array to make the display easier
	public static String[] storeAnswer(String answer) {
		String storedAnswer[] = new String[answer.length()];
		
		for (int i = 0; i < answer.length(); i++) {
			storedAnswer[i] = "" + answer.charAt(i);
			//System.out.print(i); //test
		}
		return storedAnswer;
	}
	
	
	
	//Calculate out approx board length
	public static int calcBoardLength(String[] board) {
		String string;
		String display = "";
		String display2 = "";
		int length;
		int a = 0;
		
		for (int i = 0; i < board.length; i++) {
			if (board[i].equals("\n\n")) {
				a = i;
				break;
			} else {
				//board[i] = board[i].replaceAll("\\s+", "@");
				display += board[i];
			}
		}
		for (int i = a; i < board.length; i++) {
			//board[i] = board[i].replaceAll("\\s+", "@");
			display2 += board[i];
		}
		display = display.replaceAll("\\s+", "@");
		display2 = display2.replaceAll("\\s+", "@");
		
		if (display.length() > display2.length()) {
			length = display.length() + 4;
		} else {
			length = display2.length() + 4;
		}
		
		//System.out.print(length); //test
		return length;
	}
	
	
	//Display available letters
	public static void showLetters(String[] cons, String[] vows) {
			System.out.println("Vowels: ");
			for (int i = 0; i < 5; i++) {
				System.out.print(vows[i] + " ");
			}
			
			System.out.println("\n\nConsonants: ");
			for (int i = 0; i < 21; i++) {
				if (i == 10) {
					System.out.print(cons[i] + "\n");
				} else {
					System.out.print(cons[i] + " ");
				}
			}
	}
	
	//DISPLAY THE BOARD
	public static void showBoard(String[] ansBoard, String category,
								String letterMsg) {
		int bLength = Answer.calcBoardLength(ansBoard);
		String border = "";
		String border2 = "";
		
		for (int a = 0; a <= bLength; a++) {
			border += "-";
			border2 += "=";
		}
		
		//math to center title
		int halfWord = (int)(category.length() / 2);
		int halfBoard = (int)(border.length() / 2);
		int leftSpace = halfBoard - halfWord - 1;
		String prntSpace = "";
		
		for (int a = 0; a <= leftSpace; a++) {
			prntSpace += " ";
		}
			
		System.out.println(border);
		System.out.println(prntSpace + category); //FORMAT THIS
		System.out.println(border2 + "\n");
		for (int i = 0; i < ansBoard.length; i++) {
			System.out.print(ansBoard[i]);
		}
		System.out.println("\n\n" + border);
		
		System.out.println(letterMsg);
		
	}
	
	//SET UP INITIAL BOARD DISPLAY
	public static String[] setNewDisplay(String[] answers, int halfBoard) {
		String ansBoard[] = new String[answers.length];
		boolean newLine = false;
		int spaceCnt = 0;
		
		for (int i = 0; i < answers.length; i++) {
			if (answers[i].equals("@")) {
				//MAY NEED TO FIX, takes care of spacing on board
				spaceCnt++;
				if (!newLine && (Math.abs(i - halfBoard) < 5 || spaceCnt == 3)) {
					ansBoard[i] = "\n\n";
					newLine = true;
				} else {
					ansBoard[i] = "   ";
					
				}
			} else if (answers[i].equals(",") || answers[i].equals("-") || answers[i].equals("'") ||
					answers[i].contentEquals("!") || answers[i].contentEquals("?") ||
					answers[i].contentEquals(".")){
				ansBoard[i] = answers[i];
			} else {
				ansBoard[i] = "[ ]";
			}
			//System.out.print(ansBoard[i]); //test
		}
		
		return ansBoard;
	}
	
	
	
	//Display everything - WORK ON THIS MORE
	public static void setDisplay (CategoryAnswer catAns) {
		String category = catAns.category();
		String answer = catAns.answer();
		String blank = "[ ]";
		
		StringBuffer display = new StringBuffer("");
		String storedAnswer[];
		String blankPhrase[];
		
	}
	
	//To check if the board is solved
	public static boolean isSolved (String[] board) {
		boolean solved = true;
		
		return solved;
	}
}