package heap;

public class MinHeap implements Algorithm {

	@Override
	public boolean add(HeapNode root, String value) 
	{
		int leftHeight=0;
		int rightHeight=0;
			
		HeapRealNode iteratingNode = (HeapRealNode) root;  
			
		do 
		{
			// swap value of current node in heap if new value is smaller.
			if(iteratingNode.value.compareTo(value) >  0) 
			{
				// swap
				String swapValue = value;
				value = iteratingNode.value;
				iteratingNode.value = swapValue;
			}
			
			leftHeight = iteratingNode.left.height(); 
			rightHeight = iteratingNode.right.height();
			
			// insert into right sub heap if its height is less than left sub heap, second condition ensures that right sub heap exists.
			if(leftHeight > rightHeight && rightHeight != 0 )  
			{																	   
				iteratingNode = (HeapRealNode) iteratingNode.right;
			}
			// insert into left sub heap if its height is less than or equal to right sub heap, second condition here ensures that left sub heap exists.
			else if(leftHeight <= rightHeight && leftHeight != 0) 
			{																		   
				iteratingNode = (HeapRealNode) iteratingNode.left;
			}
			
			// continue until we find our empty postion to insert new node. empty position is found when either left sub heap height or right sub heap height is 0.
		}while(leftHeight != 0 && rightHeight != 0); 
			
		//HeapNode newHeapNode = new HeapNode(value, iteratingNode);
		// Now we have the value to be inserted in valueToInsertIntoHeap and position in iteratingNode.
		if(leftHeight == 0) // add new node to left child.
		{
			//iteratingNode.left = newHeapNode;
			iteratingNode.addLeft(value);
		}
		else if(rightHeight == 0) // add node to right child since left child is already present.
		{
			//iteratingNode.right = newHeapNode;
			iteratingNode.addRight(value);
		}
		
		return true;
	}
}
	
