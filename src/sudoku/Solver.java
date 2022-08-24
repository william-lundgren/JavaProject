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
	
	@Override
	public boolean isValid() {
		for (int j = 0; j < 9; j++) {
			if(columnContainsDuplicates(j)) {
				return false;
			}
		}
		for(int i = 0; i < 9; i++) {
			if(rowContainsDuplicates(i)) {
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
		for(int i = 0; i < m.length; i++) {
			for(int j = 0; j < m[i].length; j++) {
				field[i][j] = m[i][j];
			}
		}
	}

	@Override
	public int[][] getMatrix() {
		return field;
	}

}
