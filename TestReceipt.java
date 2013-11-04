public class TestReceipt {
	public static void main(String[] args) {
		ReceiptInterface testReceipt = new Receipt();
		System.out.println(testReceipt.toString());
		
		testReceipt = new Receipt(25, 12, 2013, "Walmart", 34.50);
		System.out.println(testReceipt.toString());
	}
}