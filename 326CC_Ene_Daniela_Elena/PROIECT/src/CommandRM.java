import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommandRM extends WriteCommand implements Command{
	
	String nume;

	public CommandRM(String nume){
		this.nume = nume;
	}

	public void RMrecursive(Repository r) throws CommandException{
		//r este folderul unde sunt
		Folder f;
		f = (Folder) r;
		Pattern p = Pattern.compile(nume);
		//caut in toate subdirectoarele sau fisierele folderului unde sunt
		for (int i = 0; i < f.childTree.size(); i++){
			Repository rep = f.childTree.get(i);
			if(rep instanceof Folder){
				Folder folderChild;
				folderChild = (Folder)rep;
				//verfic daca exista folderul pe care vreau sa-l sterg
				Matcher m = p.matcher(folderChild.numeFolder);
				if(m.find()){
					if(execute(folderChild))//daca am permisiuni
					{
						((Folder) r).childTree.remove(folderChild);//sterg toate subdirectoarele si fisierele
						//scad dimensiunea folderului parinte
						((Folder)r).dimensiune = ((Folder)r).dimensiune - folderChild.dimensiune;
						System.out.println("The folder"+folderChild.numeFolder+" has been removed");
					}
				}
				else
					throw new CommandException("Folder does not exist");
			}
		}
	}
	@Override
	public void execute(Repository r) throws CommandException {
		//r este folderul unde sunt
			Folder f;
			f = (Folder) r;
			Pattern p = Pattern.compile(nume);
			boolean ok = false;
			//idem ca mai sus
			for (int i = 0; i < f.childTree.size(); i++){
				Repository rep = f.childTree.get(i);
				if(rep instanceof Folder){
					Folder folderChild;
					folderChild = (Folder)rep;
					Matcher m = p.matcher(folderChild.numeFolder);
					//verfic daca exista folderul pe care vreau sa-l sterg
					if(m.find()){
						ok = true;
						if(execute(folderChild))//daca am permisiuni
						{
							//daca e gol
							if(folderChild.childTree.size() == 0){
								((Folder) r).childTree.remove(folderChild);
								i--;
								System.out.println("The folder "+folderChild.numeFolder +" has been removed");
							}
							else 
								throw new CommandException("Folder "+folderChild.numeFolder+" is not empty");
								
						}
						else
							throw new CommandException("No permissions to write");
					}

				}			
				else
				{
					File fileChild;
					fileChild = (File)rep;
					Matcher m = p.matcher(fileChild.numeFisier);
					if(m.find()){
						ok = true;
						if(execute(fileChild)){
							((Folder) r).childTree.remove(fileChild);
							i--;
							System.out.println("The file "+fileChild.numeFisier+" has been removed");
							Folder fCopy;
							fCopy = f;
							while(fCopy != null){
								fCopy.dimensiune = fCopy.dimensiune - fileChild.dimensiuneFisier;
								fCopy = fCopy.parent;
							}
						}
						else
							throw new CommandException("No permissions to write");					
					}
				}						
			}
			if(ok == false)
				throw new CommandException("The file or folder does not exist");
	}
}
		
	
