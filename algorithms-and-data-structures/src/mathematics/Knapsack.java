package mathematics;

import java.util.Arrays;

import tools.Output;

/*
 * The knapsack problem is a problem in combinatorial optimization:
 * Given a set of items, each with a weight and a value, determine 
 * the number of each item to include in a collection so that the 
 * total weight is less than or equal to a given limit and the total
 * value is as large as possible.
 */

public class Knapsack {
	
	/*
	 * 0-1 knapsack problem restricts the number of copies of each kind of item
	 * to zero or one.
	 */
	public static final int[] zeroOneKnapsack(int[] values, int[] weights,
			int capacity) {

		// Generate Solution Matrix
		int[][] solutionMatrix = new int[values.length + 1][capacity + 1];
		for (int i = 1; i <= values.length; i++) {
			for (int j = 0; j <= capacity; j++) {
				if (j - weights[i - 1] >= 0) {
					solutionMatrix[i][j] = Math
							.max(solutionMatrix[i - 1][j], values[i - 1]
									+ solutionMatrix[i - 1][j - weights[i - 1]]);
				} else {
					solutionMatrix[i][j] = solutionMatrix[i - 1][j];
				}
			}
		}

		// Get Optimal Subset
		int[] subset = new int[weights.length];
		int numItems = 0;
		int i = solutionMatrix.length - 1;
		for (int j = solutionMatrix[0].length - 1; j >= 0 && i > 0; i--) {
			// If the item is in the optimal subset, add it and subtract its
			// weight
			// from the column we are checking.
			if (solutionMatrix[i][j] != solutionMatrix[i - 1][j]) {
				subset[numItems] = i - 1;
				j -= weights[i - 1];
				numItems++;
			}
		}
		return Arrays.copyOfRange(subset, 0, numItems);
	}

	/*
	 * Unbounded knapsack problem allows any number of copies of each kind of item.
	 */
	public static int[] unboundedKnapsack(int[] values, int[] weights,
			int capacity) {
		int[] sums = new int[capacity + 1];
		int[] bests = new int[capacity + 1];
		for (int c = 0; c < weights.length; c++) {
			for (int i = 0; i <= capacity; i++) {
				if (weights[c] > i)
					continue;
				if (sums[i] > sums[i - weights[c]] + values[c])
					continue;
				sums[i] = sums[i - weights[c]] + values[c];
				bests[i] = c;
			}
		}

		int[] knapsack = new int[capacity];
		int numItems = 0;
		while (capacity > 0) {
			knapsack[numItems] = bests[capacity];
			capacity -= weights[bests[capacity]];
			numItems++;
		}
		return Arrays.copyOfRange(knapsack, 0, numItems);
	}
}