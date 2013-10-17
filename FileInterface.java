import java.nio.file.Path;

public interface FileInterface {
	public void setPath(String thePath);
	
	public Path getPath();
	public String getParentPath();
	public int getLines();
	
	public boolean createFolder();
	public boolean createFile();
	public boolean checkIfExists();
	public boolean fileCheck(String fileName);
	
	public String readLine(int line);
	
	public String toString();
}