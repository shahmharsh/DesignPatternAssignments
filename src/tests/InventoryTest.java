package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import videoStore.Inventory;
import videoStore.MovieAlreadyExistsException;
import videoStore.MovieNotFoundException;
import videoStore.NotEnoughCopiesException;

public class InventoryTest {

	Inventory inventory;
	@Before
	public void setUp() throws Exception {
		inventory = new Inventory();
		
	}

	@After
	public void tearDown() throws Exception {
		inventory = null;
	}

	@Test
	public void testAddMovie() {
		try {
			inventory.addMovie("abc", 10.99, 3);
			inventory.addMovie("def", 2.99, 5);
		} catch (MovieAlreadyExistsException e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected=MovieAlreadyExistsException.class)
	public void testAddDuplicateMovie() throws MovieAlreadyExistsException {
			inventory.addMovie("abc", 10.99, 3);
			inventory.addMovie("abc", 2.99, 5);
	}

	@Test
	public void testAddCopies() {
		try {
			inventory.addMovie("abc", 10.99, 3);
			inventory.addCopies(1, 5);
			assertEquals(8, inventory.findQuantity(1, null));
		} catch (MovieAlreadyExistsException | MovieNotFoundException e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected=MovieNotFoundException.class)
	public void testAddCopiesOfMovieNotPresentInInventory() throws MovieNotFoundException {
		inventory.addCopies(1, 3);	
	}
	
	@Test
	public void testSellMovie() {
		try {
			inventory.addMovie("abc", 10.99, 3);
			assertEquals(3, inventory.findQuantity(0, "abc"));
			inventory.sellMovie(1, 3);
			assertEquals(0, inventory.findQuantity(0, "abc"));
		} catch (MovieAlreadyExistsException | MovieNotFoundException | NotEnoughCopiesException e) {
			fail(e.getMessage());
		} 
	}
	
	@Test(expected=NotEnoughCopiesException.class)
	public void testSellMoreMoviesThanAvailable() throws NotEnoughCopiesException {
		try {
			inventory.addMovie("abc", 10.99, 3);
			inventory.sellMovie(1, 4);
		} catch (MovieAlreadyExistsException | MovieNotFoundException e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected=MovieNotFoundException.class)
	public void testSellMovieNotPresentInInventory() throws MovieNotFoundException {
		try {
			inventory.sellMovie(1, 5);
		} catch (NotEnoughCopiesException e) {
			fail(e.getMessage());
		}	
	}

	@Test
	public void testChangePrice() {
		try {
			inventory.addMovie("abc", 10.99, 3);
			inventory.changePrice(1, 1.99);
			assertEquals(1.99, inventory.findPrice(1, null), 0.001);
		} catch (MovieAlreadyExistsException | MovieNotFoundException e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected=MovieNotFoundException.class)
	public void testChangePriceOfMovieNotPresentInInventory() throws MovieNotFoundException {
		inventory.changePrice(1, 1.99);	
	}

	@Test
	public void testFindPrice() {
		try {
			inventory.addMovie("abc", 10.99, 3);
			assertEquals(10.99, inventory.findPrice(1, null), 0.001);
			inventory.addMovie("def", 2.00, 3);
			assertEquals(2, inventory.findPrice(2, null), 0.001);
		} catch (MovieAlreadyExistsException | MovieNotFoundException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testFindQuantity() {
		try {
			inventory.addMovie("abc", 10.99, 3);
			assertEquals(3, inventory.findQuantity(1, null));
			inventory.addMovie("def", 2.00, 8);
			assertEquals(8, inventory.findQuantity(2, null));
		} catch (MovieAlreadyExistsException | MovieNotFoundException e) {
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void testGetID() {
		try {
			inventory.addMovie("abc", 10.99, 3);
			assertEquals(1, inventory.getID("abc"));
			inventory.addMovie("def", 10.99, 3);
			assertEquals(2, inventory.getID("def"));
		} catch (MovieAlreadyExistsException | MovieNotFoundException e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected=MovieNotFoundException.class)
	public void testGetIDOfMovieNotPresentInInventory() throws MovieNotFoundException {
		inventory.getID("abc");	
	}
	
	@Test
	public void testSize() {
		try {
			assertEquals(0, inventory.size());
			inventory.addMovie("abc", 10.99, 3);
			assertEquals(1, inventory.size());
			inventory.addMovie("def", 10.99, 3);
			inventory.addMovie("ghi", 10.99, 3);
			assertEquals(3, inventory.size());
		} catch (MovieAlreadyExistsException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetMovieDetails() {
		try {
			inventory.addMovie("abc", 10.99, 3);
			String movieDetails = "ID: 1, Name: abc, Price: 10.99, Quantity: 3";
			assertEquals(movieDetails, inventory.getMovieDetails(1));
			
			inventory.addMovie("def", 10.99, 3);
			movieDetails = "ID: 2, Name: def, Price: 10.99, Quantity: 3";
			assertEquals(movieDetails, inventory.getMovieDetails(2));
		} catch (MovieAlreadyExistsException | MovieNotFoundException e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected=MovieNotFoundException.class)
	public void testGetMovieDetailsOfMovieNotPresentInInventory() throws MovieNotFoundException {
		inventory.getMovieDetails(1);
	}
}
