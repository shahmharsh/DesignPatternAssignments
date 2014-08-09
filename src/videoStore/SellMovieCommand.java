package videoStore;

import org.json.JSONException;
import org.json.JSONObject;

class SellMovieCommand implements Command {

	private DVDInventory moviesInventory;
	private int id;
	private int quantity;
	
	SellMovieCommand(DVDInventory inventory, int id, int quantity) {
		this.moviesInventory = inventory;
		this.id = id;
		this.quantity = quantity;
	}
	
	@Override
	public void execute() throws NotEnoughCopiesException, MovieNotFoundException {
		moviesInventory.sellMovie(id, quantity);		
	}
	
	/* returns JSON representation of command */
	@Override
	public String toString() {
		JSONObject JSONRepresentation = new JSONObject();
		try {
			JSONRepresentation.put("Command Name", "SellMovie");
			JSONRepresentation.put("Movie ID", id);
			JSONRepresentation.put("Quantity", quantity);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return JSONRepresentation.toString();
	}

}
