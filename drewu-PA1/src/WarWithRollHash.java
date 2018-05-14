// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add member fields and additional methods)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Rolling Hash implementation for finding substrings of
 * size 2k within U
 * @author drewu
 *
 */
public class WarWithRollHash
{
	/**
	 * Array to hold S values
	 */
	private String[] s;
	
	/**
	 * Variable to hold k
	 */
	private int k;
	
	/**
	 * Hash Table to hold values
	 */
	private Hashtable<String, Integer> S;
	
	/**
	 * Constructor for WarWithRollHash, initializes table
	 * @param s
	 * @param k
	 */
	public WarWithRollHash(String[] s, int k)
	{
		this.s = s;
		this.k = k;
		S = new Hashtable<String, Integer>();
		for(int i = 0; i < s.length; i++)
		{
			S.put(s[i], s[i].hashCode());
		}
	}
	
	/**
	 * Compute all of the 2k length strings with rolling hash
	 * @return
	 */
	public ArrayList<String> compute2k()
	{
		ArrayList<String> T = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < s.length; i++)
		{
			for(int j = 0; j < s.length; j++)
			{
				// If the substring proves to be valid, it is added to 
				// the list T
				if(isValid(s[i], s[j]))
				{
					sb.append(s[i]);
					sb.append(s[j]);
					T.add(sb.toString());
					sb = new StringBuilder();
				}
			}
		}
		return T;
	}
	
	/**
	 * Verify that the substrings are within s
	 * @param a
	 * @param b
	 * @return
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
		long[] rolls = hashRolls();
		int comp = S.get(s[0]);
		for(int i = 0; i < s.length; i++)
		{
			for(int j = 0; j < list.size(); j++)
			{
				int listComp = list.get(j).hashCode();
				if(comp == listComp)
				{
					flag++;
				}
			}
			if(i < S.size() - 1)
				comp = comp + (int)rolls[i];
		}
		// Will only return true if every single substring is valid
		return flag == list.size();
	}
	
	/**
	 * Grab hash values between keys
	 * @return
	 * 	Difference between each key in the hash table
	 *  reiterates Robin Karp behavior
	 */
	private long[] hashRolls()
	{
		long[] rolls = new long[S.size() - 1];
		for(int i = 0; i < rolls.length; i++)
		{
			rolls[i] = s[i+1].hashCode() - s[i].hashCode();
		}
		return rolls;
	}
	
}
