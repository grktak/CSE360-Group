package application;

import java.util.ArrayList;

public class Cart {
	
	private int numberOfItems = 0;
	
	private float totalCost;
	
	private ArrayList<FoodItem> foodItemsInCart = new ArrayList<FoodItem>();

	public String getFormattedFoodItemsInCart()
	{
		StringBuilder fieldContent = new StringBuilder("");
		for(int i = 0; i < foodItemsInCart.size(); i++)
		{
			fieldContent.append(foodItemsInCart.get(i).getFormattedFoodItem());
		}
		return fieldContent.toString();
	}
	
	public boolean isEmpty()
	{
		if(foodItemsInCart.size() <= 0)
		{
			return true;
		}
		
		return false;
	}
	
	public boolean itemExistsInCart(FoodItem item)
	{
		if(foodItemsInCart.contains(item))
		{
			return true;
		}
		
		return false;
	}
	
	public int getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(float f) {
		this.totalCost = (f - (f * .2f));
	}
	
	public ArrayList<FoodItem> getItemsInCart()
	{
		return foodItemsInCart;
	}

	public void addFoodItemToCart(FoodItem item)
	{
		foodItemsInCart.add(item);
		numberOfItems++;
		totalCost += item.getPrice();
	}
	
	public void removeItemFromCart(FoodItem item)
	{
		foodItemsInCart.remove(item);
		numberOfItems--;
		totalCost -= item.getPrice();
	}
	
	public void emptyCart()
	{
		foodItemsInCart.removeAll(foodItemsInCart);
		numberOfItems = 0;
		totalCost = 0;
	}
	
	public void addCartItemsFromOrder(Order order)
	{
		foodItemsInCart.removeAll(foodItemsInCart);
		for(int i = 0; i < order.getOrderItems().size(); i++)
		{
			addFoodItemToCart(order.getOrderItems().get(i));
		}
	}
	
	public int totalCartTime() {
		int totalTime = 0;
		for(int i=0; i<foodItemsInCart.size();i++) {
			totalTime+=foodItemsInCart.get(i).getTimeToMake();
		}
		return totalTime;
	}
	
}
