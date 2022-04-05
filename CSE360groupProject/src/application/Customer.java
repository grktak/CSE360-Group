package application;

import java.util.ArrayList;

public class Customer extends User{

	private CreditCard creditInfo;
	private boolean hasCoupon;
	private ArrayList<FoodItem> favoriteOrders = new ArrayList<FoodItem>();
	private ArrayList<Order> orderHistory = new ArrayList<Order>();

	public Customer(CreditCard creditInfo, String user, String number)
	{
		super(user, number);
		this.creditInfo = creditInfo; 
	}
	
	public Customer(String user, String number)
	{
		super(user, number);
		this.creditInfo = new CreditCard("NOT ON FILE", "NOT ON FILE", "NOT ON FILE"); 
	}
	
	public CreditCard getCreditInfo() {
		return creditInfo;
	}

	public ArrayList<FoodItem> getFavoriteOrders() {
		return favoriteOrders;
	}

	public void setFavoriteOrders(ArrayList<FoodItem> favoriteOrders) {
		this.favoriteOrders = favoriteOrders;
	}

	public boolean hasCoupon() {
		return hasCoupon;
	}

	public void setHasCoupon(boolean hasCoupon) {
		this.hasCoupon = hasCoupon;
	}

	public ArrayList<Order> getOrderHistory() {
		return orderHistory;
	}
	
	public String getFormattedOrderHistory()
	{
		StringBuilder fieldContent = new StringBuilder("");
		for(int i = 1; i < orderHistory.size() + 1; i++)
		{
			fieldContent.append("ORDER #" + i + "\n");
			orderHistory.get(i-1).setOrderNumber(i);
			fieldContent.append(orderHistory.get(i-1).getFormattedOrderItems());
		}
		
		return fieldContent.toString();
	}
	
}
