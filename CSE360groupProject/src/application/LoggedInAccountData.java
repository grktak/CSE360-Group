package application;

import java.util.ArrayList;

public class LoggedInAccountData {
	public static ArrayList<Customer> cachedCustomers = new ArrayList<Customer>();
	
	public static Customer loggedInCustomer = null;
	
	//Starting Menus
	public static Menu appetizerMenu;
	public static Menu entreeMenu;
	public static Menu spacialsMenu;
	public static Menu desertMenu;
	public static Menu drinksMenu;
	
	public static void initalizeMenu()
	{
		
		//Appetizer
		appetizerMenu = new Menu();
		
		String[] ingredients1 = {"Potatoes", "Salt", "Sugar"};
		appetizerMenu.addItem(new FoodItem(6, "Fries", ingredients1, 5));
		
		String[] ingredients2 = {"Bread", "Cheese", "Garlic"};
		appetizerMenu.addItem(new FoodItem(10, "Garlic Bread", ingredients2, 10));
		//- - - - - 
		
		//Entree
		entreeMenu = new Menu();
		
		String[] ingredients3 = {"Bread", "Cheese", "Sauce"};
		entreeMenu.addItem(new FoodItem(12, "Pizza", ingredients3, 20));
		
		String[] ingredients4 = {"Bread", "Cheese", "Patty"};
		entreeMenu.addItem(new FoodItem(13, "Burger", ingredients4, 9));
		//- - - - -
		
		//Spacials
		spacialsMenu = new Menu();
		
		String[] ingredients5 = {"Lettuce", "Tomato", "Dressing"};
		spacialsMenu.addItem(new FoodItem(9, "Salad", ingredients5, 5));
		//- - - - -
		
		//Desert
		desertMenu = new Menu();
		
		String[] ingredients6 = {"Chocolate", "Ice Cream", "Frosting"};
		desertMenu.addItem(new FoodItem(11, "Chocolate cake", ingredients6, 25));
		//- - - - -
		
		//Drinks
		drinksMenu = new Menu();
		String[] ingredients7 = {"Syrup", "Water", "Carbonation"};
		drinksMenu.addItem(new FoodItem(3, "Sprite", ingredients7, 1));
		//- - - - -
	}
	
	public static int getTotalWaitTime()
	{
		int waitTime = 0;
		
		for(int i = 0; i < cachedCustomers.size(); i++)
		{
			for(int j = 0; j < cachedCustomers.get(i).getOrderHistory().size(); j++)
			{
				waitTime += cachedCustomers.get(i).getOrderHistory().get(j).CalculateOrderTotalWaitTime();
			}
		}
		
		for(int i = 0; i < loggedInCustomer.getOrderHistory().size(); i++)
		{
			waitTime += loggedInCustomer.getOrderHistory().get(i).CalculateOrderTotalWaitTime();
		}
		
		return waitTime;
	}
	
	public static int getLineNumber()
	{
		int lineNumber = 0;
		
		for(int i = 0; i < cachedCustomers.size(); i++)
		{
			for(int j = 0; j < cachedCustomers.get(i).getOrderHistory().size(); j++)
			{
				lineNumber++;
			}
		}
		
		for(int i = 0; i < loggedInCustomer.getOrderHistory().size(); i++)
		{
			lineNumber++;
		}
		
		return lineNumber;
	}
	
	
}
