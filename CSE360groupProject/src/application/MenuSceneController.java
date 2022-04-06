package application;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuSceneController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML private Text userLabelMenu;
	@FXML private TextArea appetizerMenuTextArea;
	@FXML private TextArea entreeMenuTextArea;
	@FXML private TextArea spacialsMenuTextArea;
	@FXML private TextArea desertMenuTextArea;
	@FXML private TextArea drinkMenuTextArea;
	@FXML private TextArea cartTextArea;
	@FXML private Label waitTimeLabel;
	@FXML private Label totalCostLabel;
	
	//combo boxes for ordering
	@FXML private ComboBox<String> cBoxAppetizer;
	@FXML private ComboBox<String> cBoxEntree;
	@FXML private ComboBox<String> cBoxSpacial;
	@FXML private ComboBox<String> cBoxDessert;
	@FXML private ComboBox<String> cBoxDrink;
	
	//Add and remove buttons for ordering
	@FXML private Button addButtonAppetizer;
	
	private DecimalFormat df = new DecimalFormat("0.00");
	
	public void logOut(ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("login.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		
		LoggedInAccountData.cachedCustomers.add(LoggedInAccountData.loggedInCustomer);
		LoggedInAccountData.loggedInCustomer = null;
		
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToHomeNonValidate(ActionEvent event) throws IOException { 
		 FXMLLoader loader = new FXMLLoader();
		 loader.setLocation(getClass().getResource("home.fxml"));
		 root = loader.load();
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 
		 HomeSceneController controller = loader.getController();
		 controller.initData();
		 
		 stage.setScene(scene);
		 stage.show(); 
	}
	
	public void switchToPayment(ActionEvent event) throws IOException { 
		 if(!LoggedInAccountData.loggedInCustomer.getCustomerCart().isEmpty())
		 {
			FXMLLoader loader = new FXMLLoader();
		 	loader.setLocation(getClass().getResource("payment.fxml"));
		 	root = loader.load();
		 	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 	scene = new Scene(root);
		 
		 	PaymentSceneController controller = loader.getController();
		 	controller.initData();
		 
		 	stage.setScene(scene);
		 	stage.show(); 
		 }
		 else
		 {
			 System.out.println("Cart is empty");
		 }
	}
	
	public void initData()
	{
		if(LoggedInAccountData.loggedInCustomer.hasCoupon())
		{
			LoggedInAccountData.loggedInCustomer.getCustomerCart().setTotalCost(LoggedInAccountData.loggedInCustomer.getCustomerCart().getTotalCost());
		}
		
		userLabelMenu.setText("Hello, " + LoggedInAccountData.loggedInCustomer.getUserName());
		waitTimeLabel.setText("WAIT TIME: " + LoggedInAccountData.getTotalWaitTime() + " min");
		totalCostLabel.setText("Total: $" + df.format(LoggedInAccountData.loggedInCustomer.getCustomerCart().getTotalCost()));
		
		populateMenu();
		populateCart();
		populateComboBoxes();
	}
	
	public void populateMenu()
	{		
		appetizerMenuTextArea.setText(LoggedInAccountData.appetizerMenu.getFormattedMenu());
		entreeMenuTextArea.setText(LoggedInAccountData.entreeMenu.getFormattedMenu());
		spacialsMenuTextArea.setText(LoggedInAccountData.spacialsMenu.getFormattedMenu());
		desertMenuTextArea.setText(LoggedInAccountData.desertMenu.getFormattedMenu());
		drinkMenuTextArea.setText(LoggedInAccountData.drinksMenu.getFormattedMenu());
	}
	
	public void populateCart()
	{		
		if(LoggedInAccountData.loggedInCustomer.getCustomerCart().isEmpty() == false)
		{
			cartTextArea.setText(LoggedInAccountData.loggedInCustomer.getCustomerCart().getFormattedFoodItemsInCart());
		}
	}
	
	public void populateComboBoxes()
	{	
		//Appetizer menu
		ArrayList<String> appetizerMenuItemNames = new ArrayList<String>();
		for(int i = 0; i < LoggedInAccountData.appetizerMenu.items.size(); i++)
		{
			appetizerMenuItemNames.add(LoggedInAccountData.appetizerMenu.items.get(i).getName());
		}
		ObservableList<String> observableList = FXCollections.observableList(appetizerMenuItemNames);
		cBoxAppetizer.setItems(observableList);
		
		//Entree menu
		ArrayList<String> entreeMenuItemNames = new ArrayList<String>();
		for(int i = 0; i < LoggedInAccountData.entreeMenu.items.size(); i++)
		{
			entreeMenuItemNames.add(LoggedInAccountData.entreeMenu.items.get(i).getName());
		}
		ObservableList<String> observableList2 = FXCollections.observableList(entreeMenuItemNames);
		cBoxEntree.setItems(observableList2);
		
		//Spacials menu
		ArrayList<String> spacialMenuItemNames = new ArrayList<String>();
		for(int i = 0; i < LoggedInAccountData.spacialsMenu.items.size(); i++)
		{
			spacialMenuItemNames.add(LoggedInAccountData.spacialsMenu.items.get(i).getName());
		}
		ObservableList<String> observableList3 = FXCollections.observableList(spacialMenuItemNames);
		cBoxSpacial.setItems(observableList3);
		
		//Desserts menu
		ArrayList<String> dessertMenuItemNames = new ArrayList<String>();
		for(int i = 0; i < LoggedInAccountData.desertMenu.items.size(); i++)
		{
			dessertMenuItemNames.add(LoggedInAccountData.desertMenu.items.get(i).getName());
		}
		ObservableList<String> observableList4 = FXCollections.observableList(dessertMenuItemNames);
		cBoxDessert.setItems(observableList4);
		
		//Drinks menu
		ArrayList<String> drinkMenuItemNames = new ArrayList<String>();
		for(int i = 0; i < LoggedInAccountData.drinksMenu.items.size(); i++)
		{
			drinkMenuItemNames.add(LoggedInAccountData.drinksMenu.items.get(i).getName());
		}
		ObservableList<String> observableList5 = FXCollections.observableList(drinkMenuItemNames);
		cBoxDrink.setItems(observableList5);
	}
	
	public void addItemToCartAppetizer() throws IOException
	{
		if(cBoxAppetizer.getValue() != null)
		{
			//Get the item that we are adding
			String selectedItemName = cBoxAppetizer.getValue();
			FoodItem itemToAdd = LoggedInAccountData.appetizerMenu.getItemByName(selectedItemName);
		
			//update the logged in users cart by adding the item
			System.out.println("Adding item to cart");
			LoggedInAccountData.loggedInCustomer.getCustomerCart().addFoodItemToCart(itemToAdd);
		
			//display the added item in the cart area
			cartTextArea.appendText(itemToAdd.getFormattedFoodItem());
			
			if(LoggedInAccountData.loggedInCustomer.hasCoupon())
			{
				LoggedInAccountData.loggedInCustomer.getCustomerCart().setTotalCost(LoggedInAccountData.loggedInCustomer.getCustomerCart().getTotalCost());
			}
			
			totalCostLabel.setText("Total: $" + df.format(LoggedInAccountData.loggedInCustomer.getCustomerCart().getTotalCost()));
		}
	}
	
	public void addItemToCartEntree() throws IOException
	{
		//Get the item that we are adding
		if(cBoxEntree.getValue() != null)
		{
			String selectedItemName = cBoxEntree.getValue();
			
			FoodItem itemToAdd = LoggedInAccountData.entreeMenu.getItemByName(selectedItemName);
			
			//update the logged in users cart by adding the item
			LoggedInAccountData.loggedInCustomer.getCustomerCart().addFoodItemToCart(itemToAdd);
			
			//display the added item in the cart area
			cartTextArea.appendText(itemToAdd.getFormattedFoodItem());
			
			if(LoggedInAccountData.loggedInCustomer.hasCoupon())
			{
				LoggedInAccountData.loggedInCustomer.getCustomerCart().setTotalCost(LoggedInAccountData.loggedInCustomer.getCustomerCart().getTotalCost());
			}
			
			totalCostLabel.setText("Total: $" + df.format(LoggedInAccountData.loggedInCustomer.getCustomerCart().getTotalCost()));
		}
	}
	
	public void addItemToCartSpacial() throws IOException
	{
		if(cBoxSpacial.getValue() != null)
		{
			//Get the item that we are adding
			String selectedItemName = cBoxSpacial.getValue();
			FoodItem itemToAdd = LoggedInAccountData.spacialsMenu.getItemByName(selectedItemName);
		
			//update the logged in users cart by adding the item
			LoggedInAccountData.loggedInCustomer.getCustomerCart().addFoodItemToCart(itemToAdd);
		
			//display the added item in the cart area
			cartTextArea.appendText(itemToAdd.getFormattedFoodItem());
			
			if(LoggedInAccountData.loggedInCustomer.hasCoupon())
			{
				LoggedInAccountData.loggedInCustomer.getCustomerCart().setTotalCost(LoggedInAccountData.loggedInCustomer.getCustomerCart().getTotalCost());
			}
			
			totalCostLabel.setText("Total: $" + df.format(LoggedInAccountData.loggedInCustomer.getCustomerCart().getTotalCost()));
		}
	}
	
	public void addItemToCartDessert() throws IOException
	{
		if(cBoxDessert.getValue() != null)
		{
			//Get the item that we are adding
			String selectedItemName = cBoxDessert.getValue();
			FoodItem itemToAdd = LoggedInAccountData.desertMenu.getItemByName(selectedItemName);
		
			//update the logged in users cart by adding the item
			LoggedInAccountData.loggedInCustomer.getCustomerCart().addFoodItemToCart(itemToAdd);
		
			//display the added item in the cart area
			cartTextArea.appendText(itemToAdd.getFormattedFoodItem());
			
			if(LoggedInAccountData.loggedInCustomer.hasCoupon())
			{
				LoggedInAccountData.loggedInCustomer.getCustomerCart().setTotalCost(LoggedInAccountData.loggedInCustomer.getCustomerCart().getTotalCost());
			}
			
			totalCostLabel.setText("Total: $" + df.format(LoggedInAccountData.loggedInCustomer.getCustomerCart().getTotalCost()));
		}
	}
	
	public void addItemToCartDrink() throws IOException
	{
		if(cBoxDrink.getValue() != null)
		{
			//Get the item that we are adding
			String selectedItemName = cBoxDrink.getValue();
			FoodItem itemToAdd = LoggedInAccountData.drinksMenu.getItemByName(selectedItemName);
		
			//update the logged in users cart by adding the item
			LoggedInAccountData.loggedInCustomer.getCustomerCart().addFoodItemToCart(itemToAdd);
		
			//display the added item in the cart area
			cartTextArea.appendText(itemToAdd.getFormattedFoodItem());
			
			if(LoggedInAccountData.loggedInCustomer.hasCoupon())
			{
				LoggedInAccountData.loggedInCustomer.getCustomerCart().setTotalCost(LoggedInAccountData.loggedInCustomer.getCustomerCart().getTotalCost());
			}
			
			totalCostLabel.setText("Total: $" + df.format(LoggedInAccountData.loggedInCustomer.getCustomerCart().getTotalCost()));
		}
	}
	
	public void removeItemFromCartAppetizer() throws IOException
	{
		if(cBoxAppetizer.getValue() != null)
		{
			//Get selected item to remove
			String selectedItemName = cBoxAppetizer.getValue();
			FoodItem itemToRemove = LoggedInAccountData.appetizerMenu.getItemByName(selectedItemName);
		
			//Remove the item from the users cart
			if(LoggedInAccountData.loggedInCustomer.getCustomerCart().itemExistsInCart(itemToRemove))
			{
				LoggedInAccountData.loggedInCustomer.getCustomerCart().removeItemFromCart(itemToRemove);
				System.out.println("Removing item from cart.");
			}
		
			//Update the cart area display
			cartTextArea.setText("");
			cartTextArea.setText(LoggedInAccountData.loggedInCustomer.getCustomerCart().getFormattedFoodItemsInCart());
			
			if(LoggedInAccountData.loggedInCustomer.hasCoupon())
			{
				LoggedInAccountData.loggedInCustomer.getCustomerCart().setTotalCost(LoggedInAccountData.loggedInCustomer.getCustomerCart().getTotalCost());
			}
			
			totalCostLabel.setText("Total: $" + df.format(LoggedInAccountData.loggedInCustomer.getCustomerCart().getTotalCost()));
		}
	}
	
	public void removeItemFromCartEntree() throws IOException
	{
		if(cBoxEntree.getValue() != null)
		{
			//Get selected item to remove
			String selectedItemName = cBoxEntree.getValue();
			FoodItem itemToRemove = LoggedInAccountData.entreeMenu.getItemByName(selectedItemName);
		
			//Remove the item from the users cart
			if(LoggedInAccountData.loggedInCustomer.getCustomerCart().itemExistsInCart(itemToRemove))
			{
				LoggedInAccountData.loggedInCustomer.getCustomerCart().removeItemFromCart(itemToRemove);
				System.out.println("Removing item from cart.");
			}
		
			//Update the cart area display
			cartTextArea.setText("");
			cartTextArea.setText(LoggedInAccountData.loggedInCustomer.getCustomerCart().getFormattedFoodItemsInCart());
			
			if(LoggedInAccountData.loggedInCustomer.hasCoupon())
			{
				LoggedInAccountData.loggedInCustomer.getCustomerCart().setTotalCost(LoggedInAccountData.loggedInCustomer.getCustomerCart().getTotalCost());
			}
			
			totalCostLabel.setText("Total: $" + df.format(LoggedInAccountData.loggedInCustomer.getCustomerCart().getTotalCost()));
		}
	}
	
	public void removeItemFromCartSpacial() throws IOException
	{
		if(cBoxSpacial.getValue() != null)
		{
			//Get selected item to remove
			String selectedItemName = cBoxSpacial.getValue();
			FoodItem itemToRemove = LoggedInAccountData.spacialsMenu.getItemByName(selectedItemName);
		
			//Remove the item from the users cart
			if(LoggedInAccountData.loggedInCustomer.getCustomerCart().itemExistsInCart(itemToRemove))
			{
				LoggedInAccountData.loggedInCustomer.getCustomerCart().removeItemFromCart(itemToRemove);
				System.out.println("Removing item from cart.");
			}
		
			//Update the cart area display
			cartTextArea.setText("");
			cartTextArea.setText(LoggedInAccountData.loggedInCustomer.getCustomerCart().getFormattedFoodItemsInCart());
			
			if(LoggedInAccountData.loggedInCustomer.hasCoupon())
			{
				LoggedInAccountData.loggedInCustomer.getCustomerCart().setTotalCost(LoggedInAccountData.loggedInCustomer.getCustomerCart().getTotalCost());
			}
			
			totalCostLabel.setText("Total: $" + df.format(LoggedInAccountData.loggedInCustomer.getCustomerCart().getTotalCost()));
		}
	}
	
	public void removeItemFromCartDessert() throws IOException
	{
		if(cBoxDessert.getValue() != null)
		{
			//Get selected item to remove
			String selectedItemName = cBoxDessert.getValue();
			FoodItem itemToRemove = LoggedInAccountData.desertMenu.getItemByName(selectedItemName);
		
			//Remove the item from the users cart
			if(LoggedInAccountData.loggedInCustomer.getCustomerCart().itemExistsInCart(itemToRemove))
			{
				LoggedInAccountData.loggedInCustomer.getCustomerCart().removeItemFromCart(itemToRemove);
				System.out.println("Removing item from cart.");
			}
		
			//Update the cart area display
			cartTextArea.setText("");
			cartTextArea.setText(LoggedInAccountData.loggedInCustomer.getCustomerCart().getFormattedFoodItemsInCart());
			
			if(LoggedInAccountData.loggedInCustomer.hasCoupon())
			{
				LoggedInAccountData.loggedInCustomer.getCustomerCart().setTotalCost(LoggedInAccountData.loggedInCustomer.getCustomerCart().getTotalCost());
			}
			
			totalCostLabel.setText("Total: $" + df.format(LoggedInAccountData.loggedInCustomer.getCustomerCart().getTotalCost()));
		}
	}
	
	public void removeItemFromCartDrink() throws IOException
	{
		if(cBoxDrink.getValue() != null)
		{
			//Get selected item to remove
			String selectedItemName = cBoxDrink.getValue();
			FoodItem itemToRemove = LoggedInAccountData.drinksMenu.getItemByName(selectedItemName);
		
			//Remove the item from the users cart
			if(LoggedInAccountData.loggedInCustomer.getCustomerCart().itemExistsInCart(itemToRemove))
			{
				LoggedInAccountData.loggedInCustomer.getCustomerCart().removeItemFromCart(itemToRemove);
				System.out.println("Removing item from cart.");
			}
		
			//Update the cart area display
			cartTextArea.setText("");
			cartTextArea.setText(LoggedInAccountData.loggedInCustomer.getCustomerCart().getFormattedFoodItemsInCart());
			
			if(LoggedInAccountData.loggedInCustomer.hasCoupon())
			{
				LoggedInAccountData.loggedInCustomer.getCustomerCart().setTotalCost(LoggedInAccountData.loggedInCustomer.getCustomerCart().getTotalCost());
			}
			
			totalCostLabel.setText("Total: $" + df.format(LoggedInAccountData.loggedInCustomer.getCustomerCart().getTotalCost()));
		}
	}
	
}
