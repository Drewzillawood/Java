package edu.iastate.cs228.hw3;

import java.io.FileNotFoundException;

public class MainTestEvenThoughNotSupposedToMainTest {

	public static void main(String[] args) throws FileNotFoundException {
		
//		DoublySortedList DSL = new DoublySortedList();
//		
//		DSL.add("grape", 50);
//		DSL.add("pineapple", 20);
//		DSL.add("apple", 15);
//		
//		System.out.println(DSL.toString());
//		System.out.println(DSL.printInventoryB());
		
		DoublySortedList DSL = new DoublySortedList("ifile2.txt");
		
		System.out.println(DSL.toString());
		
//		DSL2.bulkSell("sell.txt");
//		
//		System.out.println(DSL2.toString());
//		
//		System.out.println(DSL2.inquire("appl"));
//		
//		DSL2.remove("apple");
//		DSL2.remove(6);
//		DSL2.remove(6);
//		DSL2.remove("people");
//		
//		System.out.println(DSL2.toString());
		
//		Pair<DoublySortedList> pair = DSL2.split();
//		
//		System.out.println(pair.getFirst().toString());
//		System.out.println(pair.getSecond().toString());
//		
//		System.out.println(DSL.toString());
//		
//		System.out.println(DSL.printInventoryB());
//		
		DSL.restock("restock.txt");
//		
		System.out.println(DSL.toString());
		System.out.println(DSL.printInventoryB());
//		
//		DSL.bulkSell("sell.txt");
//		
//		System.out.println(DSL.toString());
//		System.out.println(DSL.printInventoryB());
//		
//		DSL.clearStorage();
//		
//		System.out.println(DSL);
//		
//		DSL.sell("grape", 65);
//		
//		Pair<DoublySortedList> pair = DSL.split();
//		
//		System.out.println(pair.getFirst().printInventoryB());
//		System.out.println(pair.getSecond().printInventoryB());
//		
//		System.out.println(pair.getFirst().printInventoryN());
//		System.out.println(pair.getSecond().printInventoryN());
//		
//		pair.getFirst().compactStorage();
//		pair.getSecond().compactStorage();
//		
//		System.out.println(pair.getFirst().printInventoryB());
//		System.out.println(pair.getSecond().printInventoryB());
//		
//		System.out.println(pair.getFirst().printInventoryN());
//		System.out.println(pair.getSecond().printInventoryN());
		
	}

}
