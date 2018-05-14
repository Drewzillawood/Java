package firstCollection;

import java.util.Iterator;

public class FirstCollectionApplication {

	@SuppressWarnings({"unchecked", "rawtypes"})
	public static void main(String[] args) {
		
		FirstCollection test = new FirstCollection(2);
		
		test.add("cat");
		test.add("dog");
		test.add("bird");
		test.add("rat");
		test.add("frog");
		
		System.out.println(test.toString());
		
		Iterator<String> iter = test.iterator();
		
		iter.next();
		iter.remove();
		
		System.out.println(test.toString());
		
		iter.next();
		iter.next();
		iter.remove();
		
		System.out.println(test.toString());

	}

}
