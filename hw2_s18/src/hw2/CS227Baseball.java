package hw2;

/**
 * Simplified model of American baseball.
 * 
 * @author drewu
 */
public class CS227Baseball
{
	/**
	 * Constant indicating that a pitch results in a ball.
	 */
	public static final int	BALL		= 0;

	/**
	 * Constant indicating that a pitch results in a strike.
	 */
	public static final int	STRIKE		= 1;

	/**
	 * Constant indicating that a pitch results in an out from a fly ball.
	 */
	public static final int	POP_FLY		= 2;

	/**
	 * Number of strikes causing a player to be out.
	 */
	public static final int	MAX_STRIKES	= 3;

	/**
	 * Number of balls causing a player to walk.
	 */
	public static final int	MAX_BALLS	= 4;

	/**
	 * Number of outs before the teams switch.
	 */
	public static final int	MAX_OUTS	= 3;

	/***********************************************************************/
	/**
	 * Inning count
	 */
	private double			inning;

	/**
	 * Numbers of balls
	 */
	private int				balls;

	/**
	 * Number of strikes
	 */
	private int				strikes;

	/**
	 * Number of outs
	 */
	private int				outs;

	/**
	 * If team0 is up to bat
	 */
	private boolean			team0;

	/**
	 * Number of innings for this game
	 */
	private int				maxInnings;

	/**
	 * Player on first
	 */
	private boolean			first;

	/**
	 * Player on second
	 */
	private boolean			second;

	/**
	 * Player on third
	 */
	private boolean			third;

	/**
	 * Score of team0
	 */
	private int				team0Score;

	/**
	 * Score of team1
	 */
	private int				team1Score;

	/***********************************************************************/

	/**
	 * Constructs a game that has the given number of innings and starts at the
	 * top of inning 1.s
	 * 
	 * @param givenNumInnings
	 */
	public CS227Baseball(int givenNumInnings)
	{
		maxInnings = givenNumInnings;
		inning = 1.0;
		team0 = true;
		first = false;
		second = false;
		third = false;
	}

	/**
	 * Advances all players on base by one base, updating the score if there is
	 * currently a player on third.
	 * 
	 * @param newPlayerOnFirst
	 */
	public void advanceRunners(boolean newPlayerOnFirst)
	{	
		if(third)
		{
			third = !third;
			score(1);
		}

		if(second)
		{
			third = true;
			second = !second;
		}

		if(first)
		{
			second = true;
		}

		first = newPlayerOnFirst;
	}

	/**
	 * Returns the number of balls for the current batter.
	 * 
	 * @return Number of balls
	 */
	public int getBalls()
	{
		return balls;
	}

	/**
	 * Returns the current inning.
	 * 
	 * @return current inning
	 */
	public int getInning()
	{
		return (int)Math.floor(inning);
	}

	/**
	 * Returns the number of outs for the current batter.
	 * 
	 * @return number of outs
	 */
	public int getOuts()
	{
		return outs;
	}

	/**
	 * Returns the score of the indicated team, where an argument of true
	 * indicates team 0 (batting in the top of the inning) and an argument of
	 * false indicates team 1 (batting in the bottom of the inning).
	 * 
	 * @param team0
	 *            which team
	 * 
	 * @return the score for specified team
	 */
	public int getScore(boolean team0)
	{
		return team0 ? team0Score : team1Score;
	}

	/**
	 * Returns the number of strikes for the current batter.
	 * 
	 * @return number of strikes
	 */
	public int getStrikes()
	{
		return strikes;
	}

	/**
	 * Returns true if the game is over, false otherwise.
	 * 
	 * @return if the game is over
	 */
	public boolean isOver()
	{
		return Math.floor(inning) > maxInnings;
	}

	/**
	 * Returns true if it's the first half of the inning (team 0 is at bat).
	 * 
	 * @return if it's first half of the inning
	 */
	public boolean isTop()
	{
		return Math.floor(inning + 0.5) - Math.floor(inning) < 0.00000001;
	}

	/**
	 * Pitch not resulting in a hit.
	 * 
	 * @param outcome
	 */
	public void pitch(int outcome)
	{	
		if(isOver())
			return;
		
		switch(outcome)
		{
			case STRIKE:
				strikes++;
				if(strikes == MAX_STRIKES)
				{
					yerrrrrrrrrrrOUT();
				}
				break;
			case BALL:
				balls++;
				if(balls == MAX_BALLS)
				{
					advanceRunners(true);
					sssssssAAAAAFEUUUH();
				}
				break;
			case POP_FLY:
				yerrrrrrrrrrrOUT();
				break;
		}
		cycleOut();
	}

	/**
	 * Pitch resulting in a hit where no player is out.
	 * 
	 * @param numBases
	 *            number of bases to advance
	 */
	public void pitchWithHit(int numBases)
	{	
		if(isOver())
			return;
		
		advanceRunners(true);
		for(int i = 1; i < numBases; i++)
			advanceRunners(false);
		sssssssAAAAAFEUUUH();
	}

