package application;

import java.util.ArrayList;

public class Customer{

	private CreditCard creditInfo;
	private boolean hasCoupon;
	private ArrayList<FoodItem> favoriteOrders = new ArrayList<FoodItem>();

	public Customer(CreditCard creditInfo)
	{
		this.creditInfo = creditInfo; 
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
