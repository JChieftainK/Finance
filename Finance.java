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
		super("Financing Elements"); //Used to change title of window
		contents = getContentPane(); 
		contents.setLayout(new BorderLayout()); //Set the main window to BorderLayout
		
		//*************************************
		menuBar = new JMenuBar(); //Set up JMenu
		
		menu = new JMenu("File"); //Set up "File" for JMenu
		menuBar.add(menu); //Add the "File" option
			
		menuItem = new JMenuItem("Exit"); //Add "Exit" to "File" menu
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK)); //Type control + E to call
		menuItem.addActionListener(this); //Add a listener to the object
		menu.add(menuItem); //Add the "Exit"
		
		setJMenuBar(menuBar); //Adds menu bar to GUI
		//*************************************
	//*************************************
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(3,1));
		
		flowPanel = new JPanel();
		flowPanel.setLayout(new FlowLayout());
		
		dayText = new JTextField("DD",2);
		dayText.setHorizontalAlignment(JTextField.RIGHT);
		flowPanel.add(dayText);
		slashLabel = new JLabel("/");
		flowPanel.add(slashLabel);
		monthText = new JTextField("MM",2);
		monthText.setHorizontalAlignment(JTextField.RIGHT);
		flowPanel.add(monthText);
		slashLabel = new JLabel("/");
		flowPanel.add(slashLabel);
		yearText = new JTextField("YYYY",3);
		yearText.setHorizontalAlignment(JTextField.RIGHT);
		flowPanel.add(yearText);
		
		mainPanel.add(flowPanel);
		
		flowPanel = new JPanel();
		flowPanel.setLayout(new FlowLayout());
			
		reasonText = new JTextField(15);
		flowPanel.add(reasonText);
		moneyLabel = new JLabel("$");
		flowPanel.add(moneyLabel);
		amountText = new JTextField(4);
		amountText.setHorizontalAlignment(JTextField.RIGHT);
		flowPanel.add(amountText);
		
		mainPanel.add(flowPanel);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2));
		flowPanel = new JPanel();
		flowPanel.setLayout(new FlowLayout());
		
		incomeButton = new JButton("Income");
		incomeButton.setForeground(new Color(0,150,0));
		incomeButton.addActionListener(this);
		flowPanel.add(incomeButton);
		buttonPanel.add(flowPanel);
		flowPanel = new JPanel();
		flowPanel.setLayout(new FlowLayout());
		expenseButton = new JButton("Expense");
		expenseButton.setForeground(Color.RED);
		expenseButton.addActionListener(this);
		flowPanel.add(expenseButton);
		buttonPanel.add(flowPanel);
		
		mainPanel.add(buttonPanel);
		//*************************************
		
		setSize(200, 200); //Changes the size of the window by pixels
		setVisible(true); //Tells it to be seen on the computer screen
	}
	
	public void actionPerformed(ActionEvent ae){ //Method that is called for Items that have action listeners
		if(ae.getSource() instanceof JMenuItem){ //Checks the instance of the listener for Menu stuff
			JMenuItem source = (JMenuItem)(ae.getSource());
			if(source.getText() == "Exit"){ //Exit clicked. Exit program
				System.exit(0);
			}
		}
	}
	
	public static void main(String[] args) { //Main method starts program
		Finance fin = new Finance(); //Create an object of the finance app
		fin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Set default close operation to the normal exit on close
	}
}