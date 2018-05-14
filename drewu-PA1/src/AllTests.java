
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.rules.Timeout;
import org.junit.runner.Description;

/*
Tests are at the bottom.  They look like this:

	@Test public void distinctSizeNoDups()
	{
		p = 9;

		assertTrue("distinctSize() returns wrong value for tree with no duplicates", bst0.distinctSize() == bstRef0.distinctSize());
	}

	The test fails iff the last argument to the assert is false.

	JUnit is not meant for tracking point totals, so we are forced to use crude methods.  We use p to track the point value of the current
	question; in the TestWatcher instance, we reset p to 9000 after each question so we can detect if we forget to set p in the next question.

	We track both lost points and earned points so when we output we can check whether the totals are correct.

*/

public class AllTests
{
	
	// general functionality
	static int totalPoints = 250;

	static int p = 9001;
	static int partial = 0;
	static int totalEarned = 0;
	static int totalLost = 0;
	static StringBuilder builder = new StringBuilder();

	// BST test-specific data structures
	static int shortBSTWordlistSize = 128;
	static int longBSTWordlistSize = 50000;

	static ArrayList<String> words;
	static ArrayList<String> wordsShort;
	static ArrayList<String> wordsShortNoDups;
	static ArrayList<String> wordsToAdd;
	static ArrayList<String> wordsToRemove;
	static ArrayList<String> wordsLong;
	static ArrayList<String> wordsToAddLong;

	static String[] wordsLongArray;
	static String[] wordsToAddLongArray;

	static BinaryST bst0;
	static BinaryST bst1;
	static BinaryST bst2;

	static BinarySTRef bstRef0;
	static BinarySTRef bstRef1;
	static BinarySTRef bstRef2;

	static BinaryST bstDups;
	static BinarySTRef bstDupsRef;

	static BinaryST bstLong0;
	static BinaryST bstLong1;

	static BinarySTRef bstLongRef0;
	static BinarySTRef bstLongRef1;

	static long constructorDelta;
	static long addRemoveDelta;
	static long freqDelta;
	static long rankDelta;

	static long constructorDeltaRef;
	static long addRemoveDeltaRef;
	static long freqDeltaRef;
	static long rankDeltaRef;

	// war-specific data structures
  static String warTestsFile = "./data/tests.txt";
  static String warSolsFile = "./data/testsols.txt";

  static List<String[]> tests = new LinkedList<String[]>();
  static int[] testK = {3,3,4,5,6,10,7,11};
  static List<String[]> answers = new LinkedList<String[]>();

  static double arrayWarRatio = Double.POSITIVE_INFINITY;
  static double bstWarRatio = Double.POSITIVE_INFINITY;
  static double hashWarRatio = Double.POSITIVE_INFINITY;
  static double rollhashWarRatio = Double.POSITIVE_INFINITY;



	@Rule
	public Timeout timeout = Timeout.seconds(60);

	@Rule
	public TestWatcher watcher = new TestWatcher()
	{
		@Override
		protected void succeeded(Description description)
		{

			if(p > totalPoints)
			{
				System.out.println("ERROR: POINT VALUES NOT RESET!"); // this ensures that we don't miss resetting point values
			}

			totalEarned += p;
			p = 9000;
		}

		@Override
		protected void failed(Throwable e, Description description)
		{

			if(p > totalPoints)
			{
				System.out.println("ERROR: POINT VALUES NOT RESET!"); // this ensures that we don't miss resetting point values
			}

	    if(partial > 0)
	    {
	      builder.append("test " + description + " partially failed with exception " + e + " (-" + (p - partial) + ");\n");

	      totalLost+= p - partial;
	      totalEarned += partial;
	      partial = 0;
	    }
	    else
	    {
        builder.append("test " + description + " failed with exception " + e + " (-" + p + ");\n");
        totalLost += p;
	    }

			p = 9000;
		}
	};

