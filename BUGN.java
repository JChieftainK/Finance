/*
	Author: Justin Kennedy
	Financial Program
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BUGN extends JFrame implements ActionListener {
	private Container contents;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	
	public BUGN(){
		super("Budget Using Graphs & Numbers");
		contents = getContentPane();
		contents.setLayout(new BorderLayout());
		
		//*************************************
		//Set up JMenu
		menuBar = new JMenuBar();
		//Set up "File" for JMenu
		menu = new JMenu("File");
		menuBar.add(menu);
		
		//Add "Exit" to "File" menu
		menuItem = new JMenuItem("Exit");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		setJMenuBar(menuBar); //Adds menu bar to GUI
		//*************************************
		
		setSize(200, 200);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae){
		JMenuItem source = (JMenuItem)(ae.getSource());
		//Exit clicked. Exit program
		if(source.getText() == "Exit"){
			System.exit(0);
		}
	}
	
	public static void main (String[] args){
		BUGN bn = new BUGN();
		bn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}