package application;

import java.util.ArrayList;

public class Customer extends User{

	private CreditCard creditInfo;
	private boolean hasCoupon;
	private ArrayList<FoodItem> favoriteOrders = new ArrayList<FoodItem>();

	public Customer(CreditCard creditInfo, String user, String number)
	{
		super(user, number);
		this.creditInfo = creditInfo; 
	}
	
	public Customer(String user, String number)
	{
		super(user, number);
		this.creditInfo = new CreditCard("1234567891011121314", "X/X", "XXX"); 
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
	
}
