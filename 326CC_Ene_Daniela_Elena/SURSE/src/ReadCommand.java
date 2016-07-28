
public abstract class ReadCommand{

	//verific daca utilizatorul curent are permisiuni de citire pe folder
	boolean execute(Folder dir){
		//daca sunt guest
		if(Main.UtilizatorCurent.equals(Main.listaUtilizatori.get(1)))
			return false;
		//daca sunt root
		if(dir.numeFolder.equals("root"))
			return true;
		int indexUtilizatorCurent = 1;
		for(int i = 0; i < dir.permisiuniFolder.size();i++)
		{
			if(dir.permisiuniFolder.get(i).utilizator.equals(Main.UtilizatorCurent))
				indexUtilizatorCurent = i;
		}
		
		if(dir.permisiuniFolder.get(indexUtilizatorCurent).read == true)
			return true;
		return false;			
	}
	
	//verific daca utilizatorul curent are permisiuni de citire pe fisier
	boolean execute(File file){
		//daca sunt guest
		if(Main.UtilizatorCurent.equals(Main.listaUtilizatori.get(1)))
			return false;
		//daca sunt root
		if(file.numeFisier.equals("root"))
			return true;
		int indexUtilizatorCurent = 0;
		for(int i = 0; i < file.permisiuniFisier.size();i++)
		{
			if(file.permisiuniFisier.get(i).utilizator.equals(Main.UtilizatorCurent))
				indexUtilizatorCurent = i;
		}
		
		if(file.permisiuniFisier.get(indexUtilizatorCurent).read == true)
			return true;
		return false;
	}
	

}
