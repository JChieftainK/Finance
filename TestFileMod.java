import javax.swing.*;

public class TestFileMod {
	public static void main(String[] args) {
		FileInterface fileMan = new FileMod();
		JFileChooser chooser = new JFileChooser();
		
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int chooseValue = chooser.showSaveDialog(null);

		if(chooseValue == JFileChooser.APPROVE_OPTION){
			System.out.println("File: " + chooser.getSelectedFile());
			System.out.println("Absolute Path: " + chooser.getSelectedFile().getAbsolutePath());
			fileMan.setPath(chooser.getSelectedFile().getAbsolutePath()); //Set File path
			System.out.println(fileMan.toString());
			System.out.println(fileMan.checkIfExists());
			System.out.println("justin.txt == " + fileMan.fileCheck("justin.txt"));
			System.out.println("Justin.txt == " + fileMan.fileCheck("Justin.txt"));
			System.out.println(fileMan.toString());
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