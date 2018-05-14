package hw3;

import java.util.Random;
import api.Jewel;

/**
 * 
 * JewelFactory class
 * @author drewu
 *
 */
public class JewelFactory {
	
	/**
	 * Max type for this JewelFactory
	 */
	private int maxType;
	
	/**
	 * Random number generator for this JewelFactory
	 */
	private Random r;
	
	/**
	 * Constructs a JewelFactory that will create Jewel objects with types 0 through givenMaxType - 1.
	 * 
	 * @param givenMaxType
	 * 		number of types of Jewels
	 */
	public JewelFactory(int givenMaxType)
	{
		maxType = givenMaxType;
		r = new Random();
	}
	
	/**
	 * Constructs a JewelFactory that will create jewels with types 0 through givenMaxType - 1.
	 * using the given Random instance.
	 * 
	 * @param givenMaxType
	 * 		number of types of jewels
	 * @param rand
	 * 		random number generator to use
	 */
	public JewelFactory(int givenMaxType, Random rand)
	{
		maxType = givenMaxType;
		r = rand;
	}
	
	/**
	 * Returns a random instance of Jewel with a type that is less than this factory's maximum value.
	 * 
	 * @return
	 * 		a Jewel object with randomly selected type
	 */
	public Jewel generate()
	{
		return new Jewel(r.nextInt(maxType));
	}

	/**
	 * Creates a grid of the given width and height that is initialized with random Jewels with type 
	 * less than this factory's maximum type. The Jewels are produced using this factory's random number 
	 * generator, where the random selection is restricted so that the resulting grid has no runs.
	 * 
	 * @param width
	 * 		number of columns for the created grid
	 * @param height
	 * 		number of rows for the created grid
	 * @return
	 * 		a 2D array of randomly selected Jewel objects that contains no runs
	 */
	public Jewel[][] createGrid(int width, int height)
	{
		Jewel[][] jewels = new Jewel[width][height];
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				jewels[i][j] = generate();
			}
		}
		return jewels;
	}
	
}
