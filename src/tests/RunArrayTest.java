package tests;

import static org.junit.Assert.*;

import java.awt.Font;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import assignment4.RunArray;

public class RunArrayTest {

	RunArray runArray;
	@Before
	public void setUp() throws Exception {
		runArray = new RunArray();
	}

	@After
	public void tearDown() throws Exception {
		runArray = null;
	}

	@Test
	public void testAddRun() {
		assertTrue(runArray.addRun(1, 10, new Font(Font.SANS_SERIF, Font.BOLD, 10)));
		assertTrue(runArray.addRun(11, 10, new Font(Font.SANS_SERIF, Font.BOLD, 12)));
	}
	
	@Test
	public void testAppendRun() {
		assertTrue(runArray.appendRun(10, new Font(Font.SANS_SERIF, Font.BOLD, 10)));
		assertTrue(runArray.appendRun(5, new Font(Font.SANS_SERIF, Font.BOLD, 12)));
	}

	@Test
	public void testGetFont() {
		Font fontA = new Font(Font.SANS_SERIF, Font.BOLD, 10);
		runArray.addRun(0, 10, fontA);
		assertEquals(fontA, runArray.getFont(3));
		
		Font fontB = new Font(Font.SANS_SERIF, Font.BOLD, 10);
		runArray.addRun(11, 10, fontB);
		assertEquals(fontB, runArray.getFont(21));
	}
	
	@Test
	public void testGetFontWhenIndexNotPresent() {
		Font fontA = new Font(Font.SANS_SERIF, Font.BOLD, 10);
		runArray.addRun(0, 10, fontA);
		assertEquals(null, runArray.getFont(11));

	}
}
