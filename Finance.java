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
	private JPanel mainPanel, buttonPanel, flowPanel;
	private JTextField dayText, monthText, yearText, reasonText, amountText;
	private JLabel moneyLabel, slashLabel;
	private JButton incomeButton, expenseButton;
	private ReceiptInterface receipts;
	private BagInterface linkedReceiptBag, linkedFileBag;
	private FileInterface settingsFile;
	
	public Finance(){ //Default Constructor
		super("Financing Elements"); //Used to change title of window
		contents = getContentPane(); 
		contents.setLayout(new BorderLayout()); //Set the main window to BorderLayout
		//*************************************
		receipts = new Receipt();
		linkedReceiptBag = new LinkedBag();
		linkedFileBag = new LinkedBag();
		settingsFile = new FileMod();
		JFileChooser chooser = new JFileChooser();
		Object[] objects = {"New", "Open"};
		while(true){
			int optionChose = JOptionPane.showOptionDialog(null, "Testing OPEN and NEW", "Title", 
							JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, objects, objects[1]);
			if(JOptionPane.NO_OPTION == optionChose){
				int openValue = chooser.showOpenDialog(null);
				if(openValue == JFileChooser.APPROVE_OPTION){
					System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
					settingsFile.setPath(chooser.getSelectedFile().getAbsolutePath()); //Set Settings File Path
					if(settingsFile.fileCheck("FinanceSettings.txt")){
						for(int i = 0; i < settingsFile.getLines(); i++){
							linkedFileBag.add(new FileMod(settingsFile.getParentPath(), settingsFile.readLine(i)));
						}
						break;
					}else{
						JOptionPane.showMessageDialog(null, "The file \"" + chooser.getSelectedFile().getName() + 
												"\" is the incorrect file.\nPlease open the file named \"FinanceSettings.txt\"", 
												"Incorrect File", JOptionPane.ERROR_MESSAGE);
					}
				}else if(openValue == JFileChooser.CANCEL_OPTION){
					System.out.println("Cancel");
					System.exit(0);
				}else if(openValue == JFileChooser.ERROR_OPTION){
					System.out.println("Error");
					System.exit(0);
				}
			}else if(JOptionPane.YES_OPTION == optionChose){
				System.out.println("Yes/New");
				break;
			}else{
				System.exit(0);
			}
		}
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
		contents.add(mainPanel, BorderLayout.CENTER);
		
		setSize(300, 150); //Changes the size of the window by pixels
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