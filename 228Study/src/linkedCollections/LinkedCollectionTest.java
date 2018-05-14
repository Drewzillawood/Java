package linkedCollections;

import java.util.Iterator;

public class LinkedCollectionTest {
	public static void main(String[] args) {
		// Create a list of strings ["a", "b", "c", "d", "e"]
		DoublyLinkedCollection<String> list1 = new DoublyLinkedCollection<String>();
		list1.add("e");
		list1.add("d");
		list1.add("c");
		list1.add("b");
		list1.add("a");

		String s;

		// print out its elements
		System.out.println("list1:");
		Iterator<String> iter = list1.iterator();
		while (iter.hasNext()) {
			s = iter.next();
			System.out.println(s);
		}
		System.out.println();

		// Create a new list list2 that copies every other element from list1.
		// With add() limited to the beginning of the list, we first construct a
		// temporary list that contains the selected elements from list1
		// in the reverse order, and then builds list2 from the temporary list.

		// a) create a temporary list tmp that extracts every other element from
		// list1, listing the extracted elements in the reverse order
		DoublyLinkedCollection<String> tmp = new DoublyLinkedCollection<String>();
		iter = list1.iterator();
		int i = 0;
		while (iter.hasNext()) {
			s = iter.next();
			if (i % 2 == 0)
				tmp.add(s);
			i++;
		}

		iter = tmp.iterator();
		System.out.println("temporary list:");
		while (iter.hasNext())
			System.out.println(iter.next());
		System.out.println();

		// b) copy the elements of tmp onto list2 with an order reversal.
		DoublyLinkedCollection<String> list2 = new DoublyLinkedCollection<String>();
		iter = tmp.iterator();
		while (iter.hasNext()) {
			list2.add(iter.next());
		}

		System.out.println("list2:");
		iter = list2.iterator();
		while (iter.hasNext())
			System.out.println(iter.next());
	}
}