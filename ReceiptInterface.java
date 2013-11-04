public interface ReceiptInterface {
	public void setWhere(String newEntry);
	public void setDay(int day);
	public void setMonth(int month);
	public void setYear(int year);
	public void setCost(double cost);
	
	public String getWhere();
	public int getDay();
	public int getMonth();
	public int getYear();
	public double getCost();
	
	public String toString();	
	public boolean equals(Receipt testEntry);
}