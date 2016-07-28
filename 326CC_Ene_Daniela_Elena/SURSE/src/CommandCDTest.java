import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class CommandCDTest {

	@Test
	public void test() throws IOException, CommandException {

		String output = "testFolder";
		Main.init();
		CommandMKDIR mkdir = new CommandMKDIR(output);
		Repository r;
		r = (Repository) Main.FolderCurent;
		mkdir.execute(r);
		CommandCD cd = new CommandCD(output);
		cd.execute((Repository)Main.FolderCurent);
		assertEquals(Main.FolderCurent.numeFolder,output);
	}

}
