package application;

import java.util.ArrayList;

public class Order {

	private int orderNumber = -1;
	private ArrayList<FoodItem> orderItems = new ArrayList<FoodItem>();

	public ArrayList<FoodItem> getOrderItems() {
		return orderItems;
	}
	
	public String getFormattedOrderItems()
	{
		if(this.orderItems.isEmpty()) {
			return " ";
		}
		StringBuilder fieldContent = new StringBuilder("");
		for(int i = 0; i < orderItems.size(); i++)
		{
			fieldContent.append(orderItems.get(i).getName() + "\n" + "Ingredients: " + orderItems.get(i).listIngredients()
					+ "\n" + "Time to make: " + Integer.toString(orderItems.get(i).getTimeToMake()) + "\n" + "$" + Integer.toString(orderItems.get(i).getPrice()));
			fieldContent.append("\n\n");
		}
		
		System.out.println("ERROR HERE");
		fieldContent.toString();
		return fieldContent.toString();
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	
}
