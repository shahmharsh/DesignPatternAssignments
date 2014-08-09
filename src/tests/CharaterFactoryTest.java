package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import assignment4.Character;
import assignment4.CharacterFactory;

public class CharaterFactoryTest {

	@Test
	public void testGetCharacter() {
		CharacterFactory charFactoryObject = CharacterFactory.getInstance();
		Character objA = charFactoryObject.getCharacter('a');
		assertEquals('a', objA.getValue());
		Character objB = charFactoryObject.getCharacter('a');
		assertSame(objA, objB);
	}
	
	@Test
	public void testGetUniqueCharacterCount() {
		CharacterFactory charFactoryObject = CharacterFactory.getInstance();
		
		assertEquals(0, charFactoryObject.getUniqueCharacterCount());
		charFactoryObject.getCharacter('a');
		assertEquals(1, charFactoryObject.getUniqueCharacterCount());
		charFactoryObject.getCharacter('a');
		assertEquals(1, charFactoryObject.getUniqueCharacterCount());
		charFactoryObject.getCharacter('b');
		assertEquals(2, charFactoryObject.getUniqueCharacterCount());
	}

}
