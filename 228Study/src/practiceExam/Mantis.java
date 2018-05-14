package practiceExam;

public class Mantis extends Insect implements Behavior {
	
	public Mantis(int size, String color) {
		
		super(size, color);
		
	}
	
	public void move() {
		
		System.out.println("crawl");
		
	}
	
	@Override
	public void attack() {
		
		System.out.println("strike");
		
	}
	
	public Grasshopper preyOn() {
		
		return new Locust(3, "Brown");
		
	}

}
