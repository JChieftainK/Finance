public class FileMod implements FileInterface{
	private Path aPath;
	
	public FileMod(){
		aPath = null;
	}
	
	/**	@param
	*	@return */
	public boolean checkIfExists(Path p){
		return Files.exists(p);
	}
	
	/**	@param 
	*	@return */
	public boolean createFolder(Path p){
		boolean success = false;
		try{
			if(!checkIfExists(p)){
				Files.createDirectories(p);
				success = true;
			}
		}catch(IOException e){
			success = false;
		}
		return success;
	}
}