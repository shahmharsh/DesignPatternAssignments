package videoStore;

/* Performs inventory backup after every 10 operations that change system state */
public class PersistentInventory implements DVDInventory {

	private Inventory inventory;
	private CommandInvoker commands;
	private int numberOfOperations;
	
	public PersistentInventory(Inventory inventory) {
		this.inventory = inventory;
		commands = new CommandInvoker();
		numberOfOperations = 1;
	}
	
	/* Performs inventory backup to file if required */
	private void backupCheck()
	{
		if(++numberOfOperations % 10 == 0)
		{
		    CareTaker careTaker = new CareTaker();
		    // Get state from inventory object and give it to caretaker
		    careTaker.add(inventory.saveStateToMemento());
		    commands.clear();
		}
	}
	
	@Override
	public void addMovie(String name, double price, int quantity) throws MovieAlreadyExistsException {
		try {
			commands.execute(new AddMovieCommand(inventory, name, price, quantity));
			backupCheck();
		} catch (MovieNotFoundException | NotEnoughCopiesException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addCopies(int id, int quantity) throws MovieNotFoundException {
		try {
			commands.execute(new AddCopiesCommand(inventory, id, quantity));
			backupCheck();
		} catch (NotEnoughCopiesException | MovieAlreadyExistsException e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void sellMovie(int id, int quantity) throws NotEnoughCopiesException, MovieNotFoundException {
		try {
			commands.execute(new SellMovieCommand(inventory, id, quantity));
			backupCheck();
		} catch (MovieAlreadyExistsException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void changePrice(int id, double price) throws MovieNotFoundException {
		try {
			commands.execute(new ChangePriceCommand(inventory, id, price));
			backupCheck();
		} catch (MovieAlreadyExistsException | NotEnoughCopiesException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public double findPrice(int id, String movieName) throws MovieNotFoundException {
		return inventory.findPrice(id, movieName);
	}

	@Override
	public int findQuantity(int id, String movieName) throws MovieNotFoundException {
		return inventory.findQuantity(id, movieName);
	}
	
	@Override
	public int getID(String movieName) throws MovieNotFoundException {
		return inventory.getID(movieName);
	}
	
	@Override
	public int size() {
		return inventory.size();
	}
	
	@Override
	public String getMovieDetails(int id) throws MovieNotFoundException {
		return inventory.getMovieDetails(id);
	}

	/* Restores the inventory to previous state saved on disk. 
	 * The programmer has to make sure he calls this method first after a system crash before performing
	 * any other operations or he might lose important information.*/
	public void restore() {
		CareTaker careTaker = new CareTaker();
		// Store inventory from caretaker
	    inventory.getStateFromMemento(careTaker.get());
	    
	    commands.restoreInventory(inventory);
	}
}
