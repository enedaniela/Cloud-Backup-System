
public class CommandSYNC implements Command{

	Folder folder;
	public CommandSYNC(Folder folder) {
		this.folder = folder;
	}

	@Override
	public void execute(Repository r) {
		for(int i  = 0; i < ((Folder)r).childTree.size(); i++)
			if(((Folder)r).childTree.get(i).equals(folder)){
				((Folder)r).childTree.remove(i);
				for( Repository rep :CloudService.arrStation.get(0).hSet )
					if(rep instanceof Folder && ((Folder)rep).numeFolder.equals(folder.numeFolder))
					{
						 ((Folder)r).childTree.add((Repository) rep);
						 if(rep instanceof Folder)
							 ((Folder)rep).parent = ((Folder)r);
					}											
			}		
	}

}
