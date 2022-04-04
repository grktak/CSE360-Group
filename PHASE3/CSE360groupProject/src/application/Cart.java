package application;

public class Cart {
	
	private int numberOfItems;
	
	private int totalCost;
	
	private FoodItem[] foodItemsInCart;

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

	public FoodItem[] getFoodItemsInCart() {
		return foodItemsInCart;
	}

	public void setFoodItemsInCart(FoodItem[] foodItemsInCart) {
		this.foodItemsInCart = foodItemsInCart;
	}
	
}
