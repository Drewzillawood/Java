// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add member fields and additional methods)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

import java.util.ArrayList;

/**
 * WarWithArray class finds all 2k substrings from a set of S substrings 
 * all of length k
 * @author drewu
 *
 */
public class WarWithArray
{
	
	/**
	 * Array to hold S values
	 */
	private String[] S;
	
	/**
	 * Variable to hold k
	 */
	private int k;
	
	/**
	 * WarWithArray
	 * This class will contain a method to return T. This class uses array to store S. You must not use
	 * any other data structure to store S. This class will have following constructor and method.
	 * @param s 
	 * 	Array of all k length substrings within U
	 * @param k
	 * 	Length of substrings
	 */
	public WarWithArray(String[] s, int k)
	{
		S = s;
		this.k = k;
	}
	
	/**
	 * Builds an arrayList of all 2k length strings from S
	 * @return
	 * 	ArrayList of 2k length substrings
	 */
	public ArrayList<String> compute2k()
	{
		ArrayList<String> T = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < S.length; i++)
		{
			for(int j = 0; j < S.length; j++)
			{
				// If the substring proves to be valid, it is added to 
				// the list T
				if(isValid(S[i], S[j]))
				{
					sb.append(S[i]);
					sb.append(S[j]);
					T.add(sb.toString());
					sb = new StringBuilder();
				}
			}
		}
		return T;
	}
	
	/**
	 * Helper method checks if any substring between the two
	 * is contained within S
	 * @param a
	 * @param b
	 * @return
	 * 	Index at which a valid substring can be obtained
	 *  Returns -1 if no valid substring exists within S
	 */
	private boolean isValid(String a, String b)
	{
		ArrayList<String> list = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		// Collect all substrings shared between a and b
		for(int i = 1; i < k; i++)
		{
			sb.append(a.substring(i));
			sb.append(b.substring(0, i));
			list.add(sb.toString());
			sb = new StringBuilder();
		}
		// Compare the substrings between a and b against
		// all substrings supplied by S to see if they are
		// all valid
		int flag = 0;
		for(int i = 0; i < S.length; i++)
		{
			for(int j = 0; j < list.size(); j++)
			{
				if(S[i].equals(list.get(j)))
				{
					flag++;
				}
			}
		}
		// Will only return true if every single substring is valid
		return flag == list.size();
	}
}