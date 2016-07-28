
public class CommandCD extends ReadCommand implements Command {
	private MyObserver observer = new MyObserver();
	String path;
	public CommandCD(String path){
		this.path = path;
	}
	@Override
	public void execute(Repository r) throws CommandException {
		boolean ok = false;
		Folder f;
		f = (Folder)r;
		if(execute(f)){

			if(path.startsWith("~")){
				Main.FolderCurent = Main.FolderRoot;
				Main.AbsolutePath.removeAllElements();
				Main.AbsolutePath.add(Main.FolderCurent.numeFolder);
			}
			String[] arr = path.split("/");
			for(int i = 0; i < arr.length; i++){
				if(arr[i].equals("..")){
					if(!Main.FolderCurent.equals(Main.FolderRoot)){
						ok = true;
						Main.FolderCurent = Main.FolderCurent.parent;
						Main.AbsolutePath.remove(Main.AbsolutePath.size()-1);
					}
					
				}
				if(arr[0].contains("~"))
					arr[0] = "root";
				for(Repository rep : Main.FolderCurent.childTree){
					//daca am gasit folderul cautat
					if(rep instanceof Folder && ((Folder) rep).numeFolder.equals(arr[i])){
						ok = true;
						Main.FolderCurent = (Folder)rep;
						Main.AbsolutePath.add(Main.FolderCurent.numeFolder);
					}
				
				}
			}
			if(ok == false)
				try {
					throw new MyInvalidPathException(path);
				} catch (MyInvalidPathException e) {
					System.out.println(e.message);
					observer.exception(e);
				}
		
		}
		else
			throw new CommandException("No permissions to write");
	}

}