	@SuppressWarnings("unused")
	@AfterClass
	public static void printResults()
	{
		if(totalEarned + totalLost != totalPoints)
		{
			System.out.println("\n\nERROR!  Earned " + totalEarned + " but lost " + totalLost + "; should add to " + totalPoints);
			System.out.println("missing " + (totalPoints - totalEarned - totalLost));
		}
		else
		{
			System.out.println("\n\nearned " + totalEarned + " / " + totalPoints);
		}

		System.out.println("\n" + builder);

		System.out.println("student:reference timing ratios (smaller is better for student)");
		System.out.println("bstConstructor\tbstAddRemove\tbstFreq\tbstRank\tWarWithArray\tWarWithBst\tWarWithHash\tWarWithRollHash");

		long totalDelta = constructorDelta + addRemoveDelta + freqDelta + rankDelta;
		long totalDeltaRef = constructorDeltaRef + addRemoveDeltaRef + freqDeltaRef + rankDeltaRef;

		System.out.println("" + 
			(float)constructorDelta/(float)constructorDeltaRef + "\t" + 
			(float)addRemoveDelta/(float)addRemoveDeltaRef + "\t" + 
			(float)freqDelta/(float)freqDeltaRef + "\t" + 
			(float)rankDelta/(float)rankDeltaRef + "\t" +

      arrayWarRatio + "\t" +
      bstWarRatio + "\t" +
      hashWarRatio + "\t" +
      rollhashWarRatio + "\t"
		);

	}


