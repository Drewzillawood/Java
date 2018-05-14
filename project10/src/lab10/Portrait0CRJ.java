package lab10;

import java.awt.Graphics;

/**
 * Cyclops look a lot like you and me, but they only have one eye.
 */
public class Portrait0CRJ extends Portrait
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Portrait0CRJ()
	{
		super(0.2);

		// Cyclops are all limb and no body.
		setBodyHeight(0.1);
		setArmSpan(0.75);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		int midX = getWidth() / 2;

		// Draw a big eye right in the middle of the face.
		int eyeRadius = (int)(0.05 * SIZE);
		g.fillOval(midX - eyeRadius, headRadius - eyeRadius * 2, 2 * eyeRadius, 2 * eyeRadius);

		// And give him a creepy smile.
		int smileRadius = (int)(0.5 * headRadius);
		g.drawArc(midX - smileRadius, (int)(0.8 * headRadius), smileRadius * 2, smileRadius * 2, 0, -180);
	}
}