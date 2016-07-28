
public class CommandUPLOAD implements Command{
	private MyObserver observer = new MyObserver();
	Repository rep;
	public CommandUPLOAD(Repository rep) {
		this.rep = rep;
	}

	@Override
	public void execute(Repository r) {
		int totalDim = 0;
		int index = 0;
		for(MyStoreStation ss : CloudService.arrStation)
		{
			ss.machine.id = index;
			totalDim+=ss.dimensiune;
			index++;
		}
		if((rep instanceof Folder && totalDim < ((Folder)rep).dimensiune) || rep instanceof File && totalDim < ((File)rep).dimensiuneFisier)
			try {
				throw new MyNotEnoughSpaceException();
			} catch (MyNotEnoughSpaceException e) {
				observer.exception(e);
			}
		else{
				storeRecrusively(rep);					
		}		
		CloudService.arrStation.get(0).printStoreStation();	
		System.out.println("-----------------------------");
		CloudService.arrStation.get(1).printStoreStation();	
		System.out.println("-----------------------------");
		CloudService.arrStation.get(2).printStoreStation();	
	}
	
	public void storeRecrusively(Repository r){
		int i = 0;
		//este fisier, verific unde am loc si adaug
		if(r instanceof File)
		{
			if(CloudService.arrStation.get(0).dimensiune >= ((File)r).dimensiuneFisier)
			{
				CloudService.arrStation.get(0).store(r);
				i = 0;
			}
			else if(CloudService.arrStation.get(1).dimensiune >= ((File)r).dimensiuneFisier)
			{
				CloudService.arrStation.get(1).store(r);
				i = 1;
			}
			else{
				CloudService.arrStation.get(2).store(r);
				i = 2;
			}
		}
		//este folder, adaug si folderul
		else{
				CloudService.arrStation.get(i).store(r);
			//pentru toate subdirectoarele si fisierele apelez recursiv
			for(Repository child : ((Folder)r).childTree){					
					storeRecrusively(child);
				}
		}
					
				
	}

}
