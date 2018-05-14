package practiceExam;

public class Bee extends Insect implements Behavior, Cloneable {

	private String swarm;
	
	public Bee(int size, String color, String swarm) {
		
		super(size, color);
		this.swarm = swarm;
		
	}
	
	public String getSwarm() {
		
		return swarm;
		
	}
	
	public void move() {
		
		System.out.println("fly");
		
	}
	
	@Override
	public void attack() {
		
		System.out.println("sting");
		
	}
	
	public void makeHoney() {
		
		System.out.println("Orange Blossom");
		
	}
	
	@Override 
	public boolean equals(Object o) {
		
		if(this.getColor() == ((Bee)o).getColor() && this.getSize() == ((Bee)o).getSize() && this.getSwarm() == ((Bee)o).getSwarm()) {
			
			return true;
			
		} else {
		
			return false;
		
		}
		
	}
	
	@Override
	public Bee clone() {
		
		return new Bee(this.getSize(),this.getColor(),this.getSwarm());
		
	}
	
}
