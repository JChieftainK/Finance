import javax.swing.*;

public class TestFileMod {
	public static void main(String[] args) {
		FileInterface fileMan = new FileMod();
		JFileChooser chooser = new JFileChooser();
		
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int chooseValue = chooser.showOpenDialog(null);

		if(chooseValue == JFileChooser.APPROVE_OPTION){
			System.out.println("File: " + chooser.getSelectedFile());
			System.out.println("Absolute Path: " + chooser.getSelectedFile().getAbsolutePath());
			fileMan.setPath(chooser.getSelectedFile().getAbsolutePath()); //Set File path
			
			System.out.println(fileMan.toString());
			System.out.println(fileMan.checkIfExists());
			System.out.println("justin.txt == " + fileMan.fileCheck("justin.txt"));
			System.out.println("Justin.txt == " + fileMan.fileCheck("Justin.txt"));
			System.out.println(fileMan.toString());
			
			fileMan.add("MiFinance");
			System.out.println("Added 'MiFinance'");
			System.out.println(fileMan.toString());
			
			System.out.println("Folder created: " + fileMan.createFolder());
			
			fileMan.add("FinanceSettings.txt");
			System.out.println("Added 'FinanceSettings.txt'");
			System.out.println(fileMan.toString());
			
			System.out.println("File created: " + fileMan.createFile());
			
			System.out.println("File Appended to: " + fileMan.appendToFile("Hello:World:!"));
			
			System.out.println("Test");
			FileInterface fileTest = new FileMod(fileMan.getParentPath(), "Accounting.txt");
			System.out.println(fileTest.toString());
			
		}
		
		
		
		/*int openValue = chooser.showOpenDialog(null);
		if(openValue == JFileChooser.APPROVE_OPTION){
			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
			settingsFile.setPath(chooser.getSelectedFile().getAbsolutePath()); //Set Settings File Path
			if(settingsFile.fileCheck("FinanceSettings.txt")){
				for(int i = 0; i < settingsFile.getLines(); i++){
					linkedFileBag.add(new FileMod(settingsFile.getParentPath(), settingsFile.readLine(i)));
				}
			}
		}*/
	}
}