package hw1;

/**
 * This class models a cab. Passengers pay to get rides. There is a base fare
 * charged for any ride when it starts, onto which is added a per-mile rate that
 * is charged when the cab drives. The cab has a meter showing how much is to be
 * charged to the passenger, and it also keeps track of the total cash collected
 * and the total miles driven since the cab was constructed. The base fare and
 * the per-mile rate are determined by arguments to the constructor. The amount
 * to be charged to the passenger is always shown on the meter. The current rate
 * is the amount by which the meter increases per mile driven. It is zero when
 * there is no passenger, but after a passenger has been picked up the current
 * rate is equal to the per-mile rate given in the constructor. Thus when the
 * cab has no passenger and is just driving around, the value on the meter does
 * not go up. Picking up a passenger is indicated by a call to pickUp(), at
 * which time the meter is immediately set to the base fare, and the current
 * rate is set to the actual per-mile rate. As the cab drives, the value on the
 * meter now increases, since the current rate is nonzero.
 * 
 * A ride is ended by a call to dropOff(), at which point
 * 
 * the value on the meter is added to the total cash, and the meter is reset to
 * zero, and the current rate is set to zero. Note that if pickUp() is called,
 * and then pickUp() is called again without the dropOff() method having been
 * called, the previous value on the meter is lost and not added to the total
 * cash. (It is as though a new passenger got in, and the previous passenger got
 * out without paying.)
 * 
 * @author drewu
 *
 */
public class Cab
{
	/**
	 * This cab's base fare
	 */
	private double	baseFare;

	/**
	 * This cab's per mile rate
	 */
	private double	ratePM;

	/**
	 * Total miles driven by this cab
	 */
	private double	totalMiles;

	/**
	 * Cash on the meter
	 */
	private double	meter;

	/**
	 * Money collected from fares
	 */
	private double	cash;

	/**
	 * The rate that is being registered currently
	 */
	private double	rate;

	/**
	 * If we have a passenger
	 */
	private boolean	passenger;

	/**
	 * Constructs a cab that will use the given base fare and per-mile rate.
	 * 
	 * @param givenBaseFare
	 *            base fare charged for any ride
	 * @param givePerMileRate
	 *            per-mile rate
	 */
	public Cab(double givenBaseFare, double givePerMileRate)
	{
		baseFare = givenBaseFare;
		ratePM = givePerMileRate;
		passenger = false;
		totalMiles = 0;
		meter = 0;
		cash = 0;
		rate = 0;
	}

	/**
	 * Drives the cab the given number of miles, updating the total miles and
	 * possibly updating the meter.
	 * 
	 * @param miles
	 */
	public void drive(double miles)
	{
		totalMiles += miles;
		meter += miles * rate;
	}
	
	/**
	 * Starts a new ride, setting the meter to the base fare and setting the
	 * current rate to the per mile charge.
	 */
	public void pickUp()
	{
		passenger = true;
		meter = baseFare;
		rate = ratePM;
	}

	/**
	 * Ends the current ride, updating the total cash collected and resetting
	 * the meter and current rate to zero.
	 */
	public void dropOff()
	{
		passenger = false;
		cash += meter;
		meter = 0;
		rate = 0;
	}

	/**
	 * Returns the average income earned by this cab per mile driven.
	 * 
	 * @return Average income earned
	 */
	public double getAverageIncomePerMile()
	{
		return cash / totalMiles;
	}

	/**
	 * Returns the current per-mile rate, which is always either zero or the
	 * per-mile rate given in the constructor.
	 * 
	 * @return Current per-mile rate
	 */
	public double getCurrentRate()
	{
		return rate;
	}

	/**
	 * Returns the amount of money shown on the meter for the current ride.
	 * 
	 * @return Amount of money shown on meter
	 */
	public double getMeter()
	{
		return meter;
	}

	/**
	 * Returns the total cash collected by this cab during its lifetime.
	 * 
	 * @return Total cash collected
	 */
	public double getTotalCash()
	{
		return cash;
	}

	/**
	 * Returns the total miles driven by this cab during its lifetime.
	 * 
	 * @return Total miles driven
	 */
	public double getTotalMiles()
	{
		return totalMiles;
	}

	/**
	 * Determines whether the cab currently has a passenger (i.e., the current
	 * rate is nonzero).
	 * 
	 * @return True, if there is a passenger
	 */
	public boolean hasPassenger()
	{
		return passenger;
	}
}