	// ================================================================
	// SETUP
	// ================================================================
	@BeforeClass
	public static void setUp() throws Exception
	{
		boolean success = true;

		words = new ArrayList<String>();
		wordsShort = new ArrayList<String>();
		wordsShortNoDups = new ArrayList<String>();
		wordsToAdd = new ArrayList<String>();
		wordsToRemove = new ArrayList<String>();
		wordsLong = new ArrayList<String>();
		wordsToAddLong = new ArrayList<String>();

		HashSet<String> wordsSeen = new HashSet<String>();

		// BST SETUP ****************

		// load words from file
		try
		{
			ArrayList<String> words = new ArrayList<String>();

			// adapted from https://stackoverflow.com/questions/5868369/how-to-read-a-large-text-file-line-by-line-using-java
			FileInputStream fstream = new FileInputStream("data/principia");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			String strLine;
			// this while loop is very inefficient
			while ((strLine = br.readLine()) != null)
			{
				words.add(strLine);

				// add to short list
				if(wordsShort.size() < shortBSTWordlistSize)
				{
					wordsShort.add(strLine);
				}

				// add to short list with no duplicates
				if(!wordsSeen.contains(strLine))
				{
					wordsSeen.add(strLine);
					if(wordsShortNoDups.size() < shortBSTWordlistSize)
					{
						wordsShortNoDups.add(strLine);
					}
					// after we've found all no-duplicate words, find an additional three to test add
					else if(wordsToAdd.size() < 3)
					{
						wordsToAdd.add(strLine);
					}
				}

				// add to long lists
				if(wordsLong.size() < longBSTWordlistSize)
				{
					wordsLong.add(strLine);
				}
				// after we've found all words for the long list, find an additional chunk to add
				else if(wordsToAddLong.size() < longBSTWordlistSize)
				{
					wordsToAddLong.add(strLine);
				}
			}
			br.close();

			wordsToRemove.add(wordsShortNoDups.get(0));
			wordsToRemove.add(wordsShortNoDups.get(1));
			wordsToRemove.add(wordsShortNoDups.get(2));


			// System.out.println("short word list: ");
			// for(String s : wordsShort)
			// {
			// 	System.out.println(s);
			// }

			// System.out.println("\nshort word list no dups:");
			// for(String s : wordsShortNoDups)
			// {
			// 	System.out.println(s);
			// }

		}
		catch(Exception e)
		{
			System.out.println("SETUP ERROR: couldn't load wordlists");
			success = false;
		}


		// construct base bsts with no dups
		try
		{
			bst0 = new BinaryST();
			bstRef0 = new BinarySTRef();

			bst1 = new BinaryST();
			bstRef1 = new BinarySTRef();

			bst2 = new BinaryST();
			bstRef2 = new BinarySTRef();

			for(String s : wordsShortNoDups)
			{
				bst0.add(s);
				bstRef0.add(s);

				bst1.add(s);
				bstRef1.add(s);

				bst2.add(s);
				bstRef2.add(s);
			}
		}
		catch(Exception e)
		{
			System.out.println("SETUP ERROR: couldn't initialize bsts with no duplicates");
			success = false;
		}
		
		// construct no-dups bsts with added elements
		try
		{
			for(String s : wordsToAdd)
			{
				bst1.add(s);
				bstRef1.add(s);
				
				bst2.add(s);
				bstRef2.add(s);
			}
		}
		catch(Exception e)
		{
			System.out.println("SETUP ERROR: couldn't add elements to no-duplicate BSTs");
			success = false;
		}

		// construct no-dups bst with removed elements
		try
		{
			for(String s : wordsToRemove)
			{
				bst2.remove(s);
				bstRef2.remove(s);
			}
		}
		catch(Exception e)
		{
			System.out.println("SETUP ERROR: couldn't remove elements from no-duplicate BSTs");
			success = false;
		}

		// construct bst with duplicates
		try
		{
			bstDups = new BinaryST();
			bstDupsRef = new BinarySTRef();

			for(String s : wordsShort)
			{
				bstDups.add(s);
				bstDupsRef.add(s);
			}
		}
		catch(Exception e)
		{
			System.out.println("SETUP ERROR: couldn't construct BSTs with duplicates");
			success = false;
		}


		// construct efficiency bsts
		try
		{
			wordsLongArray = wordsLong.toArray(new String[wordsLong.size()]);
			wordsToAddLongArray = wordsToAddLong.toArray(new String[wordsToAddLong.size()]);

			bstLong0 = new BinaryST(wordsLongArray);
			bstLong1 = new BinaryST(wordsLongArray);

			bstLongRef0 = new BinarySTRef(wordsLongArray);
			bstLongRef1 = new BinarySTRef(wordsLongArray);
		}
		catch(Exception e)
		{
			System.out.println("SETUP ERROR: couldn't construct large BSTs for efficiency tests");
			success = false;
		}

		// construct stage 2 efficiency bsts
		try
		{
			for(String s : wordsToAddLongArray)
			{
				bstLong1.add(s);
				// bstLong2.add(s);

				bstLongRef1.add(s);
				// bstLongRef2.add(s);
			}
		}
		catch(Exception e)
		{
			System.out.println("SETUP ERROR: couldn't add words to BSTs for second phase of efficiency tests");
			success = false;
		}


		// WAR SETUP ****************
    try
    {
        List<String> strs = Files.readAllLines(Paths.get(warTestsFile));
        for (String str : strs) {
            tests.add(str.split(","));
        }
    }
		catch(Exception e)
		{
			System.out.println("SETUP ERROR: couldn't load war tests file");
			success = false;
		}

    try
    {
        List<String> strs = Files.readAllLines(Paths.get(warSolsFile));
        for (String str : strs) {
            answers.add(str.split(","));
        }
    }
		catch(Exception e)
		{
			System.out.println("SETUP ERROR: couldn't load war sols file");
			success = false;
		}




		if(!success)
		{
			System.out.println("WARNING: setup failed for at least one setup stage - consider investigating!");
		}
		
		return;
	}


	// utility functions
	public boolean arrayMatch(String[] a, String[] b)
	{
		if(a.length != b.length)
		{
			return false;
		}

		for(int i = 0; i < a.length; i++)
		{
			if(a[i].compareTo(b[i]) != 0)
			{
				return false;
			}
		}

		return true;
	}














	// ================================================================
	// TEST BST NO DUPLICATES
	// ================================================================
	@Test public void distinctSizeNoDups()
	{
		p = 9;

		assertTrue("distinctSize() returns wrong value for tree with no duplicates", bst0.distinctSize() == bstRef0.distinctSize());
	}

	@Test public void sizeNoDups()
	{
		p = 9;

		assertTrue("size() returns wrong value for tree with no duplicates", bst0.size() == bstRef0.size());
	}

	@Test public void heightNoDups()
	{
		p = 9;

		assertTrue("height() returns wrong value for tree with no duplicates", bst0.height() == bstRef0.height());
	}

