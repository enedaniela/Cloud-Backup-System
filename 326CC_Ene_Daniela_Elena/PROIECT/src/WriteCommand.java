
public abstract class WriteCommand{
	//verific daca utilizatorul curent are permisiuni de scriere pe folder
	public boolean execute(Folder dir){
		//daca sunt guest
		if(Main.UtilizatorCurent.equals(Main.listaUtilizatori.get(1)))
			return false;
		//daca sunt root
		if(dir.numeFolder.equals("root"))
			return true;

		int indexUtilizatorCurent = 0;
		for(int i = 0; i < dir.permisiuniFolder.size();i++)
		{
			if(dir.permisiuniFolder.get(i).utilizator.equals(Main.UtilizatorCurent)){
				indexUtilizatorCurent = i;
				}
		}
		
		if(dir.permisiuniFolder.get(indexUtilizatorCurent).write == true)
			return true;
		return false;
	}
	//verific daca utilizatorul curent are permisiuni de scriere pe fisier
	public boolean execute(File file){
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
		
		if(file.permisiuniFisier.get(indexUtilizatorCurent).write == true)
			return true;
		return false;
	}
}
