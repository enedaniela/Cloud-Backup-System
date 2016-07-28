import javax.print.attribute.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;


public class Filter extends DocumentFilter {

	int promptPosition;
	public Filter() throws BadLocationException{
		promptPosition = 15;
	}
    public void insertString(final FilterBypass fb, final int offset, final String string, final AttributeSet attr)
            throws BadLocationException {
        if (offset >= promptPosition) {
            super.insertString(fb, offset, string, (javax.swing.text.AttributeSet) attr);
        }
    }

    public void remove(final FilterBypass fb, final int offset, final int length) throws BadLocationException {
        if (offset >= promptPosition) {
            super.remove(fb, offset, length);
        }
    }

    public void replace(final FilterBypass fb, final int offset, final int length, final String text, final AttributeSet attrs)
            throws BadLocationException {
        if (offset >= promptPosition) {
            super.replace(fb, offset, length, text, (javax.swing.text.AttributeSet) attrs);
        }
    }
}
