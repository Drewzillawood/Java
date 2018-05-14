package studyGuide_s18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author drewu
 *
 */
public class ContactDirectory
{
	/**
	 * List of contacts
	 */
	ArrayList<Contact> contacts;

	/**
	 * Constructor
	 */
	public ContactDirectory()
	{
		contacts = new ArrayList<Contact>();
	}

	/**
	 * Add a contact
	 * 
	 * @param c
	 */
	public void addContact(Contact c)
	{
		contacts.add(c);
	}

	/**
	 * Add contacts from file
	 * 
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("resource")
	public void addfromFile(String fileName) throws FileNotFoundException
	{
		File f = new File(fileName);
		Scanner s = new Scanner(f);
		while(s.hasNextLine())
		{
			String c = s.nextLine();
			Scanner s_1 = new Scanner(c).useDelimiter(", ");
			contacts.add(new Contact(s_1.next(), s_1.next()));
		}
	}

	/**
	 * Lookup in our list of contacts
	 * 
	 * @param name
	 * @return
	 */
	public String lookup(String name)
	{
		for(Contact c : contacts)
			if(c.getName().equals(name))
				return c.getPhoneNumber();
		return null;
	}
}
