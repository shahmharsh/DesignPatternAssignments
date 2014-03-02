package heap;

public class HeapRealNode extends HeapNode{

	public HeapRealNode(String value) 
	{
		this.value = value;
		left = new HeapNullNode();
		right = new HeapNullNode();
	}

	@Override
	public int height()
	{
		int leftHeight = left.height(); // recursive call to get height of left child
		int rightHeight = right.height(); // recursive call to get height of right child
    	
    	// current node height is one more than height of its child having greater height.
    	int height = (leftHeight > rightHeight) ? (1 + leftHeight) : (1 + rightHeight);
    	
        return height; 
	}
	
	@Override
	public int size()
	{
		int leftSize = left.size();
		int rightSize = right.size();
		
		return (leftSize + rightSize + 1);
	}
	
	@Override
	public boolean hasRight()
	{
		return !(right.isNull());
	}
	
	@Override
	public boolean hasLeft()
	{
		return !(left.isNull());
	}
	
	@Override
	public boolean isNull()
	{
		return false;
	}
	
	public void addLeft(String value)
	{
		left = null;
		left = new HeapRealNode(value);
	}
	
	public void addRight(String value)
	{
		right = null;
		right = new HeapRealNode(value);
	}
}
