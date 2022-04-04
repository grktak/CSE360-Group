package application;

import java.util.ArrayList;

public class RestarauntManager {

	private ArrayList<FoodItem> currentOrders = new ArrayList<FoodItem>();

	public ArrayList<FoodItem> getCurrentOrders() {
		return currentOrders;
	}

	public void setCurrentOrders(ArrayList<FoodItem> currentOrders) {
		this.currentOrders = currentOrders;
	}	
	
}
