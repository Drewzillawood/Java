package practiceExam;

public abstract class Insect {

	protected int size;
	protected String color;
	
	public Insect(int size, String color) {
		
		this.size = size;
		this.color = color;
		
	}
	
	public int getSize() {
		
		return size;
		
	}
	
	public String getColor() {
		
		return color;
		
	}
	
	public abstract void attack();
	
}
