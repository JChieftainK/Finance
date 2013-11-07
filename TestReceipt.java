import java.text.DecimalFormat;

public class TestReceipt {
	public static void main(String[] args) {
		DecimalFormat pricePattern = new DecimalFormat("#0.00");
		DecimalFormat timePattern = new DecimalFormat("00");
	
		ReceiptInterface testReceipt = new Receipt();
		System.out.println(testReceipt.toString());
		
		testReceipt = new Receipt(7, 4, 2013, "Walmart", 34.50);
		System.out.println(testReceipt.toString());
		
		System.out.println(timePattern.format(testReceipt.getDay()) + ", " + 
							timePattern.format(testReceipt.getMonth()) + ", " + 
							pricePattern.format(testReceipt.getCost()));
		
		System.out.println(new Receipt(31, 10, 2013, "Halloween", 100.57));
		System.out.println(testReceipt.equals(new Receipt(7,4,2013, "Walmart", 34.50)));
		System.out.println(testReceipt.equals(new Receipt(7,4,2013, "Walmart", 34.50)));

	}
}