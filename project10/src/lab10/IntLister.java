package lab10;

public class IntLister {

	   public static void main(String[] args) {
	      
	      IntList list = new IntList();
	      
	      list.add(5);
	      list.add(4);
	      list.add(3);
	      System.out.println(list);
	      System.out.println("Size: " + list.size());
	      System.out.println("Min: " + list.getMinimum());
	      System.out.println("Max: " + list.getMaximum());
	   }
	}