package videoStore;

import java.io.Serializable;

public class Memento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Inventory state;
	
	public Memento(Inventory state)
	{
		this.state = state;
	}

	public Inventory getState() 
	{
		return state;
	}	
}
