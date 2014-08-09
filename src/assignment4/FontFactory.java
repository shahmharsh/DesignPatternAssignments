package assignment4;

import java.awt.Font;
import java.util.ArrayList;

/* Flyweight factory class for font, uses singleton pattern for single point global access */
public class FontFactory {
	private static ArrayList<Font> fontPool = new ArrayList<Font>();
	
	private FontFactory() {	}
	
	private static class FontFactoryHolder {
		 private final static FontFactory INSTANCE = new FontFactory();
	}
	
	public static FontFactory getInstance() 
	{
		return FontFactoryHolder.INSTANCE;
	}
	
	/* Checks if the font is currently present in the pool and returns it if present 
	 * else creates new font, adds it to pool and returns it */
	public Font getFont(String name, int style, int size)
	{
		for(int i=0; i < fontPool.size(); i++)
		{
			Font currentFont = fontPool.get(i);
			String currentFontName = currentFont.getFamily();
			int currentFontSize = currentFont.getSize();
			int currentFontStyle = currentFont.getStyle();
			
			if(currentFontName.equals(name) && currentFontStyle == style && currentFontSize == size)
				return currentFont;
		}
		
		Font newFont = new Font(name, style, size);
		fontPool.add(newFont);
		return newFont;
	}
	
	public int getUniqueFontCount() {
		return fontPool.size();
	}
}
