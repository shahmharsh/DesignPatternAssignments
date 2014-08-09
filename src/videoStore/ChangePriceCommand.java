package videoStore;

import org.json.JSONException;
import org.json.JSONObject;

class ChangePriceCommand implements Command {

	private DVDInventory moviesInventory;
	private int id;
	private double price;
	
	ChangePriceCommand(DVDInventory inventory, int id, double price) {
		this.moviesInventory = inventory;
		this.id = id;
		this.price = price;
	}
	
	@Override
	public void execute() throws MovieNotFoundException {
		moviesInventory.changePrice(id, price);		
	}
	
	/* returns JSON representation of command */
	@Override
	public String toString() {
		JSONObject JSONRepresentation = new JSONObject();
		try {
			JSONRepresentation.put("Command Name", "ChangePrice");
			JSONRepresentation.put("Movie ID", id);
			JSONRepresentation.put("Price", price);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return JSONRepresentation.toString();
	}
}
