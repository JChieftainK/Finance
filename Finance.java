/**
*	@author Justin Kennedy
*	Program for tracking expenses and income
*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Finance extends JFrame implements ActionListener{
	private Container contents;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	
	public Finance(){ //Default Constructor
		super("Budget Using Graphs & Numbers");
		contents = getContentPane();
		contents.setLayout(new BorderLayout());
		
		//*************************************
		menuBar = new JMenuBar(); //Set up JMenu
		
		menu = new JMenu("File"); //Set up "File" for JMenu
		menuBar.add(menu);
			
		menuItem = new JMenuItem("Exit"); //Add "Exit" to "File" menu
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		setJMenuBar(menuBar); //Adds menu bar to GUI
		//*************************************
		
		setSize(200, 200);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource() instanceof JMenuItem){
			JMenuItem source = (JMenuItem)(ae.getSource());
			if(source.getText() == "Exit"){ //Exit clicked. Exit program
				System.exit(0);
			}
		}
	}
	
	public static void main(String[] args) { //Main method starts program
		Finance fin = new Finance();
		fin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}