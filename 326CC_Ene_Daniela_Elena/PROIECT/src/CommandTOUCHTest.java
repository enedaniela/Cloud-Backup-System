import static org.junit.Assert.*;
import org.junit.Test;


public class CommandTOUCHTest {

	@Test
	public void test() throws CommandException {
		Main.init();
		String output = "testFolder";
		CommandTOUCH touch = new CommandTOUCH(output,5,true);
		Repository r;
		r = (Repository) Main.FolderCurent;
		touch.execute(r);
		File last = (File)(Main.FolderCurent.childTree.get(Main.FolderCurent.childTree.size()-1));
		assertEquals(output,last.numeFisier);		
	}

}
