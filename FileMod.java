import java.nio.file.*;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class FileMod implements FileInterface{
	private Path aPath;
	
	public FileMod(){
		aPath = null;
	}
	
	public FileMod(String pathForFile, String fileName){
		pathForFile += fileName;
		aPath = Paths.get(pathForFile);
	}
	
	public void setPath(String thePath){
		aPath = Paths.get(thePath);
	}
	
	public Path getPath(){
		return aPath;
	}
	
	public String getParentPath(){
		Path temp = aPath.getParent();
		return temp.toString();
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
	
	/**	@return */
	public boolean checkIfExists(){
		return Files.exists(aPath);
	}
	
	public boolean fileCheck(String fileName){
		return (aPath.getFileName().toString()).equals(fileName);
	}
	
	/**	@param 
	*	@return */
	public boolean createFolder(){
		boolean success = false;
		try{
			if(!checkIfExists()){
				Files.createDirectories(aPath);
				success = true;
			}
		}catch(IOException e){
			success = false;
		}
		return success;
	}
	
	public boolean createFile(){
		return false;
	}
	
	public String toString(){
		return aPath.toString();
	}
}