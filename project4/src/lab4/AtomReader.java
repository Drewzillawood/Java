package lab4;

import java.util.Scanner;
import lab2.Atom;

public class AtomReader
{
  public static void main(String[] args)
  {
     String text = "Uranium U 92 92 146";
     Scanner parser = new Scanner(text);
     String name = parser.next();
     parser.next();  // skip over the "U", we don't need to save it
     int protons = parser.nextInt();
     int electrons = parser.nextInt();
     int neutrons = parser.nextInt();
     System.out.println(name);
     Atom atom = new Atom(protons, neutrons, electrons);
     System.out.println("Atomic mass: " + atom.getAtomicMass());
     parser.close();
     
     Scanner parser2 = new Scanner("Uranium,U,92,92,146");
     parser2.useDelimiter(",");

     // Skip over first two fields.  Notice how we can just ignore the value
     // returned by the next() method?
     parser2.next();
     parser2.next();
     int protonCount = parser2.nextInt();
     int electronCount = parser2.nextInt();
     int neutronCount = parser2.nextInt();
     
     Atom u238 = new Atom(protonCount, neutronCount, electronCount); 
     System.out.println("Atomic mass: " + u238.getAtomicMass());
     parser2.close();
  }
}