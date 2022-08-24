package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				//TODO limit to one character
				JTextField text = new JTextField(1);
				fields[i][j] = text;
				text.setFont(new Font("Serif",Font.PLAIN,  50));
				text.setHorizontalAlignment(JTextField.CENTER);
				
				// Corner 3x3's
				if((i < 3 || i > 5) && (j < 3 || j > 5)) {
					text.setBackground(Color.orange);
				}
				
				// Middle 3x3
				else if(i < 6 && i > 2 && j < 6 && j > 2 ) {
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
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					fields[i][j].setText("");
				}
			}
		});
		
		solve.addActionListener(event -> {
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					try {
						s.add(i, j, Integer.parseInt(fields[i][j].getText()));
					}
					catch (NumberFormatException e) {
						s.add(i, j, 0);
					}
				}
			}
			if(!s.isValid()) {
	 			JOptionPane.showMessageDialog(null, "Du har skrivit in ett bräde som inte fungerar");
			}
			else {
				s.solve();
			}
		});
		
		buttonPanel.add(solve);
		buttonPanel.add(clear);
		
		frame.add(gridPanel, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);

		frame.setSize(width, height);
		frame.setVisible(true);
		
		//group.add(alphabetic);
//				group.add(freq);
//				
//				JTextField textBox = new JTextField(30);
//		
//				
//				alphabetic.addActionListener(event -> {
//					model.sort((n1, n2) -> n1.getKey().compareTo(n2.getKey()));
//				});
//				
//				freq.addActionListener(event -> {
//					model.sort((n1, n2) -> n2.getValue() - n1.getValue());
//				});
//				
//				search.addActionListener(event -> {
//				 	String text = textBox.getText();
//				 	for(int i = 0; i < model.getSize(); i++) {
//				 		if(model.getElementAt(i).getKey().equalsIgnoreCase(text)) {
//				 			list.setSelectedIndex(i);
//				 			list.ensureIndexIsVisible(i);
//				 			break;
//				 		}
//				 		if(i == model.getSize() - 1) {
//				 			JOptionPane.showMessageDialog(null, "Den finns inte");
//				 		}
//				 	}
//				});
//				
//				textBox.addActionListener(event ->{
//					String text = textBox.getText();
//				 	for(int i = 0; i < model.getSize(); i++) {
//				 		if(model.getElementAt(i).getKey().equalsIgnoreCase(text)) {
//				 			list.setSelectedIndex(i);
//				 			list.ensureIndexIsVisible(i);
//				 			break;
//				 		}
//				 		if(i == model.getSize() - 1) {
//				 			JOptionPane.showMessageDialog(null, "Den finns inte");
//				 		}
//				 	}
//				});
			
//				panel.add(alphabetic);
//				panel.add(freq);
//				panel.add(textBox);
//				panel.add(search);
//
//				pane.add(panel, BorderLayout.SOUTH);
	}
	public static void print(Object message) {
		System.out.println(message);
	}
}

