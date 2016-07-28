import javax.swing.text.BadLocationException;


public class CommandPWD extends ReadCommand implements Command{
	private MyObserver observer = new MyObserver();
	@Override
	public void execute(Repository r) throws BadLocationException {

		String path = "";
		for(String s : Main.AbsolutePath){
			path = path + "/" + s;
		}
		if(path.length() > 255)
			try {
				throw new MyPathTooLongException(path);
			} catch (MyPathTooLongException e) {
				Main.jTextArea1.append(e.message);
				e.printStackTrace();
				observer.exception(e);
			}
		else
			Main.frame.addTextArea(path);

	
	}
}
