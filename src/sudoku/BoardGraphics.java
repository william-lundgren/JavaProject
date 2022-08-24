package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;

import javax.print.attribute.AttributeSet;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


public class BoardGraphics {

	public BoardGraphics() {
		SwingUtilities.invokeLater(() -> createWindow("Sudoku", 450, 500));
	}

	private void createWindow(String title, int width, int height) {
		JTextField[][] fields = new JTextField[9][9];
		
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel gridPanel = new JPanel();
		GridLayout grid = new GridLayout(9, 9);
		grid.setHgap(3);
		grid.setVgap(3);
		gridPanel.setLayout(grid);
		
		Solver s = new Solver();
		
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				
				JTextField text = new JTextField(1);
				fields[r][c] = text;
				text.setFont(new Font("Serif",Font.PLAIN,  50));
				text.setHorizontalAlignment(JTextField.CENTER);
				
				text.addKeyListener(new KeyAdapter() {
			         @Override
			         public void keyTyped(KeyEvent e) {
			        	 char c = e.getKeyChar();
							if (Character.isDigit(c) && Character.getNumericValue(c) > 0) {
								text.setText(Character.toString(c));
							}
							
							e.consume();
			         }
			      });
				
				// Corner 3x3's
				if((r < 3 || r > 5) && (c < 3 || c > 5)) {
					text.setBackground(Color.orange);
				}
				
				// Middle 3x3
				else if(r < 6 && r > 2 && c < 6 && c > 2 ) {
					text.setBackground(Color.orange);
				}
				gridPanel.add(text);
			}	
		}			

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
     
		JButton solve = new JButton("Solve");
		JButton clear = new JButton("Clear");
		
		clear.addActionListener(event -> {
			s.clear();
			for(int r = 0; r < 9; r++) {
				for(int c = 0; c < 9; c++) {
					fields[r][c].setText("");
				}
			}
		});
		
		solve.addActionListener(event -> {
			for(int r = 0; r < 9; r++) {
				for(int c = 0; c < 9; c++) {
					try {
						s.add(r, c, Integer.parseInt(fields[r][c].getText()));
					}
					catch (NumberFormatException e) {
						s.add(r, c, 0);
					}
				}
			}
			
			print("Start");
			boolean solutionFound = s.solve();
			print("Done!");
			if (solutionFound) {
				int[][] solvedMatrix = s.getMatrix();
				
				for (int r = 0; r < 9; r++) {
					for (int c = 0; c < 9; c++) {
						fields[r][c].setText(Integer.toString(solvedMatrix[r][c]));
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Ingen lösning funnen.");
			}
		});
		
		buttonPanel.add(solve);
		buttonPanel.add(clear);
		
		frame.add(gridPanel, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);

		frame.setSize(width, height);
		frame.setVisible(true);
	}
	public static void print(Object message) {
		System.out.println(message);
	}
}

