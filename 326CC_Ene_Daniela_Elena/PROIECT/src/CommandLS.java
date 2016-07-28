import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.BadLocationException;


public class CommandLS extends ReadCommand implements Command{
	String pathOrRegex;
	
	public CommandLS(){
		
	}
	public CommandLS(String pathOrRegex){
		this.pathOrRegex = pathOrRegex;
	}
	

	public void LSrecursive(Repository r) throws BadLocationException{
		Pattern p = Pattern.compile(pathOrRegex);
		Folder f;
		f = (Folder) r;
		//pentru toate subdirectoarele folderului curent
		for (Repository rep : f.childTree){

			//daca este folder, apelez recursiv
			if(rep instanceof Folder ){
				Matcher m = p.matcher(((Folder)rep).numeFolder);
				if(m.find()){
					((Folder)rep).folderDetails();					
					LSrecursive(rep);
				}
			}
			//daca este fisier, afisez informatiile
			else if(rep instanceof File){
				File file;
				file = (File)rep;
				Matcher mf = p.matcher(((File)rep).numeFisier);
				if(execute(file) && mf.find())
					file.fileDetails();
			}
		}
	}
	
	public void LSTable(Repository r) throws CommandException{
		String[] columnNames = {"Foldere",
								"Fisiere"};
		String[][] data = new String[100][2];
		data[0][0] = "Foldere";
		data[0][1] = "Fisiere";
		int i = 1, j = 1;
		boolean ok = false;
		if(r instanceof Folder){
			Folder f;
			f = (Folder) r;
			for (Repository rep : f.childTree){
				if(rep instanceof Folder){
					Folder folderChild;
					folderChild = (Folder)rep;
					if(execute(folderChild))//daca am permisiuni de citire
					{
						ok = true;
						data[i][0] = folderChild.numeFolder;
						i++;
					}
				}
				else if(rep instanceof File){
					File fileChild;
					fileChild = (File)rep;
					if(execute(fileChild))//daca am permisiuni de citire
					{
						ok = true;
						data[j][1] = fileChild.numeFisier;
						j++;
					}
				}
			}
				
		}
		if(ok == false){
			throw new CommandException("No permissions to read");
		}
		else
			Main.frame.addTable(columnNames, data);
	}
	public void LSDetails(Repository r) throws CommandException, BadLocationException{
		boolean ok = false;
		Pattern p = Pattern.compile(pathOrRegex);
		if(r instanceof Folder){
			Folder f;
			f = (Folder) r;
			for (Repository rep : f.childTree){
				if(rep instanceof Folder){
					Folder folderChild;
					folderChild = (Folder)rep;
					Matcher m = p.matcher(folderChild.numeFolder);
					if(execute(folderChild) && m.find()){//daca am permisiuni
						folderChild.folderDetails();
						ok = true;
					}
				}
				else if(rep instanceof File){
					File fileChild;
					fileChild = (File)rep;
					Matcher m = p.matcher(fileChild.numeFisier);
					if(execute(fileChild) && m.find()){//daca am permisiuni
						fileChild.fileDetails();
						ok = true;
					}
				}
			}
				
		}
		if(ok == false)
			throw new CommandException("No permissions to read");
	}
	
	@Override
	public void execute(Repository r) throws CommandException, BadLocationException {
		boolean ok = false;
		if(r instanceof Folder){
			Folder f;
			f = (Folder) r;
			for (Repository rep : f.childTree){
				if(rep instanceof Folder){
					Folder folderChild;
					folderChild = (Folder)rep;
					if(execute(folderChild)){//daca am permisiuni de citire
						Main.frame.addTextArea(folderChild.numeFolder);
						ok = true;
					}
				}
				else if(rep instanceof File){
					File fileChild;
					fileChild = (File)rep;
					if(execute(fileChild)){//daca am permisiuni de citire
						Main.frame.addTextArea(fileChild.numeFisier);
						ok = true;
					}
				}
			}
				
		}
		else if(r instanceof File){
			File fileChild;
			fileChild = (File)r;
			if(execute(fileChild)){//daca am permisiuni
				fileChild.fileInfo();
				ok = true;
			}
		}
		if(ok == false)
			throw new CommandException("No permissions to read or the folder is empty");
		
	}
	
	public void executeRegex(Repository r) throws CommandException, BadLocationException{
		
		Pattern p = Pattern.compile(pathOrRegex);
		boolean ok = false;
		if(r instanceof Folder){
			Folder f;
			f = (Folder) r;

			for (Repository rep : f.childTree){
				if(rep instanceof Folder){
					Folder folderChild;
					folderChild = (Folder)rep;
					Matcher m = p.matcher(folderChild.numeFolder);
					if(execute(folderChild) && m.find()){//daca am permisiuni de citire
						Main.frame.addTextArea("\n"+folderChild.numeFolder);
						ok = true;
					}
				}
				else if(rep instanceof File){
					File fileChild;
					fileChild = (File)rep;
					Matcher m = p.matcher(fileChild.numeFisier);
					if(execute(fileChild) && m.find()){//daca am permisiuni de citire
						Main.frame.addTextArea("\n"+fileChild.numeFisier);
						ok = true;
					}
				}
			}
				
		}
		else if(r instanceof File){
			File fileChild;
			fileChild = (File)r;
			Matcher m = p.matcher(fileChild.numeFisier);
			if(execute(fileChild) && m.find()){//daca am permisiuni
				fileChild.fileInfo();
				ok = true;
			}
		}
		if(ok == false)
			throw new CommandException("No permissions to read");
	}
	
}
