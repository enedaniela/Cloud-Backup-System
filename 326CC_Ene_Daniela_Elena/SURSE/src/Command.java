import javax.swing.text.BadLocationException;


interface Command {
	void execute(Repository r) throws CommandException, BadLocationException;

}
