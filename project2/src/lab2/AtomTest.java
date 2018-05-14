package lab2;

public class AtomTest {

	public static void main(String[] args) {
		
		Atom uranium238 = new Atom(92,146,92);
		
		System.out.println(uranium238.getAtomicCharge());
		System.out.println(uranium238.getAtomicMass());
		
		uranium238.decay();
		System.out.println(uranium238.getAtomicCharge());
		System.out.println(uranium238.getAtomicMass());
		

	}

}
