package lab8;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import plotter.Plotter;
import plotter.Polyline;

public class checkpoint2 {
	
	public static int width = 1;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<Polyline> pList = new ArrayList<Polyline>();
		
		fileProcessing("hello.txt",list);
			
		lineProcessing(list, pList);
		
		
		Plotter plotter = new Plotter();
		for(int i = 0; i < pList.size(); i++){
			
			plotter.plot(pList.get(i));
			
		}
		

	}

	private static void fileProcessing(String fileName, ArrayList<String> newList) throws FileNotFoundException {

		File file = new File(fileName);
		Scanner scanner = new Scanner(file);

		while (scanner.hasNextLine()) {

			String line = scanner.nextLine();

			if (line.contains("#") || line.isEmpty()) {

				// Don't want to do anything with this line

			} else {
				
				newList.add(line);
				
			}

		}

		scanner.close();
		

	}

	private static void lineProcessing(ArrayList<String> listing, ArrayList<Polyline> pListing) {

		for(int i = 0; i < listing.size(); i++){
			
			String current = listing.get(i);
			Scanner scanner = new Scanner(current);
			
			if(scanner.hasNextInt()){
				
				width = scanner.nextInt();
				
			}
			
			Polyline pl = new Polyline(scanner.next());
			
			while(scanner.hasNextInt()){
			
				pl.addPoint(new Point(scanner.nextInt(), scanner.nextInt()));
				
			}
			
			pListing.add(pl);
			scanner.close();
			
		}
		

	}

}