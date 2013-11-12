/**
*	@author Justin Kennedy
*	Interface for File Modification
*/
import java.nio.file.Path;

public interface FileInterface {
	/** Uses given string, directory, and saves it to
	*	the path data member
	*	@param thePath is the directory to set with */
	public void setPath(String thePath);
	
	/** Used to get the current path
	*	@return Path returned data member*/
	public Path getPath();
	
	/** Stores the path but forgets the last file name
	*	Uses temp to not change actual data member
	*	@return String is the string of the path*/
	public String getParentPath();
	
	/** This method is to find out how many lines
	*	are in a file. Returning -1 if it is an incorrect file
	*	@return int is the number of lines counted*/
	public int getLines();
	
	/**	Checks for existence of the folder and then
	*	creates the folder if it does not exists. Returns success
	*	@return boolean success determined if folder is created or not */
	public boolean createFolder();
	
	/**	Checks for existence of the file and if it does
	*	not exist then it creates a file. Returning success 
	*	or failure
	*	@return boolean used to tell if the file was created or not */
	public boolean createFile();
	
	/** Checks if the file is already there
	*	or if it is not.
	*	@return boolean telling if it is there or not*/
	public boolean checkIfExists();
	
	/**	@param fileName used to check if the file given is correct
	*	@return boolean to state if it is the same file name or not */
	public boolean fileCheck(String fileName);
	
	/**	Adds the given string to the end of the path
	*	@param fileName used to add to the end of the path*/
	public void add(String fileName);
	
	/** This will read the lines of a file until it finds the 
	*	line given and stores that then returns the String.
	*	null is used as a way to tell if anything went wrong.
	*	@param line is the index line to pick from file
	*	@return String the line that was chosen*/
	public String readLine(int line);
	
	/**	Appends to the paths file and adds given string
	*	returns failure or success 
	*	@param aTF the String that will be written to the file
	*	@return boolean deciding failure or success */
	public boolean appendToFile(String aTF);
	
	/**	Writes to the file but erases everything and places
	*	an empty string into it
	*	@return boolean deciding failure or success */
	public boolean clearFile();
	
	/** Called to create a string of the path and return it
	*	@return String the string of the path */
	public String toString();
}