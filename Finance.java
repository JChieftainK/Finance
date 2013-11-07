/**
*	@author Justin Kennedy
*	Program for tracking expenses and income
*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.text.DecimalFormat;

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
	private BagInterface <Receipt> linkedReceiptBag;
	private BagInterface <FileMod> linkedFileBag;
	private FileInterface settingsFile;
	private DecimalFormat pricePattern = new DecimalFormat("#0.00");
	private DecimalFormat timePattern = new DecimalFormat("00");
	
	public Finance(){ //Default Constructor
		super("Financing Elements"); //Used to change title of window
		contents = getContentPane(); 
		contents.setLayout(new BorderLayout()); //Set the main window to BorderLayout
		//*************************************
		linkedReceiptBag = new LinkedBag <Receipt>(); //create an instance of LinkedBag for receipts
		linkedFileBag = new LinkedBag <FileMod>(); //create a linkedBag for file lines
		settingsFile = new FileMod(); //create a file modification to make and edit files/folders
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //set to only select folders
		Object[] objects = {"New", "Open"}; //Used to make custom JOption text
		while(true){ //runs indefinitely, must use break or exit to change
			//Store the option of Opening or Creating a folder. Open = NoOption && New = YesOption
			int optionChose = JOptionPane.showOptionDialog(null, "Testing OPEN and NEW", "Title", 
							JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, objects, objects[1]); 
			
			if(JOptionPane.NO_OPTION == optionChose){ //If Open is selected
				JOptionPane.showMessageDialog(null, "Select the folder then click open.\n You may only select a folder",
												"Title", JOptionPane.PLAIN_MESSAGE); //Help Text for users
				int openValue = chooser.showOpenDialog(null); //store the option for choosing a folder
				
				if(openValue == JFileChooser.APPROVE_OPTION){ //Opening a folder was chosen
					System.out.println("You chose to open this folder: " + chooser.getSelectedFile().getName()); //DEBUG
					settingsFile.setPath(chooser.getSelectedFile().getAbsolutePath()); //Set Settings File Path
					settingsFile.add("FinanceSettings.txt"); //Add file to be main settings
					if(settingsFile.checkIfExists()){ //Test for required file inside folder
						for(int i = 1; i <= settingsFile.getLines(); i++){ //Get amount of lines in file and traverse file
							linkedFileBag.add(new FileMod(settingsFile.getParentPath(), settingsFile.readLine(i)));
						}
						createProfiles();
						break;
					}else{ //File is not inside of folder
						JOptionPane.showMessageDialog(null, "The folder \"" + chooser.getSelectedFile().getName() + 
												"\" is the incorrect folder.\nPlease open the folder containing \"FinanceSettings.txt\"", 
												"Incorrect Folder", JOptionPane.ERROR_MESSAGE);
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
	
	public void createProfiles(){
		for(int i = 1; i <= se)
	}
	
	public static void main(String[] args) { //Main method starts program
		Finance fin = new Finance(); //Create an object of the finance app
		fin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Set default close operation to the normal exit on close
	}
}