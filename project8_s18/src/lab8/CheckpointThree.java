package lab8;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import plotter.Plotter;
import plotter.Polyline;

public class CheckpointThree
{
	public static void main(String args[]) throws FileNotFoundException
	{
		Plotter p = new Plotter();
		ArrayList<Polyline> lines = readFile("hello.txt");
		for(Polyline line : lines)
			p.plot(line);
	}

	private static ArrayList<Polyline> readFile(String fileName) throws FileNotFoundException
	{
		Scanner s = new Scanner(new File(fileName));
		ArrayList<Polyline> polylines = new ArrayList<Polyline>();
		while(s.hasNextLine())
		{
			String temp = s.nextLine();
			if(!temp.isEmpty())
				readLine(temp, polylines);
		}
		s.close();
		return polylines;
	}

	private static void readLine(String s, ArrayList<Polyline> polylines)
	{
		Scanner sc = new Scanner(s);
		int thickness;
		Polyline p;

		if(sc.hasNextInt())
		{
			thickness = sc.nextInt();
			p = new Polyline(sc.next(), thickness);
		}
		else
		{
			String temp = sc.next();
			if(!temp.equals("#") && sc.hasNext())
				p = new Polyline(temp);
			else
			{
				sc.close();
				return;
			}
		}

		while(sc.hasNext())
			p.addPoint(new Point(sc.nextInt(), sc.nextInt()));

		polylines.add(p);
		sc.close();
	}
}
