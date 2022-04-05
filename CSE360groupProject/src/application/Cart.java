package application;

import java.util.ArrayList;

public class Cart {
	
	private int numberOfItems = 0;
	
	private int totalCost;
	
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

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
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
}
