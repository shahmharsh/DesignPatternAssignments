package heap;

import java.util.ArrayList;
import java.util.Iterator;

public class IngHeapDecorator extends HeapDecorator {
	
	public IngHeapDecorator(Heap originalHeap) {
		this.decoratedHeap = originalHeap;
	}

	@Override
	public String toString() {
		ArrayList<String> heapArrayList = new ArrayList<String>();
		
		Iterator<String> heapIterator = iterator(); 
		while(heapIterator.hasNext())
		{
			heapArrayList.add(heapIterator.next());
		}
		
		return heapArrayList.toString();
	}

	@Override
	public Iterator<String> iterator() {
		return (new IngFilter(decoratedHeap.iterator()));
	}

	@Override
	public String[] toArray() {
		ArrayList<String> heapArrayList = new ArrayList<String>();
		
		Iterator<String> heapIterator = iterator(); 
		while(heapIterator.hasNext())
		{
			heapArrayList.add(heapIterator.next());
		}

		String [] heapArray = new String[heapArrayList.size()];
		return heapArrayList.toArray(heapArray);
	}
}
