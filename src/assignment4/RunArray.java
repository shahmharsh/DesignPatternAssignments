package assignment4;

import java.awt.Font;
import java.util.ArrayList;


/* Class to hold the font information of the document.
 * The programmer will have to use the  'clear()' method and build 
 * the run array again if he wants to modify the existing runs i.e. 
 * for example if the run array currently hold runs 0-10 as FontA 
 * and 11-20 as FontB, to make 8-12 as FontC, the programmer 
 * will have to first call the clear() method and then add 0-7 as 
 * FontA, 8-12 as FontB and 13-20 as FontC. */
public class RunArray {
	
	private ArrayList<RunInfo> runArray;

	private class RunInfo {
		private int startIndex;
		private int length;
		private Font fontValue;
		
		private RunInfo(int startIndex, int length, Font value)
		{
			this.startIndex = startIndex;
			this.length = length;
			this.fontValue = value;
		}
	}
	
	public RunArray() 
	{
		runArray = new ArrayList<RunInfo>();
	}
	
	/* adds the run specified by the parameters to the array and always returns true */
	public boolean addRun(int startIndex, int length, Font font)
	{
		RunInfo run = new RunInfo(startIndex, length, font);
		runArray.add(run);
		return true;
	}
	
	/* adds the font information to the end of the document and always returns true */
	public boolean appendRun(int length, Font font)
	{
		int startIndex = 0;
		
		if(runArray.size()!=0)
		{
			RunInfo lastRun = runArray.get(runArray.size() - 1);
			startIndex = lastRun.startIndex + length + 1;
		}
		
		RunInfo run = new RunInfo(startIndex, length, font);
		runArray.add(run);
		
		return true;
	}
	
	/* returns the font of the specified index, null if the index is not found*/
	public Font getFont(int index)
	{
		for(int i=0; i<runArray.size(); i++)
		{
			RunInfo currrentRun = runArray.get(i);
			int start = currrentRun.startIndex;
			int end = start + currrentRun.length;
			if(index >= start && index <= end)
			{
				return currrentRun.fontValue;
			}
		}
		return null;
	}
	
	/* clears run rray*/
	public void clear()
	{
		runArray.clear();
	}
}
