package mini2;

import java.util.Scanner;

public class FunWithLoops {

	/**
	 * Return the number of iterations required until n is equal to 1 where each
	 * iteration updates n in the following way:
	 * 
	 * @param n
	 * @return The number of iterations of our loop
	 */
	public static int findStoppingTime(int n) {

		// Checking for if there is a non-positive or zero number entered
		if (n <= 0) {

			return -1;

		}

		int i = 0;
		for (i = 0; n != 1; i++) {

			// Divides number by two if even
			if (n % 2 == 0) {

				n = n / 2;

				// Else multiply by three, then add one
			} else {

				n = (n * 3) + 1;

			}

		}

		// Return number of iterations
		return i;

	}

	/**
	 * Return a string obtained by alternating characters from two given string,
	 * starting with the first character of the first string
	 * 
	 * @param s
	 * @param t
	 * @return The combination of the two strings
	 */
	public static String interleaveWithRuns(String s, String t) {

		if (s.equals("") && t.equals("")) {

			return "";

		}

		// String designated to store the combination of two input strings
		String newString = "";
		int max = Math.max(s.length(), t.length());
		int j = 0, k = 0;

		for (int i = 0; i < max; i++) {

			if (j < s.length()) {

				if (checkRun(s, j) > 1) {

					for (int index = 0; index < checkRun(s, j); index++) {

						newString += s.charAt(j);

					}
					j += checkRun(s, j);

				} else {

					newString += s.charAt(j);
					j++;

				}

			}

			if (k < t.length()) {

				if (checkRun(t, k) > 1) {

					for (int index = 0; index < checkRun(t, k); index++) {

						newString += t.charAt(k);

					}

					k += checkRun(t, k);

				} else {

					newString += t.charAt(k);
					k++;

				}

			}

		}

		return newString;

	}
	
	/**
	 * Helper method to check how many instances in a row of a character occur
	 * 
	 * @param s
	 * @param index
	 * @return
	 */
	private static int checkRun(String s, int index) {

		int run = 1;
		int record = 0;

		for (int i = index; i < s.length(); i++) {

			if (i == s.length() - 1) {

				record = run;

			} else {

				if (s.charAt(i) == s.charAt(i + 1)) {

					run++;

				} else if (s.charAt(i) != s.charAt(i + 1)) {

					record = run;
					return record;

				}

			}

		}

		return record;

	}

	/**
	 * Given a string of text containing numbers separated by commas, returns
	 * true if the numbers form an arithmetic sequence (a sequence in which each
	 * value differs from the previous one by a fixed number)
	 * 
	 * @param text
	 * @return Whether or not the series is an arithmetic series
	 */
	public static boolean isArithmetic(String text) {

		Scanner scanner = new Scanner(text);
		scanner.useDelimiter(",");
		int pattern = 0;
		int i = 0;
		int checkOne = 0;
		int checkTwo = 0;

		// This while loop is to determine the Arithmetic, if there is any
		while (scanner.hasNextInt() && i < 2) {

			if (i == 0) {

				pattern = scanner.nextInt();

			} else if (i == 1) {

				pattern = Math.abs(pattern - scanner.nextInt());

			}
			i++;

		}

		// While loop to continue as there is something left to scan
		while (scanner.hasNextInt()) {

			// Store a temporary variable
			checkOne = scanner.nextInt();

			if (scanner.hasNextInt()) {

				// Store the next temporary variable
				checkTwo = scanner.nextInt();

				// When the last two scans are an odd number, exception is
				// granted
			} else if (!scanner.hasNextInt() && (checkOne - checkTwo) == pattern || (checkOne - (2 * pattern)) == pattern) {

				scanner.close();
				return true;

			}

			// If our predetermined pattern is not present, terminate
			if (checkTwo - checkOne != pattern) {

				scanner.close();
				return false;

			}

		}

		// Exception case where our list had a non-int value
		if (scanner.hasNext() && !scanner.hasNextInt()) {

			scanner.close();
			return false;

		}

		// If there is nothing left to scan, return true
		scanner.close();
		return true;

	}

	/**
	 * Returns the length of the longest consecutive run of the same character
	 * in a string 's'
	 * 
	 * @param s
	 * @return The highest number of consecutive characters
	 */
	public static int longestRun(String s) {

		int run = 1;
		int record = 1;

		// For loop which iterates through the string
		for (int i = 0; i < s.length() - 1; i++) {

			// Check if the current and next character are the same
			if (s.charAt(i) == s.charAt(i + 1)) {

				run++;

				// If the next two characters are not the same, end our
				// current run, and if a previous run was shorter,
				// this run is the new longest run
			} else if (s.charAt(i) != s.charAt(i + 1)) {

				if (run > record) {

					record = run;

				}

				run = 1;

			}

		}

		if (run > record) {

			record = run;

		}

		return record;

	}

	/**
	 * Returns a string similar to the given string with all runs of
	 * consecutive, repeated characters removed
	 * 
	 * @param s
	 * @return The new string with any repeated characters removed
	 */
	public static String removeMultipleLetters(String s) {

		if (s.equals("")) {

			return "";

		}

		char next = s.charAt(0);

		// Initializing a newString with the first char of the input String
		String newString = "";
		newString += next;

		// Iterate through each character of the input string
		for (int i = 0; i < s.length(); i++) {

			// If we have non-similar chars, add them to our newString
			if (s.charAt(i) != next) {

				newString += s.charAt(i);
				next = s.charAt(i);

			}

		}

		return newString;

	}

	/**
	 * Reverses the order of words in a given string, where a "word" is any set
	 * of adjacent characters separated by whitespace
	 * 
	 * @param s
	 * @return The reversed string
	 */
	@SuppressWarnings("unused")
	public static String reverseWords(String s) {

		if (s.equals("")) {

			return "";

		}

		Scanner scanner = new Scanner(s);
		String newString = "";
		String temp = "";
		String endString = "";
		String finalString = "";

		// Backwards for loop, reverses our given string
		for (int i = s.length() - 1; i >= 0; i--) {

			newString += s.charAt(i);

		}

		// Scanner will iterate across the reversed string
		// again starting from the end of the string
		// reversing each word individually
		while (scanner.hasNext()) {

			temp = scanner.next();
			for (int i = temp.length() - 1; i >= 0; i--) {

				endString += temp.charAt(i);

			}
			endString += " ";

		}

		// Eliminate extra space at end of string
		endString = endString.substring(0, endString.length() - 1);

		// Reverse the entire String which was stored into endString
		// into a new variable once more, finalString
		for (int i = endString.length() - 1; i >= 0; i--) {

			finalString += endString.charAt(i);

		}

		scanner.close();
		return finalString;

	}

	/**
	 * Given a string, prints n lines of output as a triangle, where n is the
	 * length of the string
	 * 
	 * @param s
	 */
	public static void triangleWord(String s) {

		for (int i = 0; i < s.length(); i++) {

			for (int j = 0; j < s.length(); j++) {

				if (j <= s.length() - i - 2) {

					System.out.print(" ");

				} else {

					System.out.print(s.charAt(j));

				}

			}

			System.out.println("");

		}

	}

}
