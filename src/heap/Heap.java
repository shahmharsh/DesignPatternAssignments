package heap;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class Heap extends AbstractCollection<String> {
	
	private HeapNode root;
	private Algorithm minMax;
	
	public Heap(Algorithm minMax)
	{
		root=null;
		this.minMax = minMax;
	}
	
	public boolean add(String value) 
	{
		if(value == null) // makes sure null values are not allowed
		{
			return false;
		}
		
		value = value.trim(); // removes leading and trailing whitespace
		if(value.length() == 0) // makes sure blank or white spaces are not allowed
		{
			return false;
		}
		
		if(root == null)
		{
			root = new HeapRealNode(value);
			return true;
		}
		
		return minMax.add(root, value);
	}

	@Override
	public boolean isEmpty() {
		
		return (root == null);
	}

	public String peek()
	{
		return root.value;
	}
	
	@Override
	public Iterator<String> iterator() 
	{
		return new HeapIterator();
	}
	
	@Override
	public int size() 
	{
		return root.size();
	}
	
	@Override
	public String toString() 
	{
		ArrayList<String> heapArrayList = new ArrayList<String>();
		
		Iterator<String> heapIterator = iterator(); 
		while(heapIterator.hasNext())
		{
			heapArrayList.add(heapIterator.next());
		}
		
		return heapArrayList.toString();
	}

	@Override
	public String[] toArray() 
	{
		ArrayList<String> heapArrayList = new ArrayList<String>();
		
		Iterator<String> heapIterator = iterator(); 
		while(heapIterator.hasNext())
		{
			heapArrayList.add(heapIterator.next());
		}

		String [] heapArray = new String[heapArrayList.size()];
		return heapArrayList.toArray(heapArray);
	}
	
	private class HeapIterator implements Iterator<String>
	{

		private Stack<HeapNode> stack = new Stack<HeapNode>(); 
		
		public HeapIterator()
		{
			HeapNode iteratingNode = root;

			while(iteratingNode.hasLeft())
			{	
				stack.push(iteratingNode);
				iteratingNode = iteratingNode.left; 
			}
			
			stack.push(iteratingNode);
		}
		
		@Override
		public boolean hasNext() 
		{
			return (!stack.empty());
		}

		@Override
		public String next() 
		{
			if(stack.empty())
			{
				throw new NoSuchElementException();
			}
			else
			{
				HeapNode temporaryNode = stack.pop();
				if(temporaryNode.hasRight())
				{
					stack.push(temporaryNode.right);
					HeapNode iteratingNode = temporaryNode.right;
					while(iteratingNode.hasLeft())
					{	
						stack.push(iteratingNode);
						iteratingNode = iteratingNode.left; 
					}	
				}
				
				return temporaryNode.value;
			}
			
		}

		@Override
		public void remove() 
		{
			throw new UnsupportedOperationException();
		}
	}
	
	public static void main(String args[])
	{
		Heap heap;
		heap = new Heap(new MinHeap());
		//System.out.println(heap.isEmpty());
		heap.add("abcing");
		heap.add("arwbbcing");
		heap.add("abwrcing");
		heap.add("a3vfeing");
		//System.out.println(heap.size());
		
		/*Iterator<String> heapIterator = heap.iterator();
		
		while(heapIterator.hasNext())
			System.out.println(heapIterator.next());
		
		System.out.println(heap.toString());
		String[] array = heap.toArray();
		
		for(int i=0;i<array.length; i++)
			System.out.println(array[i]);*/
		
		/*
		IngFilter ingFilterObject = new IngFilter(heapIterator);
		
		while(ingFilterObject.hasNext())
			System.out.println(ingFilterObject.next());*/
	}	
}
