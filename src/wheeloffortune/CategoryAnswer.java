package wheeloffortune;

public enum CategoryAnswer {
	PHRASE("PHRASE", ""),
	PERSON("PERSON", ""),
	PEOPLE("PEOPLE", ""),
	PLACES("PLACE(S)", ""),
	EVENTS("EVENT(S)", ""),
	QUOTATION("QUOTATION", ""),
	THINGS("THING(S)", ""),
	TITLES("TITLE(S)", "");
	
	private String category;
	private String answer;
	
    private CategoryAnswer( String category, String answer)
    {
           this.category = category;
           this.answer = answer;
    }
      
    public String category() { return category; }
    public String answer() { return answer; }
    
    public void setAnswer(String text) {
    	this.answer = replaceSpaces(text);
    }
    
    public static String replaceSpaces(String text) {
		text = text.replaceAll("\\s+", "@");
		System.out.println(text + "\n"); // test
		return text;
	}

}
