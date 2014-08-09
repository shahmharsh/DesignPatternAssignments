package videoStore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.FileChannel;

class FileOperationsHandler {
	
	private static void createIfNotFound(String filePath, String temporaryFilePath)
	{
		File storageFile = new File(filePath);
		File temporaryFile = new File(temporaryFilePath);
		try {
			
			if(!storageFile.exists()) 		    
		    	storageFile.createNewFile();
			
			if(!temporaryFile.exists())
				temporaryFile.createNewFile();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* Reference: http://stackoverflow.com/questions/106770/standard-concise-way-to-copy-a-file-in-java/115086#115086 */
	private static void copyToPermanentStorage(String destinationFile, String sourceFile) 
	{
	    FileChannel source = null;
	    FileChannel destination = null;

	    try {
	        source = new FileInputStream(sourceFile).getChannel();
	        destination = new FileOutputStream(destinationFile).getChannel();
	        destination.transferFrom(source, 0, source.size());
	    } catch (IOException e) {
			e.printStackTrace();
		}
	    finally {
	    	try{
	    		if(source != null)
	    			source.close();
	    		if(destination != null)       
	    			destination.close();
	    	}catch(IOException e) {
				e.printStackTrace();
			}
	    }
	}
	
	/* Writes content to temporary file first and then copies the temporary file content to main file */
	static void pushToFile(Object content, String filePath, String temporaryFilePath)
	{
		// creates the files if they dont exist
		createIfNotFound(filePath, temporaryFilePath);
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
				
		try {		
			fos = new FileOutputStream(temporaryFilePath);
			out = new ObjectOutputStream(fos);
			out.writeObject(content);
			out.close();
			fos.close();
			// copy to main file
			copyToPermanentStorage(filePath, temporaryFilePath);
			
		} catch (IOException e) {
			e.printStackTrace();		
		} finally {
			try {
				if(fos!=null)
					fos.close();
				if(out!=null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* gets contents from file specified by filePath */ 
	static Object getFromFile(String filePath) 
	{
		FileInputStream fis = null;
		ObjectInputStream in = null;
		Object fileContent = null;
		try {
			fis = new FileInputStream(filePath);
			in = new ObjectInputStream(fis);
			fileContent = (Object) in.readObject();
	    } catch (IOException e) {
	      e.printStackTrace();
	    } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(fis!=null)
					fis.close();
				if(in!=null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return fileContent;
	}
}