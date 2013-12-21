public class Receipt implements ReceiptInterface {
	private String whereFrom;
	private int dayOfMonth;
	private int monthOfYear;
	private int year;
	private double currencyAmount;
	private int expenseOrIncome;
	
	public Receipt(){
		whereFrom = null;
		dayOfMonth = -1;
		monthOfYear = -1;
		year = -1;
		currencyAmount = -1;
		expenseOrIncome = -1;
	}
	
	public Receipt(int day, int month, int year, String where, double currency, int eOrI){
		whereFrom = where;
		dayOfMonth = day;
		monthOfYear = month;
		this.year = year;
		currencyAmount = currency;
		expenseOrIncome = eOrI;
	}
	
	public void setWhere(String newEntry){
		whereFrom = newEntry;
	}
	
	public void setDay(int day){
		dayOfMonth = day;
	}
	
	public void setMonth(int month){
		monthOfYear = month;
	}
	
	public void setYear(int year){
		this.year = year;
	}
	
	public void setCost(double currency){
		currencyAmount = currency;
	}
	
	public void setExpenseOrIncome(String eOrI){
		eOrI = eOrI.toLowerCase();
		if(eOrI.equals("income")){
			expenseOrIncome = 0;
		}else if(eOrI.equals("expense")){
			expenseOrIncome = 1;
		}else{
			expenseOrIncome = -1;
		}
	}

	public String getWhere(){
		return whereFrom;
	}
	
	public int getDay(){
		return dayOfMonth;
	}
	
	public int getMonth(){
		return monthOfYear;
	}
	
	public int getYear(){
		return year;
	}
	
	public double getCost(){
		return currencyAmount;
	}
	
	public int getExpenseOrIncome(){
		return expenseOrIncome;
	}

	public String toString(){
		String eOrI = "null";
		if(expenseOrIncome == 1){
			eOrI = "Expense";
		}else if(expenseOrIncome == 0){
			eOrI = "Income";
		}
		return new String(dayOfMonth + ", " + monthOfYear + ", " + year + ", " + whereFrom + ", " + currencyAmount + ", " + eOrI);
	}
	
	public boolean equals(Receipt testEntry){
		boolean result = false;
		if(this.dayOfMonth == testEntry.getDay()){
			if(this.monthOfYear == testEntry.getMonth()){
				if(this.year == testEntry.getYear()){
					if(this.whereFrom.equals(testEntry.getWhere())){
						if(this.currencyAmount == testEntry.getCost()){
							if(this.expenseOrIncome == testEntry.getExpenseOrIncome()){
								result = true;
							}
						}
					}
				}
			}
		}
		return result;
	}
}