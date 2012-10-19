import java.util.Arrays;

import tools.Output;

public class Knapsack {

	public static void main(String[] args) {

		int[] values = { 2, 4, 2, 1, 10 };
		int[] weights = { 1, 12, 2, 1, 4 };
		int capacity = 15;
		Output.printArray(zeroOneKnapsack(values, weights, capacity), "Solution");

	}
	
	public static final int[] zeroOneKnapsack(int[] values, int[] weights, int capacity) {
		int[][] solution = getSolutionMatrix(values, weights, capacity);
		return getOptimalSubset(solution, weights);
	}

	/**
	 * 
	 * Returns the solution matrix for the Knapsack problem associated with the
	 * given values, weights and knapsack capacity.
	 * 
	 * @param values
	 *            The values of the items.
	 * @param weights
	 *            The weights of the items.
	 * @param capacity
	 *            The capacity of the knapsack.
	 * 
	 */
	private static int[][] getSolutionMatrix(int[] values, int[] weights,
			int capacity) {
		int[][] matrix = new int[values.length + 1][capacity + 1];
		for (int i = 1; i <= values.length; i++) {
			for (int j = 0; j <= capacity; j++) {
				if (j - weights[i - 1] >= 0) {
					matrix[i][j] = Math.max(matrix[i - 1][j], values[i - 1]
							+ matrix[i - 1][j - weights[i - 1]]);
				} else {
					matrix[i][j] = matrix[i - 1][j];
				}
			}
		}
		return matrix;
	}

	/**
	 * 
	 * Returns the optimal subset of items that should be included in the
	 * knapsack given a completed solution matrix.
	 * 
	 * @param solutionMatrix
	 *            An N by W matrix, where N is the number of items and W is the
	 *            capacity of the knapsack.
	 * @param weights
	 *            An array of size N containing the weights of each of the
	 *            items.
	 * 
	 */
	private static int[] getOptimalSubset(int[][] solutionMatrix, int[] weights) {
		int[] subset = new int[weights.length];
		int numItems = 0;
		int i = solutionMatrix.length - 1;
		for (int j = solutionMatrix[0].length - 1; j >= 0 && i > 0; i--) {
			// If the item is in the optimal subset, add it and subtract its
			// weight
			// from the column we are checking.
			if (solutionMatrix[i][j] != solutionMatrix[i - 1][j]) {
				subset[numItems] = i;
				j -= weights[i - 1];
				numItems++;
			}
		}
		return Arrays.copyOfRange(subset, 0, numItems);
	}
}