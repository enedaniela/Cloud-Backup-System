import java.util.ArrayList;


public class CloudService {

	public static ArrayList<MyStoreStation> arrStation = new ArrayList<>();
	
	public boolean getCommand(String commandType){
		
		if(commandType.startsWith("upload ")){
			String[] arr = commandType.split(" ");
			for(Repository rep : Main.FolderCurent.childTree)
				if(rep instanceof Folder && ((Folder)rep).numeFolder.equals(arr[1]))
					upload(((Folder)rep));
				else if(rep instanceof File && ((File)rep).numeFisier.equals(arr[1])){
					upload((File)rep);
				}
		}
		else if(commandType.startsWith("sync ")){
			String[] arr = commandType.split(" ");
			for(int i = 0; i < Main.FolderCurent.childTree.size(); i++){
				Repository rep = Main.FolderCurent.childTree.get(i);
				if(rep instanceof Folder && ((Folder)rep).numeFolder.equals(arr[1]))
					sync(((Folder)rep));
			}
		}
		return true;
	}
	
	public void upload(Repository rep){
		Repository r;
		r = (Repository) Main.FolderCurent;
		CommandUPLOAD upload = new CommandUPLOAD(rep);
		upload.execute(r);
	}
	
	public void sync(Folder folder){
		Repository r;
		r = (Repository) Main.FolderCurent;
		CommandSYNC sync = new CommandSYNC(folder);
		sync.execute(r);
	}
}