	@Test public void freqLeftNoDups()
	{
		p = 3;

		String left = wordsShortNoDups.get(0);
		assertTrue("frequency() returns wrong value for string 0 for tree with no duplicates", bst0.frequency(left) == bstRef0.frequency(left));
	}

	@Test public void freqMidNoDups()
	{
		p = 2;

		String mid = wordsShortNoDups.get(wordsShortNoDups.size()/3);

		// // verification output
		// System.out.println("\nrankMidNoDups word: " + mid);
		// System.out.println("  student gave " + bst0.rankOf(mid));
		// System.out.println("  ref gave " + bstRef0.rankOf(mid));
		// System.out.println("  student search " + bst0.search(mid));

		assertTrue("frequency() returns wrong value for string 1 for tree with no duplicates", bst0.frequency(mid) == bstRef0.frequency(mid));
	}

	@Test public void freqMid2NoDups()
	{
		p = 2;

		String mid = wordsShortNoDups.get(wordsShortNoDups.size()*2/3);
		assertTrue("frequency() returns wrong value for string 2 for tree with no duplicates", bst0.frequency(mid) == bstRef0.frequency(mid));
	}

	@Test public void freqRightNoDups()
	{
		p = 2;

		String right = wordsShortNoDups.get(wordsShortNoDups.size() - 1);
		assertTrue("frequency() returns wrong value for string 3 for tree with no duplicates", bst0.frequency(right) == bstRef0.frequency(right));
	}

	@Test public void inOrderNoDups()
	{
		p = 9;

		assertTrue("inorder traversal wrong for tree with no duplicates", arrayMatch(bst0.inOrder(), bstRef0.inOrder()));
	}

	@Test public void preOrderNoDups()
	{
		p = 9;

		assertTrue("preorder traversal wrong for tree with no duplicates", arrayMatch(bst0.preOrder(), bstRef0.preOrder()));
	}

	@Test public void rankLeftNoDups()
	{
		p = 2;

		String left = "A";
		assertTrue("rankOf() returns wrong value for rank-0 string for tree with no duplicates", bst0.rankOf(left) == bstRef0.rankOf(left));
	}

	@Test public void rankMidNoDups()
	{
		p = 3;

		String mid = wordsShortNoDups.get(wordsShortNoDups.size()/3);
		assertTrue("rankOf() returns wrong value for test string 1 for tree with no duplicates", bst0.rankOf(mid) == bstRef0.rankOf(mid));
	}

	@Test public void rankMid2NoDups()
	{
		p = 3;

		String mid = wordsShortNoDups.get(wordsShortNoDups.size()*2/3);
		assertTrue("rankOf() returns wrong value for test string 2 for tree with no duplicates", bst0.rankOf(mid) == bstRef0.rankOf(mid));
	}

	@Test public void rankRightNoDups()
	{
		p = 2;

		String right = "ZZZZZZZZ";

		// verification output
		// System.out.println("rankRightNoDups word: " + right);
		// System.out.println("  student gave " + bst0.rankOf(right));
		// System.out.println("  ref gave " + bstRef0.rankOf(right));
		// System.out.println("  student search " + bst0.search(right));

		assertTrue("rankOf() returns wrong value for max rank string for tree with no duplicates", bst0.rankOf(right) == bstRef0.rankOf(right));
	}


	void searchForAddedNoDups(int i)
	{
		p = 3;

		String add = wordsToAdd.get(i);
		assertTrue("word " + add + " not found after adding for tree with no duplicates", bst1.search(add));
	}

	@Test public void searchForAdded0NoDups(){searchForAddedNoDups(0);}
	@Test public void searchForAdded1NoDups(){searchForAddedNoDups(1);}
	@Test public void searchForAdded2NoDups(){searchForAddedNoDups(2);}

	void searchForNotAddedNoDups(String word)
	{
		p = 3;

		assertTrue("non-added word " + word + " found after adding other words for tree with no duplicates", !bst1.search(word));
	}

