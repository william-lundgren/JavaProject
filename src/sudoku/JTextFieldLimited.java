package sudoku;

import javax.print.attribute.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

class JTextFieldLimited extends PlainDocument {
	private int max;
   
	JTextFieldLimited(int max) {
      super();
      this.max = max;
   }
   
   public void insertString(int offset, String text, AttributeSet attr) throws BadLocationException {
      if (text == null)
    	  System.out.println("aaaaa");
    	  return;
      
      if ((getLength() + text.length()) <= max) {
    	  System.out.println("AAAA");
         super.insertString(offset, text, attr);
      }
   }
}
