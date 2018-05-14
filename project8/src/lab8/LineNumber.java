package lab8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LineNumber {

	public static void main(String[] args) throws FileNotFoundException {
		
//		Scanner scanner = new Scanner(System.in);
//		File outFile = new File("checkpoint1.txt");
//		PrintWriter out = new PrintWriter(outFile);
//	    int lineCount = 1;
//
//	    while (scanner.hasNextLine())
//	    {
//	      String line = scanner.nextLine();
//	      out.print(lineCount + " ");
//	      out.println(line);
//	      lineCount += 1;
//	    }
//	    
//	    System.out.println("Done");
//	    scanner.close();
//	    out.close();
		
		File file = new File("U:/cs227/workspace/project7/src/lab7/Deck.java");
	    Scanner scanner = new Scanner(file);
	    int lineCount = 1;

	    while (scanner.hasNextLine())
	    {
	      String line = scanner.nextLine();
	      System.out.print(lineCount + " ");
	      System.out.println(line);
	      lineCount += 1;
	    }
	    scanner.close();

	}

}