	@Test public void searchForNotAdded0NoDups(){searchForNotAddedNoDups("FIRSTUNLIKELYWORD");}
	@Test public void searchForNotAdded1NoDups(){searchForNotAddedNoDups("SECONDUNLIKELYWORD");}
	@Test public void searchForNotAdded2NoDups(){searchForNotAddedNoDups("THIRDUNLIKELYWORD");}

	void searchForRemovedNoDups(int i)
	{
		p = 3;

		String rem = wordsToRemove.get(i);
		assertTrue("removed word " + rem + " found after removing for tree with no duplicates", !bst2.search(rem));
	}

	@Test public void searchForRemoved0NoDups(){searchForRemovedNoDups(0);}
	@Test public void searchForRemoved1NoDups(){searchForRemovedNoDups(1);}
	@Test public void searchForRemoved2NoDups(){searchForRemovedNoDups(2);}

	void searchForNotRemovedNoDups(int i)
	{
		p = 3;

		String word = wordsShortNoDups.get(i + 3);
		assertTrue("non-removed word " + word + " not found after removing other words for tree with no duplicates", bst2.search(word));
	}

	@Test public void searchForNotRemoved0NoDups(){searchForNotRemovedNoDups(0);}
	@Test public void searchForNotRemoved1NoDups(){searchForNotRemovedNoDups(1);}
	@Test public void searchForNotRemoved2NoDups(){searchForNotRemovedNoDups(2);}


	// ================================================================
	// TESTS BST DUPLICATES
	// ================================================================

	@Test public void distinctSizeDups()
	{
		p = 9;

		assertTrue("distinctSize() returns wrong value for tree with duplicates", bstDups.distinctSize() == bstDupsRef.distinctSize());
	}

	@Test public void sizeDups()
	{
		p = 8;

		assertTrue("size() returns wrong value for tree with duplicates", bstDups.size() == bstDupsRef.size());
	}

	@Test public void heightDups()
	{
		p = 8;

		assertTrue("height() returns wrong value for tree with duplicates", bstDups.height() == bstDupsRef.height());
	}

	@Test public void freqLeftDups()
	{
		p = 3;

		String left = wordsShort.get(0);
		assertTrue("frequency() returns wrong value for test string 0 for tree with duplicates", bstDups.frequency(left) == bstDupsRef.frequency(left));
	}

	@Test public void freqMidDups()
	{
		p = 3;

		String mid = wordsShort.get(wordsShort.size()/2);
		assertTrue("frequency() returns wrong value for test string 1 with duplicates", bstDups.frequency(mid) == bstDupsRef.frequency(mid));
	}

	@Test public void freqRightDups()
	{
		p = 3;

		String right = wordsShort.get(wordsShort.size() - 1);
		assertTrue("frequency() returns wrong value for test string 2 string for tree with duplicates", bstDups.frequency(right) == bstDupsRef.frequency(right));
	}

	@Test public void inOrderDups()
	{
		p = 8;

		assertTrue("inorder traversal wrong for tree with no duplicates", arrayMatch(bstDups.inOrder(), bstDupsRef.inOrder()));
	}

	@Test public void rankLeftDups()
	{
		p = 2;

		String left = "A";
		assertTrue("rankOf() returns wrong value for rank-0 string for tree with duplicates", bstDups.rankOf(left) == bstDupsRef.rankOf(left));
	}

	@Test public void rankMidDups()
	{
		p = 2;

		String mid = wordsShort.get(wordsShort.size()/3);

		// // verification output
		// System.out.println("\nrankMidDups word: " + mid);
		// System.out.println("  student gave " + bstDups.rankOf(mid));
		// System.out.println("  ref gave " + bstDupsRef.rankOf(mid));
		// System.out.println("  student search " + bstDups.search(mid));

		assertTrue("rankOf() returns wrong value for test string 0 for tree with duplicates", bstDups.rankOf(mid) == bstDupsRef.rankOf(mid));
	}

	@Test public void rankMid2Dups()
	{
		p = 2;

		String mid = wordsShort.get(wordsShort.size()*2/3);

		// // verification output
		// System.out.println("\nrankMid2Dups word: " + mid);
		// System.out.println("  student gave " + bstDups.rankOf(mid));
		// System.out.println("  ref gave " + bstDupsRef.rankOf(mid));
		// System.out.println("  student search " + bstDups.search(mid));

		assertTrue("rankOf() returns wrong value for test string 0 for tree with duplicates", bstDups.rankOf(mid) == bstDupsRef.rankOf(mid));
	}

