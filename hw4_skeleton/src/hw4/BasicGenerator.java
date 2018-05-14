
package hw4;

import java.util.Random;

import api.Generator;
import api.Position;
import api.Shape;

/**
 * Generator for Shape objects in MagicTetris. All six shapes are equally
 * likely, and the generated shape is magic with 20% probability.
 */
public class BasicGenerator implements Generator
{
	/**
	 * Get next random shape
	 * 
	 * @return new Shape
	 */
	@Override
	public Shape getNext(int width)
	{
		Random r = new Random();
		Shapes[] s = Shapes.values();
		Shapes selection = s[r.nextInt(s.length)];
		
		boolean isMagic = r.nextInt(5) < 1 ? true : false;
		
		switch(selection)
		{
			case LShape :
				return new LShape(new Position(-1, width / 2 + 1), isMagic);
			case JShape :
				return new JShape(new Position(-1, width /2), isMagic);
			case IShape :
				return new IShape(new Position(-2, width / 2), isMagic);
			case OShape :
				return new OShape(new Position(-1, width / 2), isMagic);
			case TShape :
				return new TShape(new Position(0, width / 2), isMagic);
			default :
				return new SZShape(new Position(-1, width / 2), isMagic);
		}
	}
}
