package com.problemstatement.practise;
import java.io.File;
import java.io.IOException;

public class MainTest {

	public static void main(String[] args) throws IOException {
		File file = new File("src//inputString.txt");
		// <{}>([)]
		BracketsProblemStatement ps = new BracketsProblemStatement(file);
		System.out.println(ps.getInputString());
		System.out.println("Balanced: " + ps.isBalanced() + "; MatchingPairs: " + ps.getMatchingPairCount());
	}
}
