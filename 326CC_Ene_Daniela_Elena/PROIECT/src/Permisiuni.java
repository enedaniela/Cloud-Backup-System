public class Permisiuni {

	boolean read;
	boolean write;
	Utilizator utilizator;

	public Permisiuni(boolean read, boolean write, Utilizator utilizator){
		this.read = read;
		this.write = write;
		this.utilizator = utilizator;
	}
	
	//permisiuni pentru guest
	public Permisiuni(){
		this.read = true;
		this.write = false;
		this.utilizator = Main.listaUtilizatori.get(1);
	}
	
}


