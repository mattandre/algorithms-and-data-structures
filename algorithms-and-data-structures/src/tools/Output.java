package tools;

public class Output {
	
	/**
	 * Prints an array to the console, applying the given title.
	 */
	public static void printArray(int[] array, String title) {
		StringBuilder builder = new StringBuilder();
		builder.append("\n").append(title).append(":\n{");
		
		if (array.length != 0)
			builder.append(array[0]);
		
		for (int i = 1; i < array.length; i++) {
			builder.append(", ").append(array[i]);
		}
		
		builder.append("}");
		
		System.out.println(builder.toString());
		
	}

	/**
	 * Prints a matrix (2-dimensional array) to the console, applying the given title.
	 */
	public static void printMatrix(int[][] matrix, String title) {
		StringBuilder builder = new StringBuilder();
		builder.append(title).append(":\n");
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				builder.append(matrix[i][j]).append("\t");
			}
			builder.append("\n");
		}
		System.out.print(builder.toString());
	}
	
}
