package videoStore;

import java.io.Serializable;

class Movie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	double price;
	int ID;
	int quantity;
	
	public Movie(int currentID, String name, double price, int quantity)
	{
		this.ID = currentID; 
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	void addCopies(int quantity)
	{
		this.quantity += quantity;
	}
	
	void changePrice(double newPrice)
	{
		this.price = newPrice;
	}
	
	void sell(int quantity) throws NotEnoughCopiesException
	{
		if(quantity > this.quantity)
			throw new NotEnoughCopiesException("Not Enough Copies for the Movie");
		
		this.quantity = this.quantity - quantity;
	}
}
