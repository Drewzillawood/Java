package mini1;

/**
 * Vending Machine class
 * 
 * @author Drew
 *
 */
public class VendingMachine
{

	private int	nickel;
	private int	dime;
	private int	quarter;
	private int	itemsPurhcased;
	private int	balance;
	private int	totalValue;

	/**
	 * Our constructor for our vending machine.
	 * 
	 * @param givenQuarters
	 * @param givenDimes
	 * @param givenNickels
	 */
	public VendingMachine(int givenQuarters, int givenDimes, int givenNickels)
	{

		nickel = givenNickels;
		dime = givenDimes;
		quarter = givenQuarters;
		totalValue = (quarter * 25) + (dime * 10) + (nickel * 5);

	}

	/**
	 * Return the number of quarters in this machine
	 * 
	 * @return The number of quarters in this machine
	 */
	public int getQuarters()
	{

		return quarter;

	}

	/**
	 * Return the number of dimes in this machine
	 * 
	 * @return The number of dimes in this machine
	 */
	public int getDimes()
	{

		return dime;

	}

	/**
	 * Return the number of nickels in this machine
	 * 
	 * @return The number of nickels in this machine
	 */
	public int getNickels()
	{

		return nickel;

	}

	/**
	 * Returns the balance of this machine (amount of money currently available
	 * to purchase something)
	 * 
	 * @return Balance of this machine
	 */
	public int getBalance()
	{

		return balance;

	}

	/**
	 * Return the total value of all coins in this machine
	 * 
	 * @return Total value of all coins in this machine
	 */
	public int getTotalValue()
	{

		return totalValue;

	}

	/**
	 * Returns the total number of items that have ever been purchased from this
	 * machine
	 * 
	 * @return Total items purchased since this machine was constructed
	 */
	public int getCount()
	{

		return itemsPurhcased;

	}

	/**
	 * Returns a short string representation of the number of coins in this
	 * machine, in the order "q,d,n" where q is the number of quarters, d is the
	 * number of dimes, and n is the number of nickels;
	 * 
	 */
	public java.lang.String toString()
	{

		String first = Integer.toString(quarter);
		String second = Integer.toString(dime);
		String third = Integer.toString(nickel);

		return first + ", " + second + ", " + third;

	}

	/**
	 * Adds the given number of quarters to this machine and increases the
	 * balance by the combined value of the coins.
	 * 
	 * @param howmany
	 *            - number of quarters to insert
	 */
	public void insertQuarters(int howmany)
	{

		totalValue = totalValue + (25 * howmany);
		balance = balance + (25 * howmany);
		quarter = quarter + howmany;

	}

	/**
	 * Adds the given number of dimes to this machine and increases the balance
	 * by the combined value of the coins.
	 * 
	 * @param howmany
	 *            - number of dimes to insert
	 */
	public void insertDimes(int howmany)
	{

		totalValue = totalValue + (10 * howmany);
		balance = balance + (10 * howmany);
		dime = dime + howmany;

	}

	/**
	 * Adds the given number of nickels to this machine and increase the balance
	 * by the combined value of the coins.
	 * 
	 * @param howMany
	 *            - number of nickels to insert
	 */
	public void insertNickels(int howmany)
	{

		totalValue = totalValue + (5 * howmany);
		balance = balance + (5 * howmany);
		nickel = nickel + howmany;

	}

	/**
	 * Reduces the balance to zero, and reduces the nickels, dimes, and quarters
	 * by the amount of the current balance, using the same algorithm that is
	 * used for returning change
	 */
	public void cancel()
	{

		totalValue = totalValue - balance;
		quarter = quarter - (balance / 25);
		balance = balance % 25;
		dime = dime - (balance / 10);
		balance = balance % 10;
		nickel = nickel - (balance / 5);
		balance = 0;

	}

	/**
	 * Simulates the purchasing of an item using the current balance. Reduces
	 * balance to zero, and reduces the nickels, dimes, and quarters by the
	 * amount needed for change, if possible. The count of purchased items
	 * increases by 1
	 * 
	 * @param cost
	 */
	public void purchase(int cost)
	{

		if(cost > balance)
		{

			System.out.println("Please insert additional funds.");
			System.exit(0);

		}

		totalValue = totalValue + cost;
		balance = balance - cost;

		for(; balance >= 25 && quarter > 0;)
		{

			balance = balance - 25;
			quarter--;

		}

		for(; balance >= 10 && dime > 0;)
		{

			balance = balance - 10;
			dime--;

		}

		for(; balance >= 5 && nickel > 0;)
		{

			balance = balance - 5;
			nickel--;

		}

		balance = 0;
		itemsPurhcased++;

	}

}
