package examples;

import java.util.Arrays;

import api.Cell;
import api.Position;
import api.Shape;
import hw4.*;

public class GeneralTests
{
	public static void main(String[] args)
	{
		Shape o = new SZShape(new Position(0,0), true);
		Cell[] cells = o.getCells();
		
		System.out.println(Arrays.toString(o.getCells()));
		
		for(Cell c : cells)
			System.out.println(c.getBlock());
		System.out.println();
		
		o.shiftDown();
		System.out.println("After shift down");
		System.out.println(Arrays.toString(o.getCells()));
		
		for(int i = 0; i < 4; i++)
		{
			o.cycle();
			System.out.println(Arrays.toString(o.getCells()));
		}
		System.out.println();
		
		System.out.println("Rotation about (0,0)");
		for(int i = 0; i < 4; i++)
		{
			o.transform();
			System.out.println(Arrays.toString(o.getCells()));
		}
		System.out.println();
		
		Shape newO = o.clone();
		System.out.println(newO.getClass());
		newO.shiftDown();
		System.out.println(Arrays.toString(o.getCells()));
	}
}
