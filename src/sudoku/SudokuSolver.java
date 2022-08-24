package sudoku;

public interface SudokuSolver {
	/**
	 * @return true if the board can be solved else false.
	 */
	boolean solve();

	/**
	 * Puts digit in the box row, col.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * @param digit The digit to insert in box row, col
	 * @throws IllegalArgumentException if row, col or digit is outside the range
	 *                                  [0..9]
	 */
	void add(int row, int col, int digit);

	/**
	 * Removes digit in the box row, col.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * @throws IllegalArgumentException if row, col or digit is outside the range
	 *                                  [0..9]
	 */
	void remove(int row, int col);

	/**
	 * Get the digit in the box row, col.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * @throws IllegalArgumentException if row, col or digit is outside the range
	 *                                  [0..9]
	 * @return the digit at box row, col.
	 */
	int get(int row, int col);

	/**
	 * Checks that all filled in digits follows the the sudoku rules.
	 * 
	 * @return true if the grid is valid else false
	 */
	boolean isValid();

	/**
	 * Clears the matrix for the board and sets all values to 0.
	 */
	void clear();

	/**
	 * Fills the grid with the digits in m. The digit 0 represents an empty box.
	 * 
	 * @param m the matrix with the digits to insert
	 * @throws IllegalArgumentException if m has the wrong dimension or contains
	 *                                  values outside the range [0..9]
	 */
	void setMatrix(int[][] m);

	/**
	 * Gets the matrix representing the board.
	 * 
	 * @return 2D array for the matrix
	 */
	int[][] getMatrix();
}