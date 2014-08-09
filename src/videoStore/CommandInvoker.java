package videoStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class CommandInvoker {
	
	private static final String filePath = "commands.ser";
	private static final String temporaryFilePath = "temporary_commands.ser";
	private JSONArray allCommands; // command stack
	
	/* creates the files if they dont already exists */
	CommandInvoker() {
		allCommands = new JSONArray();
	}

	
	void execute(Command currentCommand) throws MovieNotFoundException, NotEnoughCopiesException, MovieAlreadyExistsException
	{
		allCommands.put(currentCommand.toString());
		currentCommand.execute();
		FileOperationsHandler.pushToFile(allCommands.toString(), filePath, temporaryFilePath);
	}
	
	private JSONArray getCommandsFromFile()
	{
	    String JSONStringFromFile = (String) FileOperationsHandler.getFromFile(filePath);;
	    JSONArray JSONArrayFromFile = null;
	   		
		try {
			JSONArrayFromFile = new JSONArray(JSONStringFromFile);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return JSONArrayFromFile;
	}
	
	void restoreInventory(Inventory inventory)
	{
		JSONArray commandsToOperate = getCommandsFromFile();
		
		for(int i=0; i<commandsToOperate.length(); i++)
		{
			try {
				String commandJSONString = commandsToOperate.getString(i);
				JSONObject commandJSONObject = new JSONObject(commandJSONString);
				String commandType = commandJSONObject.getString("Command Name");
				
				int movieID, quantity, price;
				String movieName;
				
				switch(commandType) 
				{
					case "AddCopies":
						movieID = commandJSONObject.getInt("Movie ID");
						quantity = commandJSONObject.getInt("Quantity");
						inventory.addCopies(movieID, quantity);
						break;
						
					case "AddMovie":
						movieName = commandJSONObject.getString("Movie Name");
						price = commandJSONObject.getInt("Price");
						quantity = commandJSONObject.getInt("Quantity");
						inventory.addMovie(movieName, price, quantity);
						break;
						
					case "ChangePrice":
						movieID = commandJSONObject.getInt("Movie ID");
						price = commandJSONObject.getInt("Price");
						inventory.changePrice(movieID, price);
						break;
						
					case "SellMovie":
						movieID = commandJSONObject.getInt("Movie ID");
						quantity = commandJSONObject.getInt("Quantity");
						inventory.sellMovie(movieID, quantity);
						break;
						
				}
			} catch (JSONException e) {
				e.printStackTrace();
			} catch ( MovieNotFoundException | MovieAlreadyExistsException | NotEnoughCopiesException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* clears current command stack */
	void clear()
	{
		allCommands = null;
		allCommands = new JSONArray();
		FileOperationsHandler.pushToFile(allCommands.toString(), filePath, temporaryFilePath);
	}
}
