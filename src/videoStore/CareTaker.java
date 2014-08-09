package videoStore;

public class CareTaker {
	private static final String filePath = "inventory_memento.ser";
	private static final String temporaryFilePath = "temporary_memento.ser";
	
	/* saves memento to file */
	public void add(Memento state)
	{
		FileOperationsHandler.pushToFile(state, filePath, temporaryFilePath);
	}

	/* gets memento from file */
	public Memento get()
	{
	    Memento mementoObjectFromFile = (Memento) FileOperationsHandler.getFromFile(filePath);
		
		return mementoObjectFromFile;
	}
}
