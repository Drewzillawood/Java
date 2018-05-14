package lab5;

public class Basketball
{
	private double diameter;
	private boolean dribbleable;
	
	public Basketball(double givenDiameter)
	{
		dribbleable = false;
		diameter = givenDiameter;
	}

	public boolean isDribbleable()
	{
		return dribbleable;
	}

	public double getDiameter()
	{
		return diameter;
	}

	public double getCircumference()
	{
		return Math.PI * diameter;
	}

	public void inflate()
	{
		dribbleable = true;
	}
}
