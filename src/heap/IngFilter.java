package heap;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IngFilter implements Iterator<String> {
	
	private Iterator<String> ingIterator;
	private String current;
	private boolean usedFlag;
	
	public IngFilter(Iterator<String> input)
	{
		ingIterator = input;
		usedFlag = true;
	}
	
	@Override
	public boolean hasNext()
	{
		while(ingIterator.hasNext())
		{
			current = ingIterator.next();
			if(current.endsWith("ing"))
			{
				usedFlag = false;
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String next()
	{
		if(!usedFlag)
		{
			usedFlag = true;
			return current;
		}
		else if(hasNext())
		{
			usedFlag = true;
			return current;
		}
		else
		{
			throw new NoSuchElementException();
		}
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
}
