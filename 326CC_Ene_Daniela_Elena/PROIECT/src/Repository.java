import javax.swing.text.BadLocationException;

public interface Repository {
	
	public void accept(Command c) throws CommandException, BadLocationException;

}
