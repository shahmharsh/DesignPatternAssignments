package heapTests;

import static org.junit.Assert.*;

import java.util.Iterator;

import heap.Heap;
import heap.IngHeapDecorator;
import heap.MaxHeap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IngHeapDecoratorTest {

	IngHeapDecorator decoratedHeap;
	
	@Before
	public void setUp() throws Exception {
		decoratedHeap = new IngHeapDecorator(new Heap(new MaxHeap()));
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testSize() {
		decoratedHeap.add("abc");
		assertEquals(1, decoratedHeap.size());
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testIterator() {
		decoratedHeap.add("abc");
		decoratedHeap.add("abcing");
		
		Iterator<String> heapIterator = decoratedHeap.iterator();
		
		System.out.println(heapIterator.next());
	}

	@Test
	public void testToArray() {
		fail("Not yet implemented");
	}

	
}
