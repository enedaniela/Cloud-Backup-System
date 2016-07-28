
public class CommandTOUCH extends WriteCommand implements Command{

	String numeFisier;
	int dimensiuneFisier;
	boolean tipFisier;
	
	public CommandTOUCH(String numeFisier, int dimensiuneFisier, boolean tipFisier){
		this.numeFisier = numeFisier;
		this.dimensiuneFisier = dimensiuneFisier;
		this.tipFisier = tipFisier;
		
	}
	@Override
	public void execute(Repository r) throws CommandException {
		//creez fisierul
		File f = new File(numeFisier,dimensiuneFisier,tipFisier);
		//convertesc folderul parinte din Repository la Folder
		Folder fParent;
		fParent = (Folder)r;
		if(execute(fParent)){
		//adaug fisierul
		fParent.add(f);
		fParent.dimensiune = fParent.dimensiune + f.dimensiuneFisier;
		System.out.println("New file "+f.numeFisier+" in folder "+fParent.numeFolder+"\n");
		}
		else
			throw new CommandException("No permissions to write");
	}

}
