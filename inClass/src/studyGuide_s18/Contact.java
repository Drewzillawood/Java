package studyGuide_s18;

/**
 * 
 * @author drewu
 *
 */
public class Contact
{
	/**
	 * Name
	 */
	private String	name;

	/**
	 * Phone Number
	 */
	private String	phoneNumber;

	/**
	 * Contact constructor
	 * 
	 * @param givenName
	 * @param givenPhoneNumber
	 */
	public Contact(String givenName, String givenPhoneNumber)
	{
		name = givenName;
		phoneNumber = givenPhoneNumber;
	}

	/**
	 * Name getter
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Phone number getter
	 * 
	 * @return the number
	 */
	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	/**
	 * Number as array
	 * 
	 * @return phone number as an integer array
	 */
	public int[] getPhoneNumberArray()
	{
		int[] nums = new int[10];
		for(int i = 0, j = 0; i < phoneNumber.length(); i++)
		{
			if(phoneNumber.charAt(i) != '-')
			{
				nums[j] = Character.getNumericValue(phoneNumber.charAt(i));
				j++;
			}
		}
		return nums;
	}
}
