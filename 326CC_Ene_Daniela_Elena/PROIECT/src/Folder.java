import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.text.BadLocationException;


public class Folder implements Repository, Cloneable{

	String numeFolder;
	String dataCreare;
	int dimensiune;
	Folder parent;
	MachineID machineID;
	Vector<Folder> subdir;
	public List<Repository> childTree = new ArrayList<Repository>();
	public Vector<Permisiuni> permisiuniFolder;
	
	public Folder(Folder copie){
		this.numeFolder = copie.numeFolder;
		this.dataCreare = copie.dataCreare;
		this.dimensiune = copie.dimensiune;
		this.permisiuniFolder = copie.permisiuniFolder;
		for(int i = 0; i < copie.childTree.size(); i++)
			if(copie.childTree.get(i) instanceof Folder){
				Folder f = new Folder((Folder)copie.childTree.get(i));
				f.parent = this;
				this.childTree.add(f);				
			}
			else{
				File f = new File((File)copie.childTree.get(i));
				this.childTree.add(f);
				
			}
	}
	
	public Folder(String nume){
		this.numeFolder = nume;
		this.dataCreare = getCurrentDate();
		this.dimensiune = 0;
		this.parent = Main.FolderCurent;
		permisiuniFolder = new Vector<Permisiuni>();
		
		//creez permisiuni pentru root si guest
		Permisiuni rootPermisiuni = new Permisiuni(true, true, Main.listaUtilizatori.get(0));
		Permisiuni guestPermisiuni = new Permisiuni();
		Permisiuni currentPermisiuni = new Permisiuni(true, true, Main.UtilizatorCurent);
		
		//le adaug in vectorul de permisiuni al folderului
		this.permisiuniFolder.add(0,rootPermisiuni);
		this.permisiuniFolder.add(1,guestPermisiuni);
		this.permisiuniFolder.add(2,currentPermisiuni);
		
		//adaug permisiuni pentru toti ceilalti utilizatori
		for(int i = 2; i < Main.listaUtilizatori.size(); i++){
			if(!Main.listaUtilizatori.get(i).equals(Main.UtilizatorCurent)){
				Permisiuni allPermisiuni = new Permisiuni(false,false,Main.listaUtilizatori.get(i));
				this.permisiuniFolder.add(allPermisiuni);
			}
		}		
		
	}
	
	public String getCurrentDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	@Override
	public void accept(Command c) throws CommandException, BadLocationException {
		c.execute(this);
	}
		
	public void folderDetails() throws BadLocationException{
		Main.frame.addTextArea("<D: "+this.numeFolder+" "+this.dimensiune + " " + this.dataCreare);
	}
	
	//adauga subarbore la ierarhie
    public void add(Repository component) {
        childTree.add(component);
    }

    //sterge subarbore din ierarhie
    public void remove(Repository component) {
        childTree.remove(component);
    }

}
