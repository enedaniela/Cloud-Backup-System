import static org.junit.Assert.*;
import org.junit.Test;


public class CommandMKDIRTest {

	@Test
	public void test() throws CommandException {
		Main.init();
		String output = "testFolder";
		CommandMKDIR mkdir = new CommandMKDIR(output);
		Repository r;
		r = (Repository) Main.FolderCurent;
		mkdir.execute(r);
		Folder last = (Folder)(Main.FolderCurent.childTree.get(Main.FolderCurent.childTree.size()-1));
		assertEquals(output,last.numeFolder);				
	}

}
