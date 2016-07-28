
public class CommandMKDIR extends WriteCommand implements Command{
	
	String numeFolder;
	
	public CommandMKDIR(String numeFolder){
		this.numeFolder = numeFolder;
	}
	
	public void execute(Repository r) throws CommandException{
		//creez folderul
		Folder f = new Folder(numeFolder);
		//convertesc folderul parinte din Repository la Folder
		Folder fParent;
		fParent = (Folder)r;
		//adaug folderul nou creat
		if(execute(fParent)){
			fParent.add(f);
			//Main.frame.addTextArea("<Success");
		}
		else
			//Main.frame.addTextArea("Error: No permissions to write");
			throw new CommandException("No permissions to write");
	}
}
