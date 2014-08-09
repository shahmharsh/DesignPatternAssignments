package videoStore;
import java.io.Serializable;
import java.util.Hashtable;

public class Inventory implements DVDInventory, Serializable{
	
	private static final long serialVersionUID = 1L;
	private int currentID;
	private Hashtable<Integer, Movie> movies;

	public Inventory()
	{
		movies = new Hashtable<Integer, Movie>();
		currentID = 0;
	}
	
	@Override
	public void addMovie(String name, double price, int quantity) throws MovieAlreadyExistsException
	{
		try {
			findPrice(name); // dummy method to check if movie already exists
		} catch (MovieNotFoundException e) {
			Movie newMovie = new Movie(++currentID, name, price, quantity);
			movies.put(newMovie.ID, newMovie);
			return;
		}
		
		throw new MovieAlreadyExistsException("A movie with this name already exists in inventory");
	}
	
	@Override
	public void addCopies(int id, int quantity) throws MovieNotFoundException
	{
		Movie selectedMovie = movies.get(id);
		if(selectedMovie == null)
			throw new MovieNotFoundException("Movie not found in inventory");
		
		selectedMovie.addCopies(quantity);
		movies.put(id, selectedMovie);
	}
	
	@Override
	public void sellMovie(int id, int quantity) throws NotEnoughCopiesException, MovieNotFoundException
	{
		Movie selectedMovie = movies.get(id);
		if(selectedMovie == null)
			throw new MovieNotFoundException("Movie not found in inventory");
		
		selectedMovie.sell(quantity);
		movies.put(id, selectedMovie);
	}
	
	@Override
	public void changePrice(int id, double price) throws MovieNotFoundException
	{
		Movie selectedMovie = movies.get(id);
		if(selectedMovie == null)
			throw new MovieNotFoundException("Movie not found in inventory");
		
		selectedMovie.changePrice(price);
		movies.put(id, selectedMovie);
	}
	
	@Override
	public double findPrice(int id, String movieName) throws MovieNotFoundException
	{
		if(movieName != null)
			return findPrice(movieName);
		
		if(id > 0)
			return findPrice(id);
		
		throw new MovieNotFoundException("Movie not found in inventory");
	}
	
	private double findPrice(int id) throws MovieNotFoundException
	{
		if(!movies.containsKey(id))
			throw new MovieNotFoundException("Movie not found in inventory");
		
		Movie currentMovie = movies.get(id);
		return currentMovie.price;
	}
	
	private double findPrice(String movieName) throws MovieNotFoundException
	{
		for(Integer id: movies.keySet())
		{
			Movie currentMovie = movies.get(id);
			if(currentMovie.name.equalsIgnoreCase(movieName))
				return currentMovie.price;
		}
		
		throw new MovieNotFoundException("Movie not found in inventory");
	}
	
	@Override
	public int findQuantity(int id, String movieName) throws MovieNotFoundException
	{
		if(movieName != null)
			return findQuantity(movieName);
		
		if(id > 0)
			return findQuantity(id);
		
		throw new MovieNotFoundException("Invalid ID and/or Movie Name");
	}
	
	private int findQuantity(int id) throws MovieNotFoundException
	{
		if(!movies.containsKey(id))
			throw new MovieNotFoundException("Movie not found in inventory");
		
		Movie currentMovie = movies.get(id);
		return currentMovie.quantity;
	}
	
	private int findQuantity(String movieName) throws MovieNotFoundException
	{
		for(Integer id: movies.keySet())
		{
			Movie currentMovie = movies.get(id);
			if(currentMovie.name.equalsIgnoreCase(movieName))
				return currentMovie.quantity;
		}
		
		throw new MovieNotFoundException("Movie not found in inventory");
	}
	
	@Override
	public int getID(String movieName) throws MovieNotFoundException {
		for(Integer id: movies.keySet())
		{
			Movie currentMovie = movies.get(id);
			if(currentMovie.name.equalsIgnoreCase(movieName))
				return currentMovie.ID;
		}
		
		throw new MovieNotFoundException("Movie not found in inventory");
	}
	
	@Override
	public int size() {
		return movies.size();
	}
	
	@Override
	public String getMovieDetails(int id) throws MovieNotFoundException {
		
		if(!movies.containsKey(id))
			throw new MovieNotFoundException("Movie not found in inventory");
		
		Movie currentMovie = movies.get(id);
		String movieDetails = "ID: " + currentMovie.ID 
							+ ", Name: " + currentMovie.name 
							+ ", Price: " + currentMovie.price
							+ ", Quantity: " + currentMovie.quantity;
		return movieDetails;
	}
	
	/* returns a memento of current inventory */
	Memento saveStateToMemento() {
		 return new Memento(this);
	}

	/* restores the current inventory to state passed as memento */
	void getStateFromMemento(Memento passedMemento) {
		Inventory temporaryInventory = passedMemento.getState();
		movies = temporaryInventory.movies;
		currentID = temporaryInventory.currentID;
	}
}
