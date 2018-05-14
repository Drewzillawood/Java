package studyGuide_s18;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class TestContact
{

	public static void main(String[] args) throws FileNotFoundException
	{
		Contact c = new Contact("Drew", "402-452-6255");
		System.out.println(c.getName());
		System.out.println(c.getPhoneNumber());
		System.out.println(Arrays.toString(c.getPhoneNumberArray()));
		
		ContactDirectory d = new ContactDirectory();
		d.addContact(c);
		System.out.println(d.lookup("Drew"));
		System.out.println(d.lookup("Zach"));
		
		System.out.println();
		
		ContactDirectory n = new ContactDirectory();
		n.addfromFile("U://cs227/workspace/inClass/src/studyGuide_s18/test.txt");
		System.out.println(n.lookup("Drew"));
		System.out.println(n.lookup("Jake"));
		System.out.println(n.lookup("John"));
		System.out.println(n.lookup("Jared"));

	}

}
