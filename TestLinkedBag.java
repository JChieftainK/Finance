import javax.swing.*;
class TestLinkedBag {
	public static void main(String[] args) {
		BagInterface <String> aBag = new LinkedBag<String>();
		System.out.println("Full: " + aBag.isFull());
		System.out.println("Empty: " + aBag.isEmpty());
		System.out.println("Size: " + aBag.getCurrentSize());
		System.out.println("Adding 3 entries: Hello, World, Goodnight");
		aBag.add("Hello");
		aBag.add("World");
		aBag.add("Goodnight");
		System.out.println("\nDone Adding");
		System.out.println("Full: " + aBag.isFull());
		System.out.println("Empty: " + aBag.isEmpty());
		System.out.println("Size: " + aBag.getCurrentSize());
		System.out.print("Array: ");
		Object[] aBagArray = aBag.toArray();
		for(int i = 0; i < aBagArray.length; i++){
			System.out.print(aBagArray[i] + " ");
		}
		
		System.out.println("\n\nRemove World");
		System.out.println("Removed: " + aBag.remove("World"));
		aBagArray = aBag.toArray();
		for(int i = 0; i < aBagArray.length; i++){
			System.out.print(aBagArray[i] + " ");
		}
		
		System.out.println("\n\nAdd Hello, check Frequency");
		aBag.add("Hello");
		System.out.println("Hello Frequency: " + aBag.getFrequencyOf("Hello"));
		System.out.print("Array: ");
		aBagArray = aBag.toArray();
		for(int i = 0; i < aBagArray.length; i++){
			System.out.print(aBagArray[i] + " ");
		}
		System.out.println();
		System.out.println("Contains Goodnight: " + aBag.contains("Goodnight"));
		
		aBag.clear();
		System.out.println("\nCleared Entries");
		System.out.println("Full: " + aBag.isFull());
		System.out.println("Empty: " + aBag.isEmpty());
		System.out.println("Size: " + aBag.getCurrentSize());
		System.out.println("Contains Goodnight: " + aBag.contains("Goodnight"));
		
		System.out.println("\n\nTesting JOptionPane");
		Object[] objects = {"New", "Open"};
		//System.out.println(JOptionPane.NO_OPTION == JOptionPane.showOptionDialog(null, "Testing OPEN and NEW", "Title", 
							//JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, objects, objects[1]));
							
		JFileChooser chooser = new JFileChooser();
		int openValue = chooser.showOpenDialog(null);
			if(openValue == JFileChooser.APPROVE_OPTION){
				System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
				String result = chooser.getSelectedFile().getAbsolutePath();
				System.out.println(chooser.getSelectedFile().getAbsolutePath());
			}
	}
}