package assignment4;

import java.util.ArrayList;

/* Flyweight factory class for character, uses singleton pattern for single point global access */
public class CharacterFactory {
	
	private static ArrayList<Character> characterPool = new ArrayList<Character>();
	
	private CharacterFactory() { }
	
	private static class CharacterFactoryHolder {
		 private final static CharacterFactory INSTANCE = new CharacterFactory();
	}
	
	public static CharacterFactory getInstance() 
	{
		return CharacterFactoryHolder.INSTANCE;
	}
	
	/* Checks if the char is currently present in the pool and returns it if present 
	 * else creates new character, adds it to pool and returns it 
	 * Input is the unicode of the character*/
	public Character getCharacter(int unicode)
	{
		for(int i=0; i < characterPool.size(); i++)
		{
			Character currentCharacter = characterPool.get(i);
			if(currentCharacter.unicodePoint == unicode)
				return currentCharacter;
		}
		
		Character newCharacter = new Character(unicode);
		characterPool.add(newCharacter);
		return newCharacter;
	}
	
	public int getUniqueCharacterCount() {
		return characterPool.size();
	}
}
