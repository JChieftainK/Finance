import java.nio.file.*;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class FileMod implements FileInterface{
	private Path aPath;
	
	public FileMod(){ //Default constructor
		aPath = null; //Sets initial path to null
	}
	
	/** Gets the directory path and the file name then
	*	combines the information and sets it to the path
	*	@param pathForFile is the directory
	*	@param fileName gets the file Name itself */
	public FileMod(String pathForFile, String fileName){ //Constructor
		aPath = Paths.get(pathForFile, fileName); //use Path.get to set both with correct slash
	}
	
	/** Uses given string, directory, and saves it to
	*	the path data member
	*	@param thePath is the directory to set with */
	public void setPath(String thePath){
		aPath = Paths.get(thePath); //sets the path data member
	}
	
	/** Used to get the current path
	*	@return Path returned data member*/
	public Path getPath(){
		return aPath; //returns the path data member
	}
	
	/** Stores the path but forgets the last file name
	*	Uses temp to not change actual data member
	*	@return String is the string of the path*/
	public String getParentPath(){
		Path temp = aPath.getParent(); //Stores parent path in temporary Path
		return temp.toString(); //returns the temp
	}
	
	/**	Appends to the paths file and adds given string
	*	returns failure or success 
	*	@param aTF the String that will be written to the file
	*	@return boolean deciding failure or success */
	public boolean appendToFile(String aTF){
		boolean success = false; //Assume failure
		try { //try due to exception thrown
			if(checkIfExists()){ //check the file first
				BufferedWriter bufferWrite = null; //Create a buffer variable with null. Will be used later
				bufferWrite = new BufferedWriter(new FileWriter(aPath.toString(), true)); //Gives path, and boolean for appending
				bufferWrite.write(aTF); //writes to the file that was given
				bufferWrite.newLine(); //Inserts a new line in the file
				bufferWrite.close(); //close the file so that the file is written to
				success = true; //change result to a successful result
			}
		} catch (IOException e) {
			success = false; //fail due to error
		}
		return success; //return failure or success
	}
	
	public int getLines(){
		int nrlines = 0;
		if(checkIfExists()){
			try{
				BufferedReader bufferRead = null;
				bufferRead = new BufferedReader(new FileReader(aPath.toString()));
			
				while(bufferRead.readLine() != null){
					nrlines += 1;
				}
				bufferRead.close();
			}catch(IOException e){
				nrlines = -1;
			}
		}
		return nrlines;
	}
	
	public String readLine(int line){
		String result = null;
		try{
			BufferedReader buffRead = null;
			buffRead = new BufferedReader(new FileReader(aPath.toString()));
			String temp;
			for(int i = 0; (temp = buffRead.readLine()) != null; i++){
				if(i == line){
					result = temp;
					break;
				}
			}
			buffRead.close();
		}catch(IOException e){
			result = null;
		}
		return result;
	}
	
	/** Checks if the file is already there
	*	or if it is not.
	*	@return boolean telling if it is there or not*/
	public boolean checkIfExists(){
		return Files.exists(aPath); //checks for existence and returns boolean
	}
	
	/**	@param fileName used to check if the file given is correct
	*	@return boolean to state if it is the same file name or not */
	public boolean fileCheck(String fileName){
		return (aPath.getFileName().toString()).equals(fileName); //Returns boolean after comparing
	}
	
	/**	Adds the given string to the end of the path
	*	@param fileName used to add to the end of the path*/
	public void add(String fileName){
		aPath = Paths.get(aPath.toString(), fileName); //Used Paths.get to get correct slash
	}
	
	/**	Checks for existence of the folder and then
	*	creates the folder if it does not exists. Returns success
	*	@return boolean success determined if folder is created or not */
	public boolean createFolder(){
		boolean success = false; //Expect success to fail unless otherwise
		try{ //Need to try due to an exception
			if(!checkIfExists()){ //Makes sure the path does not already exist
				Files.createDirectories(aPath); //creates the folders with the path
				success = true; //sets success to true to return
			} //end try
		}catch(IOException e){ //catch in case an exception is called
			success = false; //sets success to false
		}
		return success; //returns success
	}
	
	/**	Checks for existence of the file and if it does
	*	not exist then it creates a file. Returning success 
	*	or failure
	*	@return boolean used to tell if the file was created or not */
	public boolean createFile(){
		boolean success = false; //assume it will fail
		try { //try due to IOexception
			if(!checkIfExists()){ //Make sure the path does not already exist
				Files.createFile(aPath); //create the path (file)
				success = true; //change success to true
			}
		}catch (IOException e) { //exception thrown 
			success = false; //success has failed so false
		}
		return success; //return success or failure
	}
	
	/** Called to create a string of the path and return it
	*	@return String the string of the path */
	public String toString(){
		return aPath.toString();
	}
}