package sudoku;

public class Solver implements SudokuSolver {
	private int[][] field;
	
	public Solver() {
		field = new int[9][9];
	}

	@Override
	public boolean solve() {
		return solve(0, 0);
	}
	
	private boolean solve(int r, int c) {
		if (r == 9 && c == 0)
			return true;
		
		if (field[r][c] != 0) {
			return solve(r + (c + 1) / 9, (c + 1) % 9);
		}
		else {
			for (int i = 1; i <= 9; i++) {
				field[r][c] = i;
				if (isValid()) {
					if (r == 9 && c == 9 || solve(r + (c + 1) / 9, (c + 1) % 9)) {
						return true;
					}
				}
			}
			
			field[r][c] = 0;
			return false;
		}
	}

	@Override
	public void add(int row, int col, int digit) {
		field[row][col] = digit;
	}

	@Override
	public void remove(int row, int col) {
		field[row][col] = 0;
	}

	@Override
	public int get(int row, int col) {
		return field[row][col];
	}
	
	private boolean rowContainsDuplicates(int row) {
		for(int i = 0; i < 8; i++) {
			for(int j = i + 1; j < 9; j++) {
				if(field[row][i] != 0 && field[row][i] == field[row][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean columnContainsDuplicates(int column) {
		for(int i = 0; i < 8; i++) {
			for(int j = i + 1; j < 9; j++) {
				if(field[i][column] != 0 && field[i][column] == field[j][column]) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean squareContainsDuplicates(int squareRow, int squareColumn) {
		int[] numberCount = new int[10];
		
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				if (++numberCount[field[squareRow * 3 + r][squareColumn *  3 + c]] == 2 && field[squareRow * 3 + r][squareColumn*  3 + c] != 0) {
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	@Override
	public boolean isValid() {	
		for(int i = 0; i < 9; i++) {
			if(squareContainsDuplicates(i / 3, i % 3) || rowContainsDuplicates(i) || columnContainsDuplicates(i)  ) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void clear() {
		setMatrix(new int[9][9]);
	}

	@Override
	public void setMatrix(int[][] m) {
		for(int r = 0; r < m.length; r++) {
			for(int c = 0; c < m[r].length; c++) {
				field[r][c] = m[r][c];
			}
		}
	}

	@Override
	public int[][] getMatrix() {
		return field;
	}

}
