package hw1;

public class TenPinBolwingTest {

	public static void main(String[] args) {
		
		int frames = 10;
		
		TenPinBowling game = new TenPinBowling(frames);
		
		game.roll(10);
		game.roll(10);
		game.roll(1);
		game.roll(3);
		game.roll(6);

		
		System.out.println(game.getScore());
		
		
//		 game.roll(6);
//		 game.roll(4);
//		 game.roll(8);
//		 game.roll(1);
//		 System.out.println(game.getScore()); // expected 19
////		
//		game.roll(10);
//		game.roll(10);
//		game.roll(8);
//		game.roll(1);


		

//		System.out.println("Score = " + game.getScore());
		
	}

}
