package heapTests;
import static org.junit.Assert.*;
import heap.Heap;
import heap.MaxHeap;
import heap.MinHeap;

import org.junit.Test;


public class HeapTest {

	Heap heap;

	@Test
	public void testPeek() {
		heap = new Heap(new MaxHeap());
		heap.add("abc");
		assertEquals("abc", heap.peek());
	}
	
	@Test
	public void testSize() {
		heap = new Heap(new MinHeap());
		heap.add("abc");
		heap.add("arwbbc");
		heap.add("abwrc");
		
		assertEquals(heap.size(), 3);
	}

	@Test
	public void testMaxHeapAddString() {
		heap = new Heap(new MaxHeap());
		assertTrue(heap.add("abc"));
	}

	@Test
	public void testIterator() {
		heap = new Heap(new MinHeap());
		heap.add("abc");
		heap.add("arwbbc");
		heap.add("abwrc");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testToArray() {
		fail("Not yet implemented");
	}

}
