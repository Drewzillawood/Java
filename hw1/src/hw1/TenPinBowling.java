package hw1;

public class TenPinBowling {
	
	/**
	 *  Value to hold onto the initial roll of a round
	 */
	private int firstRoll;
	
	/**
	 * Value to hold onto the second roll of a round
	 */
	private int secondRoll;
	
	/**
	 * Boolean to detect if the last round was a spare or not
	 */
	private boolean spare;
	
	/**
	 * Boolean to detect if the last round was a strike or not
	 */
	private boolean strike;
	
	/**
	 * History is a variable used to store the incrementing values around a strike
	 */
	private int history;
	
	/**
	 * Iteration constant for 'for loops'
	 */
	private int i;
	
	/**
	 * Value to hold the value within the second to last frame
	 */
	private int beforePreviousStrike;
	
	/**
	 * Value to hold the value within the last frame
	 */
	private int previousStrike;

	/**
	 * Game is a boolean to be set by default as "not over," or false. Once the
	 * game is over, game will be set to "over," or true.
	 */
	private boolean game;

	/**
	 * Score is an integer value which is updated each time a frame progresses
	 * MAX score is 300
	 */
	private int score;

	/**
	 * frameNum is an integer value which will begin at zero, and increment by
	 * one after each frame advancement.
	 */
	private int frameNum;

	/**
	 * rollNum is an integer value which will begin at one, and increment by one
	 * within the frame at each roll, then reset to one for the next frame.
	 * rollNum will report a -1 if the game is over.
	 */
	private int rollNum;

	/**
	 * pinUp is an integer value which will begin at ten, and reset to ten at
	 * each frame
	 */
	private int pinUp;

	/**
	 * Setting a global variable to determine when our frames have reached their
	 * maximum and will set the game to 'over'
	 */
	private int maxFrames;

	/**
	 * PINS will be our default maximum value for pins up in a frame.
	 */
	public static final int PINS = 10;

	/**
	 * The "TenPinBowling" method is our constructor for the TenPinBowling
	 * object default values set pins to ten, roll number to 1, frame number to
	 * 1, and game to false. The parameter howManyFrames determines how many
	 * frames would like to be played
	 * 
	 * @param howManyFrames
	 */
	public TenPinBowling(int howManyFrames) {

		maxFrames = howManyFrames;
		rollNum = 1;
		frameNum = 1;
		game = false;
		beforePreviousStrike = 0;
		previousStrike = 0;
		strike = false;
		spare = false;
		pinUp = PINS;
		i = 0;

	}

	/**
	 * The "getFrameNumber" method is a getter which will return the current
	 * frame the game is at.
	 * 
	 * @return frameNum
	 */
	public int getFrameNumber() {
		
		if(game == true){
			
			return -1;
			
		}
		
		return frameNum;

	}

	/**
	 * The "getRollNumber" method is a getter which will return the current roll
	 * within a given frame.
	 * 
	 * @return rollNum
	 */
	public int getRollNumber() {
		
		if(game == true){
			
			return -1;
			
		}
		
		return rollNum;

	}

	/**
	 * The "getPinUp" method is a getter which will return the current number of
	 * pins up in a given frame.
	 * 
	 * @return pinUp
	 */
	public int getPinsUp() {
		
		if(game == true){
			
			return -1;
			
		}
		
		return pinUp;

	}

	/**
	 * The "isOver" getter method is called to see whether or not the game is
	 * currently over.
	 * 
	 * @return game
	 */
	public boolean isOver() {

		return game;

	}

	/**
	 * The "getScore" method is a getter which will retrieve the current score
	 * in the game.
	 * 
	 * @return score
	 */
	public int getScore() {

		return score;

	}

	/**
	 * The "roll" method is a mutator which will roll down the number of pins
	 * specified. If the number given is greater than the number of pins still
	 * standing, or the game is over, the method shall do nothing Once pinUp is
	 * equivalent to zero, the number of frames will iterate and the number of
	 * frames will reset to 1.
	 * 
	 * @param pins
	 */
	public void roll(int pins) {
		
		// We check to see if the game is at the maxFrames, thus ending the game
		if (frameNum > maxFrames) {

			game = true;

		}
		
		// Check to see if the game is already over, or if the number of pins entered
		// is not reasonable, if so, do nothing
		if (game == true || (pinUp - pins) < 0) {

			return;

		}
		
		// If we have had a strike occur, add the current, and next rolls
		if (strike){
			
			i++;	
			history += pins;
			
			if(i == 2){
	
				score += history;
				strike = false;
				history = 0;
				i = 0; 
				
			}
			
			
			
		} else if(strike && beforePreviousStrike != 0 && previousStrike != 0){
			
			score += beforePreviousStrike + previousStrike;
			beforePreviousStrike = 0;
			previousStrike = 0;
			strike = false;
			
		} else if(strike && beforePreviousStrike != 0){
			
			score += beforePreviousStrike;
			beforePreviousStrike = 0;
			strike = false;
		}
		
		// If we have had a spare occur, add the current roll
		if (spare) {

			score += pins;
			spare = false;

		}
		
		// If we strike down ten pins, we are awarded a strike
		if (pins == PINS && rollNum == 1) {
			
			if(beforePreviousStrike == 0){
				
				beforePreviousStrike = PINS;
				
			} else if(beforePreviousStrike != 0 && previousStrike == 0){
				
				previousStrike = PINS;
				
			}
			
			strike = true;
			score += pins;
			System.out.println(score);
			firstRoll = pins;
			
			pinReset();
		
		// If we strike down the rest of the pins, but the number was
		// not ten, thus we achieve a spare
		} else if ((pinUp - pins) == 0 && rollNum == 2) {
			
			score += pins;
			secondRoll = pins;
			
			// Once the first and second rolls in a frame add up to ten
			// we can justify a spare
			if ((firstRoll + secondRoll) == PINS) {

				spare = true;

			}

			pinReset();
			
		// If we don't knock down all the pins at the end of the 
	    // second roll, add the scores to our total score
		} else if ((pinUp - pins) != 0 && rollNum == 2) {

			secondRoll = pins;
			score += pins;
			
			pinReset();
			
		// First roll, but is not a strike, add score to total score,
		// and subtract the pins from pinUp
		} else {

			firstRoll = pins;
			score += pins;
			System.out.println("PINS = " + pins);
			System.out.println(score);
			pinUp -= pins;
			rollNum++;

		}



	}

	/**
	 * Reset is a mutator method which resets this game to the starting
	 * conditions described in the constructor
	 */
	public void reset() {

		rollNum = 1;
		frameNum = 1;
		game = false;
		pinUp = PINS;

	}

	/**
	 * This mutator method is simply to reset the pins at any given moment for
	 * when all pins have been knocked down.
	 */
	private void pinReset() {

		frameNum++;
		rollNum = 1;
		pinUp = PINS;

	}

}
