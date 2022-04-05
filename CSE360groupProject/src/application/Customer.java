package application;

import java.util.ArrayList;

public class Customer extends User{

	private CreditCard creditInfo;
	private boolean hasCoupon;
	private ArrayList<FoodItem> favoriteOrders = new ArrayList<FoodItem>();
	private ArrayList<FoodItem> orderHistory = new ArrayList<FoodItem>();

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

	public ArrayList<FoodItem> getOrderHistory() {
		return orderHistory;
	}

	public void setOrderHistory(ArrayList<FoodItem> orderHistory) {
		this.orderHistory = orderHistory;
	}
	
	public String getFormattedOrderHistory()
	{
		if(this.orderHistory.isEmpty()) {
			return " ";
		}
		StringBuilder fieldContent = new StringBuilder("");
		for(int i = 0; i < orderHistory.size(); i++)
		{
			fieldContent.append(orderHistory.get(i).getName() + "\n" + "Ingredients: " + orderHistory.get(i).listIngredients()
					+ "\n" + "Time to make: " + Integer.toString(orderHistory.get(i).getTimeToMake()) + "\n" + "$" + Integer.toString(orderHistory.get(i).getPrice()));
			fieldContent.append("\n\n");
		}
		
		System.out.println("ERROR HERE");
		fieldContent.toString();
		return fieldContent.toString();
	}
	
}
