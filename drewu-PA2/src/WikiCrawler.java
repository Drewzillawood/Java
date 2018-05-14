// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add additional methods and fields)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * My WikiCrawler
 * 
 * @author drewu
 */
public class WikiCrawler
{
	/**
	 * Holds list of topics
	 */
//	private ArrayList<String>	listOfTopics;
//
//	/**
//	 * Maximum vertices for this implementation
//	 */
//	private int					maxVertices;
//
//	/**
//	 * Seed for our Crawl
//	 */
//	private String				seed;
//
//	/**
//	 * Number of requests our crawl has completed For implementing sleep every 3
//	 * seconds in crawls
//	 */
//	private int					requests;

	/**
	 * Holds base wiki link
	 */
	static final String			BASE_URL	= "https://en.wikipedia.org";

	/**
	 * WikiCrawler constructor
	 * 
	 * @param seedUrl
	 * @param max
	 * @param topics
	 * @param fileName
	 */
	public WikiCrawler(String seedUrl, int max, ArrayList<String> topics, String fileName) throws FileNotFoundException
	{
//		File f = new File(fileName);
//		listOfTopics = topics;
//		maxVertices = max;
//		seed = seedUrl;
//		requests = 0;
	}

	/**
	 * Extracts links from the HTML document
	 * 
	 * @param doc
	 * @return List of all HTML links of interest (relating to topics)
	 */
	// NOTE: extractLinks takes the source HTML code, NOT a URL
	public ArrayList<String> extractLinks(String doc)
	{
		ArrayList<String> links = new ArrayList<String>();
		return links;
	}

	/**
	 * Our crawl method to navigate the wiki
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void crawl() throws IOException, InterruptedException
	{
		URL url = new URL(BASE_URL + "/wiki/Physics");
		InputStream is = url.openStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
//		List<String> s = br.lines().collect(Collectors.toList());
//		ArrayList<String> refs = new ArrayList<String>();
		Thread.sleep(3);
		br.close();
		is.close();
	}

}
