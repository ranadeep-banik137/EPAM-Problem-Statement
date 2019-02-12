package com.problemstatement.practise;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BracketsProblemStatement {

	private static final String BACKWORD_DIAMOND_BRACKET = "<";
	private static final String FORWARD_DIAMOND_BRACKET = ">";
	private static final String OPEN_SQUARE_BRACKET = "[";
	private static final String CLOSE_SQUARE_BRACKET = "]";
	private static final String OPEN_CURLY_BRACE = "{";
	private static final String CLOSE_CURLY_BRACE = "}";
	private static final String OPEN_PARANTHESIS = "(";
	private static final String CLOSE_PARANTHESIS = ")";
	
	private int dummyIndex;
	private String inputString;
	
	public String getInputString() {
		return inputString;
	}

	public void setInputString(String inputString) {
		this.inputString = inputString;
	}

	private String formattedString;
	private int matchingPair;
	private int tempMatch;
	private Map<String, String> mapper;
	private List<String> closedBracketList;
	private List<String> listOfAlphanumerics;
	
	public BracketsProblemStatement(final String inputString) {
		setInputString(inputString);
		initiateAllBasics();
		formattedString = removeAllAlphanumerics();
	}
	
	public BracketsProblemStatement(final File file) {
		String s = "";
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNext()) {
				s = s + scanner.next();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		setInputString(s);
		initiateAllBasics();
		formattedString = removeAllAlphanumerics();
	}
	
	private void initiateAllBasics() {
		initaiteMapper();
		addClosedBracketList();
		createListOfAlphanumerics();
	}
	
	private void initaiteMapper() {
		mapper = new HashMap<>();
		mapper.put("<", ">");
		mapper.put("{", "}");
		mapper.put("[", "]");
		mapper.put("(", ")");
	}
	
	private void addClosedBracketList() {
		closedBracketList = new ArrayList<>();
		mapper.forEach((k,v) -> {
			closedBracketList.add(v);
		});
	}
	
	private void createListOfAlphanumerics() {
		listOfAlphanumerics = new ArrayList<>();
		for (char c = 'A'; c <= 'Z'; c++) {
			listOfAlphanumerics.add(String.valueOf(c));
		}
		for (int i = 0; i< 10; i++) {
			listOfAlphanumerics.add(String.valueOf(i));
		}
	}
	
	public int countOfChar(final String character, final String s) {
		int count = 0;
		for (int index = 0; index < s.length() ; index ++) {
			if (String.valueOf(s.charAt(index)).equals(character)) {
				count++;
			}
		}
		return count;
	}
	
	public String removeAllAlphanumerics() {
		String formattedString = "";
		for (int index = 0; index < this.inputString.length(); index++) {
			String charAtIndex  = String.valueOf(inputString.charAt(index));
			if (!listOfAlphanumerics.contains(charAtIndex.toUpperCase())) {
				formattedString = formattedString + charAtIndex;
			}
		}
		return formattedString;
	}
	
	public boolean isBalanced(final String s) {
		return ((this.countOfChar(BACKWORD_DIAMOND_BRACKET, s) == this.countOfChar(FORWARD_DIAMOND_BRACKET, s)) && (this.countOfChar(OPEN_SQUARE_BRACKET,s) == this.countOfChar(CLOSE_SQUARE_BRACKET, s)) && (this.countOfChar(OPEN_CURLY_BRACE,s) == this.countOfChar(CLOSE_CURLY_BRACE, s)) && (this.countOfChar(OPEN_PARANTHESIS, s) == this.countOfChar(CLOSE_PARANTHESIS, s)));
	}
	
	public String isBalanced() {
		int match = getMatchingCount();
		return isBalanced(inputString) && match > 0  ? "YES" : "NO";
	}
	
	public int calculateMatchingCount(int index, String s) {
		String substr = "";
		if (!closedBracketList.contains(String.valueOf(s.charAt(0))) && s.length() != 1) {
			for (int i = index; i < s.length(); i++) {
				String key = String.valueOf(s.charAt(index));
				substr = substr + String.valueOf(s.charAt(i));
				if (String.valueOf(s.charAt(i)).equals(mapper.get(key)) && isBalanced(substr)) {
					if (index + 1 == i) {
						tempMatch ++;
						index = i+1;
						substr = "";
					}
				}
			}
		} else {
			dummyIndex = 0;
		}
		if (!substr.isEmpty()) {
			dummyIndex ++;
			calculateMatchingCount(0, substr.length() > 1 ? substr.substring(1, substr.length() - 1) : substr);
		}
		matchingPair = tempMatch + dummyIndex;
		return matchingPair;
	}
	
	public int getMatchingCount() {
		return !closedBracketList.contains(String.valueOf(formattedString.charAt(0))) ? calculateMatchingCount(0, formattedString) : 0;
	}
	
	public int getMatchingPairCount() {
		return this.matchingPair;
	}
	
	public int calculatePairs(String input) {
		return isBalanced(input) ? this.countOfChar(FORWARD_DIAMOND_BRACKET, input) + this.countOfChar(CLOSE_CURLY_BRACE, input) + this.countOfChar(CLOSE_PARANTHESIS, input) + this.countOfChar(CLOSE_SQUARE_BRACKET, input): 0;
	}
	
	public int getTotalPairs(String input) {
		return this.countOfChar(FORWARD_DIAMOND_BRACKET, input) + this.countOfChar(CLOSE_CURLY_BRACE, input) + this.countOfChar(CLOSE_PARANTHESIS, input) + this.countOfChar(CLOSE_SQUARE_BRACKET, input);
	}
}
