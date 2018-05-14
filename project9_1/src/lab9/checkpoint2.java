package lab9;

public class checkpoint2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thing box1 = new Thing();
		Thing box2 = new Thing();
		Thing box3 = new Thing("banana");
		box1.addThing(box3);
		for(int i = 0; i < 2; i++)
		{
			box1.addThing(box2);
			box2.addThing(new Thing(i + ": lumps of coal"));
		}
		Thing.listAllPresents(box1);

	}

}
