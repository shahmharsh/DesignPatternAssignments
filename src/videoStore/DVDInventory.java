package videoStore;

public interface DVDInventory {
	
	public void addMovie(String name, double price, int quantity) throws MovieAlreadyExistsException;
	public void addCopies(int id, int quantity) throws MovieNotFoundException;
	public void sellMovie(int id, int quantity) throws NotEnoughCopiesException, MovieNotFoundException;
	public void changePrice(int id, double price) throws MovieNotFoundException;
	
	/* returns price of the movie,  either by ID or movie name. User has to send one of the two
	 parameters, if search is by ID then it has to be greater than 0 and if by movieName then it has to be non null.*/
	public double findPrice(int id, String movieName) throws MovieNotFoundException;
	
	/* returns current quantity of the movie,  either by ID or movie name. User has to send one of the two
	 parameters, if search is by ID then it has to be greater than 0 and if by movieName then it has to be non null.*/
	public int findQuantity(int id, String movieName) throws MovieNotFoundException;
	
	/* returns number of movies currently present in inventory */
	public int size();
	
	public int getID(String movieName) throws MovieNotFoundException;
	public String getMovieDetails(int id) throws MovieNotFoundException;
}
