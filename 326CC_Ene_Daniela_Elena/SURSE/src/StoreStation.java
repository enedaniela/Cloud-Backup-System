
public abstract class StoreStation {
	public MachineID machine;
	public abstract void store(Repository r);
	public abstract Repository search();
	
}
