package application;

import java.util.ArrayList;

public class Menu {

	public static ArrayList<FoodItem> items = new ArrayList<FoodItem>();
	public static int numOfAppetizers;
	public static int numOfEntrees;
	public static int numOfSpecials;
	public static int numOfDesserts;
	public static int numOfDrinks;
	
	public static void addItem(FoodItem item)
	{
		items.add(item);
	}
	
	public static void removeItem(FoodItem item)
	{
		items.remove(item);
	}
	
	public static void editItem(FoodItem item, int price, String name, String ingredients[], int timeToMake)
	{
		removeItem(item);
		item.setPrice(price);
		item.setName(name);
		item.setIngredients(ingredients);
		item.setTimeToMake(timeToMake);
		addItem(item);
	}
}
