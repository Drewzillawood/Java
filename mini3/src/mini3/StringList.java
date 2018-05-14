package mini3;

import java.util.ArrayList;

import api.Combiner;
import api.IntCombiner;
import api.Selector;
import api.Transformation;

public class StringList {

	private ArrayList<String> stringList;
	
	public StringList() {
		
		stringList = new ArrayList<String>();
		
	}
	
	public StringList(ArrayList<String> String) {
		
		stringList = new ArrayList<String>();
		stringList = String;
		
	}
	
	public StringList(String[] string) {
		
		stringList = new ArrayList<String>();
		
		for(int i = 0; i < string.length; i++) {
			
			stringList.add(string[i]);
			
		}
		
	}
	
	public void append(String s) {
		
		stringList.add(s);
		
	}
	
	public String get(int index) {
		
		return stringList.get(index);
		
	}
	
	public int size() {
		
		return stringList.size();
		
	}
	
	public StringList map(Transformation transform) {
		
		StringList newStringList = new StringList();
	
		for(int i = 0; i < stringList.size(); i++) {
			
			newStringList.append(transform.apply(stringList.get(i)));
			
		}
	
		return newStringList;
		
	}
	
	public StringList filter(Selector selector){
		
		StringList newStringList = new StringList();
		
			for(int i = 0; i < stringList.size(); i++) {
				
				if(selector.select(stringList.get(i))) {
					
					newStringList.append(stringList.get(i));
					
				}
				
			}
		
		return newStringList;
		
	}
	
	public String reduce(Combiner combiner, String initialValue) {
		
		String result = "";
		result += initialValue;
		
		for(int i = 0; i < stringList.size(); i++) {
			
			result = combiner.combine(result, stringList.get(i));
			
		}
		
		return result;
		
	}
	
	public int reduce (IntCombiner combiner, int initialValue){
		
		int result = initialValue;
		
		for(int i = 0; i < stringList.size(); i++){
			
			result = combiner.combine(result, stringList.get(i));
			
		}
		
		return result;
		
	}
 
	public String toString(){
		
		String result = "[";
		
		for(int i = 0; i < stringList.size(); i++){
			
			if(i > stringList.size()-2){
			
				result += stringList.get(i);
			
			} else {
				
				result = result + stringList.get(i) + ", ";
				
			}
				
		}
		
		result += "]";
		
		return result;
		
	}

}
