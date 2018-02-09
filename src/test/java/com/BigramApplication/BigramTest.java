package com.BigramApplication;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;;

/**
 * Unit test for Bigram Class which generates a histogram of the bigrams in the
 * text.
 * 
 * @author Valentina Palghadmal
 */
public class BigramTest {
	Bigram bigram;

	/*
	 * Create a Bigram object before each test
	 */
	@Before
	public void beforeEachTest() {
		bigram = new Bigram();
	}

	/**
	 * test to check if one word present in file
	 */
	@Test
	public void checkOneWord() {
		Map<String, Integer> output = new LinkedHashMap<String, Integer>();
		Assert.assertEquals(output, bigram.calculateBigram("One"));
	}

	/**
	 * test to check if two word present in file
	 */
	@Test
	public void checkTwoWord() {
		Map<String, Integer> output = new LinkedHashMap<String, Integer>();
		output.put("the quick", 1);
		Assert.assertEquals(output, bigram.calculateBigram("The quick"));
	}

	/**
	 * test to check if sentence present in file
	 */
	@Test
	public void checkSentence() {
		Map<String, Integer> output = new LinkedHashMap<String, Integer>();
		output.put("the quick", 2);
		output.put("quick brown", 1);
		output.put("brown fox", 1);
		output.put("fox and", 1);
		output.put("and the", 1);
		output.put("quick blue", 1);
		output.put("blue hare", 1);
		Assert.assertEquals(output, bigram.calculateBigram("The quick brown fox and the quick blue hare"));
	}

	/**
	 * test to check specials characters present in file and ignore them
	 */
	@Test
	public void checkSpecialChar() {
		Map<String, Integer> output = new LinkedHashMap<String, Integer>();
		output.put("the quick", 2);
		output.put("quick brown", 1);
		output.put("brown fox", 1);
		output.put("fox and", 1);
		output.put("and the", 1);
		output.put("quick blue", 1);
		output.put("blue hare", 1);
		Assert.assertEquals(output, bigram.calculateBigram("The quick: brown, fox and the quick 'blue hare &&"));
	}

	/**
	 * test to check different cases present in file
	 */
	@Test
	public void checkCaseInsentive() {
		Map<String, Integer> output = new LinkedHashMap<String, Integer>();
		output.put("the quick", 4);
		output.put("quick the", 3);
		Assert.assertEquals(output, bigram.calculateBigram("THE QUICK the quick The Quick ThE QUIck"));
	}

	/**
	 * test to check hyphen in between words and consider it as whole word.
	 */
	@Test
	public void checkWithHyphen() {
		Map<String, Integer> output = new LinkedHashMap<String, Integer>();
		output.put("fifty-six bottles", 2);
		output.put("bottles of", 2);
		output.put("of pop", 2);
		output.put("pop on", 1);
		output.put("on the", 1);
		output.put("the wall", 1);
		output.put("wall fifty-six", 1);
		Assert.assertEquals(output,
				bigram.calculateBigram("Fifty-six bottles of pop on the wall, fifty-six bottles of pop."));
	}

	/**
	 * test to check multiple sentences in file and ignore them
	 */
	@Test
	public void checkMutliple() {
		Map<String, Integer> output = new LinkedHashMap<String, Integer>();
		output.put("fifty-six bottles", 2);
		output.put("bottles of", 2);
		output.put("of pop", 2);
		output.put("pop on", 1);
		output.put("on the", 1);
		output.put("the wall", 1);
		output.put("wall fifty-six", 1);
		Assert.assertEquals(output,
				bigram.calculateBigram("Fifty-six bottles of pop on the wall. fifty-six bottles of pop."));
	}

	/**
	 * test to check extra spaces present in file and discard them
	 */
	@Test
	public void checkSpace() {
		Map<String, Integer> output = new LinkedHashMap<String, Integer>();
		output.put("fifty-six bottles", 2);
		output.put("bottles of", 2);
		output.put("of pop", 2);
		output.put("pop on", 1);
		output.put("on the", 1);
		output.put("the wall", 1);
		output.put("wall fifty-six", 1);
		Assert.assertEquals(output,
				bigram.calculateBigram("Fifty-six     bottles of pop  \ton the wall.    fifty-six bottles of pop."));
	}

	/**
	 * test to check if file is empty
	 */
	@Test
	public void checkEmptyLine1() {
		Map<String, Integer> output = new LinkedHashMap<String, Integer>();
		Assert.assertEquals(output, bigram.calculateBigram(""));
	}

	/**
	 * test to check if file is empty
	 */
	@Test
	public void checkEmptyLine2() {
		Map<String, Integer> output = new LinkedHashMap<String, Integer>();
		Assert.assertEquals(output, bigram.calculateBigram("\t"));
	}

	/**
	 * test to check if file is valid
	 */
	@Test
	public void checkFileNameNull() {
		Assert.assertEquals(null, bigram.callBigram(null));
	}

	/**
	 * test to check if file is valid
	 */
	@Test
	public void checkFileNameInvalid() {
		Assert.assertEquals(null, bigram.callBigram("xyz.txt"));
	}

	/**
	 * test to check if file is valid
	 */
	@Test
	public void checkFileNameMissing() {
		Assert.assertEquals(null, bigram.callBigram(""));
	}

}
