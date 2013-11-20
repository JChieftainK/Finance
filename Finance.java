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
	private JLabel moneyLabel, slashLabel, amountLabel, currentAmount, successOrNot;
	private JButton incomeButton, expenseButton;
	private ReceiptInterface receipts;
	private ListInterface <Receipt> linkedReceiptList;
	private ListInterface <FileMod> linkedFileList;
	private FileInterface settingsFile;
	private DecimalFormat pricePattern = new DecimalFormat("#0.00");
	private DecimalFormat timePattern = new DecimalFormat("00");
	private int accountNumber = 1;
	
	public Finance(){ //Default Constructor
		super("Financing Elements"); //Used to change title of window
		contents = getContentPane(); 
		contents.setLayout(new BorderLayout()); //Set the main window to BorderLayout
		currentAmount = new JLabel("0.00");
		//*************************************
		linkedReceiptList = new LList <Receipt>(); //create an instance of LinkedBag for receipts
		linkedFileList = new LList <FileMod>(); //create a linkedBag for file lines
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
							linkedFileList.add(new FileMod(settingsFile.getParentPath(), settingsFile.readLine(i)));
						}
						createProfile();
						calculateAmount();
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
		//*************************************
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1,2));
		
		flowPanel = new JPanel();
		flowPanel.setLayout(new FlowLayout());
		
		mainPanel.add(flowPanel);
		
		flowPanel = new JPanel();
		flowPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		amountLabel = new JLabel("Amount: ");
		flowPanel.add(amountLabel);
		flowPanel.add(currentAmount);
		
		mainPanel.add(flowPanel);
		
		//*************************************
		contents.add(mainPanel, BorderLayout.SOUTH);
		//*************************************
		
		setSize(300, 160); //Changes the size of the window by pixels
		setVisible(true); //Tells it to be seen on the computer screen
	}
	
	public void actionPerformed(ActionEvent ae){ //Method that is called for Items that have action listeners
		if(ae.getSource() instanceof JMenuItem){ //Checks the instance of the listener for Menu stuff
			JMenuItem source = (JMenuItem)(ae.getSource());
			if(source.getText() == "Exit"){ //Exit clicked. Exit program
				System.exit(0);
			}
		}else if(ae.getSource() == incomeButton){
			if(checkInputs(dayText.getText(), monthText.getText(), yearText.getText(), reasonText.getText(), amountText.getText())){
				int optionChose = JOptionPane.showConfirmDialog(null, "Is this information correct?" +
												"\nDay: " + timePattern.format(Integer.parseInt(dayText.getText())) + 
												"\nMonth: " + timePattern.format(Integer.parseInt(monthText.getText())) +
												"\nYear: " + yearText.getText() + 
												"\nFrom: " + reasonText.getText() +
												"\nAmount: " + pricePattern.format(Double.parseDouble(amountText.getText())),
												"Information Given", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if(optionChose == JOptionPane.OK_OPTION){
					linkedReceiptList.add(new Receipt(Integer.parseInt(dayText.getText()), Integer.parseInt(monthText.getText()),
												Integer.parseInt(yearText.getText()), reasonText.getText(), 
												Double.parseDouble(amountText.getText()), 0));
					//Add saving to the current account number
					(linkedFileList.getEntry(accountNumber)).appendToFile(dayText.getText() + ":" + monthText.getText() + ":" +
												yearText.getText() + ":" + reasonText.getText() + ":" + 
												pricePattern.format(Double.parseDouble(amountText.getText())) + ":" + "0");
					resetGUI();
				}
			}else{
				JOptionPane.showMessageDialog(null, "The information is in an incorrect format." + 
												"\nDay: " + dayText.getText() + "\nMonth: " + monthText.getText() +
												"\nYear: " + yearText.getText() + "\nFrom: " + reasonText.getText() +
												"\nAmount: " + amountText.getText(),
												"Incorrect Format", JOptionPane.ERROR_MESSAGE);
				resetGUI();
			}
		}else if(ae.getSource() == expenseButton){
			System.out.println("Expense");
			if(checkInputs(dayText.getText(), monthText.getText(), yearText.getText(), reasonText.getText(), amountText.getText())){
				int optionChose = JOptionPane.showConfirmDialog(null, "Is this information correct?" +
												"\nDay: " + timePattern.format(Integer.parseInt(dayText.getText())) + 
												"\nMonth: " + timePattern.format(Integer.parseInt(monthText.getText())) +
												"\nYear: " + yearText.getText() + 
												"\nFrom: " + reasonText.getText() +
												"\nAmount: " + pricePattern.format(Double.parseDouble(amountText.getText())),
												"Information Given", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if(optionChose == JOptionPane.OK_OPTION){
					linkedReceiptList.add(new Receipt(Integer.parseInt(dayText.getText()), Integer.parseInt(monthText.getText()),
												Integer.parseInt(yearText.getText()), reasonText.getText(), 
												Double.parseDouble(amountText.getText()), 1));
					//Add expense to the current account number
					(linkedFileList.getEntry(accountNumber)).appendToFile(dayText.getText() + ":" + monthText.getText() + ":" +
												yearText.getText() + ":" + reasonText.getText() + ":" + 
												pricePattern.format(Double.parseDouble(amountText.getText())) + ":" + "1");
					resetGUI();
				}
			}else{
				JOptionPane.showMessageDialog(null, "The information is in an incorrect format." + 
												"\nDay: " + dayText.getText() + "\nMonth: " + monthText.getText() +
												"\nYear: " + yearText.getText() + "\nFrom: " + reasonText.getText() +
												"\nAmount: " + amountText.getText(),
												"Incorrect Format", JOptionPane.ERROR_MESSAGE);
				resetGUI();
			}
		}
	}
	
	public boolean checkInputs(String dayString, String monthString, String yearString, String reasonString, String amountString){
		boolean result = false;
		String regex = "\\d+";
		System.out.println(dayString.matches(regex));
		if(dayString.matches(regex) && ((Integer.parseInt(dayString) >= 1) && (Integer.parseInt(dayString) <= 31))){
			if(monthString.matches(regex) && ((Integer.parseInt(monthString) >=  1) && (Integer.parseInt(monthString) <= 12))){
				if(yearString.matches(regex) && (Integer.parseInt(yearString) >= 1000)){
					System.out.println("Double: " + (amountString.matches("\\d+\\.\\d+") || amountString.matches(regex)));
					if((amountString.matches("\\d+\\.\\d+") || amountString.matches(regex)) && (Double.parseDouble(amountString) >= 0)){
						System.out.println(pricePattern.format(Double.parseDouble(amountString)));
						result = true;
					}
				}
			}
		}
		return result;
	}
	
	public void resetGUI(){
		dayText.setText("DD");
		monthText.setText("MM");
		yearText.setText("YYYY");
		reasonText.setText("");
		amountText.setText("");
		calculateAmount();
	}
	
	public void calculateAmount(){
		double amount = 0.0;
		if(!linkedReceiptList.isEmpty()){
			for(int i = 1; i <= linkedReceiptList.getLength(); i++){
				if((linkedReceiptList.getEntry(i)).getExpenseOrIncome() == 1){
					amount -= (linkedReceiptList.getEntry(i)).getCost();
				}else if((linkedReceiptList.getEntry(i)).getExpenseOrIncome() == 0){
					amount += (linkedReceiptList.getEntry(i)).getCost();
				}
			}
		}
		if(amount < 0){
			currentAmount.setForeground(Color.RED);
		}else{
			currentAmount.setForeground(Color.BLACK);
		}
		currentAmount.setText(pricePattern.format(amount));
		System.out.println(pricePattern.format(amount));
	}
	
	public void createProfile(){
		if(!linkedFileList.isEmpty()){
			System.out.println("Lines: " + (linkedFileList.getEntry(accountNumber)).getLines());
			for(int i = 1; i <= (linkedFileList.getEntry(accountNumber)).getLines(); i++){
				linkedReceiptList.add(new Receipt(separateDay((linkedFileList.getEntry(accountNumber)).readLine(i)),
										separateMonth((linkedFileList.getEntry(accountNumber)).readLine(i)),
										separateYear((linkedFileList.getEntry(accountNumber)).readLine(i)),
										separateWhere((linkedFileList.getEntry(accountNumber)).readLine(i)),
										separateAmount((linkedFileList.getEntry(accountNumber)).readLine(i)),
										separateEOrI((linkedFileList.getEntry(accountNumber)).readLine(i))));
			}
		}
	}
	
	public int separateDay(String lineRead){
		int result = 0;
		String[] separatedLine = separateLineRead(lineRead);
		System.out.println("Day: " + separatedLine[0]); //DEBUG
		result = Integer.parseInt(separatedLine[0]);
		return result;
	}
	
	public int separateMonth(String lineRead){
		int result = 0;
		String[] separatedLine = separateLineRead(lineRead);
		System.out.println("Month: " + separatedLine[1]); //DEBUG
		result = Integer.parseInt(separatedLine[1]);
		return result;
	}
	
	public int separateYear(String lineRead){
		int result = 0;
		String[] separatedLine = separateLineRead(lineRead);
		System.out.println("Year: " + separatedLine[2]); //DEBUG
		result = Integer.parseInt(separatedLine[2]);
		return result;
	}
	
	public String separateWhere(String lineRead){
		String result = "";
		String[] separatedLine = separateLineRead(lineRead);
		System.out.println("Where: " + separatedLine[3]); //DEBUG
		result = separatedLine[3];
		return result;
	}
	
	public double separateAmount(String lineRead){
		double result = 0.0;
		String[] separatedLine = separateLineRead(lineRead);
		System.out.println("Amount: " + separatedLine[4]); //DEBUG
		result = Double.parseDouble(separatedLine[4]);
		return result;
	}
	
	public int separateEOrI(String lineRead){
		int result = -1;
		String[] separatedLine = separateLineRead(lineRead);
		System.out.println("Expense Or Income: " + separatedLine[5]); //DEBUG
		result = Integer.parseInt(separatedLine[5]);
		return result;
	}
	
	public String[] separateLineRead(String lineRead){
		String[] result = lineRead.split(":");
		return result;
	}
	
	
	public static void main(String[] args) { //Main method starts program
		Finance fin = new Finance(); //Create an object of the finance app
		fin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Set default close operation to the normal exit on close
	}
}