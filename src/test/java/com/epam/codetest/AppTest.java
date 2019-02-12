package com.epam.codetest;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.epam.codetest.utils.ReadFileUtil;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	private BracketsProblemStatement ps;
	private File file;
	private ReadFileUtil readFileUtil;
	
	@Before
	public void initSetup() {
		ps = new BracketsProblemStatement();
	}
	
	@Test
	public void testForInputStringFromGivenTextFile() {
		file = new File("src//test//resources//inputString.txt");
		ps.setFile(file);
		ps.setInputStringFromFile();
		ps.setFormattedString();
		System.out.println("Input String is : " + ps.getInputString());
		System.out.println("Balanced: " + ps.isBalanced() + "; MatchingPairs: " + ps.getMatchingPairCount());
		Assert.assertNotNull(ps.isBalanced());
		Assert.assertNotNull(ps.getMatchingPairCount());
	}
	
	@Test
	public void testForSampleStringFromGivenTextFile() {
		file = new File("src//test//resources//testStrings.txt");
		readFileUtil = new ReadFileUtil(file);
		List<String> demoStringList = readFileUtil.getStringListLineWiseFromFile();
		demoStringList.forEach(x -> {
			ps.setInputString(x);
			ps.setFormattedString();
			System.out.println("Input String is : " + ps.getInputString());
			System.out.println("Balanced: " + ps.isBalanced() + "; MatchingPairs: " + ps.getMatchingPairCount());
			Assert.assertNotNull(ps.isBalanced());
			Assert.assertNotNull(ps.getMatchingPairCount());
		});
	}
}
