package videoStore;

import org.json.JSONException;
import org.json.JSONObject;

class AddMovieCommand implements Command {

	private DVDInventory moviesInventory;
	private String name;
	private double price;
	private int quantity;
	
	AddMovieCommand(DVDInventory inventory, String name, double price, int quantity)
	{
		this.moviesInventory = inventory;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	@Override
	public void execute() throws MovieAlreadyExistsException {
		moviesInventory.addMovie(name, price, quantity);
	}
	
	/* returns JSON representation of command */
	@Override
	public String toString() {
		JSONObject JSONRepresentation = new JSONObject();
		try {
			JSONRepresentation.put("Command Name", "AddMovie");
			JSONRepresentation.put("Movie Name", name);
			JSONRepresentation.put("Price", price);
			JSONRepresentation.put("Quantity", quantity);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return JSONRepresentation.toString();
	}
}
