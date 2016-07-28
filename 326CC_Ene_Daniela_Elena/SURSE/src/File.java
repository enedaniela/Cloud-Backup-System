import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.text.BadLocationException;


public class File implements Repository, Cloneable{

	String numeFisier;
	int dimensiuneFisier;
	boolean tipFisier;
	String oraCreareFisier;
	MachineID machineID;
	public Vector<Permisiuni> permisiuniFisier;
	
	public File(File copie){
		this.numeFisier = copie.numeFisier;
		this.dimensiuneFisier = copie.dimensiuneFisier;
		this.oraCreareFisier = copie.oraCreareFisier;
		this.permisiuniFisier = copie.permisiuniFisier;
		this.tipFisier = copie.tipFisier;
	}
	
	public File(String numeFisier, int dimensiuneFisier, boolean tipFisier ){
		this.numeFisier = numeFisier;
		this.dimensiuneFisier = dimensiuneFisier;
		this.tipFisier = tipFisier;
		this.oraCreareFisier = getCurrentDate();
		permisiuniFisier = new Vector<Permisiuni>();
		
		//creez permisiuni pentru guest si root
		Permisiuni rootPermisiuni = new Permisiuni(true, true, Main.listaUtilizatori.get(0));
		Permisiuni guestPermisiuni = new Permisiuni();
		Permisiuni currentPermisiuni = new Permisiuni(true, true, Main.UtilizatorCurent);
	
		//le adaug in vectorul de permisiuni al fisierului
		this.permisiuniFisier.add(0,rootPermisiuni);
		this.permisiuniFisier.add(1,guestPermisiuni);
		this.permisiuniFisier.add(2, currentPermisiuni);
		
		//adaug permisiuni pentru toti ceilalti utilizatori
		for(int i = 2; i < Main.listaUtilizatori.size(); i++){
			if(!Main.listaUtilizatori.get(i).equals(Main.UtilizatorCurent)){
				Permisiuni allPermisiuni = new Permisiuni(true,false,Main.listaUtilizatori.get(i));
				this.permisiuniFisier.add(allPermisiuni);		
			}
		}
			
	}
	
	public String getCurrentDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public void fileInfo() throws BadLocationException{
		Main.frame.addTextArea("<F:"+this.numeFisier + " " + this.dimensiuneFisier + " " + this.tipFisier +" "+ this.oraCreareFisier );
	}
	
	public void fileDetails() throws BadLocationException{
		Main.frame.addTextArea("<F: "+this.numeFisier+" "+this.dimensiuneFisier + " " + this.oraCreareFisier );
	}
	
	@Override
	public void accept(Command c) throws CommandException, BadLocationException {
		c.execute(this);
		
	}

}
