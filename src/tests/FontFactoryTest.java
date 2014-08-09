package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import java.awt.Font;

import assignment4.FontFactory;

public class FontFactoryTest {

	@Test
	public void testGetFont() {
		FontFactory fontFactoryObject = FontFactory.getInstance();
		Font testObj = new Font(Font.SANS_SERIF, Font.BOLD, 10);
		Font objA = fontFactoryObject.getFont(Font.SANS_SERIF, Font.BOLD, 10);
		assertEquals(testObj, objA);
		Font objB = fontFactoryObject.getFont(Font.SANS_SERIF, Font.BOLD, 10);
		assertSame(objA, objB);
	}
	
	@Test
	public void testGetUniqueFontCount() {
		FontFactory fontFactoryObject = FontFactory.getInstance();

		fontFactoryObject.getFont(Font.SANS_SERIF, Font.BOLD, 10);
		assertEquals(1, fontFactoryObject.getUniqueFontCount());
		fontFactoryObject.getFont(Font.SANS_SERIF, Font.BOLD, 10);
		assertEquals(1, fontFactoryObject.getUniqueFontCount());
		fontFactoryObject.getFont(Font.SANS_SERIF, Font.PLAIN, 10);
		assertEquals(2, fontFactoryObject.getUniqueFontCount());
	}

}
