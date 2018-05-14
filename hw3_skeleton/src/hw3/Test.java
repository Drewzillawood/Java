package hw3;

import api.GridPosition;
import api.Jewel;

public class Test {

	public static void main(String[] args) {
		
		String[] strings = new String[3];
//		String[] s = {"2 2 2 2 2"};
		strings[0] = "2 9 2 3";
		strings[1] = "2 2 1 3";
		strings[2] = "6 6 3 2";
		Game game = new Game(strings, new JewelFactory(10));
//		ArrayList<GridPosition> hRuns = game.findHorizontalRuns(1);
//		ArrayList<GridPosition> vRuns = game.findVerticalRuns(5);
		game.findAndMarkAllRuns();
		System.out.println(game.toString());
		game.select(new GridPosition(0, 2, new Jewel(2)), new GridPosition(1, 2, new Jewel(1)));
		System.out.println(game.toString());
		game.findAndMarkAllRuns();
		System.out.println(game.toString());
		game.fillAll();
		System.out.println(game.toString());
	}

}
