public interface FileInterface {
	public void setPath(String thePath);
	
	public String getPath();
	public String getParentPath();
	public int getLines();
	
	public String readLine(int line);
	public boolean fileCheck(String fileName);
	public String toString();
	public boolean createFolder();
	public boolean createFile();
}