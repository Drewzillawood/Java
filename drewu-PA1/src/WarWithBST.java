// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add member fields and additional methods)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

import java.util.ArrayList;

/**
 * BST implementation to handle War
 * @author drewu
 *
 */
public class WarWithBST
{
	/**
	 * Storing S inside BST
	 */
	private BinaryST S;
	
	/**
	 * Array to hold S for easy traversal
	 */
	private String[] s;
	
	/**
	 * Variable to hold k
	 */
	private int k;
	
	/**
	 * BST implementation for storing and comparing substrings
	 * @param s
	 * 	All k length substrings among U
	 * @param k
	 * 	Length of all substrings inside s
	 */
	public WarWithBST(String[] s, int k)
	{
		S = new BinaryST(s);
		this.s = s;
		this.k = k;
	}
	
	/**
	 * Computes all 2k length substrings of U
	 * @return
	 * 	ArrayList of all 2k length substrings among U
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
	 * Validates whether all substrings are within S
	 * @param a
	 * @param b
	 * @return
	 *  true if all substrings are valid
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
		for(int i = 0; i < list.size(); i++)
		{
			if(S.search(list.get(i)))
			{
				flag++;
			}
		}
		// Will only return true if every single substring is valid
		return flag == list.size();
	}
}

