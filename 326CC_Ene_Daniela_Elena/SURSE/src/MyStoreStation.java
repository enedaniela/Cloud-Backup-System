import java.util.HashSet;


public class MyStoreStation extends StoreStation {

	public MachineID machine = new MachineID();
	int dimensiune = 10;
	public HashSet<Repository> hSet = new HashSet<Repository>(dimensiune);

	@Override
	public void store(Repository r) {
	
		if(r instanceof File){
			File rClone = new File((File) r);
			((File) r).machineID = this.machine;
			hSet.add(rClone);
			dimensiune-=rClone.dimensiuneFisier;
		}
		else{
			Folder rClone = new Folder((Folder) r);
			((Folder)r).machineID = this.machine;
			hSet.add(rClone);
		}
		
	}
	
	@Override
	public Repository search() {
		return null;
	}
	
	public void printStoreStation(){
		for(Repository rep : hSet)
			if(rep instanceof Folder){
				System.out.println(((Folder)rep).numeFolder);
				
			}
			else
				System.out.println(((File)rep).numeFisier);
	}

}