	@Test public void rankRightDups()
	{
		p = 2;

		String right = "ZZZZZZZZ";
		assertTrue("rankOf() returns wrong value for max rank string for tree with duplicates", bstDups.rankOf(right) == bstDupsRef.rankOf(right));
	}

	// ================================================================
	// TEST EFFICIENCY
	// ================================================================


	@SuppressWarnings("unused")
	@Test public void testConstructorEfficiency()
	{
		p = 0;
		constructorDelta = 60*1000000;

		long startTime = System.nanoTime();
		for(int i = 0; i < 100; i++)
		{
			BinaryST bst = new BinaryST(wordsLongArray);
		}
		constructorDelta = System.nanoTime() - startTime;
	}

	@SuppressWarnings("unused")
	@Test public void testConstructorEfficiencyRef()
	{
		p = 0;
		constructorDeltaRef = 60*1000000;

		long startTime = System.nanoTime();
		for(int i = 0; i < 100; i++)
		{
			BinarySTRef bst = new BinarySTRef(wordsLongArray);
		}
		constructorDeltaRef = System.nanoTime() - startTime;
	}


	@Test public void testAddRemoveEfficiency()
	{
		p = 0;
		addRemoveDelta = 60*1000000;

		long startTime = System.nanoTime();
		for(int i = 0; i < 100; i++)
		{
			for(String s : wordsToAddLongArray)
			{
				bstLong0.add(s);
				bstLong0.remove(s);
			}
		}
		addRemoveDelta = System.nanoTime() - startTime;
	}

	@Test public void testAddRemoveEfficiencyRef()
	{
		p = 0;
		addRemoveDeltaRef = 60*1000000;

		long startTime = System.nanoTime();
		for(int i = 0; i < 100; i++)
		{
			for(String s : wordsToAddLongArray)
			{
				bstLongRef0.add(s);
				bstLongRef0.remove(s);
			}
		}
		addRemoveDeltaRef = System.nanoTime() - startTime;
	}

	@Test public void testFreqEfficiency()
	{
		p = 0;
		freqDelta = 60*1000000;

		// timing
		long startTime = System.nanoTime();
		for(int i = 0; i < 100; i++)
		{
			for(String s : wordsToAddLongArray)
			{
				bstLong1.frequency(s);
			}
		}
		freqDelta = System.nanoTime() - startTime;
	}

	@Test public void testFreqEfficiencyRef()
	{
		p = 0;
		freqDeltaRef = 60*1000000;

		// timing
		long startTime = System.nanoTime();
		for(int i = 0; i < 100; i++)
		{
			for(String s : wordsToAddLongArray)
			{
				bstLongRef1.frequency(s);
			}
		}
		freqDeltaRef = System.nanoTime() - startTime;
	}

	@Test public void testRankEfficiency()
	{
		p = 0;
		rankDelta = 60*1000000;

		// timing
		long startTime = System.nanoTime();
		for(int i = 0; i < 100; i++)
		{
			for(String s : wordsToAddLongArray)
			{
				bstLong1.rankOf(s);
			}
		}
		rankDelta = System.nanoTime() - startTime;
	}

	@Test public void testRankEfficiencyRef()
	{
		p = 0;
		rankDeltaRef = 60*1000000;

		// timing
		long startTime = System.nanoTime();
		for(int i = 0; i < 100; i++)
		{
			for(String s : wordsToAddLongArray)
			{
				bstLongRef1.rankOf(s);
			}
		}
		rankDeltaRef = System.nanoTime() - startTime;
	}










	// ================================================================
	// WAR TESTS
	// ================================================================

  @Test public void WarWithArrayTest0()
  {
      int test = 0;
      p = 5;
      boolean success = warCorTest(WarWithArray.class, test);

      assertTrue(success);
  }

  @Test public void WarWithArrayTest1()
  {
      int test = 1;
      p = 5;
      boolean success = warCorTest(WarWithArray.class, test);

      assertTrue(success);
  }

