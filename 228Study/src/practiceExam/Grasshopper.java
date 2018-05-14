package practiceExam;

public abstract class Grasshopper extends Insect implements Behavior {
	
	public Grasshopper(int size, String color) {
		
		super(size, color);
		
	}
	
	public void move() {
		
		System.out.println("hop");
		
	}
	
	@Override
	public void attack() {
		
		System.out.println("bite");
		
	}
	
	public abstract String antennae();

}
