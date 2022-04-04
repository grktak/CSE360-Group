package application;

public class FoodItem {

	private int price;
	private String name;
	private String ingredients[];
	private int timeToMake;
	
	public FoodItem(int price, String name, String ingredients[], int timeToMake)
	{
		setPrice(price);
		setName(name);
		setIngredients(ingredients);
		setTimeToMake(timeToMake);
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getIngredients() {
		return ingredients;
	}
	
	public String listIngredients()
	{
		String temp = "";
		for(int i = 0; i < ingredients.length; i++)
		{
			if(i != ingredients.length - 1)
			{
				temp += ingredients[i] + ", ";
			}
			else
			{
				temp += ingredients[i];
			}
		}
		
		return temp;
	}

	public void setIngredients(String ingredients[]) {
		this.ingredients = ingredients;
	}

	public int getTimeToMake() {
		return timeToMake;
	}

	public void setTimeToMake(int timeToMake) {
		this.timeToMake = timeToMake;
	}
	
}