  @Test public void WarWithArrayTest2()
  {
      int test = 2;
      p = 5;
      boolean success = warCorTest(WarWithArray.class, test);

      assertTrue(success);
  }

  @Test public void WarWithArrayTest3()
  {
      int test = 3;
      p = 5;
      boolean success = warCorTest(WarWithArray.class, test);

      assertTrue(success);
  }

  // bst tests

  @Test public void WarWithBstTest0()
  {
      int test = 3;
      p = 5;
      boolean success = warCorTest(WarWithBST.class, test);

      assertTrue(success);
  }

  @Test public void WarWithBstTest1()
  {
      int test = 4;
      p = 5;
      boolean success = warCorTest(WarWithBST.class, test);

      assertTrue(success);
  }

  @Test public void WarWithBstTest2()
  {
      int test = 5;
      p = 5;
      boolean success = warCorTest(WarWithBST.class, test);

      assertTrue(success);
  }

  @Test public void WarWithBstTest3()
  {
      int test = 6;
      p = 5;
      boolean success = warCorTest(WarWithBST.class, test);

      assertTrue(success);
  }

  // hash tests

  @Test public void WarWithHashTest0()
  {
      int test = 3;
      p = 5;
      boolean success = warCorTest(WarWithHash.class, test);

      assertTrue(success);
  }

  @Test public void WarWithHashTest1()
  {
      int test = 4;
      p = 5;
      boolean success = warCorTest(WarWithHash.class, test);

      assertTrue(success);
  }

  @Test public void WarWithHashTest2()
  {
      int test = 5;
      p = 5;
      boolean success = warCorTest(WarWithHash.class, test);

      assertTrue(success);
  }

  @Test public void WarWithHashTest3()
  {
      int test = 6;
      p = 5;
      boolean success = warCorTest(WarWithHash.class, test);

      assertTrue(success);
  }

  // roll hash tests

  @Test public void WarWithRollHashTest0()
  {
      int test = 0;
      p = 5;
      boolean success = warCorTest(WarWithHash.class, test);

      assertTrue(success);
  }

  @Test public void WarWithRollHashTest1()
  {
      int test = 1;
      p = 5;
      boolean success = warCorTest(WarWithRollHash.class, test);

      assertTrue(success);
  }

  @Test public void WarWithRollHashTest2()
  {
      int test = 2;
      p = 5;
      boolean success = warCorTest(WarWithRollHash.class, test);

      assertTrue(success);
  }

  @Test public void WarWithRollHashTest3()
  {
      int test = 3;
      p = 5;
      boolean success = warCorTest(WarWithRollHash.class, test);

      assertTrue(success);
  }

  @Test public void WarWithRollHashTest4()
  {
      int test = 4;
      p = 5;
      boolean success = warCorTest(WarWithRollHash.class, test);

      assertTrue(success);
  }

  @Test public void WarWithRollHashTest5()
  {
      int test = 5;
      p = 5;
      boolean success = warCorTest(WarWithRollHash.class, test);

      assertTrue(success);
  }

  @Test public void WarWithRollHashTest6()
  {
      int test = 6;
      p = 5;
      boolean success = warCorTest(WarWithRollHash.class, test);

      assertTrue(success);
  }

  @Test public void WarWithRollHashTest7()
  {
      int test = 7;
      p = 5;
      boolean success = warCorTest(WarWithRollHash.class, test);

      assertTrue(success);
  }

  //

  // war performance tests

  // array perf tests

  @Test public void WarWithArrayPerformanceTest0()
  {
      int test = 5;
      p = 0;
      boolean success = warPerfTest(WarWithArray.class, WarWithArrayRef.class, test);

      assertTrue("WarWithArray performance test 0", success);
  }

  // bst tests

  @Test public void WarWithBstPerformanceTest()
  {
      int test = 7;
      p = 0;
      boolean success = warPerfTest(WarWithBST.class, WarWithBSTRef.class, test);

      assertTrue(success);
  }

  // hash tests

