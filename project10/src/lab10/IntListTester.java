package lab10;

public class IntListTester {

	public static void main(String[] args) {
		
		IntListSorted thisList = new IntListSorted();
		
		thisList.add(2);
		thisList.add(1);
		thisList.add(17);
		
		System.out.println(thisList);
		System.out.println("Size: " + thisList.size());
		System.out.println("Max:  " + thisList.getMaximum());
		System.out.println("Min:  " + thisList.getMinimum());
		System.out.println("Med:  " + thisList.getMedian());

	}

}
