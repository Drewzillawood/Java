package practiceExam;

public class TheMain {

	public static void main(String[] args) {
		
//		Behavior b = new Locust(2, "Black");
//		b.move();
		
//		Insect i = new Insect(3, "Green");
		
//		Insect b = new Bee(1, "Golden-Black", "Lake");
//		System.out.println(b.getColor());
//		System.out.println(b.getSwarm());
		
//		Mantis m = new Mantis(5, "green");
//		m.move();
//		Insect i = m.preyOn();
//		System.out.println(i.getColor());
		
//		Grasshopper g = new Locust(3, "Red");
//		Katydid k = (Katydid) g;
		
//		Grasshopper g = new Katydid(2, "Green");
//		g.attack();
//		g = new Locust(3 ,"Black");
//		System.out.println(((Locust) g).antennae());
//		Behavior b = g;
		
//		Insect k = new Katydid(2, "green");
//		Grasshopper g = (Katydid) k;
//		Locust l = (Katydid) k;
		
//		Insect i = new Mantis(4, "Yellow");
//		((Mantis)i).move();
//		((Mantis)i).preyOn().attack();
//		i = new Bee(1, "Golden-Black", "Hill");
//		((Bee)i).makeHoney();
		
		
//		Bee b = new Bee(1, "Black", "Lake");
//		System.out.println(b.equals(b));
//		Insect c = new Bee(1, "Black", "Lake");
//		System.out.println(b.equals(c));
		
		Bee b = new Bee(1, "Black", "Lake");
		System.out.println("This bee: " + b.getSize() + " " + b.getColor() + " " + b.getSwarm());
		Bee c = b.clone();
		System.out.println("This bee: " + c.getSize() + " " + c.getColor() + " " + c.getSwarm());
		
		

	}

}