  @Test public void WarWithHashPerformanceTest()
  {
      int test = 7;
      p = 0;
      boolean success = warPerfTest(WarWithHash.class, WarWithHashRef.class, test);

      assertTrue(success);
  }

  // roll hash tests

  @Test public void WarWithRollHashPerformanceTest()
  {
      int test = 7;
      p = 0;

      boolean success =  warPerfTest(WarWithRollHash.class, WarWithRollHashRef.class, test);

      assertTrue(success);
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
public boolean warCorTest(Class cls, int test) {
      boolean success = true;

      try {
          Constructor con = cls.getConstructor(String[].class, int.class);
          Method met = cls.getMethod("compute2k");

          Object war = con.newInstance(tests.get(test), testK[test]);
          List<String> result = (List<String>) met.invoke(war);
          Set a = new HashSet(result);
          Set b = new HashSet<String>(Arrays.asList(answers.get(test)));

          double ji = jaccardIndex(a,b);
          if (ji != 1) {
              if (ji > .95)
                  partial = 4;
              else if (ji > .90)
                  partial = 3;
              else if (ji > .80)
                  partial = 2;
              else if (ji > .70)
                  partial = 1;
              else partial = 0;
              success = false;
              assertTrue("User results differ. Jaccard Index: " + ji, false);
          }
      } catch (Exception e) {
          success = false;
          if (e instanceof java.lang.reflect.InvocationTargetException) {
              java.lang.reflect.InvocationTargetException ite =
                      (java.lang.reflect.InvocationTargetException) e;
              assertTrue("Exception ocurred: " + ite.getTargetException(), false);
          } else {
              assertTrue("Exception ocurred: " + e, false);
          }
      }
      return success;
  }

  @SuppressWarnings({"unchecked", "rawtypes", "unused"})
public boolean warPerfTest(Class cls1, Class cls2, int test) {
      boolean success = true;
      long iterations = 100;
      try {
          Constructor con1 = cls1.getConstructor(String[].class, int.class);
          Method met1 = cls1.getMethod("compute2k");
          Constructor con2 = cls2.getConstructor(String[].class, int.class);
          Method met2 = cls2.getMethod("compute2k");

          long theirTime = 0;
          long refTime = 0;
          List<String> result;
          Object war;
          long start;
          long finish;

          for (long i = 0; i < iterations; i++) {
              start = System.nanoTime();
              war = (Object) con1.newInstance(tests.get(test), testK[test]);
              result = (List<String>)met1.invoke(war);
              finish = System.nanoTime();
              theirTime += finish - start;

              start = System.nanoTime();
              war = con2.newInstance(tests.get(test), testK[test]);
              result = (List<String>)met2.invoke(war);
              finish = System.nanoTime();
              refTime += finish - start;
          }

          theirTime = theirTime / iterations;
          refTime = refTime / iterations;

          double ratio = (double)theirTime / (double)refTime;

          if (cls2 == WarWithArrayRef.class)
              arrayWarRatio = ratio;
          else if (cls2 == WarWithBSTRef.class)
              bstWarRatio = ratio;
          else if (cls2 == WarWithHashRef.class)
              hashWarRatio = ratio;
          else if (cls2 == WarWithRollHashRef.class)
              rollhashWarRatio = ratio;
          else {
              System.out.println("Something went wrong setting performance ratios in war testing.");
              arrayWarRatio = bstWarRatio = hashWarRatio = rollhashWarRatio = -1;
          }

          //if (theirTime > (refTime * 2))
          //    success = false;
      } catch (Exception e) {
          success = false;
          if (e instanceof java.lang.reflect.InvocationTargetException) {
              java.lang.reflect.InvocationTargetException ite =
                      (java.lang.reflect.InvocationTargetException) e;
              assertTrue("Exception ocurred: " + ite.getTargetException(), false);
          } else {
              assertTrue("Exception ocurred: " + e, false);
          }
      }
      return success;
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
public double jaccardIndex(Set a, Set b) {
      Set c = new HashSet();
      c.addAll(a);
      c.addAll(b);
      double unionSize = c.size();
      c.retainAll(a);
      c.retainAll(b);
      double intersectionSize = c.size();
      return intersectionSize / unionSize;
  }


}


