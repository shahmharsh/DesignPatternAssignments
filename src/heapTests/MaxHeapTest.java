package heapTests;

import static org.junit.Assert.*;
import heap.Heap;
import heap.MaxHeap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MaxHeapTest {

	Heap heap;

	@Before
	public void setUp() throws Exception {
		heap = new Heap(new MaxHeap());
	}

	@After
	public void tearDown() throws Exception {
		heap = null;
	}
	
	@Test
	public void testPeek() {
		heap.add("abc");
		heap.add("def");
		heap.add("ghi");
		
		assertEquals("ghi", heap.peek());
	}
	
	@Test
	public void testSize() {
		heap.add("abc");
		heap.add("def");
		heap.add("ghi");
		
		assertEquals(heap.size(), 3);
	}

	@Test
	public void testAdd(){
		assertTrue(heap.add("def"));
		assertTrue(heap.add("ghi"));
		assertTrue(heap.add("abd"));
		assertTrue(heap.add("deh"));
		assertTrue(heap.add("ghi"));
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddNull() {
		heap.add(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddBlank() {
		heap.add("");
		heap.add("    ");
	}

	@Test
	public void testIterator() {
		heap.add("abc");
		heap.add("def");
		heap.add("ghi");
		heap.add("aaa");
		heap.add("bbc");
		heap.add("xyz");
		
		fail("not yet implemented");
	}

	@Test
	public void testToString() {
		heap.add("abc");
		heap.add("def");
		heap.add("ghi");
				
		String actualHeapString = heap.toString();
		String expectedHeapString = "[abc, ghi, def]";
		assertEquals(expectedHeapString, actualHeapString);
	}

	@Test
	public void testToArray() {
		heap.add("abc");
		heap.add("def");
		heap.add("ghi");
				
		String [] actualHeapArray = heap.toArray();
		String [] expectedHeapArray = {"abc","ghi", "def"};
		assertArrayEquals(expectedHeapArray, actualHeapArray);
	}

}
