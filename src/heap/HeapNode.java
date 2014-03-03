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

}
