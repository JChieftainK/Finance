public class Receipt implements ReceiptInterface {
	private String whereFrom;
	private int dayOfMonth;
	private int monthOfYear;
	private int year;
	private double currencyAmount;
	
	public Receipt(){
		whereFrom = null;
		dayOfMonth = -1;
		monthOfYear = -1;
		year = -1;
		currencyAmount = -1;
	}
	
	public Receipt(int day, int month, int year, String where, double currency){
		whereFrom = where;
		dayOfMonth = day;
		monthOfYear = month;
		this.year = year;
		currencyAmount = currency;
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

	public String toString(){
		return new String(dayOfMonth + ", " + monthOfYear + ", " + year + ", " + whereFrom + ", " + currencyAmount);
	}
	
	public boolean equals(Receipt testEntry){
		boolean result = false;
		if(this.dayOfMonth == testEntry.getDay()){
			if(this.monthOfYear == testEntry.getMonth()){
				if(this.year == testEntry.getYear()){
					if(this.whereFrom == testEntry.getWhere()){
						if(this.currencyAmount == testEntry.getCost()){
							result = true;
						}
					}
				}
			}
		}
		return result;
	}
}