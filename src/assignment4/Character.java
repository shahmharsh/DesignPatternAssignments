package assignment4;

/* The character class considers the Entire Multilingual Plane 
 * represented in Unicode Points ie from 0 to 0x10FFFF*/
public class Character {
	int unicodePoint;
	
	public Character(int character) {
		unicodePoint = character;
	}
	
	public int getValue()
	{
		return unicodePoint;
	}
	 
}