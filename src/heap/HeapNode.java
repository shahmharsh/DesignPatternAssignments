package heap;

public abstract class HeapNode {
	
	protected String value;
	protected HeapNode left;
	protected HeapNode right;
	
	public abstract int height();
	public abstract int size();
	public abstract boolean hasLeft();
	public abstract boolean hasRight();
	public abstract boolean isNull();
	
	/*public int height()
	{
		int leftHeight = 0;
		int rightHeight = 0;
		
		if(left != null)
		  leftHeight = left.height(); // recursive call to get height of left child
		if(right != null)
    	  rightHeight = right.height(); // recursive call to get height of right child
    	
    	// current node height is one more than height of its child having greater height.
    	int height = (leftHeight > rightHeight) ? (1 + leftHeight) : (1 + rightHeight);
    	
        return height; 
	}
	
	public int size()
	{
		int leftSize = 0;
		int rightSize = 0;
		
		if(left != null)
			leftSize = left.size();
		if(right != null)
			rightSize = right.size();
		
		return (leftSize + rightSize + 1);
	}
	
	public boolean hasRight()
	{
		return (right!=null);
	}
	
	public boolean hasLeft()
	{
		return (left!=null);
	}
	
	public boolean isNull()
	{
		return false;
		
	}*/
}
