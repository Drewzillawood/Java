// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add member fields and additional methods)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Hashing implementation for finding all 2k substrings
 * @author drewu
 */
public class WarWithHash
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
	 * Hashtable to hold values
	 */
	private Hashtable<String, Integer> S;
	
	/**
	 * Constructor for Hash
	 * @param s
	 * @param k
	 */
	public WarWithHash(String[] s, int k)
	{
		S = new Hashtable<String, Integer>();
		for(int i = 0; i < s.length; i++)
		{
			S.put(s[i], i);
		}
		this.s = s;
		this.k = k;
	}
	
	/**
	 * Computes and reports all 2k strings 
	 * @return
	 * 	Arraylist of all 2k length strings among U
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
	 * Checks on all strings within s in comparison to 
	 * the substrings between a and b
	 * @param a
	 * @param b
	 * @return
	 *  True if all substrings between a and b are validCC
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
		int flag = 0;
		// Compare the substrings between a and b against
		// all substrings supplied by S to see if they are
		// all valid
		for(int i = 0; i < list.size(); i++)
		{
			if(S.containsKey(list.get(i)))
			{
				flag++;
			}
		}
		// Will only return true if every single substring is valid
		return flag == list.size();
	}
}

