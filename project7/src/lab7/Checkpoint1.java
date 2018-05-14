package lab7;

import java.util.Arrays;
import java.util.Random;

public class Checkpoint1 {

	public static void main(String[] args) {

		int[] testArray = { 1, 2, 3, -1, -2, -3, -4 };

		System.out.println(Arrays.toString(getPositiveNumbers(testArray)));
		System.out.println(Arrays.toString(randomExperiment(3, 300)));
		System.out.println(Arrays.toString(switchHalves(testArray)));

	}

	private static int[] getPositiveNumbers(int[] numbers) {

		int size = 0;

		for (int i = 0; i < numbers.length; i++) {

			if (numbers[i] > 0) {

				size++;

			}

		}

		int[] replace = new int[size];

		for (int i = 0; i < numbers.length; i++) {

			if (numbers[i] > 0) {

				replace[i] = numbers[i];

			}

		}

		return replace;

	}

	private static int[] randomExperiment(int max, int iters) {

		int[] counts = new int[max];
		Random rand = new Random();
		int temp = 0;

		for (int i = 0; i < iters; i++) {

			temp = rand.nextInt(max);
			for (int j = 0; j < max; j++) {

				if (temp == j) {

					counts[j]++;

				}

			}

		}

		return counts;

	}

	private static int[] switchHalves(int[] numbers) {

		int index = (numbers.length % 2) + (numbers.length / 2);
		int holder = 0;

		for (int i = 0; i < numbers.length / 2; i++) {

			holder = numbers[i];
			numbers[i] = numbers[index];
			numbers[index] = holder;
			index++;

		}

		return numbers;

	}

}
