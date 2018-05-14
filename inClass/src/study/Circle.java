package study;

import java.awt.Point;

public class Circle implements Shape
{

	private double radius;

	public Circle(Point p, double r)
	{

		radius = r;

	}

	public double getArea()
	{

		return Math.PI * radius * radius;

	}

	public void draw()
	{

	}

}
