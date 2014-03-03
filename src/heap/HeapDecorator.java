package heap;

import java.util.Iterator;

public abstract class HeapDecorator {
	protected Heap decoratedHeap;
	
	public boolean add(String value) {
		return decoratedHeap.add(value);
	}
	
	public boolean isEmpty() {
		return decoratedHeap.isEmpty();
	}

	public String peek() {
		return decoratedHeap.peek();
	}

	public int size() {
		return decoratedHeap.size();
	}
	
	public abstract String toString();
	public abstract Iterator<String> iterator();
	public abstract String[] toArray();
}