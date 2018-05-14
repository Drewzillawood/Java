package hashMap;

import java.util.HashMap;


class HashMapDemo { 
	public static void main(String args[]) { 
	
		HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>(); 
	
		hashMap.put('x', 4); 
		hashMap.put('y', 2); 
		hashMap.put('z', 5); 
	
		char c = 'x'; 
		if (hashMap.containsKey(c))
		{
			int x = (int) hashMap.get(c); 
			System.out.println(x); 
		}
	}
}