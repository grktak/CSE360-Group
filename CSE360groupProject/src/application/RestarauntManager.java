package application;

import java.util.ArrayList;

public class RestarauntManager extends User{

	public RestarauntManager(String userName, String phoneNumber) {
		super(userName, phoneNumber);
	}

	private ArrayList<FoodItem> currentOrders = new ArrayList<FoodItem>();

	public ArrayList<FoodItem> getCurrentOrders() {
		return currentOrders;
	}

	public void setCurrentOrders(ArrayList<FoodItem> currentOrders) {
		this.currentOrders = currentOrders;
	}	
	
}