	/**
	 * Pitch resulting in a hit and a possible out.
	 * 
	 * @param numBases
	 *            number of bases to advance
	 * @param whichBaseFielded
	 *            which bases are held
	 */
	public void pitchWithHitAndOut(int numBases, int whichBaseFielded)
	{		
		if(isOver())
			return;
		
		boolean home = numBases > 3 || first && numBases > 2 || second && numBases > 1 || third && numBases > 0;

		// Players will round bases first
		pitchWithHit(numBases);

		// Keep track of where they were
		boolean[] before = {first, second, third};
		switch(whichBaseFielded)
		{
			case 1:
				first = false;
				break;
			case 2:
				second = false;
				break;
			case 3:
				third = false;
				break;
			case 4:
				if(home)
					score(-1);
				yerrrrrrrrrrrOUT();
				break;
		}
		// Compare from before, did someone get out?
		if(first ^ before[0] || second ^ before[1] || third ^ before[2])
			yerrrrrrrrrrrOUT();
		cycleOut();
	}

	/**
	 * Returns whether there is a player on first base.
	 * 
	 * @return if player on first base
	 */
	public boolean playerOnFirst()
	{
		return first;
	}

	/**
	 * Returns whether there is a player on second base.
	 * 
	 * @return if player on second base
	 */
	public boolean playerOnSecond()
	{
		return second;
	}

	/**
	 * Returns whether there is a player on third base.
	 * 
	 * @return if player on third base
	 */
	public boolean playerOnThird()
	{
		return third;
	}

	/**
	 * Returns a three-character string representing the players on base, in the
	 * order first, second, and third, where 'X' indicates a player is present
	 * and 'o' indicates no player. For example, the string "XXo" means that
	 * there are players on first and second but not on third.
	 * 
	 * @return three-character string showing players on base
	 */
	public String getBases()
	{
		return (playerOnFirst() ? "X" : "o") + (playerOnSecond() ? "X" : "o") + (playerOnThird() ? "X" : "o");
	}

	/**
	 * Returns a one-line string representation of the current game state. The
	 * format is:
	 * 
	 * <pre>
	 *    ooo Inning:1 (T) Score:0-0 Balls:0 Strikes:0 Outs:0
	 * </pre>
	 * 
	 * The first three characters represent the players on base as returned by
	 * the <code>getBases()</code> method. The 'T' after the inning number
	 * indicates it's the top of the inning, and a 'B' would indicate the
	 * bottom.
	 * 
	 * @return one-line string representation of the game state
	 */
	public String toString()
	{
		String bases = getBases();
		String topOrBottom = (isTop() ? "T" : "B");
		String fmt = "%s Inning:%d (%s) Score:%d-%d Balls:%d Strikes:%d Outs:%d";
		return String.format(fmt, bases, getInning(), topOrBottom, getScore(true), getScore(false), getBalls(),
				getStrikes(), getOuts());
	}

	/**
	 * Returns a multi-line string representation of the current game state. The
	 * format is:
	 * 
	 * <pre>
	 *     o - o    Inning:1 (T)
	 *     |   |    Score:0-0
	 *     o - H    Balls:0 Strikes:0 Outs:0
	 * </pre>
	 * 
	 * The 'T' after the inning number indicates it's the top of the inning, and
	 * a 'B' would indicate the bottom.
	 * 
	 * @return multi-line string representation of current game state
	 */
	public String toDisplayString()
	{
		String firstChar = (playerOnFirst() ? "X" : "o");
		String secondChar = (playerOnSecond() ? "X" : "o");
		String thirdChar = (playerOnThird() ? "X" : "o");
		String topOrBottom = (isTop() ? "T" : "B");
		String firstLine = String.format("%s - %s    Inning:%d (%s)\n", secondChar, firstChar, getInning(),
				topOrBottom);
		String secondLine = String.format("|   |    Score:%d-%d\n", getScore(true), getScore(false));
		String thirdLine = String.format("%s - H    Balls:%d Strikes:%d Outs:%d\n", thirdChar, getBalls(), getStrikes(),
				getOuts());
		return firstLine + secondLine + thirdLine;
	}

	/**
	 * ssssssAAAAFEUUUH
	 */
	private void sssssssAAAAAFEUUUH()
	{
		strikes = 0;
		balls = 0;
	}

	/**
	 * Yerrrrrrrrr OUT!
	 */
	private void yerrrrrrrrrrrOUT()
	{
		sssssssAAAAAFEUUUH();
		outs++;
	}

	/**
	 * Helper method to make things more readable
	 */
	private void cycleOut()
	{
		if(outs == MAX_OUTS)
		{
			inning += 0.5;
			first = false;
			second = false;
			third = false;
			team0 = !team0;
			outs = 0;
		}
	}

	/**
	 * Simplified scoring mechanic
	 * 
	 * @param numRuns
	 *            number of points to be added
	 */
	private void score(int numRuns)
	{
		if(team0)
			team0Score += numRuns;
		else
			team1Score += numRuns;
	}

}