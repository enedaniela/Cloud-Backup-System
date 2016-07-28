import javax.swing.text.BadLocationException;


public class CommandFactory {
	public boolean getCommand(String commandType) throws CommandException, BadLocationException{

			if(commandType.startsWith("ls")){
				sendLS(commandType);
			}
			else if(commandType.startsWith("cd ")){
				sendCD(commandType);
			}
			else if(commandType.startsWith("pwd")){
				sendPWD(commandType);		
			}
			else if(commandType.startsWith("mkdir ")){
				sendMKDIR(commandType);
			}
			else if(commandType.startsWith("touch ")){
				sendTOUCH(commandType);
			}
			else if(commandType.startsWith("rm ")){
				sendRM(commandType);
			}
			return true;
	}
	
		public void sendLS(String c) throws CommandException, BadLocationException{
			boolean ok = false;
			Repository r;
			r = (Repository) Main.FolderCurent;
			String[] arr = c.split(" ");
			CommandLS ls = new CommandLS(arr[arr.length-1]);
			for(int i = 0; i < arr.length; i++){
				if(arr[i].equals("-r"))
					ls.LSrecursive(r);
				if(arr[i].equals("-a"))
					ls.LSDetails(r);
				if(arr[i].contains("-POO")){
					ls.LSTable(r);
					ok = true;
				}
			}
			if(arr.length == 1){				
				ls.execute(r);
				}
			else if(arr.length == 2 && !ok)
				ls.executeRegex(r);
			else
			{
				//pentru fiecare subdirector si fisier
				for(Repository f : Main.FolderCurent.childTree)
					//verific daca e fisier
					if(f instanceof File){
						File fisier;
						fisier = (File)f;
					//verific daca este fisierul pe care il vreau
					if(fisier.numeFisier.equals(arr[1])){
						//execut ls pt fisierul dat ca parametru
						ls.execute(r);
					}
				}
			}			
		}
		
		public void sendCD(String c) throws CommandException{
			String[] arr = c.split(" ");
			CommandCD cd =  new CommandCD(arr[1]);
			Repository r;
			r = (Repository) Main.FolderCurent;
			cd.execute(r);
		}
		
		public void sendPWD(String c) throws BadLocationException{
			CommandPWD pwd =  new CommandPWD();
			Repository r;
			r = (Repository) Main.FolderCurent;
			pwd.execute(r);
		}
		
		public void sendMKDIR(String c) throws CommandException{
			String[] arr = c.split(" ");
			for( int i = 1; i < arr.length; i++){
				CommandMKDIR mkdir = new CommandMKDIR(arr[i]);
				Repository r;
				r = (Repository) Main.FolderCurent;
				mkdir.execute(r);
			}

				
		}
		
		public void sendTOUCH(String c) throws CommandException{
			String[] arr = c.split(" ");
			int n = Integer.parseInt(arr[2]);
			boolean b;
			if(arr[3].equals("text"))
				b = true;
			else
				b = false;
			CommandTOUCH touch = new CommandTOUCH(arr[1],n,b);
			Repository r;
			r = (Repository) Main.FolderCurent;
			touch.execute(r);
		}
		
		public void sendRM(String c) throws CommandException{

			String[] arr = c.split(" ");
			CommandRM rm = new CommandRM(arr[arr.length - 1]);
			Repository r;
			r = (Repository) Main.FolderCurent;
			for(int i = 0; i < arr.length; i++){
				if(arr[i].equals("-r"))
					rm.RMrecursive(r);
			}
			if(arr.length == 2){
				rm.execute(r);
			}
		}
}
