package com.bigram;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Program to compute a histogram of the bigrams in the text.
 * @author Valentina Palghadmal
 */
public class Bigram {

	private static final Logger LOG = Logger.getLogger("Bigram");
	private static final String pattern = "[^a-zA-Z0-9\\-\\s]";
	private static final String patternExtraSpaces = "\\s+";

	public static void main(String[] args) {
		// Check to see if there is missing filename
		if (args.length < 1) {
			LOG.log(Level.SEVERE, "Missing Filename");
			return;
		}

		LinkedHashMap<String, Integer> bigramMap = callBigram(args[0]);
		printBigram(bigramMap);

	}

	/**
	 * This method checks whether filename is valid and returns the histogram in
	 * format of linked hash map
	 * 
	 * @param filename
	 *            - String
	 * @return LinkedHashMap<String, Integer>
	 */
	public static LinkedHashMap<String, Integer> callBigram(String filename) {
		if (filename == null || filename == "") {
			LOG.log(Level.SEVERE, "Missing Filename");
			return null;
		}
		// Check if file path is valid
		if (!(new File(filename)).exists()) {
			LOG.log(Level.SEVERE, "Invalid Filename or File path");
			return null;
		}

		String text = readFile(filename);
		// Check if file is empty
		if (text != null) {
			return calculateBigram(text);
		}
		return null;

	}

	/**
	 * This method read the file line by line and returns a entire contain as a
	 * string
	 * 
	 * @param filename
	 *            - String
	 * @return String
	 */
	private static String readFile(String filename) {
		// Reading the file using scanner class
		try {
			File file = new File(filename);
			Scanner sc = new Scanner(file);
			StringBuffer sb = new StringBuffer();
			while (sc.hasNext()) {
				sb.append(sc.nextLine().trim() + " ");
			}
			return sb.toString();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method calculates the histogram for the given text. This method
	 * changes the text to lower case, replaces special characters except hyphen
	 * and handles extra space
	 * 
	 * @param text
	 *            - String
	 * @return histogram - LinkedHashMap<String, Integer>
	 */
	static LinkedHashMap<String, Integer> calculateBigram(String text) {
		// Changes string to lower case to handle case sensitive
		// Handles extra space and replaces special characters except hyphen as
		// hyphen are consider part of words
		String words[] = text.toLowerCase().replaceAll(pattern, "").replaceAll(patternExtraSpaces, " ").split(" ");
		Map<String, Integer> bigramMap = new LinkedHashMap<String, Integer>();
		for (int i = 0; i < words.length - 1; i++) {
			String temp = words[i] + " " + words[i + 1];
			// If already present in map increment the count
			if (bigramMap.containsKey(temp)) {
				bigramMap.put(temp, bigramMap.get(temp) + 1);
			} else {
				bigramMap.put(temp, 1);
			}
		}
		return (LinkedHashMap<String, Integer>) bigramMap;

	}

	/**
	 * This method is just used to print Histograms of the biagrams calculated.
	 * 
	 * @param map
	 *            - LinkedHashMap<String, Integer>
	 */
	private static void printBigram(LinkedHashMap<String, Integer> map) {
		// print map using lambda function
		map.forEach((key, value) -> {
			System.out.println(key + " " + value);
		});
	}

}
