package application;

import java.util.ArrayList;

public class Menu {

	public ArrayList<FoodItem> items = new ArrayList<FoodItem>();
	public int numOfAppetizers;
	public int numOfEntrees;
	public int numOfSpecials;
	public int numOfDesserts;
	public int numOfDrinks;
	
	public void addItem(FoodItem item)
	{
		items.add(item);
	}
	
	public void removeItem(FoodItem item)
	{
		items.remove(item);
	}
	
	public void editItem(FoodItem item, int price, String name, String ingredients[], int timeToMake)
	{
		removeItem(item);
		item.setPrice(price);
		item.setName(name);
		item.setIngredients(ingredients);
		item.setTimeToMake(timeToMake);
		addItem(item);
	}
	
	public String getFormattedMenu()
	{
		StringBuilder fieldContent = new StringBuilder("");
		for(int i = 0; i < items.size(); i++)
		{
			fieldContent.append(items.get(i).getName() + "\n" + "Ingredients: " + items.get(i).listIngredients()
					+ "\n" + "Time to make: " + Integer.toString(items.get(i).getTimeToMake()) + "\n" + "$" + Integer.toString(items.get(i).getPrice()));
			fieldContent.append("\n\n");
		}
		
		return fieldContent.toString();
	}
}
