package tests;

import java.awt.Font;

import org.junit.Test;

import assignment4.Character;
import assignment4.CharacterFactory;
import assignment4.FontFactory;
import assignment4.RunArray;
import assignment4.SizeofUtil;

public class SavedSpaceTest {
	@Test
	public void testFlyweightSpace() 
	{
		// Using flyweights to represent all information
		System.out.printf("The average size of document using flyweights is %.2f bytes%n", new SizeofUtil() {
			  Character[] obj = null;
			  RunArray fontInfo = null;
			  CharacterFactory charFactory = null;
			  FontFactory fontFactory = null;
			  
		      @Override
		      protected int create() {
		    	  charFactory = CharacterFactory.getInstance();
				  fontFactory = FontFactory.getInstance();
		    	  obj = new Character[326];
		    	  fontInfo = new RunArray();
		    	  
		    	  for(int i=1; i<=54; i++)
		    		  obj[i-1] = charFactory.getCharacter(i);
		
		    	  for(int i=55; i<=326; i++)
		    		  obj[i-1] = charFactory.getCharacter(0x01);
		    	
		    	  fontInfo.addRun(0, 114, fontFactory.getFont(Font.SANS_SERIF, Font.BOLD, 10));
		    	  fontInfo.addRun(115, 215, fontFactory.getFont(Font.SANS_SERIF, Font.PLAIN, 10));
		    	  
		    	  System.out.println("Number of Flyweight Characters: " + charFactory.getUniqueCharacterCount());
		    	  System.out.println("Number of Flyweight Fonts: " + fontFactory.getUniqueFontCount());
		    	  
		    	  return 1;
		      }
		    }.averageBytes());
	}
	
	@Test
	public void testNonFlyweightSpace()
	{
		// Without using flyweights, use arrays to represent all the information
		System.out.printf("The average size of document without using flyweights is %.2f bytes%n", new SizeofUtil() {
		      Character[] charArray = null;
		      Font[] fontArray = null;
		      
		      @Override
		      protected int create() {
		    	  charArray = new Character[326];
		    	  fontArray = new Font[326];
		    	  for(int i=1;i<=326;i++)
		    	  {
		    		  charArray[i-1] = new Character(i);
		    		  fontArray[i-1] = new Font(Font.SANS_SERIF, Font.BOLD, 10);
		    	  }
		    	 
		    	  return 1;
		      }
		    }.averageBytes());
	}
}