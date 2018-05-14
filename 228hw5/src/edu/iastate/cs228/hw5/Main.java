package edu.iastate.cs228.hw5;

public class Main {

	public static void main(String[] args) {
		
		ABTreeSet<Integer> a = new ABTreeSet<Integer>();
		
		a.add(2);
		a.add(4);
		a.add(5);
		a.add(7);
		a.add(10);
		a.add(12);
		a.add(14);
		a.add(15);
		a.add(16);
		a.add(20);
		
		System.out.println(a.inorderList(a.root()));
		
		a.rebalance(a.root());
		a.remove(2);
		
		System.out.println(a.inorderList(a.root()));
		
		a.add(12);
		a.add(22);
		
		System.out.println(a.root().count());
		
		
	}

}
